package com.judian.goule.store.presenter;

import android.content.Context;
import android.util.Log;

import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.google.gson.Gson;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.views.BaseView;
import com.tencent.android.tpush.XGPushConfig;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;


import java.util.HashMap;


/**
 * Created by Administrator on 2017/7/31.
 */

public class RegisterPresenter extends BasePresenter {


    Context context;

    public RegisterPresenter(Context context) {
        super(context);

        initLoadDialog(context);
        this.context = context;

    }

    /*
      注册   参数 phone 手机号 必须 pass 密码 必须 code 验证码 必须  wx_openid  微信openid  tb_openid  淘宝openid  如果用到微信、淘宝登录用

     */

    public void register(String sign__value, final BaseView<UserInfo> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).postString(HttpAPI.REGISTER, sign__value, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddddd", "register  onSuccess" + response);
                mLoadingDialog.dismiss();
                UserInfo bean = new Gson().fromJson(response.toString(), UserInfo.class);
                view.result(bean);

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                view.error();
            }
        });

    }


    public void setPass(String params, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).postString(HttpAPI.FORGET_PASSWORD, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response.toString(), BaseBean.class);
                view.result(bean);

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                view.error();
            }
        });

    }

    public void weixin(String sign__value, final BaseView<UserInfo> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).postString(HttpAPI.WEIXIN_BIND, sign__value, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                UserInfo bean = new Gson().fromJson(response.toString(), UserInfo.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                view.error();
            }
        });

    }

    public void kuaisu(String params, final BaseView<UserInfo> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).postString(HttpAPI.QUICK_LOGIN, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                UserInfo bean = new Gson().fromJson(response.toString(), UserInfo.class);
                view.result(bean);

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                view.error();
            }
        });

    }


    public void taobao(String sign__value, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).postString(HttpAPI.TAOBAO_BIND, sign__value, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                view.error();
            }
        });

    }


    /**
     * @param params 手机号 phone  验证码 code
     * @param view
     */
    public void setPassword(String params, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).postString(HttpAPI.RESET_PASS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response.toString(), BaseBean.class);
                view.result(bean);

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                view.error();
            }
        });


    }


    /*
      传密码pass二次密码repass电话phone录用

     */

    public void rePass(String params, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).postString(HttpAPI.UPDATEPASS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response.toString(), BaseBean.class);
                view.result(bean);

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                view.error();
            }
        });

    }


}
