package com.aynu.rent.dao;

import com.aynu.entity.Houselist;
import com.aynu.entity.QueryVo;
import com.aynu.utils.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface HouselistMapper {
    List<Houselist> selectAll(PageModel pageModel);
    Integer findhouselistByVoCount(QueryVo vo);
    Houselist findhouseid(String houseid);
    void inserthouse(Houselist houselist);
    void deletehouse(int id);
    Houselist findid(int id);
    Houselist findhouseidupdate(Houselist houselist);
    void updatehouse(Houselist houselist);
    void updatehousestatus(Houselist houselist);
    void deletehousebyhouseid(String house_id);
    void updatestatus(Houselist houselist);

    int selectAllHouseListCounts();

    int addHouseBeforeFindHouseId(@Param("houseid") String houseid);
}
