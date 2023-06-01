package top.zhangdengzhen.demo.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zhangdengzhen.demo.dao.Subscribe;
import top.zhangdengzhen.demo.dao.SubscribeCategory;
import top.zhangdengzhen.demo.server.SubscribeServer;
import top.zhangdengzhen.demo.server.WeChatServer;
import top.zhangdengzhen.demo.utils.RedisUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "公众号h5")
@Controller
@RequestMapping("h5")
public class WeChatH5Controller {
    @Autowired
    private SubscribeServer subscribeServer;


    @Autowired
    private WeChatServer weChatServer;

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
    @ApiOperation(value = "公众号网页授权获取code")
    @GetMapping("/getcode")
    @ResponseBody
    String getcode()throws UnsupportedEncodingException {
        String url2="http://127.0.0.1:9000";
        String uri =  URLEncoder.encode(url2,"UTF-8");
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+p_appid+"&redirect_uri="+ uri +"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        return url;
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/getuserinfo")
    @ResponseBody
    String getuserinfo(@RequestParam String code) {
        String result1= HttpUtil.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+p_appid+"&secret="+p_appSecret+"&code="+code+"&grant_type=authorization_code");
        JSONObject jsonObject = JSONUtil.parseObj(result1);
        String token = (String) jsonObject.get("access_token");
        String s = HttpUtil.get("https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=OPENID&lang=zh_CN");
        return s;
    }

    @ApiOperation(value = "插入")
    @PostMapping("/inserth5user")
    @ResponseBody
    int insert(@RequestBody Subscribe subscribe){
        List<Subscribe> selectbyopenid = subscribeServer.selectbyopenid(subscribe.getOpenid());
        if(selectbyopenid.size()>0){
            return subscribeServer.update(subscribe);
        }
        return subscribeServer.insert(subscribe);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/subscribe")
    @ResponseBody
    int update(Subscribe subscribe){
        return subscribeServer.update(subscribe);
    }
    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    @ResponseBody
    int delete(String openid){
        return subscribeServer.delete(openid);
    }

    @ApiOperation(value = "根据openid获取用户信息")
    @GetMapping("/geth5user")
    @ResponseBody
    List<Subscribe> selectbyopenid(String openid){
        return subscribeServer.selectbyopenid(openid);
    }

    @ApiOperation(value = "根据openid获取,我的订阅")
    @GetMapping("/subscribe")
    @ResponseBody
    SubscribeCategory mySubscribe( String openid){
        return subscribeServer.mySubscribe(openid);
    }
    @ApiOperation(value = "查看哪些人需要发送消息,category,receive")
    @GetMapping("/who")
    @ResponseBody
    public List<Subscribe> selectbyCategoryReceive(String school,int category, int receive) {

        return subscribeServer.selectbyCategoryReceive(school,category,receive);
    }
}
