package com.example.ccy.ccyui.http;

import com.example.ccy.ccyui.util.Logger;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Map;

import cz.msebera.android.httpclient.client.CookieStore;


/**
 * Created by Administrator on 2016/12/6.
 */

public class HttpClient {


         private static HttpClient client;

       public  static HttpClient getInstance( ){
               if (client==null){
                   client=new HttpClient();
               }


           return client;
      }

     private  static AsyncHttpClient  asyncHttpClient;
    private HttpClient() {
        asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.setMaxRetriesAndTimeout(10,2000);

    }
    public  void setCoockieStore(CookieStore cookieStore){
        asyncHttpClient.setCookieStore(cookieStore);
    }

    public   void post1(String url,RequestParams params,TextHttpResponseHandler handler){

             asyncHttpClient.post(url,handler);
        };

    public   void post1(String url,RequestParams params,JsonHttpResponseHandler handler){

        asyncHttpClient.post(url,params,handler);
    };
    public   void post(String url, RequestParams params, JsonHttpResponseHandler handler){

         asyncHttpClient.post(url,params,handler);
    };

    public   void post(String url, RequestParams params, TextHttpResponseHandler handler){

        asyncHttpClient.post(url,params,handler);
    };

    public   void get( String  url,RequestParams params,TextHttpResponseHandler handler ){
        asyncHttpClient.get(url,params,handler);
    };
    public   void getNull( String  url,RequestParams params,TextHttpResponseHandler handler ){
        asyncHttpClient.get(url,params,handler);

    };

    public   void getApp( String  url,RequestParams params,TextHttpResponseHandler handler ){
       // asyncHttpClient.get(url,params,new B);

    };

    // 下载数据使用，会返回byte数据
    public  void get(String uString, BinaryHttpResponseHandler bHandler) {
        asyncHttpClient.get(uString, bHandler);
    }


}

