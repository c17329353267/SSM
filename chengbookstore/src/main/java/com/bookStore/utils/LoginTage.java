package com.bookStore.utils;


import com.bookStore.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 自定义简单标签，使用拦截器
 * 路径分为前台路径和后台路径
 */
//继承简单标签
public class LoginTage extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        PageContext context = (PageContext) this.getJspContext();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        //获取session域中是否存在登陆用户信息
        User login_User = (User) context.getSession().getAttribute("login_User");
        //Sesson域中未获取到该用户，说明用户未登陆
        if(login_User == null){
            //重定向，请求路径
            //System.out.println("request.getContextPat"+request.getContextPath());
            response.sendRedirect(request.getContextPath()+"/client/error/privilege.jsp");
        }
    }













}
