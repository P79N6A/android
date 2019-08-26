package com.judian.goule.store.utils;


import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.judian.goule.store.R;
import com.judian.goule.store.http.HttpAPI;

/**
 * Created by chenweize on 2017/9/28.
 * 图片加载工具类，
 */
public class FrescoUtils {
    //所有图片路径前加这个
    private static final String SERVICE_URL = HttpAPI.HOST;

    /**
     * load normal  for img
     * 正常的图片 不做任何处理
     *
     * @param url
     * @param iv
     */
    public static void load(String url, SimpleDraweeView iv) {
        GenericDraweeHierarchy hierarchy = iv.getHierarchy();
        hierarchy.setFailureImage(R.drawable.ioc_errer_image_z_y);
        if (url.contains("http://") || url.contains("https://")) {
            iv.setHierarchy(hierarchy);
            iv.setImageURI(url);
        } else {
            iv.setHierarchy(hierarchy);
            iv.setImageURI(SERVICE_URL + url);
        }
    }

    /**
     * load normal  for img
     * 图片进行倒圆，和圆型，
     * tab   1  圆形  2 倒角
     *
     * @param url
     * @param iv
     * @param tab
     * @param CornerRadius 倒角半径
     */
    public static void load(String url, SimpleDraweeView iv, int tab, int CornerRadius) {
        if (url.contains("http://") || url.contains("https://")) {
            if (tab == 1) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(0);
                roundingParams.setRoundAsCircle(true);
                iv.getHierarchy().setRoundingParams(roundingParams);
                iv.setImageURI(url);
            } else if (tab == 2) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(CornerRadius);
                iv.getHierarchy().setRoundingParams(roundingParams);
                iv.setImageURI(url);
            }
        } else {
            if (tab == 1) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(0);
                roundingParams.setRoundAsCircle(true);
                iv.getHierarchy().setRoundingParams(roundingParams);
                iv.setImageURI(SERVICE_URL + url);
            } else if (tab == 2) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(CornerRadius);
                iv.getHierarchy().setRoundingParams(roundingParams);
                iv.setImageURI(SERVICE_URL + url);
            }
        }
    }

    /**
     * load normal  for img
     * 图片进行倒圆，和圆型，
     * tab   1  圆形  2 倒角
     *
     * @param url
     * @param iv
     * @param tab
     * @param CornerRadius 倒角半径
     */
    public static void load(String url, SimpleDraweeView iv, int tab, int CornerRadius, int errorID) {
        GenericDraweeHierarchy hierarchy = iv.getHierarchy();
        if (url.contains("http://") || url.contains("https://")) {
            if (tab == 1) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(0);
                roundingParams.setRoundAsCircle(true);
                hierarchy.setRoundingParams(roundingParams);
                hierarchy.setFailureImage(errorID);
                iv.setImageURI(url);
            } else if (tab == 2) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(CornerRadius);
                hierarchy.setRoundingParams(roundingParams);
                hierarchy.setFailureImage(errorID);
                iv.setImageURI(url);
            }
        } else {
            if (tab == 1) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(0);
                roundingParams.setRoundAsCircle(true);
                hierarchy.setRoundingParams(roundingParams);
                hierarchy.setFailureImage(errorID);
                iv.setImageURI(SERVICE_URL + url);
            } else if (tab == 2) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(CornerRadius);
                hierarchy.setRoundingParams(roundingParams);
                hierarchy.setFailureImage(errorID);
                iv.setImageURI(SERVICE_URL + url);
            }
        }
    }



    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

}
