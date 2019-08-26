package com.judian.goule.store.adapter.optimization;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.YxGoodsActivity;
import com.judian.goule.store.bean.OptimizationData;
import com.judian.goule.store.bean.YouXuanData;
import com.judian.goule.store.utils.FrescoUtils;


import java.util.List;

/**
 * Created by fenxiangba on 2017/8/25.
 * 优选列表
 */

public class OptimizationAdapter extends BaseAdapter {

    private Context mContext;
    private List<YouXuanData> mData;

    public OptimizationAdapter(Context mContext, List<YouXuanData> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void setmData(List<YouXuanData> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_optimization, parent, false);
        }
        OptimizationAdapter.FriendViewHolder holder = (OptimizationAdapter.FriendViewHolder) view.getTag();
        if (holder == null) {
            holder = new OptimizationAdapter.FriendViewHolder();
            holder.mPic = (SimpleDraweeView) view.findViewById(R.id.optimization_item_sdv);
            holder.all = (LinearLayout) view.findViewById(R.id.all);
            view.setTag(holder);
        }
        FrescoUtils.load(mData.get(position).getPic(), holder.mPic, 2, 20, R.drawable.ioc_errer_image_c_y);
        holder.all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YxGoodsActivity.openMain(mContext, mData.get(position).getUrl(), mData.get(position).getName());
            }
        });
        return view;
    }

    class FriendViewHolder {
        SimpleDraweeView mPic;
        LinearLayout all;
    }

}
