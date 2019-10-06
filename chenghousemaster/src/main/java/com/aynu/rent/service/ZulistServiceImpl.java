package com.aynu.rent.service;


import com.aynu.entity.Zulist;
import com.aynu.rent.dao.ZulistMapper;
import com.aynu.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ZulistServiceImpl implements ZulistService {
	
	@Resource
	private ZulistMapper zulistMapper;
	//添加
	@Override
	public void insertzulist(Zulist zulist) {
		zulistMapper.insertzulist(zulist);
		
	}

	@Override
	public List<Zulist> findzuuserlist(PageModel pageModel) throws Exception {
		List<Zulist> zulist=zulistMapper.findzuuserlist(pageModel);
		
		return zulist;
	}

	@Override
	public Zulist findzulist(String house_id) {
		Zulist zulist=zulistMapper.findzulist(house_id);
		return zulist;
	}

	@Override
	public int findZulistCounts(Integer id) {
		return zulistMapper.selectZulistCounts(id);
	}

	@Override
	public void deletezulist(String house_id) {
		zulistMapper.deletezulist(house_id);
		
	}

	@Override
	public List<Zulist> findzulistbyuid(Integer userlist_id, PageModel pageModel) {
		//List<Zulist> zulist=zulistMapper.findzulistbyuid(userlist_id);
		Map map = new HashMap();
		map.put("userlist_id",userlist_id);
		map.put("pageModel",pageModel);
		return zulistMapper.findzulistbyuid(map);
	}

	@Override
	public int adminFindAllZulistCounts() {
		return zulistMapper.adminSelectAllZulistCounts();
	}

}
