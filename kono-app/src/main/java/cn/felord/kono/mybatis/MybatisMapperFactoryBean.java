package cn.felord.kono.mybatis;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.mapper.MapperFactoryBean;

import static org.springframework.util.Assert.notNull;

/**
 * The type Mybatis mapper factory bean.
 *
 * @param <T> the type parameter
 * @author felord.cn
 * @since 16 :18
 */
public class MybatisMapperFactoryBean<T extends CrudMapper<?, ?>> extends MapperFactoryBean<T> {


    /**
     * Instantiates a new Mybatis mapper factory bean.
     */
    public MybatisMapperFactoryBean() {
        // empty
    }

    /**
     * Instantiates a new Mybatis mapper factory bean.
     *
     * @param mapperInterface the mapper interface
     */
    public MybatisMapperFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
    }

    @Override
    protected void checkDaoConfig() {
        notNull(super.getSqlSessionTemplate(), "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
        Class<T> mapperInterface = super.getMapperInterface();
        notNull(mapperInterface, "Property 'mapperInterface' is required");

        Configuration configuration = getSqlSession().getConfiguration();

        if (isAddToConfig()) {
            try {
                // 判断Mapper 是否注册
                if (!configuration.hasMapper(mapperInterface)) {
                    configuration.addMapper(mapperInterface);
                }
                // 只有继承了CrudMapper 再进行切入
                if (CrudMapper.class.isAssignableFrom(mapperInterface)) {
                    // 一个注册SQL映射的时机
                    CrudMapperProvider crudMapperProvider = new CrudMapperProvider(mapperInterface);
                    // 注册 MappedStatement
                    crudMapperProvider.addMappedStatements(configuration);
                }
            } catch (Exception e) {
                logger.error("Error while adding the mapper '" + mapperInterface + "' to configuration.", e);
                throw new IllegalArgumentException(e);
            } finally {
                ErrorContext.instance().reset();
            }
        }
    }

}
