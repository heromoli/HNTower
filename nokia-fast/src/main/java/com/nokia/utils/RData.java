package com.nokia.utils;

import org.apache.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wow on 2019/6/6.
 *
 */
public class RData extends HashMap<String, Object> {
    public RData() {
        put("code", 0);
        put("msg", "success");
    }
    public static RData error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static RData error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static RData error(int code, String msg) {
        RData r = new RData();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static RData ok(String msg) {
        RData r = new RData();
        r.put("msg", msg);
        return r;
    }

    public static RData ok(Map<String, Object> map) {
        RData r = new RData();
        r.putAll(map);
        return r;
    }

    public static RData ok() {
        return new RData();
    }

    public RData put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static Map getMap(String key,Object value){
        RData r = new RData();
        r.put(key,value);
        return r;
    }
}
