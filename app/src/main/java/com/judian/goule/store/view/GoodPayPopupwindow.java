package com.judian.goule.store.view;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.ccy.ccyui.listener.NoDoubleClickListener;
import com.example.ccy.ccyui.util.ToastUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.utils.Token;
import com.squareup.picasso.Picasso;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class GoodPayPopupwindow extends PopupWindow {
    private TextView  txt,copy;
    private View mMenuView;
    private ImageView  close;


    public GoodPayPopupwindow(final Context context, final String text,String face) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mMenuView = inflater.inflate(R.layout.dialog_shear2, null);


        txt = (TextView) mMenuView.findViewById(R.id.txt);
        RoundedImageView   img = (RoundedImageView) mMenuView.findViewById(R.id.face);
        copy = (TextView) mMenuView.findViewById(R.id.copy);
       RelativeLayout  all = (RelativeLayout) mMenuView.findViewById(R.id.all);
        ViewGroup.LayoutParams layoutParams = all.getLayoutParams();
          layoutParams.width= (int) (MyApplication.width*0.7);
           layoutParams.height= ViewGroup.LayoutParams.WRAP_CONTENT;
          all.setLayoutParams(layoutParams);

        Picasso.with(context).load(face).into(img);
        close = (ImageView) mMenuView.findViewById(R.id.close);
        // 取消按钮
        close.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
         txt.setText(text);

        // 设置按钮监听
        copy.setOnClickListener(new NoDoubleClickListener() {


            @Override
            protected void onNoDoubleClick(View v) {
                ClipboardManager cm = (ClipboardManager)context. getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(text);
                Token.addKey(text);
                ToastUtils.toast(context,"复制成功");
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
}
