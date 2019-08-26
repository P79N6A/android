package com.judian.goule.store.adapter;

import android.widget.TextView;

import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.holder.EasyRecyclerViewHolder;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.GoodListBean;

/**
 * Created by Administrator on 2018/3/21.
 */

public class EaseAdapter extends EasyRecyclerViewAdapter {
    @Override
    public int[] getItemLayouts() {
        return new int[]{R.layout.item_coupon1};
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        GoodListBean.ResultBean  data=getItem(position);
        if (data==null)return;
        TextView  tit=viewHolder.findViewById(R.id.title);




    }

    @Override
    public int getRecycleViewItemType(int position) {
        return 0;
    }
}
