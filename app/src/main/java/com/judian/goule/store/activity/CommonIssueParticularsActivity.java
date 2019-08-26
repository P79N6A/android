package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.CommonlssueData;
import com.judian.goule.store.presenter.HelpPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/11/2.
 * 这个是常见问题的问题详情
 */

public class CommonIssueParticularsActivity extends BaseActivity {

    @BindView(R.id.common_issue_particulars_wenti)
    TextView mWenti;
    @BindView(R.id.common_issue_particulars_daan)
    TextView mDaan;

    public static final String WENTI = "issue";
    public static final String DAAN = "answer";

    private Unbinder bind;


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    public static void openMain(Context context, String issue, String answer) {
        Intent intent = new Intent(context, CommonIssueParticularsActivity.class);
        intent.putExtra(WENTI, issue);
        intent.putExtra(DAAN, answer);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_issue_particulars);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        String mIssue = getIntent().getStringExtra(WENTI);
        String mAnswer = getIntent().getStringExtra(DAAN);


        mWenti.setText(mIssue);
        mDaan.setText(mAnswer.replace("\\n", "\n"));
    }

    @OnClick({R.id.common_issue_particulars_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_issue_particulars_back:
                finish();
                break;
        }
    }


}
