package com.bookStore.admin.ordermanage.service;

import com.bookStore.entity.Order;
import com.bookStore.entity.OrderItem;
import com.bookStore.utils.PageModel;

import java.util.List;

public interface IAdminOrderManageService {
    int findAllOrderConunts(Order order);

    List<Order> findAllAdminOrders(Order order, PageModel pageModel);

    List<OrderItem> findAdminOrderItemById(String id);

    Order findAdminOrderById(String id);

    void removeOrderById(String id);

    //void deleteOrderItemById(String id);
}
