package com.hwua.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Orders {

  private String id;
  private String orderNum;
  private Timestamp orderTime;
  private long peopleCount;
  private String orderDesc;
  private long payType;
  private long orderStatus;
  private Integer productId;
  private Integer memberId;
  private Member member;


}
