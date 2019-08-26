package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.trade.biz.context.AlibcResultType;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.example.ccy.ccyui.util.Logger;
import com.fanli.ccy.alibaic.AliManage;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TKLWebActivity extends BaseActivity {

    private static String POSITION = "TKLWebActivity";

    @BindView(R.id.webAli)
    WebView webAli;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.all)
    LinearLayout all;
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.ensureIv)
    ImageView share;


    private WebChromeClient chromeClient;

    private WebSettings settings;
    private Unbinder bind;

    String url;


    WebView webGo;

    private ArrayList<String> list;
    private CdataPresenter presenter;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }


    public static void openSurl(Context context, String url) {
        Intent intent = new Intent(context, TKLWebActivity.class);
        intent.putExtra(POSITION, url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_web);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        title.setText("购了商城");
        share.setImageResource(R.mipmap.close2);
        presenter = new CdataPresenter(this);
        url = getIntent().getStringExtra(POSITION);
        initWebSet();
        webGo = new WebView(this);
        initWebGo();
        chromeClient = new WebChromeClient() {//监听网页加载
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressBar != null) {
                    progressBar.setMax(newProgress);
                }
//                Log.i("tiancao", "url:" + url);
                if (newProgress == 100) {
                    title.setText(view.getTitle());
                    settings.setBlockNetworkImage(false);
                    // 网页加载完成
                }
                super.onProgressChanged(view, newProgress);
            }
        };
        AliManage.getInstance(TKLWebActivity.this).showUrl(url, webAli, chromeClient, callBack);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);

    }


    AlibcTradeCallback callBack = new AlibcTradeCallback() {

        @Override
        public void onTradeSuccess(AlibcTradeResult tradeResult) {
            if (tradeResult.resultType.equals(AlibcResultType.TYPECART)) {
                //加购成功
//                Toast.makeText(MyApplication.application, "加购成功", Toast.LENGTH_SHORT).show();
            } else if (tradeResult.resultType.equals(AlibcResultType.TYPEPAY)) {
                //支付成功
                Toast.makeText(MyApplication.application, "支付成功,成功订单号为" + tradeResult.payResult.paySuccessOrders, Toast.LENGTH_SHORT).show();

                new CdataPresenter(MyApplication.application).getBindOrder(tradeResult.payResult.paySuccessOrders.get(0), "0", new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                    }

                    @Override
                    public void error() {
                    }
                });
            }
        }

        @Override
        public void onFailure(int paramAnonymousInt, String paramAnonymousString) {
            webGo.loadUrl("https://h5.m.taobao.com/mlapp/olist.html?spm=a2141.7756461.2.6");
        }
    };


    private void initWebSet() {
        settings = webAli.getSettings();

        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setBlockNetworkImage(true);
        settings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        settings.setSupportZoom(true);
        // 设置出现缩放工具
        settings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        settings.setUseWideViewPort(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);

        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

    }

    @OnClick({R.id.back, R.id.ensureIv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                exit();
                break;
            case R.id.ensureIv:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            exit();
        }
        return true;
    }

    private void exit() {
        if (webAli.canGoBack()) {
            webAli.goBack();
        } else {
            finish();
        }
    }


    private void initWebGo() {
        this.webGo.getSettings().setJavaScriptEnabled(true);
        this.webGo.addJavascriptInterface(new Handler(), "handler");
        webGo.getSettings().setBlockNetworkImage(true);

        webGo.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView paramAnonymous2WebView, String paramAnonymous2String) {
                paramAnonymous2WebView.loadUrl("javascript:window.handler.show(document.body.innerHTML);");
                super.onPageFinished(paramAnonymous2WebView, paramAnonymous2String);
            }
        });


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
            new CdataPresenter(TKLWebActivity.this).getBindOrderAll(orders);
        }
    }


}
