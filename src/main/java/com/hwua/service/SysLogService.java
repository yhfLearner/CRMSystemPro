package com.hwua.service;

import com.hwua.pojo.Syslog;

import java.util.List;

public interface SysLogService {
    public void saveLog(Syslog syslog) throws Exception;
    public List<Syslog> queryAllLog() throws Exception;
}
