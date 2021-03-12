package com.bjpowernode.exceptions;

public class ExpireTimeOutException extends LoginException {
    public ExpireTimeOutException() {
    }

    public ExpireTimeOutException(String message) {
        super(message);
    }
}
