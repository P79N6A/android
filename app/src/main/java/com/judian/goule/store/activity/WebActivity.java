package com.judian.goule.store.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.example.ccy.ccyui.util.NetworkUtils;
import com.example.ccy.ccyui.util.ToastUtils;
import com.fanli.ccy.alibaic.AliManage;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WebActivity extends BaseActivity {

    @BindView(R.id.web)
    WebView web;

    private static final String POSITION = "WebActivity";
    @BindView(R.id.title)
    TextView titleTab;
    @BindView(R.id.pb_progress)
    ProgressBar pbProgress;
    private String title;
    private String url;
    @BindView(R.id.ensureIv)
    ImageView ensureIv;
    @BindView(R.id.ensure)
    TextView ensure;
    boolean isOk=true;
    private Animation animation;
    private Unbinder bind;
    private ImmersionBar mImmersionBar;

    public static void openMain(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(POSITION, title);
        intent.putExtra(POSITION + 1, url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_web);
        bind = ButterKnife.bind(this);

        doBusiness(this);
        //设置沉浸栏
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().init();
    }
    private WebChromeClient chromeClient;
    public void doBusiness(Context mContext) {
        ensure.setVisibility(View.GONE);
        ensureIv.setVisibility(View.GONE);
//        ensure.setBackgroundResource(R.mipmap.topclose);
//        ensureIv.setBackgroundResource(R.mipmap.refresh);
        animation = AnimationUtils.loadAnimation(WebActivity.this, R.anim.loading);
        animation.setInterpolator(new LinearInterpolator());
        ensureIv.startAnimation(animation);
        title = getIntent().getStringExtra(POSITION);
        url = getIntent().getStringExtra(POSITION + 1);
        titleTab.setText(title);
        chromeClient = new WebChromeClient() {//监听网页加载
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        };

        AliManage.getInstance(this).showUrl(url, title, web, chromeClient, new AlibcTradeCallback() {
            @Override
            public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });

    }

    private void initWeb() {
        web.requestFocus();
        web.setHorizontalScrollBarEnabled(false);
        web.setVerticalScrollBarEnabled(false);

        initWebView();
    }

    @OnClick({R.id.back, R.id.ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (web.canGoBack()){
                    web.goBack();
                }else {
                    finish();
                }
                break;
            case R.id.ensure:
                    finish();
                break;
        }
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {

        web.getSettings().setJavaScriptEnabled(true);
        // 设置 缓存模式
        if (NetworkUtils.isNetAvailable(WebActivity.this)) {
            web.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            web.getSettings().setCacheMode(
                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        //web.getSettings().setBlockNetworkImage(true);// 把图片加载放在最后来加载渲染
        web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 支持多窗口
        web.getSettings().setSupportMultipleWindows(true);
        // 开启 DOM storage API 功能
        web.getSettings().setDomStorageEnabled(true);
        // 开启 Application Caches 功能
        web.getSettings().setAppCacheEnabled(true);
        onLoad();
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("SetJavaScriptEnabled")
    public void onLoad() {

        try {
            web.setWebViewClient(new WebViewClient() {

                @Override
                public void onLoadResource(WebView view, String url) {

                    Log.i("tag", "onLoadResource url=" + url); // 开始加载
                    super.onLoadResource(view, url);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView webview,
                                                        String url) {
                    if(url == null) return false;

                    try {
                        if(url.startsWith("weixin://") //微信
                                || url.startsWith("alipays://") //支付宝
                                || url.startsWith("mailto://") //邮件
                                || url.startsWith("tbopen://") //邮件
                                || url.startsWith("tel://")//电话
                                || url.startsWith("dianping://")//大众点评
                            //其他自定义的scheme
                                ) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(intent);
                            return true;
                        }
                    } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                        return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                    }

                    //处理http和https开头的url
//                    webview.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageFinished(WebView view, String url) {

                    String title = view.getTitle(); // 得到网页标题
//                    titleTab.setText(title);
                    Log.e("tag", "onPageFinished WebView title=" + title);

                }

                @Override
                public void onReceivedError(WebView view, int errorCode,
                                            String description, String failingUrl) {
                    ToastUtils.toast(WebActivity.this,"加载错误");
                }
            });
            web.loadUrl(url);
        } catch (Exception e) {
            return;
        }

        web.setWebChromeClient(new WebChromeClient() {//监听网页加载
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    pbProgress.setVisibility(View.GONE);
                    ensureIv.clearAnimation();
                    isOk=true;
                    ensureIv.setVisibility(View.GONE);
                    ensure.setVisibility(View.VISIBLE);

                } else {
                    // 加载中
                    if (isOk){
                        ensureIv.startAnimation(animation);
                        isOk=false;
                    }

                    pbProgress.setProgress(newProgress);
                    ensure.setVisibility(View.GONE);
                    ensureIv.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });


    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        } else {
            finish();
        }
        return true;
    }


}




