package com.aynu.user.controller;


import com.aynu.entity.User;
import com.aynu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.action")
	public String userList() throws Exception{

		return "login";
		
	}
	@RequestMapping("/logincheck.action")
	public String login(User user, Model model, HttpSession httpSession) throws Exception{
		User user1=userService.login(user);
		if(user1!=null){
			//把登陆的用户信息存放在session域中
			httpSession.setAttribute("user", user1);
			//判断是租客还是管理员
			if(user1.getType().equals("zuke")){
				return "zuke/main";
			}
			else{

				return "admin/main1";
			}
		}else{
			String error="error";
			model.addAttribute("error", error);
		return "login";
		}
	}
	@RequestMapping("/toindex.action")
	public String toindex(Model model) throws Exception{
		
		return "admin/index";
	}

/*	//把用户名和密码添加到cookie中
	private  void addCookie(String autoLogin, User user,
							HttpServletResponse response, HttpServletRequest request){
		try {
			//无论是否选中用户名,用户名都要被存储在cookie中
			Cookie cookie1 = null;
			//由于用户名会存在存储中文，工具会按照GBK进行编码，容易出现乱码
			cookie1 = new Cookie("houseZu_name", URLEncoder.encode(user.getUsername(),"utf-8"));
			//设置时间
			cookie1.setMaxAge(60*60*24*7);
			//让cookie作用在当前路径下
			//cookie1.setPath(request.getContextPath()+"/");
			response.addCookie(cookie1);
			//如果勾选自动登陆
			if("1".equals(autoLogin)){
				//把密码保存到cookie中
				Cookie cookie2 = new Cookie("houseZu_pwd",URLEncoder.encode(user.getPassword(),"utf-8"));
				cookie2.setMaxAge(60*60*24*7);
				//cookie2.setPath(request.getContextPath()+"/");
				response.addCookie(cookie2);
			}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}

	//判断用户上次是否勾选自动登陆
    *//*bookstore_name,cookiestore_password存储在cookie域中
       如果用户上次勾选了自动登陆，则循环遍历cookie,查看域中是否存在值
     *//*
	private User autologin(HttpServletRequest request) throws Exception {
		User user = new User();
		String username = null;
		String password  = null;
		//获取请求的所有的cookie
		Cookie[] cookies = request.getCookies();
		//遍历
		try {
			for(Cookie cookie:cookies){

				if("houseZu_name".equals(cookie.getName())){//如果域中存在此name
					//用户名密码存在编码，在此解码
					username = URLDecoder.decode(cookie.getValue(),"utf-8");
				}
				if("houseZu_pwd".equals(cookie.getName())){
					password = URLDecoder.decode(cookie.getValue(),"utf-8");
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		user.setUsername(username);
		user.setPassword(password);
		//根据用户名和密码查找当前用户是否存在
		return userService.login(user);
	}*/
}

