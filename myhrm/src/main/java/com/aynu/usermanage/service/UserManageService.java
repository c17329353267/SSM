package com.aynu.usermanage.service;

import com.aynu.entity.User;
import com.aynu.pages.PageModel;

import java.util.List;

public interface UserManageService {

    int removeUserById(List<Integer> ids);

    int findUserCount(User user);

    List<User> findUser(User user, PageModel pageModel);

    int addUser(User user);

    User findUserById(Integer id);

    int modifyUser(User user);
}
