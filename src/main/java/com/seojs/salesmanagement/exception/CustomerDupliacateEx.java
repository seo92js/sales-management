package com.seojs.salesmanagement.exception;

public class CustomerDupliacateEx extends RuntimeException{
    public CustomerDupliacateEx() {
        super();
    }

    public CustomerDupliacateEx(String message) {
        super(message);
    }

    public CustomerDupliacateEx(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerDupliacateEx(Throwable cause) {
        super(cause);
    }

    protected CustomerDupliacateEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
