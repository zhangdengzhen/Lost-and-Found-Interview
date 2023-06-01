package top.zhangdengzhen.demo.server.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.Admin;
import top.zhangdengzhen.demo.mapper.AdminMapper;
import top.zhangdengzhen.demo.server.AdminServer;

import java.util.List;

@Service
public class AdminServerImp implements AdminServer {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public int delete(int id) {
        return adminMapper.deleteById(id);
    }

    @Override
    public int update(Admin admin) {
        UpdateWrapper objectUpdateWrapper = new UpdateWrapper();
        objectUpdateWrapper.eq("id",admin.getId());
        if(admin.getUsername().length()>0){
            objectUpdateWrapper.set("username",admin.getUsername());
        }
        if(admin.getPassword().length()>0){
            objectUpdateWrapper.set("password",admin.getPassword());
        }
        if(admin.getEmail().length()>0){
            objectUpdateWrapper.set("email",admin.getEmail());
        }
        if(admin.getPhone().length()>0){
            objectUpdateWrapper.set("phone",admin.getPhone());
        }
        if(admin.getAvator().length()>0){
            objectUpdateWrapper.set("avator",admin.getAvator());
        }
        return adminMapper.update(null,objectUpdateWrapper);
    }

    @Override
    public List<Admin> getbyUser(String username,String password) {
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username",username).eq("password",password);
        return adminMapper.selectList(adminQueryWrapper);
    }

    @Override
    public List<Admin> getbyname(String username) {
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username",username);
        return adminMapper.selectList(adminQueryWrapper);
    }
}
