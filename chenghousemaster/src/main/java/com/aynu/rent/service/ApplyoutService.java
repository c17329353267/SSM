package com.aynu.rent.service;


import com.aynu.entity.Applyout;
import com.aynu.entity.Zulist;
import com.aynu.utils.PageModel;

import java.util.List;


public interface ApplyoutService {
	void insertapplyout(Zulist zulist);
	List<Applyout> findallapplyout(PageModel pageModel);
	void updateapplyout(Applyout applyout);
	void agreeapplyout(Integer id);
	void deleteapplyout(Integer id);

	int findAllUserApplyOutCounts();
}
