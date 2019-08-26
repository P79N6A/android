package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.util.Utils;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.my.ReferralCodeVerificationAcitivy;
import com.judian.goule.store.activity.my.UserAgreementActivity;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.presenter.RegisterPresenter;
import com.judian.goule.store.utils.JointStringUtils;
import com.judian.goule.store.utils.RSAUtils;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.tencent.android.tpush.XGPushConfig;

import java.util.HashMap;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.getCode)
    TextView getCode;

    public static final String POSITION = "SOUSUO";
    public static final String YQ_CODE = "yq_code";
    @BindView(R.id.txt)
    TextView txt;

    @BindView(R.id.ensure)
    TextView ensure;
    @BindView(R.id.pw)
    EditText pw;
    @BindView(R.id.pw2)
    EditText pw2;
    @BindView(R.id.pel)
    LinearLayout pel;
    @BindView(R.id.pe2l)
    LinearLayout pe2l;
    @BindView(R.id.telL)
    LinearLayout telL;
    @BindView(R.id.codeL)
    LinearLayout codeL;
    @BindView(R.id.ensureIv)
    ImageView ensureIv;
    @BindView(R.id.backIv)
    ImageView backIv;


    @BindView(R.id.register_check_box)
    CheckBox mCheckBox;

    private int option;
    private RegisterPresenter presenter;
    private CdataPresenter mCdataPresenter;
    private String num;
    private String code1;
    private String id;
    private String maxTel;
    private String mVcode = "dongdong";
    private String mVphone = "dongdong";
    private Unbinder bind;

    private String yq_code = "";//推荐码

    @Override
    protected void onDestroy() {
        bind.unbind();
        presenter.dismiss();
        super.onDestroy();
    }

    public static void openMain(Context context, int option) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(POSITION, option);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openMain(Context context, int option, int yq_code) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(POSITION, option);
        intent.putExtra(YQ_CODE, String.valueOf(yq_code));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openMain(Context context, int option, String id) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(POSITION, option);
        intent.putExtra(POSITION + 1, id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bind = ButterKnife.bind(this);
        backIv.setVisibility(View.GONE);
        ensureIv.setImageResource(R.mipmap.close2);
        setImmersionBar(2);

        mCdataPresenter = new CdataPresenter(this);
        presenter = new RegisterPresenter(this);
        option = getIntent().getIntExtra(POSITION, 0);
        id = getIntent().getStringExtra(POSITION + 1);
        yq_code = getIntent().getStringExtra(YQ_CODE);
        mCheckBox.setChecked(true);//设置是默认选中
        switch (option) {
            case 0:
                title.setText("用户注册");
                pel.setVisibility(View.VISIBLE);
                pe2l.setVisibility(View.VISIBLE);

                break;
            case 1:
                title.setText("找回密码");
                pel.setVisibility(View.GONE);
                pe2l.setVisibility(View.GONE);
                break;

            case 2:
                title.setText("修改手机号");

                break;
            case 3:
                title.setText("快速登录");
                pel.setVisibility(View.GONE);
                pe2l.setVisibility(View.GONE);

                break;
            case 4:
//                淘宝账
                title.setText("绑定手机号");
                pel.setVisibility(View.GONE);
                pe2l.setVisibility(View.GONE);

                break;

            case 5://微信
                title.setText("绑定手机号");
                pel.setVisibility(View.GONE);
                pe2l.setVisibility(View.GONE);

                break;
            case 6://QQ
                title.setText("绑定手机号");
                pel.setVisibility(View.VISIBLE);
                pe2l.setVisibility(View.VISIBLE);
                break;
            case 8:// 设置密码
                title.setText("修改密码");
                telL.setVisibility(View.GONE);
                codeL.setVisibility(View.GONE);
                pel.setVisibility(View.VISIBLE);
                pe2l.setVisibility(View.VISIBLE);
                break;

        }


    }

    @OnClick({R.id.back, R.id.getCode, R.id.register_btn, R.id.ensureIv, R.id.register_user_agreement_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.getCode:

                if (!isGetCode) {

                    getVercode();
                }
                break;
            case R.id.register_btn:
                if (mCheckBox.isChecked()) {
                    commit();
                } else {
                    toast("您未同意使用条款");
                }
                break;
            case R.id.register_user_agreement_tv://用户协议
                startActivity(new Intent(this, UserAgreementActivity.class));
                break;
            case R.id.ensureIv:
                finish();
                break;
        }
    }

    private void commit() {
        num = tel.getText().toString().trim();
        code1 = code.getText().toString().trim();
        String pws = pw.getText().toString().trim();
        String pw2s = pw2.getText().toString().trim();

        if (num.equals("")) {
            toast("手机号不能为空");
            return;
        }
        if (code1.equals("")) {
            toast("验证码不能为空");
            return;
        }

        TreeMap<String, String> map = new TreeMap<>();

        if (option == 8) {

            if (pws.equals("")) {
                toast("密码不能为空");

            } else if (pws.length() < 6) {
                toast("密码不能少于6位");
            } else if (!pws.equals(pw2s)) {
                toast("两次输入密码不一致");
            } else {

                map.put("token", Token.getToken());
                map.put("pass", pws);
                map.put("repass", pw2s);
                String s = RSAUtils.runRSA(JointStringUtils.JointString(map), false);


                presenter.setPassword(s, new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        toast(bean.getMsg());
                        if (bean.getCode() == 200) {
                            BaseBean.ResultBean result = bean.getResult();
                            if (result.getAvatar() != null) Token.setFace(result.getAvatar());
                            Token.setToken(result.getToken());
                            finish();
                        }
                    }

                    @Override
                    public void error() {

                    }
                });
            }
        } else {

            switch (option) {
                case 0:
                    if (pws.equals("")) {
                        toast("密码不能为空");

                    } else if (pws.length() < 6) {
                        toast("密码不能少于6位");
                    } else if (!pws.equals(pw2s)) {
                        toast("两次输入密码不一致");
                    } else {
                        //      手机  注册
                        map.put("phone", num);
                        map.put("captcha", code1);
                        map.put("password", pws);
                        map.put("repassword", pw2s);
                        map.put("yq_code", yq_code);
                        String sign__value = RSAUtils.runRSA(JointStringUtils.JointString(map), false);

                        presenter.register(sign__value, new BaseView<UserInfo>() {
                            @Override
                            public void result(UserInfo bean) {
                                toast(bean.getMsg());
                                if (bean.getCode().equals("200")) {
                                    UserInfo.ResultBean result = bean.getResult();
                                    if (result.getAvatar() != null)
                                        Token.setFace(result.getAvatar());
                                    Token.setToken(result.getToken());
                                    MainActivity.openMain(RegisterActivity.this, 0);
                                    //保存数据库
                                    UserInfoDBUtil.save(RegisterActivity.this, bean);
                                    finish();
                                }


                            }

                            @Override
                            public void error() {

                            }

                        });


                    }


                    break;
                case 1:

                    map.put("phone", num);
                    map.put("code", code1);
                    String s = RSAUtils.runRSA(JointStringUtils.JointString(map), false);

                    presenter.setPass(s, new BaseView<BaseBean>() {
                        @Override
                        public void result(BaseBean bean) {
                            toast(bean.getMsg());
                            if (bean.getCode() == 200) {
                                telL.setVisibility(View.GONE);
                                codeL.setVisibility(View.GONE);
                                pel.setVisibility(View.VISIBLE);
                                pe2l.setVisibility(View.VISIBLE);
                                option = 7;
                                num = bean.getResult().getPhone();
                                title.setText("重置密码");
                            } else {
                                option = 1;
                            }

                        }

                        @Override
                        public void error() {

                        }
                    });


                    break;
                case 7:
//                修改密码
                    TreeMap<String, String> pa = new TreeMap<>();
                    pa.put("phone", num);
                    pa.put("password", pws);
                    pa.put("repassword", pw2s);
                    String s2 = RSAUtils.runRSA(JointStringUtils.JointString(pa), false);

                    presenter.rePass(s2, new BaseView<BaseBean>() {
                        @Override
                        public void result(BaseBean bean) {
                            toast(bean.getMsg());
                            if (bean.getCode() == 200) finish();
                        }

                        @Override
                        public void error() {

                        }
                    });

                    break;
                case 2:
//                 修改手机号
//                    RequestParams  ptel=new RequestParams();
//                    ptel.put("user_id", MyApplication.userId);
//                    ptel.put("phones",num);
//                    ptel.put("photo_verity",code1);
//                    presenter.postTel(ptel, new BaseView<BaseBean>(){
//                        @Override
//                        public void result(BaseBean bean) {
//                            toast(bean.getMsg());
//                            if (bean.getCode()==200) finish();
//                        }
//
//                        @Override
//                        public void error() {
//
//                        }
//                    });

                    break;


                case 3:
//                 快速登录
                    map.put("phone", num);
                    map.put("captcha", code1);
                    map.put("audience", XGPushConfig.getToken(this));
                    map.put("source", "2");
                    if (yq_code != null) {
                        map.put("yq_code", yq_code);
                    }
                    String s1 = RSAUtils.runRSA(JointStringUtils.JointString(map), false);

                    presenter.kuaisu(s1, new BaseView<UserInfo>() {
                        @Override
                        public void result(UserInfo bean) {
                            toast(bean.getMsg());
                            if (bean.getCode().equals("200")) {
                                UserInfo.ResultBean result = bean.getResult();
                                Token.setToken(result.getToken());
                                if (result.getAvatar() != null) Token.setFace(result.getAvatar());
                                MainActivity.openMain(RegisterActivity.this, 0);
                                //保存数据库
                                UserInfoDBUtil.save(RegisterActivity.this, bean);
                                finish();
                            } else if (bean.getCode().equals("400")) {
//                                startActivity(new Intent(RegisterActivity.this, ReferralCodeVerificationAcitivy.class).putExtra("tab", "2"));
                                finish();
                            }
                        }

                        @Override
                        public void error() {

                        }
                    });

                    break;


                case 4:
//      手机 淘宝 注册

                    map.put("phone", num);
                    map.put("code", code1);
                    map.put("openid", MyApplication.session.openId);
                    map.put("nick", MyApplication.session.nick);
                    map.put("ava", MyApplication.session.avatarUrl);
                    map.put("audience", XGPushConfig.getToken(this));
                    map.put("source", "2");
                    if (yq_code != null) {
                        map.put("yq_code", yq_code);
                    }
                    String taobao_sign__value = RSAUtils.runRSA(JointStringUtils.JointString(map), false);
                    presenter.taobao(taobao_sign__value, new BaseView<BaseBean>() {
                        @Override
                        public void result(BaseBean bean) {
                            toast(bean.getMsg());
                            if (bean.getCode() == 200) {
                                BaseBean.ResultBean result = bean.getResult();
                                Token.setToken(result.getToken());
                                if (result.getAvatar() != null) Token.setFace(result.getAvatar());
                                MainActivity.openMain(RegisterActivity.this, 0);
                                finish();
                            } else if (bean.getCode() == 400) {
                                option = 8;
                                Token.setToken(bean.getResult().getToken());
                                title.setText("设置密码");
                                telL.setVisibility(View.GONE);
                                codeL.setVisibility(View.GONE);
                                pel.setVisibility(View.VISIBLE);
                                pe2l.setVisibility(View.VISIBLE);
                            } else if (bean.getCode() == 300) {
                                option = 4;
                                startActivityForResult(new Intent(RegisterActivity.this, ReferralCodeVerificationAcitivy.class).putExtra("tab", "1"), 2000);
                            }
                        }

                        @Override
                        public void error() {

                        }
                    });
                    break;


                case 5:
//      手机 微信 注册


                    map.put("phone", num);
                    map.put("code", code1);
                    map.put("unionid", MyApplication.wxUser.getUnionid());
                    map.put("weixininfo", MyApplication.wxInfo);
                    map.put("audience", XGPushConfig.getToken(this));
                    map.put("source", "2");
                    if (yq_code != null) {
                        map.put("yq_code", yq_code);
                    }
                    String weixin_sign__value = RSAUtils.runRSA(JointStringUtils.JointString(map), false);

                    presenter.weixin(weixin_sign__value, new BaseView<UserInfo>() {
                        @Override
                        public void result(UserInfo bean) {
                            toast(bean.getMsg());
                            if (bean.getCode().equals("200")) {
                                UserInfo.ResultBean result = bean.getResult();
                                Token.setToken(result.getToken());
                                if (result.getAvatar() != null)
                                    Token.setFace(result.getAvatar());
                                MainActivity.openMain(RegisterActivity.this, 0);
                                //保存数据库
                                UserInfoDBUtil.save(RegisterActivity.this, bean);
                                finish();
                            } else if (bean.getCode().equals("400")) {
                                option = 8;
                                Token.setToken(bean.getResult().getToken());
                                title.setText("设置密码");
                                telL.setVisibility(View.GONE);
                                codeL.setVisibility(View.GONE);
                                pel.setVisibility(View.VISIBLE);
                                pe2l.setVisibility(View.VISIBLE);
                            } else if (bean.getCode().equals("300")) {
                                option = 5;
                                startActivityForResult(new Intent(RegisterActivity.this, ReferralCodeVerificationAcitivy.class).putExtra("tab", "2"), 3000);
                            }
                        }

                        @Override
                        public void error() {

                        }
                    });
                    break;

            }
        }

    }


    private boolean isGetCode = false;
    private int tiem = 60;

    public void getVercode() {
        //手机号码
        num = tel.getText().toString().trim();
        switch (option) {
            case 0:
                postCode(num, "1");
                break;
            case 1:
//                重置密码
                postCode(num, "2");

                break;
            case 2:
//                绑定手机号
                postCode(num, "1");

                break;
            case 3:
//                绑定手机号
                postCode(num, "8");

                break;
            case 4:
//       手机获取验证码
                postCode(num, "7");

                break;
            case 5:
//       手机获取验证码
                postCode(num, "7");

                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 6688 || resultCode == 6677) {
            if (data != null) {
                yq_code = data.getStringExtra("yq_code");
            }
        }
    }

    private void postCode(String phone, String type) {
        if (num.equals("") || !Utils.isPhone(num)) {
            toast("请输入手机号");
        } else {
            mCdataPresenter.getCode(phone, type, new BaseView<BaseBean>() {
                @Override
                public void result(BaseBean bean) {
                    ToastUtils.toast(RegisterActivity.this, bean.getMsg());
                    if (bean.getCode() == 200) {
                        mVcode = bean.getResult().getCode();
                        mVphone = bean.getResult().getPhone();
                        isGetCode = true;
                        new Thread(new MThread()).start();
                    } else {


                    }
                }

                @Override
                public void error() {

                }
            });
        }
    }


    @Override
    protected void onPause() {
        isGetCode = false;
        super.onPause();
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
                            if (getCode != null) {
                                getCode.setTextColor(getResources().getColor(R.color.orange));
                                getCode.setText("重新获取验证码");
                                getCode.setBackgroundResource(R.drawable.bg_vcode);
                            }
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
    public void finish() {
        setResult(23);
        super.finish();
    }
}
