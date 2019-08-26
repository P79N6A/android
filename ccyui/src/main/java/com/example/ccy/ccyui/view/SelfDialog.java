package com.example.ccy.ccyui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ccy.ccyui.R;


/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class SelfDialog extends Dialog {

    private TextView yes;//确定按钮
    private TextView no;//取消按钮
    private TextView titleTv;//消息标题文本
    private TextView tel;
    private String titleStr;//从外界设置的title文本
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public static int TEL = 1;
    public static int MEMORY = 0;
    public static int LOGIN = 2;
    int option = 0;
    private String telMsg;


    public SelfDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    public SelfDialog(Context context, int op) {
        super(context, R.style.MyDialog);
        option = op;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        switch (option) {
            case 0:
                setContentView(R.layout.dialog);
                break;
            case 1:
                setContentView(R.layout.dialog_tel);
                break;
            case 2:
                setContentView(R.layout.dialog_login);
                break;
            case 3:
                setContentView(R.layout.dialog_update);
                break;
            case 4:
                setContentView(R.layout.dialog_zhanghao_yichang);
                break;
        }


        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();


    }

    public void setNoOnclickListener(onNoOnclickListener onNoOnclickListener) {
        this.noOnclickListener = onNoOnclickListener;
    }

    public void setYesOnclickListener(onYesOnclickListener onYesOnclickListener) {

        this.yesOnclickListener = onYesOnclickListener;
    }


    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

        //如果用户自定了title和message
        if (titleStr != null) {
            titleTv.setText(titleStr);
        }

    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        yes = (TextView) findViewById(R.id.dialog_OK);
        no = (TextView) findViewById(R.id.dialog_NO);
        titleTv = (TextView) findViewById(R.id.dialog_msg);
        if (option == 1) {
            tel = (TextView) findViewById(R.id.dialogTel);
            tel.setText(telMsg);
        }
    }

    public void setTel(String msg) {
        telMsg = msg;
    }


    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
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
