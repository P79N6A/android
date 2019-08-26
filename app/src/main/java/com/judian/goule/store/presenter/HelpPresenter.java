package com.judian.goule.store.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.bean.CommonlssueData;
import com.judian.goule.store.bean.HelpBean;
import com.judian.goule.store.bean.OptimizationData;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

/**
 * Created by Administrator on 2017/11/2.
 */

public class HelpPresenter extends BasePresenter {
    public HelpPresenter(Context mContext) {
        super(mContext);
        initLoadDialog(mContext);
    }

    /**
     * 广告
     */
    public void getHelp(String id, final BaseView<HelpBean> view) {
        mLoadingDialog.show();
//        params.put("column_id", id);
        HttpClient.getInstance(mContext).post(HttpAPI.HELP, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                HelpBean helpBean = new Gson().fromJson(response, HelpBean.class);
                view.result(helpBean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                view.error();
            }
        });

    }

    public void getCommonLssue(final BaseView<CommonlssueData> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        HttpClient.getInstance(mContext).post(HttpAPI.COMMONLSSUE, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                CommonlssueData helpBean = new Gson().fromJson(response, CommonlssueData.class);
                view.result(helpBean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                view.error();
            }
        });

    }

    /**
     * 优选首页
     */
    public void getYouxuan(final BaseView<OptimizationData> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        HttpClient.getInstance(mContext).post(HttpAPI.YOUXUAN, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                OptimizationData optimizationBean = new Gson().fromJson(response, OptimizationData.class);
                view.result(optimizationBean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                view.error();
            }
        });

    }
}
