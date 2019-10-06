package com.aynu.usermanage.controller;

import com.aynu.entity.User;
import com.aynu.pages.PageModel;
import com.aynu.user.service.UserService;
import com.aynu.usermanage.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class UserManageController {
    @Autowired
    private UserManageService userManageService;

    @RequestMapping("/findUser.do")
    public String findUser(@RequestParam(defaultValue = "1")Integer pageIndex,
                           User user, Model model){

        //获取用户数量
        int count = userManageService.findUserCount(user);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(count);
        //获取用户信息(user/pageModel点击搜索)
        List<User> users = userManageService.findUser(user,pageModel);
        model.addAttribute("user",user);
        model.addAttribute("users",users);
        model.addAttribute("pageModel",pageModel);
        return "/jsp/user/user.jsp";
    }
    @RequestMapping("/addUser.do")
    public String addUser(User user,Model model){
        Date createDate = new Date();
        user.setCreatedate(createDate);
        int row = userManageService.addUser(user);
        if (row>0){
            //添加成功
           // model.addAttribute("addNews","好棒棒奥！添加成功");
            return "findUser.do";
        }else{
            model.addAttribute("fail","小猪佩奇添加失败！");
            return "/jsp/fail.jsp";
        }

    }
    //根据id查找当前用户信息并做回显操作
    @RequestMapping("/findUserById.do")
    public String findUserBuId(Integer id,Model model){
        User user = userManageService.findUserById(id);
        //System.out.println("findbyid"+user);
        model.addAttribute("user",user);
        return "/jsp/user/showUpdateUser.jsp";
    }
    //修改
    @RequestMapping("/modifyUser.do")
    public String modifyUser(User user,Model model){
       int rows =  userManageService.modifyUser(user);
        //System.out.println("rows="+rows);
       if (rows>0){
           //修改成功,查询
           return "findUser.do";
       }else{
           //修改失败
           model.addAttribute("fail","小猪佩奇修改失败");
           return "/jsp/fail.jsp";
       }

    }
    //删除
    @RequestMapping("/removeUser.do")
    public String removeUser(String ids, Model model, HttpSession session){
        //为了防止删除的当前用户
        //获取session域中的登陆信息
        User login_user = (User) session.getAttribute("loginUser");
        //System.out.println("login_user="+login_user);
        //根据，对字符串ids进行分割
        String[] idArray = ids.split(",");
        //System.out.println("idArray="+idArray);
        List<Integer> IDS = new ArrayList<>();
        //将分割的字符串放入list集合
        for (String str:idArray) {
            //放入list集合前判断是否有当前登陆用户的id
            if(login_user.getId() == Integer.parseInt(str)){
                //如果含有就不执行删除操作，返回删除失败界面
                model.addAttribute("fail","小猪佩奇删除失败");
                return "/jsp/fail.jsp";
            }
            IDS.add(Integer.parseInt(str));
        }
        try {
            //删除
            int rows = userManageService.removeUserById(IDS);
            //如果删除成功
            if(rows > 0){
                return "findUser.do";
            }else {//删除失败
                model.addAttribute("fail","删除用户失败");
                return "/jsp/fail.jsp";
            }
        }catch (DataIntegrityViolationException e){
            model.addAttribute("fail","用户有发布公告或者文件，不能删除！");
            return "/jsp/fail.jsp";
        }

    }


}
