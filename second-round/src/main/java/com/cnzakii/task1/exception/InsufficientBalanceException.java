package com.cnzakii.task1.exception;

/**
 * 自定义异常类-余额不足异常时抛出
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-01
 **/
public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {
        super("余额不足");
    }


}
