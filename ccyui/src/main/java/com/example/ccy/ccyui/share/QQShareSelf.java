package com.example.ccy.ccyui.share;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.example.ccy.ccyui.util.Constants;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/26.
 */

public class QQShareSelf {


    private Tencent mTencent;
    private Activity mContext;
    private String scope;
    private static QQShareSelf qqShare;
    private IUiListener loginListener;

    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    private QQShareSelf(Activity context) {
        mContext = context;
//

        mTencent = Tencent.createInstance(Constants.QQAPPID, mContext);
    }

    public static QQShareSelf getInstance(Activity context) {
        if (qqShare == null) {
            qqShare = new QQShareSelf(context);
        }
        return qqShare;
    }


    public void shareToQzone(String url, ArrayList<String> imgs, String title, String msg) {
        //分享类型

        final Bundle params = new Bundle();
//          params.putString(QzoneShare.SHARE_TO_QQ_KEY_TYPE,SHARE_TO_QZONE_TYPE_IMAGE_TEXT );
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);//必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, msg);//选填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);//必填
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgs);
        mTencent.shareToQzone(mContext, params, new IUiListener() {
            @Override
            public void onComplete(Object o) {

                ToastUtils.toast(mContext, "分享成功");
                Logger.e("dddddddd", "分享成功");
            }

            @Override
            public void onError(UiError uiError) {
                Logger.e("dddddddd", "分享失败");
                ToastUtils.toast(mContext, "分享失败");
            }

            @Override
            public void onCancel() {
                Logger.e("dddddddd", "分享取消");
                ToastUtils.toast(mContext, "分享取消");
            }
        });
    }

    //    }
    public void shareToQzone(String url, ArrayList<String> imgs, String title, String msg, IUiListener listener) {
        //分享类型

        final Bundle params = new Bundle();
//          params.putString(QzoneShare.SHARE_TO_QQ_KEY_TYPE,SHARE_TO_QZONE_TYPE_IMAGE_TEXT );
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);//必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, msg);//选填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);//必填
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgs);
        mTencent.shareToQzone(mContext, params, listener);
    }

    /**
     * 分享图片组到qq空间
     */
    public void shareToQzone(String url, ArrayList<String> imgs) {
        //分享类型
        final Bundle params = new Bundle();
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "我一直用够了商城，去淘宝、京东等平台购物，下单即可查看返利");//必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "我一直用够了商城，去淘宝、京东等平台购物,免费领淘宝内部优惠券，还有高额返利拿到手软！........");//选填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);//必填
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgs);
        mTencent.shareToQzone(mContext, params, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                Log.i("tiancao","onComplete"+o.toString());
            }

            @Override
            public void onError(UiError uiError) {
                Log.i("tiancao","onError"+uiError.errorMessage);
            }

            @Override
            public void onCancel() {
                Log.i("tiancao","onCancel");
            }
        });
    }

    /**
     * 分享链接到qq空间
     */
    public void shareToQzone(String url) {
        //分享类型
        final Bundle params = new Bundle();
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "我一直用够了商城，去淘宝、京东等平台购物，下单即可查看返利");//必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "我一直用够了商城，去淘宝、京东等平台购物,免费领淘宝内部优惠券，还有高额返利拿到手软！........");//选填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);//必填
        mTencent.shareToQzone(mContext, params, new IUiListener() {
            @Override
            public void onComplete(Object o) {

            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }


    public void onClickShare(String url, String imgs, String title, String msg) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, msg);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgs);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "购了商城");
//        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  "");
        mTencent.shareToQQ(mContext, params, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                ToastUtils.toast(mContext, "分享成功");
            }

            @Override
            public void onError(UiError uiError) {
                ToastUtils.toast(mContext, "分享失败");
            }

            @Override
            public void onCancel() {
                ToastUtils.toast(mContext, "分享取消");
            }
        });
    }


    public void onClickShare(String url, String imgs, String title, String msg, IUiListener listener) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, msg);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgs);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "购了商城");
//        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  "");
        mTencent.shareToQQ(mContext, params, listener);
    }


    public void onClickShare(String url, String imgs) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "精品优惠券集合");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "我为小伙伴们特地挑选的淘宝优惠券，在这里，省到底！");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgs);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "购了商城");
//        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  "");
        mTencent.shareToQQ(mContext, params, new IUiListener() {
            @Override
            public void onComplete(Object o) {

            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }


    /**
     * 分享空间 图片
     *
     * @param uri
     */
    public void onClickShare(String uri, int op) {
        Bundle params = new Bundle();
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, uri);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "购了商城");
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        if (op == 1) {
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        }


        mTencent.shareToQQ(mContext, params, new IUiListener() {
            @Override
            public void onComplete(Object o) {

            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }


}
