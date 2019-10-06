package com.bookStore.admin.user.dao;

import com.bookStore.entity.User;

public interface AdminUserDao {
    User selectUserByUser(User user);
}
