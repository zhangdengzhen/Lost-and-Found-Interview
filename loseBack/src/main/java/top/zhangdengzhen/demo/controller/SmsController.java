package top.zhangdengzhen.demo.controller;

import com.apistd.uni.Uni;
import com.apistd.uni.UniException;
import com.apistd.uni.UniResponse;
import com.apistd.uni.sms.UniMessage;
import com.apistd.uni.sms.UniSMS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "短信验证")
@Controller
@RequestMapping("sms")
public class SmsController {

    @Value("${sms.ACCESS_KEY_ID}")
    public  String ACCESS_KEY_ID;
    private static String ACCESS_KEY_SECRET = "your access key secret";


    @ApiOperation(value = "发送短信,获取验证码")
    @GetMapping("/sendcode")
    @ResponseBody
    public UniResponse sendCode(@RequestParam(value = "phone") String phone, HttpServletRequest request){
        // 初始化
        Uni.init(ACCESS_KEY_ID); // 若使用简易验签模式仅传入第一个参数即可
        int num = (int)(Math.random()*10);
        int num2 = (int)(Math.random()*10);
        int num3 = (int)(Math.random()*10);
        int num4 = (int)(Math.random()*10);
        int num5 = (int)(Math.random()*10);
        int num6 = (int)(Math.random()*10);

        String code = String.valueOf(num)+
                String.valueOf(num2)+
                String.valueOf(num3)+
                String.valueOf(num4)+
                String.valueOf(num5)+
                String.valueOf(num6);

        HttpSession sessoin=request.getSession();
        sessoin.setAttribute("code",code);
        // 设置自定义参数 (变量短信)
        Map<String, String> templateData = new HashMap<String, String>();
        templateData.put("code", code);
        templateData.put("ttl", "60");

        // 构建信息
        UniMessage message = UniSMS.buildMessage()
                .setTo(phone)
                .setSignature("张灯珍测试")
                .setTemplateId("pub_verif_ttl3")
                .setTemplateData(templateData);

        // 发送短信
        try {
            UniResponse res = message.send();
            System.out.println(res);
            return res;
        } catch (UniException e) {
            System.out.println("Error: " + e);
            System.out.println("RequestId: " + e.requestId);
            return null;
        }

    }

    @ApiOperation(value = "校验短信")
    @GetMapping("/validate")
    @ResponseBody
    public Boolean validate(@RequestParam(value = "code") String code, HttpServletRequest request){
        HttpSession sessoin=request.getSession();

        String validecode = (String) sessoin.getAttribute("code");
        System.out.println("校验短信:"+validecode);
        if(StringUtils.isEmpty(validecode)){
            return false;
        }else if(validecode.equals(code)){
            return true;
        }else {
            return false;
        }
    }
}
