package com.hwua.mapper;

import com.hwua.pojo.Syslog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysLogMapper {
    public void saveLog(Syslog syslog) throws Exception;
    public List<Syslog> queryAllLog() throws Exception;
}
