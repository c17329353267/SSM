package com.aynu.usermanage.dao;

import com.aynu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserManageDao {

    List<User> selectUser(HashMap<Object, Object> map);

    int selectUserCount(User user);

    int insertUser(User user);

    User selectUserById(Integer id);

    int updateUser(User user);

    int deleteUserById(@Param("IDS") List<Integer> ids);
}
