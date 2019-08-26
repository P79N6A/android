package com.example.ccy.ccyui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by chinanet on 16/11/18.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    protected  List<T> mData;
    protected  Context mContext;
    protected LayoutInflater mInflater;
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;


    public BaseRecyclerAdapter(Context ctx, List<T> list) {
        mData = (list != null) ? list : new ArrayList<T>();
        mContext = ctx;
        mData=new ArrayList<>();
        mInflater = LayoutInflater.from(ctx);
    }
    public BaseRecyclerAdapter(Context ctx) {
        mContext = ctx;
        mData=new ArrayList<>();
        mInflater = LayoutInflater.from(ctx);
    }

     public  List<T> getmData(){
         return  mData;
     }


    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        if (observer!=null) {
            super.unregisterAdapterDataObserver(observer);
        }
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerViewHolder holder = new RecyclerViewHolder(mContext,
                mInflater.inflate(getItemLayoutId(viewType), parent, false));


        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {





        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(mData.get(position), holder.getLayoutPosition());
                }
            });
        }

        list.add(holder.itemView);
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }
        try {
            bindData(holder, position, mData.get(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public   List<View>  list=new ArrayList<>();

      public  View  getView(int  op,int  id){
          return list.get(op).findViewById(id);
      }



    @Override
    public int getItemCount() {
        return  mData==null?0:mData.size();
    }

    public void add(int pos, T item) {
        mData.add(pos, item);
        notifyItemInserted(pos);
    }
    public void add( T item) {
        mData.add( item);
        notifyDataSetChanged();
    }

    public void delete(int pos) {
        mData.remove(pos);
        notifyItemRemoved(pos);
    }
    public void delete(T pos) {
        mData.remove(pos);
      notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mLongClickListener = listener;
    }

    abstract public int getItemLayoutId(int viewType);

    abstract public void bindData(RecyclerViewHolder holder, int position, T item) throws JSONException;

    public void setData(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void setNewData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }



    public void setData() {
        mData = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void setNull() {
        mData.clear();
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        public void onItemClick(Object  itemView, int pos);
    }

    public interface OnItemLongClickListener {
        public void onItemLongClick(View itemView, int pos);
    }




}