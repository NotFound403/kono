package cn.felord.kono;

import cn.felord.kono.configuration.CaptchaCacheStorage;
import cn.felord.oss.Storage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeTypeUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
    @Autowired
    @Qualifier("minioStorage")
    Storage storage;

    @Test
    public void testOss() throws Exception {
        File file = new File("C:\\Users\\xfa00\\IdeaProjects\\kono\\456.jpg");

        InputStream inputStream = new FileInputStream(file);

        storage.putObject("img","pic_122",inputStream, MimeTypeUtils.IMAGE_JPEG_VALUE);
    }



    @Test
    public void test() {
        String put = captchaCacheStorage.put("9527");
        System.out.println("put = " + put);
    }

    @Test
    public void test1() {
        // 
        stringRedisTemplate.boundValueOps("hello").set("world");
    }



}
