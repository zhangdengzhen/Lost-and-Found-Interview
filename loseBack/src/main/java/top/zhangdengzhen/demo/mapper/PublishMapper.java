package top.zhangdengzhen.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.zhangdengzhen.demo.dao.Publish;

import java.util.List;

@Repository
public interface PublishMapper extends BaseMapper<Publish> {

//    根据openid搜索
    List<Publish> selectByUserOpenid(@Param("openid") String openid);
//    根据标题搜索
    List<Publish> selectByTitle(@Param("title") String title);

    List<Publish> selectAll(@Param("currIndex") int currIndex , @Param("pageSize") int pageSize);

    List<Publish> selectByType(@Param("type") int type,@Param("currIndex") int currIndex , @Param("pageSize") int pageSize);

    int getCountbyType(@Param("type")int type);
    Integer getCount();
    int insert(@Param("id")int id,@Param("useropenid") String useropenid,@Param("imguuid")String imguuid,@Param("title") String title,
               @Param("itemCategory") int itemCategory,
               @Param("place")String place,@Param("detail") String detail,
               @Param("contact") String contact,
               @Param("type") int type);
    Publish findById(@Param("id") int id);
    List<Publish> getByUserOpenidAndType(@Param("openid") String openid,@Param("type")int type);

//    根据openid和status获取数据
    List<Publish> getByOpenidAndStatus(@Param("openid") String openid,@Param("status")String status);
    List<Publish> findByimguuid(@Param("imguuid") String imguuid);

//    根据分页，获取已完成
    List<Publish> getStatusByPage(@Param("currIndex") int currIndex , @Param("pageSize") int pageSize);
    int getStatusByPageCount();

}
