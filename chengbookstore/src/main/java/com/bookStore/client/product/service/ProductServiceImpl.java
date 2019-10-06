package com.bookStore.client.product.service;

import com.bookStore.client.product.dao.ProductDao;
import com.bookStore.entity.Notice;
import com.bookStore.entity.Product;
import com.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;
    //查找每种类别的数量
    @Override
    public int findCountsByCategory(String category) {
        return productDao.selectCountsByCategory(category);
    }
    //查找商品信息并作分页
    @Override
    public List<Product> findProducts(String category, PageModel pageModel) {
        Map map = new HashMap<>();
        map.put("category",category);
        map.put("pageModel",pageModel);
        return productDao.selectProducts(map);
    }

    @Override
    public int findCountsByName(String name) {
        return productDao.selectCountsByName(name);
    }

    @Override
    public List<Product> findProductsByName(String name, PageModel pageModel) {
        Map map = new HashMap<>();
        map.put("name",name);
        map.put("pageModel",pageModel);
        return productDao.selectProductsByName(map);
    }

    @Override
    public Product findProductById(String id) {
        return productDao.selectProductById(id);
    }

    //查看公告版
    @Override
    public Notice findRecentNotice() {

        return productDao.selectRecentNotice();
    }
    //查看本周热卖
    @Override
    public List<Product> findWeekHotProduce() {

        return productDao.selectWeekHotProduct();
    }
}
