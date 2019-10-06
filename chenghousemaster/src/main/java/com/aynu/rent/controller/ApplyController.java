package com.aynu.rent.controller;

import com.aynu.entity.*;
import com.aynu.rent.service.ApplyService;
import com.aynu.rent.service.HouselistService;
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
public class ApplyController {
	@Autowired
	private UserlistService userlistService;
	@Autowired
	private HouselistService houselistService;
	@Autowired
	private ApplyService applyService;
	//申请看房
	@RequestMapping("/applycheckuserlist.action")
	public String applycheckuserlist(HttpSession httpSession,Model model,Integer id){
		//获取session域中登陆信息
		User user1= (User) httpSession.getAttribute("user");
		//获取用户的id属性
		Integer user_id=user1.getId();
		Userlist list=userlistService.findhasuserlist(user_id);
		if(list==null){
			model.addAttribute("error", "applycheck");
			return "redirect:houselist.action";
		}else{
			Houselist houselist=houselistService.findid(id);
			houselist.setStatus("已被申请");
			houselistService.updatehousestatus(houselist);
			Integer userlist_id=list.getId();
			Apply apply=new Apply();
			apply.setHouse_id(houselist.getHouseid());
			apply.setAddress(houselist.getAddress());
			apply.setPrice(houselist.getPrice());
			apply.setArea(houselist.getArea());
			apply.setStatus("申请中");
			apply.setUserlist_id(userlist_id);
			applyService.insertapply(apply);
			model.addAttribute("error", "applysuccess");
			return "redirect:houselist.action";
			
			
		}
		
	}
	//管理员查看所有申请看房列表
	@RequestMapping("/findapplylist.action")
	public String findapplylist(Model model,@RequestParam(defaultValue="1") Integer pageIndex) throws Exception{
		 //PageHelper.startPage(page, pageSize);
		int recourcounts=applyService.findAllApplylistCounts();
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recourcounts);
		pageModel.setPageSize(2);
		pageModel.setPageIndex(pageIndex);
		List<Apply> applylist=applyService.findapplylist(pageModel);
		model.addAttribute("applylist",applylist);
		model.addAttribute("pageModel",pageModel);
		model.addAttribute("mainPage","applylist.jsp");
		return "admin/main1";
	}
	
	@RequestMapping("/applychangehousestatus")
	public String applychangehousestatus(HttpSession httpSession,Model model,String house_id)throws Exception{
		User user1= (User) httpSession.getAttribute("user");
		Integer user_id=user1.getId();
		Userlist userlist=userlistService.findhasuserlist(user_id);
		Houselist houselist=houselistService.findhouseid(house_id);
		houselist.setStatus("已租赁");
		houselistService.updatehousestatus(houselist);
		Zulist zulist=new Zulist();
		zulist.setHouse_id(house_id);
		zulist.setPrice(houselist.getPrice());
		zulist.setAddress(houselist.getAddress());
		
		return "";
	}
	//管理员拒绝看房申请
	@RequestMapping("/refuseapply")
	public String refuseapply(String house_id,Model model){
		Houselist houselist=new Houselist();
		houselist.setHouseid(house_id);
		houselist.setStatus("未租赁");
		applyService.refuseapply(houselist);
		
		return "redirect:findapplylist.action";
	}
	
	//租客查看自己的 看房申请
	@RequestMapping("/getmyapply.action")
	public String getmyapply(Model model,HttpSession httpSession,@RequestParam(defaultValue="1") Integer pageIndex){
		//登陆的用户信息
		User user1= (User) httpSession.getAttribute("user");
		Userlist userlist=userlistService.findhasuserlist(user1.getId());
		//查询申请数据条数
		int recourcounts = userlistService.findUserApplyCounts(userlist.getId());
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recourcounts);
		pageModel.setPageSize(2);
		pageModel.setPageIndex(pageIndex);
		List<Userlist> list=userlistService.getmyapply(userlist.getId(),pageModel);
		model.addAttribute("userlist", list);
		model.addAttribute("pageModel",pageModel);
		model.addAttribute("mainPage", "myapply.jsp");
		return "zuke/main";
	}
	
	
}
