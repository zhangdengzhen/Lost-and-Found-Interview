package top.zhangdengzhen.demo.dao;

import io.swagger.models.auth.In;

import java.util.Date;

public class Problem {
    Integer id;
    String openid;
    String detail;
    String imguuid;
//    String type;//request,response
//    Integer problem_id;

    String time;

    String concat;
    public Problem() {
    }

    public Problem(Integer id, String openid, String detail, String imguuid, String time, String concat) {
        this.id = id;
        this.openid = openid;
        this.detail = detail;
        this.imguuid = imguuid;
        this.time = time;
        this.concat = concat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImguuid() {
        return imguuid;
    }

    public void setImguuid(String imguuid) {
        this.imguuid = imguuid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }
}
