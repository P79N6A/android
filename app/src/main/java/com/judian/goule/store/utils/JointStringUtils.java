package com.judian.goule.store.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by chenweize on 2017/8/17.
 * 字符串拼接  从小到大进行拼接 拼接格式   A=123&B=234
 */
public class JointStringUtils {

    /**
     * @param nameList   名字
     * @param valuesList 值  比如 user = 123  user是名字，123是值
     * @return
     */
    public static String JointString(ArrayList<String> nameList, ArrayList<String> valuesList) {
        String[] name = new String[nameList.size()];
        String[] value = new String[valuesList.size()];
        for (int i = 0; i < nameList.size(); i++) {
            name[i] = nameList.get(i);
            value[i] = valuesList.get(i);
        }
        //冒泡排序 从小到大
        for (int i = 0; i < name.length - 1; i++) {
            for (int j = 0; j < name.length - 1 - i; j++) {
                if (name[j].compareTo(name[j + 1]) > 0) {
                    String s = name[j];
                    name[j] = name[j + 1];
                    name[j + 1] = s;
                    s = value[j];
                    value[j] = value[j + 1];
                    value[j + 1] = s;
                }
            }
        }
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < valuesList.size(); i++) {
            if (i != 0) {
                string.append("&");
            }
            try {
                string.append(name[i]).append("=").append(URLEncoder.encode(value[i], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return string.toString();
    }

    /**
     * 每一个值用逗号隔开
     *
     * @param
     * @return
     */
    public static String commaString(List<String> nameList) {
        if (nameList.size()==0) {
            return "null";
        }
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < nameList.size(); i++) {
            if (i != 0) {
                string.append(",");
            }
            string.append(nameList.get(i));
        }
        return string.toString();
    }

    /**
     * 拼接字符串
     * @param map
     * @return
     */
    public static String JointString(TreeMap<String,String> map){
        int i = 0;
        StringBuilder string = new StringBuilder();
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(i != 0){
                string.append("&");
            }
            try {
                string.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }
        return string.toString();
    }
}
