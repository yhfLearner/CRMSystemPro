package com.hwua.service;

import com.hwua.pojo.Users;

public interface LoginRegService {
    public Users queryUserByUsername(String username) throws Exception;
    public int addUsers(Users user) throws Exception;
}
