package com.seojs.salesmanagement.exception;

public class OrderStatusEx extends RuntimeException{
    public OrderStatusEx() {
        super();
    }

    public OrderStatusEx(String message) {
        super(message);
    }

    public OrderStatusEx(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderStatusEx(Throwable cause) {
        super(cause);
    }

    protected OrderStatusEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
