package com.aynu.rent.service;

import com.aynu.entity.Paid;
import com.aynu.entity.QueryVo;
import com.aynu.entity.Zulist;
import com.aynu.rent.dao.PaidMapper;
import com.aynu.rent.dao.ZulistMapper;
import com.aynu.utils.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PaidServiceImpl implements PaidService {

	@Resource
	private PaidMapper paidMapper;
	@Resource
	private ZulistMapper zulistMapper;
	
	@Override
	public List<Paid> selectall(QueryVo vo, PageModel pageModel) {
		//List<Paid> list=paidMapper.selectall(vo);
		Map map = new HashMap();
		map.put("vo",vo);
		map.put("pageModel",pageModel);
		return paidMapper.selectall(map);
	}

	@Override
	public Double selectsum(QueryVo vo) {
		Double sum=paidMapper.selectsum(vo);
		return sum;
	}

	@Override
	public void deletepaid(Integer id) {
		paidMapper.deletepaid(id);
		
	}

	@Override
	public List<Zulist> findzuuserlist(PageModel pageModel) throws Exception{
		List<Zulist> list=zulistMapper.findzuuserlist(pageModel);
		return list;
	}

	@Override
	public Zulist findzukezulist(Integer id) {
		Zulist zulist=zulistMapper.findzukezulist(id);
		return zulist;
	}

	@Override
	public int findZuKeHasPaidCounts(Integer id,QueryVo vo) {
		Map map = new HashMap();
		map.put("id",id);
		map.put("vo",vo);
		return paidMapper.selectUserHasPaidCounts(map);
	}

	@Override
	public int findZuUserListCounts() {
		return zulistMapper.adminSelectAllZulistCounts();
	}

	@Override
	public int adminFindAllUserHasPaidCounts(QueryVo vo) {
		return paidMapper.adminSelectAllUserHasPaidCounts(vo);
	}


}
