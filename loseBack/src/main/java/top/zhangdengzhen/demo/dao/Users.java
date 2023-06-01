package top.zhangdengzhen.demo.dao;

public class Users {
    private Integer id;
    private String nickname;
    private String avator;
    private String phone;
    private String openid;
    private String school;
    private Integer authorize;

    public Users() {
    }

    public Users(Integer id, String nickname, String avator, String phone, String openid, String school, Integer authorize) {
        this.id = id;
        this.nickname = nickname;
        this.avator = avator;
        this.phone = phone;
        this.openid = openid;
        this.school = school;
        this.authorize = authorize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAuthorize() {
        return authorize;
    }

    public void setAuthorize(Integer authorize) {
        this.authorize = authorize;
    }
}
