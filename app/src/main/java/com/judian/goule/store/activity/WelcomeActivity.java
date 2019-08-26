package com.judian.goule.store.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.ccy.ccyui.util.ApkUpdateUtils;
import com.example.ccy.ccyui.util.NetworkUtils;
import com.example.ccy.ccyui.util.SpUtils;

import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.SelfDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.PulickData;
import com.judian.goule.store.bean.UpgradeData;
import com.judian.goule.store.db.liteorm.PublicDataDBUtil;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 启动页
 */
public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private boolean first;
    private boolean compel = false;
    private List<String> list;


    private boolean isGetCode = false;
    private int tiem = 3;
    //    private SimpleDraweeView imageView;
    private ImageView imageView;
    private RelativeLayout skip_rl;
    private TextView skip_tv;

    private boolean isCountDown = false;//是否点击了倒计时
    private boolean isUpgrade = false;//是否点击了确定升级按钮

    private int mVersionCode;//版本号
    private String mVersionName;//版本名

    private boolean isConstraintUpdate = false;//是否是强制更新
    private boolean isCommonUpdate = false;//是否是普通更新

    private MyApplication application;

    @Override
    protected void onResume() {
        isShowC = false;
        super.onResume();
        if (isUpgrade) {//在选择浏览器的时候点击了取消，回调
            if (isConstraintUpdate) {
                //退出整个应用
                Token.setPush(true);
                Token.isColse();
                removeAll();
            } else {
                start(first);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setImmersionBar(2);

        application = (MyApplication) getApplication();

        imageView = findViewById(R.id.login_form_log_image_iv);
        skip_rl = findViewById(R.id.welcome_skip_rl);
        skip_rl.setOnClickListener(this);
        skip_tv = findViewById(R.id.welcome_skip_tv);

        PulickData pulickData = PublicDataDBUtil.get(this);
        if (pulickData.getIsUpData().equals("0")) {
            getAPPVersion();//判断是否有新版本
        }
        //加载gif
        Glide.with(this).load(R.drawable.start_gif).listener(new RequestListener<Integer, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                Observable.just(resource)
                        .flatMap(new Func1<GlideDrawable, Observable<?>>() {
                            @Override
                            public Observable<?> call(GlideDrawable glideDrawable) {
                                int duration = 0;
                                try {
                                    GifDrawable gifDrawable = (GifDrawable) glideDrawable;
                                    GifDecoder decoder = gifDrawable.getDecoder();
                                    for (int i = 0; i < gifDrawable.getFrameCount(); i++) {
                                        duration += decoder.getDelay(i);
                                    }
                                } catch (Throwable e) {
                                }
                                return Observable.just(null).delay(duration, TimeUnit.MILLISECONDS);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                // 加载完成后的处理...
                            }
                        });
                return false;
            }
        }).into(new GlideDrawableImageViewTarget(imageView, 20));

        //启动动画
        /*AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);
        imageView.startAnimation(alphaAnimation);*/
        isGetCode = true;
        new Thread(new WelcomeActivity.MThread()).start();

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    private void getAPPVersion() {
        PackageManager pm = this.getPackageManager();//得到PackageManager对象
        try {
            PackageInfo pi = pm.getPackageInfo(this.getPackageName(), 0);//得到PackageInfo对象，封装了一些软件包的信息在里面
            mVersionName = pi.versionName;//获取清单文件中versionCode节点的值
            mVersionCode = pi.versionCode;
            loadVer(mVersionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadVer(String ver) {

        boolean is = true;
        if (list == null) {
            list = new ArrayList<>();
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(ver)) {
                    is = false;
                }
            }
        }
        if (is) {
            list.add(ver);
            String toJson = new Gson().toJson(list);
            SpUtils.getInstance(WelcomeActivity.this).putString("version", toJson);
            first = true;
        } else {
            first = false;
        }

        if (NetworkUtils.isNetAvailable(this)) {
            version(ver);
        } else {
            ToastUtils.toast(this, "请检查网络链接");
            if (!isCountDown) {
                start(first);
            }
        }
    }

    void start(boolean first) {
        if (first) {
            startActivity(new Intent(WelcomeActivity.this, ShowActivity.class));
        } else {
            isCountDown = true;
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        }
        overridePendingTransition(0, R.anim.push_right_out);
        finish();
    }


    public void version(String versions) {
        new CdataPresenter(this).getVersions(versions, new BaseView<UpgradeData>() {
            @Override
            public void result(UpgradeData bean) {
                //进行版本控制
                if (bean.getVersionCode() > mVersionCode) {
                    dialog(bean);//更新应用版本
                } else {
                    if (!isCountDown) {
                        //关掉升级接口请求开关
                        PulickData pulickData = new PulickData();
                        pulickData.setIsUpData("1");
                        PublicDataDBUtil.save(WelcomeActivity.this, pulickData);
                        //调到主界面
                        start(false);
                    }
                }
            }

            @Override
            public void error() {
                if (!isCountDown) {
                    start(first);
                }
            }
        });
    }

    String url = "";

    private void dialog(UpgradeData bean) {
        url = bean.getDown_link();
        if (!url.equals("")) {
            if (bean.getType().equals("1")) {
                isConstraintUpdate = true;
                isCommonUpdate = false;
                final SelfDialog selfDialog2 = new SelfDialog(this, 3);
                selfDialog2.setNoOnclickListener(new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog2.dismiss();
                    }
                });
                selfDialog2.setYesOnclickListener(new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        selfDialog2.dismiss();
                        ApkUpdateUtils.openBrowser(WelcomeActivity.this, url);
                        isUpgrade = true;
                    }
                });
                selfDialog2.setTitle("发现新版本，请立即更新");
                selfDialog2.show();
            } else {
                isConstraintUpdate = false;
                isCommonUpdate = true;
                final SelfDialog selfDialog1 = new SelfDialog(this);
                selfDialog1.setNoOnclickListener(new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog1.dismiss();
                        if (!isCountDown) {
                            start(false);
                        }
                    }
                });
                selfDialog1.setYesOnclickListener(new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        selfDialog1.dismiss();
                        ApkUpdateUtils.openBrowser(WelcomeActivity.this, url);

                        isUpgrade = true;

//                    initDownManager();
//                    Toast.makeText(getApplicationContext(), "已在后台下载，请注意查看", Toast.LENGTH_SHORT).show();
//                    start(first);
                    }
                });
                selfDialog1.setTitle("发现新版本，是否更新");
                selfDialog1.show();
            }

        }
    }

/*    private void initDownManager() {
        if (!canDownloadState()) {
            Toast.makeText(this, "下载服务不用,请您启用", Toast.LENGTH_SHORT).show();
            showDownloadSetting();
            return;
        }
        ApkUpdateUtils.download(this, url, getResources().getString(R.string.app_name));
    }

    private void showDownloadSetting() {
        String packageName = getPackageName();
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        if (intentAvailable(intent)) {
            startActivity(intent);
        }
    }

    private boolean intentAvailable(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private boolean canDownloadState() {
        try {
            int state = this.getPackageManager().getApplicationEnabledSetting(getPackageName());
            if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_skip_rl:
                if (isConstraintUpdate) {
                } else {
                    start(first);
                    isCountDown = true;
                }
                break;
        }
    }

    //广告倒计时
    class MThread extends Thread {

        @Override
        public void run() {
            while (isGetCode) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isGetCode) {
                            if (skip_tv != null) {
                                skip_tv.setText(tiem + "跳过");
                            }
                        }
                        if (tiem == 0) {
                            isGetCode = false;
                            if (skip_tv != null) {
                                skip_tv.setText(tiem + "跳过");
                            }
                            if (!isCountDown) {
                                if (!isConstraintUpdate) {
                                    if (!isCommonUpdate) {
                                        startMain();
                                    }
                                }
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

    private void startMain() {
        String strs = SpUtils.getInstance(WelcomeActivity.this).getString("version", "");
        list = new Gson().fromJson(strs, new TypeToken<List<String>>() {
        }.getType());
        start(false);
    }

}
