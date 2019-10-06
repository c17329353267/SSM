package com.bookStore.client.cart.dao;

import com.bookStore.entity.Product;
import org.apache.ibatis.annotations.Param;

public interface CartDao {
    Product selectProductById(@Param("id") String id);
}
