package com.bookStore.admin.noticemanage.dao;

import com.bookStore.entity.Notice;
import com.bookStore.utils.PageModel;

import java.util.List;

public interface NoticeManageDao {
    int selectAllNoCounts();

    List<Notice> selectAllNotices(PageModel pageModel);

    Notice selectAdminNoticeById(Integer id);

    void updateAdminNoticeById(Notice notice);

    void insertAdminNotice(Notice notice);

    void deleteAdminNoticeById(Integer id);
}
