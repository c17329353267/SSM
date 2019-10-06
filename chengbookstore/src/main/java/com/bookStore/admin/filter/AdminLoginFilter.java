package com.bookStore.admin.filter;

import com.bookStore.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截用户请求
 */
public class AdminLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取用户请求路径
        String url = request.getRequestURI();
        //如果用户访问的是login.jsp或者访问logincheck.do则可以直接进行访问
        if(url.endsWith("/logincheck.do") || url.endsWith("/login.jsp")){
            filterChain.doFilter(request,response);
        }else{
            //由于前台用户登陆信息存在在session域中，后台用户的登陆信息也存在session域中
            //所以此时判断后台登陆用户是超级管理员
            User login_User = (User) request.getSession().getAttribute("login_User");
            if(login_User != null) {
                if ("超级管理员".equals(login_User.getRole())) {
                    //如果用户已经登陆且是超级管理员
                    filterChain.doFilter(request,response);
                }else{
                    //请求重定向
                    response.sendRedirect(request.getContextPath()+"/client/error/privilege.jsp");
                }
            }else {
                //项目路径+文件路径
                response.sendRedirect(request.getContextPath()+"/client/error/privilege.jsp");
            }
        }
    }
}
