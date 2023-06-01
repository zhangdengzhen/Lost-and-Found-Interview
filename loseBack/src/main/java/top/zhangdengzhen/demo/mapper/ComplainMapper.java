package top.zhangdengzhen.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.zhangdengzhen.demo.dao.Complain;

import java.util.List;
@Repository
public interface ComplainMapper extends BaseMapper<Complain> {
    List<Complain> selectPage(@Param("currIndex") int currIndex , @Param("pageSize") int pageSize);
    int Complaincount();
}
