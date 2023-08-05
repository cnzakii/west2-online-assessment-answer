package com.cnzakii.orderManage.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单 实体类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
@Data
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = -1356821462361064062L;

    /**
     * 订单id
     */
    private int id;

    /**
     * 商品 id
     */
    private int productId;

    /**
     * 购买数量
     */
    private int quantity;

    /**
     * 购买时间
     */
    private LocalDateTime createTime;

}
