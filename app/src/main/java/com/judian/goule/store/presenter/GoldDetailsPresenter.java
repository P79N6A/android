package com.judian.goule.store.presenter;

import android.content.Context;

import com.example.ccy.ccyui.util.Logger;
import com.google.gson.Gson;
import com.judian.goule.store.bean.GoldDetailsBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

/**
 * Created by Administrator on 2017/11/2.
 */

public class GoldDetailsPresenter extends BasePresenter {
    public GoldDetailsPresenter(Context mContext) {
        super(mContext);
        initLoadDialog(mContext);
    }
    public void getDetails(String page, final BaseView<GoldDetailsBean> view){
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("page",page);

        HttpClient.getInstance(mContext).get(HttpAPI.GOLD_DETAILS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd","getDetails"+response);
                GoldDetailsBean bean = new Gson().fromJson(response,GoldDetailsBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                view.error();
            }
        });

    }
}
