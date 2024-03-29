package com.judian.goule.store.push;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;

import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import com.example.ccy.ccyui.util.ApkUpdateUtils;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.view.SelfDialog;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.activity.WelcomeActivity;
import com.judian.goule.store.bean.MessageEvent;
import com.judian.goule.store.bean.PulickData;
import com.judian.goule.store.db.NewsBean;
import com.judian.goule.store.db.NewsDao;
import com.judian.goule.store.db.liteorm.PublicDataDBUtil;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MessageReceiver extends XGPushBaseReceiver {

    public static final String LogTag = "TPushReceiver";
    private String value;

    private void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    // 通知展示
    @Override
    public void onNotifactionShowedResult(Context context,
                                          XGPushShowedResult notifiShowedRlt) {
        if (context == null || notifiShowedRlt == null) {
            return;
        }
        Log.i("tiancao", "信鸽数据" + notifiShowedRlt.toString());
        //数据转换
        XGNotification notific = new XGNotification();
        notific.setMsg_id(notifiShowedRlt.getMsgId());
        notific.setTitle(notifiShowedRlt.getTitle());
        notific.setContent(notifiShowedRlt.getContent());
        notific.setNotificationActionType(notifiShowedRlt
                .getNotificationActionType());
        notific.setActivity(notifiShowedRlt.getActivity());
        notific.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(Calendar.getInstance().getTime()));

        NewsBean bean = new NewsBean();
        bean.setTitle(notifiShowedRlt.getTitle());
        bean.setInfo(notifiShowedRlt.getContent());
        bean.setContext(notifiShowedRlt.getContent());
        bean.setName((System.currentTimeMillis() / 1000) + "");
        NewsDao.insert(bean);
        NotificationService.getInstance(context).save(notific);//开启服务

        screenOn(context);
        show(context, "您有1条新消息, " + "通知被展示 ， " + notifiShowedRlt.toString());
        Log.i("tiancao", notific.toString());
    }

    //反注册的回调
    @Override
    public void onUnregisterResult(Context context, int errorCode) {
        if (context == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "反注册成功";
        } else {
            text = "反注册失败" + errorCode;
        }
    }

    //设置tag的回调
    @Override
    public void onSetTagResult(Context context, int errorCode, String tagName) {
        if (context == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "\"" + tagName + "\"设置成功";
        } else {
            text = "\"" + tagName + "\"设置失败,错误码：" + errorCode;
        }
    }

    //删除tag的回调
    @Override
    public void onDeleteTagResult(Context context, int errorCode, String tagName) {
        if (context == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "\"" + tagName + "\"删除成功";
        } else {
            text = "\"" + tagName + "\"删除失败,错误码：" + errorCode;
        }
    }

    // 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击。此处不能做点击消息跳转，详细方法请参照官网的Android常见问题文档
    @Override
    public void onNotifactionClickedResult(Context context,
                                           XGPushClickedResult message) {

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        if (context == null || message == null) {
            return;
        }
        String text = "";
        if (message.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
            // 通知在通知栏被点击啦。。。。。
            // APP自己处理点击的相关动作
            // 这个动作可以在activity的onResume也能监听，请看第3点相关内容

            Log.i("tiancao", "" + "通知被打开 == " + isAppAlive(context));
//            NewsActivity.openMain(context, 2);
//             Intent  intent=new Intent(context, NewsActivity.class);
        } else if (message.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {
            // 通知被清除啦。。。。
            // APP自己处理通知被清除后的相关动作
            text = "通知被清除 :" + message;
        }
//        Toast.makeText(context, "广播接收到通知被点击:" + message.toString(),
//                Toast.LENGTH_SHORT).show();
        // 获取自定义key-value
        String customContent = message.getCustomContent();
        if (customContent != null && customContent.length() != 0) {
            try {
                JSONObject obj = new JSONObject(customContent);
                // key1为前台配置的key
                if (!obj.isNull("key")) {
                    String value = obj.getString("key");

                }
                // ...
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // APP自主处理的过程。。。
        Log.i("tiancao", "自定义消息" + customContent);
    }


    public void screenOn(Context context) {
        // turn on screen
        PowerManager mPowerManager = (PowerManager) context.getSystemService(context.POWER_SERVICE);
        PowerManager.WakeLock mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        mWakeLock.acquire();
        mWakeLock.release();
    }


    public static int isAppAlive(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> listInfos = activityManager
                .getRunningTasks(20);
        // 判断程序是否在栈顶
        if (listInfos.get(0).topActivity.getPackageName().equals(context.getPackageName())) {
            return 1;
        } else {
            // 判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : listInfos) {
                if (info.topActivity.getPackageName().equals(context.getPackageName())) {
                    return 2;
                }
            }
            return 0;// 栈里找不到，返回3
        }
    }


    //注册的回调
    @Override
    public void onRegisterResult(Context context, int errorCode,
                                 XGPushRegisterResult message) {
        // TODO Auto-generated method stub
        if (context == null || message == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = message + "注册成功";
            // 在这里拿token
            String token = message.getToken();
        } else {
            text = message + "注册失败错误码：" + errorCode;
        }

    }

    // 消息透传的回调
    @Override
    public void onTextMessage(Context context, XGPushTextMessage message) {
        String text = "收到消息:" + message.toString();
        // 获取自定义key-value
        String customContent = message.getCustomContent();
        if (customContent != null && customContent.length() != 0) {
            try {
                JSONObject obj = new JSONObject(customContent);
                // key1为前台配置的key
                if (!obj.isNull("type")) {
                    value = obj.getString("type");
                    if (value.equals("1")) {
                        Intent textIntent = new Intent(context, LoginActivity.class);
                        textIntent.putExtra("type", value);
                        context.startActivity(textIntent);
                    } else if (value.equals("2")) {
                        PulickData pulickData = new PulickData();
                        pulickData.setIsUpData("0");
                        PublicDataDBUtil.save(context, pulickData);
                    }
                } else {
                    show(context, "推送数据解析异常");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
