package com.aynu.user.service;

import com.aynu.entity.User;
import com.aynu.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userdao;
    @Override
    public User findPersonByNameAndpassword(User user) {
        return userdao.selectUserByNameAndPassword(user);
    }
}
