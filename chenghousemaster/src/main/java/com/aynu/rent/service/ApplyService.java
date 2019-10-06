package com.aynu.rent.service;



import com.aynu.entity.Apply;
import com.aynu.entity.Houselist;
import com.aynu.utils.PageModel;

import java.util.List;


public interface ApplyService {
	
	public void insertapply(Apply apply);
	public List<Apply> findapplylist(PageModel pageModel) throws Exception;
	Apply findbyhouse_id(String house_id);
	public void deletebyhouse_id(String house_id);
	public void refuseapply(Houselist houselist);

    int findAllApplylistCounts();
}
