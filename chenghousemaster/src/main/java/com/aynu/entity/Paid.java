package com.aynu.entity;

import lombok.Data;

import java.sql.Date;
//
@Data
public class Paid {
private Integer id;
private String house_id;
private String address;
private Date date;
private Double price;
private String paydate;
private String name;
private Integer userlist_id;
private String status;
}
