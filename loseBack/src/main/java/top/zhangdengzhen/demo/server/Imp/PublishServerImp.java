package top.zhangdengzhen.demo.server.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.Publish;
import top.zhangdengzhen.demo.mapper.PublishMapper;
import top.zhangdengzhen.demo.server.ImgServer;
import top.zhangdengzhen.demo.server.PublishServer;

import java.util.List;

@Service
public class PublishServerImp implements PublishServer {
    @Autowired
    private PublishMapper publishMapper;


    @Autowired
    private ImgServer imgServer;

    @Override
    public List<Publish> selectByUserOpenid(String openid) {
        return publishMapper.selectByUserOpenid(openid);
    }

    @Override
    public List<Publish> selectByTitle(String title) {
        return publishMapper.selectByTitle(title);
    }

    @Override
    public List<Publish> selectAll(int currIndex ,  int pageSize) {
        return publishMapper.selectAll(currIndex,pageSize);
    }

    @Override
    public List<Publish> selectByType(int type,int currIndex , int pageSize) {
        return publishMapper.selectByType(type,currIndex,pageSize);
    }

    @Override
    public int insert(int id, String useropenid, String imguuid, String title, int itemCategory, String place, String detail, String contact, int type) {
        return publishMapper.insert(id, useropenid,
                imguuid,  title,
                itemCategory, place,
                detail,contact,
                type);
    }

    @Override
    public Integer getCount() {
        return publishMapper.getCount();
    }

    @Override
    public Publish findById(int id) {
        return publishMapper.findById(id);
    }

    @Override
    public Publish findByimguuid(String imguuid) {
        List<Publish> publishes = publishMapper.findByimguuid(imguuid);
        return publishes.size()>0?publishes.get(0):null;
    }

    @Override
    public int getCountbyType(int type) {
        return publishMapper.getCountbyType(type);
    }

    @Override
    public List<Publish> getByUserOpenidAndType(String openid, int type) {
        return publishMapper.getByUserOpenidAndType(openid,type);
    }

    @Override
    public int delebyid(int id) {
        UpdateWrapper<Publish> objectUpdateWrapper = new UpdateWrapper<Publish>();
        objectUpdateWrapper.eq("id",id).set("deleted",1);
        return publishMapper.update(null,objectUpdateWrapper);
    }

    @Override
    public int deletebyIdDb(int id) {
        int i=0;
//        没有发布图片
        if(publishMapper.findById(id).getImgs().size()>0){
            if(imgServer.deletebyuuid(publishMapper.findById(id).getImgs().get(0).getUuid())>0){
                i=publishMapper.deleteById(id);
            }
        }else{
            i=publishMapper.deleteById(id);
        }

        return i;
    }

    @Override
    public int updatetype(int id, int type) {
        UpdateWrapper<Publish> publishUpdateWrapper = new UpdateWrapper<>();
        publishUpdateWrapper.eq("id",id).set("type",type);

        return publishMapper.update(null,publishUpdateWrapper);
    }

    @Override
    public List<Publish> getByOpenidAndStatus(String openid, String status) {
        return publishMapper.getByOpenidAndStatus(openid,status);
    }

    @Override
    public List<Publish> getStatusByPage(int currIndex, int pageSize) {
        return publishMapper.getStatusByPage(currIndex,pageSize);
    }

    @Override
    public int getStatusByPageCount() {
        return publishMapper.getStatusByPageCount();
    }


}
