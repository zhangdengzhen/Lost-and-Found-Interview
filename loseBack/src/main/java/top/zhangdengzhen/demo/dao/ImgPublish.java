package top.zhangdengzhen.demo.dao;

import java.sql.DatabaseMetaData;
import java.util.Date;

public class ImgPublish {
    private int id;
    private String uuid;
    private String url;
    private String filename;

    private int p_id;
    private String title;
    private String place;
    private String detail;
    private int type;
    private String category_name;
    private Date time;

    private int u_id;
    private String nickname;
    private String avator;
    private String phone;
    private String openid;
    private String school;

    public ImgPublish() {
    }

    public ImgPublish(int id, String uuid, String url, String filename, int p_id, String title, String place, String detail, int type, String category_name, Date time, int u_id, String nickname, String avator, String phone, String openid, String school) {
        this.id = id;
        this.uuid = uuid;
        this.url = url;
        this.filename = filename;
        this.p_id = p_id;
        this.title = title;
        this.place = place;
        this.detail = detail;
        this.type = type;
        this.category_name = category_name;
        this.time = time;
        this.u_id = u_id;
        this.nickname = nickname;
        this.avator = avator;
        this.phone = phone;
        this.openid = openid;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
