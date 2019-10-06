package com.aynu.user.controller;

import com.aynu.entity.User;
import com.aynu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;
    //登陆校验
    @RequestMapping("login.do")
    //为了防止越过登陆直接进入main.jsp,采用拦截器，通过session域中是否存在已经登陆的用户信息
    public String loginCheck(User user, HttpSession session, Model model) {
        User loginUser = userService.findPersonByNameAndpassword(user);
        if(loginUser != null){
            //如果存在用户信息就放入session域中
            session.setAttribute("loginUser",loginUser);
            return "/jsp/main.jsp";
        }else{
            //用户名密码错误
            model.addAttribute("login_error","用户名或密码错误，请重新输入！");
            return "/index.jsp";
        }
    }
    //注销退出
    @RequestMapping("logout.do")
    public String logout(HttpSession session,Model model){
        //删除session域中存储的用户信息
        session.removeAttribute("loginUser");
        model.addAttribute("login_error","请重新登陆");
        return "/index.jsp";
    }

}
