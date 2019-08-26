package com.judian.goule.store.presenter;

import android.content.Context;
import android.util.Log;


import com.ali.auth.third.core.model.Session;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;

import com.google.gson.Gson;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.utils.JointStringUtils;
import com.judian.goule.store.utils.RSAUtils;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.tencent.android.tpush.XGPushConfig;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.TreeMap;


/**
 * Created by Administrator on 2017/7/27.
 */

public class LoginPresenter extends BasePresenter {
    Context context;

    public LoginPresenter(Context context) {
        super(context);
        this.context = context;
        initLoadDialog(context);

    }

    public void getName(String nick_name, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        params.put("nick_name", nick_name);
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).post(HttpAPI.USERNAME, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getName" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
            }
        });

    }

    public void getlogin(String params, final BaseView<UserInfo> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).postString(HttpAPI.LOGIN, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
//                Log.i("tiancao", "登录数据" + response);
                try {
                    UserInfo bean = new Gson().fromJson(response, UserInfo.class);
                    view.result(bean);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
            }
        });
    }

    /*
           WXopenid
      */
    public void postWX(String openId, String info, final BaseView<UserInfo> view) {
        mLoadingDialog.show();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("unionid", openId);
        params.put("weixin_info", info);
        params.put("audience", XGPushConfig.getToken(context));
        params.put("source", "2");
        String sign__value = RSAUtils.runRSA(JointStringUtils.JointString(params), false);

        HttpClient.getInstance(context).postString(HttpAPI.WXOPENID, sign__value, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                UserInfo bean = new Gson().fromJson(response, UserInfo.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
            }
        });

    }

    /*
       淘宝openid
  */
    public void postTaoBao(Session session, final BaseView<UserInfo> view) {
        mLoadingDialog.show();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("openid", session.openId);
        map.put("audience", XGPushConfig.getToken(context));
        map.put("source", "2");
        String sign__value = RSAUtils.runRSA(JointStringUtils.JointString(map), false);

        params.put("nick", session.nick);
        params.put("ava", session.avatarUrl);
        params.put("sign__value", sign__value);
        HttpClient.getInstance(context).post(HttpAPI.TAOBAO, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                UserInfo bean = new Gson().fromJson(response, UserInfo.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
            }
        });

    }

    /**
     * 提交信鸽token
     */
    public void submitXGtoken(String token, String XGtoken, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("token", token);
        params.put("device_type", "android");
        params.put("device_token", XGtoken);
        HttpClient.getInstance(context).post(HttpAPI.XGTOKEN, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
            }
        });

    }
}
