package com.aynu.rent.dao;



import com.aynu.entity.Checkout;
import com.aynu.utils.PageModel;

import java.util.List;

public interface CheckoutMapper {
	void insertcheckout(Checkout checkout);
	List<Checkout> getallcheckout(PageModel pageModel);
	void deletecheckout(Integer id);

    int selectAllCheckOutCounts();
}
