package com.hwua.config;

//import com.hwua.interceptor.AddMvcInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AddMVCConfig {
    @Bean
    public WebMvcConfigurer addMvcConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/pages/main.html").setViewName("pages/main");
                registry.addViewController("/pages/product-add.html").setViewName("pages/product-add");
                registry.addViewController("/pages/product-list.html").setViewName("pages/product-list");
                registry.addViewController("/pages/user-add.html").setViewName("pages/user-add");
                registry.addViewController("/pages/syslog-list.html").setViewName("pages/syslog-list");
                registry.addViewController("/pages/user-role-add.html").setViewName("pages/user-role-add");
                registry.addViewController("/pages/user-list.html").setViewName("pages/user-list");
                registry.addViewController("/pages/role-add.html").setViewName("pages/role-add");
                registry.addViewController("/pages/permission-list.html").setViewName("pages/permission-list");
                registry.addViewController("/403.html").setViewName("403");


            }

            /*@Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new AddMvcInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/index.html", "/login.html", "/user/login");
            }*/
        };

    }
}
