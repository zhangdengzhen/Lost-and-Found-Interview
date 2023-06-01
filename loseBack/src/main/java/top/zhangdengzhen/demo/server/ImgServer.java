package top.zhangdengzhen.demo.server;

import org.apache.ibatis.annotations.Param;
import top.zhangdengzhen.demo.dao.Img;

import java.util.List;

public interface ImgServer {

    int insertBatch( List<Img> list);
    List<Img> selectByUuid(String uuid);
    int deletebyuuid(String uuid);

    List<Img> selectByFilename(String filename);
}
