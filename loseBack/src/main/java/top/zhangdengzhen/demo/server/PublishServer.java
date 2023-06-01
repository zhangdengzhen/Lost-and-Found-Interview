package top.zhangdengzhen.demo.server;

import org.apache.ibatis.annotations.Param;
import top.zhangdengzhen.demo.dao.Publish;

import java.util.List;

public interface PublishServer {

    //    根据openid搜索
    List<Publish> selectByUserOpenid(String openid);
    //    根据标题搜索
    List<Publish> selectByTitle(String title);

    List<Publish> selectAll(int currIndex , int pageSize);

//    根据类别获取数据
    List<Publish> selectByType(int type ,int currIndex ,int pageSize);
    int insert(int id,String useropenid,String imguuid, String title,
                int itemCategory,
               String place,String detail,
               String contact,
              int type);

//    获取总发布数据
    Integer getCount();
//    根据发布的id获取数据
    Publish findById(int id);
    Publish findByimguuid(String imguuid);

//    根据类别获取数据数量
    int getCountbyType(int type);

    List<Publish> getByUserOpenidAndType(String openid,int type);
//    根据id删除,逻辑删除
    int delebyid(int id);

//    真正删除
    int deletebyIdDb(int id);
//修改type为3，表示已被找回，或认领
    int updatetype(int id,int type);
    List<Publish> getByOpenidAndStatus(String openid,String status);
//    获取已经完成
    List<Publish> getStatusByPage( int currIndex ,  int pageSize);
    int getStatusByPageCount();
}
