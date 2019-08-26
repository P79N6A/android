package com.example.ccy.ccyui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinanet on 16/11/18.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;//集合类，layout里包含的View,以view的id作为key，value是view对象
    private Context mContext;//上下文对象
    private int mScreenWidth;
    public RecyclerViewHolder(Context ctx, View itemView) {
        super(itemView);
        mContext = ctx;
        mViews = new SparseArray<View>();
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = wm.getDefaultDisplay().getWidth();
    }

    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    public View getView(int viewId) {
        return findViewById(viewId);
    }

    public TextView getTextView(int viewId) {
        return (TextView) getView(viewId);
    }

    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return (ImageView) getView(viewId);
    }

    public ImageButton getImageButton(int viewId) {
        return (ImageButton) getView(viewId);
    }

    public EditText getEditText(int viewId) {
        return (EditText) getView(viewId);
    }

    public RecyclerViewHolder setText(int viewId, String value) {
        TextView view = findViewById(viewId);
        view.setText(value);
        return this;
    }
    public RecyclerViewHolder setTextViewHaveStrikeLine(int viewID, String text){
        TextView tv = findViewById(viewID);
        tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        tv.setText(text);
        return this;
    }
    public RecyclerViewHolder setHSVGallery(int viewID, List<Integer> list, ArrayList<Integer> listBig){

        RecyclerView hsv = findViewById(viewID);

        return this;
    }


    public RecyclerViewHolder setImageResource(int viewId, int value) {
        ImageView view = findViewById(viewId);
        view.setImageResource(value);
        return this;
    }


    public RecyclerViewHolder setImageBitmap(int viewId, Bitmap value) {
        ImageView view = findViewById(viewId);
        view.setImageBitmap(value);
        return this;
    }



    public RecyclerViewHolder setBackground(int viewId, int resId) {
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }
    //根据图片的路径获取图片(主要用于显示本地所有图片)
    public RecyclerViewHolder setImageResource(int viewID, String pathName, int rate, int nums){
        ImageView iv = (ImageView) getView(viewID);
        if (pathName==null){
            //iv.setImageResource(R.mipmap.take_photo);
        }
        ViewGroup.LayoutParams params = iv.getLayoutParams();
        params.width = mScreenWidth / nums;
        params.height = params.width * rate;
        iv.setLayoutParams(params);
        //iv.setImageBitmap(BitmapFactory.decodeFile(pathName));//不能粗暴的使用该方法,会有OOM的危险
        ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(pathName), iv);//调用UIL组件获取图片显示在ImageView
        return this;
    }

    public SimpleDraweeView getSimpleDraweeView(int viewId) {
        return (SimpleDraweeView) getView(viewId);
    }
    public RecyclerViewHolder setImageURI(int viewID, String bean, String uri, double scale){
        SimpleDraweeView iv =  findViewById(viewID);
        //采用第三方框架比如Google volley, assync-http, universal-image-loader
        ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
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
    public RecyclerViewHolder setImageURI(int viewID, String bean, String uri){
        SimpleDraweeView iv =  findViewById(viewID);
        //采用第三方框架比如Google volley, assync-http, universal-image-loader
        if (uri==null){
        }else {
            setImg(iv,bean+uri,0);
        }
        return this;
    }
    public  static   void setImg(SimpleDraweeView mImg, String url,int option)
    {   if (url==null)return;
        Uri uri = Uri.parse(url);

        ImageRequest request=null;
        switch (option){
            case  0:
                request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setResizeOptions(new ResizeOptions(350, 350))
                        .build();
                break;
            case  1:
                request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setResizeOptions(new ResizeOptions(350, 350))
                        .build();
                break;
        }

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mImg.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .setImageRequest(request).build();
        mImg.setController(controller);
    }

    public RecyclerViewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public RecyclerViewHolder setImagePURI(int id, String bean, String uri) {
        ImageView iv =  findViewById(id);
        Log.d("RecyclerViewHolder", "setImagePURI: "+bean+uri);

        if (uri==null||uri.equals("")){

        }else {
            Picasso.with(mContext).load(bean+uri).into(iv);
//			iv.setImageURI(bean+uri);
        }
        return this;

    }
    public RecyclerViewHolder setImageStaURI(int id, String bean, String uri) {
        SimpleDraweeView iv =  findViewById(id);
        Log.d("RecyclerViewHolder", "setImagePURI: "+bean+uri);
        setControllerListener(iv,bean+uri, (int) (mScreenWidth/2.3));
        return this;

    }


    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageWidth) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                layoutParams.width = imageWidth;
                layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
                simpleDraweeView.setLayoutParams(layoutParams);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                Log.d("TAG", "Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(Uri.parse(imagePath)).build();
        simpleDraweeView.setController(controller);
    }


  public    interface   ImageLintener{
        void  ok();
    }


    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageWidth, final ImageLintener lintener) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                layoutParams.width = imageWidth;
                layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
                simpleDraweeView.setLayoutParams(layoutParams);
                lintener.ok();
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                Log.d("TAG", "Intermediate image received");

            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(Uri.parse(imagePath)).build();
        simpleDraweeView.setController(controller);

    }


}