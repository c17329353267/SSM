package com.aynu.noticemanage.controller;

import com.aynu.entity.Notice;
import com.aynu.entity.User;
import com.aynu.noticemanage.service.NoticeService;
import com.aynu.pages.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/findAllNotices.do")
    public String findAllNotice(@RequestParam(defaultValue = "1")Integer pageIndex, Notice notice, Model model){
        //查询数据库总条数
        System.out.println("notice="+notice);
    int counts = noticeService.findNoticeCounts(notice);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        //在此赋初值按照每页两条数据显示
        pageModel.setPageSize(2);
        //总条数
        pageModel.setRecordCount(counts);
        //查询所有数据
        List<Notice> notices = noticeService.findAllNotices(notice,pageModel);
        //将查询的信息进行回显
        model.addAttribute("notices",notices);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("notice",notice);
        return "/jsp/notice/notice.jsp";
    }
    //修改
    @RequestMapping("/modifyNotice.do")
    public String modifyNotice(String flag,Model model,Integer id,Notice notice){

        if(flag == null){
            //根据id进行查找
            notice = noticeService.findNoticeById(id);
            //System.out.println("findnoticeByid="+notice);
            model.addAttribute("notice",notice);
            return "/jsp/notice/showUpdateNotice.jsp";
        }else{
            //在此进行修改
            System.out.println("mofifyNotice="+notice);
            int row = noticeService.modifyNotice(notice);
            System.out.println("row="+row);
            if(row > 0){
                //修改成功
                return "findAllNotices.do";
            }else{
                //修改失败
                model.addAttribute("fail","公告管理修改失败");
                return "/jsp/fail.jsp";
            }
        }
    }
    //预览
    @RequestMapping("/previewNotice.do")
    public String previewNotice(Integer id,Model model){
        //根据id查找一条Notice
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice",notice);
        return "/jsp/notice/previewNotice.jsp";
    }
    //添加
    @RequestMapping("/addShowNotice.do")
    public String addShowNotice(Notice notice, Model model, HttpSession session){
        System.out.println("addNotice="+notice);
         User user = (User) session.getAttribute("loginUser");
        System.out.println("add--User="+user);
        notice.setUser(user);
        int row = noticeService.addNotice(notice);
        if(row > 0){
            //添加成功
           return "findAllNotices.do" ;
        }else {
            //添加失败
            model.addAttribute("fail","添加失败小猪佩奇");
            return "/jsp/fail.jsp";
        }
    }
    //删除
    @RequestMapping("/removeNotices.do")
    public String removeNotices(Integer[] ids,Model model){
       /* for (Integer id:ids
             ) {
            System.out.print(id+" ");
        }*/
       int rows = noticeService.removeNotices(ids);
       if(ids.length == rows){
           //删除成功
           return "findAllNotices.do";
       }else{
           model.addAttribute("fail","删除失败小猪佩奇");
           return "/jsp/fail.jsp";
       }

    }
}
