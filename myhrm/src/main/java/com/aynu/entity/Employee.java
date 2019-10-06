package com.aynu.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Employee {
    private Integer id;
    private Dept dept;
    private Job job;
    private String name;
    private String card_id;
    private String address;
    private String post_code;
    private String tel;
    private String phone;
    private String qq_num;
    private String email;
    private int sex;
    private String party;//是否是党员
    //private Date birthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String race;//名族
    private String education;//学历
    private String speciality;//特长
    private String hobby;//兴趣爱好
    private String remark;
    private Date create_date;

}
