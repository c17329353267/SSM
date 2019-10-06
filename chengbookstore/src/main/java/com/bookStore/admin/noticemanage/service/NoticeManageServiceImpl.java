package com.bookStore.admin.noticemanage.service;

import com.bookStore.admin.noticemanage.dao.NoticeManageDao;
import com.bookStore.entity.Notice;
import com.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeManageServiceImpl implements INoticeManageService {
    @Resource
    private NoticeManageDao noticeManageDao;
    @Override
    public int findAllNoticeCounts() {
        return noticeManageDao.selectAllNoCounts();
    }

    @Override
    public List<Notice> findAllNotices(PageModel pageModel) {
        return noticeManageDao.selectAllNotices(pageModel);
    }

    @Override
    public Notice findAdminNoticeById(Integer id) {
        return noticeManageDao.selectAdminNoticeById(id);
    }

    @Override
    public void modifyAdminNoticeById(Notice notice) {
        noticeManageDao.updateAdminNoticeById(notice);
    }

    @Override
    public void addAdminNotice(Notice notice) {
        noticeManageDao.insertAdminNotice(notice);
    }

    @Override
    public void removeAdminNotice(Integer id) {
        noticeManageDao.deleteAdminNoticeById(id);
    }
}
