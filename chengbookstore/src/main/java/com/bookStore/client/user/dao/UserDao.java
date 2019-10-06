package com.bookStore.client.user.dao;

import com.bookStore.entity.Order;
import com.bookStore.entity.OrderItem;
import com.bookStore.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int insertUser(User user);

    User selectUserByEmail(String email);

    User selectUserByUsername(String username);

    int changeStateByActiveCode(String activeCode);

    User selectUserByNameAndPwd(User user);

    int updateUser(User user);

    List<Order> selectOrderByUser(User login_user);

    //查询订单信息
    List<OrderItem> selectOrderItemById(String id);

    int deleteOrderById(String id);

    int deleteOrderItemById(String id);

    int updatePnumById(@Param("buynum") int buynum, @Param("productId") String product_id);
}
