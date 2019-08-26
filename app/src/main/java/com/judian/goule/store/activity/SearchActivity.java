package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.FixPopupwindow;
import com.judian.goule.store.views.BaseView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.spend_serach)
    EditText mSpendSerach;

    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;


    @BindView(R.id.souImg)
    ImageView souImg;

    @BindView(R.id.floBtn)
    ImageView mFloBtn;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.jiagIv)
    ImageView jiagIv;
    @BindView(R.id.liebiao)
    ImageView liebiao;
    @BindView(R.id.allSearch)
    LinearLayout allSearch;

    private int option;
    private String type = "all";

    private CdataPresenter presenter;
    HashMap<String, String> params = new HashMap<>();

    @BindViews({R.id.zh, R.id.sl, R.id.jiagTv})
    List<TextView> tvs;

    @BindViews({R.id.zhbm, R.id.slbm, R.id.jiagbm})
    List<TextView> bms;
    FixPopupwindow popupwindow;
    private String mKeyword;

    private BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> homeData, homeData1;
    private static final String POSITION = "SearchActivity";
    private List<GoodListBean.ResultBean> beanList;
    private Unbinder bind;

    public static void openMain(Context context, String keywords, String url) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(POSITION, keywords);
        intent.putExtra(POSITION + 1, url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openMain(Context context, String keywords, String url, int option) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(POSITION, keywords);
        intent.putExtra(POSITION + 1, url);
        intent.putExtra(POSITION + 2, option);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bind = ButterKnife.bind(this);

        doBusiness(this);
        setImmersionBar(1);
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        presenter.dismiss();
        super.onDestroy();
    }

    @Override
    protected void onResume() {

        super.onResume();


    }

    public static final int MIN_CLICK_DELAY_TIME = 2000;
    private long lastClickTime = 0;
    String url = HttpAPI.TB_SEARCH;

    public void doBusiness(Context mContext) {
        mKeyword = getIntent().getStringExtra(POSITION);
        int option = getIntent().getIntExtra(POSITION + 2, 0);
        if (option == 1) {
            Token.addKey(mKeyword);
        }
        beanList = new ArrayList<GoodListBean.ResultBean>();


        initMg(2);
        presenter = new CdataPresenter(this);
        if (mKeyword != null) {
            mSpendSerach.setText(mKeyword);
            params.put("page", 1 + "");
            params.put("keyword", mKeyword);
            Token.addHistory(mKeyword);
            presenter.getSearch(url, params, goodView);
        }
        initTab(getList());
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

        mSpendSerach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //do something;

                    long currentTime = Calendar.getInstance().getTimeInMillis();
                    if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                        lastClickTime = currentTime;
                        String key = mSpendSerach.getText().toString().trim();
                        if (key.equals("")) {
                            ToastUtils.toast(SearchActivity.this, "请输入关键字");
                        } else {
                            mKeyword = key;
                            if (homeData != null)
                                homeData.setNull();
                            if (homeData1 != null)
                                homeData1.setNull();
                            page = 1;
                            Token.addHistory(mKeyword);
                            params.put("page", page + "");
                            params.put("keyword", mKeyword);
                            presenter.getSearch(url, params, goodView);
                        }

                    }
                    return true;
                }
                return false;
            }
        });
        int top = allSearch.getTop();
        Logger.e("ddd", "allSearch.getTop() == " + top);
        popupwindow = new FixPopupwindow(this, top, new FixPopupwindow.FixListener() {
            @Override
            public void map(HashMap<String, String> map) {
                params.putAll(map);
                shuaxin();
            }
        });
    }

    private void initTab(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(list.get(i)));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        url = HttpAPI.TB_SEARCH;
                        break;
                    case 1:
                        url = HttpAPI.BD_SEARCH;
                        break;
                }

                shuaxin();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    int page = 1;

    /**
     * s商品数据
     */
    BaseView<GoodListBean> goodView = new BaseView<GoodListBean>() {
        @Override
        public void result(GoodListBean bean) {
            if (bean.getCode() == 200) {

                if (lie == 1) {
                    homeData.addAll(bean.getResult());
                    if (bean.getResult().size() < 10) {
                        homeData.loadMoreEnd();
                    } else {
                        homeData.loadMoreComplete();
                    }
                } else {
                    homeData1.addAll(bean.getResult());
                    if (bean.getResult().size() < 10) {
                        homeData1.loadMoreEnd();
                    } else {
                        homeData1.loadMoreComplete();
                    }

                }

            } else {
                ToastUtils.toast(SearchActivity.this, bean.getMsg());

                if (lie == 1) {
                    if (page == 1)
                        homeData.setNull();
                    homeData.loadMoreEnd();


                } else {
                    if (page == 1)
                        homeData1.setNull();
                    homeData1.loadMoreEnd();
                }

            }
        }

        @Override
        public void error() {

            if (lie == 1) {
                homeData.loadMoreFail();
            } else {
                homeData1.loadMoreFail();
            }
        }
    };

    String price = "price";

    int lie = 2;

    @OnClick({R.id.spend_serach, R.id.zh, R.id.sl, R.id.jiag, R.id.souImg, R.id.back, R.id.goBtn, R.id.floBtn, R.id.qh_iv, R.id.liebiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mSpendSerach.getWindowToken(), 0);
                finish();
                break;
            case R.id.floBtn:


                break;
            case R.id.souImg:
                if (souImg.isSelected()) {
                    souImg.setSelected(false);
                    params.put("ifcoupon", "");
                } else {
                    souImg.setSelected(true);
                    params.put("ifcoupon", "1");
                }
                shuaxin();
                break;

            case R.id.goBtn:
                String trim = mSpendSerach.getText().toString().trim();
                if (trim.equals("")) {
                    ToastUtils.toast(SearchActivity.this, "请输入关键字");
                } else {
                    mKeyword = trim;
                    try {
                        if (homeData != null)
                            homeData.setNull();
                        if (homeData1 != null)
                            homeData1.setNull();
                        page = 1;
                        params.put("keyword", mKeyword);
                        params.put("page", page + "");
                        presenter.getSearch(url, params, goodView);
                    } catch (Exception e) {
                        Logger.e("ddddddddd", "Exception =" + e);
                    }
                }

                break;
            case R.id.zh:
                selTextColor(0);
                params.put("type", "");
                shuaxin();
                break;
            case R.id.sl:
                selTextColor(1);
                params.put("type", "month_sales");
                shuaxin();
                break;

            case R.id.jiag:
                selTextColor(2);
                if (price.equals("price")) {
                    price = "price_desc";
                    jiagIv.setImageResource(R.mipmap.san1);
                } else {
                    price = "price";
                    jiagIv.setImageResource(R.mipmap.san2);
                }
                params.put("type", price);
                shuaxin();
                break;
            case R.id.qh_iv:
                popupwindow.showAtLocation(allSearch, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.liebiao:

                Log.i("tiancao", "lie == " + lie);
                if (lie == 1) {


                    if (homeData != null) {
                        beanList = homeData.getData();
                    }
                    lie = 2;

                } else {

                    if (homeData1 != null) {
                        beanList = homeData1.getData();
                    }
                    lie = 1;
                }
                initMg(lie);
                break;


        }
    }

    private void initMg(int i) {
        mList.setLayoutManager(new GridLayoutManager(this, i));
        if (i == 1) {
            liebiao.setImageResource(R.mipmap.leixing1);
            if (homeData == null) {
                homeData = AdapterUtil.getSpendData2(this, beanList);
                homeData.setOnLoadMoreListener(loadMoreListener);
            }
            mList.setAdapter(homeData);
            homeData.setNewData(beanList);
        }
        if (i == 2) {
            //垂直+水平分割线
            liebiao.setImageResource(R.mipmap.leixing2);
            if (homeData1 == null) {
                homeData1 = AdapterUtil.getSouData2(this, beanList);
                homeData1.setOnLoadMoreListener(loadMoreListener);
            }
            mList.setAdapter(homeData1);
            homeData1.setNewData(beanList);
        }
    }

    BaseQuickAdapter.RequestLoadMoreListener loadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            Logger.e("ffffffffff", "onLoadMoreRequested");
            jiazai();
        }
    };


    public void onScrollY(int scrollY) {
        if (scrollY > 400) {
            mFloBtn.setVisibility(View.VISIBLE);
        } else {
            mFloBtn.setVisibility(View.GONE);
        }

    }

    public void shuaxin() {
        Token.addHistory(mKeyword);
        pullRefreshLayout.setRefreshing(false);
        page = 1;
        if (homeData != null) homeData.setNull();
        if (homeData1 != null) homeData1.setNull();

        params.put("page", page + "");
        presenter.getSearch(url, params, goodView);
    }

    public void jiazai() {
        page++;
        params.put("page", page + "");
        presenter.getSearch(url, params, goodView);
    }


    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("搜全网");
        list.add("搜APP");
        return list;
    }

    private void selTextColor(int position) {

        option = position;
        for (int i = 0; i < tvs.size(); i++) {
            tvs.get(i).setTextColor(getResources().getColor(R.color.darkgreyt));
            bms.get(i).setVisibility(View.GONE);
        }
        bms.get(position).setVisibility(View.VISIBLE);
        tvs.get(position).setTextColor(getResources().getColor(R.color.tab_s));
        jiagIv.setImageResource(R.mipmap.san);
    }
}
