package com.aynu.entity;

import lombok.Data;
//合同
@Data
public class Hetong {
	private Integer id;
	private String chuzu;//出租方
	private String chuzu_idcard;
	private String zuke;//租方
	private String zuke_idcard;
	private String fromdate;//起始日期
	private String todate;
	private double price;
	private String address;//地址
	private String house_id;
	private Integer payday;


}
