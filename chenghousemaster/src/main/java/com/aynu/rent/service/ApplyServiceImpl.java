package com.aynu.rent.service;


import com.aynu.entity.Apply;
import com.aynu.entity.Houselist;
import com.aynu.rent.dao.ApplyMapper;
import com.aynu.rent.dao.HouselistMapper;
import com.aynu.utils.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ApplyServiceImpl implements ApplyService {
	
	@Resource
	private ApplyMapper applyMapper;
	@Resource
	private HouselistMapper houselistMapper;
	
	@Override
	public void insertapply(Apply apply) {
		applyMapper.insertapply(apply);
		
	}

	@Override
	public List<Apply> findapplylist(PageModel pageModel) throws Exception {
		List<Apply> apply=applyMapper.findapplylist(pageModel);
		return  apply;
	}

	@Override
	public Apply findbyhouse_id(String house_id) {
		Apply apply=applyMapper.findbyhouse_id(house_id);
		return apply;
	}

	@Override
	public void deletebyhouse_id(String house_id) {
		applyMapper.deletebyhouse_id(house_id);
		
	}

	@Override
	public void refuseapply(Houselist houselist) {
		houselistMapper.updatestatus(houselist);
		applyMapper.deletebyhouse_id(houselist.getHouseid());
	}

	@Override
	public int findAllApplylistCounts() {
		return applyMapper.selectAllApplylistCounts();
	}


}
