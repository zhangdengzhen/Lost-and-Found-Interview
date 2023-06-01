package top.zhangdengzhen.demo.server;

import org.apache.ibatis.annotations.Param;
import top.zhangdengzhen.demo.dao.Problem;
import top.zhangdengzhen.demo.dao.ProblemImg;

import java.util.List;

public interface ProblemServer {
    int insert(Problem problem);
    int deletebyid(Integer id);
    List<Problem> selectbyOpenid_Type(String openid);
    List<ProblemImg> selectByPage(int currIndex , int pageSize);
    Integer selectCount();
}
