package com.hwua.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {
    //注册得时候调用此方法进行密码加密
    public static String md5hash(String username,String password){
        ByteSource salt = ByteSource.Util.bytes(username);
        return new SimpleHash(Md5Hash.ALGORITHM_NAME,password, salt, 1024).toHex();
    }
}
