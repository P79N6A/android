package com.judian.goule.store.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.judian.goule.store.R;
import com.judian.goule.store.bean.CateBean;
import com.judian.goule.store.utils.Token;

import java.util.List;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class LoadPopupwindow extends PopupWindow {



       Context mContext;
    private View mMenuView;

          ImageView img;
    public LoadPopupwindow(final Context context) {
        super(context);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.dialog_load, null);

        final List<CateBean.ResultBean> list= Token.getCate().getResult();

        img= (ImageView) mMenuView.findViewById(R.id.img);

        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, com.example.ccy.ccyui.R.anim.loading);
        // 使用ImageView显示动画
        img.startAnimation(hyperspaceJumpAnimation);

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


}
