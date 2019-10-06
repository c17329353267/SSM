package com.aynu.rent.dao;




import com.aynu.entity.Apply;
import com.aynu.entity.Applyout;
import com.aynu.utils.PageModel;

import java.util.List;

public interface ApplyMapper {
	void insertapply(Apply apply);
	public List<Apply> findapplylist(PageModel pageModel) throws Exception;
	Apply findbyhouse_id(String house_id);
	public void deletebyhouse_id(String house_id);
	public void updateapplyout(Applyout applyout);

    int selectAllApplylistCounts();
}
