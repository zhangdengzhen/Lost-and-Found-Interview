package top.zhangdengzhen.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.zhangdengzhen.demo.dao.Img;
import top.zhangdengzhen.demo.dao.ImgPublish;

import java.util.List;

@Repository
public interface ImgMapper extends BaseMapper<Img> {
    int insertBatch(@Param("list") List<Img> list);
    ImgPublish getPublishByName(@Param("filename")String filename);
}
