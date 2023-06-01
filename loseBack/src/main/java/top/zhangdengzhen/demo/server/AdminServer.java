package top.zhangdengzhen.demo.server;

import top.zhangdengzhen.demo.dao.Admin;

import java.util.List;

public interface AdminServer {
    int insert(Admin admin);
    int delete(int id);
    int update(Admin admin);
    List<Admin> getbyUser(String username,String password);
    List<Admin> getbyname(String username);
}
