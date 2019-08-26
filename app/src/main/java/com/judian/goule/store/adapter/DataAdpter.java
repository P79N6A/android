package com.judian.goule.store.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ccy.ccyui.util.Logger;
import com.facebook.drawee.view.SimpleDraweeView;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.GoodListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21.
 */

public class DataAdpter extends RecyclerView.Adapter<DataAdpter.DataHolde> {

    List<GoodListBean.ResultBean>  mData;
       Context  context;

    public DataAdpter(Context context, List<GoodListBean.ResultBean>  mData) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public DataHolde onCreateViewHolder(ViewGroup parent, int viewType) {
               View view= LayoutInflater.from(context).inflate(R.layout.item_comment1,parent);
        return new DataHolde(view);
    }

     public  void addAll(  List<GoodListBean.ResultBean>  datas){
         mData.addAll(datas);
         notifyDataSetChanged();
     }

     public  void setNull(){
         mData.clear();
         notifyDataSetChanged();
     }



    @Override
    public void onBindViewHolder(DataHolde holder, int position) {
        GoodListBean.ResultBean hotBean=mData.get(position);
        Logger.d("xxxxxxxxx","onBindViewHolder");
          holder.pic.setText(hotBean.getPrice());
          holder.title.setText(hotBean.getTitle());
          holder.payOld.setText(hotBean.getReserve_price());




    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public  class DataHolde  extends RecyclerView.ViewHolder{
       TextView  pic;
       TextView  title;
       TextView  payOld;
       SimpleDraweeView face;


     public DataHolde( View itemView) {
         super(itemView);

         pic= (TextView) itemView.findViewById(R.id.pic);
         title= (TextView) itemView.findViewById(R.id.title);
         payOld= (TextView) itemView.findViewById(R.id.payOld);
         face= (SimpleDraweeView) itemView.findViewById(R.id.face);





     }
 }



}
