package com.bookStore.entity;

import lombok.Data;

@Data
public class OrderItem {
    private Order order;//订单
    private Product product;//商品
    private int buynum;//购买数量
}
