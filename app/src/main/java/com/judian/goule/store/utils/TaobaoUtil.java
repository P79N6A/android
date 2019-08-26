package com.judian.goule.store.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Patterns;

import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class TaobaoUtil {




    public static  void openTaoB(Activity activity, String  url){



        if (isAvilible(activity,"com.taobao.taobao")){
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse(url); // 商品地址
            intent.setData(uri);
            intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
            activity.startActivity(intent);


        }else {
            if (Patterns.WEB_URL.matcher(url).matches()) {
                //符合标准
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                activity.startActivity(intent);
            }
//            ToastUtils.toast(activity,"未发现淘宝");
        }

    }



    private  static boolean isAvilible(Context context, String packageName){
        final PackageManager packageManager = context.getPackageManager();//获取packagemanager
        List< PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息

        if(pinfo != null){
            for(int i = 0; i < pinfo.size(); i++){
//                Logger.d("","packageName== "+pinfo.get(i).packageName);
               if ( packageName.equals(pinfo.get(i).packageName)){
                   return true;
               }

            }
        }
        return false;
    }














}
