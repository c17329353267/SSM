package com.aynu.noticemanage.dao;

import com.aynu.entity.Notice;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface NoticeDao {

    int selectNoticeCounts(Notice notice);

    List<Notice> selectAllNotices(Map map);

    Notice selectNoticeById(Integer id);

    int updateNotice(Notice notice);

    int insertNotice(Notice notice);

    int deleteNotices(@Param("IDS") Integer[] ids);
}
