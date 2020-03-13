package com.hwua.service.impl;

import com.hwua.mapper.UserManagerMapper;
import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.pojo.Users;
import com.hwua.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserManagerServiceImpl implements UserManagerService {
    @Autowired
    private UserManagerMapper userMapper;
    @Override
    public List<Users> queryAllUsers() throws Exception {
        return userMapper.queryAllUsers();
    }

    @Override
    public Users queryRolesByUid(Integer id) throws Exception {
        return userMapper.queryRolesByUid(id);
    }

    @Override
    public Role queryPermissionByUid(Integer id) throws Exception {
        return userMapper.queryPermissionByUid(id);
    }

    @Override
    public List<Role> queryAllRoles() throws Exception {
        return userMapper.queryAllRoles();
    }

    @Override
    public int addRoleByUid(Integer uid, Integer[] ids) throws Exception {
        return userMapper.addRoleByUid(uid,ids);
    }

    @Override
    public int deleteRoleById(Integer id) throws Exception {
        return userMapper.deleteRoleById(id);
    }

    @Override
    public int addPermissionByRid(Integer rid, Integer[] ids) throws Exception {
        return userMapper.addPermissionByRid(rid, ids);
    }

    @Override
    public List<Permission> queryAllPermission() throws Exception {
        return userMapper.queryAllPermission();
    }

    @Override
    public List<Role> queryRolePermission() throws Exception {
        return userMapper.queryRolePermission();
    }

    @Override
    public int deletePermissionsByIds(Integer pid) throws Exception {
        return userMapper.deletePermissionsByIds(pid);
    }

    @Override
    public int addNewRole(Role role) throws Exception {
        return userMapper.addNewRole(role);
    }

    @Override
    public Role validateRoleByRoleName(String roleName) throws Exception {
        return userMapper.validateRoleByRoleName(roleName);
    }

    @Override
    public Role validateRoleByRoleDesc(String roleDesc) throws Exception {
        return userMapper.validateRoleByRoleDesc(roleDesc);
    }

    @Override
    public int addNewPermission(Permission permission) throws Exception {
        return userMapper.addNewPermission(permission);
    }

    @Override
    public Permission validatePermissionByName(String permissionName) throws Exception {
        return userMapper.validatePermissionByName(permissionName);
    }

    @Override
    public Permission validatePermissionByUrl(String url) throws Exception {
        return userMapper.validatePermissionByUrl(url);
    }

}
