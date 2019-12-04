package com.example.facapp.common;

import java.util.Iterator;
import java.util.Map;

/**
 *  * description: 处理SQL的参数绑定
 *  * author: jt
 *  * date: 2017-11-30 上午9:25
 *  * modify: 
 *  
 */
public class ParameterBind {
    public static String bind(String sql, Map map) {
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            sql = sql.replace(":" + key, map.get(key).toString());
        }
        return sql;
    }

    private ParameterBind() {
        throw new IllegalStateException("Utility class");
    }

}
