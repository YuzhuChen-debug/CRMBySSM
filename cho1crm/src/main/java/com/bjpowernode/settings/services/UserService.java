package com.bjpowernode.settings.services;


import com.bjpowernode.exceptions.LocalStatusException;
import com.bjpowernode.exceptions.LoginActOrLoginPwdErrorException;
import com.bjpowernode.exceptions.LoginException;
import com.bjpowernode.settings.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User login(User user) throws LoginActOrLoginPwdErrorException, LocalStatusException, LoginException;
}
