package cn.felord.kono.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.mybatis.NamespaceStrategy;

@Configuration
public class MybatisNamespaceStrategyConfiguration {
    @Bean
    public NamespaceStrategy mybatisNamespaceStrategy(){
        return new NamespaceStrategy() {
            @Override
            public String getNamespace(Class<?> domainType) {
                return "cn.felord.kono.mapper."+domainType.getSimpleName()+"Mapper";
            }
        };
    }
}
