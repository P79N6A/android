package com.judian.goule.store.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.MyScrollView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.ProductDetails;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.presenter.ProductDetailPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.GoodPayPopupwindow;
import com.judian.goule.store.views.BaseView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 旧的商品详情
 */
public class ProductDetailsActivity extends BaseActivity implements MyScrollView.OnScrollListenter {


    @BindView(R.id.product)
    SimpleDraweeView mProduct;
    @BindView(R.id.tit)
    TextView mTitle;

    @BindView(R.id.ensure)
    ImageView ensure;
    @BindView(R.id.price)
    TextView mPrice;
    @BindView(R.id.reserve_price)
    TextView mReservePrice;
    @BindView(R.id.month_sales)
    TextView mMonthSales;
    @BindView(R.id.shareTv)
    TextView mShare;
    @BindView(R.id.buy)
    TextView mBuy;
    @BindView(R.id.tianmao)
    TextView mTianmao;
    @BindView(R.id.taobao)
    TextView mTaobao;
    @BindView(R.id.coupon)
    TextView mCoupon;
    @BindView(R.id.rebate_num)
    TextView mRebateNum;
    @BindView(R.id.share_click)
    LinearLayout mShareClick;
    @BindView(R.id.buy_click)
    LinearLayout mBuyClick;

    private static String POSITION = "ProductDetailsActivity";
    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.shareAll)
    RelativeLayout mShareAll;
    @BindView(R.id.pushInfo)
    TextView mPushInfo;

    @BindView(R.id.priceH)
    TextView priceH;


    @BindView(R.id.push)
    LinearLayout mPush;
    @BindView(R.id.scroll)
    MyScrollView mScroll;


    @BindView(R.id.tabA)
    Toolbar mTabAll;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.tv4)
    TextView mTv4;

    @BindView(R.id.title)
    TextView title;

    private String num;
    private String url;
    private CdataPresenter mPresenter;
    private BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> mHomeData;
    private GoodPayPopupwindow goodPay;
    private String mFace;
    private int mIntExtra;

    public static void openMain(Context context, int op, String num_iid) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(POSITION, num_iid);
        intent.putExtra(POSITION + 2, op);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openMain(Context context, String num_iid) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(POSITION, num_iid);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openMain(Activity context, String num_iid, int postion) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(POSITION, num_iid);
        intent.putExtra(POSITION + 1, postion);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivityForResult(intent, postion);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        doBusiness(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(false)
                .init();


    }

    public void doBusiness(final Context mContext) {
        mTv1.setText(Html.fromHtml(getString(R.string.one)));
        mTv2.setText(Html.fromHtml(getString(R.string.two)));
        mTv3.setText(Html.fromHtml(getString(R.string.three)));
        mTv4.setText(Html.fromHtml(getString(R.string.four)));


        ensure.setImageResource(R.mipmap.love);
        mScroll.setListenter(this);
        num = getIntent().getStringExtra(POSITION);
        mIntExtra = getIntent().getIntExtra(POSITION + 1, 0);
        int op = getIntent().getIntExtra(POSITION + 2, 0);
        String urlHttp = "";
        switch (op) {
            case 0:
                urlHttp = HttpAPI.PRODUCT_DETAILS;
                break;
            case 1:
                urlHttp = HttpAPI.MIAOSHA_DETAILS;
                break;
        }

        Logger.d("dddd", "ddddddddd" + mIntExtra);
        mPresenter = new CdataPresenter(this);


        new ProductDetailPresenter(mContext).productDetails(urlHttp, num, new BaseView<ProductDetails>() {

            private ViewGroup.LayoutParams layoutParams;

            @Override
            public void result(ProductDetails bean) {
                if (bean.getCode().equals("200")) {
                    layoutParams = mProduct.getLayoutParams();
                    layoutParams.width = MyApplication.width;
                    layoutParams.height = layoutParams.width;
                    mFace = bean.getResult().getPict_url();
                    Logger.e("6666666", "mProduct== " + bean.getResult().getPict_url());
                    AdapterUtil.setImg(mProduct, bean.getResult().getPict_url());
                    mTitle.setText(bean.getResult().getTitle());
                    String price = bean.getResult().getPrice();
                    int m = price.indexOf(".");
                    if (m != -1) {
                        mPrice.setText(price.subSequence(0, m));
                        priceH.setText(price.subSequence(m, price.length()));
                    } else mPrice.setText(bean.getResult().getPrice());


                    mReservePrice.setText("原价 ¥ " + bean.getResult().getReserve_price() + "");
                    mReservePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    mReservePrice.getPaint().setAntiAlias(true);
                    mMonthSales.setText(bean.getResult().getMonth_sales());
                    int coupon = bean.getResult().getHave_coupon();
                    String type = bean.getResult().getUser_type();
                    if (bean.getResult().getLove().equals("0")) {
                        ensure.setImageResource(R.mipmap.love);
                    } else {
                        ensure.setImageResource(R.mipmap.love1);
                    }
                    url = bean.getResult().getItem_url();
                    if (coupon == 1) {
                        mCoupon.setVisibility(View.VISIBLE);
                        mCoupon.setText(bean.getResult().getCoupon_money() + "优惠券");
                    }
                    if (type.equals("0")) {
                        mTaobao.setVisibility(View.VISIBLE);
                        mTianmao.setVisibility(View.GONE);
                    } else {
                        mTaobao.setVisibility(View.GONE);
                        mTianmao.setVisibility(View.VISIBLE);
                    }
                    mRebateNum.setText(bean.getResult().getFanli_money() + "");
                    mShare.setText(" 分享 赚" + bean.getResult().getFanli_money());
                    mBuy.setText("去淘宝购买 返" + bean.getResult().getFanli_money());
                    if (bean.getResult().getIntroduce() != null) {
                        mPush.setVisibility(View.VISIBLE);
                        mPushInfo.setText(bean.getResult().getIntroduce());

                    } else {
                        mPush.setVisibility(View.GONE);
                    }

                } else {
                    showToast(bean.getMsg());
                }
            }

            @Override
            public void error() {

            }
        });


        mShareClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareActivity.openMain(ProductDetailsActivity.this, num);

            }
        });
        mList.setFocusable(false);
        mHomeData = AdapterUtil.getSpendData(this, new ArrayList<GoodListBean.ResultBean>());
        mList.setAdapter(mHomeData);
        mPresenter.getShareGoods(new BaseView<GoodListBean>() {
            @Override
            public void result(GoodListBean bean) {

                if (bean.getCode() == 200) {
                    mHomeData.addAll(bean.getResult());
                }
            }

            @Override
            public void error() {

            }
        });

    }


    @OnClick({R.id.back, R.id.ensure, R.id.buy_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (mIntExtra == 32) {
                    setResult(23);
                    finish();
                } else {
                    setResult(23);
                    finish();
                }


                break;

            case R.id.buy_click:
//            url="http://item.taobao.com/item.htm?id=538051967543";
//                TaobaoUtil.openTaoB(this, url);


                break;
            case R.id.ensure:
                clickLove();

                break;
        }
    }


    private void clickLove() {

        if (Token.isLogin()) {
            mPresenter.addLove(num, new BaseView<BaseBean>() {
                @Override
                public void result(BaseBean bean) {
                    ToastUtils.toast(ProductDetailsActivity.this, bean.getMsg());
                    if (bean.getCode() == 200) {
                        ensure.setImageResource(R.mipmap.love1);
                    }
                    if (bean.getCode() == 403) {
                        ensure.setImageResource(R.mipmap.love);
                    }
                }

                @Override
                public void error() {

                }
            });

        } else {

            ToastUtils.toast(ProductDetailsActivity.this, "您处于未登录状态");
        }
    }


    @Override
    public void onBottom() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onScrollY(int scrollY) {

        if (scrollY > 20) {
            title.setVisibility(View.VISIBLE);
        } else {

            mTabAll.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                    , ContextCompat.getColor(this, R.color.white), 0));
            title.setVisibility(View.INVISIBLE);
        }

        if (scrollY < 1000) {
            Logger.d("dddd", "scrollY==" + scrollY);
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
