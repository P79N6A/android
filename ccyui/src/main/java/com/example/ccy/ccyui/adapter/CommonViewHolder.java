package com.example.ccy.ccyui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.ccy.ccyui.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;


/**
 * 万能ViewHolder
 * @author Administrator
 *
 */
public class CommonViewHolder {

	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;
	private Context mContext;
	private int mScreenWidth;
	
	CommonViewHolder(Context context, int layoutID, int position, View convertView, ViewGroup parent){
		this.mContext = context;
		this.mViews = new SparseArray<View>();
		this.mPosition = position;
		this.mConvertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
		this.mConvertView.setTag(this);
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		mScreenWidth = wm.getDefaultDisplay().getWidth();
	}
	
	public static CommonViewHolder getViewHolder(Context context, int layoutID, int position, 
			View convertView, ViewGroup parent){
		if(convertView==null) {
			CommonViewHolder viewHolder = new CommonViewHolder(context, layoutID, position, convertView, parent);
			return viewHolder;
		} else {
			CommonViewHolder viewHolder = (CommonViewHolder) convertView.getTag();
			viewHolder.mPosition = position;
			return viewHolder;
		}
	}

	public View getConvertView() {
		return this.mConvertView;
	}
	
	public <T extends View>T getView(int viewID){
		View view = mViews.get(viewID);
		
		if(view==null){
			view = getConvertView().findViewById(viewID);
			mViews.put(viewID, view);
		}
		
		return (T)view;
	}
		public TextView getTextView(int viewID){
			TextView view = (TextView) mViews.get(viewID);

		if(view==null){
			view = (TextView) getConvertView().findViewById(viewID);
			mViews.put(viewID, view);
		}

		return view;
	}

	public CommonViewHolder setTextView(int viewID, String text){
		TextView tv = getView(viewID);
		tv.setText(text);
		return this;
	}
	
	//带中划线的TextView
	public CommonViewHolder setTextViewHaveStrikeLine(int viewID, String text){
		TextView tv = getView(viewID);
		tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
		tv.setText(text);
		return this;
	}
	
	public CommonViewHolder setImageResource(int viewID, int resourceID,int width){
		ImageView iv = getView(viewID);
		LayoutParams layoutParams = iv.getLayoutParams();
		if (width!=0){
			layoutParams.width= (int) (width/2.2);
			layoutParams.height= (int) (layoutParams.width);
		}else {
			layoutParams.width= RelativeLayout.LayoutParams.MATCH_PARENT;
			layoutParams.height= (int) (layoutParams.width*1.33);
		}
		iv.setLayoutParams(layoutParams);
		iv.setImageResource(resourceID);
		return this;
	}
	public CommonViewHolder setImageResource(int viewID, int resourceID){
		ImageView iv = getView(viewID);
		iv.setImageResource(resourceID);
		return this;
	}

	//为商品格子动态设置图片的宽高,rate表示长宽比率,nums表示每行显示几列图片
	public CommonViewHolder setImageResource(int viewID, int resourceID, int rate, int nums){
		ImageView iv = getView(viewID);

		LayoutParams params = iv.getLayoutParams();
		params.width = mScreenWidth / nums;
		params.height = params.width * rate;
		iv.setLayoutParams(params);
		iv.setImageResource(resourceID);
		return this;
	}

	//根据图片的路径获取图片(主要用于显示本地所有图片)
	public CommonViewHolder setImageResource(int viewID, String pathName, int rate, int nums){
		ImageView iv = getView(viewID);
		if (pathName==null){
		//	iv.setImageResource(R.mipmap.take_photo);
		}
		LayoutParams params = iv.getLayoutParams();
		params.width = mScreenWidth / nums;
		params.height = params.width * rate;
		iv.setLayoutParams(params);
		//iv.setImageBitmap(BitmapFactory.decodeFile(pathName));//不能粗暴的使用该方法,会有OOM的危险
		// ImageLoader.getInstance().displayImage(Scheme.FILE.wrap(pathName), iv);//调用UIL组件获取图片显示在ImageView
		return this;
	}

	public CommonViewHolder setImageBitmap(int viewID, Bitmap bitmap){
		ImageView iv = getView(viewID);
		iv.setImageBitmap(bitmap);
		return this;
	}

	//根据图片路径URI来得到Bitmap
		private Bitmap getBitmap(String pathName){
			return BitmapFactory.decodeFile(pathName);
		}

	public CommonViewHolder setImageURI(int viewID,String bean, String uri,double scale){
		SimpleDraweeView iv = getView(viewID);
		//采用第三方框架比如Google volley, assync-http, universal-image-loader
		LayoutParams layoutParams = iv.getLayoutParams();
		if (mScreenWidth!=0){
			layoutParams.width= (int) (mScreenWidth/scale);
			layoutParams.height= (int) (layoutParams.width);
		}else {
			layoutParams.width= RelativeLayout.LayoutParams.MATCH_PARENT;
			layoutParams.height= (int) (layoutParams.width*1.33);
		}

		iv.setLayoutParams(layoutParams);
		if (uri==null){
		}else {
			setImg(iv,bean+uri,1);
		}
		return this;
	}
	public CommonViewHolder setImageURI(int viewID,String bean, String url){
		SimpleDraweeView iv = getView(viewID);
		setImg(iv,bean+url,0);
		return this;
	}

	public CommonViewHolder setImageURI(int viewID,String url){
		SimpleDraweeView iv = getView(viewID);
		if (url!=null)  iv.setImageURI(url);
		return this;
	}
	private  void setImg(SimpleDraweeView mImg, String url,int option)
	{
		Uri uri = Uri.parse(url);

		ImageRequest request=null;
		switch (option){
			case  0:
				request = ImageRequestBuilder.newBuilderWithSource(uri)
						.setResizeOptions(new ResizeOptions(400, 400))
						.build();
				break;
			case  1:
				request = ImageRequestBuilder.newBuilderWithSource(uri)
						.setResizeOptions(new ResizeOptions(400, 400))
						.build();
				break;
		}

		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setOldController(mImg.getController())
				.setControllerListener(new BaseControllerListener<ImageInfo>())
				.setImageRequest(request).build();
		mImg.setController(controller);
	}



	public CommonViewHolder setImagePURI(int viewID,String bean, String uri){
		SimpleDraweeView iv = getView(viewID);
		if (uri==null){
		}else {
			Picasso.with(mContext).load(bean+uri).into(iv);
//			iv.setImageURI(bean+uri);
		}
		return this;
	}






	public int getPosition() {
		return mPosition;
	}
}