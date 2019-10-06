package com.aynu.rent.service;


import com.aynu.entity.Houselist;
import com.aynu.rent.dao.HouselistMapper;
import com.aynu.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class HouselistServiceImpl implements HouselistService {
	
	@Resource
	private HouselistMapper houselistMapper;
	
	@Override
	public List<Houselist> selectAll(PageModel pageModel) {
		List<Houselist> houselist=houselistMapper.selectAll(pageModel);
		return houselist;
	}

	@Override
	public Houselist findhouseid(String houseid) {
		Houselist houselist=houselistMapper.findhouseid(houseid);
		return houselist;
	}

	@Override
	public void inserthouse(Houselist houselist) {
		// TODO Auto-generated method stub
		houselistMapper.inserthouse(houselist);
	}

	@Override
	public void deletehouse(int id) {
		// TODO Auto-generated method stub
		houselistMapper.deletehouse(id);
	}

	@Override
	public Houselist findhouseidupdate(Houselist houselist) {
		Houselist list=houselistMapper.findhouseidupdate(houselist);
		return list;
	}

	@Override
	public void updatehouse(Houselist houselist) {
		houselistMapper.updatehouse(houselist);
		
	}

	@Override
	public Houselist findid(int id) {
		Houselist list=houselistMapper.findid(id);
		return list;
	}

	@Override
	public void updatehousestatus(Houselist houselist) {
		// TODO Auto-generated method stub
		houselistMapper.updatehousestatus(houselist);
	}

	@Override
	public void deletehousebyhouseid(String house_id) {
		houselistMapper.deletehousebyhouseid(house_id);
		
	}

	@Override
	public int selectAllHouseCounts() {
		return houselistMapper.selectAllHouseListCounts();
	}

	@Override
	public int addHouseBeforeFindHouseId(String houseid) {
		return houselistMapper.addHouseBeforeFindHouseId(houseid);
	}


}
