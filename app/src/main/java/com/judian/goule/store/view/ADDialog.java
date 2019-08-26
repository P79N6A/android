package com.judian.goule.store.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.WebActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.bean.BaseBean;


/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class ADDialog extends Dialog {


    private final Activity context;
    private final BaseBean.ResultBean bean;
    private TextView txt,tv2;
    private EditText et;


    public ADDialog(Activity context, BaseBean.ResultBean key ) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.bean = key;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_ad);
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
        SimpleDraweeView img = (SimpleDraweeView) findViewById(R.id.img);
        AdapterUtil.setControllerListener(img,bean.getPic_url(), (int) (MyApplication.width*0.8));
        findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                WebActivity.openMain(context,bean.getTitle(),bean.getUrl());
                dismiss();
            }
        });
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
         void onYesClick();
    }

    public interface onNoOnclickListener {
         void onNoClick();
    }

}
