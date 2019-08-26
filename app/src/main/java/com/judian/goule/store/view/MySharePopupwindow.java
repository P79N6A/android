package com.judian.goule.store.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
import com.judian.goule.store.R;

/**
 * 新的分享弹出框
 */
public class MySharePopupwindow extends PopupWindow {

    private View v;

    public MySharePopupwindow(final Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.popup_my_share, null);

        v.findViewById(R.id.popup_my_share_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.myShareClick(1);
                }
                dismiss();
            }
        });
        v.findViewById(R.id.popup_my_share_pen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.myShareClick(2);
                }
                dismiss();
            }
        });
        v.findViewById(R.id.popup_my_share_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.myShareClick(3);
                }
                dismiss();
            }
        });
        v.findViewById(R.id.popup_my_share_kj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.myShareClick(4);
                }
                dismiss();
            }
        });
        v.findViewById(R.id.popup_my_share_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        // 设置SelectPicPopupWindow的View
        this.setContentView(v);
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

    private MySharePopupwindowListener mListener;

    public interface MySharePopupwindowListener {
        void myShareClick(int tab);
    }

    public void setmListener(MySharePopupwindowListener mListener) {
        this.mListener = mListener;
    }
}
