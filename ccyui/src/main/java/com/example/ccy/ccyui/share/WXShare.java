package com.example.ccy.ccyui.share;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.ccy.ccyui.R;
import com.example.ccy.ccyui.util.Constants;
import com.example.ccy.ccyui.util.Logger;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/24.
 */

public class WXShare {

    private static WXShare wxShare;
    private Activity mContext;
    private IWXAPI api;
    private SendMessageToWX.Req mReq;

    private WXShare(Activity context) {

        mContext = context;
//        微信
        /*api = WXAPIFactory.createWXAPI(context, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);*/
        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
        ChechVersionIsSupport();
    }

    public static WXShare getInstance(Activity context) {
        if (wxShare == null) {
            wxShare = new WXShare(context);

        }
        return wxShare;
    }

    /**
     * 拉起微信发送多张图片
     */
    public static void shareImages(Context mContext, ArrayList<Uri> imageUris) {
        Intent shareIntent = new Intent();
        // 1 Finals 2016-11-2 调用系统分享
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // 2 Finals 2016-11-2 添加图片数组

        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        shareIntent.setType("image/*");

        ComponentName mComponentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        shareIntent.setComponent(mComponentName);
        // 4 Finals 2016-11-2 开始分享。
        mContext.startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }

    /**
     * 拉起微信朋友圈发送多张图片
     */
    public static void shareweipyqSomeImg(Context context, ArrayList<Uri> imageUris) {
        Intent shareIntent = new Intent();
        //1调用系统分析
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        shareIntent.setType("image/*");

        //3指定选择微信
        ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        shareIntent.setComponent(componentName);

        //4开始分享
        context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }

    /**
     * 拉起qq发送多张图片
     */
    public static void shareImagesQQ(Context mContext, ArrayList<Uri> imageUris) {
        Intent shareIntent = new Intent();
        // 1 Finals 2016-11-2 调用系统分享
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // 2 Finals 2016-11-2 添加图片数组

        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        shareIntent.setType("image/*");

        ComponentName mComponentName = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
        shareIntent.setComponent(mComponentName);
        // 4 Finals 2016-11-2 开始分享。
        mContext.startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }


    //
    public static void shareImagesWB(Context mContext, ArrayList<Uri> imageUris) {
        Intent shareIntent = new Intent();
        // 1 Finals 2016-11-2 调用系统分享
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // 2 Finals 2016-11-2 添加图片数组

        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        shareIntent.setType("image/*");

        // 3 Finals 2016-11-2 指定选择微信。
//        ComponentName mComponentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
//        ComponentName mComponentName = new ComponentName("com.sina.weibog3", "com.sina.weibo.EditActivity");
//        shareIntent.setComponent(mComponentName);
        // 4 Finals 2016-11-2 开始分享。
        shareIntent.setPackage("com.sina.weibo");
        mContext.startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }


    //    系统
    public static void shareImagesSys(Context mContext, ArrayList<Uri> imageUris) {
        Intent mulIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        mulIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        mulIntent.setType("image/*");
        mContext.startActivity(Intent.createChooser(mulIntent, "多文件分享"));

    }


    public void reData() {

        api.sendReq(mReq);
    }


    public void shareWX(int option, String url) {
        ChechVersionIsSupport();
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage message = new WXMediaMessage(webpage);
        message.title = "全网优惠券大全";
        message.description = "搜券神器，汇集全网内部优惠券，网购省钱，最高可省90%";
        Bitmap thumb = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.logo);
        message.thumbData = bmpToByteArray2(thumb, true);
        mReq = new SendMessageToWX.Req();
        mReq.message = message;
        switch (option) {
            case 0:
//                       朋友圈
                mReq.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
            case 1:
//                       聊天
                mReq.scene = SendMessageToWX.Req.WXSceneSession;

                break;
        }


        api.sendReq(mReq);


    }


    // 网页分享
    public void shareWX(int option, String title, String url, String txt, Bitmap bmp) {
        ChechVersionIsSupport();
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage message = new WXMediaMessage(webpage);
        message.title = title;
        message.description = txt;
//        message.thumbData=bmpToByteArray(img,false);

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 300, 300, true);

        message.thumbData = bitmap2Bytes(thumbBmp, 300); // 设置缩略图
        mReq = new SendMessageToWX.Req();
        mReq.transaction = buildTransaction("webpage");
        mReq.message = message;
        switch (option) {
            case 0:
//                       朋友圈
                mReq.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
            case 1:
//                       聊天
                mReq.scene = SendMessageToWX.Req.WXSceneSession;

                break;
        }
        api.sendReq(mReq);


    }


    public void shareWX(int option, String title, String url, String txt, String img) {
        ChechVersionIsSupport();
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage message = new WXMediaMessage(webpage);
        message.title = title;
        message.description = txt;

//        message.thumbData=bmpToByteArray(img,false);
        mReq = new SendMessageToWX.Req();
//        Logger.e("ddddddd","key=="+url);
        mReq.message = message;
        mReq.transaction = buildTransaction("img");

        switch (option) {
            case 0:
//                       朋友圈
                mReq.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
            case 1:
//                       聊天
                mReq.scene = SendMessageToWX.Req.WXSceneSession;

                break;
        }
        api.sendReq(mReq);
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxkb && options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return output.toByteArray();
    }


    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    public void shareWX(int option, String msg, String url, int img) {
        ChechVersionIsSupport();
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage message = new WXMediaMessage(webpage);
        message.title = msg;
        message.description = "我一直用千会小姐APP，去淘宝平台购物,免费领淘宝内部优惠券，还有高额返利拿到手软！........";
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), img);
        message.thumbData = bmpToByteArray(bitmap, true);
        mReq = new SendMessageToWX.Req();
//        Logger.e("ddddddd","key=="+url);
        mReq.message = message;
        switch (option) {
            case 0:
//                       朋友圈
                mReq.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
            case 1:
//                       聊天
                mReq.scene = SendMessageToWX.Req.WXSceneSession;

                break;
        }


        api.sendReq(mReq);


    }


    /**
     * 分享 图片  微信
     *
     * @param option
     * @param bitmap
     * @param w
     * @param h
     */
    public void shareWX(int option, Bitmap bitmap, int w, int h) {
        ChechVersionIsSupport();
        WXImageObject img = new WXImageObject(bitmap);
        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = img;
//
//        message.thumbData=bmpToByteArray(bitmap,true);
        mReq = new SendMessageToWX.Req();


        mReq.message = message;
        switch (option) {
            case 0:
//                       朋友圈
                mReq.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
            case 1:
//                       聊天
                mReq.scene = SendMessageToWX.Req.WXSceneSession;

                break;
        }

        Logger.d("ddddddddd", "api==" + api);
        api.sendReq(mReq);

        Logger.d("ddddddddd", "api222==" + api);

    }


    public static byte[] bmpToByteArray2(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] bmpToByteArray(final Bitmap bmp,
                                        final boolean needRecycle) {
        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i,
                    j), null);
            if (needRecycle)
                bmp.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                // F.out(e);
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }


    public void handleIntent(Intent intent, IWXAPIEventHandler var2) {
        api.handleIntent(intent, var2);
    }


    public void loginWX() {
        if (!api.isWXAppInstalled()) {
            /*Intent intent = new Intent(this, MyDialogActivity.class);
            startActivity(intent);*/
            Log.i("tiancao", "有安装微信");
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        }
    }


    /**
     * 检查微信版本是否支持
     */
    private boolean ChechVersionIsSupport() {

        if (api.getWXAppSupportAPI() == 0) {
            Toast.makeText(mContext, "未发现微信", Toast.LENGTH_SHORT).show();
        }
        return api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }

}
