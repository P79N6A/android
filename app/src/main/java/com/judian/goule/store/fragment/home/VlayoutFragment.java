package com.judian.goule.store.fragment.home;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.baoyz.widget.PullRefreshLayout;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.listener.NoDoubleClickListener;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.NetworkUtils;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.nukc.LoadMoreWrapper.LoadMoreAdapter;
import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;
import com.judian.goule.store.activity.youxuan.TetrisActivity;
import com.judian.goule.store.adapter.MeAdapter;
import com.judian.goule.store.bean.BannerData;
import com.judian.goule.store.bean.ClassifyData;
import com.judian.goule.store.db.liteorm.HomeDataDBUtil;
import com.kepler.jd.Listener.LoginListener;
import com.kepler.jd.login.KeplerApiManager;
import com.kepler.jd.sdk.bean.KeplerAttachParameter;
import com.kepler.jd.sdk.exception.KeplerBufferOverflowException;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.AllGoodsActivity;
import com.judian.goule.store.activity.JDActivity;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.activity.NewsActivity;
import com.judian.goule.store.activity.SignActivity;
import com.judian.goule.store.activity.SouActivity;
import com.judian.goule.store.activity.WebActivity;
import com.judian.goule.store.adapter.BaseDelegateAdapter;
import com.judian.goule.store.adapter.ExamplePagerAdapter;
import com.judian.goule.store.adapter.ImageHolder;
import com.judian.goule.store.bean.BannerBean;
import com.judian.goule.store.bean.CateBean;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.MsBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.CatePopupwindow;
import com.judian.goule.store.views.BaseView;
import com.squareup.picasso.Picasso;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VlayoutFragment extends Fragment implements LoadMoreAdapter.OnLoadMoreListener, MeAdapter.GoodsListener {


    @BindView(R.id.review)
    RecyclerView review;

    @BindView(R.id.tab)
    Toolbar tab;


    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;
    @BindView(R.id.floBtn)
    ImageView floBtn;
    @BindView(R.id.all)
    RelativeLayout all;
    Unbinder unbinder;

    int BANNER_VIEW_TYPE = 1;
    int MENU_VIEW_TYPE = 2;
    int NEWS_VIEW_TYPE = 3;
    int TITLE_VIEW_TYPE = 4;
    int GRID_VIEW_TYPE = 5;


    private String cate_id = "";
    private String type = "all";

    private int option;
    String fanli = "fanli", couponAmount = "couponAmount", price = "price";
    ImageView[] sIvs;
    TextView[] tvs;
    SimpleDraweeView[] imageViews;
    @BindView(R.id.vp1)
    ViewPager vp1;

    private ConvenientBanner<String> banner;
    private CdataPresenter presenter;
    private List<DelegateAdapter.Adapter> adapters;
    private List<GoodListBean.ResultBean> listData;
    private DelegateAdapter delegateAdapter;
    private MagicIndicator magicIndicator;
    private CatePopupwindow mWindow;
    ImageView mJiantou;
    private int index = 0;
    private LoadMoreAdapter loadMoreWrapper;
    private int offsetTotal;
    private BaseDelegateAdapter menuAdapter;//分类


    private List<GoodListBean.ResultBean> mData = new ArrayList<>();//商品数据
    private MeAdapter meAdapter;

    @Override
    public void onResume() {
        super.onResume();
    }

    public VlayoutFragment() {
        // Required empty public constructor
    }

    String url = HttpAPI.SEARCH;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vlayout, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new CdataPresenter(getContext());
        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_Bitmap);
        pullRefreshLayout.setBackgroundColor(getResources().getColor(R.color.blue));
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuaxin();
            }

            @Override
            public void onMove(boolean ismove) {

            }
        });
        initRecy();
        return view;
    }


    private void initRecy() {
        adapters = new LinkedList<>();
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(getContext());
        review.setLayoutManager(layoutManager);


        review.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dy, int i2) {
                if (layoutManager.getOffsetToStart() > 1000) {
                    floBtn.setVisibility(View.VISIBLE);
                } else {
                    floBtn.setVisibility(View.GONE);
                }
            }
        });


        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        review.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 30);
        delegateAdapter = new DelegateAdapter(layoutManager, true);
        review.setAdapter(delegateAdapter);

        //广告轮播
        BaseDelegateAdapter bannerAdapter = new BaseDelegateAdapter(MyApplication.application, new LinearLayoutHelper()
                , R.layout.vl_banner, 1, BANNER_VIEW_TYPE) {
            @Override
            public int getItemCount() {
                return 1;
            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (banner == null) {
                    banner = holder.getView(R.id.banner);
                    ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
                    layoutParams.width = MyApplication.width;
                    layoutParams.height = (int) (layoutParams.width * 0.5);//这里设置轮播图的高度
                    banner.setLayoutParams(layoutParams);
                    banner.startTurning(4000);
                    //判断数据库是否有首页数据
                    BannerBean bannerData = HomeDataDBUtil.getBanner(getContext());
                    if (bannerData.getMsg().equals("")) {
                        presenter.getBanner("2", new BaseView<BannerBean>() {
                            @Override
                            public void result(BannerBean bean) {
                                if (bean.getCode() == 200) {
                                    HomeDataDBUtil.saveBanner(getContext(), bean);
                                    setBannerUI(bean);
                                }
                            }

                            @Override
                            public void error() {

                            }
                        });
                    } else {
                        setBannerUI(bannerData);
                    }
                }

            }

            @Override
            protected void convert(BaseViewHolder holder, Object item, int position) {

            }
        };
        adapters.add(0, bannerAdapter);

        //第一层分类
        GridLayoutHelper layoutHelper = new GridLayoutHelper(5);
        menuAdapter = new BaseDelegateAdapter<ClassifyData>(getContext(), layoutHelper, R.layout.sub_cate_gridview
                , 0, MENU_VIEW_TYPE) {
            @Override
            protected void convert(BaseViewHolder holder, final ClassifyData item, final int position) {
                holder.setTextView(R.id.tvCateName, item.getSlide_name());
                Picasso.with(getContext()).load(HttpAPI.HOST + item.getBanner()).into((ImageView) holder.getView(R.id.ivPic));
                holder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
//                        Log.i("tiancao", "类型：" + item.getType());
                        switch (item.getType()) {
                            case "2"://接口
                                if (item.getStyle().equals("1")) {
                                    if (Token.isLogin()) {
                                        jdbean = item;
                                        if (!KeplerApiManager.getWebViewService().isKeplerLogined()) {
                                            // 直接授权登录京东​
                                            KeplerApiManager.getWebViewService().login(
                                                    getActivity(), mLoginListener);
                                        } else {

                                            JDActivity.openMain(getContext(), item, 4);

                                        }

                                    } else {

                                        startActivity(new Intent(getContext(), LoginActivity.class));
                                    }


                                } else {
                                    AllGoodsActivity.openMain(getContext(), item, 2);
                                }

                                break;
                            case "1"://H5
                                if (item.getStyle().equals("1")) {
                                    if (Token.isLogin()) {
                                        jdbean = item;
                                        if (!KeplerApiManager.getWebViewService().isKeplerLogined()) {
                                            // 直接授权登录京东​
                                            KeplerApiManager.getWebViewService().login(
                                                    getActivity(), mLoginListener);
                                        } else {

                                            KeplerAttachParameter mKeplerAttachParameter = new KeplerAttachParameter();//这个是即时性参数  可以设置
                                            try {
                                                KeplerApiManager.getWebViewService().openJDUrlWebViewPage(item.getUrl(),
                                                        mKeplerAttachParameter);
                                            } catch (KeplerBufferOverflowException e) {
                                                e.printStackTrace();
                                                Logger.e("fffffff", "KeplerBufferOverflowException == " + e);
                                            }
                                        }

                                    } else {

                                        startActivity(new Intent(getContext(), LoginActivity.class));
                                    }


                                } else {
                                    WebActivity.openMain(getContext(), item.getSlide_name(), item.getUrl());
                                }


                                break;
                            case "3"://页面
                                try {
                                    startActivity(new Intent(getContext(), Class.forName(getActivity().getPackageName() + ".activity." + item.getUrl())));
                                } catch (ClassNotFoundException e) {
                                    Logger.e("dddddddd", "ClassNotFoundException == " + e);

                                    e.printStackTrace();
                                }
                        }

                    }
                });
            }

        };


        adapters.add(1, menuAdapter);
        BaseDelegateAdapter tuAdapter = new BaseDelegateAdapter(
                getContext(), new LinearLayoutHelper()
                , R.layout.vl_today, 1, NEWS_VIEW_TYPE) {

            @Override
            public int getItemCount() {
                return 1;
            }

            @Override
            public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //设置banner样式
                if (imageViews == null) {
                    imageViews = new SimpleDraweeView[]{holder.getView(R.id.oneIv), holder.getView(R.id.twoIv), holder.getView(R.id.threeIv),
                            holder.getView(R.id.fuorIv), holder.getView(R.id.fiveIv), holder.getView(R.id.sixIv), holder.getView(R.id.sevenIv), holder.getView(R.id.eightIv), holder.getView(R.id.nineIv)};
                }
            }

            @Override
            protected void convert(BaseViewHolder holder, Object item, int position) {

            }
        };

        StickyLayoutHelper stLayoutHelper = new StickyLayoutHelper();
        BaseDelegateAdapter tabAdapter = new BaseDelegateAdapter(
                getContext(), stLayoutHelper
                , R.layout.vl_tab, 1, TITLE_VIEW_TYPE) {

            @Override
            public int getItemCount() {
                return 1;
            }

            @Override
            protected void convert(BaseViewHolder holder, Object item, int position) {

            }

            @Override
            protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
                super.onBindViewHolderWithOffset(holder, position, offsetTotal);
                VlayoutFragment.this.offsetTotal = offsetTotal;

            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //设置banner样式
                magicIndicator = holder.getView(R.id.magic);
                initMagicIndicator2(tabTile);
                mJiantou = holder.getView(R.id.jiantou);
                tvs = new TextView[]{holder.getView(R.id.zh), holder.getView(R.id.sl), holder.getView(R.id.juaneTv),
                        holder.getView(R.id.jiagTv), holder.getView(R.id.jies)};

                sIvs = new ImageView[]{holder.getView(R.id.juaneIv), holder.getView(R.id.jiagIv)};

                holder.getView(R.id.move).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mWindow != null) {
                            mWindow.showAtLocation(all, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                            mJiantou.setImageResource(R.mipmap.jiantou_up);
                        }

                    }
                });
                holder.getView(R.id.juane).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (option == 2) {
                            if (type.equals("couponAmount")) {
                                couponAmount = "componAmount_asc";
                                sIvs[0].setImageResource(R.mipmap.san2);
                            } else {
                                couponAmount = "couponAmount";
                                sIvs[0].setImageResource(R.mipmap.san1);
                            }
                        } else {

                            selTextColor(2);
                            sIvs[0].setImageResource(R.mipmap.san1);
                        }
                        type = couponAmount;
                        shuaxin1();

                    }
                });

                holder.getView(R.id.jiag).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (option == 3) {
                            if (type.equals("price")) {
                                price = "price_asc";

                                sIvs[1].setImageResource(R.mipmap.san2);
                            } else {
                                price = "price";
                                sIvs[1].setImageResource(R.mipmap.san1);
                            }
                        } else {
                            selTextColor(3);
                            sIvs[1].setImageResource(R.mipmap.san1);
                        }

                        type = price;
                        shuaxin1();
                    }
                });


                for (TextView tv : tvs)
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            switch (v.getId()) {
                                case R.id.zh:
                                    selTextColor(0);
                                    if (type.equals("all")) {

                                    } else {
                                        type = "all";
                                        shuaxin1();
                                    }
                                    break;
                                case R.id.sl:
                                    selTextColor(1);
                                    if (type.equals("sales")) {

                                    } else {
                                        type = "sales";

                                        shuaxin1();
                                    }
                                    break;
                                case R.id.juaneTv:
                                    if (option == 2) {
                                        if (type.equals("couponAmount")) {
                                            couponAmount = "componAmount_asc";
                                            sIvs[0].setImageResource(R.mipmap.san2);
                                        } else {
                                            couponAmount = "couponAmount";
                                            sIvs[0].setImageResource(R.mipmap.san1);
                                        }
                                    } else {

                                        selTextColor(2);
                                        sIvs[0].setImageResource(R.mipmap.san1);
                                    }
                                    type = couponAmount;
                                    shuaxin1();


                                    break;
                                case R.id.jiagTv:
                                    if (option == 3) {
                                        if (type.equals("price")) {
                                            price = "price_asc";

                                            sIvs[1].setImageResource(R.mipmap.san2);
                                        } else {
                                            price = "price";
                                            sIvs[1].setImageResource(R.mipmap.san1);
                                        }
                                    } else {
                                        selTextColor(3);
                                        sIvs[1].setImageResource(R.mipmap.san1);
                                    }

                                    type = price;
                                    shuaxin1();


                                    break;
                                case R.id.jies:
                                    selTextColor(4);
                                    if (type.equals("new")) {

                                    } else {
                                        type = "new";
                                        shuaxin1();
                                    }

                                    break;


                            }


                        }
                    });

            }
        };

        adapters.add(tuAdapter);
        adapters.add(tabAdapter);
        meAdapter = new MeAdapter(getContext(), mData);
        meAdapter.setmListener(this);
        adapters.add(meAdapter);
        delegateAdapter.setAdapters(adapters);
        loadMoreWrapper = LoadMoreWrapper.with(delegateAdapter)
                .setShowNoMoreEnabled(true)
                .setListener(this).into(review);

        initData();

    }

    /**
     * 设置轮播图片数据
     */
    private List<String> imageurl = new ArrayList<>();

    private void setBannerUI(final BannerBean bean) {
        List<BannerData> datalist = bean.getData();
        imageurl.clear();
        for (int i = 0; i < datalist.size(); i++) {
            imageurl.add(datalist.get(i).getBanner());
        }
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolder();
            }
        }, imageurl)
                .setPageIndicator(new int[]{R.mipmap.ic_dot_normal, R.mipmap.ic_dot_pressed})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        //轮播图，点击监听
        banner.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(int position) {
                BannerData dataBean = bean.getData().get(position);
//                    Log.i("tiancao", dataBean.toString());
                switch (dataBean.getStyle()) {
                    case "1"://商品
                        if (Token.isLogin()) {

                        } else {
                            startActivity(new Intent(getContext(), LoginActivity.class));
                        }
                        break;
                    case "2"://列表
                        //类型的转换
                        try {
                            ClassifyData datas = new ClassifyData();
                            datas.setBanner(dataBean.getBanner());
                            datas.setCate_id(dataBean.getCate_id());
                            datas.setCopy(dataBean.getCate_id());
//                                datas.setExplain();
                            datas.setNum_iid(dataBean.getNum_iid());
                            datas.setSlide_id(dataBean.getSlide_id());
                            datas.setSlide_name(dataBean.getSlide_name());
                            datas.setSlide_sort(dataBean.getSlide_sort());
                            datas.setStatus(dataBean.getStatus());
                            datas.setStyle(dataBean.getStyle());
                            datas.setType(dataBean.getType());
                            datas.setUrl(dataBean.getUrl());
                            AllGoodsActivity.openMain(getContext(), datas, 2);
                        } catch (Exception e) {

                        }
                        break;
                    case "3"://h5
                        WebActivity.openMain(getContext(), dataBean.getSlide_name(), dataBean.getUrl());
                        break;
                }

            }
        });
    }

    /**
     * 分类样式
     */
    private void initMagicIndicator2(final List<CateBean.ResultBean> resultBeen) {
        if (magicIndicator == null) return;
        ExamplePagerAdapter mExamplePagerAdapter = new ExamplePagerAdapter(resultBeen);
        vp1.setAdapter(mExamplePagerAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setScrollPivotX(0.25f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return resultBeen == null ? 0 : resultBeen.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(resultBeen.get(index).getCategory_name());
                simplePagerTitleView.setNormalColor(Color.parseColor("#303030"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#ff3d01"));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        VlayoutFragment.this.index = index;
                        vp1.setCurrentItem(index);

                        cate_id = resultBeen.get(index).getId();
                        if (mWindow != null)
                            mWindow.setState(index);
                        shuaxin1();
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setLineHeight(UIUtil.dip2px(context, 1));
                linePagerIndicator.setColors(Color.parseColor("#ff3d01"));
                return linePagerIndicator;
            }
        });

        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vp1);
        vp1.setCurrentItem(index);

    }

    BaseView<GoodListBean> goodView = new BaseView<GoodListBean>() {
        @Override
        public void result(GoodListBean bean) {
            if (bean.getCode() == 200) {
                if (bean.getResult().size() != 0) {
                    if (page == 1) {
                        mData.clear();
                        mData.addAll(bean.getResult());
                    } else {
                        mData.addAll(bean.getResult());
                    }
                } else {
                    mData.clear();
                }
                try {
                    if (bean.getResult().size() < 10) {
                        noMoreData();
                    } else {
                        loadMoreWrapper.setLoadMoreEnabled(true);
                        loadMoreWrapper.getOriginalAdapter().notifyDataSetChanged();
                    }
                } catch (Exception r) {

                }
                meAdapter.notifyDataSetChanged();
            } else {
                if (page == 1) {
                    mData.clear();
                }
                noMoreData();
                meAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void error() {
            if (page == 1) {
                mData.clear();
            }
            noMoreData();
            meAdapter.notifyDataSetChanged();
        }
    };

    private List<CateBean.ResultBean> tabTile = new ArrayList<>();
    BaseView<CateBean> cateBeanBaseView = new BaseView<CateBean>() {
        @Override
        public void result(CateBean bean) {
            if (bean.getCode() == 200) {
                tabTile = bean.getResult();
                initMagicIndicator2(bean.getResult());
                mWindow = new CatePopupwindow(getContext(), tab.getHeight(), bean.getResult(), new CatePopupwindow.OnOptionLister() {
                    @Override
                    public void map(Map<String, Integer> map) {
                        mJiantou.setImageResource(R.mipmap.jiantou_down);
                        for (int i = 0; i < tabTile.size(); i++) {
                            tabTile.get(i).setSel(false);
                        }
                        index = map.get("id");
                        vp1.setCurrentItem(index);
                        cate_id = tabTile.get(map.get("id")).getId();
                        shuaxin1();
                    }
                });
            }
        }

        @Override
        public void error() {
        }
    };

    int page = 1;

    void shuaxin1() {
        page = 1;
        review.scrollToPosition(offsetTotal);
        presenter.getSpendData(url, cate_id, type, page, goodView);

    }

    //下拉刷新
    public void shuaxin() {
        review.scrollToPosition(0);
        pullRefreshLayout.setRefreshing(false);
        if (NetworkUtils.isNetAvailable(getContext())) {
            page = 1;
            try {
                presenter.getCate(cateBeanBaseView);
            } catch (Exception e) {
            }
            //获取新的轮播图
            presenter.getBanner("2", new BaseView<BannerBean>() {
                @Override
                public void result(BannerBean bean) {
                    if (bean.getCode() == 200) {
                        HomeDataDBUtil.saveBanner(getContext(), bean);
                        setBannerUI(bean);
                    }
                }

                @Override
                public void error() {

                }
            });
            //第一层分类
            presenter.getMs3(new BaseView<MsBean>() {
                @Override
                public void result(MsBean bean) {
                    bean.setType("1");
                    HomeDataDBUtil.saveClassify(getContext(), bean);
                    menuAdapter.setNewData(bean.getResult());
                }

                @Override
                public void error() {

                }
            });
        }
    }

    //初始化数据
    private void initData() {
        review.scrollToPosition(0);
        pullRefreshLayout.setRefreshing(false);
        if (NetworkUtils.isNetAvailable(getContext())) {
            page = 1;
            try {
                presenter.getCate(cateBeanBaseView);
            } catch (Exception e) {
            }
            //先获取数据库
            String[] ss = {"1"};
            MsBean mClassify_xiao = HomeDataDBUtil.getClassify(getContext(), "type", ss);
            if (mClassify_xiao.getMsg().equals("")) {
                presenter.getMs3(new BaseView<MsBean>() {
                    @Override
                    public void result(MsBean bean) {
                        bean.setType("1");
                        HomeDataDBUtil.saveClassify(getContext(), bean);
                        menuAdapter.setNewData(bean.getResult());
                    }

                    @Override
                    public void error() {

                    }
                });
            } else {
                menuAdapter.setNewData(mClassify_xiao.getResult());
            }

            //第二层分类
           /* String[] sss = {"2"};
            MsBean mClassify_er = HomeDataDBUtil.getClassify(getContext(), "type", sss);
            Log.i("tiancao", "数据" + mClassify_er.toString());
            if (mClassify_er.getMsg().equals("")) {
                presenter.getMs(new BaseView<MsBean>() {
                    @Override
                    public void result(MsBean bean) {
                        if (bean.getCode() == 200) {
                            bean.setType("2");
                            HomeDataDBUtil.saveClassify(getContext(), bean);
                            setClassifysUI(bean);
                        }
                    }

                    @Override
                    public void error() {

                    }
                });
            } else {
                setClassifysUI(mClassify_er);
            }*/
            presenter.getMs(msBeanBaseView);

            presenter.getSpendData(url, cate_id, type, page, goodView);

        }
    }

    /*
      今日必买
     */
    private ClassifyData jdbean = new ClassifyData();

    //中间图片区
    private int mItem1;
    private int mItem2;
    private int mItem3;
    private int mItem4;
    private int mItem5;

    BaseView<MsBean> msBeanBaseView = new BaseView<MsBean>() {
        @Override
        public void result(final MsBean bean) {
            if (bean.getCode() == 200) {
                int num = imageViews.length <= bean.getResult().size() ? imageViews.length : bean.getResult().size();
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(20f);//设置圆角
                for (int i = 0; i < num; i++) {
                    if (bean.getResult().get(i).getSlide_name().equals("今日爆款")) {
                        imageViews[0].setImageURI(HttpAPI.HOST + bean.getResult().get(i).getBanner());
                        mItem1 = i;
                    }
                    if (bean.getResult().get(i).getSlide_name().equals("京东优选")) {
                        imageViews[1].getHierarchy().setRoundingParams(roundingParams);
                        imageViews[1].setImageURI(HttpAPI.HOST + bean.getResult().get(i).getBanner());
                        mItem2 = i;
                    }
                    if (bean.getResult().get(i).getSlide_name().equals("高佣好货")) {
                        imageViews[1].getHierarchy().setRoundingParams(roundingParams);
                        imageViews[1].setImageURI(HttpAPI.HOST + bean.getResult().get(i).getBanner());
                        mItem2 = i;
                    }
                    if (bean.getResult().get(i).getSlide_name().equals("天猫精选")) {
                        imageViews[2].getHierarchy().setRoundingParams(roundingParams);
                        imageViews[2].setImageURI(HttpAPI.HOST + bean.getResult().get(i).getBanner());
                        mItem3 = i;
                    }
                    if (bean.getResult().get(i).getSlide_name().equals("每日精品")) {
                        imageViews[3].getHierarchy().setRoundingParams(roundingParams);
                        imageViews[3].setImageURI(HttpAPI.HOST + bean.getResult().get(i).getBanner());
                        mItem4 = i;
                    }
                    if (bean.getResult().get(i).getSlide_name().equals("9.9")) {
                        imageViews[4].getHierarchy().setRoundingParams(roundingParams);
                        imageViews[4].setImageURI(HttpAPI.HOST + bean.getResult().get(i).getBanner());
                        mItem5 = i;
                    }
                }

                imageViews[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setItemClick(mItem1, bean);
                    }
                });
                imageViews[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setItemClick(mItem2, bean);
                    }
                });
                imageViews[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setItemClick(mItem3, bean);
                    }
                });
                imageViews[3].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setItemClick(mItem4, bean);
                    }
                });
                imageViews[4].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setItemClick(mItem5, bean);
                    }
                });
            }
        }

        @Override
        public void error() {
        }
    };

    //第二层分类的点击
    private void setItemClick(int i, final MsBean bean) {
        ClassifyData item = bean.getResult().get(i);
//        Log.i("tiancao", item.toString());
        switch (item.getType()) {
            case "2"://接口
                if (item.getCopy().equals("京东优选")) {
                    if (Token.isLogin()) {
                        jdbean = item;
                        if (!KeplerApiManager.getWebViewService().isKeplerLogined()) {
                            // 直接授权登录京东​
                            KeplerApiManager.getWebViewService().login(
                                    getActivity(), mLoginListener);
                        } else {
                            JDActivity.openMain(getContext(), item, 4);
                        }

                    } else {
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                } else {
                    AllGoodsActivity.openMain(getContext(), item, 2);
                }
                break;

            case "1"://H5
                if (item.getStyle().equals("1")) {
                    if (Token.isLogin()) {
                        jdbean = item;
                        if (!KeplerApiManager.getWebViewService().isKeplerLogined()) {
                            // 直接授权登录京东​
                            KeplerApiManager.getWebViewService().login(
                                    getActivity(), mLoginListener);
                        } else {
                            KeplerAttachParameter mKeplerAttachParameter = new KeplerAttachParameter();//这个是即时性参数  可以设置
                            try {
                                KeplerApiManager.getWebViewService().openJDUrlWebViewPage(item.getUrl(),
                                        mKeplerAttachParameter);
                            } catch (KeplerBufferOverflowException e) {
                                e.printStackTrace();
                                Logger.e("fffffff", "KeplerBufferOverflowException == " + e);
                            }
                        }
                    } else {
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                } else
                    WebActivity.openMain(getContext(), item.getSlide_name(), item.getUrl());
                break;
            case "3"://页面
                try {
                    startActivity(new Intent(getContext(), Class.forName(getActivity().getPackageName() + ".activity." + item.getUrl())));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.renwu, R.id.serach, R.id.msg, R.id.floBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.renwu://签到
                if (Token.isLogin()) {
                    startActivity(new Intent(getContext(), SignActivity.class));
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }

                break;
            case R.id.serach://搜索
                startActivity(new Intent(getContext(), SouActivity.class));
                break;
            case R.id.msg:
                if (Token.isLogin()) {
                    startActivityForResult(new Intent(getContext(), NewsActivity.class), 25);
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;

            case R.id.floBtn:
                floBtn.setVisibility(View.GONE);
                review.scrollToPosition(0);
                break;


        }

    }


    private void selTextColor(int position) {
        for (int i = 0; i < sIvs.length; i++) {
            sIvs[i].setImageResource(R.mipmap.san);
        }
        option = position;
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setTextColor(getResources().getColor(R.color.dark_grey));
        }
        tvs[position].setTextColor(getResources().getColor(R.color.main));
    }


    public void noMoreData() {
        if (loadMoreWrapper != null) {
            loadMoreWrapper.setLoadMoreEnabled(false);
            if (!loadMoreWrapper.getShowNoMoreEnabled())
                loadMoreWrapper.setShowNoMoreEnabled(true);
            loadMoreWrapper.getOriginalAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
        if (enabled.getLoadMoreEnabled()) {
            page++;
            presenter.getSpendData(url, cate_id, type, page, goodView);
        } else {
            if (loadMoreWrapper != null) {
                loadMoreWrapper.getOriginalAdapter().notifyDataSetChanged();
            }
        }
    }


    LoginListener mLoginListener = new LoginListener() {
        @Override
        public void authSuccess(final Object token) {
            switch (jdbean.getType()) {
                case "2":
                    JDActivity.openMain(getContext(), jdbean, 4);
                    break;
                case "1":
                    KeplerAttachParameter mKeplerAttachParameter = new KeplerAttachParameter();//这个是即时性参数  可以设置
                    try {
                        KeplerApiManager.getWebViewService().openJDUrlWebViewPage(jdbean.getUrl(),
                                mKeplerAttachParameter);
                    } catch (KeplerBufferOverflowException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        @Override
        public void authFailed(final int errorCode) {
            switch (errorCode) {
                case KeplerApiManager.KeplerApiManagerLoginErr_Init:// 初始化失败
                    break;
                case KeplerApiManager.KeplerApiManagerLoginErr_InitIng:// 初始化没有完成
                    break;
                case KeplerApiManager.KeplerApiManagerLoginErr_openH5authPageURLSettingNull:// 跳转url
                    break;
                case KeplerApiManager.KeplerApiManagerLoginErr_getTokenErr:// 获取失败(oath授权之后，获取cookie过程出错)
                    break;
                case KeplerApiManager.KeplerApiManagerLoginErr_User_Cancel:// 用户取消
                    break;
                case KeplerApiManager.KeplerApiManagerLoginErr_AuthErr_ActivityOpen:// 打开授权页面失败
                    break;
                default:
                    break;
            }


        }
    };

    /**
     * 首页商品点击的回调
     */
    @Override
    public void mGoodsClick(GoodListBean.ResultBean hotBean) {
        if (Token.isLogin()) {
            TetrisActivity.openMain(getActivity(), hotBean, 6);
        } else {
            startActivity(new Intent(getContext(), LoginActivity.class));
        }
    }
}
