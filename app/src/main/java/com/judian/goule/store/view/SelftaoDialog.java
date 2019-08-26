package com.judian.goule.store.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.judian.goule.store.R;
import com.judian.goule.store.utils.TestData;


/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class SelftaoDialog extends Dialog {


    private final Activity context;
    private final String key;
    private TextView txt, tv2;
    private EditText et;


    public SelftaoDialog(Activity context, String key) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.key = key;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_tao);
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
        txt = (TextView) findViewById(R.id.txt);
        txt.setText(key);
        findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dismiss();
                if(mListener!=null){
                    mListener.onNoClick();
                }
            }
        });
        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                TestData.taoKouLing(context, key);
                if(mListener!=null){
                    mListener.onYesClick();
                }
            }
        });
    }


    /**
     * 设置确定按钮和取消被点击的接口
     */
    private onOnclickListener mListener;

    public interface onOnclickListener {
        void onYesClick();

        void onNoClick();
    }

    public void setmListener(onOnclickListener mListener) {
        this.mListener = mListener;
    }
}
