package com.aynu.rent.service;


import com.aynu.entity.QueryVo;
import com.aynu.entity.Solve;
import com.aynu.entity.Wrong;
import com.aynu.rent.dao.SolveMapper;
import com.aynu.rent.dao.WrongMapper;
import com.aynu.utils.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SolveServiceImpl implements SolveService {
	@Resource
	private SolveMapper solveMapper;
	@Resource
	private WrongMapper wrongMapper;
	@Override
	public List<Solve> selectall(QueryVo vo,PageModel pageModel) {
		//List<Solve> list=solveMapper.selectall(vo);
		Map map = new HashMap();
		map.put("vo",vo);
		map.put("pageModel",pageModel);
		return solveMapper.selectall(map);
	}
	@Override
	public List<Wrong> findwrong(QueryVo vo, PageModel pageModel) {
		//List<Wrong> list=wrongMapper.findwrong(vo);
		Map map = new HashMap();
		map.put("vo",vo);
		map.put("pageModel",pageModel);
		return wrongMapper.findwrong(map);
	}

	@Override
	public int findNotSolveWrongCounts(Integer id) {
		return wrongMapper.selectNotSolveWrongCounts(id);
	}
	@Override
	public int findMyHasSolveWrongCounts(QueryVo vo) {

	    return solveMapper.selectMyHasSolveWrongCounts(vo);
	}

	@Override
	public Integer selectcount(QueryVo vo) {
		Integer count=solveMapper.selectcount(vo);
		return count;
	}
	@Override
	public void deletesolve(Integer id) {
		solveMapper.deletesolve(id);
		
	}
	@Override
	public Wrong findbyid(Integer id) {
		Wrong wrong=wrongMapper.findbyid(id);
		return wrong;
	}
	@Override
	public void insertwrong(Wrong wrong) {
		wrong.setStatus("待处理");
		wrongMapper.insertwrong(wrong);
		
	}
	@Override
	public void gotosolve(Integer id, Solve solve) {
		solveMapper.insertsolve(solve);
		wrongMapper.deletewrong(id);
		
	}

	@Override
	public int findNotSolveAllCounts() {
		return wrongMapper.selectNotSolveAllCounts();
	}

}
