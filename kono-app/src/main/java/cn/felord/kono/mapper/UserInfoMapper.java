package cn.felord.kono.mapper;


import cn.felord.kono.entity.UserInfo;


/**
 * 数据库脚本在resources/sql/ddl.sql中
 */

public interface UserInfoMapper {
    /**
     * Save user info.
     *
     * @param userInfo the user info
     * @return the user info
     */
    Integer save(UserInfo userInfo);

    /**
     * Find by id user info.
     *
     * @param userId the user id
     * @return the user info
     */
    UserInfo findById(Integer userId);
}
