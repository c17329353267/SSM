package com.aynu.user.service;


import com.aynu.entity.User;
import com.aynu.entity.UserExample;
import com.aynu.user.dao.UserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;

	@Override
	public List<User> userList() throws Exception {
		// TODO Auto-generated method stub
		UserExample example=new UserExample();
		List<User> list=userMapper.selectByExample(example);
		System.out.println("123"+list);
		return list;
	}

	@Override
	public User login(User user) throws Exception {
		User user1=userMapper.selectByUser(user);
		return user1;
	}

	
}
