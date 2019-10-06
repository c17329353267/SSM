package com.bookStore.client.product.service;

import com.bookStore.entity.Notice;
import com.bookStore.entity.Product;
import com.bookStore.utils.PageModel;

import java.util.List;

public interface ProductService {
    int findCountsByCategory(String category);

    List<Product> findProducts(String category, PageModel pageModel);

    int findCountsByName(String name);

    List<Product> findProductsByName(String name, PageModel pageModel);

    Product findProductById(String id);

    Notice findRecentNotice();

    List<Product> findWeekHotProduce();
}
