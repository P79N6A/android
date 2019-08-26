package com.judian.goule.store.utils;


import android.app.Activity;
import android.widget.Toast;

import com.example.ccy.ccyui.util.Logger;
import com.kepler.jd.Listener.OpenAppAction;
import com.kepler.jd.login.KeplerApiManager;
import com.kepler.jd.sdk.bean.KelperTask;
import com.kepler.jd.sdk.bean.KeplerAttachParameter;
import com.kepler.jd.sdk.exception.KeplerBufferOverflowException;

import org.json.JSONException;

/**
 * Created by Administrator on 2018/3/8.
 */

public class JDManage {

    private static JDManage jdManage;
    private static Activity activity;
    KeplerAttachParameter mKeplerAttachParameter = new KeplerAttachParameter();//这个是即时性参数  可以设置
    KelperTask mKelperTask;
    public final static String mhome = "http://m.jd.com";
    public static final int timeOut = 15;
    public JDManage(Activity activity) {
        jdManage=new JDManage(activity);
        this.activity = activity;

    }
    public static JDManage getInstance(Activity context){
        if (jdManage==null){
            activity = context;
            jdManage=new JDManage(context);
        }
        return jdManage;
    }



    OpenAppAction mOpenAppAction = new OpenAppAction() {
        @Override
        public void onStatus(final int status) {

        }
    };

           public void openHome(){
               try {
                   KeplerApiManager.getWebViewService().openJDUrlWebViewPage(mhome, mKeplerAttachParameter);
               } catch (KeplerBufferOverflowException e) {

                   e.printStackTrace();
               }
           }


//    app打开商品详情
    public void  openDetail(String  info){

        try {
          KeplerApiManager
                    .getWebViewService()
                    .openItemDetailsPage(info,
                            mKeplerAttachParameter, activity, mOpenAppAction, timeOut);
        } catch (KeplerBufferOverflowException e) {

            e.printStackTrace();
            Toast.makeText(activity, "传参出错:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public  void openUrl(String  info){
        try {
            KeplerApiManager.getWebViewService().openJDUrlWebViewPage(info,
                    mKeplerAttachParameter);
        } catch (KeplerBufferOverflowException e) {
            e.printStackTrace();
            Logger.e("fffffff","KeplerBufferOverflowException == "+e);


        }

    }




}
