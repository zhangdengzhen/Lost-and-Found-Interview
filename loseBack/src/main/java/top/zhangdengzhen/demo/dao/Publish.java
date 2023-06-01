package top.zhangdengzhen.demo.dao;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;
import java.util.List;

public class Publish {
   private Integer id;

   private String title;

   private String place;
   private String detail;
   private String contact;
   private int type;//类型/寻物，拾物,0,1

    @TableField("itemCategory")
    private Category category;
    private Users users;
    private List<Img> imgs;
    private Date time;
    private String status;

    private Integer deleted;
    public Publish() {
    }

    public Publish(Integer id, String title, String place, String detail, String contact, int type, Date time, String status,  Integer deleted) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.detail = detail;
        this.contact = contact;
        this.type = type;
        this.time = time;
        this.status = status;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<Img> getImgs() {
        return imgs;
    }

    public void setImgs(List<Img> imgs) {
        this.imgs = imgs;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
