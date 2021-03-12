package com.bjpowernode.ExceptionHandler;

import com.bjpowernode.exceptions.IpErrorException;
import org.springframework.jdbc.LobRetrievalFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class LoginExceptionHandler  {
    @ExceptionHandler(value = LobRetrievalFailureException.class)
    public ModelAndView  LoginActOrLoginPwdErrorExceptionHandler(Exception e){
        ModelAndView mv = new ModelAndView();
        mv.addObject("e",e);
        mv.setViewName("forword:/login.jsp");
        return mv;
    }

    @ExceptionHandler(value = IpErrorException.class)
    public ModelAndView  IpErrorException(Exception e){
        ModelAndView mv = new ModelAndView();
        mv.addObject("e",e);
        mv.setViewName("forword:/login.jsp");
        return mv;
    }
}
