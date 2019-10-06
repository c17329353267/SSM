package com.aynu.rent.dao;



import com.aynu.entity.Zulist;
import com.aynu.utils.PageModel;

import java.util.List;
import java.util.Map;

public interface ZulistMapper {
	List<Zulist> findzulistbyuid(Map map);
	int selectZulistCounts(Integer id);


	 void insertzulist(Zulist zulist);
	 List<Zulist> findzuuserlist(PageModel pageModel) throws Exception;
	 Zulist findzulist(String house_id);
	 void deletezulist(String house_id);
	 Zulist findzukezulist(Integer id);

    int adminSelectAllZulistCounts();
}
