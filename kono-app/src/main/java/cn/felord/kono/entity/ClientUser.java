package cn.felord.kono.entity;

import cn.felord.kono.mybatis.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author system
 * @since 2020-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClientUser   {

    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标示
     */
    @PrimaryKey
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 用户昵称
     */
    private String nickName;

    private String country;

    private String province;

    private String city;

    /**
     * 微信 openId
     */
    private String wxOpenId;

    /**
     * 注册时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 最近一次登录ip
     */
    private String lastLoginIp;

    /**
     * 最近一次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 过期时间，默认2199-01-01
     */
    private LocalDateTime invalidTime;

    /**
     * 是否可用(1可用，0已删除)
     */
    private Boolean enabled;

    /**
     * 0 未知 1 男 2 女
     */
    private Boolean gender;


}
