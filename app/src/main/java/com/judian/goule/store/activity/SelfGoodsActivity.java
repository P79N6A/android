package com.judian.goule.store.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.SelfGoodsBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SelfGoodsActivity extends BaseActivity  {

    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;


    @BindViews({R.id.zh, R.id.sl, R.id.juaneTv, R.id.jiagTv, R.id.jies})
    List<TextView> tvs;

    @BindViews({R.id.juaneIv, R.id.jiagIv})
    List<ImageView> sIvs;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.floBtn)
    ImageView floBtn;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.activity_all)
    RelativeLayout mActivityAll;
    @BindView(R.id.qh_iv)
    TextView mQhIv;
    @BindView(R.id.jiantou)
    ImageView mJiantou;

    @BindView(R.id.cateTab)
    LinearLayout cateTab;
    @BindView(R.id.tabll)
    RelativeLayout tabll;


    private int option;
    private String cate_id = "";
    private String type = "all";

    private CdataPresenter presenter;

    private BaseQuickAdapter<SelfGoodsBean.ResultBean,BaseViewHolder> homeData;
    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        presenter.dismiss();
        super.onDestroy();
    }

    String  url=HttpAPI.SELFGOODS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods);
        bind = ButterKnife.bind(this);
        tabll.setVisibility(View.GONE);
        mQhIv.setVisibility(View.GONE);
        cateTab.setVisibility(View.GONE);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f)
                .init();

        cate_id = "0";
        mTitle.setText("自营商品");

        presenter = new CdataPresenter(this);
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

        type = "all";


        initList();


        floBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mList.smoothScrollToPosition(0);

            }
        });
        shuaxin();
    }

    private void initList() {
        mList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            private int totalDy = 0;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy>1000){
                    floBtn.setVisibility(View.VISIBLE);
                }else {
                    floBtn.setVisibility(View.GONE);
                }

            }
        });

        mList.setLayoutManager(new LinearLayoutManager(this));
        homeData = AdapterUtil.getSelfData(this);
        mList.setFocusable(false);
        mList.setAdapter(homeData);
        homeData.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                jiazai();
            }
        });

    }


    private boolean change = false;
    String fanli = "fanli", couponAmount = "couponAmount", price = "price";


    @OnClick({R.id.back, R.id.zh, R.id.sl, R.id.juane, R.id.move, R.id.jiag, R.id.jies})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.move:
                mJiantou.setImageResource(R.mipmap.jiantou_up);

                break;

            case R.id.zh:
                selTextColor(0);
                if (type.equals("all")) {

                } else {
                    type = "all";
                    change = true;
                    shuaxin1();
                }


                break;
            case R.id.sl:
                selTextColor(1);
                if (type.equals("sales")) {

                } else {
                    type = "sales";
                    change = true;
                    shuaxin1();
                }
                break;
            case R.id.juane:

                if (option == 2) {

                    if (type.equals("couponAmount")) {
                        couponAmount = "componAmount_asc";
                        sIvs.get(0).setImageResource(R.mipmap.san2);
                    } else {
                        couponAmount = "couponAmount";
                        sIvs.get(0).setImageResource(R.mipmap.san1);
                    }
                } else {

                    selTextColor(2);
                    sIvs.get(0).setImageResource(R.mipmap.san1);
                }
                change = true;
                type = couponAmount;
                shuaxin1();


                break;
            case R.id.jiag:

                if (option == 3) {
                    if (type.equals("price")) {
                        price = "price_asc";

                        sIvs.get(1).setImageResource(R.mipmap.san2);
                    } else {
                        price = "price";
                        sIvs.get(1).setImageResource(R.mipmap.san1);
                    }
                } else {
                    selTextColor(3);
                    sIvs.get(1).setImageResource(R.mipmap.san1);
                }

                change = true;
                type = price;
                shuaxin1();


                break;
            case R.id.jies:
                selTextColor(4);
                if (type.equals("new")) {

                } else {
                    type = "new";
                    change = true;
                    shuaxin1();
                }

                break;
        }
    }

    int page = 1;
    /**
     * s商品数据
     */
    /**
     * s商品数据
     */
    BaseView<SelfGoodsBean> goodView = new BaseView<SelfGoodsBean>() {
        @Override
        public void result(SelfGoodsBean bean) {

            if (bean.getCode() == 200) {
                if (change) {
                    homeData.setNull1();
                    change = false;
                }
                homeData.addAll(bean.getResult());
                if (bean.getResult().size()<10){
                    homeData.loadMoreEnd();
                }else {
                    homeData.loadMoreComplete();
                }

            } else {
                if (page == 1) {
                    homeData.setNull();
                }
                ToastUtils.toast(SelfGoodsActivity.this,bean.getMsg());
                homeData.loadMoreEnd();
            }
        }

        @Override
        public void error() {

            homeData.loadMoreFail();

        }
    };



    public void shuaxin() {
        pullRefreshLayout.setRefreshing(false);
        page = 1;
        homeData.setNull();
        presenter.getSelfData(url,cate_id, type, page, goodView);
    }

    public void shuaxin1() {
        page = 1;
        homeData.setNull();
        presenter.getSelfData(url,cate_id, type, page, goodView);
    }


    public void jiazai() {
        ++page;
        presenter.getSelfData(url,cate_id, type, page, goodView);

    }

    private void selTextColor(int position) {
        for (int i = 0; i < sIvs.size(); i++) {
            sIvs.get(i).setImageResource(R.mipmap.san);
        }
        option = position;
        for (int i = 0; i < tvs.size(); i++) {
            tvs.get(i).setTextColor(getResources().getColor(R.color.dark_grey));
        }
        tvs.get(position).setTextColor(getResources().getColor(R.color.tab_s));
    }


    public void onScrollY(int scrollY) {
        if (scrollY > 400) {
            floBtn.setVisibility(View.GONE);
        } else {
            floBtn.setVisibility(View.GONE);
        }

    }


    private void initSearch() {

        fanli = "fanli";
        couponAmount = "couponAmount";
        price = "price";
        type = "all";
        for (int i = 0; i < sIvs.size(); i++) {
            sIvs.get(i).setImageResource(R.mipmap.san);

        }
        selTextColor(0);

    }

}
