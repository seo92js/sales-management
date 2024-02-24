package com.seojs.salesmanagement.exception;

public class ProductNotFoundEx extends RuntimeException{
    public ProductNotFoundEx() {
        super();
    }

    public ProductNotFoundEx(String message) {
        super(message);
    }

    public ProductNotFoundEx(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundEx(Throwable cause) {
        super(cause);
    }

    protected ProductNotFoundEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
