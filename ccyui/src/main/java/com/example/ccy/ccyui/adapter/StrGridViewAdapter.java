package com.example.ccy.ccyui.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccy.ccyui.R;
import com.example.ccy.ccyui.bean.HomeCate;

import java.util.List;

//网格视图适配器
public class StrGridViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<HomeCate> mList;//


	public StrGridViewAdapter(Context context, List<HomeCate> list){
		this.mContext = context;
		this.mList = list;

	}
	@Override
	public int getCount() {//图片的数量
		return mList==null?0:mList.size();
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
		if(convertView==null){
			viewHolder = new ViewHolder();
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);
			convertView = layoutInflater.inflate(R.layout.sub_str_gridview, null);
			viewHolder.ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
			viewHolder.tvCateName = (TextView) convertView.findViewById(R.id.tvCateName);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (position<mList.size()){
			HomeCate cate = mList.get(position);


			if (cate.getIcon()!=0){
				viewHolder.ivPic.setImageResource(cate.getIcon());
			}
			viewHolder.tvCateName.setText(cate.getCate_name());

		}
		return convertView;
	}

	class ViewHolder {
		ImageView ivPic;
		TextView tvCateName;
	}
}
