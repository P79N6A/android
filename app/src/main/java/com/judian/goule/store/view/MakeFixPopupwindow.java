package com.judian.goule.store.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.judian.goule.store.R;

import java.util.HashMap;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class MakeFixPopupwindow extends PopupWindow {

    Context mContext;
    private View mMenuView;

    private EditText min,mam;
    private TextView tanmao,taob,btnNo,btnOk;
    HashMap<String,String> map=new HashMap<>();

    public  LinearLayout all;

    public MakeFixPopupwindow(final Context context, int  height, final FixListener listener) {
        super(context);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_fix_make, null);

        all = (LinearLayout) mMenuView.findViewById(R.id.all);
        LinearLayout.LayoutParams  layoutParams1= (LinearLayout.LayoutParams) all.getLayoutParams();
        layoutParams1.setMargins(0,height,0,0);
       all.setLayoutParams(layoutParams1);

         mMenuView.findViewById(R.id.mss).setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 dismiss();
             }
         });


        min = (EditText) mMenuView.findViewById(R.id.numMin);
        mam = (EditText) mMenuView.findViewById(R.id.numMax);
        taob = (TextView) mMenuView.findViewById(R.id.taob);
        tanmao = (TextView) mMenuView.findViewById(R.id.tanmao);
        btnNo = (TextView) mMenuView.findViewById(R.id.btnNo);
        btnOk = (TextView) mMenuView.findViewById(R.id.btnOk);


        taob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("userType","");
                taob.setBackgroundResource(R.drawable.dialog_h);
                tanmao.setBackgroundResource(R.drawable.dialog_d);
                taob.setTextColor(context.getResources().getColor(R.color.main));
                tanmao.setTextColor(context.getResources().getColor(R.color.dark22));
            }
        });

        tanmao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("userType","1");
                tanmao.setBackgroundResource(R.drawable.dialog_h);
                taob.setBackgroundResource(R.drawable.dialog_d);
                tanmao.setTextColor(context.getResources().getColor(R.color.main));
                taob.setTextColor(context.getResources().getColor(R.color.dark22));
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("userType","");
                map.put("start_price","");
                map.put("end_price","");
                min.setText("");
                mam.setText("");
                taob.setTextColor(context.getResources().getColor(R.color.main));
                tanmao.setTextColor(context.getResources().getColor(R.color.dark22));
                tanmao.setBackgroundResource(R.drawable.dialog_h);
                tanmao.setBackgroundResource(R.drawable.dialog_d);
                listener.map(map);
                dismiss();
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String   minS=   min.getText().toString().trim();
                if (!minS.equals("")){
                    map.put("start_price",minS);
                }
                String   mamS=   mam.getText().toString().trim();
                if (!mamS.equals("")){
                    map.put("end_price",mamS);
                }
                listener.map(map);
                dismiss();
            }
        });






        // 设置按钮监听
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框


    }







    public interface  FixListener{
        void  map(HashMap<String,String> map);

    }






}
