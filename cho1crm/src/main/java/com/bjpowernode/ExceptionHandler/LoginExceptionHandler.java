package com.bjpowernode.ExceptionHandler;

import com.bjpowernode.exceptions.activityException.AddActivityErrorException;
import com.bjpowernode.exceptions.activityException.SearchActivityCountErrorException;
import com.bjpowernode.exceptions.activityException.SearchActivityListException;
import com.bjpowernode.exceptions.loginException.ExpireTimeOutException;
import com.bjpowernode.exceptions.loginException.IpErrorException;
import com.bjpowernode.exceptions.loginException.LocalStatusException;
import com.bjpowernode.exceptions.loginException.LoginActOrLoginPwdErrorException;
import com.bjpowernode.exceptions.userException.UserCouldNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LoginExceptionHandler  {
    @ExceptionHandler(value = LoginActOrLoginPwdErrorException.class)
    @ResponseBody
    public Map<String,Object> LoginActOrLoginPwdErrorExceptionHandler(Exception e){
        e.printStackTrace();
        String msg = e.getMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("success",false);
        return map;
    }


    @ExceptionHandler(value = IpErrorException.class)
    @ResponseBody
    public Map<String,Object> IpErrorExceptionHandler(Exception e){
        e.printStackTrace();
        String msg = e.getMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("success",false);
        return map;
    }


    @ExceptionHandler(value = LocalStatusException.class)
    @ResponseBody
    public Map<String,Object> LocalStatusExceptionHandler(Exception e){
        e.printStackTrace();
        String msg = e.getMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("success",false);
        return map;
    }


    @ExceptionHandler(value = ExpireTimeOutException.class)
    @ResponseBody
    public Map<String,Object> ExpireTimeOutExceptionHandler(Exception e){
        e.printStackTrace();
        String msg = e.getMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("success",false);
        return map;
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String,Object> otherExceptionHandler(Exception e){
        e.printStackTrace();
        String msg = e.getMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("success",false);
        return map;
    }


    @ExceptionHandler(value = UserCouldNotFoundException.class)
    @ResponseBody
    public Map<String,Object> UserCouldNotFoundExceptionHandler(Exception e){
        e.printStackTrace();
        String msg = e.getMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("success",false);
        return map;
    }


    @ExceptionHandler(value = AddActivityErrorException.class)
    @ResponseBody
    public Map<String,Object> AddActivityErrorExceptionHandler(Exception e){
        e.printStackTrace();
        String msg = e.getMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("success",false);
        return map;
    }


    @ExceptionHandler(value = SearchActivityListException.class)
    @ResponseBody
    public Map<String,Object> SearchActivityListExceptionHandler(Exception e){
        e.printStackTrace();
        String msg = e.getMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("success",false);
        return map;
    }


    @ExceptionHandler(value = SearchActivityCountErrorException.class)
    @ResponseBody
    public Map<String,Object> SearchActivityCountErrorExceptionHandler(Exception e){
        e.printStackTrace();
        String msg = e.getMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("success",false);
        return map;
    }
}
