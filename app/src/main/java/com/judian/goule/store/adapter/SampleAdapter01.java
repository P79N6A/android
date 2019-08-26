package com.judian.goule.store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.judian.goule.store.R;
import com.judian.goule.store.activity.WebActivity;
import com.judian.goule.store.bean.XuBean;
import com.taobao.library.BaseBannerAdapter;
import com.taobao.library.VerticalBannerView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */

public class SampleAdapter01 extends BaseBannerAdapter<XuBean.ResultBean> {

    private final Context context;
    private List<XuBean.ResultBean> mDatas;

    public SampleAdapter01(Context context,List<XuBean.ResultBean> datas) {
        super(datas);
        mDatas=datas;
        this.context = context;
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,null);
    }

    @Override
    public void setItem(final View view, final XuBean.ResultBean data) {
        TextView tv = (TextView) view.findViewById(R.id.txt);
        tv.setText(data.getTitle());
        //你可以增加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //比如打开 url
                WebActivity.openMain(context,data.getTitle(), data.getUrl());
//                Toast.makeText(view.getContext(),data,Toast.LENGTH_SHORT).show();
            }
        });
    }





}