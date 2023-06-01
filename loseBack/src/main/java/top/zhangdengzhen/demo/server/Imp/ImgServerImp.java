package top.zhangdengzhen.demo.server.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.Img;
import top.zhangdengzhen.demo.mapper.ImgMapper;
import top.zhangdengzhen.demo.server.ImgServer;

import java.util.List;

@Service
public class ImgServerImp implements ImgServer {
    @Autowired
    private ImgMapper imgMapper;


    @Override
    public int insertBatch(List<Img> list) {
        return imgMapper.insertBatch(list);
    }

    @Override
    public List<Img> selectByUuid(String uuid) {
        QueryWrapper<Img> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid",uuid);
        return imgMapper.selectList(queryWrapper);
    }

    @Override
    public int deletebyuuid(String uuid) {
        QueryWrapper<Img> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid",uuid);

        return imgMapper.delete(queryWrapper);
    }

    @Override
    public List<Img> selectByFilename(String filename) {
        QueryWrapper<Img> imgQueryWrapper = new QueryWrapper<>();
        imgQueryWrapper.eq("filename",filename);
        return imgMapper.selectList(imgQueryWrapper);
    }
}
