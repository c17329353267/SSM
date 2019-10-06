package com.bookStore.client.product.controller;

import com.bookStore.client.product.service.ProductService;
import com.bookStore.entity.Notice;
import com.bookStore.entity.Product;
import com.bookStore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client/product/")
public class ProductController {

    @Autowired
    private ProductService productService;
    //跟举类别查找
    @RequestMapping("findProductByCategory.do")
    public String findProductByCategory(String category, @RequestParam(defaultValue = "1") Integer pageIndex, Model model){
        //System.out.println(category);
        //根据类别查看数据条数
        int counts = productService.findCountsByCategory(category);
        //分页标签
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);//其实索引
        pageModel.setRecordCount(counts);//多少条数据
        //查看商品
        List<Product> products = productService.findProducts(category,pageModel);//商品在页面展示需要根据类别并做分页
        model.addAttribute("products",products);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("category",category);
        return "/client/product_list.jsp";
    }
    //根据书名查找
    @RequestMapping("findProductByName.do")
    public String findProductByName(String name,@RequestParam(defaultValue = "1") Integer pageIndex, Model model){
        //System.out.println("书名："+name);
        //查询该书数量
        int counts = productService.findCountsByName(name);
        PageModel pageModel= new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(counts);
        //查询书籍信息
        List<Product> products = productService.findProductsByName(name,pageModel);
        //System.out.println("products="+products);
        model.addAttribute("products",products);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("name",name);
        //return null;
        return "/client/product_search_list.jsp";
    }
    //根据id查找
    @RequestMapping("findProductById.do")
    public String findProductById(String id,Model model){
        System.out.println("id="+id);
        Product product = productService.findProductById(id);
        //System.out.println("pruduct="+product);
        model.addAttribute("p",product);
        return "/client/info.jsp";
    }
    //查看本周热卖
    @RequestMapping("showIndex.do")
    public String showIndex(Model model){
        //查看最新公告
        Notice notice = productService.findRecentNotice();
        //查看本周热卖
        List<Product> products = productService.findWeekHotProduce();
        model.addAttribute("n",notice);
        model.addAttribute("products",products);
        return "/client/index.jsp";
    }
}
