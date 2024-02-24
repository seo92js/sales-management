package com.seojs.salesmanagement.exception;

public class ProductDuplicateEx extends RuntimeException{
    public ProductDuplicateEx() {
        super();
    }

    public ProductDuplicateEx(String message) {
        super(message);
    }

    public ProductDuplicateEx(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductDuplicateEx(Throwable cause) {
        super(cause);
    }

    protected ProductDuplicateEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
