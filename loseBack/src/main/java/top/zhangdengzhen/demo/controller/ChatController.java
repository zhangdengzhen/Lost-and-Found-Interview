package top.zhangdengzhen.demo.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zhangdengzhen.demo.dao.Users;
import top.zhangdengzhen.demo.server.UsersServer;
import top.zhangdengzhen.demo.utils.RedisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "聊天模块")
@RequestMapping("chatroom")
public class ChatController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UsersServer usersServer;

    @ApiOperation(value = "根据openid,获取好友列表")
    @GetMapping("/friend")
    @ResponseBody
    List<Users> selectAll(@RequestParam("openid") String openid) {
        ArrayList<Users> users = new ArrayList<>();
        for(Object o:redisUtil.sGet(openid)){
            JSONObject entries = JSONUtil.parseObj(o);
            users.add(usersServer.selectByOpenid(entries.getStr("openid")));
        }
       return users;
    }

    @ApiOperation(value = "根据openid,friendid,获取聊天记录")
    @GetMapping("/record")
    @ResponseBody
    List<Object> getrecord(@RequestParam("openid")String openid,@RequestParam("friendopenid")String friendopenid) {
        return  redisUtil.lGetAll(openid+"-"+friendopenid+"-record");
    }
}

