package com.bookStore.admin.user.service;

import com.bookStore.admin.user.dao.AdminUserDao;
import com.bookStore.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements  AdminUserService{
    @Resource
    private AdminUserDao adminUserDao;
    @Override
    public User findUser(User user) {
        return adminUserDao.selectUserByUser(user);
    }
}
