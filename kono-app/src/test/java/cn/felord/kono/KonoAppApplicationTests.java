package cn.felord.kono;

import cn.felord.kono.configuration.CaptchaCacheStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.web.servlet.MockMvc;

/**
 * The type Kono app application tests.
 */
@SpringBootTest
@AutoConfigureMockMvc
class KonoAppApplicationTests {
    /**
     * The Mock mvc.
     */
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CaptchaCacheStorage captchaCacheStorage;
    @Autowired
    StringRedisTemplate stringRedisTemplate;



    @Test
    public void test (){
        String put = captchaCacheStorage.put("9527");
        System.out.println("put = " + put);
    }

    @Test
    public void test1(){
        // 
        stringRedisTemplate.boundValueOps("hello").set("world");
    }

}
