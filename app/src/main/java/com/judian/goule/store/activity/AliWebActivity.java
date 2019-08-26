package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.trade.biz.context.AlibcResultType;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.example.ccy.ccyui.share.QQShareSelf;
import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.fanli.ccy.alibaic.AliManage;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.TaoTokenBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.view.ShareUrlPopupwindow;
import com.judian.goule.store.views.BaseView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AliWebActivity extends BaseActivity {

    private static String POSITION = "AliWebActivity";

    @BindView(R.id.webAli)
    WebView webAli;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.all)
    LinearLayout all;
    @BindView(R.id.aliweb_tlits_tv)
    TextView mTile;

    private WebChromeClient chromeClient;

    private Animation animation;
    boolean isOk = true;
    private String numid;
    private WebSettings settings;
    private GoodListBean.ResultBean taoBean;
    private String txt;
    private Bitmap bitmap;
    private Unbinder bind;

    //0
    public static void openMain(Context context, String goodId, String numId, int option) {
        Intent intent = new Intent(context, AliWebActivity.class);
        intent.putExtra(POSITION, goodId);
        intent.putExtra(POSITION + 1, numId);
        intent.putExtra(POSITION + 2, option);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public static void openMain(Context context, String url, int option) {
        Intent intent = new Intent(context, AliWebActivity.class);
        intent.putExtra(POSITION + 2, option);
        intent.putExtra(POSITION + 4, url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //1
    public static void openUrl(Context context, String url, int option) {
        Intent intent = new Intent(context, AliWebActivity.class);
        intent.putExtra(POSITION + 2, option);
        intent.putExtra(POSITION + 4, url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    //3
    public static void openMain(Context context, int option) {
        Intent intent = new Intent(context, AliWebActivity.class);
        intent.putExtra(POSITION + 2, option);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //2
    public static void openMain(Context context, String url, int option, String tilte) {
        Intent intent = new Intent(context, AliWebActivity.class);
        intent.putExtra(POSITION + 3, tilte);
        intent.putExtra(POSITION + 4, url);
        intent.putExtra(POSITION + 2, option);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    TaoTokenBean.ResultBean bean;

    public static void openXQ(Context context, TaoTokenBean.ResultBean bean, int option) {
        Intent intent = new Intent(context, AliWebActivity.class);
        intent.putExtra(POSITION + 6, bean);
        intent.putExtra(POSITION + 2, option);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openXQ(Context context, GoodListBean.ResultBean bean, int option) {
        Intent intent = new Intent(context, AliWebActivity.class);
        intent.putExtra(POSITION + 5, bean);
        intent.putExtra(POSITION + 2, option);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    private String num;
    boolean fin, order;
    private ShareUrlPopupwindow mPopupwindow;
    private Runnable runnable;
    int mOption = 0;

    private Bitmap bitmap1;
    boolean isScc = false, isClike = false;

    private void goShare() {
        if (!isScc) {
            ToastUtils.toast(this, "图片处理中...");
        }

        if (isScc && isClike) {

            isClike = false;
            if (bitmap1 != null)
                WXShare.getInstance(AliWebActivity.this).shareWX(mOption, taoBean.getTitle(), taoBean.getCoupon_link(), txt, bitmap1);
            else
                ToastUtils.toast(this, "图片加载失败...");

        }
    }

    private Target mTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            bitmap1 = bitmap;
            isScc = true;
            goShare();

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };


    WebView webGo;

    private ArrayList<String> list;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_web);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        list = new ArrayList<>();
        String goodId = getIntent().getStringExtra(POSITION);
        numid = getIntent().getStringExtra(POSITION + 1);
        String tilte = getIntent().getStringExtra(POSITION + 3);
        final String url = getIntent().getStringExtra(POSITION + 4);
        final int option = getIntent().getIntExtra(POSITION + 2, 0);
        initWebSet();
        webGo = new WebView(this);
        initWebGo();
        chromeClient = new WebChromeClient() {//监听网页加载
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressBar != null) {
                    progressBar.setMax(newProgress);
                }

//                Logger.e("aaaaaaaaaaa","  url =="+view.getUrl());

                if (newProgress == 100) {
                    settings.setBlockNetworkImage(false);
                    // 网页加载完成
                    if (option != 6) {

                    } else {

                    }
                }
                super.onProgressChanged(view, newProgress);
            }
        };

        taoBean = (GoodListBean.ResultBean) getIntent().getSerializableExtra(POSITION + 5);
        bean = (TaoTokenBean.ResultBean) getIntent().getSerializableExtra(POSITION + 6);
        num = numid;
        if (taoBean.getCoupon_money().equals("0")) {
            mTile.setText("立即购买");
        } else {
            mTile.setText("立即领券");
        }

        if (option == 1) {

            AliManage.getInstance(AliWebActivity.this).showUrl(url, webAli, chromeClient, callBack);


        } else if (option == 2) {

            AliManage.getInstance(AliWebActivity.this).showUrl(url, tilte, webAli, chromeClient, callBack);
        } else if (option == 3) {

            AliManage.getInstance(AliWebActivity.this).showCart(webAli, chromeClient, callBack);

        } else if (option == 5) {

            AliManage.getInstance(AliWebActivity.this).showUrl(numid, tilte, webAli, chromeClient, callBack);
        } else if (option == 6) {
            if (taoBean != null) {

                txt = "现价" + taoBean.getPrice() + "元 券后价" + taoBean.getCoupon_price() + "元";
                list.add(taoBean.getPict_url());
                Picasso.with(this).load(taoBean.getPict_url()).into(mTarget);
                numid = taoBean.getNum_iid();
                AliManage.getInstance(AliWebActivity.this).showUrl(taoBean.getCoupon_click_url(), taoBean.getTitle(), webAli, chromeClient, callBack);
            }


            if (bean != null) {
                txt = "现价" + bean.getPrice() + "元 券后价" + bean.getCoupon_price() + "元";
                list.add(bean.getPic());
                Picasso.with(this).load(bean.getPic()).into(mTarget);
                numid = bean.getNum_iid();
                AliManage.getInstance(AliWebActivity.this).showUrl(bean.getCoupon_link(), bean.getShare_title(), webAli, chromeClient, callBack);
            }


        } else if (option == 8) {

            AliManage.getInstance(AliWebActivity.this).showUrl(url, webAli, chromeClient, callBack);
        }

        mPopupwindow = new ShareUrlPopupwindow(this, new ShareUrlPopupwindow.OnShareClickListener() {
            @Override
            public void weixin() {
                mOption = 1;
                isClike = true;
                goShare();
            }

            @Override
            public void pengyou() {
                mOption = 0;
                isClike = true;
                goShare();
            }

            @Override
            public void qq() {
                if (QQShareSelf.checkApkExist(AliWebActivity.this, "com.tencent.mobileqq")) {
                    if (taoBean != null) {
                        QQShareSelf.getInstance(AliWebActivity.this).onClickShare(taoBean.getCoupon_link(), taoBean.getPict_url(), taoBean.getTitle(), txt);
                    }
                    if (bean != null) {
                        QQShareSelf.getInstance(AliWebActivity.this).onClickShare(bean.getCoupon_link(), bean.getPic(), bean.getShare_title(), txt);
                    }


                } else {
                    Toast.makeText(AliWebActivity.this, "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void zone() {

                if (QQShareSelf.checkApkExist(AliWebActivity.this, "com.tencent.mobileqq")) {
                    if (taoBean != null) {
                        QQShareSelf.getInstance(AliWebActivity.this).shareToQzone(taoBean.getCoupon_link(), list, taoBean.getTitle(), txt);
                    }

                    if (bean != null) {
                        QQShareSelf.getInstance(AliWebActivity.this).shareToQzone(bean.getCoupon_link(), list, bean.getShare_title(), txt);
                    }


                } else {
                    Toast.makeText(AliWebActivity.this, "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                }

            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);

    }


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


    }


    AlibcTradeCallback callBack = new AlibcTradeCallback() {

        @Override
        public void onTradeSuccess(AlibcTradeResult tradeResult) {
            if (tradeResult.resultType.equals(AlibcResultType.TYPECART)) {
                //加购成功
//                Toast.makeText(MyApplication.application, "加购成功", Toast.LENGTH_SHORT).show();
            } else if (tradeResult.resultType.equals(AlibcResultType.TYPEPAY)) {
                //支付成功
                try {
                    Toast.makeText(MyApplication.application, "支付成功,成功订单号为" + tradeResult.payResult.paySuccessOrders, Toast.LENGTH_SHORT).show();

                    new CdataPresenter(MyApplication.application).getBindOrder(tradeResult.payResult.paySuccessOrders.get(0), "0", new BaseView<BaseBean>() {
                        @Override
                        public void result(BaseBean bean) {
                        }

                        @Override
                        public void error() {
                        }
                    });
                } catch (Exception e) {
                    Logger.e("ddddddddddd", "fffffff Exception =" + e);
                }

            }
        }

        @Override
        public void onFailure(int paramAnonymousInt, String paramAnonymousString) {
            webGo.loadUrl("https://h5.m.taobao.com/mlapp/olist.html?spm=a2141.7756461.2.6");

        }
    };


    @OnClick({R.id.back, R.id.close, R.id.share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                exit();
                break;
            /*case R.id.shareImg://  这个是以前的分享赚界面
                ShareActivity.openMain(this, numid);
                break;*/
            case R.id.close:
                finish();
                break;
            case R.id.share:
                mPopupwindow.showAtLocation(all, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
            new CdataPresenter(AliWebActivity.this).getBindOrderAll(orders);
        }
    }


}
