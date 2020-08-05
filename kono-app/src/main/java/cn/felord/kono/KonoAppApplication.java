package cn.felord.kono;

import cn.felord.kono.mybatis.MybatisMapperFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = {"cn.felord.kono.mapper"},factoryBean = MybatisMapperFactoryBean.class)
@SpringBootApplication
public class KonoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(KonoAppApplication.class, args);
    }

}
