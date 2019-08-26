package com.judian.goule.store.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.kepler.jd.Listener.AsyncInitListener;
import com.kepler.jd.login.KeplerApiManager;
import com.judian.goule.store.MyApplication;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * Created by Administrator on 2018/3/14.
 */

public class InitializeService  extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_INIT_WHEN_APP_CREATE = "com.guesslive.caixiangji.service.action.app.create";
    public static final String EXTRA_PARAM = "com.guesslive.caixiangji.service.extra.PARAM";
    public InitializeService() {
        super("InitializeService");
    }
    /**
     * 启动调用
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }
    public static final String appKey = "5ec303c29c1a472e92ff1858ad4e175b";
    public static final String keySecret = "d38f9a80cf924315901bf588db93f923";
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit(EXTRA_PARAM);
            }
        }
    }

    /**
     * 启动初始化操作
     */
    private void performInit(String param) {
        initFresco();//初始化图片加载控件
        initMobclickAgent();//初始化Realm数据库
        initAliBc();//初始化用户(Realm数据库)
        initJD();//初始化推送
    }

    private void initMobclickAgent() {
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType. E_UM_NORMAL);

    }

    private void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this,config);


    }

    private void  initJD(){
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


    private void initAliBc(){

        AlibcTradeSDK.asyncInit(MyApplication.application, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
//                Toast.makeText(MyApplication.this, "初始化成功", Toast.LENGTH_SHORT).show();
                AlibcTradeSDK.setShouldUseAlipay(true);

                // 设置是否使用同步淘客打点
                AlibcTradeSDK.setSyncForTaoke(true);

                // 是否走强制H5的逻辑，为true时全部页面均为H5打开
//                AlibcTradeSDK.setForceH5(true);

                // 设置全局淘客参数，方便开发者用同一个淘客参数，不需要在show接口重复传入

                // AlibcTradeSDK.setTaokeParams(new AlibcTaokeParams(""))

                // 设置渠道信息(如果有渠道专享价，需要设置)
                //    AlibcTradeSDK.setChannel(typeName, channelName)
//                Map utMap = new HashMap<>();
//                utMap.put("debug_api_url","http://muvp.alibaba-inc.com/online/UploadRecords.do");
//                utMap.put("debug_key","baichuan_sdk_utDetection");
//                UTTeamWork.getInstance().turnOnRealTimeDebug(utMap);

            }

            @Override
            public void onFailure(int code, String msg) {
//                Toast.makeText(WelcomeActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
            }
        });

    }





}
