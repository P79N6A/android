package com.judian.goule.store.utils;

import android.content.ClipboardManager;
import android.content.Context;

import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.SpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.bean.CateBean;
import com.judian.goule.store.bean.XuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class Token {


//
    public static String getToken( )
    {
        return SpUtils.getInstance(MyApplication.application).getString("TOKEN","");
    }
    public static boolean isLogin()
    {
        return !getToken().equals("");
    }

    public static void setAgent(String  agent)//z赋值
    {
        SpUtils.getInstance(MyApplication.application).putString("AGENT",agent);
    }

    public static String getAgent( )//获取0:普通,1：审核,2:代理
    {
        return  SpUtils.getInstance(MyApplication.application).getString("AGENT","0");
//        return "0";
    }


    public static void setToken(String  token)
    {
        SpUtils.getInstance(MyApplication.application).putString("TOKEN",token);
    }
    public static void setFace(String  face)
    {
        SpUtils.getInstance(MyApplication.application).putString("face",face);

    }


    public static String getFace( )
    {
        return SpUtils.getInstance(MyApplication.application).getString("face","");

    }
   public static void setPhone(String  phone)
    {
        SpUtils.getInstance(MyApplication.application).putString("phone",phone);

    }


    public static String getPhone( )
    {
        return SpUtils.getInstance(MyApplication.application).getString("phone","");
    }





    public static void setLiveBg(String  face)
    {

        SpUtils.getInstance(MyApplication.application).putString("livebg",face);

    }
    public static void setSearchBg(String  face)
    {

        SpUtils.getInstance(MyApplication.application).putString("searchbg",face);

    }

    public static String getSearchBg( )
    {
        return SpUtils.getInstance(MyApplication.application).getString("searchbg",null);


    }
    public static String getLiveBg( )
    {
        return SpUtils.getInstance(MyApplication.application).getString("livebg",null);

    }


    public static void setCate(String  face)
    {
        SpUtils.getInstance(MyApplication.application).putString("cate",face);

    }


    public static CateBean getCate( )
    {
        String  ss=SpUtils.getInstance(MyApplication.application).getString("cate","");
        if (ss.equals("")){
            return new CateBean();
        }else {

            return  new Gson().fromJson(ss,CateBean.class);
        }

    }
   public static void setGg(String  gg)
    {
        SpUtils.getInstance(MyApplication.application).putString("gg",gg);

    }


    public static XuBean getGg( )
    {


       String  ss=SpUtils.getInstance(MyApplication.application).getString("gg","");
        if (ss.equals("")){
            return new XuBean();
        }else {

            return  new Gson().fromJson(ss,XuBean.class);
        }

    }





    public static String getUsetId( )
    {
        return SpUtils.getInstance(MyApplication.application).getString("userId","");

    }
    public static void setUserId(String  userId)
    {
        SpUtils.getInstance(MyApplication.application).putString("userId",userId);

    }

    public static boolean isPush( )
    {
        return SpUtils.getInstance(MyApplication.application).getBoolean("push",true);
//        return false;

    }
    public static void setPush(boolean  userId)
    {
        SpUtils.getInstance(MyApplication.application).putBoolean("push",userId);

    }

    public static boolean isMsg( )
    {
        return SpUtils.getInstance(MyApplication.application).getBoolean("Msg",true);
//        return false;

    }
    public static void setMsg(boolean  msg)
    {
        SpUtils.getInstance(MyApplication.application).putBoolean("Msg",msg);

    }




    public static boolean  isBCopy(String   key)
    {

        boolean isT=false;
        String  ss= SpUtils.getInstance(MyApplication.application).getString("isBCopy","");
        if (key.equals(ss)){
            isT=true;
        }else {
            ClipboardManager cm = (ClipboardManager)MyApplication.application. getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setText("");
            isT=false;
        }
        return  isT;
    }


    public static void logout( )
    {
        SpUtils.getInstance(MyApplication.application).putString("TOKEN","");
        SpUtils.getInstance(MyApplication.application).putString("face","");
        SpUtils.getInstance(MyApplication.application).putString("AGENT","0");
        SpUtils.getInstance(MyApplication.application).putString("userId","");
        SpUtils.getInstance(MyApplication.application).putString("yqm","");
    }



    public static void   isColse()
    {
        ClipboardManager cm = (ClipboardManager)MyApplication.application. getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText("");
        SpUtils.getInstance(MyApplication.application).putString("isBCopy",null);
        Logger.d("dddddd","ss==isColse ="+SpUtils.getInstance(MyApplication.application).getString("isBCopy",null));
    }




    public static void   addKey(String key)
    {

        Logger.e("ddddddddd","vvvvv  addKey == "+key);
        SpUtils.getInstance(MyApplication.application).putString("isBCopy",key);
    }

    public static String    getKey()
    {
       return SpUtils.getInstance(MyApplication.application).getString("isBCopy","");
    }


    public static void   addHistory(String key)
    {
        boolean isT=false;
        List<String>  list = null;
        String  ss= SpUtils.getInstance(MyApplication.application).getString("HISTORY",null);

        list=new Gson().fromJson(ss,new TypeToken<List<String >>(){}.getType());
        if (list==null){
            list=new ArrayList<>();
            list.add(key);
        }
        if (list!=null&&list.size()!=0){
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(key)){
                    isT=true;
                }}
        }
        if (!isT){
            list.add(0,key);
        }
        String s = new Gson().toJson(list);
        SpUtils.getInstance(MyApplication.application).putString("HISTORY",s);

    }

    public static  List<String>   getHistory()
    {
        List<String>  list = null;
        String  ss= SpUtils.getInstance(MyApplication.application).getString("HISTORY",null);

        list=new Gson().fromJson(ss,new TypeToken<List<String >>(){}.getType());
        if (list==null){
            list=new ArrayList<>();
        }
        return  list;
    }


    public static void   closeHistory()
    {

        SpUtils.getInstance(MyApplication.application).putString("HISTORY","");

    }




}
