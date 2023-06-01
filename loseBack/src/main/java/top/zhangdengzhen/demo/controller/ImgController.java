package top.zhangdengzhen.demo.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zhangdengzhen.demo.dao.Img;
import top.zhangdengzhen.demo.dao.Publish;
import top.zhangdengzhen.demo.server.ImgServer;
import top.zhangdengzhen.demo.server.PublishServer;
import top.zhangdengzhen.demo.utils.Constants;
import top.zhangdengzhen.demo.utils.ImgResponse;
import top.zhangdengzhen.demo.utils.MyBlogUtils;
import top.zhangdengzhen.demo.utils.Response;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "图片模块")
@Controller
@RequestMapping("img")
public class ImgController {

    @Autowired
    private ImgServer imgServer;
    @Value("${python.base_url}")
    private String baseurl;
    @Value("${python.img_url}")
    private String img_url;

    @Autowired
    private PublishServer publishServer;
    @PostMapping({"/upload/file"})
    @ApiOperation("文件上传")
    @ResponseBody
    public ImgResponse upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
        //创建文件
        File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
        System.out.println(fileDirectory.mkdir());
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);

            ImgResponse imgResponse = new ImgResponse();
            imgResponse.setFilename(newFileName);
            imgResponse.setUrl(MyBlogUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/img/upload/file/" + newFileName);
            imgResponse.setCode(200);
            return imgResponse;
        } catch (IOException e) {
            e.printStackTrace();
            ImgResponse imgResponse = new ImgResponse();
            imgResponse.setUrl("");
            imgResponse.setCode(400);
            return imgResponse;
        }
    }
    @PostMapping({"/insertBatch2"})
    @ApiOperation("批量插入")
    @ResponseBody
    Response<String> insertBatch2(@RequestBody List<Img> imgs){
        Response<String> stringResponse = new Response<>();
        String simpleUUID = IdUtil.simpleUUID();
        imgs.forEach(img->{
            img.setUuid(simpleUUID);
        });
        if(imgServer.insertBatch(imgs)>0){
            stringResponse.setCode(200);
            stringResponse.setMessage(simpleUUID);
            return stringResponse;
        }else {
            stringResponse.setCode(400);
            stringResponse.setMessage("");
            return stringResponse;
        }
    }
    @PostMapping({"/insertBatch"})
    @ApiOperation("批量插入,同时调用算法，特征提取")
    @ResponseBody
    Response<String> insertBatch(@RequestBody List<Img> imgs){
        Response<String> stringResponse = new Response<>();
        String simpleUUID = IdUtil.simpleUUID();
        imgs.forEach(img->{
            img.setUuid(simpleUUID);
            JSONObject json1 = JSONUtil.createObj()
                    .put("url", img.getUrl());
            String result2 = HttpRequest.post(baseurl+img_url)
                    .body(json1.toString())
                    .execute().body();
        });
        if(imgServer.insertBatch(imgs)>0){
            stringResponse.setCode(200);
            stringResponse.setMessage(simpleUUID);
            return stringResponse;
        }else {
            stringResponse.setCode(400);
            stringResponse.setMessage("");
            return stringResponse;
        }
    }

    @GetMapping({"/upload/file"})
    @ApiOperation("根据uuid获取图片")
    @ApiImplicitParam(name = "uuid" ,required = true,value = "ff1d47eff53f4b73b5e4bf88e38b21ec")
    @ResponseBody
    List<Img> selectByUuid(@RequestParam("uuid") String uuid){
        return imgServer.selectByUuid(uuid);
    }
    @GetMapping({"/getbyfilename"})
    @ApiOperation("根据图片名称获取发布内容")
    @ApiImplicitParam(name = "filename" ,required = true,value = "20230323_22391063.jpg")
    @ResponseBody
    Publish selectByFilename(@RequestParam("filename") String filename){
        List<Img> imgs = imgServer.selectByFilename(filename);
        if(imgs.size()>0){
           return publishServer.findByimguuid(imgs.get(0).getUuid());
        }else {
            return null;
        }
    }
}

