package top.zhangdengzhen.demo.server.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.Users;
import top.zhangdengzhen.demo.mapper.UsersMapper;
import top.zhangdengzhen.demo.server.UsersServer;

import java.util.List;

@Service
public class UsersServerImp implements UsersServer {
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public int insert(Users users) {
        return usersMapper.insert(users);
    }

    @Override
    public int delete(String openid) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        return usersMapper.delete(queryWrapper);
    }

    @Override
    public Users selectByOpenid(String openid) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        List<Users> usersList = usersMapper.selectList(queryWrapper);
        return  usersList.size()>0 ? usersList.get(0) : null;
    }

    @Override
    public int update(Users users) {
        if(users.getOpenid()==""){
            users.setOpenid(null);
        }
        if(users.getAvator()==""){
            users.setAvator(null);
        }
        if(users.getNickname()==""){
            users.setNickname(null);
        }
        if(users.getPhone()==""){
            users.setPhone(null);
        }
        if(users.getSchool()==""){
            users.setSchool(null);
        }

        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",users.getOpenid());
        return usersMapper.update(users,queryWrapper);
    }

    @Override
    public List<Users> selectAll() {
        return usersMapper.selectList(null);
    }

    @Override
    public Long count() {
        return usersMapper.selectCount(null);
    }

    @Override
    public List<Users> selectPage(int currIndex, int pageSize) {
        return usersMapper.selectPage(currIndex,pageSize);
    }
}
