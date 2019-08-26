package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.FragmentAdapter;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.ImageBean;
import com.judian.goule.store.fragment.MyOrderFragment;
import com.judian.goule.store.fragment.SorderFragment;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SorderActivity extends BaseActivity {

    @BindView(R.id.num)
    TextView mNum;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private List<Fragment> mList;
    private int colorSel, dark_grey;
    private MyOrderFragment mMyOrderFragment;

    private static final String POSITION = "SorderActivity";
    private int option;
    private Unbinder bind;

    public static void openMain(Context context, int position) {
        Intent intent = new Intent(context, SorderActivity.class);
        intent.putExtra(POSITION, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorder);
        bind = ButterKnife.bind(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.2f)
                .init();
        option = getIntent().getIntExtra(POSITION,0);

        colorSel = getResources().getColor(R.color.tab_s);
        dark_grey = getResources().getColor(R.color.dark_greyt);


        shuxin();
        inintVp();

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tv = (TextView) tab.getCustomView().findViewById(R.id.news_title);
                tv.setTextColor(colorSel);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tv = (TextView) tab.getCustomView().findViewById(R.id.news_title);
                tv.setTextColor(dark_grey);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        shuxin();
    }

    public void shuxin(){
          if (mNum==null)return;
          new CdataPresenter(this).getZanNum(new BaseView<BaseBean>() {
              @Override
              public void result(BaseBean bean) {
                  if (bean.getCode()==200){
                      mNum.setText(bean.getResult().getNum());
                      if (bean.getResult().getNum().equals("0")){
                          mNum.setVisibility(View.GONE);
                      }else {
                          mNum.setVisibility(View.VISIBLE);
                      }
                  }
              }

              @Override
              public void error() {

              }
          });



      }



    private void inintVp() {

        mList = new ArrayList<>();
        SorderFragment sorderFragment=new SorderFragment();
        sorderFragment.bind("1");
        SorderFragment sorderFragment1=new SorderFragment();
        sorderFragment1.bind("2");
        mMyOrderFragment = new MyOrderFragment();

        mList.add(sorderFragment);
        mList.add(sorderFragment1);
        mList.add(mMyOrderFragment);
        List<ImageBean> cates=new ArrayList<>();
              cates.add(new ImageBean(R.mipmap.new1,"最新晒单"));
              cates.add(new ImageBean(R.mipmap.hot,"热门晒单"));
              cates.add(new ImageBean(R.mipmap.my,"我的晒单"));



        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), mList, cates);
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);
        for (int i = 0; i < cates.size(); i++) {
            TabLayout.Tab tab = mTab.getTabAt(i);
            tab.setCustomView(adapter.getTabView(this, i));
        }
        if (cates.size()!=0){
            TextView tv = (TextView) mTab.getTabAt(option).getCustomView().findViewById(R.id.news_title);
            tv.setTextColor(colorSel);
        }
      mVp.setCurrentItem(option);
        mTab.setScrollPosition(option,0,true);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mMyOrderFragment!=null){
            mMyOrderFragment.shuaxin();
        }

    }

    @OnClick({R.id.back, R.id.comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.comment:
                startActivityForResult(new Intent(this,MyComActivity.class),45);

                break;
        }
    }








}
