package com.aynu.rent.dao;


import com.aynu.entity.Paid;
import com.aynu.entity.QueryVo;

import java.util.List;
import java.util.Map;


public interface PaidMapper {
     List<Paid> selectall(Map map);
     Double selectsum(QueryVo vo);
     void deletepaid(Integer id);
     void insertpaid(Paid paid);

    int selectUserHasPaidCounts(Map map);

    int adminSelectAllUserHasPaidCounts(QueryVo vo);
}
