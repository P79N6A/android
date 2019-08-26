package com.judian.goule.store.presenter;

import android.content.Context;
import android.util.Log;

import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.google.gson.Gson;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.CommdityData;
import com.judian.goule.store.bean.CommodityInfoData;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.MiandanInfoData;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;

/**
 * Created by muze on 2017/10/28.
 * 优选模块的数据请求
 */

public class YXDataPresenter extends BannerPresenter {

    Context context;

    public YXDataPresenter(Context context) {
        super(context);
        initLoadDialog(context);
        this.context = context;

    }

    /**
     * 获取商品信息
     */
    public void getCommodityInfo(String num_iid, final BaseView<CommodityInfoData> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("num_iid", num_iid);
        HttpClient.getInstance(context).post(HttpAPI.GETGOODSDETAILS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                CommodityInfoData bean = new Gson().fromJson(response, CommodityInfoData.class);
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
     * 获取淘宝商品详情
     */
    public void getCommodityDetails(String num_iid, final BaseView<CommdityData> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("num_iid", num_iid);
        params.put("datatype", "android");
        HttpClient.getInstance(context).post(HttpAPI.GETGOODSINFO, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                CommdityData bean = new Gson().fromJson(response, CommdityData.class);
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
     * 获取收藏列表
     */
    public void getCollectList(HashMap<String, String> params, final BaseView<GoodListBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).post(HttpAPI.GETCOLLECTLIST, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                GoodListBean bean = new Gson().fromJson(response, GoodListBean.class);
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
     * 删除收藏商品
     */
    public void deleteCollectList(String num_iid, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("num_iids", num_iid);
        HttpClient.getInstance(context).post(HttpAPI.DELETEGETCOLLECT, params, new RawResponseHandler() {
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
     * 添加收藏商品
     */
    public void getCollectCommodity(String num_iids, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("num_iid", num_iids);
        HttpClient.getInstance(context).post(HttpAPI.FAVORITEADD, params, new RawResponseHandler() {
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
     * 搜索商品
     */
    public void getMakeUrl(String ids, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("ids", ids);
        Logger.e("ddddddd", "getMakeUrl" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.GETLISTMOBILE, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getMakeUrl  onSuccess== " + response);
                try {
                    BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                    view.result(bean);
                } catch (Exception e) {
                    Logger.e("ddddddddd", "Exception =" + e);
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });

    }


    /**
     * 获取免单活动信息
     */
    public void getMiandanInfo(final BaseView<MiandanInfoData> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).post(HttpAPI.MIANDACTIVITYINFO, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
//                Log.i("tiancao", response);
                MiandanInfoData bean = new Gson().fromJson(response, MiandanInfoData.class);
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
     * 获取免单商品列表
     */
    public void getMiandanCommodityList(final BaseView<GoodListBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).post(HttpAPI.MIANDANSHANGPLIST, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                GoodListBean bean = new Gson().fromJson(response, GoodListBean.class);
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
