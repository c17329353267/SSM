package com.aynu.rent.dao;

import com.aynu.entity.QueryVo;
import com.aynu.entity.Topaid;
import com.aynu.utils.PageModel;

import java.util.List;
import java.util.Map;

public interface TopaidMapper {
    void inserttopaid(Topaid topaid);
    List<Topaid> findtopaid(Map map);
    Topaid findbyid(Integer id);
    void deletetopaid(Integer id);

    int selectTotalPaidCounts(Integer id);

    int adminSelectAllHasNotPaidCounts();

    List<Topaid> adminSelectAllUsertoPaid(Map map);
}
