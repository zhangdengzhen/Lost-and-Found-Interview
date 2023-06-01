package top.zhangdengzhen.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.zhangdengzhen.demo.dao.Users;

import java.util.List;

@Repository
public interface UsersMapper extends BaseMapper<Users> {
    List<Users> selectPage(@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
}
