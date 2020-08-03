package cn.felord.kono.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 服务人员信息
 * </p>
 *
 * @author system
 * @since 2020-07-31
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ServeUser {

    private static final long serialVersionUID = 1L;
    @Id
    private String userId;

    private String nickName;
    /**
     * 手机
     */
    private String phoneNumber;

    /**
     * 身份证
     */
    private String idNumber;

    /**
     * 身份证照片正面
     */
    private String imageFront;

    /**
     * 身份证照片反面
     */
    private String imageBack;

    /**
     * 现住址
     */
    private String address;

    /**
     * 所属机构
     */
    private String orgId;

    private Boolean enabled;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;
   @Column("USER_ID")
    private Set<ClientUserRole> clientUserRoles;


}
