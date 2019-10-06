package com.aynu.usermanage.service;

import com.aynu.entity.User;
import com.aynu.pages.PageModel;
import com.aynu.usermanage.dao.UserManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserManageDao userManageDao;

    @Override
    public int removeUserById(List<Integer> ids) {


        return userManageDao.deleteUserById(ids);
    }

    @Override
    public int findUserCount(User user) {
        return userManageDao.selectUserCount(user);
    }

    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        HashMap<Object,Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("status",user.getStatus());
        map.put("start",pageModel.getFirstLimitParam());
        map.put("pageSize",pageModel.getPageSize());
        List<User> users = userManageDao.selectUser(map);
        return users;
    }

    @Override
    public int addUser(User user) {

        return userManageDao.insertUser(user);
    }

    @Override
    public User findUserById(Integer id) {

        return userManageDao.selectUserById(id);
    }

    @Override
    public int modifyUser(User user) {

        return userManageDao.updateUser(user);
    }
}
