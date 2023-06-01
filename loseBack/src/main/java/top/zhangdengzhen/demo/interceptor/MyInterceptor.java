package top.zhangdengzhen.demo.interceptor;

import cn.hutool.json.JSONUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.zhangdengzhen.demo.utils.Jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 自定义拦截器类
 */
public class MyInterceptor implements HandlerInterceptor {// 实现HandlerInterceptor接口
    /**
     * 访问控制器方法前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");
        Jwt jwt = new Jwt();
        if(token!=null){
            if(jwt.verifyJwt(token)!=null){
                return true;
            }else {
                response.setContentType("application/json;charset=UTF-8");
                SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>() {
                    private static final long serialVersionUID = 1L;
                    {
                        put("code", "400");
                        put("message", "未授权，请登录后再尝试");
                    }};


                // 实际的逻辑是在这里
                PrintWriter out = response.getWriter();
                out.println(JSONUtil.toJsonStr(sortedMap));

            }

        }else{
            response.setContentType("application/json;charset=UTF-8" );
            SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>() {
                private static final long serialVersionUID = 1L;
                {
                    put("code", "400");
                    put("message", "未授权，请登录后再尝试");
                }};


            // 实际的逻辑是在这里
            PrintWriter out = response.getWriter();
            out.println(JSONUtil.toJsonStr(sortedMap));

        }
        return false;
    }

    /**
     * 访问控制器方法后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(new Date() + "--postHandle:" + request.getRequestURL());
    }

    /**
     * postHandle方法执行完成后执行，一般用于释放资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(new Date() + "--afterCompletion:" + request.getRequestURL());
    }
}
