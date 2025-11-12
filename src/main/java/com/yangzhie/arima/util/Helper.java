package com.yangzhie.arima.util;

import org.json.simple.JSONObject;

public class Helper {
    public Object safe(JSONObject object, String key) {
        if (object != null) {
            return object.get(key);
        } else {
            return null;
        }
    }
}
