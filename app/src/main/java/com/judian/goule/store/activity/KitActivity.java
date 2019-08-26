package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class KitActivity extends BaseActivity implements BaseView<BaseBean> {


    @BindView(R.id.ensure)
    TextView ensure;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.jine)
    TextView jine;
    @BindView(R.id.kiting)
    TextView kiting;
    @BindView(R.id.ableKit)
    TextView ableKit;
    @BindView(R.id.djs)
    TextView djs;
    @BindView(R.id.kitList)
    RelativeLayout kitList;
    @BindView(R.id.kitMoney)
    TextView kitMoney;
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
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        doBusiness(this);
        setImmersionBar(2);
    }

    public void doBusiness(Context mContext) {
        title.setText("我的余额");
        presenter = new CdataPresenter(this);


    }

    @Override
    protected void onStart() {
        presenter.getKitInfo(this);
        super.onStart();
    }


    private void initView(final BaseBean.ResultBean user) {
        jine.setText(user.getAcc_gold());
        kitMoney.setText(user.getMin_money());
         kiting.setText(user.getMoney());
        ableKit.setText(user.getGold());
        djs.setText(user.getTotal());


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getKitInfo(this);

    }


    @Override
    public void finish() {
        setResult(20);


        super.finish();
    }

    @Override
    public void result(BaseBean bean) {

        initView(bean.getResult());


    }

    @Override
    public void error() {

    }


    @OnClick({R.id.back,R.id.kitList, R.id.goKit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.goKit:
                startActivityForResult(new Intent(this, Kit1Activity.class), 20);
                break;
            case R.id.kitList:
                startActivityForResult(new Intent(this, WithdrawalsRecordActivity.class), 20);
                break;
        }
    }
}
