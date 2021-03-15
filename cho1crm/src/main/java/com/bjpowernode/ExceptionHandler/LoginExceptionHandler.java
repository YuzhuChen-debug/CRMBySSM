package com.bjpowernode.ExceptionHandler;

import com.bjpowernode.exceptions.LoginActOrLoginPwdErrorException;
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


}
