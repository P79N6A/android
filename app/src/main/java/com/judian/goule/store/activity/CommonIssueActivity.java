package com.judian.goule.store.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.CommonlssueData;
import com.judian.goule.store.bean.HelpBean;
import com.judian.goule.store.presenter.HelpPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/11/2.
 * 这个是常见问题
 */

public class CommonIssueActivity extends BaseActivity {

    @BindView(R.id.common_issue_list)
    ListView mListView;
    private CommonBaseAdapter<CommonlssueData.ResultBean> adapterProblem;
    private Unbinder bind;


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_issue);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        doBusiness(this);

    }

    public void doBusiness(Context mContext) {
        adapterProblem = AdapterUtil.getCommonIssueAdapter(CommonIssueActivity.this);

        mListView.setAdapter(adapterProblem);


        new HelpPresenter(this).getCommonLssue(new BaseView<CommonlssueData>() {
            @Override
            public void result(CommonlssueData bean) {

                adapterProblem.addAll(bean.getResult());
            }

            @Override
            public void error() {

            }
        });
    }

    @OnClick({R.id.common_issue_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_issue_back:
                finish();
                break;
        }
    }


}
