package com.hwua.service;

import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.pojo.Users;
import com.hwua.pojo.UsersRole;

import java.util.List;

public interface UserManagerService {
    public List<Users> queryAllUsers() throws Exception;

    public Users queryRolesByUid(Integer id) throws Exception;
    public Role queryPermissionByUid(Integer id) throws Exception;
    public List<Role> queryAllRoles() throws Exception;
    public int addRoleByUid(Integer uid,Integer[] ids) throws Exception;
    public int deleteRoleById(Integer id) throws Exception;
    public int addPermissionByRid(Integer rid,Integer[] ids) throws Exception;
    public List<Permission> queryAllPermission() throws Exception;
    public List<Role> queryRolePermission() throws Exception;
    public int deletePermissionsByIds(Integer pid) throws Exception;
}
