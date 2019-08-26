package com.judian.goule.store.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.ccy.ccyui.util.Logger;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.FragmentAdapter;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.LimitTimeBean;
import com.judian.goule.store.fragment.LimitFragment;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;


import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LimitActivity extends BaseActivity {


    @BindView(R.id.magic)
    MagicIndicator magicIndicator;
    @BindView(R.id.vp)
    ViewPager vp;
    private CdataPresenter presenter;
    private List<LimitTimeBean.ResultBean> mDataList;
    private int height;
    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit);
        bind = ButterKnife.bind(this);
        presenter = new CdataPresenter(this);
        height = magicIndicator.getLayoutParams().height;
         presenter.getLimitTime(new BaseView<LimitTimeBean>() {
             @Override
             public void result(LimitTimeBean bean) {
                 if (bean.getCode()==200){
                     mDataList =bean.getData();
                     initMagicIndicator1();
                 }
             }
             @Override
             public void error() {
             }
         });




    }

    int option=0;
    private void initMagicIndicator1() {

        final List<Fragment> list = new ArrayList<>();
            LimitFragment data = new LimitFragment();
            list.add(data);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list, null);
        vp.setAdapter(adapter);

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setSkimOver(true);
        int padding = UIUtil.getScreenWidth(this) / 2;
        commonNavigator.setRightPadding(padding);
        commonNavigator.setLeftPadding(padding);

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                CommonPagerTitleView localCommonPagerTitleView = new CommonPagerTitleView(context);
                localCommonPagerTitleView.setContentView(R.layout.item_limmit_time);
                TextView      tabTime = ((TextView)localCommonPagerTitleView.findViewById(R.id.time));
                TextView    tabState = ((TextView)localCommonPagerTitleView.findViewById(R.id.state));
                  tabTime.setText(mDataList.get(index).getNew_time());

                if (mDataList.get(index).getIs_start()==1){
                    tabState.setText("已开始");


                }else {
                    if (option==0){
                        option=index-1;
                    }
                    tabState.setText("未开始");
                }
                tabState.setText(mDataList.get(index).getIntro());

                localCommonPagerTitleView.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View paramAnonymous2View)
                    {
                       vp.setCurrentItem(index);
                    }
                });
                return localCommonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator localLinePagerIndicator = new LinePagerIndicator(context);
                Integer[] arrayOfInteger = new Integer[1];
                arrayOfInteger[0] = Integer.valueOf(Color.parseColor("#e92f63"));
                localLinePagerIndicator.setColors(arrayOfInteger);
                Logger.e("sssssssss","height == "+height);
                localLinePagerIndicator.setLineHeight(height);
                if (height==0){
                    localLinePagerIndicator.setLineHeight(48.0F);
                }
                return localLinePagerIndicator;
            }


//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
//                indicator.setFillColor(Color.parseColor("#e92f63"));
//                return indicator;
//            }





        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vp);
        vp.setCurrentItem(option);
        Logger.e("sssssssss","option 1 == "+option);
    }


    @OnClick({R.id.back, R.id.share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share:
                break;
        }
    }
}
