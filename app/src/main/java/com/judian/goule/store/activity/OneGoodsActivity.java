package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.MyScrollView;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.ImageHolderStr;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.GoodsDetailBean;
import com.judian.goule.store.bean.OneGoodsBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.self.OrderDetailActivity;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OneGoodsActivity extends BaseActivity implements MyScrollView.OnScrollListenter {



    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.yue)
    TextView yue;
    @BindView(R.id.pay)
    TextView pay;

    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.old)
    TextView old;
    @BindView(R.id.stitle)
    TextView title;

        @BindView(R.id.title)
    TextView til;



    @BindView(R.id.sale)
    TextView sale;

    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.datails)
    WebView datails;


    @BindView(R.id.scroll)
    MyScrollView scroll;
 ;

    private CdataPresenter presenter;
    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }


    private static final String POSITION = "AliinfoActivity";
    private OneGoodsBean.ResultBean bean;
    private String id;



    public static void openMain(Context context,String  ali) {
        Intent intent = new Intent(context, OneGoodsActivity.class);
        intent.putExtra(POSITION, ali);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_goods);
        bind = ButterKnife.bind(this);
//        ImmersionBar.with(this)
//                .fitsSystemWindows(true)
//                .init();
        presenter = new CdataPresenter(this);
        id=getIntent().getStringExtra(POSITION);
        til.setText("商品详情");
        presenter.getGoodsDet(id,new BaseView<GoodsDetailBean>() {
            @Override
            public void result(GoodsDetailBean bean) {
                if (bean.getErrcode() == 200) {
                    if (bean.getResult().getUsable() == null) {
                        yue.setText("¥ 0.00");
                    } else {
                        yue.setText("¥ " + bean.getResult().getUsable());
                    }
                    setData(bean.getResult());

                }else {
                    ToastUtils.toast(OneGoodsActivity.this,bean.getErrmsg());
                }
            }

            @Override
            public void error() {
            }
        });

        initWeb();

    }

    private void initWeb() {


        WebSettings webSettings = datails.getSettings();

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//设置webview推荐使用的窗口
        webSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
        webSettings.setDisplayZoomControls(false);//隐藏webview缩放按钮
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放

        //主要用于平板，针对特定屏幕代码调整分辨率
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 240) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == DisplayMetrics.DENSITY_TV) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }

        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型：
         * 1、LayoutAlgorithm.NARROW_COLUMNS ：适应内容大小
         * 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        //WebView加载web资源
//        webView.loadUrl("http://www.newbd.com/user/register/glassRegister");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        datails.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });


    }

    private void setData(GoodsDetailBean.ResultBean result) {
        title.setText(result.getTitle());
        price.setText("¥" + result.getPrice());
        old.setText("¥" + result.getReserve_price());
        old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        old.getPaint().setAntiAlias(true);
        sale.setText("月销量" + result.getMonth_sales());

        if (result.getUsable() == null) {
            yue.setText("¥ 0.00");
        } else {
            yue.setText("¥ " + result.getUsable());
        }
        String ss = result.getPict_url();
        String[] strs = ss.split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            list.add(strs[i]);
        }

        datails.loadDataWithBaseURL(HttpAPI.HOST, result.getContent(), "text/html", "utf-8", null);
        initBanner(list);
    }


    @BindView(R.id.banner)
    ConvenientBanner<String> banner;

    private void initBanner(List<String> list) {
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.width = MyApplication.width;
//        layoutParams.height = (int) (layoutParams.width * 0.5);
        layoutParams.height = MyApplication.width;
        banner.setLayoutParams(layoutParams);
        banner.startTurning(4000);

        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolderStr();
            }
        }, list).setPageIndicator(new int[]{R.mipmap.ic_dot_normal, R.mipmap.ic_dot_pressed})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

    }


    @OnClick({ R.id.pay,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.pay:
                if (Token.isLogin())
                OrderDetailActivity.openMain(this, id);
                else  ToastUtils.toast(this,"请先登录");
                break;
            case R.id.back:
                 finish();
                break;
        }
    }

    @Override
    public void onBottom() {

    }
    @BindView(R.id.tabA)
    Toolbar mTabAll;

    @BindView(R.id.tabTv)
    TextView tabTv;
    @Override
    public void onScrollY(int scrollY) {

        if (scrollY > 20) {
            tabTv.setVisibility(View.VISIBLE);
        } else {

            mTabAll.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                    , ContextCompat.getColor(this, R.color.white), 0));
            tabTv.setVisibility(View.INVISIBLE);
        }

        if (scrollY < 1000) {

            float alpha = (float) scrollY / 1000;
            try {
                mTabAll.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                        , ContextCompat.getColor(this, R.color.white), alpha));
            } catch (Exception e) {
                Logger.e("fffff", "Exception ==" + e);
            }
        } else {

            mTabAll.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                    , ContextCompat.getColor(this, R.color.white), 1));
        }

    }
}
