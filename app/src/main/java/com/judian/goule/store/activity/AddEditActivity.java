package com.judian.goule.store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.example.ccy.ccyui.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddEditActivity extends BaseActivity implements BaseView<UserInfo> {

    @BindView(R.id.addEd_city)
    TextView addEdCity;
    @BindView(R.id.addEd_add)
    EditText addEdAdd;
    @BindView(R.id.addEd_top_tv)
    TextView addEdTopTv;

    private int option=0;
    private CdataPresenter mPresenter;
    private Unbinder bind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        bind = ButterKnife.bind(this);
        mPresenter = new CdataPresenter(this);
       mPresenter.getUserInfo(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.2f)
                .init();


    }




    @OnClick({R.id.addEd_btn, R.id.baocun, R.id.addEd_city})
    public void onClick(View view) {
        Log.d("ccc", "onClick: "+view.getId());
        switch (view.getId()) {
            case R.id.addEd_btn:
                finish();
                break;
            case R.id.baocun:
                   commit();
                break;
            case R.id.addEd_city:
                selCity();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    private void commit() {
        String trim = addEdAdd.getText().toString().trim();
         if (trim.equals("")){

             ToastUtils.toast(AddEditActivity.this,"详细地址不能为空");


         }else {

             mPresenter.saveAddress(shengname, shiname, quname,trim, new BaseView<BaseBean>() {
                 @Override
                 public void result(BaseBean bean) {
                     ToastUtils.toast(AddEditActivity.this, bean.getMsg());
                     if (bean.getCode() == 200) {
                         setResult(20);
                         finish();
                     }
                 }

                 @Override
                 public void error() {

                 }
             });



         }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.getUserInfo(this);


    }

    private void selCity() {

        startActivityForResult(new Intent(this, CityListActivity.class), 12);
    }


    private String shiname, shengname, quname, address, addressId;


    @Override
    public void result(UserInfo bean) {
        quname=bean.getResult().getDistrict();
        shengname=bean.getResult().getProvince();
        shiname=bean.getResult().getCity();
        addEdCity.setText(shengname+"--"+shiname+"--"+quname);
    }

    @Override
    public void error() {

    }

    @Override
    public void finish() {
        setResult(20);
        super.finish();
    }
}
