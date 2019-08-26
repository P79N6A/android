package com.judian.goule.store.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.example.ccy.ccyui.adapter.CommonViewHolder;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.view.MyGridView;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.CateBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class MakeTypePopupwindow extends PopupWindow {


    MyGridView  myGv;
       Context mContext;
    private View mMenuView;
      int num=1;

    public  LinearLayout all;

    public MakeTypePopupwindow(final Context context, int  height, final OnOptionLister lister) {
        super(context);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_type_make, null);

        final List<CateBean.ResultBean> list= getData();
         myGv= (MyGridView) mMenuView.findViewById(R.id.myGv);
        final CommonBaseAdapter<CateBean.ResultBean>  adapter=new CommonBaseAdapter<CateBean.ResultBean>(context,list,R.layout.item_make_cate) {
            @Override
            protected void convert(final CommonViewHolder viewHolder, final CateBean.ResultBean resultBean, int position) {
                                  viewHolder.setTextView(R.id.name,resultBean.getCategory_name());
                                 if (resultBean.isSel()){
                                     viewHolder.getView(R.id.log).setVisibility(View.VISIBLE);
                                 }else {
                                     viewHolder.getView(R.id.log).setVisibility(View.INVISIBLE);
                                 }

                    viewHolder.getView(R.id.all).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                              if (resultBean.isSel()){

                              }else {
                                  for (int i = 0; i < getmData().size(); i++) {
                                      getmData().get(i).setSel(false);}
                                  lister.map(resultBean);
                                  resultBean.setSel(true);
                                  notifyDataSetChanged();

                              }
                              dismiss();

                        }
                    });


            }
        };
         myGv.setAdapter(adapter);

          Logger.e("ddddddddddd","fffffffff height =="+height);
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


    public interface OnOptionLister {

        void map(CateBean.ResultBean cateId);

    }


    List<CateBean.ResultBean> getData(){
              List<CateBean.ResultBean>  list=new ArrayList<>();
        CateBean.ResultBean  bean=new CateBean.ResultBean("","每日必推");
        CateBean.ResultBean  bean1=new CateBean.ResultBean("price_desc","价格由高到低");
        CateBean.ResultBean  bean2=new CateBean.ResultBean("price","价格由低到高");
        CateBean.ResultBean  bean3=new CateBean.ResultBean("month_sales","销量由高到低");
                   list.add(bean);
                   list.add(bean1);
                   list.add(bean2);
                   list.add(bean3);
              return  list;
    }







}
