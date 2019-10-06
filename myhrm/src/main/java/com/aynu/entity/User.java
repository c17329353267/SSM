package com.aynu.entity;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Integer id;
    private String loginname;
    private String password;
    private String status;
    private Date createdate;
    private String username;


}
