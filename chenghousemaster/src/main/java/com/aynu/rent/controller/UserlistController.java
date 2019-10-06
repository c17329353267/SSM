package com.aynu.rent.controller;


import com.aynu.entity.User;
import com.aynu.entity.Userlist;
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
public class UserlistController {

	@Autowired
	private UserlistService userlistService;

	@RequestMapping("/findhasuserlist.action")
	public String findhasuserlist(HttpSession httpSession, Model model) throws Exception {
		User user1 = (User) httpSession.getAttribute("user");
		Integer user_id = user1.getId();
		Userlist userlist = userlistService.findhasuserlist(user_id);
		model.addAttribute("userlist", userlist);
		model.addAttribute("mainPage", "updateuserlist.jsp");
		return "zuke/main";

	}

	//查找并更新用户信息
	@RequestMapping("/checkuserlist.action")
	public String checkuserlist(Model model, Userlist userlist, HttpSession httpSession) throws Exception {
		if (userlist.getId() == null) {
			String idcard = userlist.getIdcard();
			Userlist list = userlistService.checkuserlist(idcard);

			if (list != null) {
				model.addAttribute("error", "该身份证已被绑定,一个身份证号码只能被一个账户绑定！");
				model.addAttribute("mainPage", "updateuserlist.jsp");
				model.addAttribute("userlist", userlist);
			} else {
				User user1 = (User) httpSession.getAttribute("user");
				Integer user_id = user1.getId();
				userlist.setUser_id(user_id);
				userlistService.insertuserlist(userlist);
				Userlist list1 = userlistService.checkuserlist(idcard);
				model.addAttribute("error", "资料完善成功");
				model.addAttribute("mainPage", "updateuserlist.jsp");
				model.addAttribute("userlist", list1);
			}


		} else {

			Userlist list = userlistService.finduserlistupdate(userlist);
			if (list != null) {
				model.addAttribute("error", "该身份证号码已被绑定");
				model.addAttribute("mainPage", "updateuserlist.jsp");
				model.addAttribute("userlist", userlist);
			} else {
				userlistService.updateuserlist(userlist);
				model.addAttribute("error", "更新成功");
				model.addAttribute("mainPage", "updateuserlist.jsp");
				model.addAttribute("userlist", userlist);
			}

		}
		return "zuke/main";
	}

	//管理员查看所有的用户
	@RequestMapping("/findalluserlist.action")
	public String findalluserlist(Model model, @RequestParam(defaultValue = "1") Integer pageIndex) {
		int recourcounts = userlistService.findAllZuKeListCounts();
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recourcounts);
		pageModel.setPageSize(2);
		pageModel.setPageIndex(pageIndex);
		List<Userlist> userlist = userlistService.findalluserlist(pageModel);
		model.addAttribute("userlist", userlist);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("mainPage", "userlist.jsp");
		return "admin/main1";

	}

	//删除用户信息
	@RequestMapping("/deleteuserlist.action")
	public String deleteuserlist(Model model, Integer id) {
		userlistService.deleteuserlist(id);
		model.addAttribute("error", "deletesuccess");
		return "redirect:findalluserlist.action";
	}

	//到添加账户界面
	@RequestMapping("/toAdduserlist.action")
	public String addUserList(Model model) {
		model.addAttribute("mainPage", "adduserlist.jsp");
		//model.addAttribute("mainPage", "addschedule.jsp");
		return "admin/main1";
	}

	//添加操作
	@RequestMapping("/addNewUser.action")
	public String addNewUser(User user, Model model, @RequestParam(defaultValue = "1") Integer pageIndex) {
		//System.out.println("添加用户操作user="+user);
		userlistService.addNewUser(user);
		model.addAttribute("user", user);
		int recourcounts = userlistService.findAllZuKeListCounts();
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recourcounts);
		pageModel.setPageSize(2);
		pageModel.setPageIndex(pageIndex);
		List<Userlist> userlist = userlistService.findalluserlist(pageModel);
		model.addAttribute("userlist", userlist);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("mainPage", "userlist.jsp");
		//return "findalluserlist.action";
		return "admin/main1";
	}

	//到修改密码界面
	@RequestMapping("/tomodifypassword.action")
	public String tomodifypassword(Model model) {
		model.addAttribute("mainPage", "modifypassword.jsp");
		return "zuke/main";
	}
	//去数据库修改密码
	@RequestMapping("/modifypassword.action")
	public String modifypassword(HttpSession session,String password){
		//从session域中获取登陆用户信息
		User user = (User) session.getAttribute("user");
		user.setPassword(password);
		userlistService.modifyUserPassoword(user);
		return "zuke/main";
	}

}
