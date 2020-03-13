package com.hwua.mapper;

import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserManagerMapper {
    //查询所有的用户
    public List<Users> queryAllUsers() throws Exception;
    //通过用户id查询对应的角色
    public Users queryRolesByUid(Integer id) throws Exception;
    //通过用户id查询对应的权限
    public Role queryPermissionByUid(Integer id) throws Exception;
    //查询所有的角色
    public List<Role> queryAllRoles() throws Exception;
    //通过用户id为该用户添加角色
    public int addRoleByUid(@Param("userId") Integer uid,@Param("ids") Integer[] ids) throws Exception;
    //通过id删除该角色
    public int deleteRoleById(Integer id) throws Exception;
    //通过角色id为该角色添加权限
    public int addPermissionByRid(@Param("roleId") Integer rid,@Param("ids") Integer[] ids) throws Exception;
    //查询所有的角色
    public List<Permission> queryAllPermission() throws Exception;
    //查询角色和权限
    public List<Role> queryRolePermission() throws Exception;
    //通过id批量删除权限
    public int deletePermissionsByIds( Integer pid) throws Exception;
}
