package cn.felord.kono.mybatis;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.mapper.MapperFactoryBean;

import static org.springframework.util.Assert.notNull;

/**
 * @author felord.cn
 * @since 16:18
 **/
public class MybatisMapperFactoryBean<T extends CrudMapper<?, ?>> extends MapperFactoryBean<T> {


    public MybatisMapperFactoryBean() {
        // empty
    }

    public MybatisMapperFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
    }

    @Override
    protected void checkDaoConfig() {
        notNull(super.getSqlSessionTemplate(), "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
        Class<T> mapperInterface = super.getMapperInterface();
        notNull(mapperInterface, "Property 'mapperInterface' is required");

        Configuration configuration = getSqlSession().getConfiguration();
        if (isAddToConfig() && !configuration.hasMapper(mapperInterface)) {
            try {
                configuration.addMapper(mapperInterface);
                 // 一个写入 SQL映射的时机
                CrudMapperProvider crudMapperProvider = new CrudMapperProvider(mapperInterface);
                crudMapperProvider.addMappedStatements(configuration);

            } catch (Exception e) {
                logger.error("Error while adding the mapper '" + mapperInterface + "' to configuration.", e);
                throw new IllegalArgumentException(e);
            } finally {
                ErrorContext.instance().reset();
            }
        }
    }

}
