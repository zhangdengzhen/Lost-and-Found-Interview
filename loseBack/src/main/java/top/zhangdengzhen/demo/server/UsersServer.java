package top.zhangdengzhen.demo.server;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.Users;

import java.util.List;


public interface UsersServer {

    int insert(Users users);
    int delete(String openid);
    Users selectByOpenid(String openid);
    int update(Users users);
    List<Users> selectAll();
    Long count();
    List<Users> selectPage(int currIndex, int pageSize);
}
