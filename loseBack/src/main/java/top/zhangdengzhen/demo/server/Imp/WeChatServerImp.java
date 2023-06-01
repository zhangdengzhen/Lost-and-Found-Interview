package top.zhangdengzhen.demo.server.Imp;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.TemplateMessage;
import top.zhangdengzhen.demo.server.WeChatServer;
import top.zhangdengzhen.demo.utils.RedisUtil;

@Service
public class WeChatServerImp implements WeChatServer {

    @Autowired
    private RedisUtil redisUtil;
    @Value("${wx.public.appid}")
    private String p_appid;
    @Value("${wx.public.appsecret}")
    private String p_appSecret;
    @Value("${wx.public.template_id}")
    private  String template_id;
    @Override
    public String sendMessage(TemplateMessage templateMessage) {
        if (redisUtil.hasKey("access_token")){

        }else {
            //        获取accese_token
            String token = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+p_appid+"&secret="+p_appSecret);
            JSONObject response = JSONUtil.parseObj(token);
            redisUtil.set("access_token",response.getStr("access_token"),7100);
        }

//        {"access_token":"67_ho-uV8CUahXn4inHSiY_VmjA-_iTqlnp6MDmjenE2zDi72S1NgaLCzY2iaihig3MeGW15SfHdATf-CvD3KA7CbtXjefSZ_5TTkWLNxn452YcIX1sR7T8lgN_KhQPKDaADAEZS","expires_in":7200}
        templateMessage.setTemplate_id(template_id);
        JSONObject json3= JSONUtil.parseObj(templateMessage, false,true);
        String json= json3.toString();
        String content = HttpRequest.post("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+redisUtil.get("access_token")).body(json)
                .execute().body();;
        System.out.println(content);
        return content;
    }
}
