package top.zhangdengzhen.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.zhangdengzhen.demo.dao.Subscribe;
import top.zhangdengzhen.demo.dao.SubscribeCategory;

@Repository
public interface SubscribeMapper  extends BaseMapper<Subscribe> {
//    根据openid获取已经订阅的内容
    SubscribeCategory mySubscribe(@Param("openid") String openid);
}
