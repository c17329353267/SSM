package com.bookStore.client.order.service;

import com.bookStore.client.order.dao.OrderDao;
import com.bookStore.entity.Order;
import com.bookStore.entity.OrderItem;
import com.bookStore.entity.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Override
    public void createOrder(Order order, Map<Product, Integer> cart) {
        //循环遍历所有商品 存在事务
        for (Product p:cart.keySet()
             ) {
            OrderItem item = new OrderItem();//订单信息，商品信息，购买数量
            item.setOrder(order);
            item.setProduct(p);
            item.setBuynum(cart.get(p));//每件商品对应一个数量

            orderDao.updateProductPnum(item);//修改商品库存数量=源库存数量-购买量

            orderDao.insertOrderItem(item);//添加订单项
        }
        //添加订单
        orderDao.inserOrder(order);
    }

    @Override
    public void paySuccess(String out_trade_no) {
        //当支付成功后根据订单号修改支付状态
        orderDao.updateOrderPayState(out_trade_no);
    }
}
