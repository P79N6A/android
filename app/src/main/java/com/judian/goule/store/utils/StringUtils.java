package com.judian.goule.store.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenweize on 2017/9/28.
 * String 工具转换类
 */
public class StringUtils {
    //判断是否是全数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
