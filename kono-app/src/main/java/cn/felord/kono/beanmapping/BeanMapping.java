package cn.felord.kono.beanmapping;

import cn.felord.kono.entity.UserInfo;
import cn.felord.kono.entity.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

/**
 * @author felord.cn
 * @since 16:09
 **/
@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface BeanMapping {

    @Mapping(target = "addTime", expression = "java(LocalDateTime.now())")
    UserInfoVO toUserInfoVo(UserInfo userInfo);

}
