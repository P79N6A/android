package com.judian.goule.store;

import android.app.Activity;
import android.app.ActivityManager;
import android.database.sqlite.SQLiteDatabase;

import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.ali.auth.third.core.model.Session;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.SpUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.judian.goule.store.bean.UserInfo;
import com.kepler.jd.Listener.AsyncInitListener;
import com.kepler.jd.login.KeplerApiManager;
import com.judian.goule.store.bean.WXUserBean;
import com.judian.goule.store.greendao.DaoMaster;
import com.judian.goule.store.greendao.DaoSession;
import com.judian.goule.store.utils.MyBitmapMemoryCacheParamsSupplier;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


import java.util.ArrayList;
import java.util.List;


/**
 * MultiDexApplication
 * Created by Administrator on 2017/5/4.
 */

public class MyApplication extends MultiDexApplication {
    public static MyApplication application = null;
    public static int width;
    public static int height;
    public static int share;
    public static String ali = "";
    public static String face = "";
    public static String wxInfo = "";
    public static WXUserBean wxUser;
    public static Session session;

    private UserInfo.ResultBean mUserInfo;//用户信息

    private String kefu_weixin = "";//从客服接口获取的微信，也是公司的微信号（用作判断，是否复制了改微信号）(也可用作APP 索要复制的文本)

    //是否需要显示粘贴板内容
    private boolean isClipboard = true;

    // 京东开普勒
    public static final String appKey = "cbb6193e7ef248878b72733bae6621e8";
    public static final String keySecret = "3dcc444abb404f82987ec3b2d811ea9c";

    @Override
    public void onCreate() {
        super.onCreate();
        mlist = new ArrayList<>();
        Logger.d("ddddd", "MyApplication   onCreate  ");
        application = this;

        initFresco();//初始化图片加载控件
        initMobclickAgent();//初始化Realm数据库
        initAliBc();//初始化用户(Realm数据库)
        initJD();//初始化京东
        setupDatabase();//初始化数据库
        initPush();//初始化推送
        ZXingLibrary.initDisplayOpinion(this);//初始化二维码扫描

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, final Throwable ex) {
                // Custom code here to handle the error.

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());

        }
    }

    private void initPush() {
        XGPushConfig.enableDebug(this, false);

        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.i("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.i("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }


    private void initMobclickAgent() {
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

    }

    private void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .setBitmapMemoryCacheParamsSupplier(new MyBitmapMemoryCacheParamsSupplier((ActivityManager) getSystemService(ACTIVITY_SERVICE)))
                .build();
        Fresco.initialize(this, config);


    }

    private void initJD() {
        KeplerApiManager.asyncInitSdk(MyApplication.application, appKey, keySecret,
                new AsyncInitListener() {

                    @Override
                    public void onSuccess() {

                        Log.e("Kepler", "Kepler asyncInitSdk onSuccess ");
                    }

                    @Override
                    public void onFailure() {

                        Log.e("Kepler",
                                "Kepler asyncInitSdk 授权失败，请检查lib 工程资源引用；包名,签名证书是否和注册一致");

                    }
                });

    }


    private void initAliBc() {

        AlibcTradeSDK.asyncInit(MyApplication.application, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                Log.e("alibc", "阿里百川初始化成功");
                AlibcTradeSDK.setShouldUseAlipay(true);

                // 设置是否使用同步淘客打点
                AlibcTradeSDK.setSyncForTaoke(true);

                // 是否走强制H5的逻辑，为true时全部页面均为H5打开
//                AlibcTradeSDK.setForceH5(true);

                // 设置全局淘客参数，方便开发者用同一个淘客参数，不需要在show接口重复传入

//                 AlibcTradeSDK.setTaokeParams(new AlibcTaokeParams(""))

                // 设置渠道信息(如果有渠道专享价，需要设置)
                //    AlibcTradeSDK.setChannel(typeName, channelName)
//                Map utMap = new HashMap<>();
//                utMap.put("debug_api_url","http://muvp.alibaba-inc.com/online/UploadRecords.do");
//                utMap.put("debug_key","baichuan_sdk_utDetection");
//                UTTeamWork.getInstance().turnOnRealTimeDebug(utMap);

            }

            @Override
            public void onFailure(int code, String msg) {
                Log.e("alibc", "阿里百川初始化失败 msg:" + msg + "code" + code);
            }
        });

    }

    @Override
    public void onTrimMemory(int level) {
        Logger.d("ddddd", "MyApplication   onTrimMemory  ");
        super.onTrimMemory(level);
    }

    private void setupDatabase() {
//        创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "goule.db", null);
//              获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
//        获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    private static DaoSession daoSession;

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    @Override
    public void onTerminate() {
        SpUtils.getInstance(application).putBoolean("pushdata", false);
        super.onTerminate();
    }

    private List<Activity> mlist;

    /*
    添加Activity
     */
    public void addActivity(Activity activity) {
        if (mlist.contains(activity)) return;
        mlist.add(activity);
    }

    /*
    销毁单个Activity
     */
    public void removeActivity(Activity activity) {
        if (mlist.contains(activity)) {
            mlist.remove(activity);
            activity.finish();
        }

    }

    /*
       销毁所有的Activity
        */
    public void removeAllActivity() {
        for (Activity activity : mlist) {
            activity.finish();
        }
    }

    /*
       以下都是 set get  的方法
        */

    public UserInfo.ResultBean getmUserInfo() {
        return mUserInfo;
    }

    public void setmUserInfo(UserInfo.ResultBean mUserInfo) {
        this.mUserInfo = mUserInfo;
    }

    public boolean isClipboard() {
        return isClipboard;
    }

    public void setClipboard(boolean clipboard) {
        isClipboard = clipboard;
    }

    public String getKefu_weixin() {
        return kefu_weixin;
    }

    public void setKefu_weixin(String kefu_weixin) {
        this.kefu_weixin = kefu_weixin;
    }
}
