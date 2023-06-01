package top.zhangdengzhen.demo.server.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.Problem;
import top.zhangdengzhen.demo.dao.ProblemImg;
import top.zhangdengzhen.demo.mapper.ProblemMapper;
import top.zhangdengzhen.demo.server.ProblemServer;

import java.util.List;

@Service
public class ProblemServerImp implements ProblemServer {

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public int insert(Problem problem) {
        return problemMapper.insert(problem);
    }

    @Override
    public int deletebyid(Integer id) {
        return problemMapper.deleteById(id);
    }

    @Override
    public List<Problem> selectbyOpenid_Type(String openid) {
        QueryWrapper<Problem> problemQueryWrapper = new QueryWrapper<>();
        problemQueryWrapper.eq("openid",openid);
        return problemMapper.selectList(problemQueryWrapper);
    }

    @Override
    public List<ProblemImg> selectByPage(int currIndex, int pageSize) {
        return problemMapper.selectByPage(currIndex,pageSize);
    }

    @Override
    public Integer selectCount() {
        return problemMapper.selectCount();
    }

}
