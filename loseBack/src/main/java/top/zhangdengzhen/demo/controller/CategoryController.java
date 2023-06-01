package top.zhangdengzhen.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zhangdengzhen.demo.dao.Category;
import top.zhangdengzhen.demo.server.CategoryServer;

import java.util.List;

@Controller
@Api(tags = "类别模块")
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryServer categoryServer;


    @ApiOperation(value = "获取全部类别")
    @GetMapping("/selectall")
    @ResponseBody
    List<Category> selectAll() {
        return categoryServer.selectAll();
    }

    @ApiOperation(value = "插入")
    @PostMapping("/insert")
    @ResponseBody
    int insert(@RequestBody Category category) {
        return categoryServer.insert(category);
    }

    @ApiImplicitParam(name = "id",value = "2",required = true)
    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    @ResponseBody
    int delete(@RequestParam int id) {
        return categoryServer.delete(id);
    }
}
