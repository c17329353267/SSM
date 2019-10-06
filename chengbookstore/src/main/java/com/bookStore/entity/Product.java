package com.bookStore.entity;

import lombok.Data;

/**
 * 使用该注解已经实现了equals 和 hashcode
 */
@Data
public class Product {
    private String id;
    private String name;//书名
    private double price;//价格
    private String category;//种类
    private int pnum;//库存
    private String imgurl;//图片存放地址
    private String description;//描述

}
