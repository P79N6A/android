package com.judian.goule.store.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.WithdrawalsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */

public class WithdrawalsAdapter extends BaseQuickAdapter<WithdrawalsBean.ResultBean,BaseViewHolder> {
    int op=0;
    public WithdrawalsAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<WithdrawalsBean.ResultBean> data,int op) {
        super(layoutResId, data);
        this.op=op;

    }

    @Override
    protected void convert(BaseViewHolder helper, WithdrawalsBean.ResultBean item,int p) {
            TextView  tv= helper.getView(R.id.stauts);
        String status = "";
        int i = Integer.parseInt(item.getType());
        String v_state = item.getV_state();
        switch (op){
                 case 0:
                     switch (v_state){
                         case "0":
                             status = "待审核";
                             tv.setTextColor(mContext.getResources().getColor(R.color.red));
                             break;
                         case "1":
                             status = "已结算";
                             tv.setTextColor(mContext.getResources().getColor(R.color.y_sel));
                             break;
                     }

                     tv.setText(status);

                     helper.setText(R.id.money_num,item.getMoney());
                     helper.setText(R.id.zhifubao,item.getIntro());
                     helper.setText(R.id.time,item.getAdd_time());
                     break;

                 case 1:
                     switch (i){
                         case 1:
                             status = "结算";
                             tv.setTextColor(mContext.getResources().getColor(R.color.red));
                             break;
                         case 2:
                             status = "签到";
                             tv.setTextColor(mContext.getResources().getColor(R.color.y_sel));
                             break;
                     }

                     tv.setText(status);
                     helper.setText(R.id.money_num,"+"+item.getMoney());
                     helper.setText(R.id.zhifubao,item.getIntro());
                     helper.setText(R.id.time,item.getAdd_time());
                     break;

          case 2:
              helper.setText(R.id.money_num,"+"+item.getScore());
                     switch (i){
                         case 1:
                             status = "结算";
                             tv.setTextColor(mContext.getResources().getColor(R.color.red));
                             break;
                         case 4:
                             helper.setText(R.id.money_num,item.getScore());
                             break;
                     }

//                     tv.setText(status);

                     helper.setText(R.id.zhifubao,item.getInfo());
                     helper.setText(R.id.time,item.getAdd_time());
                     break;



             }



    }

}
