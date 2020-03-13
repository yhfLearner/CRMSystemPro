package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

  private Integer id;
  private String email;
  private String username;
  private String password;
  private String phoneNum;
  private long status;
  private List<Role> roleList;
}
