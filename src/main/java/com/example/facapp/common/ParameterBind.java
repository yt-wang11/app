package com.example.facapp.common;

import java.util.Iterator;
import java.util.Map;

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
