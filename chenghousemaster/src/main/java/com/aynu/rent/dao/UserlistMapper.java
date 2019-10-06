package com.aynu.rent.dao;



import com.aynu.entity.User;
import com.aynu.entity.Userlist;
import com.aynu.utils.PageModel;

import java.util.List;
import java.util.Map;

public interface UserlistMapper {
	//查询我的租赁信息
	Userlist findhasuserlist(Integer user_id);
	int selectHasUserListCounnts(Integer user_id);
	List<Userlist> getUserzuList(Map map);
	List<Userlist> getmycheckout(Map map);
	int selectCheckOutCounts(Integer id);
	List<Userlist> getmyapply(Map map);//查看当前登陆用户看房申请列表
	int selectUserApplyCounts(Integer id);
	List<Userlist> getmyapplyout(Map map);
	int selectUserselfApplyOutCounts(Integer id);


	Userlist checkuserlist(String idcard);
	void insertuserlist(Userlist userlist);
	void updateuserlist(Userlist userlist);
	Userlist finduserlistupdate(Userlist userlist);
	List<Userlist> findalluserlist(PageModel pageModel);
	void deleteuser(Integer id);
	void deleteuserlist(Integer id);

	int selectAllZuKeListCounts();

	void insertNewUser(User user);

	void updateUserPassword(User user);
}
