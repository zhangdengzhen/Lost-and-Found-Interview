package top.zhangdengzhen.demo.dao;

import java.util.List;

public class ProblemImg{
    Integer id;
    String openid;
    String detail;


    String time;

    String concat;
    List<Img> imgs;

    public ProblemImg() {
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

    public List<Img> getImgs() {
        return imgs;
    }

    public void setImgs(List<Img> imgs) {
        this.imgs = imgs;
    }

    public ProblemImg(Integer id, String openid, String detail, String time, String concat) {
        this.id = id;
        this.openid = openid;
        this.detail = detail;
        this.time = time;
        this.concat = concat;
    }
}