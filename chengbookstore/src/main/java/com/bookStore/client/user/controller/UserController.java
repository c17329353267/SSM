package com.bookStore.client.user.controller;


import com.bookStore.client.user.service.UserService;
import com.bookStore.entity.Order;
import com.bookStore.entity.OrderItem;
import com.bookStore.entity.User;
import com.bookStore.utils.ActiveCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/client/user/")
public class UserController {

    @Autowired
    private UserService userService;

    //查找邮箱
    @RequestMapping("findEmail.do")
    @ResponseBody
    public String findEmail(String email){
        //数据库设置了唯一字段，根据传入的email去数据库查找，如果有则已经存在
        //System.out.println("email="+email);
        User user = userService.findUser(email);
        if(user != null){//查到
           return "EXIT" ;
        }else {
            return "NOEXIT";
        }

    }
    //查找用户名
    @RequestMapping("findUsername.do")
    @ResponseBody
    public String findUsername(String username){
        //System.out.println("usrename"+username);//由于用户名设置了唯一属性
        User user = userService.findUserByUsername(username);
        if(user != null){
            return "EXIT";
        }else{
            return "NOEXIT";
        }
        //return null;
    }
   /*
    根据注册的邮箱发送邮件
    */
    //添加
    @RequestMapping("register.do")
    public String register(User user, HttpSession session, String checkCode, HttpServletRequest request){
        //获取验证码
        String getCheckCode = (String) session.getAttribute("checkcode_session");
      /*  System.out.println("checkCode="+getCheckCode);
        System.out.println("user="+user);*/
        //设置唯一激活编码
        user.setActiveCode(ActiveCodeUtils.createActiveCode());
        //如果输入的验证码和生成的验证码一样
        if(getCheckCode.equals(checkCode)){
            //上传的邮箱
            int row = userService.addUser(user,request);
            System.out.println("row="+row);
            if(row > 0){
                //添加成功
                return "/client/registersuccess.jsp";
            }else {
                request.setAttribute("user",user);
                request.setAttribute("fail","用户注册失败，请重新注册！");
                return "/client/register.jsp";
            }
        }else{//若果验证不一样
            //model.addAttribute("user",user);
            request.setAttribute("fail","用户注册失败，请重新注册！");
            request.setAttribute("user",user);
            return "/client/register.jsp";
        }

    }
    //注册邮箱根据注册码修改状态
    @RequestMapping("activeUser.do")
    public String activeUser(String activeCode){
        int row = userService.changeStateByActiveCode(activeCode);
        if(row > 0){
            //注册成功
            return "/client/activesuccess.jsp";
        }else{
            //注册失败
            return "/client/failActive.jsp";
        }
    }
    //判断用户是否登陆,用户的登陆名和密码存储在Session域中
    //怎么判断用户上次是否勾选自动登陆，如果勾选直接到我的账户
    @RequestMapping("myAccount.do")
    public String myAccount(HttpSession session,HttpServletRequest request) {
        //获取session域中存储的登陆User信息
        User login_User = (User) session.getAttribute("login_User");
        //如果sesson域中存在用户名
        if(login_User != null){
            return "/client/myAccount.jsp";
        }else{
            //此时的loginUser == null
             //session域中不存在该用户，跳转到登陆界面
            //session域中不存，再判断cookie域中是否保存有用户名密
            login_User = autologin(request);
            if(login_User != null){//不为空表示数据库中存在此用户名和密码，即做登陆
                session.setAttribute("login_User",login_User);
                return "/client/myAccount.jsp";
             }
            return "/client/login.jsp";
        }
       // return null;
    }
    //用户登陆,用户名存入session域
    @RequestMapping("login.do")
    public String login(User user, String remember, String autologin, HttpSession session,
                        HttpServletRequest request, HttpServletResponse response){
        //根据当前用户名和密码查询数据进行登陆
        User login_User = userService.findUserByNameAndPwd(user);
        if(login_User != null){//查到不为空
            //查看当前用户是否被激活
            if(login_User.getState() == 1) {
                //勾选自动登陆，或者勾选记住用户名,使用Cookie来保存信息
                if("1".equals(autologin)){//自动登陆被勾选
                    //把用户名和密码存在cookie中
                    addCookie(autologin,user,response,request);
                }else if ("1".equals(remember)){//记住用户名
                    addCookie(autologin,user,response,request);
                }
                session.setAttribute("login_User", login_User);
                return "/client/index.jsp";
            }else {
                request.setAttribute("error","该用户未激活");
                return "/client/login.jsp";
            }
        }else {//登陆失败
            request.setAttribute("error","用户名或密码错误");
            return "/client/login.jsp";
        }

    }
    //用户信息修改
    @RequestMapping("modifyUser.do")
    public String modifyUser(User user,Model model){
        System.out.println("user="+user);
        int row = userService.modifyUserById(user);
        if(row>0){
            //修改成功
            return "/client/login.jsp";
        }else {
            //修改失败
            model.addAttribute("fail","修改失败！！！");
            return "/client/modifyuserinfo.jsp";
        }

    }
    //用户退出
    @RequestMapping("logout.do")
    public String logout(HttpSession session,HttpServletResponse response){
        //删除session域中的内容
        session.removeAttribute("login_User");
        //把当前cookie中存在的用户名和密码，使用null进行覆盖
        Cookie cookie1 = new Cookie("bookstore_name",null);
        cookie1.setMaxAge(0);
        Cookie cookie2 = new Cookie("bookstore_pwd",null);
        cookie2.setMaxAge(0);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        return "/client/login.jsp";
    }
    //订单查询
    @RequestMapping("findOrderByUser.do")
    public String findOrderByUser(HttpSession session,Model model){
        //获取session域中的登陆用户信息
        User login_user = (User) session.getAttribute("login_User");
        List<Order> orders = userService.findOrdersByUser(login_user);
        model.addAttribute("orders",orders);
        return "/client/orderlist.jsp";
    }
    //根据id查看订单的详细信息
    @RequestMapping("findOrderById.do")
    public String findOrderById(String id,Model model){
        //id主键 订单项信息
        System.out.println("订单id="+id);
        List<OrderItem> items = userService.findOrderItemById(id);
        //items  order product bnum
        model.addAttribute("items",items);
        System.out.println("items=="+items);
        return "/client/orderInfo.jsp";
    }
    //删除订单
    @RequestMapping("delOrderById.do")
    public String delOrderById(String id,String type,Model model){
        //判断此id对应的商品是否是已经支付，已经支付不用修改库存，否则修改库存
        boolean result = userService.removeOrder(id,type);//删除订单，删除订单项，//恢复库存数量
        if(result){
            return "/client/delOrderSuccess.jsp";
        }else{
            model.addAttribute("error","删除订单失败！");
            return "/client/orderlist.jsp";
        }
    }
    //把用户名和密码添加到cookie中
    private  void addCookie(String autoLogin,User user,
                            HttpServletResponse response,HttpServletRequest request){
        try {
            //无论是否选中用户名,用户名都要被存储在cookie中
            Cookie cookie1 = null;
            //由于用户名会存在存储中文，工具会按照GBK进行编码，容易出现乱码
            cookie1 = new Cookie("bookstore_name", URLEncoder.encode(user.getUsername(),"utf-8"));
            //设置时间
            cookie1.setMaxAge(60*60*24*7);
            //让cookie作用在当前路径下
            //cookie1.setPath(request.getContextPath()+"/");
            response.addCookie(cookie1);
            //如果勾选自动登陆
            if("1".equals(autoLogin)){
                //把密码保存到cookie中
                Cookie cookie2 = new Cookie("bookstore_pwd",URLEncoder.encode(user.getPassword(),"utf-8"));
                cookie2.setMaxAge(60*60*24*7);
                //cookie2.setPath(request.getContextPath()+"/");
                response.addCookie(cookie2);
            }
        }catch (UnsupportedEncodingException e){
                e.printStackTrace();
        }
    }

    //判断用户上次是否勾选自动登陆
    /*bookstore_name,cookiestore_password存储在cookie域中
       如果用户上次勾选了自动登陆，则循环遍历cookie,查看域中是否存在值
     */
    private User autologin(HttpServletRequest request) {
        User user = new User();
        String username = null;
        String password  = null;
        //获取请求的所有的cookie
        Cookie[] cookies = request.getCookies();
        //遍历
        try {
        for(Cookie cookie:cookies){

                if("bookstore_name".equals(cookie.getName())){//如果域中存在此name
                //用户名密码存在编码，在此解码
                    username = URLDecoder.decode(cookie.getValue(),"utf-8");
                }
                if("bookstore_pwd".equals(cookie.getName())){
                    password = URLDecoder.decode(cookie.getValue(),"utf-8");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
            user.setUsername(username);
            user.setPassword(password);
            //根据用户名和密码查找当前用户是否存在
            return userService.findUserByNameAndPwd(user);
    }
}
