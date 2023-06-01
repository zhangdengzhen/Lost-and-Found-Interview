package top.zhangdengzhen.demo.dao;

import com.baomidou.mybatisplus.annotation.TableField;

//实名认证
public class Indentify {
    String openid;
    String name;

    String id_number;

    String encode_id_number;

    public Indentify() {
    }

    public Indentify(String openid, String name, String id_number, String encode_id_number) {
        this.openid = openid;
        this.name = name;
        this.id_number = id_number;
        this.encode_id_number = encode_id_number;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getEncode_id_number() {
        return encode_id_number;
    }

    public void setEncode_id_number(String encode_id_number) {
        this.encode_id_number = encode_id_number;
    }

    @Override
    public String toString() {
        return "Indentify{" +
                "openid='" + openid + '\'' +
                ", name='" + name + '\'' +
                ", id_number='" + id_number + '\'' +
                ", encode_id_number='" + encode_id_number + '\'' +
                '}';
    }
}
