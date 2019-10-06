package com.aynu.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Notice {
    private Integer id;
    private String title;
    private String content;
    //从页面页面接收的日期类型数据，写入数据库的类型为Date
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
    private User user;
}
