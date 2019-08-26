package com.judian.goule.store.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.ccy.ccyui.util.NetworkUtils;
import com.example.ccy.ccyui.util.ToastUtils;
import com.judian.goule.store.R;


/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class WifiDialog extends Dialog {


    private final Context context;



    public WifiDialog(Context context,WifiLintener lintener ) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.lintener = lintener;

    }

  public   WifiLintener   lintener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.popup_wifi);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
         initData();
        //初始化界面控件的事件
    }




    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {


    }

    /**
     * 初始化界面控件
     */


    private void initView() {
        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtils.isNetAvailable(context)){
                    dismiss();
                    lintener.wifi();
                }else {
                    ToastUtils.toast(context,"暂无网络");
                }

            }
        });
    }

    public interface WifiLintener {
        void   wifi();


    }
}
