package com.judian.goule.store;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.example.ccy.ccyui.util.ToastUtils;
import com.fanli.ccy.alibaic.AliManage;
import com.gyf.barlibrary.OnKeyboardListener;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.fragment.HelpFragment;
import com.judian.goule.store.fragment.MyFragment;
import com.judian.goule.store.fragment.ShareFragment;
import com.judian.goule.store.fragment.home.VlayoutFragment;
import com.judian.goule.store.fragment.optimization.OptimizationFragment;
import com.judian.goule.store.im.BaseActivity;
import com.judian.goule.store.listener.HomeClikeListener;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.service.MyService;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.ADDialog;
import com.judian.goule.store.views.BaseView;
import com.kepler.jd.login.KeplerApiManager;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by geyifeng on 2017/7/20.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener, HomeClikeListener {

    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.ll_home)
    LinearLayout ll_home;
    @BindView(R.id.ll_category)
    LinearLayout ll_category;
    @BindView(R.id.ll_service)
    LinearLayout ll_service;
    @BindView(R.id.ll_mine)
    LinearLayout ll_mine;
    @BindView(R.id.live)
    LinearLayout mLive;
    @BindView(R.id.tab)
    LinearLayout mTab;
    @BindView(R.id.bll)
    LinearLayout mBll;
    private OptimizationFragment weAreFragment;
    private VlayoutFragment categoryFourFragment;
    private ShareFragment shareFragment;
    private MyFragment mineFourFragment;
    private HelpFragment helpFragment;
    private CdataPresenter cdataPresenter;

    @Override
    protected void onResume() {
        super.onResume();
        /*if (MyApplication.share == 6) {
            selectedFragment(4);
            MyApplication.share = 0;
        }*/
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_fragment_two);
        ButterKnife.bind(this);
        new CdataPresenter(this).getIsAgent();
        startService(new Intent(this, MyService.class));
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.keyboardEnable(true)
                .setOnKeyboardListener(new OnKeyboardListener() {
                    @Override
                    public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
                        if (isPopup) {
                            mTab.setVisibility(View.GONE);
                            mBll.setVisibility(View.GONE);
                        } else {
                            mTab.setVisibility(View.VISIBLE);
                            mBll.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .init();
    }

    private static final String POSITION = "MainActivity";

    public static void openMain(Context context, int position) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(POSITION, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initView() {
        WindowManager wm1 = getWindowManager();
        MyApplication.width = wm1.getDefaultDisplay().getWidth();
        MyApplication.height = wm1.getDefaultDisplay().getHeight();
        selectedFragment(0);
        tabSelected(ll_home);
        mImmersionBar
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f).init();
        cdataPresenter = new CdataPresenter(this);
        cdataPresenter.getLiveBg();
        cdataPresenter.getIsAgent();


        //获取用户信息
        getUserinfo();

        //广告
        if (Token.isPush()) {
            cdataPresenter.getHomeAD(new BaseView<BaseBean>() {
                @Override
                public void result(BaseBean bean) {
                    if (bean.getCode() == 200) {
                        if (bean.getResult().getIf_show().equals("1")) {
                            ADDialog dialog = new ADDialog(MainActivity.this, bean.getResult());
                            dialog.show();
                            Token.setPush(false);
                        }
                    }
                }

                @Override
                public void error() {

                }
            });
        }
    }

    private void getUserinfo() {
        cdataPresenter.getUserInfo(new BaseView<UserInfo>() {
            @Override
            public void result(UserInfo bean) {
                if (bean.getCode().equals("200")) {

                } else if (bean.getCode().equals("400") && bean.getMsg().equals("请先登录")) {
                    outLogin();
                }
            }

            @Override
            public void error() {

            }
        });
    }

    @Override
    protected void setListener() {
        ll_home.setOnClickListener(this);
        ll_category.setOnClickListener(this);
//        ll_service.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
        mLive.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                selectedFragment(0);
//                mImmersionBar.fitsSystemWindows(false).transparentStatusBar().init();
                mImmersionBar
                        .fitsSystemWindows(true)
                        .statusBarColor(R.color.white)
                        .statusBarDarkFont(true, 0.2f).init();
                break;
            case R.id.ll_category:
                if (Token.isLogin()) {
                    selectedFragment(2);
//                    startActivity(new Intent(this, MakeActivity.class));
                    mImmersionBar
                            .fitsSystemWindows(true)
                            .statusBarColor(R.color.white)
                            .statusBarDarkFont(true, 0.2f).init();
                } else {
                    startActivityForResult(new Intent(this, LoginActivity.class), 12);
                }

                break;
            case R.id.ll_service:

                /*selectedFragment(4);
                mImmersionBar.fitsSystemWindows(true).transparentStatusBar().init();*/
                break;
            case R.id.ll_mine:
                if (Token.isLogin()) {
                    selectedFragment(3);
                    mImmersionBar.fitsSystemWindows(false).transparentStatusBar().init();
                } else {
                    startActivityForResult(new Intent(this, LoginActivity.class), 12);
                }

                break;
            case R.id.live:
                if (Token.isLogin()) {

                    mImmersionBar
                            .fitsSystemWindows(true)
                            .statusBarColor(R.color.white)
                            .statusBarDarkFont(true, 0.2f).init();
                    selectedFragment(1);
                } else {
                    startActivityForResult(new Intent(this, LoginActivity.class), 12);
                }


                break;
        }
    }

    public static int option = 0;

    public void selectedFragment(int position) {
        option = position;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 1://合伙人中心
                tabSelected(mLive);
                if (weAreFragment == null) {
                    weAreFragment = new OptimizationFragment();
                    transaction.add(R.id.content, weAreFragment);
                } else
                    transaction.show(weAreFragment);
                break;
            case 0://花钱
                tabSelected(ll_home);
                if (categoryFourFragment == null) {
                    categoryFourFragment = new VlayoutFragment();
                    transaction.add(R.id.content, categoryFourFragment);
                } else
                    transaction.show(categoryFourFragment);
                break;
            case 2://分享赚
                tabSelected(ll_category);
                if (shareFragment == null) {
                    shareFragment = new ShareFragment();
                    transaction.add(R.id.content, shareFragment);
                } else
                    transaction.show(shareFragment);
                break;
            case 3://我的
                tabSelected(ll_mine);
                if (mineFourFragment == null) {
                    mineFourFragment = new MyFragment();
                    mineFourFragment.setListener(this);
                    transaction.add(R.id.content, mineFourFragment);
                } else
                    transaction.show(mineFourFragment);
                break;
            case 4://使用教程
                /*tabSelected(ll_service);
                if (helpFragment == null) {
                    helpFragment = new HelpFragment();
                    transaction.add(R.id.content, helpFragment);
                } else
                    transaction.show(helpFragment);*/
                break;


        }
        transaction.commit();


        switch (position) {
            case 2: //赚钱

                break;

            case 3://我的

                if (mineFourFragment != null) {
                    mineFourFragment.initData();
                }
                break;

            case 4://直播

                break;
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (weAreFragment != null)
            transaction.hide(weAreFragment);
        if (categoryFourFragment != null)
            transaction.hide(categoryFourFragment);
        if (shareFragment != null)
            transaction.hide(shareFragment);
        if (mineFourFragment != null)
            transaction.hide(mineFourFragment);
        /*if (helpFragment != null)
            transaction.hide(helpFragment);*/
    }

    private void tabSelected(LinearLayout linearLayout) {
        ll_home.setSelected(false);
        ll_category.setSelected(false);
//        ll_service.setSelected(false);
        ll_mine.setSelected(false);
        mLive.setSelected(false);
        if (linearLayout == null) return;
        linearLayout.setSelected(true);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_fragment_two;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            exit();
        }
        return true;
    }

    private long time = 0;

    private void exit() {
        if (System.currentTimeMillis() - time > 2000) {
            //获得当前的时间
            time = System.currentTimeMillis();
            ToastUtils.toast(this, "再点击一次退出应用程序");
        } else {
            Token.setPush(true);
            Token.isColse();
            removeAll();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 63) {
            selectedFragment(0);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClike(int option) {
        selectedFragment(option);
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
