package com.aynu.rent.service;


import com.aynu.entity.Houselist;
import com.aynu.utils.PageModel;

import java.util.List;


public interface HouselistService {
	List<Houselist> selectAll(PageModel pageModel);
	Houselist findhouseid(String houseid);
	void inserthouse(Houselist houselist);
	void deletehouse(int id);
	Houselist findid(int id);
	Houselist findhouseidupdate(Houselist houselist);
	void updatehouse(Houselist houselist);
	void updatehousestatus(Houselist houselist);
	void deletehousebyhouseid(String house_id);

	int selectAllHouseCounts();

    int addHouseBeforeFindHouseId(String houseid);
}
