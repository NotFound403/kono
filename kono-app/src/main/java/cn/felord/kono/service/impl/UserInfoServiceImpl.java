package cn.felord.kono.service.impl;

import cn.felord.kono.entity.UserInfo;
import cn.felord.kono.mapper.UserInfoMapper;
import cn.felord.kono.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author system
 * @since 2020-08-17
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
