package com.hwua.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.hwua.realm.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public Realm userRealm (CredentialsMatcher  hashedCredentialsMatcher) throws Exception{
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);//给realm设置一个密码匹配器
        return userRealm;
    }

    @Bean
    public SessionsSecurityManager securityManager(Realm userRealm) throws Exception{
        SessionsSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 配置一个ShiroFilterFactory工厂,拦截url请求的
     * @return
     * @throws Exception
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SessionsSecurityManager securityManager) throws Exception{
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl("/login.html");//当认证失败，跳转到login.html页面
        Map<String,String> shiroFilterChainMap = new LinkedHashMap();
        //shiroFilterChainMap.put("/manager/user","anon");//此过滤器代表不用认证，直接可以访问
        shiroFilterChainMap.put("/lr/logout","logout");//此路径的话直接进行登出,回到LoginUrl 指定的页面
        shiroFilterChainMap.put("/product/**","authc");//此过滤器代表认证过滤器，也就是说，此url必须登录后才能访问
        shiroFilterChainMap.put("/showProduct/**","authc");
        shiroFilterChainMap.put("/pages/main.html","authc");
        shiroFilterChainMap.put("/showLog*","authc");
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainMap);
        return filterFactoryBean;
    }
    /**
     * 配置一个CredentialsMatcher类型的加密对象
     *当调用自定义realm验证方法的时候，会把表单传过来的密码交给我们的加密匹配器对象进行加密，加密好以后再和数据库中取到的密码进行比对
     * @return
     * @throws Exception
     */
   @Bean
   public CredentialsMatcher  hashedCredentialsMatcher() throws  Exception{
       HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
       hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);//选择md5加密方式
       hashedCredentialsMatcher.setHashIterations(1024);//设置加密次数
       return hashedCredentialsMatcher;
   }

}
