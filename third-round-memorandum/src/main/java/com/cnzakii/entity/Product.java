package com.cnzakii.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 商品 实体类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
@Data
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = -2697422634915995127L;

    /**
     * Id
     */
    private int id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品价格
     */
    private double price;
}
