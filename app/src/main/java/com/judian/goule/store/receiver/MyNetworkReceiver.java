package com.judian.goule.store.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Administrator on 2018/3/6.
 */

public class MyNetworkReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        //**判断当前的网络连接状态是否可用*/
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ( info != null && info.isAvailable()){
            //当前网络状态可用
            int netType = info.getType();
            if (netType == ConnectivityManager.TYPE_WIFI){
                Log.e("NETSTATUE", "当前网络状态为-wifi");
            }else if (netType == ConnectivityManager.TYPE_MOBILE ){
                Log.e("NETSTATUE", "当前网络状态为-mobile");
            }
        }else {
            //当前网络不可用


            Log.e("NETSTATUE", "无网络连接");
        }
    }

}
