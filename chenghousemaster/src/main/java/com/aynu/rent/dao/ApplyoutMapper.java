package com.aynu.rent.dao;



import com.aynu.entity.Applyout;
import com.aynu.utils.PageModel;

import java.util.List;

public interface ApplyoutMapper {
	void insertapplyout(Applyout applyout);
	List<Applyout> findallapplyout(PageModel pageModel);
	int selectAllUserApplyOutCounts();


	void updateapplyout(Applyout applyout);
	void updateapplyoutbyhouse(Applyout applyout);
	Applyout findbyid(Integer id);
	void deleteapplyout(Integer id);


}
