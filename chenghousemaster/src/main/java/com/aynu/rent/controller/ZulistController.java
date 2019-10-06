package com.aynu.rent.controller;

import com.aynu.entity.Hetong;
import com.aynu.entity.User;
import com.aynu.entity.Userlist;
import com.aynu.entity.Zulist;
import com.aynu.rent.service.UserlistService;
import com.aynu.rent.service.ZulistService;
import com.aynu.utils.PageModel;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/zulist")
public class ZulistController {
	@Autowired
	private ZulistService zulistService;
	@Autowired
	private UserlistService userlistService;
	//跳到增添合同的页面
	@RequestMapping("/toaddhetong.action")
	public String toaddhetong(Model model,String house_id){
		Hetong hetong=new Hetong();
		hetong.setHouse_id(house_id);
		model.addAttribute("hetong", hetong);
		model.addAttribute("mainPage", "addhetong.jsp");
		
		return "admin/main1";
	}
	//管理员查看所有在租列表
	@RequestMapping("/findzulist")
	public String findzulist(Model model,@RequestParam(defaultValue="1") Integer pageIndex) throws Exception{
		int recordCounts = zulistService.adminFindAllZulistCounts();
		System.out.println("recordCounts="+recordCounts);
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recordCounts);
		pageModel.setPageSize(2);
		pageModel.setPageIndex(pageIndex);
		List<Zulist> zulist=zulistService.findzuuserlist(pageModel);
		model.addAttribute("pageModel",pageModel);
		model.addAttribute("zulist", zulist);
		model.addAttribute("mainPage", "zulist.jsp");
		return "admin/main1";
	}
	
	//查看我的在租列表
	@RequestMapping("/myzulist.action")
	public String myzulist(Model model,HttpSession httpSession,@RequestParam(defaultValue="1") Integer pageIndex) throws Exception{

		//查看我的在租列表的数据条数

		//获取session域中的用户登陆信息
		User user1= (User) httpSession.getAttribute("user");
		int recordCounts = userlistService.findhasuserlistCounts(user1.getId());
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recordCounts);
		pageModel.setPageSize(2);
		pageModel.setPageIndex(pageIndex);
		Userlist userlist=userlistService.findhasuserlist(user1.getId());
		List<Userlist> list=userlistService.getUserzuList(userlist.getId(),pageModel);
		model.addAttribute("userlistzu", list);
		model.addAttribute("pageModel",pageModel);
		model.addAttribute("userlist",userlist);
		model.addAttribute("mainPage", "myzulist.jsp");
		return "zuke/main";
	}
	
}
