package com.aynu.entity;

import lombok.Data;
//申请
@Data
public class Apply {
private Integer id;
private String house_id;
private String address;
private double area;
private double price;
private Integer userlist_id;
private String status;
private Userlist userlist;
}
