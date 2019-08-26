package com.judian.goule.store.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ccy.ccyui.listener.NoDoubleClickListener;
import com.example.ccy.ccyui.util.ToastUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.ExDetailBean;
import com.judian.goule.store.other.CustomNum;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.self.EtActivity;
import com.judian.goule.store.utils.TestData;
import com.judian.goule.store.views.BaseView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sks on 2015/9/15.
 * 头像选择弹出框
 */
public class EXPayPopupwindow extends PopupWindow {
    private TextView  txt,copy;
    private View v;
    private ImageView  close;
    private  List<TextView> mTvs=new ArrayList<>(); String mTyprs="" ,godNum="1";

    public EXPayPopupwindow(final Context context, final ExDetailBean.ResultBean result) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.go_buy, null);
        FlexboxLayout layout = (FlexboxLayout) v.findViewById(R.id.type);
        CustomNum customNum = (CustomNum) v.findViewById(R.id.num_set);
        TextView typeStr = (TextView) v.findViewById(R.id.typeStr);
        TextView pic = (TextView) v.findViewById(R.id.pic);
        ImageView img = (ImageView) v.findViewById(R.id.img);
        TextView godId = (TextView) v.findViewById(R.id.godId);
        godId.setText("商品编号: "+result.getId());
        TextView numx = (TextView) v.findViewById(R.id.numx);
            v.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();

                }
            });
        Picasso.with(context).load(result.getPict_url()).into(img);

        numx.setText("(剩余"+result.getCount()+"件)");
        typeStr.setText(result.getAttr_name());
        pic.setText(result.getRequire_points());
        customNum.setMaxNum(10);
        customNum.setCostom(new CustomNum.costom() {
            @Override
            public void num(int num) {
                godNum=num+"";
            }
        });
        final List<String> goods_type = result.getGoods_type();

        for (int i = 0; i < goods_type.size(); i++) {
            mTvs.add(TestData.createNewFlexItemTextView(context, goods_type.get(i), new TestData.FlexLintener() {
                @Override
                public void flexOnClick(String txt) {
                    for (int i1 = 0; i1 < mTvs.size(); i1++) {
                        mTvs.get(i1).setTextColor(context.getResources().getColor(R.color.dark_greyt));
                        mTvs.get(i1).setBackgroundResource(R.drawable.item_s);
                        if (goods_type.get(i1).equals(txt)) {
                             if ( mTyprs .equals(txt)){
                                 mTyprs="";
                             }else {
                                 mTvs.get(i1).setTextColor(context.getResources().getColor(R.color.white));
                                 mTvs.get(i1).setBackgroundResource(R.drawable.item_ex);
                                 mTyprs = txt;
                             }


                        }
                    }
                }


            }));

            layout.addView(mTvs.get(i));
        }

        v.findViewById(R.id.goPay).setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {

                   if (mTyprs.equals("")){
                       ToastUtils.toast(context,"请选择商品"+result.getAttr_name());
                   }else {
                       new CdataPresenter(context).getGoPay(godNum, result.getId(), mTyprs, new BaseView<BaseBean>() {
                           @Override
                           public void result(BaseBean bean) {
                               ToastUtils.toast(context,bean.getMsg());
                               if (bean.getCode()==200){
                                   dismiss();
                               }else if (bean.getCode()==401) {
                                   context.startActivity(new Intent(context, EtActivity.class));}
                           }

                           @Override
                           public void error() {

                           }
                       });


                   }


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
}
