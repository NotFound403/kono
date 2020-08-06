package cn.felord.kono.entity;

import cn.felord.kono.mybatis.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author system
 * @since 2020-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClientUserRole   {

    private static final long serialVersionUID = 1L;
    @PrimaryKey
    private String roleId;

    private String userId;

    private Integer roleCode;

    private String roleName;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    private Boolean enabled;


}
