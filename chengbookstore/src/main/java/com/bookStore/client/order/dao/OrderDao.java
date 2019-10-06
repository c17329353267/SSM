package com.bookStore.client.order.dao;

import com.bookStore.entity.Order;
import com.bookStore.entity.OrderItem;

public interface OrderDao {
    void updateProductPnum(OrderItem item);

    void insertOrderItem(OrderItem item);

    void inserOrder(Order order);

    void updateOrderPayState(String out_trade_no);
}
