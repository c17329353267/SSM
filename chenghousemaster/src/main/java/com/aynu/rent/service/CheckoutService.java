package com.aynu.rent.service;



import com.aynu.entity.Checkout;
import com.aynu.utils.PageModel;

import java.util.List;

public interface CheckoutService {
    void insertcheckout(Checkout checkout);
    List<Checkout> getallcheckout(PageModel pageModel);
    void deletecheckout(Integer id);

    int findAllCheckOutCounts();
}
