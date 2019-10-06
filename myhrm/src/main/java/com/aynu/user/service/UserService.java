package com.aynu.user.service;

import com.aynu.entity.User;

public interface UserService {

    User findPersonByNameAndpassword(User user);
}
