package com.judian.goule.store.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;

import com.example.ccy.ccyui.util.NetworkUtils;
import com.example.ccy.ccyui.util.ToastUtils;
import com.judian.goule.store.R;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class WifiPopupwindow extends PopupWindow {

    private View mMenuView;


    public WifiPopupwindow(final Context context, final WifiLintener lintener ) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mMenuView = inflater.inflate(R.layout.popup_wifi, null);

       mMenuView.findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
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


    public interface WifiLintener {
        void   wifi();


    }
}
