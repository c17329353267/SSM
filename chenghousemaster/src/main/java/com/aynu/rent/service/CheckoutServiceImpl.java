package com.aynu.rent.service;


import com.aynu.entity.Checkout;
import com.aynu.rent.dao.CheckoutMapper;
import com.aynu.utils.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
	
	@Resource
	private CheckoutMapper checkoutMapper;

	@Override
	public void insertcheckout(Checkout checkout) {
		checkoutMapper.insertcheckout(checkout);
		
	}

	@Override
	public List<Checkout> getallcheckout(PageModel pageModel) {
		List<Checkout> checkout=checkoutMapper.getallcheckout(pageModel);
		return checkout;
	}

	@Override
	public void deletecheckout(Integer id) {
		
		checkoutMapper.deletecheckout(id);
	}

	@Override
	public int findAllCheckOutCounts() {
		return checkoutMapper.selectAllCheckOutCounts();
	}


}
