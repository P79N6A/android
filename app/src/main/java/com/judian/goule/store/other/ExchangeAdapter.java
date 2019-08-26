package com.judian.goule.store.other;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.judian.goule.store.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */

public class ExchangeAdapter extends BaseQuickAdapter<CommodityBean.ResultBean,BaseViewHolder> {
    public ExchangeAdapter(@LayoutRes int layoutResId, @Nullable List<CommodityBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityBean.ResultBean item,int p) {
        helper.setText(R.id.commodity_title,item.getTitle())
                .setText(R.id.commodity_jifen,item.getRequire_points())
                .setText(R.id.commodity_num,item.getCount())
                .setText(R.id.commodity_money,item.getPrice());
        Picasso.with(mContext).load(item.getPict_url_two()).into((ImageView) helper.getView(R.id.commodity_img));

    }
}
