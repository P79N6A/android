package com.judian.goule.store.im;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.ccy.ccyui.util.CacheManager;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.utils.TestData;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.SelftaoDialog;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类
 * Created by geyifeng on 2017/5/9.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;
    private MyApplication application;

    private BaseActivity context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(setLayoutId());
        if (application == null) {
            application = (MyApplication) getApplication();
        }
        context = this;

        //绑定控件
        unbinder = ButterKnife.bind(this);
        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();
        //初始化数据
        initData();
        //view与数据绑定
        initView();
        //设置监听
        setListener();

        addActivity();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }

    protected abstract int setLayoutId();

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    /**
     * 设置标题栏颜色，以及是否有标题栏
     * 1 有标题栏   2 无标题栏
     */
    protected void setImmersionBar(int tag) {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        if (tag == 1) {
            mImmersionBar
                    .fitsSystemWindows(true)
                    .statusBarColor(R.color.white)
                    .statusBarDarkFont(true, 0.2f).init();
        } else {
            mImmersionBar.fitsSystemWindows(false).transparentStatusBar().init();
        }
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void setListener() {
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        CacheManager.clearAllCache(getApplicationContext());
        MobclickAgent.onResume(this);
        //初始化粘贴板数据
        initClipboardText();
    }

    private void initClipboardText() {
        boolean isShowC = application.isClipboard();
        if (isShowC) {
            final String key = TestData.getClipboardText(this);
            if (key != null && !key.equals("")) {
                if (Token.isBCopy(key))
                    return;
                if (application.getKefu_weixin().equals(key)) {//判断复制的文本是否是圆宝妹的微信号
                    return;
                }
                SelftaoDialog dialog = new SelftaoDialog(this, key);
                dialog.setmListener(new SelftaoDialog.onOnclickListener() {
                    @Override
                    public void onYesClick() {
                        application.setClipboard(false);
                    }

                    @Override
                    public void onNoClick() {
                        TestData.setClipboardText(BaseActivity.this, key);
                        application.setClipboard(false);
                    }
                });
                dialog.show();
            }
        }
    }

    private boolean isCurrentRunningForeground = true;

    @Override
    protected void onStart() {
        super.onStart();
        if (!isCurrentRunningForeground) {
//            Log.i("tiancao", "回到前台");
            application.setClipboard(true);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        isCurrentRunningForeground = isRunningForeground();
        if (!isCurrentRunningForeground) {
//            Log.i("tiancao", "进入后台");
            application.setClipboard(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void addActivity() {

        application.addActivity(context);

    }

    public void removeActivity() {
        application.removeActivity(context);

    }

    public void removeActivity(Activity activity) {
        application.removeActivity(activity);

    }


    public void removeAll() {
        application.removeAllActivity();
    }

    public boolean isRunningForeground() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = activityManager.getRunningAppProcesses();
        // 枚举进程
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcessInfo.processName.equals(this.getApplicationInfo().processName)) {
                    Log.d("tiancao", "EntryActivity isRunningForeGround");
                    return true;
                }
            }
        }
        Log.d("tiancao", "EntryActivity isRunningBackGround");
        return false;
    }
}
