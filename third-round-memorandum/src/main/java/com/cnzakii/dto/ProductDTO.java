package com.cnzakii.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 商品 数据传输对象
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
@Getter
@Setter
public class ProductDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5492869549586887851L;

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

    /**
     * 商品库存
     */
    private int quantity;

    @Override
    public String toString() {
        return id + ". 商品名：" + name +", 商品价格：" + price + ", 商品库存：" + quantity;
    }
}
