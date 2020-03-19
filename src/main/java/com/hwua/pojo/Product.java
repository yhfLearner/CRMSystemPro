package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  private Integer id;
  private String productNum;
  private String productName;
  private String cityName;
  private String departureTime;
  private Double productPrice;
  private String productDesc;
  private Integer productStatus;
  private Orders orders;
}
