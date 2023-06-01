package top.zhangdengzhen.demo.dao;

public class TemplateMessage {
    private String touser;
    private String template_id;
    private String topcolor;
    private data data;

    public TemplateMessage(String touser, String template_id, String topcolor, String title,String detail,String time,String user,String color) {
        this.touser = touser;
        this.template_id = template_id;
        this.topcolor = topcolor;
        this.data=new data(new title(title,color),new detail(detail,color),new time(time,color),new myusers(user,color));

    }

    public TemplateMessage() {
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public top.zhangdengzhen.demo.dao.data getData() {
        return data;
    }

    public void setData(top.zhangdengzhen.demo.dao.data data) {
        this.data = data;
    }
}
class data{
    title title;
    detail detail;
    time time;
    myusers user;

    public data(top.zhangdengzhen.demo.dao.title title, top.zhangdengzhen.demo.dao.detail detail, top.zhangdengzhen.demo.dao.time time, myusers user) {
        this.title = title;
        this.detail = detail;
        this.time = time;
        this.user = user;
    }

    public data() {
    }

    public top.zhangdengzhen.demo.dao.title getTitle() {
        return title;
    }

    public void setTitle(top.zhangdengzhen.demo.dao.title title) {
        this.title = title;
    }

    public top.zhangdengzhen.demo.dao.detail getDetail() {
        return detail;
    }

    public void setDetail(top.zhangdengzhen.demo.dao.detail detail) {
        this.detail = detail;
    }

    public top.zhangdengzhen.demo.dao.time getTime() {
        return time;
    }

    public void setTime(top.zhangdengzhen.demo.dao.time time) {
        this.time = time;
    }

    public myusers getUser() {
        return user;
    }

    public void setUser(myusers user) {
        this.user = user;
    }
}
class title{
    private String value;
    private String color;

    public title(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
class detail{
    private String value;
    private String color;

    public detail(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
class time{
    private String value;
    private String color;

    public time(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
class myusers{
    private String value;
    private String color;

    public myusers(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}