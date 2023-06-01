package top.zhangdengzhen.demo.server;

import org.apache.ibatis.annotations.Param;
import top.zhangdengzhen.demo.dao.Subscribe;
import top.zhangdengzhen.demo.dao.SubscribeCategory;

import java.util.List;

public interface SubscribeServer {
    int insert(Subscribe subscribe);
    int update(Subscribe subscribe);
    int delete(String openid);
    List<Subscribe> selectbyopenid(String openid);
    SubscribeCategory mySubscribe( String openid);

//    根据类别和是否接收消息查询数据
    List<Subscribe> selectbyCategoryReceive(String school,int category,int receive);
}
