package cn.felord.kono.controller;

import cn.felord.kono.entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author a
 * @since 15:49
 **/
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/get")
    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("felord.cn");
        userInfo.setAge(18);
        return userInfo;
    }

}
