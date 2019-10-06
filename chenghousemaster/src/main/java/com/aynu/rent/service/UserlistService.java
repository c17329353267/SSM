package com.aynu.rent.service;



import com.aynu.entity.User;
import com.aynu.entity.Userlist;
import com.aynu.utils.PageModel;

import java.util.List;

public interface UserlistService {
	//查看我的在租列表
	Userlist findhasuserlist(Integer user_id);
	int findhasuserlistCounts(Integer user_id);
	List<Userlist> getUserzuList(Integer id, PageModel pageModel);
	List<Userlist> getmycheckout(Integer id,PageModel pageModel);//获取租退人信息
	int findCheckOutCounts(Integer id);//获取当前登陆用户租退房屋条数
	List<Userlist> getmyapply(Integer id,PageModel pageModel);//获取我的(当前租户)申请看房列表
	int findUserApplyCounts(Integer id);
	List<Userlist> getmyapplyout(Integer id,PageModel pageModel);//查看用户申请退租列表
	int findUserelfApplyOut(Integer id);


	Userlist checkuserlist(String idcard);
	void insertuserlist(Userlist userlist);
	void updateuserlist(Userlist userlist);
	Userlist finduserlistupdate(Userlist userlist);
	List<Userlist> findalluserlist(PageModel pageModel);
	void deleteuserlist(Integer id);

	int findAllZuKeListCounts();

	void addNewUser(User user);

	void modifyUserPassoword(User user);
}
