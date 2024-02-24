package com.seojs.salesmanagement.exception;

public class OrderNotFoundEx extends RuntimeException{
    public OrderNotFoundEx() {
        super();
    }

    public OrderNotFoundEx(String message) {
        super(message);
    }

    public OrderNotFoundEx(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundEx(Throwable cause) {
        super(cause);
    }

    protected OrderNotFoundEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
