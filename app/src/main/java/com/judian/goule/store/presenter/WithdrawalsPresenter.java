package com.judian.goule.store.presenter;

import android.content.Context;
import android.util.Log;

import com.example.ccy.ccyui.util.Logger;
import com.google.gson.Gson;
import com.judian.goule.store.bean.WithdrawalsBean;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

/**
 * 提现的明细， 账户明细
 */

public class WithdrawalsPresenter extends BasePresenter {

    public WithdrawalsPresenter(Context mContext) {
        super(mContext);
        initLoadDialog(mContext);
    }

    public void getWithdrawals(String url, String page, final BaseView<WithdrawalsBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("page", page);
        HttpClient.getInstance(mContext).get(url, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Log.i("tiancao", response);
                WithdrawalsBean withdrawalsBean = new Gson().fromJson(response, WithdrawalsBean.class);
                view.result(withdrawalsBean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                view.error();
                mLoadingDialog.dismiss();
            }
        });
    }
}
