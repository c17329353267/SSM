package com.bookStore.client.user.service;

import com.bookStore.entity.Order;
import com.bookStore.entity.OrderItem;
import com.bookStore.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    int addUser(User user, HttpServletRequest request);

    User findUser(String email);

    User findUserByUsername(String username);

    int changeStateByActiveCode(String activeCode);

    User findUserByNameAndPwd(User user);

    int modifyUserById(User user);

    List<Order> findOrdersByUser(User login_user);


    List<OrderItem> findOrderItemById(String id);


    boolean removeOrder(String id,String type);
}
