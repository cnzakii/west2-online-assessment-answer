package com.cnzakii.task1.animal;

/**
 * 中华田园犬，继承{@link Animal}类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-01
 **/
public class ChineseRuralDog extends Animal {

    /**
     * 是否注射狂犬病疫苗
     */
    private boolean isVaccineInjected;

    public ChineseRuralDog(int age, String gender, boolean isVaccineInjected) {
        super("中华田园犬", age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseRuralDog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                '}';
    }
}
