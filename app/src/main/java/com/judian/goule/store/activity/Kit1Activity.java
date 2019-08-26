package com.judian.goule.store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ccy.ccyui.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.DialogUtils.AuditDialog;
import com.judian.goule.store.utils.DialogUtils.DisimissListener;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Kit1Activity extends AppCompatActivity implements BaseView<BaseBean> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.aliName)
    TextView aliName;
    @BindView(R.id.maney)
    EditText maney;
    @BindView(R.id.kitW)
    RelativeLayout kitW;
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
        setContentView(R.layout.activity_kit1);
        bind = ButterKnife.bind(this);
        ImmersionBar.with(this).fitsSystemWindows(false).init();
        title.setText("提取");
        presenter = new CdataPresenter(this);

    }

    @Override
    protected void onResume() {
        new CdataPresenter(this).getWiatKit(this);
        super.onResume();
    }

    @OnClick({R.id.back, R.id.goKit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.goKit:
                commit();
                break;
        }
    }

    String mAccount, money;

    private void commit() {
        money = maney.getText().toString().trim();

        if (mAccount.equals("")) {
            ToastUtils.toast(this, "请先绑定支付宝");
        } else {

            presenter.getKit(money, mAccount, new BaseView<BaseBean>() {
                @Override
                public void result(BaseBean bean) {
                    if (bean.getCode() == 200) {
                        maney.setText("");
                        maney.setHint("请输入提取金额，当前可提取" + bean.getResult().getGold() + "元");
                        setUi();
                    }else {
                        ToastUtils.toast(Kit1Activity.this,bean.getMsg());
                    }
                }

                @Override
                public void error() {

                }
            });


        }
    }

    private void setUi() {
        new AuditDialog(this, new DisimissListener() {
            @Override
            public void disimiss() {
                finish();
            }

            @Override

            public void progressDisimiss() {

            }
        }, "申请将在2个工作日内完成审核", "审核中......");
    }


    @Override
    public void result(BaseBean bean) {
        initView(bean.getResult());


    }

    private void initView(BaseBean.ResultBean result) {
        if (aliName == null) return;

        if (result.getIs_ali().equals("0")) {
            kitW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Kit1Activity.this, SzalipayActivity.class));

                }
            });

        } else {
            kitW.setOnClickListener(null);
            aliName.setText(result.getAli_account());
            maney.setHint("请输入提取金额，当前可提取" + result.getGold() + "元");
            name.setText(result.getName());
            mAccount = result.getAli_account();
        }

    }

    @Override
    public void error() {

    }
}
