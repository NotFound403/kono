package cn.felord.kono.service.impl;

import cn.felord.kono.entity.UserInfo;
import cn.felord.kono.mapper.UserInfoMapper;
import cn.felord.kono.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
