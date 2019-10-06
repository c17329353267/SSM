package com.aynu.entity;

import lombok.Data;

import java.util.List;

//申请退租
@Data
public class Applyout {
 private Integer id;
 private String house_id;
 private String address;
 private String status;
 private Integer userlist_id;
 private List<Userlist> userlist;

 
}
