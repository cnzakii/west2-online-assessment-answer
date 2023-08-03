package com.cnzakii.task1.exception;

/**
 * 自定义异常类-没找到动物异常，店内没有动物可买时抛出
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-03
 **/
public class AnimalNotFountException extends RuntimeException {

    public AnimalNotFountException() {
        super("动物未找到");
    }
}
