package cn.felord.kono.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author a
 * @since 15:43
 **/
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -8938650956516110149L;
    private String name;
    private Integer age;


}
