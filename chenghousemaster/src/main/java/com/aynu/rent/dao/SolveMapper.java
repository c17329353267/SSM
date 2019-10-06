package com.aynu.rent.dao;



import com.aynu.entity.QueryVo;
import com.aynu.entity.Solve;

import java.util.List;
import java.util.Map;

public interface SolveMapper {
	 List<Solve> selectall(Map map);
	 Integer selectcount(QueryVo vo);
	int selectMyHasSolveWrongCounts(QueryVo vo);


	 void deletesolve(Integer id);
	 void insertsolve(Solve solve);
}
