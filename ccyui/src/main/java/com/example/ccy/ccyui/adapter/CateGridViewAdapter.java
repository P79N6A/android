package com.example.ccy.ccyui.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ccy.ccyui.R;
import com.example.ccy.ccyui.bean.HomeCate;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

//网格视图适配器
public class CateGridViewAdapter extends BaseAdapter {
	
	private Context mContext;//
	private List<HomeCate> mList;//
	int mPage;
	public CateGridViewAdapter(Context context, List<HomeCate> list){
		this.mContext = context;
		this.mList = list;

	}

	@Override
	public int getCount() {//图片的数量
		return mList.size()<10?mList.size():10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		if (observer!=null)super.unregisterDataSetObserver(observer);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView==null){//判定装换视图为null
			viewHolder = new ViewHolder();//声明一个Holder
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);//利用容器进行存储数据
			convertView = layoutInflater.inflate(R.layout.sub_cate_gridview, null);
			viewHolder.ivPic = (SimpleDraweeView) convertView.findViewById(R.id.ivPic);
			viewHolder.tvCateName = (TextView) convertView.findViewById(R.id.tvCateName);
			convertView.setTag(viewHolder);//为转换视图设置标签
		} else {
			viewHolder = (ViewHolder) convertView.getTag();//否则直接获取
		}
		HomeCate cate = mList.get(position);
		viewHolder.ivPic.setImageResource(cate.getIcon());
		viewHolder.tvCateName.setText(cate.getCate_name());
		
		return convertView;
	}

	class ViewHolder {
		SimpleDraweeView ivPic;
		TextView tvCateName;
	}
}
