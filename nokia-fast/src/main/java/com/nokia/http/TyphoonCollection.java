package com.nokia.http;

import com.alibaba.fastjson.JSONObject;
import com.nokia.http.service.HttpClientUtil;
import com.nokia.utils.RData;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.MapMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/6/16 0016.
 */
@RestController
@RequestMapping("/api/weather")
public class TyphoonCollection {
    private static final Logger logger = LoggerFactory.getLogger(TyphoonCollection.class);
    //台风列表
    private static String TYPHOON_LIST_URL = "http://typhoon.nmc.cn/weatherservice/typhoon/jsons/list_%s?t=%s";
    //单个台风路径及详细信息
    private static String TYPHOON_INFO_URL = "http://typhoon.nmc.cn/weatherservice/typhoon/jsons/view_%s?t=%s";
    //云层图
    private static String CLOUDS_LIST_URL = "http://typhoon.nmc.cn/weatherservice/imgs/satellite/default?t=%s";
    //降雨量
    private static String RAINFALL_URL = "http://typhoon.nmc.cn/weatherservice/diamond14/rainfall/%s.json?t=%s";
    //灾害天气
    private static String DISASTER_URL = "http://typhoon.nmc.cn/weatherservice/diamond14/rainfall/%s.json?t=%s";
    //海区预报
    private static String DIAMOND_URL = "http://typhoon.nmc.cn/weatherservice/diamond8/view/%s_%s.json?t=%s";
    //


    private HttpClientUtil httpClientUtil = HttpClientUtil.getInstance("typhoon.nmc.cn");

    @GetMapping("/typhoon_list")
    public RData getTyphoonList(String year) {
        if (StringUtils.isEmpty(year)) {
            year = getYear();
        }
        String url = String.format(TYPHOON_LIST_URL, year, System.currentTimeMillis());
        JSONObject jsonObject = getJson(url);
        RData rData = RData.ok();
        rData.put("json", jsonObject);
        return rData;
    }

    @GetMapping("/typhoon_info")
    public RData getTyphoonInfo(@RequestParam String id) {
        String url = String.format(TYPHOON_INFO_URL, id, System.currentTimeMillis());
        JSONObject jsonObject = getJson(url);
        RData rData = RData.ok();
        rData.put("json", jsonObject);
        return rData;
    }

    @GetMapping("/clouds_list")
    public RData getCloudsImg() {
        //CLOUDS_LIST_URL
        String url = String.format(CLOUDS_LIST_URL, System.currentTimeMillis());
        JSONObject jsonObject = getJson(url);
        String time = String.valueOf(jsonObject.get("time"));
        String imgUrl = "http://typhoon.nmc.cn/weatherservice/imgs/satellite/%s.png";
        List<String> list = new ArrayList<>();
        list.add(String.format(imgUrl, time + "_0_0"));
        list.add(String.format(imgUrl, time + "_0_1"));
        list.add(String.format(imgUrl, time + "_1_0"));
        list.add(String.format(imgUrl, time + "_1_1"));
        jsonObject.put("imglist", list);
        RData rData = RData.ok();
        rData.put("json", jsonObject);
        return rData;
    }

    @GetMapping("/rainfall")
    public RData rainfall(String hour) {
        if (StringUtils.isEmpty(hour)) {
            hour = "24";
        }
        String url = String.format(RAINFALL_URL, hour, System.currentTimeMillis());
        JSONObject jsonObject = getJson(url);
        RData rData = RData.ok();
        rData.put("json", jsonObject);
        return rData;
    }

    @GetMapping("/diamond_all")
    public RData diamond_all(String hour) {
        if (StringUtils.isEmpty(hour)) {
            hour = "24";
        }
        String url0 = String.format(DIAMOND_URL, "0", hour, System.currentTimeMillis());
        String url1 = String.format(DIAMOND_URL, "1", hour, System.currentTimeMillis());
        String url2 = String.format(DIAMOND_URL, "2", hour, System.currentTimeMillis());
        JSONObject jsonObject0 = getJson(url0);
        JSONObject jsonObject1 = getJson(url1);
        JSONObject jsonObject2 = getJson(url2);
        RData rData = RData.ok();
        rData.put("json0", jsonObject0);
        rData.put("json1", jsonObject1);
        rData.put("json2", jsonObject2);
        return rData;
    }

    @GetMapping("/diamond")
    public RData diamond(String n, String hour) {
        if (StringUtils.isEmpty(hour)) {
            hour = "24";
        }
        if (StringUtils.isEmpty(n)) {
            n = "0";
        }
        String url = String.format(DIAMOND_URL, n, hour, System.currentTimeMillis());
        JSONObject jsonObject = getJson(url);
        RData rData = RData.ok();
        rData.put("json", jsonObject);
        return rData;
    }

    private JSONObject getJson(String url) {
        Map<String, Object> map = httpClientUtil.toGet(url);
        String result = String.valueOf(map.get("result"));
        result = result.substring(result.indexOf("(") + 1, result.length() - 1);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }


    public static String getYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

}
