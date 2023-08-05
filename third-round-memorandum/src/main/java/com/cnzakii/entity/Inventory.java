package com.cnzakii.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 库存 实体类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
@Data
public class Inventory implements Serializable {
    @Serial
    private static final long serialVersionUID = -8492122229252952545L;

    /**
     * 商品id
     */
    private int id;

    /**
     * 商品库存
     */
    private int quantity;
}
