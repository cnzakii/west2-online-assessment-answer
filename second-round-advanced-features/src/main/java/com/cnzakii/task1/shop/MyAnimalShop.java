package com.cnzakii.task1.shop;

import com.cnzakii.task1.Customer;
import com.cnzakii.task1.animal.Animal;
import com.cnzakii.task1.exception.AnimalNotFountException;
import com.cnzakii.task1.exception.InsufficientBalanceException;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 自己的宠物店，实现{@link AnimalShop}类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-01
 **/
public class MyAnimalShop implements AnimalShop {

    /**
     * 余额
     */
    private double balance;

    /**
     * 当天支出
     */
    private double expenditure;

    /**
     * 当天收入
     */
    private double income;

    /**
     * 动物列表
     */
    private List<Animal> animals;

    /**
     * 顾客列表
     */
    private List<Customer> customers;

    /**
     * 是否营业
     */
    private boolean isBusiness;

    public MyAnimalShop(double initBalance) {
        this.balance = initBalance;
        this.expenditure = 0;
        this.income = 0;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isBusiness = true;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    /**
     * 实现买入新动物
     *
     * @param animal 新动物信息
     * @param profit 利润
     */
    @Override
    public void buyAnimal(Animal animal, double profit) {
        // 计算宠物店的实际资产
        double realAssets = this.balance + this.income - this.expenditure;

        if (animal.getPrice() > realAssets) {
            throw new InsufficientBalanceException();
        }

        // 计入当日支出
        this.expenditure = this.expenditure + animal.getPrice();
        animal.setPrice(animal.getPrice() + profit);
        this.animals.add(animal);
    }

    /**
     * 实现招待用户
     *
     * @param customer 需要招待的客户
     */
    @Override
    public void entertainClients(Customer customer) {
        // 尝试获取动物
        if (this.animals == null || this.animals.isEmpty()) {
            // 如果动物列表为空，则直接抛出异常
            throw new AnimalNotFountException();
        }

        // 出售动物
        Animal animal = animals.get(0);
        System.out.println("顾客: " + customer.getName() + "\n购买了: " + animal);

        // 计入当日收入
        this.income = this.income + animal.getPrice();
        animals.remove(0);

        // 记录顾客信息
        this.customers.add(customer);
        customer.setArrivalCount(customer.getArrivalCount() + 1);
        customer.setLatestArrivalTime(LocalDate.now());
    }

    /**
     * 实现歇业
     */
    @Override
    public void outOfBusiness() {
        LocalDate today = LocalDate.now();
        // 输出今日顾客信息
        System.out.println("今日顾客:");
        for (Customer customer : this.customers) {
            if (Objects.equals(today, customer.getLatestArrivalTime())) {
                System.out.println(customer);
            }
        }
        // 计算当日利润，并更新宠物店余额
        double profit = this.income - this.expenditure;

        if (profit < 0) {
            System.out.println("今日亏损，" + "收入为：" + this.income + "，支出为" + this.expenditure + "，亏损：" + Math.abs(profit));
        } else {
            System.out.println("今日盈利，" + "收入为：" + this.income + "，支出为" + this.expenditure + "，盈利：" + profit);
        }
    }
}
