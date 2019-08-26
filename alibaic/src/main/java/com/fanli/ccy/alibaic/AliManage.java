package com.fanli.ccy.alibaic;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyCartsPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.alibaba.baichuan.android.trade.page.AlibcShopPage;
import com.alibaba.baichuan.trade.biz.applink.adapter.AlibcFailModeType;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11.
 */

public class AliManage {

    private Activity mContext;
    private AlibcTaokeParams alibcTaokeParams = null;//淘客参数，包括pid，unionid，subPid
    private AlibcShowParams alibcShowParams;//页面打开方式，默认，H5，Native
    private Map<String, String> exParams;//yhhpass参数
    private static AliManage aliManage;

    private AlibcBasePage detailPage;
    private static Activity activity;


    private AliManage(Activity context) {
        mContext = context;
        alibcTaokeParams = new AlibcTaokeParams("mm_110537465_20128676_282582107", "", "");
        alibcShowParams = new AlibcShowParams(OpenType.H5, true);
        alibcShowParams.setClientType("");
        alibcShowParams.setShowTitleBar(true);

        alibcShowParams.setNativeOpenFailedMode(AlibcFailModeType.AlibcNativeFailModeNONE);
        alibcShowParams.setTitle("");
        exParams = new HashMap<>();
        exParams.put("method", "taobao.tbk.uatm.favorites.get");//自定义参数部分，可任意增删改
        exParams.put("alibaba", "阿里巴巴");//自定义参数部分，可任意增删改

    }


    public static AliManage getInstance(Activity context) {
        if (aliManage == null) {
            activity = context;
            aliManage = new AliManage(context);
        }
        return aliManage;
    }


    // 打开 订单
    public void openOrder(WebView web, WebChromeClient chromeClient, int status, boolean allOrder, AlibcTradeCallback callback) {
        AlibcBasePage page = new AlibcMyOrdersPage(status, allOrder);
        AlibcTrade.show(mContext, web, null, chromeClient, page, alibcShowParams, alibcTaokeParams, exParams, callback);
    }


    // 打开 商品详情
    public void openGoods(String numid) {
        alibcTaokeParams = new AlibcTaokeParams("", "", "");

        alibcShowParams.setTitle("商品详情");
        AlibcDetailPage alibcBasePage = new AlibcDetailPage(numid);
        AlibcTrade.show(mContext, alibcBasePage, alibcShowParams, alibcTaokeParams, exParams, new AlibcTradeCallback() {
            @Override
            public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }


    // 打开 店铺
    public void openStore(String id, String txt) {
        AlibcBasePage alibcBasePage = new AlibcShopPage(id);
        alibcShowParams.setTitle(txt);
//        AlibcTrade.show(mContext, alibcBasePage, alibcShowParams, alibcTaokeParams, exParams , new DemoTradeCallback());

    }

    // 打开 店铺
    public void openStore(String id, String txt, WebView web, WebChromeClient chromeClient, AlibcTradeCallback callback) {
        AlibcBasePage alibcBasePage = new AlibcShopPage(id);
        alibcShowParams.setTitle(txt);
        AlibcTrade.show(mContext, web, null, chromeClient, alibcBasePage, alibcShowParams, alibcTaokeParams, exParams, callback);

    }

    // 打开 购物车
    public void showCart(WebView web, WebChromeClient chromeClient, AlibcTradeCallback callback) {
        AlibcBasePage alibcBasePage = new AlibcMyCartsPage();
        AlibcTrade.show(mContext, web, null, chromeClient, alibcBasePage, alibcShowParams, alibcTaokeParams, exParams, callback);
    }

    public void openGoods(String id, WebView web, WebChromeClient chromeClient, String goodId, AlibcTradeCallback callback) {
        addJIluy(goodId, id);
        AlibcBasePage alibcBasePage = new AlibcDetailPage(id);
        AlibcTrade.show(mContext, web, null, chromeClient, alibcBasePage, alibcShowParams, alibcTaokeParams, exParams, callback);
    }


    // 打开 链接 浏览记录

    public void showUrl(String url, String tilte, WebView web, WebChromeClient chromeClient, AlibcTradeCallback callback) {
        alibcShowParams.setTitle(tilte);
        AlibcBasePage page = new AlibcPage(url);
        AlibcTrade.show(mContext, web, null, chromeClient, page, alibcShowParams, alibcTaokeParams, exParams, callback);

    }


    public void showUrl(String url, WebView web, WebChromeClient chromeClient, AlibcTradeCallback callback) {
        AlibcBasePage page = new AlibcPage(url);
        AlibcTrade.show(mContext, web, null, chromeClient, page, alibcShowParams, alibcTaokeParams, exParams, callback);
    }

    public void showUrl(String url, AlibcTradeCallback callback) {
        AlibcBasePage page = new AlibcPage(url);
        AlibcTrade.show(mContext, page, alibcShowParams, alibcTaokeParams, exParams, callback);
    }

//  登录中

    public static void loginTaobao(final Context context, AlibcLoginCallback loginCallback) {
        AlibcLogin alibcLogin = AlibcLogin.getInstance();
        alibcLogin.showLogin(loginCallback);

    }

    //  退出登录

    public static void logOut(final Context context, AlibcLoginCallback loginCallback) {
        AlibcLogin alibcLogin = AlibcLogin.getInstance();
        alibcLogin.logout(loginCallback);

    }

    //判断淘宝是都登录了
    public static boolean isLogins() {
        AlibcLogin alibcLogin = AlibcLogin.getInstance();
        return alibcLogin.isLogin();
    }

    private void addJIluy(String goodId, String numId) {


    }


//使用百川sdk提供默认的Activity打开detail

    public void detailActivity(String id, String goodId) {
        alibcShowParams.setTitle("商品详情");
        detailPage = new AlibcDetailPage(id);
        AlibcTrade.show(activity, detailPage, alibcShowParams, alibcTaokeParams, exParams, new AlibcTradeCallback() {

            @Override
            public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });

    }

    public void detailActivity(String id) {
        alibcShowParams.setTitle("商品详情");
        detailPage = new AlibcDetailPage(id);
        AlibcTrade.show(activity, detailPage, alibcShowParams, alibcTaokeParams, exParams, new AlibcTradeCallback() {
            @Override
            public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });

    }


}
