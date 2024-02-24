package com.seojs.salesmanagement.exception;

public class NotInStockEx extends RuntimeException{
    public NotInStockEx() {
        super();
    }

    public NotInStockEx(String message) {
        super(message);
    }

    public NotInStockEx(String message, Throwable cause) {
        super(message, cause);
    }

    public NotInStockEx(Throwable cause) {
        super(cause);
    }

    protected NotInStockEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
