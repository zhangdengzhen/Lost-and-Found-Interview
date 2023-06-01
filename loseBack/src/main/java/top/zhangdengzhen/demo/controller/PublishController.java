package top.zhangdengzhen.demo.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zhangdengzhen.demo.dao.*;
import top.zhangdengzhen.demo.mapper.PublishMapper;
import top.zhangdengzhen.demo.server.*;
import top.zhangdengzhen.demo.utils.Constants;
import top.zhangdengzhen.demo.utils.PageResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Api(tags = "发布模块")
@Controller
@RequestMapping("publish")
public class PublishController {

    @Autowired
    private PublishServer publishServer;
    @Autowired
    private WeChatServer weChatServer;
    @Autowired
    private SubscribeServer subscribeServer;
    @Autowired
    private UsersServer usersServer;

    @Autowired
    private ImgServer imgServer;

    @Value("${python.base_url}")
    private String baseurl;
    @Value("${python.delete_byname}")
    private String deletebyname;
    @Value("${wx.public.template_id}")
    private  String template_id;
    @ApiImplicitParam(name = "openid",value = "dddd",required = true)
    @ApiOperation(value = "获取用户发布")
    @GetMapping("/mypublish")
    @ResponseBody
    List<Publish> getall(@RequestParam String openid){
        return publishServer.selectByUserOpenid(openid);
    }

    @ApiImplicitParam(name = "title",value = "测试",required = true)
    @ApiOperation(value = "搜索")
    @GetMapping("/search")
    @ResponseBody
    List<Publish> search(@RequestParam String title){
        return publishServer.selectByTitle(title);
    }


    @ApiOperation(value = "获取所有数据")
    @GetMapping("/all")
    @ResponseBody
    PageResponse<List<Publish>> selectAll(@RequestParam("currentPage")int currentPage, @RequestParam("pageSize")int pageSize){
        Integer count = publishServer.getCount();
        if(currentPage<=0){
            currentPage=1;
        }

        Integer currentIndex = (currentPage-1)*pageSize;
        PageResponse<List<Publish>> listPageResponse = new PageResponse<>();
        listPageResponse.setCode(Constants.SUCCESS);
        listPageResponse.setTotal(count);
        listPageResponse.setData(publishServer.selectAll(currentIndex,pageSize));
        return listPageResponse;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "type",value = "类型/寻物，拾物,0,1",required = true),
            @ApiImplicitParam(name = "currentPage",value = "1",required = true),
            @ApiImplicitParam(name = "pageSize",value = "10",required = true)
    })

    @ApiOperation(value = "根据类别获取发布数据（寻物，拾物）")
    @GetMapping("/type")
    @ResponseBody
    PageResponse<List<Publish>> selectByType(@RequestParam("type") int  type,@RequestParam("currentPage") int currentPage,@RequestParam("pageSize") int  pageSize){
        Integer count = publishServer.getCountbyType(type);
        if(currentPage<=0){
            currentPage=1;
        }

        Integer currentIndex = (currentPage-1)*pageSize;
        PageResponse<List<Publish>> listPageResponse = new PageResponse<>();
        listPageResponse.setCode(Constants.SUCCESS);
        listPageResponse.setTotal(count);
        listPageResponse.setData(publishServer.selectByType(type,currentIndex,pageSize));
        return listPageResponse;

    }


    @ApiOperation(value = "插入数据")
    @PostMapping("/insert")
    @ResponseBody
    int insert(@RequestBody PublishBody publishBody){
        int insert = publishServer.insert(
                publishBody.getId(),
                publishBody.getUseropenid(),
                publishBody.getImguuid(),
                publishBody.getTitle(),
                publishBody.getItemCategory(),
                publishBody.getPlace(),
                publishBody.getDetail(),
                publishBody.getContact(),
                publishBody.getType());
        if(insert<1){
            return 0;
        }else {
            Users users = usersServer.selectByOpenid(publishBody.getUseropenid());

//        查看有哪些用户订阅了
            List<Subscribe> subscribes = subscribeServer.selectbyCategoryReceive(users.getSchool(), publishBody.getItemCategory(), 1);
            for (int i=0;i<subscribes.size();i++){
//                构造消息模板
                TemplateMessage templateMessage = new TemplateMessage(
                        subscribes.get(i).getOpenid(),
                        template_id,
                        "black",
                        publishBody.getTitle(),
                        publishBody.getDetail(),
                        DateUtil.now(),users.getNickname(),"black");
                weChatServer.sendMessage(templateMessage);
            }
            return 1;
        }


    }
    @ApiImplicitParam(name = "id",value = "7",required = true)
    @ApiOperation(value = "根据id获取发布内容")
    @GetMapping("/findByid")
    @ResponseBody
    Publish findById(@RequestParam int  id){
        return publishServer.findById(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid",value = "",required = true),
            @ApiImplicitParam(name = "type",value = "1",required = true)
    })
    @ApiOperation(value = "根据openid,和type获取发布内容")
    @GetMapping("/selectbyOpenidAndType")
    @ResponseBody
    public List<Publish> getByUserOpenidAndType(String openid, int type){
        return publishServer.getByUserOpenidAndType(openid,type);
    }

    @ApiImplicitParam(name = "id",value = "",required = true)
    @ApiOperation(value = "根据id删除发布")
    @GetMapping("/deletebyid")
    @ResponseBody
    int delebyid(int id){
//        Publish byId = publishServer.findById(id);
//        if (byId.getImgs().size()>0){
//            byId.getImgs().forEach(img->{
//                JSONObject json1 = JSONUtil.createObj()
//                        .put("name", img.getFilename());
//                String result2 = HttpRequest.post(baseurl+deletebyname)
//                        .body(json1.toString())
//                        .execute().body();
//
//            });
//            if(imgServer.deletebyuuid(byId.getImgs().get(0).getUuid())>0){
//                return publishServer.delebyid(id);
//            }else {
//                return 0;
//            }
//        }else {
//            return  publishServer.delebyid(id);
//        }
        return publishServer.delebyid(id);
    }

    @ApiImplicitParam(name = "id",value = "",required = true)
    @ApiOperation(value = "根据id设置状态，设置为已完成（已经被找回，或者认领）")
    @GetMapping("/updatestatus")
    @ResponseBody
    public int updatetype(@RequestParam Integer id){
        int  type=3;
        return publishServer.updatetype(id,type);
    }

    @ApiOperation(value = "根据openid和status(ing,ed)获取数据")
    @GetMapping("/openidstatus")
    @ResponseBody
    public List<Publish> getByOpenidAndStatus(@RequestParam("openid") String openid, @RequestParam("status")String status){
        return publishServer.getByOpenidAndStatus(openid,status);
    }

    @ApiOperation(value = "获取已完成，被认领和被找回")
    @GetMapping("/getStatusByPage")
    @ResponseBody
    public PageResponse<List<Publish>> getStatusByPage(@RequestParam("currIndex") int currIndex ,@RequestParam("pageSize")  int pageSize){
        Integer currentIndex = (currIndex-1)*pageSize;
        PageResponse<List<Publish>> listPageResponse = new PageResponse<>();
        listPageResponse.setCode(200);
        listPageResponse.setTotal(publishServer.getStatusByPageCount());
        listPageResponse.setData(publishServer.getStatusByPage(currentIndex,pageSize));
        return  listPageResponse;
    }
}
