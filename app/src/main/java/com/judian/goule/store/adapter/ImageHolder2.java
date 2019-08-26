package com.judian.goule.store.adapter;

import android.content.Context;
import android.net.Uri;

import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.bean.DHBannerBean;


/**
 * Created by Administrator on 2017/6/20.
 */
public class ImageHolder2 implements Holder<DHBannerBean.ResultBean> {
    private SimpleDraweeView iv;

    @Override
    public SimpleDraweeView createView(Context context) {
        iv = new SimpleDraweeView(context);
        iv.setScaleType(SimpleDraweeView.ScaleType.CENTER_CROP);
        return iv;
    }

    @Override
    public void UpdateUI(Context context, int position, DHBannerBean.ResultBean data) {
        setImg(iv, data.getBanner());

    }
    private  void setImg(SimpleDraweeView mImg, String url)
    {
        Uri uri = Uri.parse(url);
        ImageRequest request=null;
                request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setResizeOptions(new ResizeOptions(MyApplication.width-200, (int) ((MyApplication.width-200)*0.4)))
                        .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mImg.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .setImageRequest(request).build();
        mImg.setController(controller);
    }

}