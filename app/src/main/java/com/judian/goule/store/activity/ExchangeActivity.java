package com.judian.goule.store.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.view.MyScrollView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.adapter.ImageHolder2;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.DHBannerBean;
import com.judian.goule.store.bean.JiFBean;
import com.judian.goule.store.other.CommodityBean;
import com.judian.goule.store.other.ExchangeDetailsActivity;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.presenter.ExchangePresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeActivity extends BaseActivity implements MyScrollView.OnScrollListenter {
    @BindView(R.id.exchange_banner)
    ConvenientBanner<DHBannerBean.ResultBean> banner;
    @BindView(R.id.exchange_back)
    ImageView exchangeBack;


    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.jf)
    TextView jf;

    @BindView(R.id.scroll)
    MyScrollView scroll;
    private ViewGroup.LayoutParams layoutParams;
    private BaseQuickAdapter<CommodityBean.ResultBean,BaseViewHolder> adapter;
    private ExchangePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange1);
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.2f)
                .init();
        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuaxin();
            }

            @Override
            public void onMove(boolean ismove) {

            }
        });
        scroll.setListenter(this);
        scroll.setLoading(true);

        initBanner();
        initRecy();
        exchangeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        new CdataPresenter(this).getInt(new BaseView<JiFBean>() {
            @Override
            public void result(JiFBean bean) {

                if (bean.getCode() == 200) {
                    jf.setText("剩余积分："+bean.getResult().getAble_score());
                }

            }

            @Override
            public void error() {

            }
        });
    }

    private void initBanner() {
        WindowManager wm1 = getWindowManager();
        MyApplication.width = wm1.getDefaultDisplay().getWidth();
        MyApplication.height = wm1.getDefaultDisplay().getHeight();
        layoutParams = banner.getLayoutParams();
        layoutParams.width = MyApplication.width;
        layoutParams.height = (int) (layoutParams.width * 0.5);
        banner.setLayoutParams(layoutParams);
        banner.startTurning(4000);


        new CdataPresenter(this).getDhLun("1",new BaseView<DHBannerBean>() {
            @Override
            public void result(final DHBannerBean bean) {

                banner.setPages(new CBViewHolderCreator() {
                    @Override
                    public Object createHolder() {
                        return new ImageHolder2();
                    }
                }, bean.getResult()).setPageIndicator(new int[]{R.mipmap.ic_dot_normal, R.mipmap.ic_dot_pressed})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

                banner.setOnItemClickListener(new OnItemClickListener() {


                    @Override
                    public void onItemClick(int position) {
                        DHBannerBean.ResultBean dataBean = bean.getResult().get(position);
                        WebActivity.openMain(ExchangeActivity.this, dataBean.getName(), dataBean.getUrl());

                    }
                });


            }

            @Override
            public void error() {

            }
        });

    }

    public void initRecy() {


        adapter = new BaseQuickAdapter<CommodityBean.ResultBean, BaseViewHolder>( R.layout.iten_exchange) {
            @Override
            protected void convert(BaseViewHolder helper, final CommodityBean.ResultBean item, int position) {
                AdapterUtil.setImgB((SimpleDraweeView) helper.getView(R.id.commodity_img),item.getPict_url(),2.2);
                helper.setTextView(R.id.commodity_title, item.getTitle())
                        .setTextView(R.id.commodity_jifen, item.getRequire_points())
                        .setTextView(R.id.commodity_num, item.getCount()+"")
                        .setTextView(R.id.commodity_money, item.getPrice());

            ProgressBar  progressBar= helper.getView(R.id.progressBar);
                progressBar.setMax(item.getCount_num());
                progressBar.setProgress(item.getCount());

             helper.getView(R.id.all).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     ExchangeDetailsActivity.openMain(mContext,item.getId());
                 }
             });


            }
        };
        list.setLayoutManager(new GridLayoutManager(this,2){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        list.setFocusable(false);
        list.setAdapter(adapter);

        mPresenter = new ExchangePresenter(this);
        mPresenter.getCommodity("1", mView);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        });

    }


    BaseView<CommodityBean> mView = new BaseView<CommodityBean>() {
        @Override
        public void result(CommodityBean bean) {

            if (bean.getCode() == 200) {

                adapter.addAll(bean.getResult());
                if (bean.getResult().size()<10)
                    adapter.loadMoreEnd();
                else adapter.loadMoreComplete();

            } else {
                adapter.loadMoreEnd();
            }


        }

        @Override
        public void error() {
            adapter.loadMoreFail();
        }
    };

    int page = 1;


    public void shuaxin() {
        pullRefreshLayout.setRefreshing(false);
       adapter.setNull();
        page=1;
        mPresenter.getCommodity(page + "", mView);

    }


    @Override
    public void onBottom() {

        if (adapter.mLoading){
            scroll.setLoading(false);
            ++page;
            mPresenter.getCommodity(page + "", mView);
        }



    }

    @Override
    public void onScrollY(int scrollY) {

    }

}
