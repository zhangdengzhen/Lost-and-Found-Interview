package top.zhangdengzhen.demo.dao;

public class Img {
    private Integer id;
    private String uuid;
    private String url;
    private String filename;
    public Img() {
    }

    public Img(Integer id, String uuid, String url, String filename) {
        this.id = id;
        this.uuid = uuid;
        this.url = url;
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
