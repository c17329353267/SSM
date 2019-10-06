package com.bookStore.admin.productmanage.service;

import com.bookStore.admin.productmanage.dao.ProductManageDao;
import com.bookStore.entity.Product;
import com.bookStore.entity.ProductList;
import com.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductManageServiceImpl implements ProductManageService {

    @Resource
    private ProductManageDao productManageDao;
    @Override
    public List<Product> findAllProducts(Product product, PageModel pageModel,Double minprice,Double maxprice) {
        Map map = new HashMap<>();
        map.put("product",product);
        map.put("pageModel",pageModel);
        map.put("minprice",minprice);
        map.put("maxprice",maxprice);
        return productManageDao.selectAllProducts(map);
    }

    @Override
    public int findAllCounts(Product product) {
        return productManageDao.selectAllCounts(product);
    }

    @Override
    public int addProducts(Product product) {
        return productManageDao.insertProducts(product);
    }

    @Override
    public Product findProductById(String id) {
        return productManageDao.selectProductById(id);
    }

    @Override
    public void modifyProductById(Product product) {
        productManageDao.updateProductById(product);
    }

    @Override
    public int removeProductById(String id) {
        return productManageDao.deleteProductById(id);
    }

    @Override
    public List<ProductList> findAllProductList(String year, String month) {
        return productManageDao.selectAllProductList(year,month);
    }
    //查询类别
    /*@Override
    public List<Product> findAllProdcutsCateGorry() {
        return productManageDao.selectProductCateGory();
    }*/
}
