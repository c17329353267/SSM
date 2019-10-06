package com.aynu.rent.controller;

import com.aynu.entity.Applyout;
import com.aynu.entity.User;
import com.aynu.entity.Userlist;
import com.aynu.entity.Zulist;
import com.aynu.rent.service.ApplyoutService;
import com.aynu.rent.service.UserlistService;
import com.aynu.rent.service.ZulistService;
import com.aynu.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/applyout")
public class ApplyoutController {
	@Autowired
	private ZulistService zulistService;
	@Autowired
	private ApplyoutService applyoutService;
	@Autowired
	private UserlistService userlistService;
	//插入退租信息
	@RequestMapping("/insertapplyout")
	public String insertapplyout(String house_id,Model model){
		Zulist zulist=zulistService.findzulist(house_id);
		applyoutService.insertapplyout(zulist);
		model.addAttribute("error", "applysuccess");
		return "redirect:/zulist/myzulist.action";
	}
	//查看所有退租申请(管理员应该有的权限)
	@RequestMapping("/findallapplyout.action")
	public String findallapplyout(Model model ,@RequestParam(required=false,defaultValue="1") Integer pageIndex){
		int recourcounts = applyoutService.findAllUserApplyOutCounts();
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recourcounts);
		pageModel.setPageSize(2);
		pageModel.setPageIndex(pageIndex);
		List<Applyout> applyout=applyoutService.findallapplyout(pageModel);
		model.addAttribute("applyout", applyout);
		//model.addAttribute("p", p);
		model.addAttribute("pageModel",pageModel);
		model.addAttribute("mainPage", "applyout.jsp");
		return "admin/main1";
	}
	//管理员拒绝退租申请
	@RequestMapping("/refuseapplyout")
	public String refuseapplyout(Model model,Integer id){
		Applyout applyout=new Applyout();
		applyout.setId(id);
		applyout.setStatus("已拒绝");
		applyoutService.updateapplyout(applyout);
		model.addAttribute("mainPage", "applyout.jsp");
		//return "redirect:findallapplyout.action";
		return "redirect:getmyapplyout.action";
	}
	//管理员同意退租申请
	@RequestMapping("/agreeapplyout")
	public String agreeapplyout(Model model,Integer id){
		applyoutService.agreeapplyout(id);
		model.addAttribute("error", "applyoutsucess");
		return "redirect:findallapplyout.action";
	}
		//删除申请退租列表(对于已经被拒绝的进行删除)
		@RequestMapping("/deleteapplyout.action")
		public String deleteapplyout(Model model,Integer id,HttpSession httpSession){
		//删除后的跳转，管理员进入删除跳转到管理端权限界面，普通身份删除跳转到zuke端界面
			//获取session域 中登陆信息
			User user1= (User) httpSession.getAttribute("user");
			//Userlist userlist=userlistService.findhasuserlist(user1.getId());
			applyoutService.deleteapplyout(id);
			model.addAttribute("error", "deletesucess");
			if("admin".equals(user1.getType())){//如果登陆的是管理员身份
				return "redirect:findallapplyout.action";
			}else {
				return "redirect:getmyapplyout.action";
			}

		}
		//租客查看自己的 退房申请
		@RequestMapping("/getmyapplyout.action")
		public String getmyapplyout(Model model,HttpSession httpSession,@RequestParam(defaultValue="1") Integer pageIndex){
			User user1= (User) httpSession.getAttribute("user");
			Userlist userlist=userlistService.findhasuserlist(user1.getId());
			//查看当前登陆用户申请退租信息条数
			int recourcounts = userlistService.findUserelfApplyOut(userlist.getId());
			PageModel pageModel = new PageModel();
			pageModel.setRecordCount(recourcounts);
			pageModel.setPageSize(2);
			pageModel.setPageIndex(pageIndex);
			//PageHelper.startPage(page, pageSize);
			List<Userlist> list=userlistService.getmyapplyout(userlist.getId(),pageModel);
			//PageInfo<Userlist> p=new PageInfo<Userlist>(list);
			model.addAttribute("userlist", list);
			//model.addAttribute("p", p);
			model.addAttribute("pageModel",pageModel);
			model.addAttribute("mainPage", "myapplyout.jsp");
			return "zuke/main";
		}
}
