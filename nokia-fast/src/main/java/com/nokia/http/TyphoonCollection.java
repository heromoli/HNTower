package com.nokia.http;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.nokia.http.service.HttpClientUtil;
import com.nokia.modules.disaster.entity.ZjwaterLandInfo;
import com.nokia.modules.disaster.entity.ZjwaterTyphoonInfo;
import com.nokia.modules.disaster.entity.ZjwaterTyphoonPoint;
import com.nokia.modules.workflow.entity.Supervision;
import com.nokia.utils.RData;
import com.nokia.utils.excel.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2020/6/16 0016.
 */
@RestController
@RequestMapping("/api/weather")
public class TyphoonCollection {
    private static final Logger logger = LoggerFactory.getLogger(TyphoonCollection.class);
    //台风列表

    private static String TYPHOON_DEFAULT_URL = "http://typhoon.nmc.cn/weatherservice/typhoon/jsons/list_default?t=%s";
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

    //台风信息导出模块链接
    //当前活跃台风
    private static String ACTIVITY_URL = " http://typhoon.zjwater.gov.cn/Api/TyhoonActivity";
    //获取台风列表
    private static String LIST_URL = "http://typhoon.zjwater.gov.cn/Api/TyphoonList/%s";
    //获取台风信息
    private static String INFO_URL = "http://typhoon.zjwater.gov.cn/Api/TyphoonInfo/%s";

    private HttpClientUtil httpClientUtil = HttpClientUtil.getInstance("typhoon.nmc.cn");

    @GetMapping("/typhoon_default")
    public RData getTyphoon_default() {
        String url = String.format(TYPHOON_DEFAULT_URL, System.currentTimeMillis());
        JSONObject jsonObject = getJson(url);
        RData rData = RData.ok();
        rData.put("json", jsonObject);
        return rData;
    }

    @GetMapping("/typhoon_list")
    public RData getTyphoon_list(@RequestParam String year) {
        if (StringUtils.isEmpty(year)) {
            year = getYear();
        }
        String url = String.format(TYPHOON_LIST_URL, year, System.currentTimeMillis());
        JSONObject jsonObject = getInfoJson(url);
        RData rData = RData.ok();
        rData.put("json", jsonObject);
        return rData;
    }

    @GetMapping("/typhoon_info")
    public RData getTyphoon_info(@RequestParam String id) {
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

    @GetMapping("/typhoonActivity")
    public RData typhoonActivity() {
        String landUrl = String.format(ACTIVITY_URL);
        logger.info("landUrl:" + landUrl);
//        JSONObject landObject = getZjwaterInfoJson(landUrl);
//        logger.info("" + landObject);
        RData rData = RData.ok();
        rData.put("json", getZjwaterAcitvity(landUrl));
        return rData;
    }

    @GetMapping("/typhoonList")
    public RData typhoonList(@RequestParam String year) {
        String listUrl = String.format(LIST_URL, year);
        logger.info("landUrl:" + listUrl);
        RData rData = RData.ok();
        rData.put("json", getZjwaterTyphoonList(listUrl));
        return rData;
    }

    @GetMapping("/typhoonInfo")
    public void getTyphoonInfo(@RequestParam("tfid")  String tfid, HttpServletResponse response) {
        String infoUrl = String.format(INFO_URL, tfid);
        logger.info("infoUrl:" + infoUrl);
        Map<String, Object> detailMap = getZjwaterInfoDetail(infoUrl);
        String name = (String) detailMap.get("name");
        List<ZjwaterLandInfo> landInfos = (List<ZjwaterLandInfo>) detailMap.get("landInfos");
        List<ZjwaterTyphoonPoint> points = (List<ZjwaterTyphoonPoint>) detailMap.get("points");

        ExcelUtil.writeExcelWithSheets(response, ExcelTypeEnum.XLSX, name, landInfos, "登陆点信息", ZjwaterLandInfo.class, points, "路径信息", ZjwaterTyphoonPoint.class);
    }

    private JSONObject getJson(String url) {
        Map<String, Object> map = httpClientUtil.toGet(url);
        String result = String.valueOf(map.get("result"));
        result = result.substring(result.indexOf("(") + 2, result.length() - 2);

        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }

    private JSONObject getInfoJson(String url) {
        Map<String, Object> map = httpClientUtil.toGet(url);
        String result = String.valueOf(map.get("result"));
        result = result.substring(result.indexOf("(") + 1, result.length() - 1);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }

    private List getZjwaterAcitvity(String url) {
        Map<String, Object> map = httpClientUtil.toGetGBK(url);
        String result = String.valueOf(map.get("result"));
        List<ZjwaterTyphoonInfo> infoList = new ArrayList<>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(result);
            Element rootElt = doc.getRootElement();
            logger.info(rootElt.getText());
            Iterator typhoonModel = rootElt.elementIterator("TyphoonModel");
            while (typhoonModel.hasNext()) {

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return infoList;
    }

    private List getZjwaterTyphoonList(String url) {
        Map<String, Object> map = httpClientUtil.toGetGBK(url);
        String result = String.valueOf(map.get("result"));
        List<ZjwaterTyphoonInfo> zjwaterTyphoonInfoList = new ArrayList<>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(result);
            Element rootElt = doc.getRootElement();
            Iterator typhoonListModel = rootElt.elementIterator("TyphoonListModel");
            while (typhoonListModel.hasNext()) {
                Element typhoonListModelEle = (Element) typhoonListModel.next();
                ZjwaterTyphoonInfo typhoonInfo = new ZjwaterTyphoonInfo();
                typhoonInfo.setTfid(typhoonListModelEle.element("tfid").getText());
                typhoonInfo.setEnname(typhoonListModelEle.element("enname").getText());
                typhoonInfo.setName(typhoonListModelEle.element("name").getText());
                typhoonInfo.setStarttime(typhoonListModelEle.element("starttime").getText());
                typhoonInfo.setEndtime(typhoonListModelEle.element("endtime").getText());
                typhoonInfo.setIsactive(typhoonListModelEle.element("isactive").getText());
                typhoonInfo.setWarnlevel(typhoonListModelEle.element("warnlevel").getText());
                zjwaterTyphoonInfoList.add(typhoonInfo);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return zjwaterTyphoonInfoList;
    }


    private Map<String, Object> getZjwaterInfoDetail(String url) {
        Map<String, Object> map = httpClientUtil.toGetGBK(url);
        String result = String.valueOf(map.get("result"));
        String name = "";
        List<ZjwaterLandInfo> landInfoList = new ArrayList<>();
        List<ZjwaterTyphoonPoint> pointList = new ArrayList<>();

        Document doc = null;
        try {
            doc = DocumentHelper.parseText(result);
            Element rootElt = doc.getRootElement();
            Iterator typhoonModel = rootElt.elementIterator("TyphoonModel");
            while (typhoonModel.hasNext()) {
                Element typhoonModelEle = (Element) typhoonModel.next();
//                Iterator name = typhoonModelEle.elementIterator("name");
                name = typhoonModelEle.elementText("name");

                Iterator land = typhoonModelEle.elementIterator("land");
                while (land.hasNext()) {
                    Element landEle = (Element) land.next();
                    Iterator landModel = landEle.elementIterator("LandModel");
                    while (landModel.hasNext()) {
                        Element landModelEle = (Element) landModel.next();
                        ZjwaterLandInfo zjwaterLandInfo = new ZjwaterLandInfo();
                        zjwaterLandInfo.setInfo(landModelEle.element("info").getText());
                        zjwaterLandInfo.setLandaddress(landModelEle.element("landaddress").getText());
                        zjwaterLandInfo.setLandtime(landModelEle.element("landtime").getText());
                        zjwaterLandInfo.setLat(landModelEle.element("lat").getText());
                        zjwaterLandInfo.setLng(landModelEle.element("lng").getText());
                        zjwaterLandInfo.setStrong(landModelEle.element("strong").getText());
                        landInfoList.add(zjwaterLandInfo);
                    }
                }

                Iterator points = typhoonModelEle.elementIterator("points");
                while (points.hasNext()) {
                    Element pointsEle = (Element) points.next();
                    Iterator typhoonPointModel = pointsEle.elementIterator("TyphoonPointModel");
                    while (typhoonPointModel.hasNext()) {
                        Element typhoonPointModelEle = (Element) typhoonPointModel.next();
                        ZjwaterTyphoonPoint point = new ZjwaterTyphoonPoint();
                        point.setCkposition(typhoonPointModelEle.element("ckposition").getText());
                        point.setLat(typhoonPointModelEle.element("lat").getText());
                        point.setLng(typhoonPointModelEle.element("lng").getText());
                        point.setMovedirection(typhoonPointModelEle.element("movedirection").getText());
                        point.setMovespeed(typhoonPointModelEle.element("movespeed").getText());
                        point.setPower(typhoonPointModelEle.element("power").getText());
                        point.setPressure(typhoonPointModelEle.element("pressure").getText());
                        point.setSpeed(typhoonPointModelEle.element("speed").getText());
                        point.setStrong(typhoonPointModelEle.element("strong").getText());
                        point.setTime(typhoonPointModelEle.element("time").getText());
                        pointList.add(point);
                    }

                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("name", name);
        resultMap.put("landInfos", landInfoList);
        resultMap.put("points", pointList);
        return resultMap;
    }


    public static String getYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

}
