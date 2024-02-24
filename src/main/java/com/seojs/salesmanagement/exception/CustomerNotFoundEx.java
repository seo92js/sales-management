package com.seojs.salesmanagement.exception;

public class CustomerNotFoundEx extends RuntimeException{
    public CustomerNotFoundEx() {
        super();
    }

    public CustomerNotFoundEx(String message) {
        super(message);
    }

    public CustomerNotFoundEx(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotFoundEx(Throwable cause) {
        super(cause);
    }

    protected CustomerNotFoundEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
