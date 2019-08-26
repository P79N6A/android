package com.judian.goule.store.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.ali.auth.third.core.model.Session;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.google.gson.Gson;
import com.judian.goule.store.bean.KefuData;
import com.judian.goule.store.bean.UpgradeData;
import com.judian.goule.store.bean.UserInfo;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.bean.AreaDetailBean;
import com.judian.goule.store.bean.AreaListBean;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.BounsBean;
import com.judian.goule.store.bean.CateBean;
import com.judian.goule.store.bean.CommentBean;
import com.judian.goule.store.bean.DHBannerBean;
import com.judian.goule.store.bean.ExDetailBean;
import com.judian.goule.store.bean.FansBean;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.GoodsDetailBean;
import com.judian.goule.store.bean.GradeBean;
import com.judian.goule.store.bean.HeMainBean;
import com.judian.goule.store.bean.HomeBean;
import com.judian.goule.store.bean.HomeDataBean;
import com.judian.goule.store.bean.HotBean;
import com.judian.goule.store.bean.HotOrderBean;
import com.judian.goule.store.bean.JiFBean;
import com.judian.goule.store.bean.LimitTimeBean;
import com.judian.goule.store.bean.LiveBean;
import com.judian.goule.store.bean.LiveInfoBean;
import com.judian.goule.store.bean.LoveBean;
import com.judian.goule.store.bean.MsBean;
import com.judian.goule.store.bean.MyCommentBean;
import com.judian.goule.store.bean.MyImgBean;
import com.judian.goule.store.bean.MySBean;
import com.judian.goule.store.bean.OneOrderBean;
import com.judian.goule.store.bean.OrderDetailBean;
import com.judian.goule.store.bean.PayOrderBean;
import com.judian.goule.store.bean.SelfGoodsBean;
import com.judian.goule.store.bean.ShareImgBean;
import com.judian.goule.store.bean.ShearTaoBean;
import com.judian.goule.store.bean.SignBean;
import com.judian.goule.store.bean.TKLBean;
import com.judian.goule.store.bean.TaoOrderBean;
import com.judian.goule.store.bean.TaoTokenBean;
import com.judian.goule.store.bean.TeamMxBean;
import com.judian.goule.store.bean.XuBean;
import com.judian.goule.store.bean.YingBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/10/28.
 */

public class CdataPresenter extends BannerPresenter {

    Context context;

    public CdataPresenter(Context context) {
        super(context);
        initLoadDialog(context);
        this.context = context;

    }


    /**
     * 删除地址
     */
    public void getDelAddress(String id, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        params.put("address_id", id);
        HttpClient.getInstance(context).post(HttpAPI.DELETEADDRESS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getVersions  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });
    }

    /**
     * 删除地址
     */
    public void getAddressDet(String id, final BaseView<AreaDetailBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        params.put("address_id", id);
        HttpClient.getInstance(context).post(HttpAPI.GETADDRESSDETAIL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getVersions  onSuccess" + response);
                AreaDetailBean bean = new Gson().fromJson(response, AreaDetailBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });
    }


    /**
     * 添加地址
     */
    public void getsaveArea(HashMap<String, String> params, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        HttpClient.getInstance(context).post(HttpAPI.ADDADDRESS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getVersions  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });

    }

    /**
     * 修改地址
     */
    public void getsUpArea(HashMap<String, String> params, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        HttpClient.getInstance(context).post(HttpAPI.UPDATEADDRESS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getVersions  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });

    }


    public void getAddNum(String num_iid) {
        params.put("token", Token.getToken());
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.ADD_CLICK_NUM + "?num_iid=" + num_iid, null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getKitInfo" + response);

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getKitInfo  onFailure" + error_msg);
            }
        });

    }


    /*
版本更新
*/
    public void getVersions(String ver, final BaseView<UpgradeData> view) {
        params.put("versions", ver);
        HttpClient.getInstance(context).post(HttpAPI.CONTROL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                try {
                    UpgradeData bean = new Gson().fromJson(response, UpgradeData.class);
                    view.result(bean);
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                view.error();
            }
        });
    }

    public void getKefu(final BaseView<KefuData> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).get(HttpAPI.KEFU, null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                KefuData bean = new Gson().fromJson(response, KefuData.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
            }
        });
    }

    public void getGoodsDet(String id, final BaseView<GoodsDetailBean> view) {

        params.put("token", Token.getToken());
        params.put("id", id);
        HttpClient.getInstance(context).get(HttpAPI.ONEGOODS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getGoodsDet  onSuccess" + response);
                GoodsDetailBean bean = new Gson().fromJson(response, GoodsDetailBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getGoodsDet  onFailure" + error_msg);
            }
        });
    }








       /*
       进入商品订单页
      */

    public void getOrderDatail(String goods_id, final BaseView<OneOrderBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("goods_id", goods_id);

        HttpClient.getInstance(context).post(HttpAPI.MAKEORDER, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getOrderDatail  onSuccess" + response);
                OneOrderBean bean = new Gson().fromJson(response, OneOrderBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getOrderDatail  onFailure" + error_msg);
            }
        });
    }

   /*
       进入商品订单页
      */

    public void getAreaList(int page, final BaseView<AreaListBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("page", page + "");
        HttpClient.getInstance(context).post(HttpAPI.GETADDRESS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getAreaList  onSuccess" + response);
                AreaListBean bean = new Gson().fromJson(response, AreaListBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getAreaList  onFailure" + error_msg);
            }
        });
    }


    /**
     * 提交订单
     */
    public void getCommitOrder(HashMap<String, String> params, final BaseView<PayOrderBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        HttpClient.getInstance(context).post(HttpAPI.PAYORDER, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getAreaList  onSuccess" + response);
                PayOrderBean bean = new Gson().fromJson(response, PayOrderBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getAreaList  onFailure" + error_msg);
            }
        });

    }

    /**
     * 限时时间
     */
    public void getLimitTime(final BaseView<LimitTimeBean> view) {
        HttpClient.getInstance(context).get(HttpAPI.XIANSHI_TIME, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getLimitTime  onSuccess" + response);
                LimitTimeBean bean = new Gson().fromJson(response, LimitTimeBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getLimitTime  onFailure" + error_msg);
            }
        });


    }


    /**
     * 限时商品列表
     */
    public void getLimitGoods(String id, int page, final BaseView<GoodListBean> view) {
        mLoadingDialog.show();
        params.put("id", id);
        params.put("page", page + "");
        params.put("token", Token.getToken());
        Logger.e("ddddddd", "getLimitGoods" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.XIANSHI_GOODS_LIST, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getLimitGoods  onSuccess" + response);
                GoodListBean bean = new Gson().fromJson(response, GoodListBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getLimitGoods  onFailure" + error_msg);
            }
        });

    }


    /**
     * 关联订单
     */
    public void getBindOrder(String order_id, String type, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        params.put("order_jmsn", order_id);
        params.put("type", type);
        HttpClient.getInstance(context).get(HttpAPI.BINDORDER, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getBindOrder  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getBindOrder  onFailure" + error_msg);
            }
        });

    }

    /**
     * 关联订单
     */
    public void getBindOrderAll(String order_id) {
        params.put("token", Token.getToken());
        params.put("orderall", order_id);
        HttpClient.getInstance(context).get(HttpAPI.GETORDERALL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getBindOrderAll  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                if (bean.getCode() != 200) ToastUtils.toast(context, bean.getMsg());

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getBindOrderAll  onFailure" + error_msg);
            }
        });

    }

    /**
     * 关联订单
     */
    public void getBindOrderAllJD(String order_id) {
        params.put("token", Token.getToken());
        params.put("orderall", order_id);
        HttpClient.getInstance(context).get(HttpAPI.GETORDERALL_JD, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getBindOrderAllJD  onSuccess" + response);

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getBindOrderAllJD  onFailure" + error_msg);
            }
        });

    }

    /**
     * juhuasuan
     */
    public void getJuhs(final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        Logger.e("ddddddd", "getjuhs  Token.getToken()" + Token.getToken());
        HttpClient.getInstance(context).get(HttpAPI.JUHUASUAN, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getjuhs response" + response);
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
                view.error();
            }
        });

    }

    /*
版本更新
*/
    public void getHot(final BaseView<HotBean> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).post(HttpAPI.HOTSO, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                HotBean bean = new Gson().fromJson(response, HotBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });
    }


    /**
     * 首页商品
     */
    public void getHomeData(String cate_id, int page, final BaseView<HomeDataBean> view) {
        params.put("cate_id", cate_id);
        params.put("page", page + "");
        mLoadingDialog.show();
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.GOODS_ALL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                HomeDataBean bean = new Gson().fromJson(response, HomeDataBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });

    }


    /**
     * 首页商品
     */
    public void getHeMain(String user_id, int page, final BaseView<HeMainBean> view) {
        params.put("user_id", user_id);
        params.put("page", page + "");
        mLoadingDialog.show();
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.COMMENT_INDEX, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                HeMainBean bean = new Gson().fromJson(response, HeMainBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });

    }


    /**
     * 直播间
     */
    public void getLive(String last_id, final BaseView<LiveBean> view) {
        params.put("last_id", last_id + "");
        Logger.e("ddddddd", "getLive  last_id" + params.toString());
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.LIVE, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getLive  onSuccess" + response);

                LiveBean bean = new Gson().fromJson(response, LiveBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
//                ToastUtils.toast(context,"网络不稳定");
                Logger.e("ddddddd", "getLive  onFailure" + error_msg);
            }
        });

    }


    /**
     * 直播间  历史数据
     */
    public void getLiveHistory(String time, final BaseView<LiveBean> view) {
        params.put("start_time", time + "");
        Logger.e("ddddddd", "getLiveHistory  onSuccess" + params);
        HttpClient.getInstance(context).get(HttpAPI.LIVE, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getLiveHistory  onSuccess" + response);

                LiveBean bean = new Gson().fromJson(response, LiveBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getLiveHistory  onFailure" + error_msg);
            }
        });

    }


    /**
     * 上传头像
     */
//    public  void setImg(File file, final BaseView<BaseBean> view){
//        params.put("token",Token.getToken());
////        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
//        HttpClient.getInstance(context).upload(HttpAPI.IMG, params, "avatar",file , new GsonResponseHandler<BaseBean>() {
//            @Override
//            public void onSuccess(int statusCode, BaseBean response) {
//                view.result(response);
//            }
//
//
//            @Override
//            public void onFailure(int statusCode, String error_msg) {
//                ToastUtils.toast(context,"网络不稳定");
//                Logger.e("ddddddd","setImg  onFailure"+error_msg);
//            }
//        });
//
//    }


    /**
     * 上传头像
     */
    public void setImg(File file, final BaseView<BaseBean> view) throws FileNotFoundException {
        RequestParams params = new RequestParams();
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("avatar", file);
        Logger.e("ddddddd", "setImg" + params.toString());
        com.example.ccy.ccyui.http.HttpClient.getInstance().post(HttpAPI.HOST + HttpAPI.IMG, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Logger.e("ddddddd", "setImg" + response);
                mLoadingDialog.dismiss();
                BaseBean baseBean = new Gson().fromJson(response.toString(), BaseBean.class);
                view.result(baseBean);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "setImg  onFailure" + responseString);
                ToastUtils.toast(context, "网络不稳定");
            }
        });

    }


    /**
     * 提交晒单
     */
    public void commitOrder(File[] files, final BaseView<BaseBean> view) throws FileNotFoundException {
        RequestParams params = new RequestParams();
        params.put("token", Token.getToken());
        params.put("files", files);
        mLoadingDialog.show();
        Logger.e("ddddddd", "commitOrder" + params.toString());
        com.example.ccy.ccyui.http.HttpClient.getInstance().post(HttpAPI.HOST + HttpAPI.INDEX, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Logger.e("ddddddd", "commitOrder" + response);
                mLoadingDialog.dismiss();
//                BaseBean  baseBean=new Gson().fromJson(response.toString(),BaseBean.class);
//                 view.result(baseBean);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "setImg  onFailure" + responseString);
                ToastUtils.toast(context, "网络不稳定");
            }
        });

    }

    /**
     * 提交晒单
     */
    public void commitOrder(File[] files, String title, String txt, String order_id, String img, final BaseView<BaseBean> view) throws FileNotFoundException {
        RequestParams params = new RequestParams();
        params.put("token", Token.getToken());
//         params.put("token","46b778e80d944723276abdb50c2a50528e1f4113c58bd9b945ae717a45bb14bc");
        params.put("files[]", files);
        params.put("title", title);
        params.put("order_id", order_id);
        params.put("content", txt);
        params.put("img", img);
        mLoadingDialog.show();
        Logger.e("ddddddd", "commitOrder          " + params.toString());
        com.example.ccy.ccyui.http.HttpClient.getInstance().post(HttpAPI.HOST + HttpAPI.INDEX, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "commitOrder" + response);
                BaseBean baseBean = new Gson().fromJson(response.toString(), BaseBean.class);
                view.result(baseBean);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Logger.e("ddddddd", "setImg  onFailure" + responseString);
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
            }
        });

    }


    /**
     * 我的余额 （金币）
     */
    public void getKitInfo(final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        HttpClient.getInstance(context).get(HttpAPI.REBATE_TIXIAN, params, new RawResponseHandler() {
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
     * 红包记录
     */
    public void getBounsList(String type, int page, final BaseView<BounsBean> view) {
        params.put("token", Token.getToken());
        params.put("user_status", type);
        params.put("page", page + "");
        mLoadingDialog.show();
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.RED_ENVELOPE, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getBounsList" + response);
                mLoadingDialog.dismiss();
                BounsBean bean = new Gson().fromJson(response, BounsBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getBounsList  onFailure" + error_msg);
            }
        });

    }


    /**
     * 我的
     */
    public void getZanList(int page, final BaseView<MyCommentBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        params.put("page", page + "");
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.ZAN_LIST, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getZanList" + response);
                mLoadingDialog.dismiss();
                MyCommentBean bean = new Gson().fromJson(response, MyCommentBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });

    }

    /**
     * 首页走势
     */
    public void getHome(final BaseView<HomeBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        HttpClient.getInstance(context).get(HttpAPI.INCOME, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                HomeBean bean = new Gson().fromJson(response, HomeBean.class);
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
     * 提现
     */
    public void getKit(String gold, String ali_account, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("gold", gold);
        params.put("ali_account", ali_account);
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).post(HttpAPI.TIXIAN, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getKit" + response);
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });

    }

    /**
     * 修改昵称
     */
    public void getName(String nick_name, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        params.put("nick_name", nick_name);
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).post(HttpAPI.USERNAME, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getName" + response);
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });

    }

    /**
     * 修改昵称
     */
    public void saveSex(String sex, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        params.put("sex", sex);
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.EDIT_SEX, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "saveSex" + response);
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "saveSex  onFailure" + error_msg);
            }
        });

    }


    /**
     * 退出登录
     */
    public void getLogout(final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        HttpClient.getInstance(context).post(HttpAPI.LOGINOUT, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
//                Log.i("tiancao", response);
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
     * 提现  类型1-注册 2-找回密码 3-修改手机号 4-修改密码 5-提现
     */
    public void getCode(String phone, String type, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("phone", phone);
        params.put("type", type);
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).post(HttpAPI.SEND, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getCode  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                mLoadingDialog.dismiss();
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getCode  onFailure" + error_msg);
            }
        });

    }

    /**
     * 旧手机号
     */
    public void getCodeOdl(String sign__value, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("sign__value", sign__value);
        HttpClient.getInstance(context).post(HttpAPI.PHONE_VERITY, params, new RawResponseHandler() {
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
     * 积分 轮播图
     */
    public void getDhLun(String type, final BaseView<DHBannerBean> view) {
        mLoadingDialog.show();
        params.put("type", type);
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.GOODS_LUN, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getDhLun  onSuccess" + response);
                DHBannerBean bean = new Gson().fromJson(response, DHBannerBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getDhLun  onFailure" + error_msg);
            }
        });

    }

    /**
     * 聊天室背景图
     */
    public void getLiveBg() {
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.LIVE_BANNER, null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getLiveBg  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                if (bean.getCode() == 200) {
                    Picasso.with(context).load(bean.getResult().getFriend_url()).into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });

                }
//                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
//                ToastUtils.toast(context,"网络不稳定");
                Logger.e("ddddddd", "getLiveBg  onFailure" + error_msg);
            }
        });

    }


    /**
     * 聊天室背景图
     */
    public void getHomeAD(final BaseView<BaseBean> view) {
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.SHOWAD, null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getHomeAD  onSuccess" + response);

                BaseBean bean = new Gson().fromJson(response, BaseBean.class);

                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
//                ToastUtils.toast(context,"网络不稳定");
                Logger.e("ddddddd", "getHomeAD  onFailure" + error_msg);
                view.error();
            }
        });

    }


    /**
     * 淘口令
     */
    public void getTaokou(String key, String txt, final BaseView<TKLBean> view) {
        params.put("tkl", key);
        params.put("title", txt);
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).get(HttpAPI.TAOKOULING, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                TKLBean bean = new Gson().fromJson(response, TKLBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
            }
        });

    }


    /**
     * 提交补单
     */
    public void getCommOrder(String order, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("order_id", order);
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).post(HttpAPI.ORDER_BIND, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getCommOrder  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getCommOrder  onFailure" + error_msg);
            }
        });

    }


    /**
     * 旧手机号
     */
    public void getChangeTel(String sign__value, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("sign__value", sign__value);
        HttpClient.getInstance(context).post(HttpAPI.USER, params, new RawResponseHandler() {
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
     * 咚咚币兑换 商品详情
     */
    public void getExdetail(String goods_id, final BaseView<ExDetailBean> view) {
        mLoadingDialog.show();
//        params.put("token",Token.getToken());
        params.put("goods_id", goods_id);
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.GOODS_DETAIL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getExdetail  onSuccess" + response);
                ExDetailBean bean = new Gson().fromJson(response, ExDetailBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getExdetail  onFailure" + error_msg);
            }
        });

    }


    /**
     * 签到记录
     */
    public void getSignList(final BaseView<SignBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();

        Logger.e("ddddddd", "getSignList  ");
        HttpClient.getInstance(context).get(HttpAPI.SIGN_LOG, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getSignList  onSuccess" + response);
                SignBean bean = new Gson().fromJson(response, SignBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getSignList  onFailure" + error_msg);
            }
        });

    }

    /**
     * 团队明细
     */
    public void getMxList(int page, String type, final BaseView<TeamMxBean> view) {
        params.put("token", Token.getToken());
        params.put("page", page + "");
        params.put("type", type);
        mLoadingDialog.show();
        HttpClient.getInstance(context).get(HttpAPI.MY_TEAM_LIST, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
//                Log.i("tiancao", "数据：" + response);
                TeamMxBean bean = new Gson().fromJson(response, TeamMxBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getMxList  onFailure" + error_msg);
            }
        });

    }

    /**
     * 拍行榜
     */
    public void getPaiList(int page, final String type, final BaseView<TeamMxBean> view) {
        if (page > 1) return;
        params.put("token", Token.getToken());
        params.put("page", page + "");
        params.put("type", type);

        mLoadingDialog.show();

//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        Logger.e("ddddddd", "getPaiList  page1" + page);
        HttpClient.getInstance(context).get(HttpAPI.RANK_LIST, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getPaiList  onSuccess" + response);

                try {
                    TeamMxBean bean = new Gson().fromJson(response, TeamMxBean.class);
                    view.result(bean);
                } catch (Exception e) {
                    Logger.e("ddddddd", "getPaiList  Exception" + e);
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getPaiList  onFailure" + error_msg);
            }
        });

    }


    /**
     * 我的晒单
     */
    public void getMysorder(String type, int page, final BaseView<MySBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
//        params.put("token","46b778e80d944723276abdb50c2a50528e1f4113c58bd9b945ae717a45bb14bc");
        params.put("page", page + "");
        params.put("type", type + "");
        Logger.e("ddddddd", "getMysorder  onSuccess" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.ME_COMMENT, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getMysorder  onSuccess" + response);
                MySBean bean = new Gson().fromJson(response, MySBean.class);
                view.result(bean);
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
     * 成员人数
     */
    public void getMeTeam(final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        HttpClient.getInstance(context).get(HttpAPI.ME_TEAM, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getMeTeam  onSuccess" + response);
                try {
                    BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                    view.result(bean);
                } catch (Exception e) {
                    Logger.e("ddddddd", "getMeTeam  Exception" + e);
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getMeTeam  onFailure" + error_msg);
            }
        });

    }


    /**
     * 分享朋友圈
     */
    public void getShare(final BaseView<ShareImgBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();

//        Logger.e("ddddddd"type"getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.SHARE_BILL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getShare  onSuccess" + response);

                try {
                    ShareImgBean bean = new Gson().fromJson(response, ShareImgBean.class);
                    view.result(bean);
                } catch (Exception e) {
                    Logger.e("ddddddd", "  Exception" + e);
                }


            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getShare  onFailure" + error_msg);
            }
        });

    }

    public void getShare2(final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();

//        Logger.e("ddddddd"type"getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.SHARE_BILL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getShare  onSuccess" + response);

                try {
                    BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                    view.result(bean);
                } catch (Exception e) {
                    Logger.e("ddddddd", "  Exception" + e);
                }


            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getShare  onFailure" + error_msg);
            }
        });

    }


    /**
     * 分享朋友圈
     */
    public void getShareAPP(final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();

//        Logger.e("ddddddd"type"getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.SHAREAPP, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getShare  onSuccess" + response);

                try {
                    BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                    view.result(bean);
                } catch (Exception e) {
                    Logger.e("ddddddd", "  Exception" + e);
                }


            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getShare  onFailure" + error_msg);
            }
        });

    }


    /**
     * 最新晒单  最热
     */
    public void getHotOrder(String type, int page, final BaseView<HotOrderBean> view) {
        params.put("page", page + "");
        mLoadingDialog.show();
        params.put("type", type + "");
        params.put("token", Token.getToken());
//        Logger.e("ddddddd"type"getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.HOT_COMMENT, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHotOrder  onSuccess" + response);
                try {
                    HotOrderBean bean = new Gson().fromJson(response, HotOrderBean.class);
                    view.result(bean);
                } catch (Exception e) {
                    Logger.e("ddddddd", "getHotOrder  Exception" + e);
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
     * 最新晒单  最热
     */
    public void getOtherOrder(String cid, int page, final BaseView<HotOrderBean> view) {
        params.put("page", page + "");
        mLoadingDialog.show();
        params.put("cid", cid + "");
//        Logger.e("ddddddd"type"getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.OTHER_COMMENT, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getOtherOrder  onSuccess" + response);
                HotOrderBean bean = new Gson().fromJson(response, HotOrderBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getOtherOrder  onFailure" + error_msg);
            }
        });

    }


    /**
     * 立即下单
     */
    public void getGoPay(String num, String goods_id, String cut, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        params.put("goods_id", goods_id);
        mLoadingDialog.show();
        params.put("cut", cut);
        params.put("num", num);
//        Logger.e("ddddddd"type"getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).post(HttpAPI.GOODS_EXCHANGE, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getGoPay  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getGoPay  onFailure" + error_msg);
            }
        });

    }


    /**
     * 最新晒单  最热
     */
    public void getComDetail(String cid, String goonId, final BaseView<OrderDetailBean> view) {
        params.put("cid", cid + "");
        params.put("num_iid", goonId + "");
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        Logger.e("ddddddd", "getComDetail  params" + params.toString());
//        Logger.e("ddddddd"type"getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.COMMENT_DETAIL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getComDetail  onSuccess" + response);


                try {
                    OrderDetailBean bean = new Gson().fromJson(response, OrderDetailBean.class);
                    view.result(bean);
                } catch (Exception e) {
                    Logger.e("ddddddd", "getComDetail  Exception" + e);
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getComDetail  onFailure" + error_msg);
            }
        });

    }


    /**
     * 立即签到
     */
    public void getSign(final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.SIGNIN_REWARD, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
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
     * 会员等级申请
     */
    public void getGrade(final BaseView<GradeBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        HttpClient.getInstance(context).get(HttpAPI.UPGRADE_USER, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                try {
                    GradeBean bean = new Gson().fromJson(response, GradeBean.class);
                    view.result(bean);
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
            }
        });

    }


    /**
     * vip 升级为合伙人
     */
    public void getGradePay(String yxzf, String jjfs, String ztdd, String yjzh, final BaseView<BaseBean> view) {
        params.put("yxzf", yxzf);
        params.put("jjfs", jjfs);
        params.put("ztdd", ztdd);
        params.put("yjzh", yjzh);
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        HttpClient.getInstance(context).get(HttpAPI.UPGRADE_USER_PAY_MONEY, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                view.error();
            }
        });

    }


    /**
     * 搜索链接
     */
    public void getSUrl(String keyword, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        params.put("keyword", keyword);
        mLoadingDialog.show();
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.TB_SEARCH_GET_URL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getSUrl  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getSUrl  onFailure" + error_msg);
            }
        });

    }


    /**
     * 晒单点赞
     */
    public void getComment(String cid, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        params.put("cid", cid);

        mLoadingDialog.show();
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).get(HttpAPI.CLICK_ZAN, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getComment  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
                view.error();
            }
        });

    }

    /**
     * 晒单点赞
     */
    public void saveComment(String cid, String content, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        params.put("cid", cid);
        params.put("content", content);
        Logger.e("ddddddd", "saveComment  params" + params.toString());
        mLoadingDialog.show();
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).post(HttpAPI.SAVE_COMMENT, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "saveComment  onSuccess" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "saveComment  onFailure" + error_msg);
                view.error();
            }
        });

    }

    /**
     * 晒单评论列表
     */
    public void getCommentList(String cid, int page, final BaseView<CommentBean> view) {
        params.put("token", Token.getToken());
        params.put("cid", cid);
        params.put("page", page + "");

        mLoadingDialog.show();
        Logger.e("ddddddd", "getCommentList  params" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.GET_COMMENT_LIST, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getCommentList  onSuccess" + response);
                CommentBean bean = new Gson().fromJson(response, CommentBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getCommentList  onFailure" + error_msg);
                view.error();
            }
        });

    }


    /**
     * 绑定支付宝
     */
    public void addAli(String name, String ali_account, String captcha, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        params.put("name", name);
        mLoadingDialog.show();
        params.put("ali_account", ali_account);
        params.put("captcha", captcha);
//        Logger.e("ddddddd","getBanner"+ HttpAPI.HOST+HttpAPI.BANNER);
        HttpClient.getInstance(context).post(HttpAPI.ALIPAY, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
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
     * 商品下 分享
     */
    public void getShareGoods(final BaseView<GoodListBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).get(HttpAPI.SHARE_GOODS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                GoodListBean bean = new Gson().fromJson(response, GoodListBean.class);
                view.result(bean);
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
     * 商品下 分享
     */
    public void getTaoToken(String num_iid, final BaseView<TaoTokenBean> view) {
        params.put("token", Token.getToken());
        params.put("num_iid", num_iid);
        mLoadingDialog.show();
        Logger.e("ddddddd", "getTaoToken  params" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.GET_TAOTOKEN, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getTaoToken  onSuccess" + response);
                TaoTokenBean bean = new Gson().fromJson(response, TaoTokenBean.class);
                view.result(bean);
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
     * 直播间  分享
     */
    public void getLiveShare(String num_iid, final BaseView<ShearTaoBean> view) {
        params.put("token", Token.getToken());
        params.put("id", num_iid);
        mLoadingDialog.show();
        Logger.e("ddddddd", "getTaoToken  params" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.LIVE_COPY, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getTaoToken  onSuccess" + response);
                ShearTaoBean bean = new Gson().fromJson(response, ShearTaoBean.class);
                view.result(bean);
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
     * 直播间  一键copy
     */
    public void getLiveCopy(String num_iid, final BaseView<TaoTokenBean> view) {
        params.put("token", Token.getToken());
        params.put("id", num_iid);
        mLoadingDialog.show();
        Logger.e("ddddddd", "getTaoToken  params" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.LIVE_MV, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getTaoToken  onSuccess" + response);
                TaoTokenBean bean = new Gson().fromJson(response, TaoTokenBean.class);
                view.result(bean);
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
     * 直播间  分享
     */
    public void getLiveInfo(final BaseView<LiveInfoBean> view) {

        HttpClient.getInstance(context).get(HttpAPI.LINE_INFO, null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getLiveShare  onSuccess" + response);
                LiveInfoBean bean = new Gson().fromJson(response, LiveInfoBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getLiveShare  onFailure" + error_msg);
            }
        });

    }

    /**
     * 直播间  分享
     */
    public void getMeImg(final BaseView<MyImgBean> view) {

        HttpClient.getInstance(context).get(HttpAPI.BANNER_ME, null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getMeImg  onSuccess" + response);
                MyImgBean bean = new Gson().fromJson(response, MyImgBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getMeImg  onFailure" + error_msg);
            }
        });

    }


    /**
     * 我的收藏
     */
    public void getLove(int page, final BaseView<LoveBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        params.put("page", page + "");
        HttpClient.getInstance(context).post(HttpAPI.I_LOVE, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getLove  onSuccess" + response);
                LoveBean bean = new Gson().fromJson(response, LoveBean.class);
                view.result(bean);
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
     * 宫格
     */
    public void getGg(final BaseView<XuBean> view) {
        mLoadingDialog.show();

        HttpClient.getInstance(context).get(HttpAPI.APP_DG_INDEX, null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getGg  onSuccess== " + response);
                Token.setGg(response);
                XuBean bean = new Gson().fromJson(response, XuBean.class);
                view.result(bean);
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
     * 添加收藏
     */
    public void addLove(String num_iid, final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        params.put("num_iid", num_iid);
        HttpClient.getInstance(context).post(HttpAPI.ADD_COLLECT, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
            }
        });

    }


    /**
     * 分类
     */
    public void getCate(final BaseView<CateBean> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).get(HttpAPI.CATEGORY, null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                CateBean bean = new Gson().fromJson(response, CateBean.class);
                Token.setCate(response);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
            }
        });

    }

    /**
     * jd分类
     */
    public void getJDCate(final BaseView<CateBean> view) {
        mLoadingDialog.show();

        HttpClient.getInstance(context).get(HttpAPI.JDCATE, null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                CateBean bean = new Gson().fromJson(response, CateBean.class);
                Logger.e("ddddddd", "getJDCate  onSuccess" + response);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getJDCate  onFailure" + error_msg);
            }
        });
    }

    /**
     * 花钱商品
     */
    public void getSpendData(String url, String cate_id, String type, int page, final BaseView<GoodListBean> view) {
        params.put("cate", cate_id);
        params.put("page", page + "");
        params.put("type", type);
        params.put("token", Token.getToken());
//        Log.i("tiancao", "页数：" + page);
        HttpClient.getInstance(context).get(url, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                GoodListBean bean = new Gson().fromJson(response, GoodListBean.class);
                /*if (bean.getResult() != null) {
                    Log.i("tiancao", "有多少个商品" + bean.getResult().size());
                } else {
                    Log.i("tiancao", "没有商品");
                }*/
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                view.error();
            }
        });
    }

    /**
     * 优选 ， 查询商品
     */
    public void getOptimizationCommodityListData(String cate_id, int page, final BaseView<GoodListBean> view) {
        params.put("two_cate", cate_id);
        params.put("page", page + "");
        params.put("token", Token.getToken());
//        Log.i("tiancao", "页数：" + page);
        HttpClient.getInstance(context).get(HttpAPI.OPTIMIZATION, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
//                Log.i("tiancao", "data" + response);
                mLoadingDialog.dismiss();
                GoodListBean bean = new Gson().fromJson(response, GoodListBean.class);
                /*if (bean.getResult() != null) {
                    Log.i("tiancao", "有多少个商品" + bean.getResult().size());
                } else {
                    Log.i("tiancao", "没有商品");
                }*/
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                view.error();
            }
        });
    }

    /**
     * 花钱商品
     */
    public void getSelfData(String url, String cate_id, String type, int page, final BaseView<SelfGoodsBean> view) {
        params.put("cate", cate_id);
        params.put("page", page + "");
        params.put("type", type);
//        mLoadingDialog.show();
        Logger.e("ddddddd", "getSpendData" + params.toString());
        HttpClient.getInstance(context).get(url, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getSpendData  onSuccess== " + response);
                mLoadingDialog.dismiss();
                SelfGoodsBean bean = new Gson().fromJson(response, SelfGoodsBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getSpendData  onFailure" + error_msg);
            }
        });

    }

    /**
     * 花钱商品
     */
    public void getZanNum(final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        Logger.e("ddddddd", "getSpendData" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.ZAN_TOTAL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getZanNum  onSuccess== " + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getZanNum  onFailure" + error_msg);
            }
        });

    }

    /**
     * 我要提现
     */
    public void getWiatKit(final BaseView<BaseBean> view) {
        params.put("token", Token.getToken());
        Logger.e("ddddddd", "getSpendData" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.WITHDRAW, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getZanNum  onSuccess== " + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getZanNum  onFailure" + error_msg);
            }
        });

    }


    /**
     * 花钱商品
     */
    public void getHotHome(int page, final BaseView<XuBean> view) {
//        params.put("page",page+"");
        mLoadingDialog.show();
        Logger.e("ddddddd", "getHotHome" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.HOTHOME, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getHotHome  onSuccess== " + response);
                mLoadingDialog.dismiss();
                XuBean bean = new Gson().fromJson(response, XuBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getMastData  onFailure" + error_msg);
            }
        });

    }

    /**
     * 自购订单
     */
    public void getSelfOrder(int page, final BaseView<TaoOrderBean> view) {
        params.put("page", page + "");
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        Logger.e("ddddddd", "getSelfOrder" + params.toString());
        HttpClient.getInstance(context).get(HttpAPI.LISTSELFORDER, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getSelfOrder  onSuccess== " + response);
                mLoadingDialog.dismiss();
                try {
                    TaoOrderBean bean = new Gson().fromJson(response, TaoOrderBean.class);
                    view.result(bean);
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getSelfOrder  onFailure" + error_msg);
            }
        });

    }


    /**
     * 搜索商品
     */
    public void getSearch(String url, HashMap<String, String> params, final BaseView<GoodListBean> view) {
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).get(url, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                try {
                    GoodListBean bean = new Gson().fromJson(response, GoodListBean.class);
                    view.result(bean);
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
            }
        });

    }

    /**
     * 搜索商品（分享赚）
     */
    public void getMakeSearch(HashMap<String, String> params, final BaseView<GoodListBean> view) {
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).get(HttpAPI.TB_SEARCH_QHXJ, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                try {
                    GoodListBean bean = new Gson().fromJson(response, GoodListBean.class);
                    view.result(bean);
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
            }
        });

    }


    /**
     * 获取分享链接
     */
    public void getMakeUrl(String ids, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("ids", ids);
        HttpClient.getInstance(context).get(HttpAPI.GETLISTMOBILE, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                try {
                    BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                    view.result(bean);
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
            }
        });

    }


    /**
     * 商品列表
     */
    public void getGoodList(String url, int page, final BaseView<GoodListBean> view) {
        mLoadingDialog.show();
        params.put("page", page + "");
        params.put("token", Token.getToken());
        Logger.e("ddddddd", "getGoodList" + params.toString());
        HttpClient.getInstance(context).getNull(url, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getGoodList  onSuccess== " + response);
                GoodListBean bean = new Gson().fromJson(response, GoodListBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
            }
        });

    }

    /**
     * 花钱商品
     */
    public void getMs(final BaseView<MsBean> view) {
        mLoadingDialog.show();
        HttpClient.getInstance(context).get(HttpAPI.GENERALLY, null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getMs  onSuccess" + response);
                MsBean bean = new Gson().fromJson(response, MsBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getMs  onFailure" + error_msg);
                view.error();
            }
        });
    }


    /**
     * 首页分类
     */
    public void getMs3(final BaseView<MsBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).get(HttpAPI.ICOINFO, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                MsBean bean = new Gson().fromJson(response, MsBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                view.error();
            }
        });
    }


    /**
     * 花钱商品
     */
    public void commitApp() {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).get(HttpAPI.SHAREAPPSUCCESS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "initTeday  onSuccess" + response);

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
     *
     */
    public void commitOrder() {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).get(HttpAPI.TODAYSHASUCCESS, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "initTeday  onSuccess" + response);

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
     * 花钱商品
     */
    public void getInt(final BaseView<JiFBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).get(HttpAPI.GET_INTEGRAL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getInt  onSuccess" + response);
                JiFBean bean = new Gson().fromJson(response, JiFBean.class);
                view.result(bean);

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getInt  onFailure" + error_msg);
                view.error();
            }
        });

    }


    /**
     * 赚钱
     */
    public void getMake(final BaseView<UserInfo> view) {
        params.put("token", Token.getToken());
        Logger.e("ddddddd", "getMake  Token.getToken()" + Token.getToken());
        HttpClient.getInstance(context).post(HttpAPI.MAKE_MONEY, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getMake response" + response);
                UserInfo bean = new Gson().fromJson(response, UserInfo.class);
                if (bean.getCode().equals("200") && bean.getResult().getAli_account() != null) {
                    MyApplication.ali = bean.getResult().getAli_account();

                }
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
                view.error();
            }
        });

    }

    /**
     * 个人信息
     */
    public void getUserInfo(final BaseView<UserInfo> view) {
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).post(HttpAPI.MY_INFO, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
//                Log.i("tiancao", "用户信息：" + response);
                UserInfo bean = new Gson().fromJson(response, UserInfo.class);
                if (bean.getCode().equals("200")) {
                    if (bean.getResult().getAli_account() != null)
                        MyApplication.ali = bean.getResult().getAli_account();
                    Token.setPhone(bean.getResult().getPhone());
                }
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                view.error();
            }
        });

    }

    /**
     * 我的身份
     */
    public void getIsAgent() {
        params.put("token", Token.getToken());
        HttpClient.getInstance(context).get(HttpAPI.IS_AGENT, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                try {
                    BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                    if (bean.getCode() == 200) Token.setAgent(bean.getResult().getIs_agent());
                    if (bean.getCode() == 400) Token.setAgent("");
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");

            }
        });

    }


    /*
       淘宝授权
  */
    public void postTaoBaoS(Session session, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("openid", session.openId);
        params.put("nick", session.nick);
        params.put("ava", session.avatarUrl);
        params.put("token", Token.getToken());

        HttpClient.getInstance(context).post(HttpAPI.TABBAO_EMPOWER, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
//                Log.i("tiancao", "成功" + response);
                mLoadingDialog.dismiss();
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                mLoadingDialog.dismiss();
//                Log.i("tiancao", error_msg);
            }
        });

    }


    /**
     * 订单列表
     *
     * @param type
     * @param page
     * @param view
     */
    public void getAgentOrder(String type, int page, final BaseView<TaoOrderBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
//        params.put("token","46b778e80d944723276abdb50c2a50528e1f4113c58bd9b945ae717a45bb14bc");
        params.put("type", type);
        params.put("page", page + "");
        HttpClient.getInstance(context).get(HttpAPI.ORDER_DETAIL, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                TaoOrderBean bean = new Gson().fromJson(response, TaoOrderBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
                view.error();
            }
        });

    }

    /**
     * 订单列表
     *
     * @param type
     * @param page
     * @param view
     */
    public void getSelfOrder(String order, String type, int page, final BaseView<TaoOrderBean> view) {
        params.put("token", Token.getToken());
//        params.put("token","46b778e80d944723276abdb50c2a50528e1f4113c58bd9b945ae717a45bb14bc");
        params.put("order", order);
        params.put("type", type);
        params.put("page", page + "");
        HttpClient.getInstance(context).get(HttpAPI.DINGDAN, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd", "getSelfOrder  onSuccess  " + response);
                try {
                    TaoOrderBean bean = new Gson().fromJson(response, TaoOrderBean.class);
                    view.result(bean);
                } catch (Exception e) {
                    Logger.e("ddddddd", "getSelfOrder  Exception  " + e);
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
                view.error();
            }
        });

    }

    /**
     * 订单列表
     *
     * @param type
     * @param page
     * @param view
     */
    public void getTeamOrder(String type, int page, final BaseView<FansBean> view) {
        params.put("token", Token.getToken());
        mLoadingDialog.show();
//        params.put("token","46b778e80d944723276abdb50c2a50528e1f4113c58bd9b945ae717a45bb14bc");
        params.put("type", type);
        params.put("page", page + "");
        HttpClient.getInstance(context).get(HttpAPI.ORDER_LIST, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getTeamOrder  onSuccess" + response);
                FansBean bean = new Gson().fromJson(response, FansBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getTeamOrder  onFailure" + error_msg);
                view.error();
            }
        });

    }

    /**
     * 合伙人
     *
     * @param view
     */
    public void getPartner(final BaseView<UserInfo> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        mLoadingDialog.show();
        HttpClient.getInstance(context).get(HttpAPI.PARTNER, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getPartner  onSuccess" + response);
                UserInfo bean = new Gson().fromJson(response, UserInfo.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getPartner  onFailure" + error_msg);
                view.error();
            }
        });

    }

    /**
     * 英雄榜
     *
     * @param view
     */
    public void getYing(int page, final BaseView<YingBean> view) {
        mLoadingDialog.show();
        params.put("page", page + "");
        HttpClient.getInstance(context).get(HttpAPI.AMONRY_LIST, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "getPartner  onSuccess" + response);
                YingBean bean = new Gson().fromJson(response, YingBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getPartner  onFailure" + error_msg);
                view.error();
            }
        });

    }




/*
       保存地址 post  传入province省和city市和district区 和user_id
     */

    public void saveAddress(String sheng, String shi, String qu, String address, final BaseView<BaseBean> view) {
        mLoadingDialog.show();
        params.put("token", Token.getToken());
        params.put("province", sheng);
        params.put("city", shi);
        params.put("district", qu);
        params.put("address", address);
        HttpClient.getInstance(context).post(HttpAPI.CITY, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                mLoadingDialog.dismiss();
                Logger.e("ddddddd", "saveAddress response" + response);
                BaseBean bean = new Gson().fromJson(response, BaseBean.class);
                view.result(bean);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mLoadingDialog.dismiss();
                ToastUtils.toast(context, "网络不稳定");
                Logger.e("ddddddd", "getHomeData  onFailure" + error_msg);
                view.error();
            }
        });
    }


}
