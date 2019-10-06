package com.aynu.noticemanage.service;

import com.aynu.entity.Notice;
import com.aynu.noticemanage.dao.NoticeDao;
import com.aynu.pages.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;
    @Override
    public int findNoticeCounts(Notice notice) {
        return noticeDao.selectNoticeCounts(notice);
    }

    @Override
    public List<Notice> findAllNotices(Notice notice, PageModel pageModel) {
        Map map = new HashMap();
        map.put("notice",notice);
        map.put("pageModel",pageModel);
        return noticeDao.selectAllNotices(map);
    }

    @Override
    public Notice findNoticeById(Integer id) {
        return noticeDao.selectNoticeById(id);
    }

    @Override
    public int modifyNotice(Notice notice) {
        return noticeDao.updateNotice(notice);
    }

    @Override
    public int addNotice(Notice notice) {
        notice.setCreate_date(new Date());

        return noticeDao.insertNotice(notice);
    }

    @Override
    public int removeNotices(Integer[] ids) {
        return noticeDao.deleteNotices(ids);
    }


}
