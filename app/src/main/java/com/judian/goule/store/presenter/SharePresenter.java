package com.judian.goule.store.presenter;

import android.content.Context;

import com.example.ccy.ccyui.util.Logger;
import com.judian.goule.store.bean.ShareInfo;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.utils.GsonUtil;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

/**
 * Created by Administrator on 2017/10/30.
 */

public class SharePresenter extends BasePresenter {
    public SharePresenter(Context mContext) {
        super(mContext);
        initLoadDialog(mContext);
    }
    public void shareInfo(String num,  final BaseView<ShareInfo> view){
        params.put("num_iid",num);
        params.put("token", Token.getToken());
        Logger.d("dddddddd","shareInfo=="+num);
        HttpClient.getInstance(mContext).get(HttpAPI.SHARE, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.d("dddddddd","shareInfo=="+response);
                ShareInfo shareInfo = GsonUtil.GsonToBean(response, ShareInfo.class);
                view.result(shareInfo);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                view.error();

            }
        });
    }
}
