package com.judian.goule.store.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ccy.ccyui.listener.NoDoubleClickListener;
import com.judian.goule.store.R;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class ShareUrlPopupwindow extends PopupWindow {

    private View mMenuView;
    private ImageView  pen,weixin;

    TextView   close;
    public ShareUrlPopupwindow(final Context context, final OnShareClickListener listener ) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mMenuView = inflater.inflate(R.layout.share_url, null);

        close = (TextView) mMenuView.findViewById(R.id.btn_cancel);
        pen = (ImageView) mMenuView.findViewById(R.id.pen);
        weixin = (ImageView) mMenuView.findViewById(R.id.weixin);
        // 取消按钮
        close.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
        pen.setOnClickListener(new NoDoubleClickListener() {

           @Override
           protected void onNoDoubleClick(View v) {
               dismiss();
               listener.pengyou();

           }
       });
        weixin.setOnClickListener(new NoDoubleClickListener() {

           @Override
           protected void onNoDoubleClick(View v) {
               dismiss();
               listener.weixin();

           }
       });
        mMenuView.findViewById(R.id.qq).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                dismiss();
                listener.qq();
            }
        });

        mMenuView.findViewById(R.id.kj).setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                dismiss();
                listener.zone();
            }
        });








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
        ColorDrawable dw = new ColorDrawable(0xaa000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

    }


  public   interface  OnShareClickListener{

      void weixin();
      void qq();
      void zone();
      void pengyou();
    }
}
