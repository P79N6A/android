package com.judian.goule.store.activity.my;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.RegisterActivity;
import com.judian.goule.store.activity.scan.QRCodeScanActivity;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.presenter.MyDataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ReferralCodeVerificationAcitivy extends BaseActivity {

    @BindView(R.id.referral_code_verification_et)
    EditText mCode;
    @BindView(R.id.referral_code_verification_next_tv)
    TextView mNext;

    private Unbinder bind;
    private MyApplication application;
    private MyDataPresenter myPresenter;
    private boolean isClick = false;
    private String StringCode;

    public static final int CODESTIAN = 1009;
    public static final String CODE_NAME = "yp_code";
    private String tab = "";// 1  从淘宝登录过来  2 微信登录过来


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral_code_verification);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        application = (MyApplication) getApplication();
        myPresenter = new MyDataPresenter(this);
        tab = getIntent().getStringExtra("tab");
        upUI();
    }

    private void upUI() {
        mCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 4) {
                    mNext.setBackground(getResources().getDrawable(R.drawable.bg_gokit_btn));
                    isClick = true;
                } else {
                    mNext.setBackground(getResources().getDrawable(R.drawable.shixinyuan_huishe));
                    isClick = false;
                }
            }
        });
    }

    private void getUserInfo(String code) {
        myPresenter.verificationCode(code, new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                if (bean.getCode() == 200) {
                    if (tab != null) {

                        if (tab.equals("1")) {
                            Intent intent = new Intent();
                            intent.putExtra("yq_code", StringCode);
                            setResult(6688, intent);
                            finish();
                        } else if (tab.equals("2")) {
                            Intent intent = new Intent();
                            intent.putExtra("yq_code", StringCode);
                            setResult(6677, intent);
                            finish();
                        } else {
                            RegisterActivity.openMain(getApplicationContext(), 0, Integer.parseInt(StringCode));
                            finish();
                        }
                    }else {
                        RegisterActivity.openMain(getApplicationContext(), 0, Integer.parseInt(StringCode));
                        finish();
                    }
                } else if (bean.getCode() == 400) {
                    toast(bean.getMsg());
                }
            }

            @Override
            public void error() {
                toast("未知的推荐码");
            }
        });
    }


    @OnClick({R.id.referral_code_verification_back, R.id.referral_code_verification_scan_iv, R.id.referral_code_verification_next_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.referral_code_verification_back:
                finish();
                break;
            case R.id.referral_code_verification_scan_iv://扫一扫
                //申请权限
                String[] permissions = {Manifest.permission.CAMERA};
                if (Build.VERSION.SDK_INT >= 23) {
                    int REQUEST_CODE_CONTACT = 101;
                    //验证是否许可权限
                    for (String str : permissions) {
                        if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                            //申请权限
                            this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                        }
                    }
                }
                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED) {
                    toast("先获取照相权限");
                } else {
                    startActivityForResult(new Intent(this, QRCodeScanActivity.class), CODESTIAN);
                }
                break;
            case R.id.referral_code_verification_next_tv://下一步
                if (isClick) {
                    StringCode = mCode.getText().toString().trim();
                    getUserInfo(StringCode);
                } else {
                    toast("请输入正确的推荐码");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODESTIAN) {
            if (data != null) {
                String yq_code = data.getStringExtra(CODE_NAME);
                mCode.setText(yq_code);
            }
        }
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}
