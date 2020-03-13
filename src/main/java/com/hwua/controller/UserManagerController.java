package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.log.MyLog;
import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.pojo.Users;
import com.hwua.pojo.UsersRole;
import com.hwua.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserManagerController {
    @Autowired
    private UserManagerService userService;

    @GetMapping("/user/queryUsers")
    @MyLog
    @ResponseBody
    public PageInfo<Users> queryAllUser(Integer pageNum,Integer pageSize) throws Exception{
        PageHelper.startPage(pageNum, pageSize);
        List<Users> productList = userService.queryAllUsers();
        PageInfo<Users> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }
    @GetMapping("/user/userDetail/{uid}")
    @MyLog
    public ModelAndView queryAllDetail(@PathVariable("uid") Integer uid) throws Exception{
        ModelAndView mv = new ModelAndView("pages/user-show");
        List<Users> usersList = new ArrayList<>();
        List<Role> roleList = new ArrayList<>();
        Users usersRole = userService.queryRolesByUid(uid);
        usersList.add(usersRole);
        Role rolesPermission = userService.queryPermissionByUid(uid);
        roleList.add(rolesPermission);
       /* mv.addObject("roleList",roleList);
        mv.addObject("permissionList",permissionList);*/
        mv.addObject("usersRoles",usersList);
        mv.addObject("rolesPermission",roleList);
        System.out.println(usersRole);
        return mv;
    }
    @GetMapping("/user/queryRoles/{uid}")
    @MyLog
    public ModelAndView showUserRoleAddView(@PathVariable("uid") Integer uid) throws Exception{
        ModelAndView mv = new ModelAndView("pages/user-role-add");
        List<Role> roleList = userService.queryAllRoles();
        mv.addObject("roleList",roleList);
        return mv;
    }
    @GetMapping("/user/addRole")
    @ResponseBody
    @MyLog
    public Map<String,Object> addRoleByUid(Integer uid, Integer[] ids) throws Exception{
        Map<String,Object> map = new HashMap<>();
        String info = "";
        int res = userService.addRoleByUid(uid, ids);
        if(res>0){
            map.put("info",true);
        }else {
            info = "该用户已拥有此角色";
            map.put("info",info);
        }
        return map;
    }
    @GetMapping("/user/allRoles")
    @MyLog
    public ModelAndView queryRoles() throws Exception{
        ModelAndView mv = new ModelAndView("pages/role-list");
        List<Role> roleList = userService.queryAllRoles();
        mv.addObject("roleList",roleList);
        return mv;
    }
    @DeleteMapping("/user/deleteRole/{id}")
    @MyLog
    public String deleteRole(@PathVariable("id") Integer id) throws Exception{
        int res = userService.deleteRoleById(id);
        if(res>0){
            return "redirect:/user/allRoles";
        }else {
            return null;
        }
    }
    @GetMapping("/user/addPermission/{oid}")
    @MyLog
    public ModelAndView show(@PathVariable("oid") Integer oid) throws Exception{
        ModelAndView mv = new ModelAndView("pages/role-permission-add");
        List<Permission> permissionList = userService.queryAllPermission();
        mv.addObject("permissionList",permissionList);
        return mv;
    }
    @GetMapping("/user/addPermission")
    @ResponseBody
    @MyLog
    public Map<String,Object> addPermissionByOid(Integer rid, Integer[] ids) throws Exception{
        Map<String,Object> map = new HashMap<>();
        String info = "";
        int res = userService.addPermissionByRid(rid, ids);
        if(res>0){
            map.put("info",true);
        }else {
            info = "该用户已拥有此权限";
            map.put("info",info);
        }
        return map;
    }


    @GetMapping("/user/rolePermission")
    @MyLog
    @ResponseBody
    public PageInfo<Role> queryAllProduct(Integer pageNum, Integer pageSize) throws Exception{
        PageHelper.startPage(pageNum, pageSize);
        List<Role> rolePermissionList = userService.queryRolePermission();
        PageInfo<Role> pageInfo = new PageInfo<>(rolePermissionList);
        return pageInfo;
    }

    @DeleteMapping("/user/deletePermission")
    @MyLog
    @ResponseBody
    public Map<String,Object> delPermissionByIds(Integer pid) throws Exception{
        Map<String,Object> map = new HashMap<>();
        int res = userService.deletePermissionsByIds(pid);
        if(res>0){
            map.put("info",true);
        }else {
            map.put("info","权限不够!");
        }
        return map;
    }

    @GetMapping("/user/addPermission/{rid}")
    public ModelAndView showAddPermissionView(@PathVariable("rid") Integer rid) throws Exception{
        return new ModelAndView("pages/permission-add");
    }

    @GetMapping("/user/permissionDetail")
    @MyLog
    public ModelAndView showPermission() throws Exception{
        ModelAndView mv = new ModelAndView("pages/permission-show");
        List<Permission> permissionList = userService.queryAllPermission();
        mv.addObject("permissionList",permissionList);
        return mv;
    }
}
