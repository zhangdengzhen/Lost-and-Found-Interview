package top.zhangdengzhen.demo.server.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.Indentify;
import top.zhangdengzhen.demo.mapper.IndentifyMapper;
import top.zhangdengzhen.demo.server.IndentifyServer;

import java.util.List;

@Service
public class IndentifyServerImp implements IndentifyServer {
    @Autowired
    private IndentifyMapper indentifyMapper;

    @Override
    public int insert(Indentify indentify) {
        QueryWrapper<Indentify> indentifyQueryWrapper = new QueryWrapper<>();
        indentifyQueryWrapper.eq("openid",indentify.getOpenid());
        List<Indentify> indentifies = indentifyMapper.selectList(indentifyQueryWrapper);
        if(indentifies.size()>0){
            UpdateWrapper<Indentify> indentifyUpdateWrapper = new UpdateWrapper<>();
            indentifyUpdateWrapper.eq("openid",indentify.getOpenid())
                    .set("name",indentify.getName())
                    .set("id_number",indentify.getId_number())
                    .set("encode_id_number",indentify.getEncode_id_number());
            return indentifyMapper.update(null,indentifyUpdateWrapper);
        }
        else {
            return indentifyMapper.insert(indentify);
        }
    }

    @Override
    public Indentify select(String openid) {
        QueryWrapper<Indentify> indentifyQueryWrapper = new QueryWrapper<>();
        indentifyQueryWrapper.eq("openid",openid);
        List<Indentify> indentifies = indentifyMapper.selectList(indentifyQueryWrapper);
        System.out.println("服务中查询"+indentifies);
        return indentifies.size()>0?indentifies.get(0):new Indentify();
    }


}
