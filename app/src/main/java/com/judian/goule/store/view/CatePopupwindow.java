package com.judian.goule.store.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.CateBean;
import com.judian.goule.store.utils.TestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class CatePopupwindow extends PopupWindow {


    FlexboxLayout flex;
       Context mContext;
    private View mMenuView;
      int num=1;
    private final List<TextView> mTvs;
    public  LinearLayout all;

    public CatePopupwindow(final Context context,int  height,  final List<CateBean.ResultBean> list, final OnOptionLister lister) {
        super(context);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_cate, null);
        final Map<String, Integer> map = new HashMap<>();
        mMenuView.findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        flex = (FlexboxLayout) mMenuView.findViewById(R.id.flex);
        ScrollView scrollView = (ScrollView) mMenuView.findViewById(R.id.scrollView);
        all = (LinearLayout) mMenuView.findViewById(R.id.all);
        LinearLayout.LayoutParams  layoutParams1= (LinearLayout.LayoutParams) all.getLayoutParams();
        layoutParams1.setMargins(0,height,0,0);
       all.setLayoutParams(layoutParams1);

        ViewGroup.LayoutParams layoutParams = scrollView.getLayoutParams();
         layoutParams.width=MyApplication.width;
        layoutParams.height= (int) (MyApplication.height*0.7);
         scrollView.setLayoutParams(layoutParams);
        flex.setFlexDirection(FlexDirection.ROW);
        flex.setFlexWrap(FlexWrap.WRAP);
        flex.setAlignItems(AlignItems.STRETCH);
        mTvs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            mTvs.add(TestData.createNewFlexItemTextView1(context, list.get(i), new TestData.FlexLintener() {
                @Override
                public void flexOnClick(String txt) {
                    for (int i1 = 0; i1 < mTvs.size(); i1++) {
                        mTvs.get(i1).setTextColor(context.getResources().getColor(R.color.tab_d));
                        mTvs.get(i1).setBackgroundResource(R.drawable.item_s);
                        if (list.get(i1).getCategory_name().equals(txt)){
                            mTvs.get(i1).setTextColor(context.getResources().getColor(R.color.tab_s));
                            mTvs.get(i1).setBackgroundResource(R.drawable.item_r);
                            map.put("id",i1);
                            lister.map(map);
                            dismiss();
                        }
                    }


                }
            }));
            mTvs.get(0).setTextColor(context.getResources().getColor(R.color.tab_s));
            mTvs.get(0).setBackgroundResource(R.drawable.item_r);
            flex.addView(mTvs.get(i));}


        all.setOnClickListener(new OnClickListener() {
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
       public  void setState(int option){
           for (int i = 0; i < mTvs.size(); i++) {
               mTvs.get(i).setTextColor(mContext.getResources().getColor(R.color.tab_d));
               mTvs.get(i).setBackgroundResource(R.drawable.item_s);
           }
           mTvs.get(option).setTextColor(mContext.getResources().getColor(R.color.tab_s));
           mTvs.get(option).setBackgroundResource(R.drawable.item_r);
       }


    public interface OnOptionLister {

        void map(Map<String, Integer> map);

    }

}
