package com.bookStore.admin.productmanage.service;

import com.bookStore.entity.Product;
import com.bookStore.entity.ProductList;
import com.bookStore.utils.PageModel;

import java.util.List;

public interface ProductManageService {
    List<Product> findAllProducts(Product product, PageModel pageModel,Double minprice,Double maxprice);

    int findAllCounts(Product product);

    int addProducts(Product product);

    Product findProductById(String id);

    void modifyProductById(Product product);

    int removeProductById(String id);

    List<ProductList> findAllProductList(String year, String month);

    //List<Product> findAllProdcutsCateGorry();
}
