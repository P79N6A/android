package com.judian.goule.store.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ccy.ccyui.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import com.judian.goule.store.R;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.TestData;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.SelftaoDialog;
import com.judian.goule.store.views.BaseView;
import com.umeng.analytics.MobclickAgent;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UserActivity extends TakePhotoActivity implements BaseView<UserInfo> {



    @BindView(R.id.cell_tv)
    TextView cellTv;


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.activity_user)
    LinearLayout activityUser;
     String  mPhone;
    private CdataPresenter presenter;


    private boolean isImg=false;
    private UserInfo.ResultBean user;
    private Unbinder bind;
    @Override
    protected void onPause() {
        MobclickAgent.onPause(this);
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        bind.unbind();
        presenter.dismiss();
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        String key = TestData.getClipboardText(this);
        if (key != null && !key.equals("")) {
            if ( Token.isBCopy(key))
                return;
            SelftaoDialog dialog = new SelftaoDialog(this, key);
            dialog.show();
        }
        MobclickAgent.onResume(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        bind = ButterKnife.bind(this);
        ImmersionBar.with(this).fitsSystemWindows(false).init();
        presenter = new CdataPresenter(this);
        initTab();


    }
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImages());
    }
    private void showImg(ArrayList<TImage> images)
    {
    }
    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }


    @Override
    public void takeCancel() {

        super.takeCancel();
    }

    @Override
    protected void onStart() {
        presenter.getUserInfo(this);
        super.onStart();
    }

    private void initView(UserInfo.ResultBean  user) {
        this.user = user;
          cellTv.setText(user.getPhone());
        mPhone=user.getPhone();



    }





    private void initTab() {
        title.setText("个人资料");

    }


    @OnClick({R.id.back,R.id.pass, R.id.cell})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.pass:
                RegisterActivity.openMain(getApplicationContext(), 8);
                break;


            case R.id.cell:
                if (mPhone == null || mPhone.equals(""))
                    startActivityForResult(new Intent(this, TelActivity.class), 16);
                else TelActivity.openMain(this, mPhone);

                break;



        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getUserInfo(this);
    }



    @Override
    public void result(UserInfo user) {
                if (user.getCode().equals("200")){
                    initView(user.getResult());
                }else {
                    ToastUtils.toast(this,user.getMsg());
                }

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
