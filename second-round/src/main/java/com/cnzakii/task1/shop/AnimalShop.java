package com.cnzakii.task1.shop;

import com.cnzakii.task1.Customer;
import com.cnzakii.task1.animal.Animal;

/**
 * 宠物店接口
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-01
 **/
public interface AnimalShop {

    /**
     * 买入新动物
     *
     * @param animal 新动物信息
     * @param profit 利润
     */
    void buyAnimal(Animal animal, double profit);

    /**
     * 招待客户
     *
     * @param customer 需要招待的客户
     */
    void entertainClients(Customer customer);

    /**
     * 歇业
     */
    void outOfBusiness();
}
