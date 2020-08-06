package cn.felord.kono;

import cn.felord.kono.beanmapping.BeanMapping;
import cn.felord.kono.controller.test.UserController;
import cn.felord.kono.entity.ClientUser;
import cn.felord.kono.entity.ClientUserRole;
import cn.felord.kono.entity.UserInfo;
import cn.felord.kono.entity.UserInfoVO;
import cn.felord.kono.mapper.ClientUserMapper;
import cn.felord.kono.mapper.ClientUserRoleMapper;
import cn.felord.kono.mapper.UserInfoMapper;
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

import java.time.LocalDateTime;

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
    @Autowired
    ClientUserMapper clientUserMapper;
    @Autowired
    ClientUserRoleMapper clientUserRoleMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    void testCrudMapperFind(){

        ClientUser byId = clientUserMapper.findById("1289391430608330754");

        System.out.println("byId = " + byId);
    }



    @Test
    public void testUser(){
        UserInfo byId = userInfoMapper.findById("");
        int insert = userInfoMapper.insert(null);
        int i = userInfoMapper.deleteById("");

        int i1 = userInfoMapper.updateById(null);


    }



    @Test
    void testCrudMapperInsert(){
        ClientUser clientUser = new ClientUser();
        clientUser.setUserId("1111111111111111111");
        clientUser.setNickName("felord.cn");
        clientUser.setAddTime(LocalDateTime.now());

        clientUserMapper.insert(clientUser);
    }


    @Test
    void testCrudMapperUpdate(){
        ClientUser clientUser = new ClientUser();
        clientUser.setUserId("1111111111111111111");
        clientUser.setNickName("felord.cn");
        clientUser.setAddTime(LocalDateTime.now());
        clientUser.setProvince("HN");
        clientUser.setCountry("CN");

        clientUserMapper.updateById(clientUser);
    }

    @Test
    void testCrudMapperDelete(){

        ClientUserRole byId = clientUserRoleMapper.findById("1290177637255876610");
        System.out.println("byId = " + byId);

//        clientUserMapper.deleteById("1111111111111111111");


    }




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
