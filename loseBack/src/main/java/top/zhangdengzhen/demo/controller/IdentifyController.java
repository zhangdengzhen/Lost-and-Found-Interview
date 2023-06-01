package top.zhangdengzhen.demo.controller;



import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zhangdengzhen.demo.dao.Indentify;
import top.zhangdengzhen.demo.server.IndentifyServer;
import top.zhangdengzhen.demo.utils.IdNumberHutool;


import javax.servlet.http.HttpUtils;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "实名认证")
@Controller
@RequestMapping("identify")
public class IdentifyController {

    @Value("${ali.identify.appcode}")
    private String appcode;
    @Value("${ali.identify.url}")
    private String aliurl;
    @Autowired
    private IndentifyServer indentifyServer;

    @Autowired
    private IdNumberHutool idNumberHutool;

    @ApiOperation(value = "输入名字，身份证认证是否正确")
    @PostMapping("/indentify")
    @ResponseBody
    public  String identify(@RequestBody info info) {


//        String appcode = "3f7545150d3a4d2cb8eced72f9b88b74";
        Map<String, String> headers = new HashMap<String, String>();

        Map<String, String> querys = new HashMap<String, String>();
        Map<String, Object> bodys = new HashMap<String, Object>();
        bodys.put("id_number", info.getId_number());
        bodys.put("name", info.getName());

        //验证身份证是否有效
        if(idNumberHutool.isvalid(info.getId_number())){
            try {

//            host, path, method, headers, querys, bodys

                String result2 = HttpRequest.post(aliurl)
                        .header("Authorization","APPCODE " + appcode)
                        .header("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
                        .form(bodys).execute().body();
//            {"request_id":"TID0016a3cd6b4b4520bc00965b337fc121","status":"OK","state":1,"result_code":1,"result_message":"一致"}
                JSONObject jsonObject = JSONUtil.parseObj(result2);
                if(jsonObject.getInt("state").equals(1)){
                    Indentify indentify =
                            new Indentify(
                            info.getOpenid(),
                            info.getName(),
                            //脱去敏感信息
                            idNumberHutool.cardNum(info.getId_number()),
//                            加密
                            idNumberHutool.encode(info.getId_number()));
                    indentifyServer.insert(indentify);
                }
                System.out.println(result2);
                return result2;
            } catch (Exception e) {
                e.printStackTrace();
                return "{state:2}";
            }
        }else {
            return "{state:2}";
        }
//需要赠送身份证OCR识别，vip优惠券。请联系客服获取。

//        {
//            "request_id": "TID8bf47ab6eda64476973cc5f5b6ebf57e",/调用记录ID
//            "status": "OK",
//                "state": 1,         //  返回值为 1 : 查询成功, 二要素一致。  返回值为 2 : 查询成功, 二要素不一致
//        }
//
////温馨提示：
//        1.当 http响应状态码为200 时，计费，以state参数返回值来做业务判断。
//        state参数返回值为 1 : 查询成功, 二要素一致
//        state参数返回值为 2 : 查询成功, 二要素不一致
//
//        2.当 http响应状态码为400 时，出现'库无'，state参数返回值为4，不计费，有以下几种原因
//                (1)现役军人、武警官兵、特殊部门人员及特殊级别官员；
//        (2)退役不到2年的军人和士兵（根据军衔、兵种不同，时间会有所不同，一般为2年）；
//        (3)户口迁出，且没有在新的迁入地迁入；
//        (4)户口迁入新迁入地，当地公安系统未将迁移信息上报到公安部（上报时间地域不同而有所差异）；
//        (5)更改姓名，当地公安系统未将更改信息上报到公安部（上报时间因地域不同而有所差异）；
//        (6)移民；
//        (7)未更换二代身份证；
//        (8)死亡。
//        (9)身份证号确实不存在
//        {
//            "status": "INVALID_ARGUMENT",
//                "result_message": "请求参数错误",
//                "request_id": "TID8bf47ab6eda64476973cc5f5b6ebf57e"
//        }

    }
}
class info{
    String openid;
    String id_number;
    String name;

    public info(String openid, String id_number, String name) {
        this.openid = openid;
        this.id_number = id_number;
        this.name = name;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}