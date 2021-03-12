package com.bjpowernode.exceptions;

public class LoginActOrLoginPwdErrorException extends LoginException {
    public LoginActOrLoginPwdErrorException() {
    }

    public LoginActOrLoginPwdErrorException(String message) {
        super(message);
    }
}
