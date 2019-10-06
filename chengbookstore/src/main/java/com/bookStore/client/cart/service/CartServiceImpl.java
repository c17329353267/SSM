package com.bookStore.client.cart.service;

import com.bookStore.client.cart.dao.CartDao;
import com.bookStore.entity.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartDao cartDao;
    //根据id查找商品
    @Override
    public Product findProductById(String id) {
        return cartDao.selectProductById(id);
    }
}
