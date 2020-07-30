package cn.felord.kono.controller.test;

import cn.felord.kono.advice.BusinessException;
import cn.felord.kono.beanmapping.BeanMapping;
import cn.felord.kono.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User controller.
 *
 * @author a
 * @since 15 :49
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private BeanMapping beanMapping;

    /**
     * 测试统一异常.
     *
     * @return the user info
     */
    @GetMapping("/get")
    public UserInfo getUserInfo() {
        throw new BusinessException("test global exception handler");
    }


    /**
     * 测试统一返回体.
     *
     * @return the user vo
     */
    @GetMapping("/vo")
    public UserInfo getUserVO() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("felord.cn");
        userInfo.setAge(18);
       return beanMapping.toUserInfoVo(userInfo);
    }
}
