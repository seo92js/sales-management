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
    public ErrorResult customerDupliacateEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("CUSTOMER_DUPLICATE_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerNotFoundEx.class)
    public ErrorResult customerNotFoundEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("CUSTOMER_NOT_FOUND_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryNotFoundEx.class)
    public ErrorResult categoryNotFoundEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("CATEGORY_NOT_FOUND_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotInStockEx.class)
    public ErrorResult notInStockEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("NOT_IN_STOCK_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderNotFoundEx.class)
    public ErrorResult orderNotFoundEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("ORDER_NOT_FOUND_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductDuplicateEx.class)
    public ErrorResult productDuplicateEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("PRODUCT_DUPLICATE_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundEx.class)
    public ErrorResult productNotFoundEx(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("PRODUCT_NOT_FOUND_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResult runtime(RuntimeException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("RUNTIME_EX", e.getMessage());
    }
}
