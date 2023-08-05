package com.cnzakii.task1.animal;

/**
 * 老鼠类，继承{@link Animal}类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-01
 **/
public class Mouse extends Animal {


    public Mouse(int age, String gender) {
        super("鼠鼠", age, gender, 8888);
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                '}';
    }
}
