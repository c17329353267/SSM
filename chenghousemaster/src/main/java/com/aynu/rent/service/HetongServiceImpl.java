package com.aynu.rent.service;


import com.aynu.entity.Hetong;
import com.aynu.rent.dao.HetongMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class HetongServiceImpl implements HetongService {

	@Resource
	private HetongMapper hetongMapper;
	
	@Override
	public void inserthetong(Hetong hetong) {
		hetongMapper.inserthetong(hetong);
		
	}

	@Override
	public Hetong findhetong(String house_id) {
		Hetong hetong=hetongMapper.findhetong(house_id);
		return hetong;
	}

	@Override
	public void updatehetong(Hetong hetong) {
		
		hetongMapper.updatehetong(hetong);
	}

	@Override
	public void deletehetong(String house_id) {
		// TODO Auto-generated method stub
		hetongMapper.deletehetong(house_id);
		
	}
	
}
