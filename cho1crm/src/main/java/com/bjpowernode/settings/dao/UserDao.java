package com.bjpowernode.settings.dao;

import com.bjpowernode.settings.domain.User;

import java.util.List;

public interface UserDao {
    User login(User user);

    List<User> getuList();
}
