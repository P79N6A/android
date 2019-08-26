package com.judian.goule.store.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.auth.third.core.model.Session;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.ApkUpdateUtils;
import com.example.ccy.ccyui.util.Dialogutils;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.SelfDialog;
import com.fanli.ccy.alibaic.AliManage;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.my.ReferralCodeVerificationAcitivy;
import com.judian.goule.store.activity.my.UserAgreementActivity;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.base.BaseUiListener;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.UpgradeData;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.presenter.LoginPresenter;
import com.judian.goule.store.utils.JianCheYYUtils;
import com.judian.goule.store.utils.JointStringUtils;
import com.judian.goule.store.utils.QingLoginUtils;
import com.judian.goule.store.utils.RSAUtils;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.kepler.jd.login.KeplerApiManager;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class LoginActivity extends BaseActivity {


    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login_qt)
    LinearLayout loginQt;
    @BindView(R.id.loginForget)
    TextView loginForget;
    @BindView(R.id.klogin)
    TextView klogin;
    @BindView(R.id.login_yan_image)
    ImageView mYan_image;
    @BindView(R.id.login_yan_image_off)
    ImageView mYan_image_off;

    private Dialog loadingDialog;
    private String name;
    private String pw;
    private LoginPresenter loginPresenter;
    private Unbinder bind;

    private boolean isVisual = false;//密码是否可视
    private ImmersionBar mImmersionBar;//沉浸栏
    private String type = "";//判断是否有账号异常

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }


    private static final String POSITION = "LoginActivity";

    public static void openMain(Activity context, int position) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(POSITION, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
        context.startActivityForResult(intent, 16);

    }

    @Override
    protected void onResume() {
        ImmersionBar.with(this).fitsSystemWindows(false).init();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bind = ButterKnife.bind(this);
        setLine();
//        final Cursor apnCursor =SqliteWrapper.query(context, this.context.getContentResolver(), Uri.withAppendedPath(Carriers.CONTENT_URI, "current"), APN_PROJECTION, null, null, null);
        int option = getIntent().getIntExtra(POSITION, 0);
        loginPresenter = new LoginPresenter(this);
        loadingDialog = Dialogutils.createLoadingDialog(LoginActivity.this, "");

        //判断是否是推送过来的信息
        type = getIntent().getStringExtra("type");
        if (type != null) {
            if (type.equals("1")) {
                showDialog();
            }
        }
    }

    private void setLine() {
        /*loginForget.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        loginForget.getPaint().setAntiAlias(true);//抗锯齿*/

        /*klogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        klogin.getPaint().setAntiAlias(true);//抗锯齿*/

        //设置沉浸栏
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        mImmersionBar
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f).init();
    }

    private void loginS() {
        name = username.getText().toString().trim();
        pw = password.getText().toString().trim();
        if (name.equals("")) {
            toast("用户名不能为空");
        } else if (pw.equals("")) {
            toast("密码不能为空");
        } else {

            TreeMap<String, String> map = new TreeMap<>();
            map.put("phone", name);
            map.put("pass", pw);
            map.put("audience", XGPushConfig.getToken(this));
            map.put("source", "2");
            String sign__value = RSAUtils.runRSA(JointStringUtils.JointString(map), false);
            loginPresenter.getlogin(sign__value, loginView);

        }
    }


    BaseView<UserInfo> loginView = new BaseView<UserInfo>() {
        @Override
        public void result(UserInfo bean) {
            toast(bean.getMsg());
            if (bean.getCode().equals("200")) {
                Token.setFace(bean.getResult().getAvatar());
                Token.setToken(bean.getResult().getToken());
                MainActivity.openMain(LoginActivity.this, 0);
                finish();
                //保存数据库
                UserInfoDBUtil.save(LoginActivity.this, bean);
            } else if (bean.getCode().equals("400") && bean.getMsg().equals("device_token不存在")) {
                loginS();
            }
        }

        @Override
        public void error() {
            Log.i("tiancao", "error");
        }
    };

    /**
     * 提交信鸽token
     */
    private void submitXGtoken(String token) {
        loginPresenter.submitXGtoken(token, XGPushConfig.getToken(this), new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                if (bean.getCode() == 200) {
//                    toast("提交成功");
                }
            }

            @Override
            public void error() {

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, new BaseUiListener());
            }
        }
    }

    @Override
    protected void onPause() {
        loadingDialog.dismiss();
        super.onPause();
    }

    @OnClick({R.id.back, R.id.loginBtn, R.id.loginForget, R.id.register, R.id.weixin, R.id.qq_Login, R.id.klogin, R.id.taobLogin, R.id.login_yan_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (type != null) {
                    if (type.equals("")) {//如果不是账号异常
                        finish();
                    } else {//账号异常做的操作
                        QingLoginUtils.outLogin(this);
                    }
                } else {
                    finish();
                }
                break;
            case R.id.loginBtn:
                loginS();
                break;
            case R.id.loginForget:
                RegisterActivity.openMain(getApplicationContext(), 1);
                break;
            case R.id.register:
//                RegisterActivity.openMain(getApplicationContext(), 0);
                startActivity(new Intent(this, ReferralCodeVerificationAcitivy.class));
                break;

            case R.id.weixin://微信登录
                loadingDialog.show();
                WXShare.getInstance(LoginActivity.this).loginWX();
                finish();
                break;
            case R.id.qq_Login://QQ登录
//                QQShareSelf.getInstance(this).loginQQ();
                break;
            case R.id.klogin://快速登录
                RegisterActivity.openMain(getApplicationContext(), 3);
                break;
            case R.id.login_yan_image:
                if (!password.getText().toString().trim().equals("")) {
                    if (!isVisual) {
                        mYan_image_off.setVisibility(View.VISIBLE);
                        isVisual = true;
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        password.setSelection(password.getText().toString().trim().length());
                    } else {
                        mYan_image_off.setVisibility(View.GONE);
                        isVisual = false;
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        password.setSelection(password.getText().toString().trim().length());
                    }
                }
                break;
            case R.id.taobLogin://淘宝登录
                try {
                    taobaoLogin();
                } catch (Exception e) {

                }

                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if (type != null) {
                if (type.equals("")) {//如果不是账号异常
                    if (loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    } else {
                        finish();
                    }
                } else {//账号异常做的操作
                    QingLoginUtils.outLogin(this);
                }
            } else {
                finish();
            }
        }
        return true;
    }


    /*
     淘宝登录
    */
    private Session session;

    private void taobaoLogin() {
        boolean b = JianCheYYUtils.checkPackage(this, "com.taobao.taobao");
        if (b) {
            AliManage.loginTaobao(LoginActivity.this, new AlibcLoginCallback() {
                @Override
                public void onSuccess(int i) {
                    loadingDialog.dismiss();
                    session = AlibcLogin.getInstance().getSession();
                    MyApplication.session = session;
                    loginPresenter.postTaoBao(session, new BaseView<UserInfo>() {
                        @Override
                        public void result(UserInfo bean) {
                            if (bean.getCode().equals("200")) {
                                toast(bean.getMsg());
                                Token.setToken(bean.getResult().getToken());
                                MainActivity.openMain(LoginActivity.this, 0);
                                //保存数据库
                                UserInfoDBUtil.save(LoginActivity.this, bean);
                                finish();
                            } else if (bean.getCode().equals("400")) {
                                toast("绑定手机号");
                                RegisterActivity.openMain(LoginActivity.this, 4);
                                finish();
                            }
                        }

                        @Override
                        public void error() {

                        }
                    });


                }

                @Override
                public void onFailure(int i, String s) {
                    loadingDialog.dismiss();
                    Toast.makeText(LoginActivity.this, s,
                            Toast.LENGTH_LONG).show();
                }
            });
        } else {
            toast("您没有安装有手机淘宝");
        }
    }

    /**
     * 账号异常弹出
     */
    private void showDialog() {
        final SelfDialog selfDialog = new SelfDialog(this, 4);
        selfDialog.setNoOnclickListener(new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.setYesOnclickListener(new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                selfDialog.dismiss();
                outLogin();
            }
        });
        selfDialog.setTitle("账号异常");
        selfDialog.show();
    }

    /**
     *
     * */
    private void outLogin() {
        //清除数据库
        UserInfoDBUtil.delete(this);


        KeplerApiManager.getWebViewService().cancelAuth(this);

        AliManage.logOut(this, new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });

        new CdataPresenter(this).getLogout(new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
            }

            @Override
            public void error() {

            }
        });
        Token.logout();
        MyApplication.ali = "";
    }
}
