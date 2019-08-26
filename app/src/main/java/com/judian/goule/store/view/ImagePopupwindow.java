package com.judian.goule.store.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.judian.goule.store.R;
import com.judian.goule.store.adapter.ViewPager1Adapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class ImagePopupwindow extends PopupWindow {

    private View mMenuView;
    private ViewPager vp;
    private LinearLayout llb;
  List<ImageView>  views=new ArrayList<>();

    public ImagePopupwindow(final Context context , final List<String > list , @Nullable int option ) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mMenuView = inflater.inflate(R.layout.bag_img, null);


        vp = (ViewPager) mMenuView.findViewById(R.id.vp);
        llb = (LinearLayout) mMenuView.findViewById(R.id.llb);


       List<View> viewList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ImageView  imageView=new ImageView(context);
            Picasso.with(context).load(list.get(i)).into(imageView);
            viewList.add(imageView);
            ImageView  iv=new ImageView(context);
            iv.setImageResource(R.mipmap.ic_dot_normal);

            iv.setPadding(6,6,6,6);
            views.add(iv);
            llb.addView(iv);
        }

         vp.setAdapter(new ViewPager1Adapter(viewList));

        if (views.size()>0)
            views.get(0).setImageResource(R.mipmap.ic_dot_pressed);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (views.size()>=position){
                    for (int i = 0; i < views.size(); i++) {
                        views.get(i).setImageResource(R.mipmap.ic_dot_normal);
                    }
                    views.get(position).setImageResource(R.mipmap.ic_dot_pressed);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       vp.setCurrentItem(option);



        mMenuView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


    public  void setOption(int  option){
        if (vp!=null){
            vp.setCurrentItem(option);
        }

    }


}
