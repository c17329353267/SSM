package com.aynu.rent.service;


import com.aynu.entity.Paid;
import com.aynu.entity.QueryVo;
import com.aynu.entity.Zulist;
import com.aynu.utils.PageModel;

import java.util.List;


public interface PaidService {
	 List<Paid> selectall(QueryVo vo, PageModel pageModel);
	 Double selectsum(QueryVo vo);
	 void deletepaid(Integer id);
	 List<Zulist> findzuuserlist(PageModel pageModel) throws Exception;
	 Zulist findzukezulist(Integer id);

	int findZuKeHasPaidCounts(Integer id,QueryVo vo);

	int findZuUserListCounts();

	int adminFindAllUserHasPaidCounts(QueryVo vo);
}
