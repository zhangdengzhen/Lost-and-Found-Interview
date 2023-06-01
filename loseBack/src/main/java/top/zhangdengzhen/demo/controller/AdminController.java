package top.zhangdengzhen.demo.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.sun.net.httpserver.HttpServer;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.index.PathBasedRedisIndexDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zhangdengzhen.demo.dao.Admin;
import top.zhangdengzhen.demo.dao.Publish;
import top.zhangdengzhen.demo.dao.Users;
import top.zhangdengzhen.demo.server.AdminServer;
import top.zhangdengzhen.demo.server.PublishServer;
import top.zhangdengzhen.demo.server.UsersServer;
import top.zhangdengzhen.demo.utils.Jwt;
import top.zhangdengzhen.demo.utils.PageResponse;
import top.zhangdengzhen.demo.utils.Response;


import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.List;

@Controller
@Api(tags = "后台管理")
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminServer adminServer;

    @Autowired
    private UsersServer usersServer;

    @Autowired
    private PublishServer publishServer;

    @ApiOperation(value = "注册")
    @PostMapping("/insert")
    @ResponseBody
    int insert(@RequestBody Admin admin){

        Digester md5 = new Digester(DigestAlgorithm.MD5);

// 5393554e94bf0eb6436f240a4fd71282
        String digestHex = md5.digestHex(admin.getPassword());
        admin.setPassword(digestHex);
        if(adminServer.getbyname(admin.getUsername()).size()>0){
            return -1;
        }else {
            admin.setUuid(IdUtil.simpleUUID());
            return adminServer.insert(admin);
        }

    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    @ResponseBody
    int delete(@RequestParam int id){
        return adminServer.delete(id);
    }
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    @ResponseBody
    int update(@RequestBody Admin admin){
        if(adminServer.getbyname(admin.getUsername()).size()>0){
            return -1;
        }
        return adminServer.update(admin);
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @ResponseBody
    String login(@RequestBody Admin admin){
        Digester md5 = new Digester(DigestAlgorithm.MD5);

// 5393554e94bf0eb6436f240a4fd71282
        String digestHex = md5.digestHex(admin.getPassword());
        List<Admin> admins =adminServer.getbyUser(admin.getUsername(),digestHex);
        Jwt jwt = new Jwt();

        String token="";
        if(admins.size()>0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",admins.get(0).getId());
            map.put("username",admins.get(0).getUsername());
            map.put("password",admins.get(0).getPassword());
            token = jwt.generateToken(map);
        }
        return token;
    }

    @ApiOperation(value = "根据用户名，密码，获取用户信息")
    @PostMapping("/getbyusername")
    @ResponseBody
    Admin getbyUser(@RequestBody user user){
        List<Admin> getbyname = adminServer.getbyUser(user.getUsername(),user.getPassword());
        return getbyname.size()>0?getbyname.get(0):null;
    }

    @ApiOperation(value = "分页，获取所有用户信息")
    @GetMapping("/getBypage")
    @ResponseBody
    PageResponse<List<Users>> selectAll(@RequestParam int currIndex, @RequestParam int pageSize){
        PageResponse<List<Users>> listResponse = new PageResponse<List<Users>>();
        listResponse.setCode(200);
        Integer currentIndex = (currIndex-1)*pageSize;
        listResponse.setTotal(usersServer.count().intValue());
        listResponse.setData(usersServer.selectPage(currentIndex, pageSize));
        return listResponse;
    }
    @ApiOperation(value = "分页，获取所有发布信息")
    @GetMapping("/getpublishBypage")
    @ResponseBody
    PageResponse<List<Publish>> selectAllPublish( int currIndex , int pageSize){
        PageResponse<List<Publish>> listPageResponse = new PageResponse<>();
        listPageResponse.setCode(200);
        Integer currentIndex = (currIndex-1)*pageSize;
        listPageResponse.setTotal(publishServer.getCount());
        listPageResponse.setData(publishServer.selectAll(currentIndex, pageSize));
        return listPageResponse;
    }
    @ApiOperation(value = "删除发布，从数据库中删除")
    @GetMapping("/deleteDb")
    @ResponseBody
    int deletebyIdDb(int id){
        return publishServer.deletebyIdDb(id);
    }
}
class user{
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}