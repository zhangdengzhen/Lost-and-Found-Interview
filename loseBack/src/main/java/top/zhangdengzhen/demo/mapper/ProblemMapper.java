package top.zhangdengzhen.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.zhangdengzhen.demo.dao.Img;
import top.zhangdengzhen.demo.dao.Problem;
import top.zhangdengzhen.demo.dao.ProblemImg;

import java.util.List;

@Repository
public interface ProblemMapper extends BaseMapper<Problem> {
    List<ProblemImg> selectByPage(@Param("currIndex") int currIndex , @Param("pageSize") int pageSize);
    Integer selectCount();
}
