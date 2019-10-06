package com.bookStore.admin.noticemanage.controller;

import com.bookStore.admin.noticemanage.service.INoticeManageService;
import com.bookStore.entity.Notice;
import com.bookStore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/notice/")
public class NoticeManageController {

    @Autowired
    private INoticeManageService noticeManageService;
    @RequestMapping("findAllNotices.do")
    public String findAllNotices(@RequestParam(defaultValue = "1")int pageIndex, Model model){
        //查询公告条数
        int recordCount = noticeManageService.findAllNoticeCounts();
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(4);
        pageModel.setRecordCount(recordCount);
        List<Notice> notices = noticeManageService.findAllNotices(pageModel);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("notices",notices);
        return "/admin/notices/list.jsp";
    }
    //根据id查找
    @RequestMapping("findNoticeById.do")
    public String findNoticeById(Integer id,Model model){
        System.out.println("Notice_id="+id);
        Notice notice = noticeManageService.findAdminNoticeById(id);
        model.addAttribute("n",notice);
        return  "/admin/notices/edit.jsp";
    }
    //根据id修改
    @RequestMapping("modifyNoticeById.do")
    public String modifyNoticeById(Notice notice){
        System.out.println("notice="+notice);
        noticeManageService.modifyAdminNoticeById(notice);
        return "findAllNotices.do";
    }
    //添加
    @RequestMapping("addAdminNotice.do")
    public String addAdminNotice(Notice notice){
        noticeManageService.addAdminNotice(notice);
        return "findAllNotices.do";
    }
    //删除
    @RequestMapping("deleteNoticeServlet.do")
    public String removeAdminNotice(Integer id){
        noticeManageService.removeAdminNotice(id);
        return "findAllNotices.do";
    }
}
