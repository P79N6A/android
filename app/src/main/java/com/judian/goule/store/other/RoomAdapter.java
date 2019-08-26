package com.judian.goule.store.other;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.judian.goule.store.R;
import com.judian.goule.store.db.RoomBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class RoomAdapter extends BaseQuickAdapter<RoomBean,BaseViewHolder> {
    public RoomAdapter(@LayoutRes int layoutResId, @Nullable List<RoomBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomBean item,int p) {
        Log.e("TAG",item.toString());
        if (item.getImage() != null){
            helper.getView(R.id.group_img).setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.live_image));
        }else {
            helper.getView(R.id.group_img).setVisibility(View.GONE);
        }
        if (item.getContent() != null){
            helper.getView(R.id.group_img).setVisibility(View.VISIBLE);
            helper.setText(R.id.live_text,item.getContent());
        }else {
            helper.getView(R.id.group_text).setVisibility(View.GONE);
        }
    }
}
