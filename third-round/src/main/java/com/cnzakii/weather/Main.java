package com.cnzakii.weather;


import cn.hutool.core.bean.BeanUtil;

import com.cnzakii.weather.entity.Daily;
import com.cnzakii.weather.entity.Location;
import com.cnzakii.weather.json.DailyJson;
import com.cnzakii.weather.json.LocationJson;
import com.cnzakii.weather.mapper.DailyMapper;
import com.cnzakii.weather.mapper.LocationMapper;
import com.cnzakii.weather.util.HttpClientFluentUtils;
import com.cnzakii.weather.util.MyJsonUtils;
import com.cnzakii.weather.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;


/**
 * 主程序
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
public class Main {


    // TODO 填入你的key值
    public static final String KEY = "你的key值";

    public static SqlSession sqlSession = MybatisUtils.getSession();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mainPage();
        while (true) {
            int num = 0;
            String input = sc.nextLine();

            if (Objects.equals(input, "exit")) {
                // 如果用户输入为exit，则直接退出程序
                System.exit(0);
            } else {
                // 如果为数字
                num = Integer.parseInt(input);
            }

            switch (num) {
                case 1 -> selectLocationInformation();
                case 2 -> selectLocationWeather();
            }
            mainPage();
        }
    }


    /**
     * 主界面
     */
    public static void mainPage() {
        System.out.println(
                """
                        ------------------------------------
                        |请输入对应编号执行对应操作            |
                        ------------------------------------
                        |1.查询城市信息                      |
                        ------------------------------------
                        |2.查询城市未来三日天气               |
                        ------------------------------------
                        |exit.退出程序                      |
                        ------------------------------------
                        """);


    }

    public static void selectLocationWeather() {
        System.out.println("请输入你要查询的城市名称");
        String input = sc.nextLine();

        if (input == null || input.isEmpty()) {
            System.out.println("输入不能为空");
        } else {
            // 获取对应的城市编码
            Location location = selectLocationInformation(input);

            if (location == null) {
                System.out.println("无法找到该城市");
            } else {
                // 先从数据库中查找
                DailyMapper dailyMapper = sqlSession.getMapper(DailyMapper.class);

                List<Daily> list = dailyMapper.getDailyListByLocationId(location.getId(), LocalDate.now(), LocalDate.now().plusDays(2));

                if (list.size() == 3) {
                    list.forEach(daily -> {
                        System.out.println("----------------------------------------");
                        System.out.println(daily);
                    });
                } else {
                    // 删除日期大于今天的数据
                    dailyMapper.deleteDailyByLocationId(location.getId(), LocalDate.now());

                    // 查询最新的天气预测
                    list = getLocationWeather(location.getId());
                    if (list == null || list.isEmpty()) {
                        System.out.println("暂无该城市的天气预测");
                    } else {
                        // 更新数据库
                        list.forEach(dailyMapper::insertDaily);
                        sqlSession.commit();
                        // 输出查询结果
                        list.forEach(daily -> {
                            System.out.println("----------------------------------------");
                            System.out.println(daily);
                        });
                    }

                }
            }

        }


    }

    public static Location selectLocationInformation() {
        System.out.println("请输入你要查询的城市名称");
        String input = sc.nextLine();

        if (input == null || input.isEmpty()) {
            System.out.println("输入不能为空");
            return null;
        }

        Location location = selectLocationInformation(input);

        if (location == null) {
            System.out.println("无法找到该城市");
        } else {
            System.out.println(location);
        }
        return location;
    }


    /**
     * 查询城市信息
     *
     * @param locationName 城市名称
     * @return 城市信息
     */
    public static Location selectLocationInformation(String locationName) {

        // 先从数据库中查找
        LocationMapper locationMapper = sqlSession.getMapper(LocationMapper.class);

        Location location = locationMapper.getLocationByName(locationName);

        if (location != null) {
            return location;
        }

        // 如果数据库中没有，则调用API查询
        location = getLocationInformation(locationName);

        // 如果查询到了，则保存进数据库
        if (location != null) {
            locationMapper.insertLocation(location);
            sqlSession.commit();
        }


        return location;
    }


    /**
     * 请求获取对应城市的三日天气
     *
     * @param locationId 城市Id
     * @return daily集合
     */
    public static List<Daily> getLocationWeather(String locationId) {
        String response = HttpClientFluentUtils
                .get("https://devapi.qweather.com/v7/weather/3d?location=" + locationId + "&key=" + KEY);

        // 获取请求状态码，查看是否请求成功
        String code = MyJsonUtils.getValueByKey(response, "code", String.class);
        if (!Objects.equals(code, "200")) {
            System.out.println(response);
            System.out.println("请求出错了");
        } else {
            DailyJson[] dailies = MyJsonUtils.getValueByKey(response, "daily", DailyJson[].class);
            List<Daily> list;
            list = Arrays.stream(dailies)
                    .map(o -> BeanUtil.copyProperties(o, Daily.class))
                    .peek(daily -> daily.setLocationId(locationId))
                    .toList();
            return list;
        }

        return null;
    }

    /**
     * 请求获取城市信息
     *
     * @param locationName 城市名
     * @return 城市信息
     */
    public static Location getLocationInformation(String locationName) {
        String encode = URLEncoder.encode(locationName, StandardCharsets.UTF_8);
        String response = HttpClientFluentUtils
                .get("https://geoapi.qweather.com/v2/city/lookup?location=" + encode + "&key=" + KEY);

        // 获取请求状态码，查看是否请求成功
        String code = MyJsonUtils.getValueByKey(response, "code", String.class);
        if (!Objects.equals(code, "200")) {
            System.out.println("请求出错了");
            System.out.println(response);
        } else {
            LocationJson[] locationJsons = MyJsonUtils.getValueByKey(response, "location", LocationJson[].class);

            // 获取城市名字完全相符的城市信息
            Optional<LocationJson> location = Arrays.stream(locationJsons)
                    .filter(o -> Objects.equals(o.getName(), locationName))
                    .findFirst();

            if (location.isPresent()) {
                return BeanUtil.copyProperties(location.get(), Location.class);
            } else {
                System.out.println("无法找到所在城市信息");
            }
        }
        return null;
    }
}
