package com.judian.goule.store.presenter;

import android.content.Context;
import android.util.Log;

import com.example.ccy.ccyui.util.ToastUtils;
import com.google.gson.Gson;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.SystemMsgData;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

/**
 * Created by muze on 2017/10/28.
 */

public class MyDataPresenter extends BannerPresenter {

    Context context;

    public MyDataPresenter(Context context) {
        super(context);
        initLoadDialog(context);
        this.context = context;

    }


    /**
     * 效验推荐码
     */
    public void verificationCode(String code, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("yq_code", code);
        HttpClient.getInstance(context).post(HttpAPI.VERIFICATIONCODE, params, new RawResponseHandler() {
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
            }
        });
    }

    /**
     * 取消绑定已有淘宝账号
     */
    public void changeTaobao(final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("app", "taobao");
        HttpClient.getInstance(context).post(HttpAPI.CHANGE_TAOBAO_ZHANGHAO, params, new RawResponseHandler() {
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
            }
        });
    }


    /**
     * 获取系统信息
     */
    public void getSystemMessages(int page,final BaseView<SystemMsgData> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("page", page+"");
        HttpClient.getInstance(context).post(HttpAPI.GET_SYSTEM_MESSAGES, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                //Log.i("junhao", "data" + response);
                SystemMsgData bean = new Gson().fromJson(response, SystemMsgData.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
            }
        });
    }

}
