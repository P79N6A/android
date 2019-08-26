package com.example.ccy.ccyui.util;

import android.util.Log;

/**
 * Created by efan on 2017/4/13.
 */

public class Logger {

    //设为false关闭日志
    private static final boolean LOG_ENABLE = false;
    public static void i(String tag, String msg){
        if (LOG_ENABLE){
            e(tag, msg);
        } }

    public static void v(String tag, String msg){
        if (LOG_ENABLE){
            e(tag, msg);
        } }

    public static void d(String tag, String msg){
        if (LOG_ENABLE){
            e(tag, msg);
        }
    }
    public static void w(String tag, String msg){
        if (LOG_ENABLE){
            e(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG_ENABLE){
            if (tag == null || tag.length() == 0
                    || msg == null || msg.length() == 0)
                return;

            int segmentSize = 3 * 1024;
            long length = msg.length();
            if (length <= segmentSize ) {// 长度小于等于限制直接打印
                Log.e(tag, msg);
            }else {
                while (msg.length() > segmentSize ) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize );
                    msg = msg.replace(logContent, "");
                    Log.e(tag, logContent);
                }
                Log.e(tag, msg);// 打印剩余日志
            }
        }

    }

}
