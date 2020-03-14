package com.hwua.service;

import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.pojo.Users;
import com.hwua.pojo.UsersRole;

import java.util.List;

public interface UserManagerService {
    public List<Users> queryAllUsers() throws Exception;

    public Users queryRolesByUid(Integer id) throws Exception;
    public List<Role> queryPermissionByUid(Integer id) throws Exception;
    public List<Role> queryAllRoles() throws Exception;
    public int addRoleByUid(Integer uid,Integer[] ids) throws Exception;
    public int deleteRoleById(Integer id) throws Exception;
    public int addPermissionByRid(Integer rid,Integer[] ids) throws Exception;
    public List<Permission> queryAllPermission() throws Exception;
    public List<Role> queryRolePermission() throws Exception;
    public int deletePermissionsByIds(Integer pid) throws Exception;
    public int addNewRole(Role role) throws Exception;
    //通过RoleName验证该角色是否存在
    public Role validateRoleByRoleName(String roleName) throws Exception;
    //通过RoleUrl验证该角色是否存在
    public Role validateRoleByRoleDesc(String roleDesc) throws Exception;
    //添加一个新的权限
    public int addNewPermission(Permission permission) throws Exception;
    //通过permissionName验证该权限是否存在
    public Permission validatePermissionByName(String permissionName) throws Exception;
    //通过url验证该权限是否存在
    public Permission validatePermissionByUrl(String url) throws Exception;
    public Role queryRoleDetailsById(Integer id) throws Exception;
}
