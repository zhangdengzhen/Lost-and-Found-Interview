package top.zhangdengzhen.demo.server;

import org.apache.ibatis.annotations.Param;
import top.zhangdengzhen.demo.dao.Complain;

import java.util.List;

public interface ComplainServer {
    int insert(Complain complain);
    int delete(int id);
    List<Complain> selectPage( int currIndex , int pageSize);
    int Complaincount();
}
