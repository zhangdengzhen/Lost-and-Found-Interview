package top.zhangdengzhen.demo.controller;




import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zhangdengzhen.demo.dao.Indentify;
import top.zhangdengzhen.demo.dao.Users;
import top.zhangdengzhen.demo.server.IndentifyServer;
import top.zhangdengzhen.demo.server.UsersServer;
import top.zhangdengzhen.demo.utils.PageResponse;
import top.zhangdengzhen.demo.utils.Response;

import java.util.List;

@Api(tags = "用户模块")
@Controller
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersServer usersServer;

    @Autowired
    private IndentifyServer indentifyServer;

    @ApiOperation(value = "插入数据")
    @PostMapping("/insert")
    @ResponseBody
    int insert(@RequestBody Users users){
        return usersServer.insert(users);
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    @ResponseBody
    int delete(@RequestParam("openid") String openid){
        return usersServer.delete(openid);
    }

    @ApiOperation(value = "根据openid获取")
    @GetMapping("/selectByopenid")
    @ResponseBody
    UserInfo selectByOpenid(@RequestParam("openid") String openid){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsers(usersServer.selectByOpenid(openid));
        userInfo.setIndentify(indentifyServer.select(openid));
        return userInfo;
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/update")
    @ResponseBody
    int update(@RequestBody Users users){
        return usersServer.update(users);
    }

    @ApiOperation(value = "获取所有用户信息")
    @GetMapping("/getall")
    @ResponseBody
    List<Users> selectAll(){
        return usersServer.selectAll();
    }

    @ApiOperation(value = "分页，获取所有用户信息")
    @GetMapping("/getBypage")
    @ResponseBody
    PageResponse<List<Users>> selectAll(@RequestParam int currIndex,@RequestParam int pageSize){
        PageResponse<List<Users>> listResponse = new PageResponse<List<Users>>();
        listResponse.setCode(200);
        Integer currentIndex = (currIndex-1)*pageSize;
        listResponse.setTotal(usersServer.count().intValue());
        listResponse.setData(usersServer.selectPage(currentIndex, pageSize));
        return listResponse;
    }
}

class UserInfo{
    Users users;
    Indentify indentify;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Indentify getIndentify() {
        return indentify;
    }

    public void setIndentify(Indentify indentify) {
        this.indentify = indentify;
    }
}