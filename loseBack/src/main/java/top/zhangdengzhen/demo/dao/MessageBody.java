package top.zhangdengzhen.demo.dao;

public class MessageBody {
    String access_token;
    TemplateMessage templateMessage;

    public MessageBody(String access_token, TemplateMessage templateMessage) {
        this.access_token = access_token;
        this.templateMessage = templateMessage;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public TemplateMessage getTemplateMessage() {
        return templateMessage;
    }

    public void setTemplateMessage(TemplateMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
}
