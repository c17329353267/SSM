package com.bookStore.admin.productmanage.dao;

import com.bookStore.entity.Product;
import com.bookStore.entity.ProductList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductManageDao {
    List<Product> selectAllProducts(Map map);

    int selectAllCounts(Product product);

    int insertProducts(Product product);

    Product selectProductById(String id);

    void updateProductById(Product product);

    int deleteProductById(String id);

    List<ProductList> selectAllProductList(@Param("year") String year,@Param("month") String month);

    //ist<Product> selectProductCateGory();
}
