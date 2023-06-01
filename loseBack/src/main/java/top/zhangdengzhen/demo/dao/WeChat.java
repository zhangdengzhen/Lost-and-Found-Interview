package top.zhangdengzhen.demo.dao;


public class WeChat {
    String token;

    String session_key;
    String openid;
    String unionid;

    Users users;

    Indentify indentify;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public WeChat() {
    }

    public Indentify getIndentify() {
        return indentify;
    }

    public void setIndentify(Indentify indentify) {
        this.indentify = indentify;
    }

    public WeChat(String token, String session_key, String openid, String unionid, Users users, Indentify indentify) {
        this.token = token;
        this.session_key = session_key;
        this.openid = openid;
        this.unionid = unionid;
        this.users = users;
        this.indentify = indentify;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
