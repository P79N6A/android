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
import com.judian.goule.store.R;
import com.judian.goule.store.bean.BannerBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.utils.FrescoUtils;


/**
 * Created by Administrator on 2017/6/20.
 */
public class ImageHolder implements Holder<String> {
    private SimpleDraweeView iv;

    @Override
    public SimpleDraweeView createView(Context context) {
        iv = new SimpleDraweeView(context);
        iv.setScaleType(SimpleDraweeView.ScaleType.CENTER_CROP);
        return iv;
    }

    @Override
    public void UpdateUI(Context context, int position, String url) {
        FrescoUtils.load(url,iv);
    }
}