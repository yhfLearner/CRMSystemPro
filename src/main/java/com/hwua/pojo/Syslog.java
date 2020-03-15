package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Syslog implements Serializable {

  private int id;
  private Timestamp visitTime;
  private String username;
  private String ip;
  private String url;
  private long executionTime;
  private String method;

}
