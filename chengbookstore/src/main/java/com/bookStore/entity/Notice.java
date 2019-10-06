package com.bookStore.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private Integer n_id;
    private String title;
    private String details;
    private Date n_time;
}
