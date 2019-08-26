package com.judian.goule.store.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 万能适配器
 * @author Administrator
 * @param <T>
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {

	protected Context context;

	public List<T> getmData() {
		return mData;
	}

	protected List<T> mData;
	protected LayoutInflater mLayoutInflater;
	protected int mLayoutID;
	
	public CommonBaseAdapter(Context context, int layoutID) {
		mData=new ArrayList<>();
		this.context = context;
		this.mLayoutID = layoutID;
		this.mLayoutInflater = LayoutInflater.from(context);
	}
	public CommonBaseAdapter(Context context, List<T> list, int layoutID) {
		this.context = context;
		this.mLayoutID = layoutID;
		mData=list;
		if (context==null)return;
		this.mLayoutInflater = LayoutInflater.from(context);
	}


	public void addData( T t){
		mData.add(t);
		notifyDataSetChanged();
	}


	public void addData( T t,int p){
        mData.remove(p);
		mData.add(p,t);
		notifyDataSetChanged();
	}
	public void addAll(List<T>  t){
		mData.addAll(t);
		notifyDataSetChanged();
	}


	public void  subData(T t){
		mData.remove(t);
		notifyDataSetChanged();
	}
	public void  subData(int t){
		mData.remove(t);
		notifyDataSetChanged();
	}



	public void setData( List<T> t){
		mData=new ArrayList<>();
		mData=t;
		notifyDataSetChanged();
	}

	public void setNull(){
		mData.clear();
		notifyDataSetChanged();
	}


	public void setNew(){
		mData=new ArrayList<>();
		notifyDataSetChanged();
	}
	public void setNull1(){
		mData.clear();

	}


	@Override
	public int getCount() {
		return mData!=null? mData.size():0;
	}

	@Override
	public T getItem(int position) {
		return mData.get(position);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
          if (observer!=null)	super.unregisterDataSetObserver(observer);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(context, mLayoutID, position, convertView, parent);
		convert(viewHolder, mData.get(position),position);

		return viewHolder.getConvertView();
	}

	//ViewHolder, T t
	protected abstract void convert(CommonViewHolder viewHolder, T t,int position);


}
