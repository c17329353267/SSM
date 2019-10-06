package com.aynu.noticemanage.service;

import com.aynu.entity.Notice;
import com.aynu.pages.PageModel;

import java.util.List;

public interface NoticeService {
    int findNoticeCounts(Notice notice);

    List<Notice> findAllNotices(Notice notice, PageModel pageModel);

    Notice findNoticeById(Integer id);

    int modifyNotice(Notice notice);

    int addNotice(Notice notice);

    int removeNotices(Integer[] ids);
}
