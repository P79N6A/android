package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.example.ccy.ccyui.adapter.CommonViewHolder;
import com.example.ccy.ccyui.adapter.StrFragmentAdapter;
import com.example.ccy.ccyui.bean.HomeCate;
import com.example.ccy.ccyui.view.MyListView;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.db.NewsBean;
import com.judian.goule.store.db.NewsDao;
import com.judian.goule.store.fragment.my.EarningsMessagesFragment;
import com.judian.goule.store.fragment.my.SystemMessagesFragment;
import com.judian.goule.store.utils.TestData;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NewsActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.messagesVp)
    NoScrollViewPager messagesVp;

    @BindView(R.id.earnings_messages_tv)
    TextView mEarnings_text;
    @BindView(R.id.earnings_messages_view)
    View mEarnings_view;

    @BindView(R.id.system_messages_tv)
    TextView mSystem_text;
    @BindView(R.id.system_messages_view)
    View mSystem_view;

    private Unbinder bind;


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        doBusiness();
    }

    public void doBusiness() {
        title.setText("消息中心");
        List<String> list = new ArrayList<>();
        SystemMessagesFragment systemMessagesFragment = new SystemMessagesFragment();
        EarningsMessagesFragment earningsMessagesFragment = new EarningsMessagesFragment();
        final List<Fragment> mFragmentlist = new ArrayList<>();
        mFragmentlist.add(earningsMessagesFragment);
        mFragmentlist.add(systemMessagesFragment);

        StrFragmentAdapter adapter = new StrFragmentAdapter(getSupportFragmentManager(), mFragmentlist, list);
        messagesVp.setAdapter(adapter);
        messagesVp.setCurrentItem(0);
    }

    @OnClick({R.id.back, R.id.earnings_messages_rl, R.id.system_messages_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.earnings_messages_rl:
                mEarnings_text.setTextColor(getResources().getColor(R.color.main));
                mEarnings_view.setVisibility(View.VISIBLE);
                mSystem_text.setTextColor(getResources().getColor(R.color.add_txt));
                mSystem_view.setVisibility(View.GONE);
                messagesVp.setCurrentItem(0);
                break;
            case R.id.system_messages_rl:
                mEarnings_text.setTextColor(getResources().getColor(R.color.add_txt));
                mEarnings_view.setVisibility(View.GONE);
                mSystem_text.setTextColor(getResources().getColor(R.color.main));
                mSystem_view.setVisibility(View.VISIBLE);
                messagesVp.setCurrentItem(1);
                break;
        }
    }

}
