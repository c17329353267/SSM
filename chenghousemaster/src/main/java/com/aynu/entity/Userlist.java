package com.aynu.entity;

import lombok.Data;

import java.util.List;

//用户租赁信息(登陆用户和房东关系表)
@Data
public class Userlist {
private Integer id;
private String name;//房东
private String idcard;//身份证号码
private String phone;//电话
private Integer user_id;
private List<Apply> apply;
private List<Zulist> zulist;
private List<Checkout> checkout;
private List<Applyout> applyout;
private User user;

}
