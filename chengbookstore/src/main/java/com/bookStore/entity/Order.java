package com.bookStore.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private String id;
    private double money;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private int paystate;
    private Date ordertime;
    private User user;
}
