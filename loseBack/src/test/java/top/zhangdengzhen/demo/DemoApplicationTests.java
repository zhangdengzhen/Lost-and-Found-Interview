package top.zhangdengzhen.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import top.zhangdengzhen.demo.utils.Jwt;

@SpringBootTest
class DemoApplicationTests {

//    @Autowired
//    private UsersMapper usersMapper;
//
//    @Autowired
//    private PublishMapper publishMapper;
//    @Test
//    void contextLoads() {
//        System.out.println(publishMapper.selectByUserOpenid("dddd"));
//    }
//
//    @Test
//    void publish(){
//
//    }


    @Test
    private void demo(){
        Jwt jwt = new Jwt();
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImRlbmd6aGVuIiwicGFzc3dvcmQiOiJxcTEyMzQ1NiIsImV4cCI6MTY3OTEwOTY3MH0.ht0eTy144zUqncG4wbF2HojPVSj0o8dwemyYGR55aME";


    }
}
