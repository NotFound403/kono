package cn.felord.kono.entity;

import cn.felord.kono.mybatis.PrimaryKey;
import lombok.Data;

import java.io.Serializable;

/**
 * @author felord.cn
 * @since 15:43
 **/
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -8938650956516110149L;
    @PrimaryKey
    private Long userId;
    private String name;
    private Integer age;
}
