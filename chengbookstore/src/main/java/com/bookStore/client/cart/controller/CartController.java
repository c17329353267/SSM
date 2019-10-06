package com.bookStore.client.cart.controller;

import com.bookStore.client.cart.service.CartService;
import com.bookStore.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/client/cart/")
public class CartController {

    @Autowired
    private CartService cartService;
    //添加购物车,把商品放到session域
    @RequestMapping("addCart.do")
    public String addCart(String id, HttpSession session){
        //根据id查找购物车中的商品
        Product product = cartService.findProductById(id);
        //商品作为主键（key）（重写hashcode和equals保证对象的唯一性），数量作为数量
        //先从session域中是否存在购物车
        Map<Product,Integer> carMap = (Map<Product, Integer>) session.getAttribute("cart");
        //如果购物车为空，则创建新的购物车
        if(carMap == null){
            carMap =new HashMap<Product,Integer>();
        }
        //像购物车中存放商品
        /*
            根据map集合的特点，当键（key)重复时，put方法返回值为原来的value,在此反回的是Integer
            由此判断，当根据id查找购物车中的商品时，如果未查找到，此时product=null,
         */
        //如果product=null,则此时的count=null,原来没有商品，则此时商品数量为1,直接将商品放入购物车，
        Integer count = carMap.put(product,1);
        // 否则原来存在此商品，则数量+1
        if(count != null){
            carMap.put(product,count+1);
        }
        //将购物车放入session域
        session.setAttribute("cart",carMap);
        return "/client/cart.jsp";
    }
    //改变购物车
    @RequestMapping("changeCart.do")
    public String changeCart(String id,Integer count,HttpSession session){
        //根据id查找购物车中的商品
        Product product = cartService.findProductById(id);
        //获取session域中的购物车
        Map<Product,Integer> carMap = (Map<Product, Integer>) session.getAttribute("cart");
        if(count == 0){
            carMap.remove(product);//数量为零，清空商品
        }else{
            carMap.put(product,count);
        }
        return "/client/cart.jsp";
    }
}
