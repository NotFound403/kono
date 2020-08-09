package cn.felord.kono.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author felord.cn
 * @since 15:43
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID = -8938650956516110149L;
    @TableId
    private Long userId;
    private String name;
    private Integer age;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }
}
