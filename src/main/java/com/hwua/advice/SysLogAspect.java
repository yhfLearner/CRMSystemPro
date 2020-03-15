package com.hwua.advice;

import com.hwua.mapper.SysLogMapper;
import com.hwua.pojo.Syslog;
import com.hwua.pojo.Users;
import com.hwua.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;

@Component
@Aspect
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;
    private long startTime;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.hwua.log.MyLog)")
    public void logPointCut() {
    }
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
    }
    //切面 配置通知
    @AfterReturning("logPointCut()")
    public void saveOperation(JoinPoint joinPoint) throws Exception {
        //用于保存日志
        Syslog syslog = new Syslog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取请求的方法名
        String methodName = method.getName();
        syslog.setMethod(methodName);
        long  executionTime = System.currentTimeMillis()-startTime;

        syslog.setExecutionTime(executionTime);
        syslog.setVisitTime(new Timestamp(System.currentTimeMillis()));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURL = request.getRequestURL().toString();
        syslog.setUrl(requestURL);
        // 客户端ip
        String ip = request.getRemoteAddr();
        syslog.setIp(ip);

        Subject subject = SecurityUtils.getSubject();
        Users user =(Users) subject.getPrincipal();
        if(user!=null){
            String userName = user.getUsername();
            syslog.setUsername(userName);
        }
        //调用service保存Operation实体类到数据库
        sysLogService.saveLog(syslog);
    }
}
