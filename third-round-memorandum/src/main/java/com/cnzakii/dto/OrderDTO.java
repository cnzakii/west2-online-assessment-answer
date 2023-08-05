package com.cnzakii.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单 数据传输对象
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
@Getter
@Setter
public class OrderDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6776406000256121437L;

    /**
     * 订单id
     */
    private int id;

    /**
     * 商品 id
     */
    private int productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品价格
     */
    private double productPrice;

    /**
     * 购买数量
     */
    private int quantity;

    /**
     * 购买时间
     */
    private LocalDateTime createTime;

    @Override
    public String toString() {
        return id + ". 商品Id：" + productId + ", 商品名称：" + productName + ", 商品价格：" + productPrice + ", 购买数量：" + quantity + ", 订单时间：" + createTime;
    }
}
