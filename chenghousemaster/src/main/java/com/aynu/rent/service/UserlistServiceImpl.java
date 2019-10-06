package com.aynu.rent.service;


import com.aynu.entity.User;
import com.aynu.entity.Userlist;
import com.aynu.rent.dao.UserlistMapper;
import com.aynu.utils.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class UserlistServiceImpl implements UserlistService {

	@Resource
	private UserlistMapper userlistMapper;
	//查看我的在租列表
	@Override
	public Userlist findhasuserlist(Integer user_id) {
		Userlist userlist=userlistMapper.findhasuserlist(user_id);
		return userlist;
	}
	@Override
	public int findhasuserlistCounts(Integer user_id) {

		return userlistMapper.selectHasUserListCounnts(user_id);
	}
	//当前用户租赁信息
	@Override
	public List<Userlist> getUserzuList(Integer id, PageModel pageModel) {
		/*List<Userlist> userlist=userlistMapper.getUserzuList(id);
		for(Userlist list:userlist){
			System.out.println(list);
		}*/
		Map map = new HashMap();
		map.put("id",id);
		map.put("pageModel",pageModel);
		return userlistMapper.getUserzuList(map);
	}
	//当前用户租退信息
	@Override
	public List<Userlist> getmycheckout(Integer id,PageModel pageModel) {
		Map map = new HashMap();
		map.put("id",id);
		map.put("pageModel",pageModel);
		List<Userlist> list=userlistMapper.getmycheckout(map);
		return list;
	}
	//获取当前登陆用户租退信息条数
	@Override
	public int findCheckOutCounts(Integer id) {
		return userlistMapper.selectCheckOutCounts(id);
	}
	//查看当前登陆用户看房申请列表
	@Override
	public List<Userlist> getmyapply(Integer id,PageModel pageModel) {
		//List<Userlist> list=userlistMapper.getmyapply(id);
		Map map = new HashMap();
		map.put("id",id);
		map.put("pageModel",pageModel);
		return userlistMapper.getmyapply(map);
	}
	//查看登陆的当前用户申请的看房数据条数
	@Override
	public int findUserApplyCounts(Integer id) {
		return userlistMapper.selectUserApplyCounts(id);
	}
	//查看当前登陆用户的申请退租列表信息
	@Override
	public List<Userlist> getmyapplyout(Integer id,PageModel pageModel) {
		//List<Userlist> list=userlistMapper.getmyapplyout(id);
		Map map = new HashMap();
		map.put("id",id);
		map.put("pageModel",pageModel);
		return userlistMapper.getmyapplyout(map);
	}
	//查看登陆用户申请退租数据条数
	@Override
	public int findUserelfApplyOut(Integer id) {
		return userlistMapper.selectUserselfApplyOutCounts(id);
	}


	@Override
	public Userlist checkuserlist(String idcard) {
		Userlist userlist=userlistMapper.checkuserlist(idcard);
		return userlist;
	}
	@Override
	public void insertuserlist(Userlist userlist) {
		userlistMapper.insertuserlist(userlist);
		
	}
	@Override
	public void updateuserlist(Userlist userlist) {
		userlistMapper.updateuserlist(userlist);
		
	}
	@Override
	public Userlist finduserlistupdate(Userlist userlist) {
		Userlist list=userlistMapper.finduserlistupdate(userlist);
		return list;
	}
	@Override
	public List<Userlist> findalluserlist(PageModel pageModel) {
		List<Userlist> list=userlistMapper.findalluserlist(pageModel);
		return list;
	}
	@Override
	public void deleteuserlist(Integer id) {
		userlistMapper.deleteuserlist(id);
		userlistMapper.deleteuser(id);
	}

	@Override
	public int findAllZuKeListCounts() {
		return userlistMapper.selectAllZuKeListCounts();
	}

	@Override
	public void addNewUser(User user) {
		userlistMapper.insertNewUser(user);
	}

	@Override
	public void modifyUserPassoword(User user) {
		userlistMapper.updateUserPassword(user);
	}
}
