package cn.felord.kono.configuration;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.mybatis.MyBatisDataAccessStrategy;
import org.springframework.data.jdbc.mybatis.NamespaceStrategy;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;


@Configuration(proxyBeanMethods = false)
public class JDBCMybatisConfiguration extends AbstractJdbcConfiguration {

    private @Autowired
    SqlSession session;
    private @Autowired
    NamespaceStrategy mybatisNamespaceStrategy;


    /*
     * (non-Javadoc)
     * @see org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration#dataAccessStrategyBean(org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations, org.springframework.data.jdbc.core.convert.JdbcConverter, org.springframework.data.jdbc.core.mapping.JdbcMappingContext)
     */
    @Bean
    @Override
    public DataAccessStrategy dataAccessStrategyBean(NamedParameterJdbcOperations operations, JdbcConverter jdbcConverter,
                                                     JdbcMappingContext context, Dialect dialect) {

        return MyBatisDataAccessStrategy.createCombinedAccessStrategy(context, jdbcConverter, operations, session,mybatisNamespaceStrategy, dialect);
    }
}