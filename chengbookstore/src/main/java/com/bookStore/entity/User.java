package com.bookStore.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String email;
    private String telephone;
    private String introduce;
    private String activeCode;
    private int state;
    private String role;
    private Date registTime;


}
