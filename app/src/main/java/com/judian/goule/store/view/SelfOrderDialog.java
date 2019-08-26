package com.judian.goule.store.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ccy.ccyui.util.ToastUtils;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;


/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class SelfOrderDialog extends Dialog {


    private final Context context;
    private final String type;
    private TextView tv1, tv2, commit;
    private EditText et;

    private String key = "";


    public SelfOrderDialog(Context context, String type) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.type = type;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.popup_order);
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
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        TextView commitJ = (TextView) findViewById(R.id.commitJ);
        et = (EditText) findViewById(R.id.et);
        if (key != null) {
            if (!key.equals("")) {
                et.setText(key);
            }
        }
        switch (type) {
            case "0":
                commitJ.setText("提交淘宝订单");
                break;
            case "1":
                commitJ.setText("提交京东订单");
                break;
            case "2":
                commitJ.setText("提交唯品会订单");
                break;


        }

        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dismiss();
            }
        });
        findViewById(R.id.commitJ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = et.getText().toString().trim();
                new CdataPresenter(context).getBindOrder(txt, type, new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        ToastUtils.toast(context, bean.getMsg());
                        if (bean.getCode() == 200) {
                            dismiss();
                        }

                    }

                    @Override
                    public void error() {

                    }
                });

            }
        });


        findViewById(R.id.commitJ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = et.getText().toString().trim();
                new CdataPresenter(context).getBindOrder(txt, "1", new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        ToastUtils.toast(context, bean.getMsg());
                        if (bean.getCode() == 200) {
                            dismiss();
                        }
                    }

                    @Override
                    public void error() {

                    }
                });

            }
        });


    }

    public void setKey(String key) {
        this.key = key;
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
