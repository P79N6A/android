package com.judian.goule.store.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.ccy.ccyui.adapter.StrFragmentAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.fragment.BounsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BounsActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;


    @BindView(R.id.taoTab)
    TabLayout taoTab;
    @BindView(R.id.taoVp)
    ViewPager taoVp;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouns);
        bind = ButterKnife.bind(this);
        title.setText("红包记录");
        taoTab.setVisibility(View.GONE);
        inintVp(getList());

    }


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }



    private void inintVp(List<String> cates) {

        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.1f)
                .init();

        final List<Fragment> list = new ArrayList<>();

        BounsFragment data = new BounsFragment();
        data.bind("1");
        BounsFragment data1 = new BounsFragment();
        data1.bind("2");
        BounsFragment data3 = new BounsFragment();
        data3.bind("3");
        list.add(data);
//        list.add(data1);
//        list.add(data3);
        StrFragmentAdapter adapter = new StrFragmentAdapter(getSupportFragmentManager(), list, cates);
        taoVp.setAdapter(adapter);
//        taoTab.setupWithViewPager(taoVp);
    }


    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("未使用");
        list.add("已使用");
        list.add("已过期");
        return list;
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
