package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.ccy.ccyui.adapter.StrFragmentAdapter;
import com.example.ccy.ccyui.util.Logger;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.fragment.OrderFragment;
import com.judian.goule.store.presenter.CdataPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrderActivity extends BaseActivity {


    @BindView(R.id.taoTab)
    TabLayout taoTab;
    @BindView(R.id.taoVp)
    ViewPager taoVp;

    private int mOption;
    private OrderFragment mData;
    private OrderFragment mData1;
    private OrderFragment mData3;
    private Unbinder bind;

    public static final String KEY_TAB = "order_key";

    private String key = "";//这个参数是，当我从淘宝，复制了订单号，这里需要粘贴订单进行查询

    @BindViews({R.id.tb, R.id.jb})
    List<TextView> tvs;

    WebView webGo, webJD;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order3);
        bind = ButterKnife.bind(this);
        doBusiness(this);
        taoTab.setVisibility(View.GONE);
        setImmersionBar(2);
        selTv(0);

    }

    private static final String POSITION = "OrderActivity";

    public static void openMain(Context context, String key, int position) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(POSITION, position);
        intent.putExtra(KEY_TAB, key);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public void doBusiness(Context mContext) {
        initWebJD();
        initWebGo();
        inintVp(getList());

        for (int i = 0; i < tvs.size(); i++) {
            final int finalI = i;
            tvs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    taoVp.setCurrentItem(finalI);
                }
            });
        }
    }


    private void initWebGo() {
        webGo = new WebView(this);
        this.webGo.getSettings().setJavaScriptEnabled(true);
        this.webGo.addJavascriptInterface(new Handler(), "handler");
        webGo.getSettings().setBlockNetworkImage(true);
        webGo.loadUrl("https://h5.m.taobao.com/mlapp/olist.html?spm=a2141.7756461.2.6");
        webGo.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView paramAnonymous2WebView, String paramAnonymous2String) {
                paramAnonymous2WebView.loadUrl("javascript:window.handler.show(document.body.innerHTML);");
                super.onPageFinished(paramAnonymous2WebView, paramAnonymous2String);
            }
        });


    }

    private void initWebJD() {
        webJD = new WebView(this);
        this.webJD.getSettings().setJavaScriptEnabled(true);
        this.webJD.addJavascriptInterface(new HandlerJD(), "handler");
        webJD.getSettings().setBlockNetworkImage(true);

        webJD.loadUrl("https://wqs.jd.com/order/orderlist_merge.shtml");
        webJD.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith("weixin:") || url.startsWith("openapp.jdmobile:")) {

                    return false;
                }

                return false;
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

//                Logger.e("bbbb44", "onPageStarted == ");
            }


            @Override
            public void onLoadResource(WebView view, String url) {
//                Logger.e("bbbb44", "onLoadResource == ");
                super.onLoadResource(view, url);
            }


            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view,
                                                              String url) {
//                Logger.e("bbbb44", "WebResourceResponse == ");
                return super.shouldInterceptRequest(view, url);
            }

        });


    }


    @OnClick(R.id.back)
    public void onClick() {
        webJD.loadUrl("javascript:window.handler.show(document.body.innerHTML);");

        finish();
    }

    private void inintVp(List<String> cates) {
        mOption = getIntent().getIntExtra(POSITION, 0);
        key = getIntent().getStringExtra(KEY_TAB);

        final List<Fragment> list = new ArrayList<>();
        mData = new OrderFragment();
        if (key != null) {
            if (!key.equals("")) {
                mData.setKey(key);
            }
        }
        mData.bind("0", mOption);
        mData1 = new OrderFragment();
        if (key != null) {
            if (!key.equals("")) {
                mData1.setKey(key);
            }
        }
        mData1.bind("1");
        mData3 = new OrderFragment();
        mData3.bind("2");
        list.add(mData);
        list.add(mData1);
//        list.add(mData3);

        StrFragmentAdapter adapter = new StrFragmentAdapter(getSupportFragmentManager(), list, cates);
        taoVp.setAdapter(adapter);
        taoTab.setupWithViewPager(taoVp);
        taoVp.setCurrentItem(0);

        taoVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selTv(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    private void selTv(int mOption) {
        for (int i = 0; i < tvs.size(); i++) {
            tvs.get(i).setSelected(false);
        }
        tvs.get(mOption).setSelected(true);

    }


    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("已完成");
        list.add("已结算");
        list.add("已付款");
        list.add("已失效");
        return list;
    }

    class Handler {
        Handler() {
        }

        @JavascriptInterface
        public void show(String paramString) {
            Logger.e("bbbb44", "paramString == " + paramString);
//            if (paramString.indexOf("mainBizOrderIds")==-1)return;
//            int mainBizOrderIds = paramString.indexOf("mainBizOrderIds") + 20;
            int size = paramString.indexOf("module");
            String orders = "";
            List<String> list = new ArrayList<>();
            while (size != -1) {
                String str = paramString.substring(size, paramString.length());
                Logger.e("bbbb44", "str == " + str.substring(7, 25));
                boolean is = true;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(str.substring(7, 25))) {
                        is = false;
                    }
                }
                if (is) list.add(str.substring(7, 25));
                paramString = str.substring(28, str.length());
                size = paramString.indexOf("module");
            }
            if (list.size() > 0) {
                orders = list.get(0);
            }
            for (int i = 1; i < list.size(); i++) {
                orders = list.get(i) + "-" + orders;
            }
            if (list.size() == 0) return;
            Logger.e("bbbb44", "orders == " + orders);
            new CdataPresenter(MyApplication.application).getBindOrderAll(orders);
        }
    }


    class HandlerJD {
        HandlerJD() {
        }

        @JavascriptInterface
        public void show(String paramString) {
            Logger.e("bbbb44", "paramString == " + paramString);
//            if (paramString.indexOf("mainBizOrderIds")==-1)return;
//            int mainBizOrderIds = paramString.indexOf("mainBizOrderIds") + 20;
            int size = paramString.indexOf("deal_id");
            String orders = "";
            List<String> list = new ArrayList<>();
            while (size != -1) {
                String str = paramString.substring(size, paramString.length());
                Logger.e("bbbb44", "str == " + str.substring(8, 19));
                boolean is = true;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(str.substring(8, 19))) {
                        is = false;
                    }
                }
                if (is) list.add(str.substring(8, 19));
                paramString = str.substring(28, str.length());
                size = paramString.indexOf("deal_id");
            }
            if (list.size() > 0) {
                orders = list.get(0);
            }
            for (int i = 1; i < list.size(); i++) {
                orders = list.get(i) + "-" + orders;
            }
            if (list.size() == 0) return;
            Logger.e("bbbb44", "orders == " + orders);
            new CdataPresenter(MyApplication.application).getBindOrderAllJD(orders);
        }
    }
}
