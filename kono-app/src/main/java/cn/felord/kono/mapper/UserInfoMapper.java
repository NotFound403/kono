package cn.felord.kono.mapper;


import cn.felord.kono.entity.UserInfo;
import cn.felord.kono.mybatis.CrudMapper;

/**
 * 数据库脚本在resources/sql/ddl.sql中
 */

public interface UserInfoMapper extends CrudMapper<UserInfo,String> {

}
