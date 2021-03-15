package com.bjpowernode.settings.services;


import com.bjpowernode.exceptions.loginException.LocalStatusException;
import com.bjpowernode.exceptions.loginException.LoginActOrLoginPwdErrorException;
import com.bjpowernode.exceptions.loginException.LoginException;
import com.bjpowernode.exceptions.userException.UserCouldNotFoundException;
import com.bjpowernode.settings.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User login(User user) throws LoginActOrLoginPwdErrorException, LocalStatusException, LoginException;

    List<User> getuList() throws UserCouldNotFoundException;
}
