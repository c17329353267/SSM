package com.aynu.rent.service;



import com.aynu.entity.QueryVo;
import com.aynu.entity.Solve;
import com.aynu.entity.Wrong;
import com.aynu.utils.PageModel;

import java.util.List;


public interface SolveService {
	 List<Solve> selectall(QueryVo vo,PageModel pageModel);
	 Integer selectcount(QueryVo vo);
	 void deletesolve(Integer id);
	 void insertwrong(Wrong wrong);
	 List<Wrong> findwrong(QueryVo vo, PageModel pageModel);
	 int findNotSolveWrongCounts(Integer id);
	 int findMyHasSolveWrongCounts(QueryVo vo);


	 Wrong findbyid(Integer id);
	 void gotosolve(Integer id, Solve solve);

	int findNotSolveAllCounts();
}
