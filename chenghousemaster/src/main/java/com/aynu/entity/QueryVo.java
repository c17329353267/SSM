package com.aynu.entity;

import lombok.Data;

//
@Data
public class QueryVo {
	private String zuname;//租客名字
	private String fromdate;//租房起始日期
	private String todate;//结束日期
	private Integer userlist_id;//租客id
}
