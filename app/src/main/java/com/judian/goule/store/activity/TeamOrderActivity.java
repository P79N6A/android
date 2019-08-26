package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.ccy.ccyui.adapter.StrFragmentAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.fragment.TeamAgentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TeamOrderActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.taoTab)
    TabLayout taoTab;
    @BindView(R.id.taoVp)
    ViewPager taoVp;

    private int mOption;
    private TeamAgentFragment    mData;
    private TeamAgentFragment mData1;
    private TeamAgentFragment mData2;
    private TeamAgentFragment mData3;
    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView( R.layout.activity_order2);
        bind = ButterKnife.bind(this);
        doBusiness(this);

        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.1f)
                .init();

    }

    private static final String POSITION = "OrderActivity";


    public void doBusiness(Context mContext) {

        title.setText("粉丝订单");
        inintVp(getList());
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    private void inintVp(List<String> cates) {
        mOption = getIntent().getIntExtra(POSITION,0);


        final List<Fragment> list = new ArrayList<>();

        mData = new TeamAgentFragment();
        mData.bind("1");
        mData1 = new TeamAgentFragment();
        mData1.bind("2");
        mData2 = new TeamAgentFragment();
        mData2.bind("3");
        mData3 = new TeamAgentFragment();
        mData3.bind("4");
        list.add(mData);
        list.add(mData1);
        list.add(mData2);
//        list.add(mData3);

        StrFragmentAdapter adapter = new StrFragmentAdapter(getSupportFragmentManager(), list, cates);
        taoVp.setAdapter(adapter);
        taoTab.setupWithViewPager(taoVp);
        taoVp.setCurrentItem(mOption);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mData3.shuaxin();
        mData1.shuaxin();
        mData.shuaxin();


    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("已付款");
        list.add("已失效");
        list.add("已结算");
//        list.add("已完成");
        return list;
    }


}
