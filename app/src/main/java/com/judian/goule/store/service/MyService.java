package com.judian.goule.store.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.ccy.ccyui.util.Logger;
import com.judian.goule.store.utils.Token;

public class MyService extends Service {
    public MyService() {
    }

    String TAG="MyService";
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("ddddd","MyService   onStartCommand  ");
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onCreate() {
        Logger.d("ddddd","MyService   onCreate  ");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Logger.d("ddddd","MyService   onStart  ");

        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Logger.d("ddddd","MyService   onDestroy  ");
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        Logger.d("ddddd","MyService   onLowMemory  ");
        super.onLowMemory();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Logger.d("ddddd","MyService   onTaskRemoved  ");
        Token.setPush(true);
        Token.isColse();
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onTrimMemory(int level) {
        Logger.d("ddddd","MyService   onTrimMemory  ");
        super.onTrimMemory(level);
    }
}
