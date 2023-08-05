package com.cnzakii.orderManage.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 此工具类用户获取SqlSession的实例对象
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/

public class MybatisUtils {

    private static final Logger LOGGER = Logger.getLogger(MybatisUtils.class.getName());
    private static SqlSessionFactory sqlSessionFactory;
    private static final String resource = "mybatis-config.xml";

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
        }
    }

    /**
     * 获取sqlSession
     *
     * @return sqlSession实例对象
     */
    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

}

