package com.judian.goule.store.utils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.ccy.ccyui.util.Constants;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.judian.goule.store.bean.QQLoginData;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.presenter.LoginPresenter;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/26.
 */

public class QQShareSelf {


    private final LoginPresenter loginPresenter;

    public Tencent getmTencent() {
        return mTencent;
    }

    private Tencent mTencent;
    private Activity mContext;
    private String scope;
    private static QQShareSelf qqShare;
    private IUiListener loginListener;


    private QQShareSelf(Activity context) {
        mContext = context;
        //初始化腾讯
        mTencent = Tencent.createInstance(Constants.QQAPPID, mContext);
        //初始化登录器
        loginPresenter = new LoginPresenter(mContext);
    }

    public static QQShareSelf getInstance(Activity context) {
        if (qqShare == null) {
            qqShare = new QQShareSelf(context);
        }
        return qqShare;
    }

    /**
     * 返回json数据样例
     * <p>
     * {"ret":0,"pay_token":"D3D678728DC580FBCDE15722B72E7365",
     * "pf":"desktop_m_qq-10000144-android-2002-",
     * "query_authority_cost":448,
     * "authority_cost":-136792089,
     * "openid":"015A22DED93BD15E0E6B0DDB3E59DE2D",
     * "expires_in":7776000,
     * "pfkey":"6068ea1c4a716d4141bca0ddb3df1bb9",
     * "msg":"",
     * "access_token":"A2455F491478233529D0106D2CE6EB45",
     * "login_cost":499}
     */
    public void loginQQ() {
        //初始化qq主操作对象
        scope = "all";
        loginListener = new IUiListener() {
            @Override
            public void onError(UiError arg0) {
                Log.i("tiancao", "onError");
            }

            @Override
            public void onComplete(Object value) {
                Log.i("tiancao", "onComplete" + value.toString());
                if (value == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) value;
                    int ret = jo.getInt("ret");
                    if (ret == 0) {
                        ToastUtils.toast(mContext, "登录成功");
                        String openID = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");
                        mTencent.setOpenId(openID);
                        mTencent.setAccessToken(accessToken, expires);
                        QQToken token = mTencent.getQQToken();
                        UserInfo info = new UserInfo(mContext, token);
                        info.getUserInfo(new IUiListener() {
                            @Override
                            public void onComplete(Object o) {
                                try {
                                    Log.v("用户名", ((JSONObject) o).getString("nickname"));
                                    Log.v("用户姓名", ((JSONObject) o).getString("gender"));
                                    Log.v("UserInfo", o.toString());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(UiError uiError) {
                                Log.i("tiancao", "uiError" + uiError.toString());
                            }

                            @Override
                            public void onCancel() {
                                Log.i("tiancao", "onCancel");
                            }
                        });
                    }

                } catch (Exception e) {
                    Log.i("tiancao", "解析异常");
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancel() {
                Log.i("tiancao", "onError");
            }
        };

        if (!mTencent.isSessionValid()) {
            //开始qq授权登录
            mTencent.login(mContext, "all", loginListener);
        }

    }

    //分享到空间
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
                new CdataPresenter(mContext).commitApp();
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


    public void shareToQzone(String url, ArrayList<String> imgs) {
        //分享类型
        final Bundle params = new Bundle();
//          params.putString(QzoneShare.SHARE_TO_QQ_KEY_TYPE,SHARE_TO_QZONE_TYPE_IMAGE_TEXT );
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "我一直用牛八客，去淘宝、京东等平台购物，下单即可查看返利");//必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "我一直用牛八客，去淘宝、京东等平台购物,免费领淘宝内部优惠券，还有高额返利拿到手软！........");//选填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);//必填
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgs);
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
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "牛八客");
//        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  "");

        mTencent.shareToQQ(mContext, params, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                Logger.e("dddddddd", "分享成功");
                new CdataPresenter(mContext).commitApp();
                ToastUtils.toast(mContext, "分享成功");
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


    public void onClickShare(String url, String imgs) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "精品优惠券集合");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "我为小伙伴们特地挑选的淘宝优惠券，在这里，省到底！");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgs);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "牛八客");
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
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "牛八客");
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
