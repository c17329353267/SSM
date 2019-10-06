package com.aynu.rent.controller;


import com.aynu.entity.Checkout;
import com.aynu.entity.User;
import com.aynu.entity.Userlist;
import com.aynu.rent.service.CheckoutService;
import com.aynu.rent.service.UserlistService;
import com.aynu.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	@Autowired
	private CheckoutService checkoutService;
	@Autowired
	private UserlistService userlistService;
	//管理员查看所有已经退租的信息列表
	@RequestMapping("/getallcheckout")
	public String getallcheckout(Model model ,@RequestParam(defaultValue="1") Integer pageIndex){
		int recordCounts=checkoutService.findAllCheckOutCounts();
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(2);
		pageModel.setRecordCount(recordCounts);
		List<Checkout> checkout=checkoutService.getallcheckout(pageModel);
		model.addAttribute("pageModel",pageModel);
		model.addAttribute("checkout", checkout);
		model.addAttribute("mainPage", "checkout.jsp");
		return "admin/main1";
	}
	//租客删除自己已退租列表
	@RequestMapping("/deletecheckout.action")
	public String deletecheckout(Integer id ) {
		checkoutService.deletecheckout(id);
		return "redirect:/checkout/getmycheckout.action";
	}
	//租客删除自己已退租列表
		@RequestMapping("/admindeletecheckout")
		public String admindeletecheckout(Integer id ) {
			System.out.println("checkout_id="+id);
			checkoutService.deletecheckout(id);
			return "redirect:/checkout/getallcheckout.action";
		}
	//查看当前登陆用户信息已经租退的
	@RequestMapping("/getmycheckout.action")
	public String getmycheckout(Model model,HttpSession httpSession,@RequestParam(defaultValue="1") Integer pageIndex ) {
	    //获取session域中的用户登陆信息
		User user1= (User) httpSession.getAttribute("user");
		Userlist userlist=userlistService.findhasuserlist(user1.getId());
        //当前登陆用户(租退人)已退租数据条数
        int recordCounts = userlistService.findCheckOutCounts(userlist.getId());
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(2);
        pageModel.setRecordCount(recordCounts);
        //获取租退人信息
		List<Userlist> list=userlistService.getmycheckout(userlist.getId(),pageModel);
		//model.addAttribute("p", p);
        model.addAttribute("pageModel",pageModel);
		model.addAttribute("userlistcheck", list);
		model.addAttribute("mainPage","mycheckout.jsp");
		return "zuke/main";
	}
}
