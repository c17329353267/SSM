package com.bookStore.admin.user.controller;

import com.bookStore.admin.user.service.AdminUserService;
import com.bookStore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/user/")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;
    //登陆
    @RequestMapping("logincheck.do")
    public String loginCheck(User user, Model model, HttpSession session){
        //System.out.println("user="+user);
        User login_User = adminUserService.findUser(user);
        if(login_User != null){
            //判断当前用户是否是超级管理员
            if("超级管理员".equals(login_User.getRole())){
                //如果是超级管理员
                //将用户信息放入session域
                session.setAttribute("login_User",login_User);
                return "/admin/login/home.jsp";
            }else{
                //非超级管理员，即权限不足
                model.addAttribute("user",user);
                model.addAttribute("error","权限不足，请使用超级管理员身份登陆");
                return "/admin/login/login.jsp";
            }
        }else {
            model.addAttribute("user",user);
            model.addAttribute("error","用户名或密码错误");
            return "/admin/login/login.jsp";
        }
    }
    //退出
    @RequestMapping("adminlogout.do")
    public String adminlogout(HttpSession session,Model model){
        //删除session域中登陆存储的信息
        session.removeAttribute("login_User");
        model.addAttribute("error","用户退出成功！");
        return "/admin/login/login.jsp";
    }
}
