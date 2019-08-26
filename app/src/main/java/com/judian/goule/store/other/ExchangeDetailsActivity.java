package com.judian.goule.store.other;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.MyScrollView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.WebActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.adapter.ImageHolderStr;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.ExDetailBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.ShareUtil;
import com.judian.goule.store.view.EXPayPopupwindow;
import com.judian.goule.store.views.BaseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExchangeDetailsActivity extends BaseActivity implements MyScrollView.OnScrollListenter {

    @BindView(R.id.go_exchange)
    TextView goExchange;


    private static final String POSITION = "ExchangeDetailsActivity";
    @BindView(R.id.exchange_banner)
    ConvenientBanner mExchangeBanner;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.pic)
    TextView mPic;
    @BindView(R.id.exNum)
    TextView mExNum;
    @BindView(R.id.imgDetail)
    SimpleDraweeView mImgDetail;
    @BindView(R.id.exAll)
    RelativeLayout mExAll;
    @BindView(R.id.txt)
    TextView mTxt;
    @BindView(R.id.scroll)
    MyScrollView mScroll;
    @BindView(R.id.tabA)
    Toolbar mTabA;
    private CdataPresenter mPresenter;
    private ExDetailBean.ResultBean mResult;
    private String mTyprs;
    private String mId;
    private EXPayPopupwindow mPayPopupwindow;

    public static void openMain(Context context, String id) {
        Intent intent = new Intent(context, ExchangeDetailsActivity.class);
        intent.putExtra(POSITION, id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_details);
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .fitsSystemWindows(false)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .init();
        mScroll.setListenter(this);
        mId = getIntent().getStringExtra(POSITION);
        mPresenter = new CdataPresenter(this);
        mPresenter.getExdetail(mId, new BaseView<ExDetailBean>() {
            @Override
            public void result(ExDetailBean bean) {
                if (bean.getCode() == 200) {
                    initView(bean.getResult());
                } else {
                    ToastUtils.toast(ExchangeDetailsActivity.this, bean.getMsg());
                }


            }

            @Override
            public void error() {

            }
        });


    }

    private void initView(ExDetailBean.ResultBean result) {
        mResult = result;

        mTitle.setText(result.getTitle());
        mPic.setText("¥" + result.getPrice());
        mExNum.setText(result.getRequire_points());
        List<String> list = new ArrayList<>();
        list.add(result.getPict_url());
        list.add(result.getPict_url_two());
        initBanner(list);
        AdapterUtil.setControllerListener(mImgDetail, result.getItem_url(), MyApplication.width);
        initPop(mResult);


    }

    private void initBanner(List<String> list) {

        ViewGroup.LayoutParams layoutParams = mExchangeBanner.getLayoutParams();
        layoutParams.width = MyApplication.width;
        layoutParams.height = (int) (layoutParams.width);
        mExchangeBanner.setLayoutParams(layoutParams);
        mExchangeBanner.startTurning(4000);


        mExchangeBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolderStr();
            }
        }, list).setPageIndicator(new int[]{R.mipmap.ic_dot_normal, R.mipmap.ic_dot_pressed})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);


    }

    public void initPop(final ExDetailBean.ResultBean result) {
        mPayPopupwindow = new EXPayPopupwindow(this, result);

    }

    @OnClick({R.id.back, R.id.help1, R.id.help, R.id.kufu, R.id.go_exchange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.help1:
                WebActivity.openMain(this, "积分", HttpAPI.JIESHAO);
                break;
            case R.id.help:
                WebActivity.openMain(this, "积分", HttpAPI.JIESHAO);
                break;
            case R.id.kufu:
                if (ShareUtil.checkApkExist(this, "com.tencent.mobileqq")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + mResult.getQq() + "&version=1")));
                } else {
                    Toast.makeText(this, "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.go_exchange:

                if (mResult == null) {
                    toast("获取信息失败");
                } else {
                    mPayPopupwindow.showAtLocation(mExAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                }

                break;
        }
    }

    @Override
    public void onBottom() {

    }

    @Override
    public void onScrollY(int scrollY) {

        if (scrollY>20){
            mTxt.setVisibility(View.VISIBLE);
        }else {
            mTxt.setVisibility(View.INVISIBLE);
        }

        if (scrollY<1000){
            Logger.d("dddd","scrollY=="+scrollY);
            float alpha = (float) scrollY / 1000;
            try {

                mTabA.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                        , ContextCompat.getColor(this, R.color.white), alpha));
            }catch (Exception e){
                Logger.e("fffff","Exception =="+e);
            }


        }else {

            mTabA.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                    , ContextCompat.getColor(this, R.color.white), 1));
        }

    }
}
