package com.bookStore.admin.ordermanage.dao;

import com.bookStore.entity.Order;
import com.bookStore.entity.OrderItem;

import java.util.List;
import java.util.Map;

public interface AdminOrderManageDao {
    List<Order> selectAdminOrders(Map map);

    int selectAdminOrderCounts(Order order);

    List<OrderItem> selectAdminOrderItemById(String id);

    Order selectAdminOrderById(String id);

    void deleteOrderById(String id);

    void deleteOrderItemById(String id);
}
