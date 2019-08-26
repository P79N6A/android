package com.judian.goule.store.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ccy.ccyui.adapter.RecyclerViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.AliWebActivity;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.TKLBean;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class PushPopupwindow extends PopupWindow {

    private  TextView info,pic;
    SimpleDraweeView icon;
    private View mMenuView;

    public PushPopupwindow(final Activity context, final TKLBean.ResultBeanX.ResultBean data) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mMenuView = inflater.inflate(R.layout.popup_goods, null);

        icon = (SimpleDraweeView) mMenuView.findViewById(R.id.icon);

          RecyclerViewHolder.setImg(icon,data.getPict_url(),1);

        info = (TextView) mMenuView.findViewById(R.id.info);
        pic = (TextView) mMenuView.findViewById(R.id.pic);
        info.setText(data.getTitle());
        pic.setText(data.getFanli_money());
        // 取消按钮
        mMenuView.findViewById(R.id.close).setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                dismiss();
            }
        });

        mMenuView.findViewById(R.id.goShare).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {



                GoodListBean .ResultBean  resultBean=new Gson().fromJson( new Gson().toJson(data),GoodListBean.ResultBean.class);
                    AliWebActivity.openXQ(context,resultBean,6);

                dismiss();
            }
        });

        mMenuView.findViewById(R.id.close1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
        this.setFocusable(false);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xaa000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框


    }


}
