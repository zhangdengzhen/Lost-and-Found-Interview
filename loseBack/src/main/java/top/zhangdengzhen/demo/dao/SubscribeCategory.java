package top.zhangdengzhen.demo.dao;

public class SubscribeCategory {
    private  int id;
    private String openid;
    private String nickname;
    private String headimgurl;
    private String school;
    private String country;
    private int sex;
    private int receive;
    private Category category;

    public SubscribeCategory(int id, String openid, String nickname, String headimgurl, String school, String country, int sex, int receive) {
        this.id = id;
        this.openid = openid;
        this.nickname = nickname;
        this.headimgurl = headimgurl;
        this.school = school;
        this.country = country;
        this.sex = sex;
        this.receive = receive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getReceive() {
        return receive;
    }

    public void setReceive(int receive) {
        this.receive = receive;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
