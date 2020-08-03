package cn.felord.kono.mapper;

import cn.felord.kono.entity.ServeUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.PagingAndSortingRepository;

@Mapper
public interface ServeUserMapper extends PagingAndSortingRepository<ServeUser, String> {
    ServeUser findServeUserByUserId(String userId);
}
