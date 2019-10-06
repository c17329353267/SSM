package com.bookStore.client.order.service;

import com.bookStore.entity.Order;
import com.bookStore.entity.Product;

import java.util.Map;

public interface OrderService {
    void createOrder(Order order, Map<Product, Integer> cart);

    void paySuccess(String out_trade_no);
}
