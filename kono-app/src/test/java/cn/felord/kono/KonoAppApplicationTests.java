package cn.felord.kono;

import cn.felord.kono.beanmapping.BeanMapping;
import cn.felord.kono.controller.test.UserController;
import cn.felord.kono.entity.UserInfo;
import cn.felord.kono.entity.UserInfoVO;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.core.IsNull.notNullValue;

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
    /**
     * The Bean mapping.
     */
    @Autowired
    BeanMapping beanMapping;

    /**
     * 测试全局异常处理.
     *
     * @throws Exception the exception
     * @see UserController#getUserInfo()
     */
    @Test
    void testGlobalExceptionHandler() throws Exception {

        String rtnJsonStr = "{\n" +
                "    \"code\": 700,\n" +
                "    \"data\": null,\n" +
                "    \"msg\": \"test global exception handler\",\n" +
                "    \"identifier\": \"-9999\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.get("/user/get"))
                .andExpect(MockMvcResultMatchers.content()
                        .json(rtnJsonStr))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试统一返回体.
     *
     * @throws Exception the exception
     * @see UserController#getUserVO()
     */
    @Test
    void testUnifiedReturnStruct() throws Exception {
//        "{\"code\":200,\"data\":{\"name\":\"felord.cn\",\"age\":18,\"addTime\":\"2020-07-30T13:08:53.201\"},\"msg\":\"\",\"identifier\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.get("/user/vo"))
                .andExpect(MockMvcResultMatchers.jsonPath("code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("data.name", Is.is("felord.cn")))
                .andExpect(MockMvcResultMatchers.jsonPath("data.age", Is.is(18)))
                .andExpect(MockMvcResultMatchers.jsonPath("data.addTime", Is.is(notNullValue())))
                .andDo(MockMvcResultHandlers.print());
    }


    /**
     * 测试 mapStruct类型转换.
     *
     * @see BeanMapping
     */
    @Test
    void testMapStruct() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("felord.cn");
        userInfo.setAge(18);
        UserInfoVO userInfoVO = beanMapping.toUserInfoVo(userInfo);

        Assertions.assertEquals(userInfoVO.getName(), userInfo.getName());
        Assertions.assertNotNull(userInfoVO.getAddTime());
    }

}
