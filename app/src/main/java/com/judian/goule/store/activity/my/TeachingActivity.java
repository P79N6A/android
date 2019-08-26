package com.judian.goule.store.activity.my;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.CenterActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.bean.HelpBean;
import com.judian.goule.store.im.BaseActivity;
import com.judian.goule.store.presenter.HelpPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 新手教程
 */
public class TeachingActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView listView;

    private CommonBaseAdapter<HelpBean.ResultBean> adapterProblem;

    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        doBusiness();
    }


    public void doBusiness() {
        adapterProblem = AdapterUtil.getArticle(this);

        listView.setAdapter(adapterProblem);


        new HelpPresenter(this).getHelp("", new BaseView<HelpBean>() {
            @Override
            public void result(HelpBean bean) {
                adapterProblem.addAll(bean.getResult());
            }

            @Override
            public void error() {

            }
        });
    }

    @OnClick({R.id.help_back, R.id.help_kefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.help_back:
                finish();
                break;
            case R.id.help_kefu:
                startActivity(new Intent(this, CenterActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_help;
    }
}
