package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.util.Utils;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SzalipayActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.getCode)
    TextView getCode;


    private static final String POSITION = "MainActivity";
    @BindView(R.id.aliEt)
    EditText mAliEt;
    @BindView(R.id.nameet)
    EditText mNameet;
    @BindView(R.id.code)
    EditText code;
    private CdataPresenter mPresenter;
    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        mPresenter.dismiss();
        super.onDestroy();
    }

    public static void openMain(Context context, int position) {
        Intent intent = new Intent(context, SzalipayActivity.class);
        intent.putExtra(POSITION, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_szalipay);
        bind = ButterKnife.bind(this);
        doBusiness(this);
        setImmersionBar(2);
    }


    public void doBusiness(Context mContext) {
        title.setText("绑定支付宝");
        mPresenter = new CdataPresenter(this);
    }

    private boolean isGetCode = false;
    private int tiem = 60;

    @OnClick({R.id.back, R.id.getCode, R.id.alipay_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.alipay_butn:
                saveAccount();
                break;
            case R.id.getCode:
                if (mAliEt.getText().toString().trim().equals("")) {
                    ToastUtils.toast(this, "请输入支付宝账号");
                    return;
                }
                if (!isGetCode) {
                    postCode(Token.getPhone(), "5");
                }
                break;
        }
    }


    private void postCode(String phone, String type) {
        if (phone.equals("") || !Utils.isPhone(phone)) {
            toast("请输入手机号");
        } else {
            mPresenter.getCode(phone, type, new BaseView<BaseBean>() {
                @Override
                public void result(BaseBean bean) {
                    ToastUtils.toast(SzalipayActivity.this, bean.getMsg());
                    if (bean.getCode() == 200) {
                        isGetCode = true;
                        new Thread(new SzalipayActivity.MThread()).start();
                    } else {


                    }
                }

                @Override
                public void error() {

                }
            });
        }
    }


    private void saveAccount() {

        String name = mNameet.getText().toString().trim();
        if (name.equals("")) {
            ToastUtils.toast(this, "请输入支付宝名称");
            return;
        }
        final String account = mAliEt.getText().toString().trim();
        if (account.equals("")) {
            ToastUtils.toast(this, "请输入支付宝账号");
            return;
        }
        String captcha = code.getText().toString().trim();
        if (captcha.equals("")) {
            ToastUtils.toast(this, "请输入验证码");
            return;
        }
        mPresenter.addAli(name, account, captcha, new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                MyApplication.ali = account;
                ToastUtils.toast(SzalipayActivity.this, "绑定成功");
                finish();
            }

            @Override
            public void error() {
            }
        });
    }

    @Override
    public void finish() {
        setResult(20);
        super.finish();
    }


    //获取验证码计
    class MThread extends Thread {

        @Override
        public void run() {
            while (isGetCode) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isGetCode) {
                            if (getCode != null) {
                                getCode.setTextColor(getResources().getColor(R.color.white));
                                getCode.setText("重新获取：" + tiem);
                                getCode.setBackgroundResource(R.drawable.bg_vcode_n);
                            }
                        }

                        if (tiem == 0) {
                            isGetCode = false;
                            tiem = 60;
                            if (getCode == null) return;
                            getCode.setTextColor(getResources().getColor(R.color.white));
                            getCode.setText("重新获取验证码");
                            getCode.setBackgroundResource(R.drawable.bg_vcode_ali);
                        }
                    }
                });
                try {
                    Thread.sleep(1000);
                    tiem--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }
    }


}
