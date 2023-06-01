package top.zhangdengzhen.demo.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zhangdengzhen.demo.dao.*;
import top.zhangdengzhen.demo.server.IndentifyServer;
import top.zhangdengzhen.demo.server.SubscribeServer;
import top.zhangdengzhen.demo.server.UsersServer;
import top.zhangdengzhen.demo.server.WeChatServer;
import top.zhangdengzhen.demo.utils.Jwt;
import top.zhangdengzhen.demo.utils.RedisUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@Api(tags = "微信授权")
@Controller
@RequestMapping("author")
public class WeChatController {

    @Autowired
    private UsersServer usersServer;

    @Autowired
    private WeChatServer weChatServer;

    @Autowired
    private IndentifyServer indentifyServer;

    @Autowired
    private RedisUtil redisUtil;
    @Value("${wx.mp.appid}")
    private String appid;
    @Value("${wx.mp.appSecret}")
    private String appSecret;

    @Value("${wx.public.appid}")
    private String p_appid;
    @Value("${wx.public.appsecret}")
    private String p_appSecret;
    @Value("${wx.public.template_id}")
    private  String template_id;
    @ApiOperation(value = "根据code获取用户信息，openid->用户信息")
    @GetMapping("/getopenid")
    @ResponseBody
    WeChat getcode(@RequestParam String code){
        String url =  "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code";
        String content = HttpUtil.get(url);
        WeChat weChat = new WeChat();
//        openid: "o0Wb75dvbH9XhnPseDNj2ouLrBnk"
//        session_key: "jRi6287BaBablQjXJp4ITg=="
//        unionid: "o82cg54l6uUgOxL1DfjDhgbTOwKU"
        JSONObject jsonObject = JSONUtil.parseObj(content);
        String openid = jsonObject.getStr("openid");
        String session_key = jsonObject.getStr("session_key");
        String unionid = jsonObject.getStr("unionid");

        Jwt jwt = new Jwt();

        String token="";
        HashMap<String, Object> map = new HashMap<>();
        map.put("openid",openid);
        token = jwt.generateToken(map);
        System.out.println("获取身份信息"+indentifyServer.select(openid));
        weChat.setIndentify(indentifyServer.select(openid));
        weChat.setToken(token);
        weChat.setOpenid(openid);
        weChat.setSession_key(session_key);
        weChat.setUnionid(unionid);


        Users users = usersServer.selectByOpenid(jsonObject.getStr("openid"));
        if(users!=null){
            weChat.setUsers(users);
        }else {
            usersServer.insert(new Users(0,"","","",openid,"",1));
        }
        System.out.println(content);
        return weChat;
    }

    @ApiOperation(value = "获取token")
    @GetMapping("/token")
    @ResponseBody
    String author(@RequestParam String openid){
        Jwt jwt = new Jwt();

        String token="";
            HashMap<String, Object> map = new HashMap<>();
            map.put("openid",openid);
            token = jwt.generateToken(map);
        return token;
    }

    @ApiOperation(value = "公众号消息模板获取token")
    @GetMapping("/wxtoken")
    @ResponseBody
    String wxauthor(){
        String content = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+p_appid+"&secret="+p_appSecret);
        System.out.println(content);
        return content;
    }

    @ApiOperation(value = "发送消息模板")
    @PostMapping("/message")
    @ResponseBody
    String sendMessage(@RequestBody TemplateMessage templateMessage){
        return weChatServer.sendMessage(templateMessage);
    }


}
