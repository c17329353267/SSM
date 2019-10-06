package com.bookStore.client.product.dao;

import com.bookStore.entity.Notice;
import com.bookStore.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    int selectCountsByCategory(@Param("category") String category);

    List<Product> selectProducts(Map map);

    int selectCountsByName(@Param("name") String name);

    List<Product> selectProductsByName(Map map);

    Product selectProductById(@Param("id") String id);

    Notice selectRecentNotice();

    List<Product> selectWeekHotProduct();
}
