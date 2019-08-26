package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.judian.goule.store.bean.CateBean;
import com.judian.goule.store.bean.ClassifyData;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.MsBean;
import com.judian.goule.store.bean.XuBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.view.CatePopupwindow;
import com.judian.goule.store.views.BaseView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AllGoodsActivity extends BaseActivity  {

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
    @BindView(R.id.tabAll)
    RelativeLayout tabAll;


    private int option;
    private String cate_id = "";
    private String type = "all";

    private CdataPresenter presenter;

    private BaseQuickAdapter<GoodListBean.ResultBean,BaseViewHolder> homeData;


    private static final String POSITION = "AllGoodsActivity";
    private ClassifyData bean;
    private XuBean.ResultBean xuBean;
    private Unbinder bind;

    public static void openMain(Context context, String title, int id) {
        Intent intent = new Intent(context, AllGoodsActivity.class);
        intent.putExtra(POSITION, title);
        intent.putExtra(POSITION + 1, id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openMain(Context context, ClassifyData bean, int id) {
        Intent intent = new Intent(context, AllGoodsActivity.class);
        intent.putExtra(POSITION+4, bean);
        intent.putExtra(POSITION + 1, id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openMain(Context context, XuBean.ResultBean bean, int id) {
        Intent intent = new Intent(context, AllGoodsActivity.class);
        intent.putExtra(POSITION+5, bean);
        intent.putExtra(POSITION + 1, id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }



    String  url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods);
        bind = ButterKnife.bind(this);
        mQhIv.setVisibility(View.GONE);
        setImmersionBar(2);
        String title = getIntent().getStringExtra(POSITION);
        int op = getIntent().getIntExtra(POSITION + 1, 0);
        url= HttpAPI.SEARCH;
        bean = (ClassifyData) getIntent().getSerializableExtra(POSITION+4);
        xuBean = (XuBean.ResultBean) getIntent().getSerializableExtra(POSITION+5);
        cate_id = "0";
        mTitle.setText(title);
         switch (op){
             case 0:
                 cateTab.setVisibility(View.GONE);
                 break;

             case 1:

                 break;
             case 2:
                 cateTab.setVisibility(View.GONE);
                 mTitle.setText(bean.getSlide_name());
                 url=bean.getUrl();
                 cate_id=bean.getCate_id();

                 break;
             case 3:
                 cateTab.setVisibility(View.GONE);
                 mTitle.setText(xuBean.getTitle());
                 url=xuBean.getHref();
                 break;
         }

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
        colorSel = getResources().getColor(R.color.tab_s);
        dark_grey = getResources().getColor(R.color.tab_d);
        type = "all";
        presenter = new CdataPresenter(this);
        floBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mList.scrollToPosition(0);
                totalDy = 0;
            }
        });

        initList();
    }
    private int totalDy = 0;
    private void initList() {
        mList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

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

        mList.setLayoutManager(new GridLayoutManager(this,2));
        homeData = AdapterUtil.getSouData(this,new ArrayList<GoodListBean.ResultBean>());
        mList.setFocusable(false);
        mList.setAdapter(homeData);
        homeData.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                jiazai();
            }
        });
        shuaxin();

    }

    private List<CateBean.ResultBean> tabTile = new ArrayList<>();

    private CatePopupwindow mWindow;

    private void initCate() {

        presenter.getSpendData(url,cate_id, type, page, goodView);
        presenter.getCate(new BaseView<CateBean>() {
            @Override
            public void result(CateBean bean) {
                if (bean.getCode() == 200) {
                    tabTile = bean.getResult();
                    initTab(bean.getResult());
                    mWindow = new CatePopupwindow(AllGoodsActivity.this,tabAll.getHeight(), bean.getResult(), new CatePopupwindow.OnOptionLister() {
                        @Override
                        public void map(Map<String, Integer> map) {
                            mJiantou.setImageResource(R.mipmap.jiantou_down);
                            mTabLayout.setScrollPosition(map.get("id"), 0f, true);
                            cate_id = tabTile.get(map.get("id")).getId();
                            type = "all";
                            page = 1;
                            initSearch();
                            shuaxin();
                        }
                    });

                }
            }

            @Override
            public void error() {
            }
        });

    }


    private List<String> mStrs = new ArrayList<>();

    private void initTab(final List<CateBean.ResultBean> result) {
        for (int i = 0; i < result.size(); i++) {

            int m = result.get(i).getCategory_name().indexOf("/");
            if (m == -1) {
                mTabLayout.addTab(mTabLayout.newTab().setText(result.get(i).getCategory_name()));
                mStrs.add(result.get(i).getCategory_name());
            } else {
                mTabLayout.addTab(mTabLayout.newTab().setText(result.get(i).getCategory_name().substring(0, m)));
                mStrs.add(result.get(i).getCategory_name().substring(0, m));
            }
        }


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (int i = 0; i < result.size(); i++) {
                    if (mStrs.get(i).equals(tab.getText())) {
                        mWindow.setState(i);
                        cate_id = result.get(i).getId();
                        type = "all";
                        page = 1;
                        initSearch();
                        shuaxin();
                    }

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private int colorSel, dark_grey, gray, white;
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
                mWindow.showAtLocation(mActivityAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
    BaseView<GoodListBean> goodView = new BaseView<GoodListBean>() {
        @Override
        public void result(GoodListBean bean) {

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
                homeData.loadMoreEnd();
                ToastUtils.toast(AllGoodsActivity.this,bean.getMsg());

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
        presenter.getSpendData(url,cate_id, type, page, goodView);
    }

    public void shuaxin1() {
        page = 1;
        homeData.setNull();
        presenter.getSpendData(url,cate_id, type, page, goodView);
    }

    public void jiazai() {
        ++page;
        presenter.getSpendData(url,cate_id, type, page, goodView);

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
