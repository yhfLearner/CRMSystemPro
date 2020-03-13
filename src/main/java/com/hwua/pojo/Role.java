package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  private String id;
  private String roleName;
  private String roleDesc;
  private Permission permission;
  private List<Permission> permissionList;
}
