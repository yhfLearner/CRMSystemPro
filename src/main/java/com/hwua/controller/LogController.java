package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.log.MyLog;
import com.hwua.pojo.Syslog;
import com.hwua.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LogController {
    @Autowired
    private SysLogService sysLogService;
    @GetMapping("/showLog")
    @ResponseBody
    public PageInfo<Syslog>  queryAllLog(Integer pageNum,Integer pageSize) throws Exception{
        PageHelper.startPage(pageNum, pageSize);
        List<Syslog> syslogList = sysLogService.queryAllLog();
        PageInfo<Syslog> pageInfo = new PageInfo<>(syslogList);
        return pageInfo;
    }
}
