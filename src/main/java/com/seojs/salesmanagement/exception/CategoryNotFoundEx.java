package com.seojs.salesmanagement.exception;

public class CategoryNotFoundEx extends RuntimeException{
    public CategoryNotFoundEx() {
        super();
    }

    public CategoryNotFoundEx(String message) {
        super(message);
    }

    public CategoryNotFoundEx(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundEx(Throwable cause) {
        super(cause);
    }

    protected CategoryNotFoundEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
