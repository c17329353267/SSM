package com.aynu.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class Document {
    private Integer id;
    private String title;
    private String filename;
    private String remark;
    private Date create_date;
    private  User user;
    private MultipartFile file;

}
