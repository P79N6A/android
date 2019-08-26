package com.judian.goule.store.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.ccy.ccyui.listener.NoDoubleClickListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.utils.FrescoUtils;
import com.judian.goule.store.utils.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页商品列表适配器
 */

public class MeAdapter extends DelegateAdapter.Adapter<MeAdapter.MyViewHolder> {

    Context context;
    List<GoodListBean.ResultBean> mData;

    public MeAdapter(Context context, List<GoodListBean.ResultBean> list) {
        this.context = context;
        this.mData = list;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_coupon, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        final GoodListBean.ResultBean hotBean = mData.get(i);
        String price = hotBean.getPrice();

        myViewHolder.pic.setText(price);
        switch (hotBean.getUser_type()) {
            case "0":
                myViewHolder.type.setImageResource(R.mipmap.tb21);
                myViewHolder.odl.setText("淘宝价¥" + hotBean.getReserve_price());
                break;
            case "1":
                myViewHolder.type.setImageResource(R.mipmap.tm);
                myViewHolder.odl.setText("天猫价¥" + hotBean.getReserve_price());
                break;
        }

        FrescoUtils.load(hotBean.getPict_url(), myViewHolder.face, 2, 20, R.drawable.ioc_errer_image_z_y);
        myViewHolder.title.setText("          " + hotBean.getTitle());
        myViewHolder.xl.setText(hotBean.getMonth_sales() + "人已买");
        UserInfo userInfo = UserInfoDBUtil.get(context);
//        Log.i("tiancao", "数据" + userInfo.toString());
        if (userInfo.getResult() != null) {
            if (userInfo.getResult().getLevel().equals("6")) {
                myViewHolder.jifen.setVisibility(View.GONE);
                myViewHolder.shengji_zhuan_tv.setText("分享赚" + hotBean.getFanli_money_shengji() + "");
            } else {
                myViewHolder.jifen.setText("分享赚" + hotBean.getFanli_money_fenxiang() + "");
                myViewHolder.shengji_zhuan_tv.setText("升级赚" + hotBean.getFanli_money_shengji() + "");
            }
        } else {
            myViewHolder.jifen.setText("分享赚" + hotBean.getFanli_money_fenxiang() + "");
            myViewHolder.shengji_zhuan_tv.setText("升级赚" + hotBean.getFanli_money_shengji() + "");
        }

/*        if (hotBean.getRebate_money() == null) {
            myViewHolder.info.setText(hotBean.getRebate_money());
            myViewHolder.pic1.setText("¥" + hotBean.getRebate_money());
        }*/

        if (hotBean.getHave_coupon() == 1) {
            myViewHolder.linquan.setVisibility(View.VISIBLE);
            myViewHolder.linquan.setText("领券减 ¥" + hotBean.getCoupon_money());
        } else {
            myViewHolder.linquan.setVisibility(View.GONE);
        }

        myViewHolder.all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.mGoodsClick(hotBean);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private GoodsListener mListener;

    public interface GoodsListener {
        void mGoodsClick(GoodListBean.ResultBean hotBean);
    }

    public void setmListener(GoodsListener mListener) {
        this.mListener = mListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pic;
        TextView pich;
        ImageView type;
        TextView odl;
        SimpleDraweeView face;
        TextView title;
        TextView xl;
        TextView jifen;
        TextView shengji_zhuan_tv;
        TextView info;
        TextView pic1;
        TextView linquan;
        LinearLayout all;

        public MyViewHolder(View view) {
            super(view);
            face = (SimpleDraweeView) view.findViewById(R.id.face);
            pic = (TextView) view.findViewById(R.id.pic);
            pich = (TextView) view.findViewById(R.id.pich);
            type = (ImageView) view.findViewById(R.id.type);
            odl = (TextView) view.findViewById(R.id.odl);
            title = (TextView) view.findViewById(R.id.title);
            xl = (TextView) view.findViewById(R.id.xl);
            jifen = (TextView) view.findViewById(R.id.jifen);
            shengji_zhuan_tv = (TextView) view.findViewById(R.id.shengji_zhuan_tv);
            info = (TextView) view.findViewById(R.id.info);
            pic1 = (TextView) view.findViewById(R.id.pic1);
            all = (LinearLayout) view.findViewById(R.id.all);
            linquan = (TextView) view.findViewById(R.id.linquan);
        }


    }
}

