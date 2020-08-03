package cn.felord.kono.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


/**
 * mybatis configuration.
 *
 * @author felord.cn
 */
@MapperScan("cn.felord.kono.mapper")
@Configuration
public class MybatisConfiguration {

}
