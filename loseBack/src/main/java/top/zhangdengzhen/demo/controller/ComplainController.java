package top.zhangdengzhen.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zhangdengzhen.demo.dao.Complain;
import top.zhangdengzhen.demo.dao.Publish;
import top.zhangdengzhen.demo.dao.Users;
import top.zhangdengzhen.demo.server.ComplainServer;
import top.zhangdengzhen.demo.server.PublishServer;
import top.zhangdengzhen.demo.server.UsersServer;
import top.zhangdengzhen.demo.utils.PageResponse;

import java.util.ArrayList;
import java.util.List;

@Controller
@Api(tags = "申诉模块")
@RequestMapping("complain")
public class ComplainController {
    @Autowired
    private ComplainServer complainServer;

    @Autowired
    private PublishServer publishServer;

    @Autowired
    private UsersServer usersServer;

    @ApiOperation(value = "插入")
    @PostMapping("/insert")
    @ResponseBody
    int insert(@RequestBody Complain complain){
        return complainServer.insert(complain);
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    @ResponseBody
    int delete(@RequestParam("id") int id){
        return complainServer.delete(id);
    }

    @ApiOperation(value = "分页获取数据")
    @GetMapping("/select")
    @ResponseBody
    public PageResponse<List<UserPublish>> selectPage(@RequestParam("currIndex") int currIndex ,@RequestParam("pageSize") int pageSize){
        PageResponse<List<UserPublish>> listPageResponse = new PageResponse<>();

        Integer currentIndex = (currIndex-1)*pageSize;
        ArrayList<UserPublish> userPublishes = new ArrayList<>();
        complainServer.selectPage(currentIndex,pageSize).forEach(item->{
            UserPublish userPublish = new UserPublish();
            userPublish.setComplain(item);
            userPublish.setPublish(publishServer.findById(item.getPublishid()));
            userPublish.setUsers(usersServer.selectByOpenid(item.getOpenid()));
            userPublishes.add(userPublish);
        });
        listPageResponse.setData(userPublishes);
        listPageResponse.setCode(200);
        listPageResponse.setTotal(complainServer.Complaincount());
        return listPageResponse;
    }
}

class UserPublish{
    Users users;
    Publish publish;
    Complain complain;

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Publish getPublish() {
        return publish;
    }

    public void setPublish(Publish publish) {
        this.publish = publish;
    }
}