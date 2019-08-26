package com.judian.goule.store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.JiFBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class IntActivity extends BaseActivity {

    @BindView(R.id.jifen)
    TextView jifen;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ensure)
    TextView ensure;
    private CdataPresenter presenter;
    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int);
        bind = ButterKnife.bind(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f)
                .init();
        title.setText("积分兑换");
        ensure.setText("积分记录");
        presenter = new CdataPresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getInt(new BaseView<JiFBean>() {
            @Override
            public void result(JiFBean bean) {

                if (bean.getCode() == 200) {
                    jifen.setText(bean.getResult().getAble_score());
                }

            }

            @Override
            public void error() {

            }
        });
    }

    @OnClick({R.id.back, R.id.goInt, R.id.ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.goInt:
                startActivity(new Intent(this, ExchangeActivity.class));
                break;

            case R.id.ensure:
                WithdrawalsRecordActivity.openMain(this,2);
                break;
        }
    }
}
