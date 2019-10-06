package com.bookStore.admin.noticemanage.service;

import com.bookStore.entity.Notice;
import com.bookStore.utils.PageModel;

import java.util.List;

public interface INoticeManageService {
    int findAllNoticeCounts();

    List<Notice> findAllNotices(PageModel pageModel);

    Notice findAdminNoticeById(Integer id);

    void modifyAdminNoticeById(Notice notice);

    void addAdminNotice(Notice notice);

    void removeAdminNotice(Integer id);
}
