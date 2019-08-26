package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.util.Utils;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.JointStringUtils;
import com.judian.goule.store.utils.RSAUtils;
import com.judian.goule.store.views.BaseView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TelActivity extends BaseActivity {

    @BindView(R.id.odlTel)
    TextView mOdlTel;
    @BindView(R.id.odl)
    LinearLayout mOdl;
    @BindView(R.id.telEt)
    EditText mTelEt;
    @BindView(R.id.codeEt)
    EditText mCodeEt;
    @BindView(R.id.codeGet)
    TextView mCodeGet;
    @BindView(R.id.title)
    TextView mTitle;
    private CdataPresenter presenter;

    private static final String POSITION = "TelActivity";
    private String mTel;
    private int mOption;
    private String mVphone;
    private String mTrim;
    private String mMCode;
    private Unbinder bind;
    private Thread thread;//线程


    public static void openMain(Context context, String tel) {
        Intent intent = new Intent(context, TelActivity.class);
        intent.putExtra(POSITION, tel);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        presenter.dismiss();
        isGetCode = false;
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);

        mTel = getIntent().getStringExtra(POSITION);
        if (mTel != null) {
            mTitle.setText("修改手机号");
            mOption = 1;
            mOdl.setVisibility(View.GONE);
            mTelEt.setText(mTel);
            mTelEt.setFocusable(false);

        } else {
            mTitle.setText("绑定手机号");
            mOption = 0;
            mOdl.setVisibility(View.GONE);
        }

        presenter = new CdataPresenter(this);

    }

    @OnClick({R.id.back, R.id.codeGet, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.codeGet:
                if (!isGetCode) {
                    getcode();
                }
                break;
            case R.id.commit:
                commit();
                break;
        }


    }

    private void commit() {
        mTrim = mTelEt.getText().toString().trim();
        mMCode = mCodeEt.getText().toString().trim();
        if (mTrim.equals("")) {
            ToastUtils.toast(this, "请输入手机号");
        } else {

            switch (mOption) {
                case 0:

                    break;
                case 1:
                    TreeMap<String, String> map1 = new TreeMap<>();
                    map1.put("phone", mTel);
                    map1.put("photo_verity", mMCode);
                    String sign__value1 = RSAUtils.runRSA(JointStringUtils.JointString(map1), false);
                    presenter.getCodeOdl(sign__value1, new BaseView<BaseBean>() {
                        @Override
                        public void result(BaseBean bean) {
                            ToastUtils.toast(TelActivity.this, bean.getMsg());
                            if (bean.getCode() == 200) {
                                mTel = mTel.substring(0, 3) + "****" + mTel.substring(7, 11);
                                mOdlTel.setText(mTel);
                                mOdl.setVisibility(View.VISIBLE);
                                mTelEt.setText("");
                                mCodeEt.setText("");
                                mOption = 2;
                                mTelEt.setFocusable(true);
                                mTelEt.setFocusableInTouchMode(true);
                                mTelEt.requestFocus();
                                isGetCode = false;
                                tiem = 60;
                                mCodeGet.setTextColor(getResources().getColor(R.color.orange));
                                mCodeGet.setText("获取验证码");
                            }


                        }

                        @Override
                        public void error() {

                        }
                    });
                    break;
                case 2:
                    if (!mVphone.equals(mTrim)) {
                        ToastUtils.toast(this, "手机号与获取验证码手机号不一致");
                    } else {
                        TreeMap<String, String> map = new TreeMap<>();
                        map.put("phone", mTel);
                        map.put("photo_verity", mMCode);
                        String sign__value = RSAUtils.runRSA(JointStringUtils.JointString(map), false);

                        presenter.getChangeTel(sign__value, new BaseView<BaseBean>() {
                            @Override
                            public void result(BaseBean bean) {
                                ToastUtils.toast(TelActivity.this, bean.getMsg());
                                if (bean.getCode() == 200) {
                                    finish();
                                }


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

    private boolean isGetCode = false;
    private int tiem = 60;

    public void getcode() {
        switch (mOption) {
            case 0://绑定手机号
                getCode(3);

                break;
            case 1:// 更换手机号  旧手机号
                getCode(6);

                break;
            case 2://  更换手机号  新手机号

                mTel = mTelEt.getText().toString().trim();
                if (mTel.equals("") && Utils.isPhone(mTel)) {
                    ToastUtils.toast(this, "请输入手机号");
                } else {
                    getCode(3);
                }
                break;


        }

    }


    private void getCode(int type) {

        presenter.getCode(mTel, type + "", new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                if (bean.getCode() == 200) {
                    mVphone = bean.getResult().getPhone();
                    isGetCode = true;
                    thread = new Thread(new MThread());
                    thread.start();
                }

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
                            if (mCodeGet != null) {
                                mCodeGet.setTextColor(getResources().getColor(R.color.dark_grey));
                                mCodeGet.setText("重新获取：" + tiem);
                            }
                        }
                        if (tiem == 0) {
                            isGetCode = false;
                            tiem = 60;
                            mCodeGet.setTextColor(getResources().getColor(R.color.orange));
                            mCodeGet.setText("重新获取验证码");
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

    @Override
    protected void onStart() {
        isGetCode = false;
        super.onStart();
    }
}
