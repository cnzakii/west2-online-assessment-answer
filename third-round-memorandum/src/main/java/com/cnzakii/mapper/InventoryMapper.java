package com.cnzakii.mapper;

import com.cnzakii.entity.Inventory;

/**
 * 库存表的mapper接口
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
public interface InventoryMapper {
    void insertInventory(Inventory inventory);


    void deleteInventoryById(Integer id);


    Inventory selectInventoryById(Integer id);

}
