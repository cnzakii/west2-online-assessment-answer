package com.cnzakii.task1;

import com.cnzakii.task1.animal.Animal;
import com.cnzakii.task1.animal.Cat;
import com.cnzakii.task1.animal.ChineseRuralDog;
import com.cnzakii.task1.animal.Mouse;
import com.cnzakii.task1.shop.AnimalShop;
import com.cnzakii.task1.shop.MyAnimalShop;

/**
 * 宠物店测试类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-03
 **/
public class Test {
    public static void main(String[] args) {
        // 创建宠物店
        AnimalShop animalShop = new MyAnimalShop(100);

        // 创建动物
        Animal animal1 = new Cat(2, "母");
        Animal animal2 = new ChineseRuralDog(1, "公", true);
        Animal animal3 = new Mouse(4, "母");

        // 创建客户
        Customer customer1 = new Customer("一号");
        Customer customer2 = new Customer("二号");
        Customer customer3 = new Customer("三号");

        // 买入动物
        animalShop.buyAnimal(animal2, 100);

        // 招待客户
        animalShop.entertainClients(customer1);

        // 再次购买动物
        animalShop.buyAnimal(animal1, 100);

        // 再次招待客户
        animalShop.entertainClients(customer2);

        // 歇业
        animalShop.outOfBusiness();

    }
}
