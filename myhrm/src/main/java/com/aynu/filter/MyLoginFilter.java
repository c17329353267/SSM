package com.aynu.filter;


import com.aynu.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 采用过滤器拦截越域用户
* 方法：通过获取session域中是否存在对象
* */
public class MyLoginFilter implements Filter {
    private  String[] need_uri = {"/index.jsp","/loginForm.jsp","/login.do"};
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //index.jsp,loginForm.jsp,login.do放掉这三个
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取每次的请求路径
        String strURI = request.getRequestURI();
        //循环查看请求地址是否包含上三个
        boolean flag = false;
        for (String str : need_uri
        ) {
            //如果包含，即已经登陆；防止空指针异常
            if (strURI.contains(str)) {
                //放行
                flag = true;
                filterChain.doFilter(request, response);
                break;
            }
        }
        if (!flag) {
            //不包含 拦截
            User login_user = (User) request.getSession().getAttribute("loginUser");
            if (login_user != null) {
                filterChain.doFilter(request, response);
            } else {
                request.setAttribute("login_error", "你还未登陆请登陆");
                //请求转发
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

}
