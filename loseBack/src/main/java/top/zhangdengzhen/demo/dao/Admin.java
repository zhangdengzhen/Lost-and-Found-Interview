package top.zhangdengzhen.demo.dao;

public class Admin {
    Integer id;
    String username;
    String password;
    String phone;
    String avator;
    String email;

    String uuid;

    public Admin(Integer id, String username, String password, String phone, String avator, String email, String uuid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.avator = avator;
        this.email = email;
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
