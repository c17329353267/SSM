package com.aynu.entity;

import lombok.Data;

@Data
public class Zulist {
	private Integer zid;
	private String house_id;
	private Double price;
	private String address;
	private Integer userlist_id;
	private Integer contract_id;
	private Userlist userlist;
	
}
