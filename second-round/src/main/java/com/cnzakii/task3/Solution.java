package com.cnzakii.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 设计一个接收邮箱String的方法boolean, 使用正则表达式或其他方法判断邮箱格式是否合法
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-03
 **/
public class Solution {

    public static void main(String[] args) {
        String email1 = "example@gmail.com";
        String email2 = "invalid_email";
        String email3 = "another_example@subdomain.domain";

        System.out.println("Email 1 是否有效：" + isValidEmail(email1));
        System.out.println("Email 2 是否有效：" + isValidEmail(email2));
        System.out.println("Email 3 是否有效：" + isValidEmail(email3));
    }

    /**
     * 使用正则表达式判断邮箱格式是否合法有效
     *
     * @param email 需要判断的邮箱
     * @return true or false
     */
    public static boolean isValidEmail(String email) {
        // 定义邮箱格式的正则表达式
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(emailRegex);

        // 创建 Matcher 对象
        Matcher matcher = pattern.matcher(email);

        // 判断是否匹配
        return matcher.matches();
    }


}
