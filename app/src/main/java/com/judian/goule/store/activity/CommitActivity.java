package com.judian.goule.store.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ccy.ccyui.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CommitActivity extends BaseActivity {

    @BindView(R.id.ev)
    EditText mEv;
    @BindView(R.id.title)
    TextView mTitle;
    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit);
        bind = ButterKnife.bind(this);
        mTitle.setText("订单提交");
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.2f)
                .init();
    }

    @OnClick({R.id.back, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
               finish();
                break;
            case R.id.commit:
                String trim = mEv.getText().toString().trim();

                if (trim.equals("")){
                    ToastUtils.toast(this,"请输入订单号");
                }else {

                  new CdataPresenter(this).getCommOrder(trim, new BaseView<BaseBean>() {
                      @Override
                      public void result(BaseBean bean) {
                          ToastUtils.toast(CommitActivity.this,bean.getMsg());

                      }

                      @Override
                      public void error() {

                      }
                  });

                }


                break;
        }
    }
}
