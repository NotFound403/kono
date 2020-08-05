package cn.felord.kono.configuration;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.mapper.MapperFactoryBean;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @author a
 * @since 16:18
 **/
public class MybatisMapperFactoryBean<T> extends MapperFactoryBean<T> {


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
        Method[] methods = mapperInterface.getMethods();




        System.out.println("methods = " + Arrays.toString(methods));



        boolean assignableFrom = CrudMapper.class.isAssignableFrom(mapperInterface);

        System.out.println("assignableFrom = " + assignableFrom);

      Type[] genericInterfaces =   mapperInterface.getGenericInterfaces();

        System.out.println("genericInterfaces = " + Arrays.toString(genericInterfaces));


        Type genericInterface1 = genericInterfaces[0];

        ParameterizedType genericInterface =(ParameterizedType) genericInterface1;


        Type[] actualTypeArguments = genericInterface.getActualTypeArguments();
        System.out.println("genericInterface.getActualTypeArguments() = " + Arrays.toString(actualTypeArguments));

        Class<?> clientUser = (Class<?>)actualTypeArguments[0];
        Class<?> type1 = (Class<?>)actualTypeArguments[1];


        System.out.println("type = " + clientUser.getName());


        notNull(mapperInterface, "Property 'mapperInterface' is required");

        Configuration configuration = getSqlSession().getConfiguration();
        if (isAddToConfig() && !configuration.hasMapper(mapperInterface)) {
            try {
                configuration.addMapper(mapperInterface);

                HashMap<String, Object> map = new HashMap<>();





                SQL where = new SQL().SELECT("*").FROM("client_user").WHERE("user_id = #{0}");



                SqlSource sqlSource = new SqlSourceBuilder(configuration).parse(where.toString(), type1, map);

                String id = mapperInterface.getName() + ".findById";
                List<ResultMap> resultMaps = getStatementResultMaps(configuration, clientUser, id);
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration,
                        id,
                        sqlSource,
                        SqlCommandType.SELECT)
                        .resultMaps(resultMaps)
                        .build();

                configuration.addMappedStatement(mappedStatement);


            } catch (Exception e) {
                logger.error("Error while adding the mapper '" + mapperInterface + "' to configuration.", e);
                throw new IllegalArgumentException(e);
            } finally {
                ErrorContext.instance().reset();
            }
        }
    }


    private List<ResultMap> getStatementResultMaps(Configuration configuration,
                                                   Class<?> resultType,
                                                   String statementId) {
        List<ResultMap> resultMaps = new ArrayList<>();

        ResultMap inlineResultMap = new ResultMap.Builder(
                configuration,
                statementId + "-Inline",
                resultType,
                new ArrayList<>(),
                null).build();
        resultMaps.add(inlineResultMap);

        return resultMaps;
    }
}
