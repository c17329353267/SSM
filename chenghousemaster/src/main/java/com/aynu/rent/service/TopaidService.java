package com.aynu.rent.service;



import com.aynu.entity.Paid;
import com.aynu.entity.QueryVo;
import com.aynu.entity.Topaid;
import com.aynu.utils.PageModel;

import java.util.List;


public interface TopaidService {
	 void inserttopaid(Topaid topaid);
	 List<Topaid> findtopaid(QueryVo vo, PageModel pageModel);
	 Topaid findbyid(Integer id);
	 void gotopay(Integer id, Paid paid);

	int findTotalPaidListCounts(Integer id);

    int adminFindAllHasNotPaidCounts();

	List<Topaid> adminFindAllUsertopaid(QueryVo vo, PageModel pageModel);
}
