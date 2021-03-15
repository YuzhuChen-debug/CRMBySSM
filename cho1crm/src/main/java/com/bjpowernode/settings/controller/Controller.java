package com.bjpowernode.settings.controller;

import com.bjpowernode.exceptions.LoginException;
import com.bjpowernode.settings.domain.User;
import com.bjpowernode.settings.services.UserService;
import com.bjpowernode.utils.DateTimeUtil;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/settings/user")
@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public boolean login(String loginAct, String loginPwd, HttpServletRequest request) throws LoginException {
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        User user = new User();
        String loginPwdMD5 = MD5Util.getMD5(loginPwd);
        user.setLoginAct(loginAct);
        user.setLoginPwd(loginPwdMD5);
        user.setExpireTime(DateTimeUtil.getSysTime());
        user.setAllowIps(ip);
        boolean flag = userService.login(user);
        //System.out.println(1);
            request.getSession().setAttribute("user",user);
            return flag;
    }
}
