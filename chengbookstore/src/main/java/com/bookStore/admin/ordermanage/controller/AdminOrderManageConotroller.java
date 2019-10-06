package com.bookStore.admin.ordermanage.controller;

import com.bookStore.admin.ordermanage.service.IAdminOrderManageService;
import com.bookStore.entity.Order;
import com.bookStore.entity.OrderItem;
import com.bookStore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/orders/")
public class AdminOrderManageConotroller {


    @Autowired
    private IAdminOrderManageService adminOrderManageService;
    @RequestMapping("findAllAdminOrders.do")
    public String findAllAdminOrders(@RequestParam(defaultValue = "1")int pageIndex, Model model,
                                     Order order){

        int recordCount = adminOrderManageService.findAllOrderConunts(order);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        //查询数据库中的所有数据
        pageModel.setRecordCount(recordCount);
        List<Order> orderList = adminOrderManageService.findAllAdminOrders(order,pageModel);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("order",order);
        model.addAttribute("orders",orderList);
        return "/admin/orders/list.jsp";
    }
    @RequestMapping("editOrderById.do")
    public String editOrderById(String id,Model model){
        //System.out.println("订单id="+id);
        List<OrderItem> orderItem = adminOrderManageService.findAdminOrderItemById(id);
        Order order = adminOrderManageService.findAdminOrderById(id);
        model.addAttribute("orderItem",orderItem);
        model.addAttribute("order",order);

        return "/admin/orders/view.jsp";
    }
    @RequestMapping("removeOrderById.do")
    public String removeOrderById(String id){
        adminOrderManageService.removeOrderById(id);
        return "findAllAdminOrders.do";
    }
}
