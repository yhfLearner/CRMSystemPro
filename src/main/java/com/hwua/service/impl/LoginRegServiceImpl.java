package com.hwua.service.impl;

import com.hwua.mapper.LoginRegMapper;
import com.hwua.pojo.Users;
import com.hwua.service.LoginRegService;
import com.hwua.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginRegServiceImpl implements LoginRegService {
    @Autowired
    private LoginRegMapper lrMapper;

    @Override
    public Users queryUserByUsername(String username) throws Exception {
        return lrMapper.queryUserByUsername(username);
    }

    @Override
    public int addUsers(Users user) throws Exception {
        user.setPassword(MD5Util.md5hash(user.getUsername(),user.getPassword()));
        return lrMapper.addUsers(user);
    }

}
