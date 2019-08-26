package com.judian.goule.store.http;

import android.content.Context;


import com.example.ccy.ccyui.util.Logger;
import com.judian.goule.store.bean.BaseBean;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by Administrator on 2016/12/6.
 */

public class HttpClient {


    private static HttpClient client;
    private static Context mContext;

    public static HttpClient getInstance(Context context) {
        mContext = context;
        if (client == null) {
            client = new HttpClient();
        }
        return client;
    }

    private static MyOkHttp asyncHttpClient;

    private HttpClient() {
        //持久化存储cookie
//        ClearableCookieJar cookieJar =
//                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        //log拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //自定义OkHttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                .cookieJar(cookieJar)       //设置开启cookie
                .addInterceptor(logging)            //设置开启log
                .build();
        asyncHttpClient = new MyOkHttp(okHttpClient);

    }

    /**
     * POST请求 + Json返回
     */
    public void post(String url, Map<String, String> params, RawResponseHandler handler) {
        Logger.e("HttpClient", "post==" + HttpAPI.HOST + url);
        if (params != null)
            Logger.e("HttpClient", "params==" + params.toString());

        asyncHttpClient.post().url(HttpAPI.HOST + url).params(params).tag(mContext).enqueue(handler);
    }


    public void post1(String url, Map<String, String> params, RawResponseHandler handler) {

        asyncHttpClient.post().url(url).params(params).tag(mContext).enqueue(handler);
    }


    public void postString(String url, String sign__value, RawResponseHandler handler) {

        asyncHttpClient.post().url(HttpAPI.HOST + url).addParam("sign__value", sign__value).tag(mContext).enqueue(handler);
    }


    /**
     * GET请求 + Raw String返回
     */

    public void get(String url, Map<String, String> params, RawResponseHandler handler) {
        Logger.e("HttpClient", "get==" + HttpAPI.HOST + url);
        if (params != null)
            Logger.e("HttpClient", "params==" + params.toString());
        asyncHttpClient.get().url(HttpAPI.HOST + url).params(params).tag(mContext).enqueue(handler);
    }

    ;

    public void getNull(String url, Map<String, String> params, RawResponseHandler handler) {
        asyncHttpClient.get().url(url).params(params).tag(mContext).enqueue(handler);
    }

    ;

    /**
     * post  上传文件
     */
    public void upload(String url, Map<String, String> params, String key, File file, GsonResponseHandler<BaseBean> handler) {

        asyncHttpClient.upload().url(HttpAPI.HOST + url).params(params).addFile(key, file).tag(mContext).enqueue(handler);
    }

    ;


}

