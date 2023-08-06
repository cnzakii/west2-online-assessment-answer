package com.cnzakii.weather.util;

import com.cnzakii.orderManage.util.MybatisUtils;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HttpClient封装工具类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/

public class HttpClientFluentUtils {

    private static final Logger LOGGER = Logger.getLogger(MybatisUtils.class.getName());

    public static String get(String url){
        String result = null;
        try {
            Response response = Request.get(url).execute();
            result = response.returnContent().toString();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,e.getLocalizedMessage());
        }
        return result;
    }


}
