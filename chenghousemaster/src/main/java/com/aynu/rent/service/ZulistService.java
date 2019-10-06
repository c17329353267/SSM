package com.aynu.rent.service;



import com.aynu.entity.Zulist;
import com.aynu.utils.PageModel;

import java.util.List;


public interface ZulistService {
	void insertzulist(Zulist zulist);
	Zulist findzulist(String house_id);
	int findZulistCounts(Integer id);

	List<Zulist> findzuuserlist(PageModel pageModel) throws Exception;
	void deletezulist(String house_id);
	List<Zulist> findzulistbyuid(Integer userlist_id, PageModel pageModel);

    int adminFindAllZulistCounts();
}
