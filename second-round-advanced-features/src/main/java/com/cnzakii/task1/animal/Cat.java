package com.cnzakii.task1.animal;

/**
 * 猫类，继承{@link Animal}类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-01
 **/
public class Cat extends Animal {

    public Cat(int age, String gender) {
        super("猫", age, gender, 200);
    }

    @Override
    public String toString() {
        return "cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                '}';
    }
}
