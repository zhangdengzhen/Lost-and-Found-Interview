package top.zhangdengzhen.demo.server.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.Subscribe;
import top.zhangdengzhen.demo.dao.SubscribeCategory;
import top.zhangdengzhen.demo.mapper.SubscribeMapper;
import top.zhangdengzhen.demo.server.SubscribeServer;

import java.util.List;

@Service
public class SubscribeServerImp implements SubscribeServer {
    @Autowired
    private SubscribeMapper subscribeMapper;
    @Override
    public int insert(Subscribe subscribe) {
        return subscribeMapper.insert(subscribe);
    }

    @Override
    public int update(Subscribe subscribe) {
        QueryWrapper<Subscribe> subscribeQueryWrapper = new QueryWrapper<>();
        subscribeQueryWrapper.eq("openid",subscribe.getOpenid());
        return subscribeMapper.update(subscribe,subscribeQueryWrapper);
    }

    @Override
    public int delete(String openid) {
        QueryWrapper<Subscribe> subscribeQueryWrapper = new QueryWrapper<>();
        subscribeQueryWrapper.eq("openid",openid);
        return subscribeMapper.delete(subscribeQueryWrapper);
    }

    @Override
    public List<Subscribe> selectbyopenid(String openid) {
        QueryWrapper<Subscribe> subscribeQueryWrapper = new QueryWrapper<>();
        subscribeQueryWrapper.eq("openid",openid);
        return subscribeMapper.selectList(subscribeQueryWrapper);
    }

    @Override
    public SubscribeCategory mySubscribe(String openid) {
        return subscribeMapper.mySubscribe(openid);
    }

    @Override
    public List<Subscribe> selectbyCategoryReceive(String school,int category, int receive) {
        QueryWrapper<Subscribe> subscribeQueryWrapper = new QueryWrapper<>();
        subscribeQueryWrapper.eq("school",school).eq("category",category)
                .eq("receive",receive);
        return subscribeMapper.selectList(subscribeQueryWrapper);
    }
}
