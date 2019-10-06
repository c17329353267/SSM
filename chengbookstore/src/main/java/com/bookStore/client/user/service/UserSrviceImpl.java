package com.bookStore.client.user.service;

import com.bookStore.client.user.dao.UserDao;
import com.bookStore.entity.Order;
import com.bookStore.entity.OrderItem;
import com.bookStore.entity.Product;
import com.bookStore.entity.User;
import com.bookStore.utils.MailUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserSrviceImpl implements UserService{
    @Resource
    private UserDao userDao;
    @Override
    public int addUser(User user, HttpServletRequest request) {
       int row = 0;
        String emailMsg = "感谢注册网上书城，请点击<a href='http://localhost:8080"
                +request.getContextPath()+"/client/user/activeUser.do?activeCode="
                +user.getActiveCode()+"'>激活</a>后使用！";
        try {
            MailUtils.sendMail(user.getEmail(),emailMsg);
            row = userDao.insertUser(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public User findUser(String email) {
        return userDao.selectUserByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }

    @Override
    public int changeStateByActiveCode(String activeCode) {
        return userDao.changeStateByActiveCode(activeCode);
    }

    @Override
    public User findUserByNameAndPwd(User user) {
        return userDao.selectUserByNameAndPwd(user);
    }
    //根据id进行修改
    @Override
    public int modifyUserById(User user) {

        return userDao.updateUser(user);
    }

    @Override
    public List<Order> findOrdersByUser(User login_user) {
        return userDao.selectOrderByUser(login_user);
    }

    @Override
    public List<OrderItem> findOrderItemById(String id) {
        return userDao.selectOrderItemById(id);
    }

    @Override
    public boolean removeOrder(String id,String type) {
        boolean result = false;
        int row1 = 0;
        int row2 = 0;
        int row3 = 0;
        //订单未支付,删除前修改库存数量
        if (type == null) {
            //根据订单id查询订单项中的商品product_id
            List<OrderItem> items = userDao.selectOrderItemById(id);
            for (OrderItem item : items
            ) {
                //拿到每个订单项的product_id
                String product_id = item.getProduct().getId();
                //获取每个订单项的购买数量
                int buynum = item.getBuynum();
                //根据订单项的product_id 去修改商品数量
                row1 = userDao.updatePnumById(buynum, product_id);
            }
            row2 = userDao.deleteOrderById(id);
            row3 = userDao.deleteOrderItemById(id);
            if (row1 > 0 && row2 > 0 && row3 > 0) {
                result = true;
            }
            return result;
        }else{//订单已经支付，只需要删除订单和订单项
            row2 = userDao.deleteOrderById(id);
            row3 = userDao.deleteOrderItemById(id);
            if (row2 > 0 && row3 > 0) {
                result = true;
            }
        }
        return result;

    }
}
