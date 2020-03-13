package com.hwua.mapper;

import com.hwua.pojo.Users;
import org.springframework.stereotype.Component;

@Component
public interface LoginRegMapper {
    public Users queryUserByUsername(String username) throws Exception;
    public int addUsers(Users user) throws Exception;
}
