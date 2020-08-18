package cn.felord.kono.beanmapping;

import org.mapstruct.Mapper;

import java.time.LocalDateTime;

/**
 * @author felord.cn
 * @since 16:09
 **/
@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface BeanMapping {


}
