package com.aynu.rent.controller;

import com.aynu.entity.Houselist;
import com.aynu.rent.service.HouselistService;
import com.aynu.utils.PageModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//import cn.itcast.utils.Page;
@Controller
public class HoustlistController {
	@Autowired
	private HouselistService houselistService;
	//查询所有房源信息
	@RequestMapping("/houselist.action")
	public String houselist(Model model ,@RequestParam(defaultValue="1") Integer pageIndex){
		//查询数据库房源列表条数
		int recordCounts = houselistService.selectAllHouseCounts();
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recordCounts);
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(2);
		List<Houselist> houselist=houselistService.selectAll(pageModel);
		model.addAttribute("houselist",houselist);
		model.addAttribute("pageModel",pageModel);
		model.addAttribute("mainPage","houselist.jsp");
		System.out.println("houselist="+houselist);
		return "zuke/main";
	}
	//管理员admin查看房源列表
	@RequestMapping("/ahouselist.action")
	public String ahouselist(Model model ,@RequestParam(defaultValue="1") Integer pageIndex){
		int recordCounts = houselistService.selectAllHouseCounts();
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recordCounts);
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(2);
		List<Houselist> houselist=houselistService.selectAll(pageModel);
		model.addAttribute("pageModel",pageModel);
		model.addAttribute("houselist",houselist);
		model.addAttribute("mainPage","ahouselist.jsp");
		return "admin/main1";
	}
	//添加房源信息之前查看是否存在通向houst_id
	@RequestMapping("/findAllHoustByid.action")
	@ResponseBody//用于接收json数据
	public String findAllHoustByid(String houseid){
		System.out.println("Houseid="+houseid);
		Houselist houselist1 = houselistService.findhouseid(houseid);
		if(houselist1 !=null){
			return "EXIT";
		}else {
			return "OK";
		}
	}
	//添加房源信息
	@RequestMapping("/addhouse.action")
	public String addhouse(Model model ,Houselist houselist){
		
		String houseid=houselist.getHouseid();
		Houselist houselist1=houselistService.findhouseid(houseid);
		if(houselist1!=null){
			model.addAttribute("error","该房屋id已存在");
			model.addAttribute("houselist",houselist);
			model.addAttribute("mainPage","addhouse.jsp");
			return "admin/main1";
		}else{
			model.addAttribute("error","添加成功");
			houselistService.inserthouse(houselist);
			model.addAttribute("houselist",houselist);
			model.addAttribute("mainPage","addhouse.jsp");
		return "admin/main1";
	}
		}
	//到添加房源界面
	@RequestMapping("/toaddhouse.action")
	public String toaddhoust(Model model){
		model.addAttribute("mainPage","addhouse.jsp");
		return "admin/main1";
	}
	@RequestMapping("/deletehouse.action")
	public String deletehouse(Integer id){
		houselistService.deletehouse(id);
		
		
		return "redirect:ahouselist.action";
	}
	@RequestMapping("/toahouselist")
	public String toahouselist(){
		
		return "ahouselist.action";
	}
	@RequestMapping("/findid")
	public String findid(Integer id,Model model){
		Houselist list=houselistService.findid(id);
		model.addAttribute("houselist",list);
		model.addAttribute("mainPage", "changehouse.jsp");
		return "admin/main1";
	}
	@RequestMapping("/findhouseidupdate")
	public String findhouseidupdate(Houselist houselist,Model model){
		Houselist list=houselistService.findhouseidupdate(houselist);
		if(list!=null){
			model.addAttribute("houselist",houselist);
			model.addAttribute("mainPage", "changehouse.jsp");
			model.addAttribute("error","该房屋id已存在");
			return "admin/main1";
		}
		else{
			houselistService.updatehouse(houselist);
			model.addAttribute("houselist",houselist);
			model.addAttribute("mainPage", "changehouse.jsp");
			model.addAttribute("error","更新成功");
			return "admin/main1";
		}
	}
	
}
