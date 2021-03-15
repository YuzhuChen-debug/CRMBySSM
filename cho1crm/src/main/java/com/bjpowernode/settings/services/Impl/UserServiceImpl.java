package com.bjpowernode.settings.services.Impl;

import com.bjpowernode.exceptions.*;
import com.bjpowernode.settings.dao.UserDao;
import com.bjpowernode.settings.domain.User;
import com.bjpowernode.settings.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(User user) throws LoginException {
        User u = userDao.login(user);
        if(u==null){
            throw new LoginActOrLoginPwdErrorException("账号或者用户名错误");
        }
        //判断ip地址是否受限
        if(!(u.getAllowIps().contains(user.getAllowIps()))){
            throw new IpErrorException("ip地址受限");
        }
        //判断账号是否锁定
        if("0".equals(u.getLockState())){
            throw new LocalStatusException("账号状态锁定");
        }
        //判断账号失效时间
        if((u.getExpireTime()).compareTo((user.getExpireTime()))<0){
            throw new ExpireTimeOutException("账号已失效");
        }

        return u;
    }
}
