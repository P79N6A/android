package com.example.ccy.ccyui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ccy.ccyui.R;



/*
 * Created by Administrator on 2017/2/20 0020.
 */

public class SelfFeeDialog extends Dialog {

    private Button yes;//确定按钮
    private Button no;//取消按钮
    private EditText pwEv;//消息标题文本

    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public SelfFeeDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_email);

        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();

        //初始化界面控件的事件
        initEvent();



    }

    public void setNoOnclickListener(onNoOnclickListener onNoOnclickListener) {
        this.noOnclickListener = onNoOnclickListener;
    }
    public void setYesOnclickListener( onYesOnclickListener onYesOnclickListener) {

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
     * 初始化界面控件
     */
    private void initView() {
        yes = (Button) findViewById(R.id.dialogPw_OK);
        no = (Button) findViewById(R.id.dialogPw_NO);
        pwEv= (EditText) findViewById(R.id.pw_ev);

    }
     public String getTxt(){
         return pwEv.getText().toString().trim();

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
