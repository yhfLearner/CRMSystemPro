package com.hwua.service.impl;

import com.hwua.mapper.SysLogMapper;
import com.hwua.pojo.Syslog;
import com.hwua.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Override
    public void saveLog(Syslog syslog) throws Exception {
        sysLogMapper.saveLog(syslog);
    }

    @Override
    public List<Syslog> queryAllLog() throws Exception {
        return sysLogMapper.queryAllLog();
    }
}
