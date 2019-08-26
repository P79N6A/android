package com.judian.goule.store.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.ccy.ccyui.util.CacheManager;
import com.example.ccy.ccyui.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.utils.TestData;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.SelftaoDialog;
import com.umeng.analytics.MobclickAgent;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//import me.imid.swipebacklayout.lib.SwipeBackLayout;
//import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**
 * Created by Administrator on 2016/12/14.
 */

public class BaseActivity extends AppCompatActivity {
    private MyApplication application;
    private BaseActivity context;
    public InputMethodManager imm;
    private ImmersionBar mImmersionBar;

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ImmersionBar.with(this).init();
//        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_BOTTOM);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

    }



    public void toast(String msg) {
        ToastUtils.toast(context, msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPause(this);
        super.onPause();
    }

    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
        overridePendingTransition(0, R.anim.push_right_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager wm1 = getWindowManager();
        MyApplication.width = wm1.getDefaultDisplay().getWidth();
        MyApplication.height = wm1.getDefaultDisplay().getHeight();
        if (application == null) {
            application = (MyApplication) getApplication();

        }
        context = this;
        addActivity();

    }


    public boolean isShowC = true;

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        /*if (isShowC) {
            String key = TestData.getClipboardText(this);
            if (key != null && !key.equals("")) {
                if (Token.isBCopy(key))
                    return;
                SelftaoDialog dialog = new SelftaoDialog(this, key);
                dialog.show();

            }
        } else {
            isShowC = true;
        }*/

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

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    public void showToast(String ss) {

        ToastUtils.toast(this, ss);

    }

    private void addActivity() {

        application.addActivity(context);

    }

    public void removeActivity() {
        application.removeActivity(context);

    }

    public void removeAll() {
        application.removeAllActivity();
    }

}
