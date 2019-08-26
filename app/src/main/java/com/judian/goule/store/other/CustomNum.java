package com.judian.goule.store.other;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ccy.ccyui.util.ToastUtils;
import com.judian.goule.store.R;

/**
 * Created by Administrator on 2017/11/7.
 */

public class CustomNum extends LinearLayout {
    private costom costom;
    private int minNum = 1;
    private int maxNum;
    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public void setCostom(CustomNum.costom costom) {
        this.costom = costom;
    }

    public CustomNum(Context context) {
        this(context,null);
    }

    public CustomNum(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomNum(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        View inflate = inflate(context, R.layout.num_custom, this);
        TextView jia = (TextView) inflate.findViewById(R.id.jia);
        TextView jian = (TextView) inflate.findViewById(R.id.jian);
        final TextView num = (TextView) inflate.findViewById(R.id.num);
        num.setText(minNum+"");
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(num.getText().toString().trim());
                if (i >= maxNum){
                    ToastUtils.toast(context,"数量最多为"+minNum);
                }else {
                    i=i+1;
                    num.setText(i+"");
                    costom.num(i);
                }

            }
        });
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(num.getText().toString().trim());
                if (i <= minNum){
                    ToastUtils.toast(context,"数量最少为"+minNum);
                }else {
                    i = i - 1;
                    num.setText(i+"");
                    costom.num(i);
                }
            }
        });
        String trim = num.getText().toString().trim();

    }
    public interface costom{
        void num(int num);
    }
}
