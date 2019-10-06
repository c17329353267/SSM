package com.bookStore.client.cart.service;

import com.bookStore.entity.Product;

public interface CartService {
    Product findProductById(String id);
}
