package com.clwater.smsfroward;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
    private static SPUtils SPUtils;
    private static SharedPreferences sharedPreferences;

    private final static String KEY = "smsforward";

    private SPUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    public static SPUtils getInstance(Context context) {
        if (SPUtils == null) {
            SPUtils = new SPUtils(context);
        }
        return SPUtils;
    }

    /**
     * 设置String类型值
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    /**
     * 获取key相对应的value，如果不设默认参数，默认值为""
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return sharedPreferences.getString(key, "");

    }
}
