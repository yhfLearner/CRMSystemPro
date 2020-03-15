package com.hwua.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
    //此方法用来处理所有控制器中方法所抛出的异常
    public String handlerException(Exception e, HttpServletRequest request) {
        SysException sys = null;
        //判断该异常是否为我们自定义的异常
        if (e instanceof SysException) {
            sys = (SysException) e;
        } else {
            sys = new SysException(e.getMessage());
        }
        request.setAttribute("javax.servlet.error.status_code",451);
        Map<String,Object> map = new HashMap<>();//存放自己定义的错误信息
        map.put("name","yhf");
        map.put("message",sys.getMessage());
        map.put("test","测试消息");
        request.setAttribute("ext",map);
        return "forward:/error";
    }

}
