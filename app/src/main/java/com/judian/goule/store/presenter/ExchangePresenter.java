package com.judian.goule.store.presenter;

import android.content.Context;

import com.example.ccy.ccyui.util.Logger;
import com.google.gson.Gson;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.other.CommodityBean;
import com.judian.goule.store.views.BaseView;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

/**
 * Created by Administrator on 2017/11/8.
 */

public class ExchangePresenter extends BasePresenter {
    Context context;
    public ExchangePresenter(Context mContext) {
        super(mContext);
        context = mContext;
        initLoadDialog(mContext);
    }

    public void getCommodity(String page, final BaseView<CommodityBean> view){
        mLoadingDialog.show();
        params.put("page",page);
        HttpClient.getInstance(context).get(HttpAPI.EXCHANGE, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd","getCommodity"+response);
                CommodityBean commodityBean = new Gson().fromJson(response,CommodityBean.class);
                view.result(commodityBean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                view.error();
            }
        });
    }
}
