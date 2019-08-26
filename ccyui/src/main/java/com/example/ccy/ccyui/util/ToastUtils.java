package com.example.ccy.ccyui.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.ccy.ccyui.view.SelfDialog;


/**
 * Created by Administrator on 2017/4/6.
 */
public class ToastUtils extends Activity {
    Context context;
    public ToastUtils(Context context) {
        this.context = context;
    }

    public static void toast(Context context, String  msg){
        if (context==null)return;
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();

    }

    public  void toast(String  msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();

    }



}
