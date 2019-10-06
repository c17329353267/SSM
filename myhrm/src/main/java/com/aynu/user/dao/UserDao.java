package com.aynu.user.dao;

import com.aynu.entity.User;

public interface UserDao {

    User selectUserByNameAndPassword(User user);
}
