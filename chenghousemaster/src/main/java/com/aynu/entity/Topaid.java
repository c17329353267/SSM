package com.aynu.entity;

import lombok.Data;

import java.sql.Date;

//未交租金表
@Data
public class Topaid {
	private Integer id;
	private String house_id;//房屋id属性
	private String address;//地址
	private Date date;//时间
	private Double price;//价格
	
	private String name;//姓名
	private Integer userlist_id;//
	private String status;//状态
	
}
