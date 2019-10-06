package com.aynu.rent.service;


import com.aynu.entity.Applyout;
import com.aynu.entity.Checkout;
import com.aynu.entity.Zulist;
import com.aynu.rent.dao.*;
import com.aynu.utils.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class ApplyoutServiceImpl implements ApplyoutService {
	
	@Resource
	private ApplyoutMapper applyoutMapper;

	@Resource
	private HouselistMapper houselistMapper;

	@Resource
	private HetongMapper hetongMapper;

	@Resource
	private CheckoutMapper checkoutMapper;

	@Resource
	private ZulistMapper zulistMapper;


	@Override
	public int findAllUserApplyOutCounts() {
		return applyoutMapper.selectAllUserApplyOutCounts();
	}
	@Override
	public List<Applyout> findallapplyout(PageModel pageModel) {
		List<Applyout> list=applyoutMapper.findallapplyout(pageModel);
		return list;
	}


	public void insertapplyout(Zulist zulist) {
		Applyout applyout=new Applyout();
		applyout.setHouse_id(zulist.getHouse_id());
		applyout.setAddress(zulist.getAddress());
		applyout.setStatus("申请中");
		applyout.setUserlist_id(zulist.getUserlist_id());
		applyoutMapper.insertapplyout(applyout);
		
	}

	@Override
	public void updateapplyout(Applyout applyout) {
		applyoutMapper.updateapplyout(applyout);
	}
	@Override
	public void agreeapplyout(Integer id) {
		Applyout applyout=applyoutMapper.findbyid(id);
		houselistMapper.deletehousebyhouseid(applyout.getHouse_id());
		hetongMapper.deletehetong(applyout.getHouse_id());
		Checkout checkout=new Checkout();
		checkout.setHouse_id(applyout.getHouse_id());
		checkout.setAddress(applyout.getAddress());
		checkout.setStatus("已退租");
		checkout.setUserlist_id(applyout.getUserlist_id());
		checkoutMapper.insertcheckout(checkout);
		applyout.setStatus("已同意");
		applyoutMapper.updateapplyoutbyhouse(applyout);
		zulistMapper.deletezulist(applyout.getHouse_id());
	}
	@Override
	public void deleteapplyout(Integer id) {
		applyoutMapper.deleteapplyout(id);
	}

}
