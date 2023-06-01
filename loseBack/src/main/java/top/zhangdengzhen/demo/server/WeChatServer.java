package top.zhangdengzhen.demo.server;

import org.springframework.web.bind.annotation.RequestBody;
import top.zhangdengzhen.demo.dao.TemplateMessage;

public interface WeChatServer {
//    发送消息模板
    String sendMessage( TemplateMessage templateMessage);
}
