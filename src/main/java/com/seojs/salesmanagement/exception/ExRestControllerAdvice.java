package com.seojs.salesmanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExRestControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerDupliacateEx.class)
    public String customerDupliacateEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerNotFoundEx.class)
    public String customerNotFoundEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryNotFoundEx.class)
    public String categoryNotFoundEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotInStockEx.class)
    public String notInStockEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderNotFoundEx.class)
    public String orderNotFoundEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductDuplicateEx.class)
    public String productDuplicateEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundEx.class)
    public String productNotFoundEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public String runtime(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return e.getMessage();
    }
}
