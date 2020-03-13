package com.hwua.controller;

import com.hwua.log.MyLog;
import com.hwua.pojo.Users;
import com.hwua.service.LoginRegService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginRegController {
    @Autowired
    private LoginRegService lrService;
    @PostMapping("/lr/login")
    @MyLog
    @ResponseBody
    public Map<String,Object> login( String username, String password) throws Exception{
        Map<String,Object> map = new HashMap<>();
        String info=null;
        Subject currentUser = SecurityUtils.getSubject();//创建一个用户（主题）
        //判断当前用户是否登录成功
        if(!currentUser.isAuthenticated()){
            //把用户名和密码封装在UsernamePasswordToken中
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                //进行登录验证
                currentUser.login(token);
                //底层交给securityManager对象去调用注册得realm从文件或数据库中找到此登录用户的用户名和密码信息，拿到这些信息以后
                //和token中的用户名、密码进行比对。
            } catch (UnknownAccountException uae) {
                info="不存在此用户";
            } catch (IncorrectCredentialsException ice) {
                info="密码不正确";
            } catch (LockedAccountException lae) {
                info="账号锁定";
            } catch (AuthenticationException ae) {
                info=ae.getMessage();
            }
        }
        if(info==null){
            map.put("info",true);
        }else{
            map.put("info",info);
        }
        return map;
    }

    @PostMapping("/lr/register")
    @MyLog
    @ResponseBody
    public Map<String,Object> register(Users user) throws Exception{
        Map<String,Object> map = new HashMap<>();
        int res = lrService.addUsers(user);
        if(res>0){
            map.put("info",true);
        }else {
            map.put("info","注册失败");
        }
        return map;
    }
    @GetMapping("lr/validateUser")
    @ResponseBody
    @MyLog
    public Map<String,Object> validateUser(String username) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Users user = lrService.queryUserByUsername(username);
        if(user!=null){
            map.put("info",false);
        }else {
            map.put("info",true);
        }
        return map;
    }
}
