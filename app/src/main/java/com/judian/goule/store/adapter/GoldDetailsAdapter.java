package com.judian.goule.store.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.GoldDetailsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */

public class GoldDetailsAdapter extends BaseQuickAdapter<GoldDetailsBean.ResultBean.GoldDetailBean,BaseViewHolder> {
    public GoldDetailsAdapter(@LayoutRes int layoutResId, @Nullable List<GoldDetailsBean.ResultBean.GoldDetailBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoldDetailsBean.ResultBean.GoldDetailBean item,int p) {

        if (!item.getType().equals("4")) {
//            helper.setTextColor(R.id.gold_num,R.color.red);
            helper.setText(R.id.gold_num,getMoney(item));
              TextView tv=    helper.getView(R.id.gold_num);
                    tv.setTextColor(mContext.getResources().getColor(R.color.red));
        }
           String type="";
           switch (item.getType()){

               case "1":
                   type="晒单";
                   break;
               case "2":
                   type="点赞";
                   break;

               case "3":
                   type="加精 ";
                   break;
               case "4":
                   type="兑换";
                   break;
           }

        helper.setText(R.id.gold_name,type);
        helper.setText(R.id.gold_time,item.getAdd_time());
    }
    protected String getMoney(GoldDetailsBean.ResultBean.GoldDetailBean type){
        String num =  "";
        switch (Integer.parseInt(type.getType())){
            case 4:
                num = "-"+type.getScore();
                break;
            default:
                num = "+"+type.getScore();
        }
        return num;
    }
}
