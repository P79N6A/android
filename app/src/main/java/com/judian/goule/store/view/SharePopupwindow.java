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

import com.judian.goule.store.R;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class SharePopupwindow extends PopupWindow {
    private TextView  stateTv,goShare;
    private View mMenuView;
    private ImageView  close,stateIv;


    public SharePopupwindow(final Context context, final int option, final OnShareClickListener listener ) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mMenuView = inflater.inflate(R.layout.activity_main22, null);


        stateTv = (TextView) mMenuView.findViewById(R.id.stateTv);

        goShare = (TextView) mMenuView.findViewById(R.id.goShare);
        close = (ImageView) mMenuView.findViewById(R.id.close);
        stateIv = (ImageView) mMenuView.findViewById(R.id.stateIv);
        // 取消按钮
        close.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
                listener.colse();
            }
        });

        switch (option){
            case 1://分享成功
                stateTv.setText("分享成功");
                stateIv.setImageResource(R.mipmap.share_dg1);
                  break;
            case 2://分享失败
                stateTv.setText("分享失败");
                stateIv.setImageResource(R.mipmap.share_dg);
                  break;


        }


        // 设置按钮监听
        goShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.goShare();
                dismiss();

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

        void goShare();
        void colse();
    }
}
