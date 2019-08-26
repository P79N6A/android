package com.judian.goule.store.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.ExamplePagerAdapter;
import com.judian.goule.store.bean.CateBean;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/7.
 */

public class HomeView extends FrameLayout {
    @BindView(R.id.banner)
    ConvenientBanner banner;

    public HomeView(@NonNull Context context) {
        this(context, null);
    }

    public HomeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private View mMenuView;

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.home_view, null);
        ButterKnife.bind(this, mMenuView);
    }


    @BindView(R.id.vp1)
    ViewPager vp1;
    private void initMagicIndicator2(final List<CateBean.ResultBean> resultBeen) {
//        magicIndicator.setBackgroundColor(Color.parseColor("#00c853"));
        ExamplePagerAdapter mExamplePagerAdapter = new ExamplePagerAdapter(resultBeen);
        vp1.setAdapter(mExamplePagerAdapter);
//        cate_id=resultBeen.get(0).getId();
//        shuaxin1();

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
//                        change = 2;
//                        cate_id=  resultBeen.get(index).getId();
//                        mWindow.setState(index);
//                        shuaxin1();
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
//        magicIndicator.setNavigator(commonNavigator);
//        ViewPagerHelper.bind(magicIndicator, vp1);
    }







}
