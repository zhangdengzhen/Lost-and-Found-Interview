package top.zhangdengzhen.demo.dao;

import io.swagger.models.auth.In;

public class Complain {
    Integer id;
    String openid;
    String detail;
    Integer publishid;
    String concat;

    public Complain() {
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

    public Integer getPublishid() {
        return publishid;
    }

    public void setPublishid(Integer publishid) {
        this.publishid = publishid;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public Complain(Integer id, String openid, String detail, Integer publishid, String concat) {
        this.id = id;
        this.openid = openid;
        this.detail = detail;
        this.publishid = publishid;
        this.concat = concat;
    }
}
