package top.zhangdengzhen.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.zhangdengzhen.demo.interceptor.MyInterceptor;
import top.zhangdengzhen.demo.utils.Constants;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 支持PUT、DELETE请求
     */
    @Bean
    public FormContentFilter httpPutFormContentFilter() {
        return new FormContentFilter();
    }
//    映射静态资源目录到本地
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/upload/file/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }

    /**
     * 添加Web项目的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有访问路径，都通过MyInterceptor类型的拦截器进行拦截
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/admin/*").excludePathPatterns("/admin/login","/admin/validate","/admin/insert");
        //放行登录页，登陆操作，静态资源
    }

}