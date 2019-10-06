package com.bookStore.admin.ordermanage.service;

import com.bookStore.admin.ordermanage.dao.AdminOrderManageDao;
import com.bookStore.entity.Order;
import com.bookStore.entity.OrderItem;
import com.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminOrderManageServiceImpl implements IAdminOrderManageService {

    @Resource
    private AdminOrderManageDao adminOrderManageDao;
    @Override
    public int findAllOrderConunts(Order order) {
        return adminOrderManageDao.selectAdminOrderCounts(order);
    }

    @Override
    public List<Order> findAllAdminOrders(Order order, PageModel pageModel) {
        Map map = new HashMap<>();
        map.put("order",order);
        map.put("pageModel",pageModel);
        return adminOrderManageDao.selectAdminOrders(map);
    }

    @Override
    public  List<OrderItem> findAdminOrderItemById(String id) {
        return adminOrderManageDao.selectAdminOrderItemById(id);
    }

    @Override
    public Order findAdminOrderById(String id) {
        return adminOrderManageDao.selectAdminOrderById(id);
    }

    @Override
    public void removeOrderById(String id) {
        //删除订单项和订单
        adminOrderManageDao.deleteOrderItemById(id);
        adminOrderManageDao.deleteOrderById(id);

    }
}
