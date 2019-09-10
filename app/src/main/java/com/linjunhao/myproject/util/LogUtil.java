package com.linjunhao.myproject.util;

import android.util.Log;

/**
 * Log 工具类，方便屏蔽日志
 *
 * @author linjunhao
 * @date 2019/9/10
 */
public class LogUtil {

    private LogUtil() {
    }

    public static final String DEFAULT_TAG = "MyProject";

    public static final int VERBOSE = 1;

    public static final int DEBUG = 2;

    public static final int INFO = 3;

    public static final int WARN = 4;

    public static final int ERROR = 5;

    public static final int NOTHING = 6;

    public static final int LEVEL = ERROR;

    private static boolean isShow(int level) {
        if (LEVEL <= level) {
            return true;
        }
        return false;
    }

    public static void v(String msg) {
        if (isShow(VERBOSE)) {
            v(DEFAULT_TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isShow(VERBOSE)) {
            Log.v(tag, msg);
        }
    }

    public static void d(String msg) {
        if (isShow(DEBUG)) {
            d(DEFAULT_TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isShow(DEBUG)) {
            Log.d(tag, msg);
        }
    }

    public static void i(String msg) {
        if (isShow(INFO)) {
            i(DEFAULT_TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isShow(INFO)) {
            Log.i(tag, msg);
        }
    }

    public static void w(String msg) {
        if (isShow(WARN)) {
            w(DEFAULT_TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isShow(WARN)) {
            Log.w(tag, msg);
        }
    }

    public static void e(String msg) {
        if (isShow(ERROR)) {
            e(DEFAULT_TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isShow(ERROR)) {
            Log.e(tag, msg);
        }
    }
}
