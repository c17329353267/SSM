package com.aynu.rent.controller;


import com.aynu.entity.*;
import com.aynu.rent.service.PaidService;
import com.aynu.rent.service.SolveService;
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
@RequestMapping("/wrong")
public class WrongController {
  @Autowired
  private SolveService solveService;
  @Autowired
  private UserlistService userlistService;
  @Autowired
  private PaidService paidService;
  @Autowired
  private ZulistService zulistService;
  //管理员查找所有已处理的报障
  @RequestMapping("/selectall.action")
  public String selectall(Model model, QueryVo vo, @RequestParam(defaultValue="1") Integer pageIndex){
	   //int recordCounts=solveService.findAllHasSolveWrong();
      Integer count=solveService.selectcount(vo);
	   PageModel pageModel = new PageModel();
	   pageModel.setPageIndex(pageIndex);
	   pageModel.setRecordCount(count);
	   pageModel.setPageSize(2);
		List<Solve> list=solveService.selectall(vo,pageModel);
		model.addAttribute("solve", list);
		model.addAttribute("count", count);
		//model.addAttribute("p", p);
        model.addAttribute("pageModel",pageModel);
		model.addAttribute("mainPage", "solve.jsp");
		model.addAttribute("vo", vo);
		return "admin/main1";
	}
		//租客查找自己已处理的报障
		@RequestMapping("/findmysolve")
		public String findmysolve(HttpSession httpSession,Model model,QueryVo vo,@RequestParam(defaultValue="1") Integer pageIndex){
			User user1= (User) httpSession.getAttribute("user");
			Userlist userlist=userlistService.findhasuserlist(user1.getId());
			//查询当前登陆用户已处理障碍

			vo.setUserlist_id(userlist.getId());
            int recordCounts = solveService.findMyHasSolveWrongCounts(vo);
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            pageModel.setRecordCount(recordCounts);
            pageModel.setPageSize(2);
			List<Solve> list=solveService.selectall(vo,pageModel);
			Integer count=solveService.selectcount(vo);
			model.addAttribute("solve", list);
			model.addAttribute("count", count);
			model.addAttribute("pageModel",pageModel);
			model.addAttribute("mainPage", "mysolve.jsp");
			model.addAttribute("vo", vo);
			return "zuke/main";
		}
		//管理员删除已处理报障记录
		@RequestMapping("/deletesolve")
		public String deletesolve(Integer id){
			solveService.deletesolve(id);
			return "redirect:selectall.action";
		}
		//zuke删除自己的已处理报障记录
			@RequestMapping("/zukedeletesolve")
			public String zukedeletesolve(Integer id){
				solveService.deletesolve(id);
				return "redirect:findmypaid.action";
			}
			//租客跳到我要报障页面
			@RequestMapping("/showaddwrong.action")
			public String showaddwrong(HttpSession httpSession,Model model,@RequestParam(defaultValue="1") Integer pageIndex)throws Exception{
				User user1= (User) httpSession.getAttribute("user");
				Userlist userlist=userlistService.findhasuserlist(user1.getId());
				int recordCounts = zulistService.findZulistCounts(userlist.getId());
				PageModel pageModel = new PageModel();
				pageModel.setPageIndex(pageIndex);
				pageModel.setRecordCount(recordCounts);
				pageModel.setPageSize(2);
				List<Zulist> list=zulistService.findzulistbyuid(userlist.getId(),pageModel);
				model.addAttribute("zulist", list);
				//model.addAttribute("p", p);
				model.addAttribute("pageModel",pageModel);
				model.addAttribute("mainPage", "showaddwrong.jsp");
				return "zuke/main";
			}
			//点击报障后跳转到添加报障信息页面
			@RequestMapping("/addwrong.action")
			public String addwrong(Integer id,Model model){
				Zulist zulist=paidService.findzukezulist(id);
				model.addAttribute("zulist", zulist);
				model.addAttribute("mainPage", "addwrong.jsp");
				return "zuke/main";
			}
			//添加报障信息到wrong表
			@RequestMapping("/insertwrong.action")
			public String insertwrong(Wrong wrong,Model model){
				solveService.insertwrong(wrong);
				model.addAttribute("error","insertwrong");
				return "redirect:showaddwrong.action";
			}
			//管理员查看所有未处理报障
			@RequestMapping("/wronglist")
			public String wronglist(Model model,@RequestParam(defaultValue="1") Integer pageIndex){
				QueryVo vo=new QueryVo();
				int recordCounts=solveService.findNotSolveAllCounts();
				PageModel pageModel = new PageModel();
				pageModel.setPageIndex(pageIndex);
				pageModel.setRecordCount(recordCounts);
				pageModel.setPageSize(2);
				List<Wrong> list=solveService.findwrong(vo,pageModel);
				model.addAttribute("wrong", list);
				//model.addAttribute("p", p);
				model.addAttribute("pageModel",pageModel);
				model.addAttribute("mainPage", "wrong.jsp");
				return "admin/main1";
			}
			//租客查看自己的未处理报障
			@RequestMapping("/mywronglist.action")
			public String mywronglist(Model model,HttpSession httpSession,@RequestParam(defaultValue="1") Integer pageIndex){
				User user1= (User) httpSession.getAttribute("user");
				Userlist userlist=userlistService.findhasuserlist(user1.getId());
				//查看未处理的房屋障碍数据条数
				int recordCounts = solveService.findNotSolveWrongCounts(userlist.getId());
				PageModel pageModel = new PageModel();
				pageModel.setPageIndex(pageIndex);
				pageModel.setRecordCount(recordCounts);
				pageModel.setPageSize(2);
				QueryVo vo=new QueryVo();
				vo.setUserlist_id(userlist.getId());
				List<Wrong> list=solveService.findwrong(vo,pageModel);
				model.addAttribute("wrong",list);
				model.addAttribute("pageModel",pageModel);
				model.addAttribute("mainPage", "mywrong.jsp");
				return "zuke/main";
			}
			//管理员处理报障
			@RequestMapping("/gotosolve")
			public String gotosolve(Integer id,Model model){
				
				Wrong wrong=solveService.findbyid(id);
				Solve solve=new Solve();
				solve.setHouse_id(wrong.getHouse_id());
				solve.setAddress(wrong.getAddress());
				solve.setDate(wrong.getDate());
				solve.setDetail(wrong.getDetail());
				solve.setName(wrong.getName());
				solve.setUserlist_id(wrong.getUserlist_id());
				solve.setStatus("已处理");
				solveService.gotosolve(id, solve);
				model.addAttribute("error", "duesucess");
				return "redirect:selectall.action";
			}
}
