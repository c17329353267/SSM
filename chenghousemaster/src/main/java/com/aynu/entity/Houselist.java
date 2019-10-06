package com.aynu.entity;

import lombok.Data;
//房源信息列表
@Data
public class Houselist {
  private Integer id;
  private String houseid;
  private String address;
  private double area;
  private double price;
  private String status;
}
