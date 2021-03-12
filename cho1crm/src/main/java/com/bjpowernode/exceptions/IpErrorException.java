package com.bjpowernode.exceptions;

public class IpErrorException extends LoginException {
    public IpErrorException() {
    }

    public IpErrorException(String message) {
        super(message);
    }
}
