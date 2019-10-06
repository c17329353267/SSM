package com.aynu.rent.service;


import com.aynu.entity.Paid;
import com.aynu.entity.QueryVo;
import com.aynu.entity.Topaid;
import com.aynu.rent.dao.PaidMapper;
import com.aynu.rent.dao.TopaidMapper;
import com.aynu.utils.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class TopaidServiceImpl implements TopaidService {
	@Resource
	private TopaidMapper topaidMapper;
	@Resource
	private PaidMapper paidMapper;

	@Override
	public void inserttopaid(Topaid topaid) {
		topaid.setStatus("租金未缴");
		topaidMapper.inserttopaid(topaid);
	}
	@Override
	public List<Topaid> findtopaid(QueryVo vo, PageModel pageModel) {
		//List<Topaid> list=topaidMapper.findtopaid(vo);
		Map map = new HashMap();
		map.put("vo",vo);
		map.put("pageModel",pageModel);
		return topaidMapper.findtopaid(map);
	}
	@Override
	public Topaid findbyid(Integer id) {
		Topaid topaid=topaidMapper.findbyid(id);
		return topaid;
	}
	@Override
	public void gotopay(Integer id, Paid paid) {
		paidMapper.insertpaid(paid);
		topaidMapper.deletetopaid(id);
		
	}
	//查看登陆的当前用户未交租金的数据条数
	@Override
	public int findTotalPaidListCounts(Integer id) {
		return topaidMapper.selectTotalPaidCounts(id);
	}

	@Override
	public int adminFindAllHasNotPaidCounts() {
		return topaidMapper.adminSelectAllHasNotPaidCounts();
	}

	@Override
	public List<Topaid> adminFindAllUsertopaid(QueryVo vo, PageModel pageModel) {
		Map map = new HashMap();
		map.put("vo",vo);
		map.put("pageModel",pageModel);
		return topaidMapper.adminSelectAllUsertoPaid(map);
	}

}
