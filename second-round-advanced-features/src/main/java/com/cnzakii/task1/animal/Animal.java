package com.cnzakii.task1.animal;

/**
 * 动物抽象类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-01
 **/
public abstract class Animal {
    /**
     * 动物名
     */
    protected String name;

    /**
     * 年龄
     */
    protected int age;

    /**
     * 性别
     */
    protected String gender;

    /**
     * 价格
     */
    protected double price;


    public Animal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String toString();
}
