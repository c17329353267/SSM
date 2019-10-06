package com.aynu.entity;

import lombok.Data;

//经过审核已经退租
@Data
public class Checkout {

	private Integer id;
	private String house_id;
	private String address;
	private String status;
	private Integer userlist_id;
	private Userlist userlist;

	
}
