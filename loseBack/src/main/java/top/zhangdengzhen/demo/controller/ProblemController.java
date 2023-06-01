package top.zhangdengzhen.demo.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zhangdengzhen.demo.dao.Problem;
import top.zhangdengzhen.demo.dao.ProblemImg;
import top.zhangdengzhen.demo.server.ProblemServer;
import top.zhangdengzhen.demo.utils.PageResponse;

import java.util.List;

@Api(tags = "问题反馈模块")
@Controller
@RequestMapping("problem")
public class ProblemController {
    @Autowired
    private ProblemServer problemServer;

    @ApiOperation(value = "插入数据")
    @PostMapping("/insert")
    @ResponseBody
    int insert(@RequestBody  Problem problem){
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        problem.setTime(now);
        return problemServer.insert(problem);
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/deletebyid")
    @ResponseBody
    int deletebyid( Integer id){
        return problemServer.deletebyid(id);
    }

    @ApiOperation(value = "根据openid")
    @GetMapping("/selectopenidtype")
    @ResponseBody
    List<Problem> selectbyOpenid_Type(String openid){
        return problemServer.selectbyOpenid_Type(openid);
    }

    @ApiOperation(value = "分页获取问题反馈")
    @GetMapping("/selectpage")
    @ResponseBody
    PageResponse<List<ProblemImg>> selectByPage(int currIndex , int pageSize){
        PageResponse<List<ProblemImg>> listPageResponse = new PageResponse<>();
        Integer currentIndex = (currIndex-1)*pageSize;
        listPageResponse.setData(problemServer.selectByPage(currentIndex,pageSize));
        listPageResponse.setTotal(problemServer.selectCount());
        listPageResponse.setCode(200);
        return listPageResponse;
    }
}
