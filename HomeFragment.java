package com.judian.goule.store.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.NetworkUtils;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.MyGridView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.kepler.jd.Listener.LoginListener;
import com.kepler.jd.login.KeplerApiManager;
import com.kepler.jd.sdk.bean.KeplerAttachParameter;
import com.kepler.jd.sdk.exception.KeplerBufferOverflowException;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.AgencyActivity;
import com.judian.goule.store.activity.AliWebActivity;
import com.judian.goule.store.activity.AllGoodsActivity;
import com.judian.goule.store.activity.CreateHbActivity;
import com.judian.goule.store.activity.JDActivity;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.activity.NewsActivity;
import com.judian.goule.store.activity.SignActivity;
import com.judian.goule.store.activity.SouActivity;
import com.judian.goule.store.activity.WebActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.adapter.ExamplePagerAdapter;
import com.judian.goule.store.adapter.ImageHolder;
import com.judian.goule.store.base.BaseFragment;
import com.judian.goule.store.bean.BannerBean;
import com.judian.goule.store.bean.CateBean;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.MsBean;
import com.judian.goule.store.bean.TaoTokenBean;
import com.judian.goule.store.bean.XuBean;
import com.judian.goule.store.behavior.RecyclerViewAppBarBehavior;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.listener.SwipyAppBarScrollListener;
import com.judian.goule.store.presenter.BannerPresenter;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.CatePopupwindow;
import com.judian.goule.store.view.WifiDialog;
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
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.review)
    RecyclerView review;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    Unbinder unbinder;
    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;


    @BindView(R.id.appbar)
    AppBarLayout appbar;

    private String cate_id = "";
    private String type = "all";

    @BindViews({R.id.img1, R.id.img2, R.id.img3, R.id.img4,R.id.img5, R.id.jkj, R.id.shij, R.id.jkj_7, R.id.shij_8})
    List<ImageView> imageViews;

    private int option;

    @BindView(R.id.shij)
    ImageView shij;

    @BindView(R.id.jkj_7)
    ImageView jkj_7;

    @BindView(R.id.shij_8)
    ImageView shij_8;
    @BindView(R.id.jkj)
    ImageView jkj;
    @BindView(R.id.tab)
    Toolbar tab;

    @BindView(R.id.spend)
    RelativeLayout spend;

    @BindView(R.id.myGv)
    MyGridView myGv1;

    @BindView(R.id.magic)
    MagicIndicator magicIndicator;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void immersionInit() {

    }
    @Override
    public void onResume() {
        Logger.e("gggggggg","vvvvvvv== SpendMoneyFragment 1");
        if (getActivity()!=null&& MainActivity.option==0){
            ImmersionBar.with(getActivity())
                    .fitsSystemWindows(true)
                    .statusBarColor(R.color.main)
                    .init();
        }

        super.onResume();
    }
    @BindView(R.id.floBtn)
    ImageView floBtn;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    private CdataPresenter presenter;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_Bitmap);
        pullRefreshLayout.setBackgroundColor(getResources().getColor(R.color.main));
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuaxin();
            }
            @Override
            public void onMove(boolean ismove) {

            }
        });
        CoordinatorLayout.LayoutParams  layoutParams= (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
        presenter = new CdataPresenter(getContext());
        initCoor();
        initBanner();
        initGv(getGvList());
        initList();
    }

    private void initCoor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mainContent.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                      Logger.e("eeeeee"," scrollY ="+scrollY);
                }
            });
        }


    }

    @Override
    protected void initData() {

    }

    private int totalDy = 0;
    private BaseQuickAdapter<GoodListBean.ResultBean,BaseViewHolder> homeData;
    private LinearLayoutManager linearLayoutManager,noLinearLayoutManager;
    private void initList() {
        linearLayoutManager = new GridLayoutManager(getContext(),2) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        noLinearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };


//        review.setHasFixedSize(true);
        review.setLayoutManager(linearLayoutManager);
        homeData= AdapterUtil.getSouData(getActivity(),new ArrayList<GoodListBean.ResultBean>());
        review.setFocusable(false);
        review.setAdapter(homeData);

//        list.setAdapter(adpter);
        homeData.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                 page++;
                presenter.getSpendData(url,cate_id, type, page, goodView);
            }
        });




        review.addOnScrollListener(new SwipyAppBarScrollListener(appbar, pullRefreshLayout, review, new SwipyAppBarScrollListener.Lintener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalDy += dy;
                if (totalDy>1000){

                    if (!flo)
                    floBtn.setVisibility(View.VISIBLE);
                }else {
                    floBtn.setVisibility(View.GONE);
                }

            }
        }));

        ViewGroup.LayoutParams  layoutParams1=   shij.getLayoutParams();
        layoutParams1.width= (int) (MyApplication.width/2);
        layoutParams1.height= (int) (layoutParams1.width*0.4826);
        shij.setLayoutParams(layoutParams1);
        jkj.setLayoutParams(layoutParams1);
        jkj_7.setLayoutParams(layoutParams1);
        shij_8.setLayoutParams(layoutParams1);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset)==0){
                    flo=false;
                }
            }
        });
        shuaxin();
    }

    private CommonBaseAdapter<XuBean.ResultBean> xuaAdapter;
    private void initGv(  List<XuBean.ResultBean>  list) {

        if (myGv1 == null) return;
        myGv1.setFocusable(false);
        myGv1.setSelector(new ColorDrawable(Color.TRANSPARENT));
        xuaAdapter = AdapterUtil.getGg(getActivity(), new AdapterUtil.HomeLintener() {
            @Override
            public void option(int op) {

            }
        });
        myGv1.setAdapter(xuaAdapter);
        xuaAdapter.addAll(list);

    }
    List<XuBean.ResultBean>   getGvList(){
        List<XuBean.ResultBean>  xuList=new ArrayList<>();
        xuList.add(new XuBean.ResultBean("1","女装配饰", R.mipmap.gv1));
        xuList.add(new XuBean.ResultBean("21","美妆日化", R.mipmap.gv2));
        xuList.add(new XuBean.ResultBean("2","天猫超市", R.mipmap.gv3));
        xuList.add(new XuBean.ResultBean("25","美食生鲜", R.mipmap.gv4));
        xuList.add(new XuBean.ResultBean("8","居家百货", R.mipmap.gv5));
        xuList.add(new XuBean.ResultBean("28","每日福利", R.mipmap.gv6));
        xuList.add(new XuBean.ResultBean("29","聚划算", R.mipmap.gv7));
        xuList.add(new XuBean.ResultBean("30","自营商品", R.mipmap.gv8));
        xuList.add(new XuBean.ResultBean("11","积分兑换", R.mipmap.gv9));
        xuList.add(new XuBean.ResultBean("31","晒单", R.mipmap.bto_item4));
        return xuList;
    }


    /**
     * s商品数据
     */
    BaseView<GoodListBean> goodView = new BaseView<GoodListBean>() {
        @Override
        public void result(GoodListBean bean) {
            if (bean.getCode() == 200) {
                if (change==2) {
//                    review.scrollToPosition(0);
//
                }

                if (page == 1) {
                    homeData.setNull();
                }

                homeData.addAll(bean.getResult());

                if (bean.getResult().size()<10)
                    homeData.loadMoreEnd();
                else homeData.loadMoreComplete();

            } else {
                if (page == 1) {
                    homeData.setNull();
                }
                homeData.loadMoreEnd();
            }

        }

        @Override
        public void error() {
            homeData.loadMoreFail();
            noNet();

        }
    };

      int page=1;
  String   url= HttpAPI.SEARCH;
    public void shuaxin() {
        review.scrollToPosition(0);
        pullRefreshLayout.setRefreshing(false);
        if (NetworkUtils.isNetAvailable(getContext())){
            page = 1;
            presenter.getCate(cateBeanBaseView);
            presenter.getMs(msBeanBaseView);
            presenter.getBanner("2",bannerBeanBaseView );
            presenter.getSpendData(url,cate_id, type, page, goodView);
        }else {

            noNet();
        }
    }






    void   noNet(){
        WifiDialog dialog=new WifiDialog(getContext(), new WifiDialog.WifiLintener() {
            @Override
            public void wifi() {
                page = 1;
//             presenter.getGg(gView);
                homeData.setNull();
                presenter.getCate(cateBeanBaseView);
//             presenter.getHotHome(1,xuBeanBaseView);
                presenter.getMs(msBeanBaseView);
                new BannerPresenter(getContext()).getBanner("2",bannerBeanBaseView );
                presenter.getSpendData(url,cate_id, type, page, goodView);

            }
        });

        dialog.show();



    }





    @BindView(R.id.imgss)
    LinearLayout mImgss;
    private MsBean.ResultBean jdbean;

    /*
      今日必买
     */
    BaseView<MsBean> msBeanBaseView= new BaseView<MsBean>() {
        @Override
        public void result(final MsBean bean) {
            if (bean.getCode() == 200) {
                mImgss.setVisibility(View.VISIBLE);
                int num= imageViews.size()<=bean.getResult().size()? imageViews.size():bean.getResult().size();

                for (int i = 0; i < num; i++) {
                    final MsBean.ResultBean resultBean = bean.getResult().get(i);
                    Picasso.with(getContext()).load(HttpAPI.HOST + resultBean.getBanner()).into(imageViews.get(i));

                    final int finalI = i;
                    imageViews.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MsBean.ResultBean resultBean=  bean.getResult().get(finalI);
                            switch (resultBean.getSlide_id()) {
                                case "39":
                                    AllGoodsActivity.openMain(getContext(),resultBean,2);

                                    break;
                                case "83"://京东商城
                                    jdbean = resultBean;
                                    jdbean.setStatus("83");
                                    if (Token.isLogin()){

                                        if (! KeplerApiManager.getWebViewService().isKeplerLogined()){
                                            // 直接授权登录京东​
                                            KeplerApiManager.getWebViewService().login(
                                                    getActivity(), mLoginListener);
                                        }else{

                                            KeplerAttachParameter mKeplerAttachParameter = new KeplerAttachParameter();//这个是即时性参数  可以设置
                                            try {
                                                KeplerApiManager.getWebViewService().openJDUrlWebViewPage(jdbean.getUrl(),
                                                        mKeplerAttachParameter);
                                            } catch (KeplerBufferOverflowException e) {
                                                e.printStackTrace();
                                                Logger.e("fffffff","KeplerBufferOverflowException == "+e);
                                            }
                                        }

                                    }else {

                                        startActivity(new Intent(getContext(),LoginActivity.class));
                                    }


                                    break;
                                case "44"://列表 9.9
                                    AllGoodsActivity.openMain(getContext(),resultBean,2);
                                    break;
                                case "82"://品牌特卖
                                    AllGoodsActivity.openMain(getContext(),resultBean,2);
                                    break;
                                case "43"://京东优选
                                    jdbean = resultBean;
                                    jdbean.setStatus("43");
                                    if (Token.isLogin()){

                                        if (! KeplerApiManager.getWebViewService().isKeplerLogined()){
                                            // 直接授权登录京东​
                                            KeplerApiManager.getWebViewService().login(
                                                    getActivity(), mLoginListener);
                                        }else
                                            JDActivity.openMain(getContext(),resultBean,4);

                                    }else {

                                        startActivity(new Intent(getContext(),LoginActivity.class));
                                    }


                                    break;

                                case "84"://唯品会
                                    WebActivity.openMain(getContext(),"唯品会",resultBean.getUrl());
                                    break;
                                case "85"://每日爆款
                                    AllGoodsActivity.openMain(getContext(),resultBean,2);
                                    break;


                                case "51"://
                                    if (Token.isLogin()){
                                        if (Token.getAgent().equals("2"))
                                            startActivity(new Intent(getContext(),CreateHbActivity.class));
                                        else
                                            startActivity(new Intent(getContext(),AgencyActivity.class));
                                    }else {
                                        startActivity(new Intent(getContext(),LoginActivity.class));
                                    }
                                    break;

                            }


                        }
                    });
                }
            } else {
            }
        }


        @Override
        public void error() {

        }
    };






    @BindView(R.id.banner)
    ConvenientBanner<BannerBean.DataBean> banner;

    private void initBanner() {
       ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.width = MyApplication.width;
        layoutParams.height = (int) (layoutParams.width * 0.41);
        banner.setLayoutParams(layoutParams);
        banner.startTurning(4000);

    }
    private int change = 0;
    @BindViews({R.id.juaneIv, R.id.jiagIv})
    List<ImageView> sIvs;
    @BindViews({R.id.zh, R.id.sl, R.id.juaneTv, R.id.jiagTv, R.id.jies})
    List<TextView> tvs;

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





    BaseView<BannerBean> bannerBeanBaseView=  new BaseView<BannerBean>() {
        @Override
        public void result(final BannerBean bean) {

            banner.setPages(new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return new ImageHolder();
                }
            }, bean.getData()).setPageIndicator(new int[]{R.mipmap.ic_dot_normal, R.mipmap.ic_dot_pressed})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

            banner.setOnItemClickListener(new OnItemClickListener() {


                @Override
                public void onItemClick(int position) {
                    BannerBean.DataBean dataBean = bean.getData().get(position);
//                        start(getContext());


                    switch (dataBean.getStyle()) {

                        case "1"://商品
                            if (Token.isLogin()){
                                new CdataPresenter(getContext()).getTaoToken( bean.getData().get(position).getNum_iid(), new BaseView<TaoTokenBean>() {
                                    @Override
                                    public void result(TaoTokenBean bean) {
                                        if (bean.getCode()==200){
                                            AliWebActivity.openXQ(getContext(),bean.getResult(),6);

                                        }else {
                                            ToastUtils.toast(getContext(),bean.getMsg());
                                        }
                                    }
                                    @Override
                                    public void error() {

                                    }
                                });
                            }else {
                                startActivity(new Intent(getContext(), LoginActivity.class));
                            }

                            break;
                        case "2"://列表
//                            RecyActivity.openMain(getContext(), 1, bean.getData().get(position));
                            break;

                        case "3"://h5
                            WebActivity.openMain(getContext(), dataBean.getSlide_name(), dataBean.getUrl());
                            break;
                    }

                }
            });


        }

        @Override
        public void error() {

        }
    };
    @BindView(R.id.jiantou)
    ImageView mJiantou;
    private CatePopupwindow mWindow;
    private List<CateBean.ResultBean> tabTile = new ArrayList<>();
    BaseView<CateBean> cateBeanBaseView=  new BaseView<CateBean>() {
        @Override
        public void result(CateBean bean) {
            if (bean.getCode() == 200) {
                tabTile = bean.getResult();
                initMagicIndicator2(bean.getResult());
                mWindow = new CatePopupwindow(getContext(),tab.getHeight(), bean.getResult(), new CatePopupwindow.OnOptionLister() {
                    @Override
                    public void map(Map<String, Integer> map) {
                        mJiantou.setImageResource(R.mipmap.jiantou_down);
                        for (int i = 0; i < tabTile.size(); i++) {
                            tabTile.get(i).setSel(false);
                        }
                        vp1.setCurrentItem(map.get("id"));
                        cate_id = tabTile.get(map.get("id")).getId();
                        change = 2;
                        shuaxin1();
                    }
                });


            }
        }

        @Override
        public void error() {
        }
    };


    @BindView(R.id.vp1)
    ViewPager vp1;
    private void initMagicIndicator2(final List<CateBean.ResultBean> resultBeen) {
//        magicIndicator.setBackgroundColor(Color.parseColor("#00c853"));
        ExamplePagerAdapter mExamplePagerAdapter = new ExamplePagerAdapter(resultBeen);
        vp1.setAdapter(mExamplePagerAdapter);
        cate_id=resultBeen.get(0).getId();
        shuaxin1();

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
                simplePagerTitleView.setSelectedColor(Color.parseColor("#FFA4C5"));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp1.setCurrentItem(index);
                        change = 2;
                        cate_id=  resultBeen.get(index).getId();
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
                linePagerIndicator.setColors(Color.parseColor("#FFA4C5"));
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vp1);
    }


    @BindView(R.id.tabll)
    RelativeLayout tabll;

    public void shuaxin1() {
        if (cate_id.equals("114")){
            tabll.setVisibility(View.GONE);
        }else {
            tabll.setVisibility(View.VISIBLE);
        }
        review.scrollToPosition(0);
        totalDy=0;
        page = 1;
        presenter.getSpendData(url,cate_id, type, page, goodView);

    }

    public void jiazai() {
        ++page;
        presenter.getSpendData(url,cate_id, type, page, goodView);

    }

    String fanli = "fanli", couponAmount = "couponAmount", price = "price";
    private boolean flo=false;
    @OnClick({R.id.serach, R.id.msg, R.id.renwu, R.id.floBtn,  R.id.move, R.id.zh, R.id.sl, R.id.juane, R.id.jiag, R.id.jies})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.serach:
                startActivity(new Intent(getContext(), SouActivity.class));
                break;
            case R.id.floBtn:
                review.scrollToPosition(0);
                totalDy = 0;
//                barBehavior.setScrolledY(0);
                appbar.setExpanded(true,true);
                floBtn.setVisibility(View.GONE);
                flo = true;

                break;

            case R.id.renwu:
//                getActivity().startActivityForResult(new Intent(getContext(), UserActivity.class), 22);
                if (Token.isLogin()){
                    startActivity(new Intent(getContext(), SignActivity.class));
                }else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }

                break;
            case R.id.msg:
                if (Token.isLogin()) {

                    startActivityForResult(new Intent(getContext(), NewsActivity.class),25);
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;

            case R.id.move:
                if (mWindow != null) {
                    mWindow.showAtLocation(spend, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    mJiantou.setImageResource(R.mipmap.jiantou_up);
                }

                break;


            case R.id.zh:
                selTextColor(0);
                if (type.equals("all")) {

                } else {
                    type = "all";
                    change = 2;
                    shuaxin1();
                }


                break;
            case R.id.sl:
                selTextColor(1);
                if (type.equals("sales")) {

                } else {
                    type = "sales";
                    change = 2;
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
                change = 2;
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

                change = 2;
                type = price;
                shuaxin1();


                break;
            case R.id.jies:
                selTextColor(4);
                if (type.equals("new")) {

                } else {
                    type = "new";
                    change = 2;
                    shuaxin1();
                }

                break;









        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    LoginListener mLoginListener = new LoginListener() {

        @Override
        public void authSuccess(final Object token) {
              switch (jdbean.getStatus()){
                  case "43":
                      JDActivity.openMain(getContext(),jdbean,4);
                      break;

                  case "83":
                      KeplerAttachParameter mKeplerAttachParameter = new KeplerAttachParameter();//这个是即时性参数  可以设置
                      try {
                          KeplerApiManager.getWebViewService().openJDUrlWebViewPage(jdbean.getUrl(),
                                  mKeplerAttachParameter);
                      } catch (KeplerBufferOverflowException e) {
                          e.printStackTrace();
                          Logger.e("fffffff","KeplerBufferOverflowException == "+e);
                      }

                      break;


              }


            Logger.e("fffffff", "app mLoginListener"+token);
        }

        @Override
        public void authFailed(final int errorCode) {
            Logger.e("fffffff", "app authFailed"+errorCode);
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


}
