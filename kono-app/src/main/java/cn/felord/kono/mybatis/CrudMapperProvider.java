package cn.felord.kono.mybatis;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Crud mapper provider.
 *
 * @author felord.cn
 */
public class CrudMapperProvider {
    /**
     * The constant HUMP_PATTERN.
     */
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");

    /**
     * The Mapper interface.
     */
    private final Class<?> mapperInterface;
    /**
     * The Entity type.
     */
    private final Class<?> entityType;
    /**
     * The Parameter type.
     */
    private final Class<?> primaryKeyType;
    /**
     * The Property descriptors.
     */
    private final List<Field> columnFields;
    /**
     * The Identifer.
     */
    private final String identifer;
    /**
     * The Table.
     */
    private final String table;

    /**
     * Instantiates a new Insert provider.
     *
     * @param mapperInterface the mapper interface
     */
    public CrudMapperProvider(Class<? extends CrudMapper<?, ?>> mapperInterface) {
        this.mapperInterface = mapperInterface;
        Type[] genericInterfaces = mapperInterface.getGenericInterfaces();
        Type genericInterface1 = genericInterfaces[0];
        ParameterizedType genericInterface = (ParameterizedType) genericInterface1;


        Type[] actualTypeArguments = genericInterface.getActualTypeArguments();
        this.entityType = (Class<?>) actualTypeArguments[0];
        this.primaryKeyType = (Class<?>) actualTypeArguments[1];

        Field[] declaredFields = this.entityType.getDeclaredFields();

        identifer = Stream.of(this.entityType.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .findAny()
                .map(Field::getName)
                .orElseThrow(() -> new IllegalArgumentException(String.format("no @PrimaryKey found in %s", this.entityType.getName())));


        columnFields = Stream.of(declaredFields)
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toList());
        this.table = camelCaseToMapUnderscore(entityType.getSimpleName()).replaceFirst("_", "");
    }

    /**
     * Find by id.
     *
     * @param configuration the configuration
     */
    private void findById(Configuration configuration) {


        String[] COLUMNS = columnFields.stream()
                .map(Field::getName)
                .map(CrudMapperProvider::camelCaseToMapUnderscore)
                .toArray(String[]::new);

        String CONDITION = primaryKey().concat(" = #{" + identifer + "}");

        String findSQL = new SQL()
                .SELECT(COLUMNS)
                .FROM(table)
                .WHERE(CONDITION)
                .toString();
        String findId = mapperInterface.getName().concat(".").concat("findById");

        Map<String, Object> additionalParameters = new HashMap<>();

        doAddMappedStatement(configuration, findId, findSQL, SqlCommandType.SELECT, primaryKeyType, additionalParameters);


    }


    /**
     * Insert.
     *
     * @param configuration the configuration
     */
    private void insert(Configuration configuration) {

        String[] COLUMNS = columnFields.stream()
                .map(Field::getName)
                .map(CrudMapperProvider::camelCaseToMapUnderscore)
                .toArray(String[]::new);

        String[] VALUES = columnFields.stream()
                .map(Field::getName)
                .map(name -> String.format("#{%s}", name))
                .toArray(String[]::new);

        String insertSQL = new SQL()
                .INSERT_INTO(table)
                .INTO_COLUMNS(COLUMNS)
                .INTO_VALUES(VALUES)
                .toString();

        String insertId = mapperInterface.getName().concat(".").concat("insert");

        Map<String, Object> additionalParameters = new HashMap<>();

        doAddMappedStatement(configuration, insertId, insertSQL, SqlCommandType.INSERT, entityType, additionalParameters);
    }


    /**
     * Delete by id.
     *
     * @param configuration the configuration
     */
    private void deleteById(Configuration configuration) {
        String CONDITION = primaryKey().concat(" = #{" + identifer + "}");

        String deleteSQL = new SQL()
                .DELETE_FROM(table)
                .WHERE(CONDITION).toString();

        String deleteId = mapperInterface.getName().concat(".").concat("deleteById");

        Map<String, Object> additionalParameters = new HashMap<>();

        doAddMappedStatement(configuration, deleteId, deleteSQL, SqlCommandType.DELETE, primaryKeyType, additionalParameters);
    }


    /**
     * Update by id.
     *
     * @param configuration the configuration
     */
    private void updateById(Configuration configuration) {

        String[] SETS = columnFields.stream()
                .map(Field::getName)
                .map(name -> String.format("%s = #{%s}", camelCaseToMapUnderscore(name), name))
                .toArray(String[]::new);

        String CONDITION = primaryKey().concat(" = #{" + identifer + "}");

        String updateSQL = new SQL().UPDATE(table)
                .SET(SETS)
                .WHERE(CONDITION).toString();

        String updateId = mapperInterface.getName().concat(".").concat("updateById");

        Map<String, Object> additionalParameters = new HashMap<>();
        doAddMappedStatement(configuration, updateId, updateSQL, SqlCommandType.UPDATE, entityType, additionalParameters);

    }


    /**
     * Add mapped statement.
     *
     * @param configuration the configuration
     */
    public void addMappedStatements(Configuration configuration) {
        insert(configuration);
        findById(configuration);
        updateById(configuration);
        deleteById(configuration);
    }


    /**
     * Do add mapped statement.
     *
     * @param configuration        the configuration
     * @param id                   the id
     * @param originalSql          the original sql
     * @param sqlCommandType       the sql command type
     * @param parameterType        the parameter type
     * @param additionalParameters the additional parameters
     */
    private void doAddMappedStatement(Configuration configuration,
                                      String id,
                                      String originalSql,
                                      SqlCommandType sqlCommandType,
                                      Class<?> parameterType,
                                      Map<String, Object> additionalParameters) {
        SqlSource sqlSource = new SqlSourceBuilder(configuration).parse(originalSql, parameterType, additionalParameters);


        List<ResultMap> resultMaps = getStatementResultMaps(configuration, entityType, id);
        MappedStatement mappedStatement = new MappedStatement.Builder(configuration,
                id,
                sqlSource,
                sqlCommandType)
                .resultMaps(resultMaps)
                .build();

        configuration.addMappedStatement(mappedStatement);
    }


    /**
     * Primary key string.
     *
     * @return the string
     */
    private String primaryKey() {

        return camelCaseToMapUnderscore(identifer);
    }


    /**
     * Camel case to map underscore string.
     *
     * @param str the str
     * @return the string
     */
    private static String camelCaseToMapUnderscore(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * Gets statement result maps.
     *
     * @param configuration the configuration
     * @param resultType    the result type
     * @param statementId   the statement id
     * @return the statement result maps
     */
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
