package com.judian.goule.store.presenter;

import android.content.Context;

import com.example.ccy.ccyui.util.Logger;
import com.judian.goule.store.bean.ProductDetails;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.utils.GsonUtil;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

/**
 * Created by Administrator on 2017/10/30.
 */

public class ProductDetailPresenter extends BasePresenter {
    public ProductDetailPresenter(Context mContext) {
        super(mContext);
        initLoadDialog(mContext);
    }
    public void productDetails(String url,String numId, final BaseView<ProductDetails> view){
        mLoadingDialog.show();
               params.put("num_iid",numId);
               params.put("token", Token.getToken());

        HttpClient.getInstance(mContext).get(url, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.d("ddddd","response=="+response);
                ProductDetails productDetails = GsonUtil.GsonToBean(response, ProductDetails.class);
                view.result(productDetails);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                view.error();
            }
        });
    }





}
