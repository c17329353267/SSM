package com.aynu.rent.controller;

import com.aynu.entity.Schedule;
import com.aynu.rent.service.ScheduleService;
import com.aynu.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;
	//管理员查看自己的日程(收租计划)安排
	@RequestMapping("/selectAll")
	public String selectAll(Model model ,@RequestParam(defaultValue="1") Integer pageIndex){
		// PageHelper.startPage(page, pageSize);
		int recourcounts=scheduleService.selectAllScheduleCounts();
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recourcounts);
		pageModel.setPageSize(2);
		pageModel.setPageIndex(pageIndex);
		List<Schedule> schedule=scheduleService.selectAll(pageModel);
		model.addAttribute("schedule", schedule);
		model.addAttribute("pageModel",pageModel);
		model.addAttribute("mainPage", "schedule.jsp");
		return "admin/main1";
	}
	@RequestMapping("/deleteschedule.action")
	public String deleteschedule(Integer id){
		scheduleService.deleteschedule(id);
		return "redirect:selectAll.action";
	}
	@RequestMapping("/insertschedule.action")
	public String insertschedule(Schedule schedule,Model model){
		scheduleService.insertschedule(schedule);
		
		return "redirect:selectAll.action";
		
	}
	@RequestMapping("/updateschedule.action")
	public String updateschedule(Schedule schedule,Model model){
		scheduleService.updateschedule(schedule);
		model.addAttribute("error", "更新成功");
		model.addAttribute("schedule", schedule);
		model.addAttribute("mainPage", "updateschedule.jsp");
		return "admin/main1";
		
	}
	@RequestMapping("/toinsert.action")
	public String toinsert(Model model){
		model.addAttribute("mainPage", "addschedule.jsp");
		
		return "admin/main1";
		
	}
	@RequestMapping("/toupdate.action")
	public String toupdate(Model model,Integer id){
		Schedule schedule=scheduleService.selectbyid(id);
		model.addAttribute("schedule", schedule);
		
		model.addAttribute("mainPage", "updateschedule.jsp");
		
		return "admin/main1";
		
	}
}
