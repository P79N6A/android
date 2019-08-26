package com.judian.goule.store.presenter;

import android.content.Context;

import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.google.gson.Gson;
import com.judian.goule.store.bean.BannerBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.views.BaseView;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;


/**
 * Created by Administrator on 2017/5/27.
 */

public class BannerPresenter extends BasePresenter {
    Context context;
    public BannerPresenter( Context context) {
        super(context);
        this.context = context;
    }


    public  void getBanner(String type, final BaseView<BannerBean> view){
          params.put("type",type);
        HttpClient.getInstance(context).get(HttpAPI.BANNER, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                BannerBean bean=new Gson().fromJson(response,BannerBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context,"网络不稳定");
                Logger.e("ddddddd","getBanner  onFailure"+error_msg);
            }
        });

    }









}
