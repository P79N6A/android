package com.example.ccy.ccyui.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2017/3/15.
 */

public class ViewAdapterUtil {
    public final static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
    public final static int getRunAreaBallXY(Activity activity) {
        int wh = getDisplayMetrics(activity).widthPixels;
        if(wh < 500){
            wh = 10;
        }else if (wh < 1000) {
            wh = 15;
        } else if (wh <2000) {
            wh =20;
        } else {
            wh = 25;
        }
        return wh ;
    }
}
