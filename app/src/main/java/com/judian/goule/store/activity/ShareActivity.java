package com.judian.goule.store.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ccy.ccyui.share.QQShareSelf;
import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.Constants;
import com.example.ccy.ccyui.util.Dialogutils;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.WriterException;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.LiveBean;
import com.judian.goule.store.bean.ShareInfo;
import com.judian.goule.store.bean.ShearTaoBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.presenter.SharePresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.LoadDialog;
import com.judian.goule.store.view.SharePopupwindow;
import com.judian.goule.store.views.BaseView;
import com.judian.goule.store.zxing.encode.EncodingHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShareActivity extends BaseActivity {
    @BindView(R.id.jinbi)
    TextView mJinbi;
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.promotion)
    TextView mPromotion;
    @BindView(R.id.copy)
    TextView mCopy;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.icon)
    SimpleDraweeView mIcon;
    @BindView(R.id.pic)
    TextView mPic;
    @BindView(R.id.picOdl)
    TextView mPicOdl;
    @BindView(R.id.ll_txt)
    TextView mLlTxt;
    @BindView(R.id.iv2Code)
    ImageView mIv2Code;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.coupon)
    LinearLayout mCoupon;
    @BindView(R.id.shareAll)
    RelativeLayout mShareAll;
    private String text;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.make_money)
    TextView mMakeMoney;

    @BindView(R.id.info)
    TextView info;


    private static String POSITION = "ShareActivity";
    private Bitmap mBitmap;
    private String mUrl;
    private String mTitle;
    private int mW;
    private int mH;
    private File mFile;
    private String filePath;
    private boolean mIsshow = false;
    private int mOption;
    protected Dialog mLoadingDialog;
    private ViewGroup.LayoutParams mLayoutParams;
    private String num;
    private Unbinder bind;


    public static void openMain(Context context, String num_iid) {
        Intent intent = new Intent(context, ShareActivity.class);
        intent.putExtra(POSITION, num_iid);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openMain(Context context, LiveBean.ResultBean.LiveMsgBean item, int option) {
        Intent intent = new Intent(context, ShareActivity.class);
        intent.putExtra(POSITION+2, item);
        intent.putExtra(POSITION+1, option);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        bind = ButterKnife.bind(this);
        setImmersionBar(1);
        title.getPaint().setFakeBoldText(true);
        title.getPaint().setStrokeWidth(3.0f);
        mLoadingDialog = Dialogutils.createLoadingDialog(ShareActivity.this, "");
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setCanceledOnTouchOutside(false);

             int  op=getIntent().getIntExtra(POSITION+1,0);
         switch (op){
             case 0:
                 doBusiness(this);
                 break;
             case  1:
                 liveShare();
                 break;


         }

        mLayoutParams = mImg.getLayoutParams();
        mLayoutParams.width = (int) (MyApplication.width * 0.6);
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mImg.setLayoutParams(mLayoutParams);


    }
     String  mTxt;
    private void liveShare() {

        final LiveBean.ResultBean.LiveMsgBean live= (LiveBean.ResultBean.LiveMsgBean) getIntent().getSerializableExtra(POSITION+2);

        new CdataPresenter(this).getLiveShare(live.getId(), new BaseView<ShearTaoBean>() {
            @Override
            public void result(ShearTaoBean bean) {
                if (bean.getCode() == 200) {
                    Picasso.with(ShareActivity.this).load(bean.getResult().getPictUrl()).into(mTarget);
                    if (bean.getResult().getShopTitle() == null) {
                        text = bean.getResult().getTitle() + "\n"
                                + "原价【" + bean.getResult().getReservePrice() + "元】" +
                                "  券后【" + bean.getResult().getZkPrice() + "元】，"
                                + "\n" + "— — — —↓购买方式↓— — — —" + "\n" +
                                "复制这条信息 " + bean.getResult().getTaoToken() + "\n" + " 打开「手机淘宝」下单";
                    } else {

                        text = bean.getResult().getShopTitle() + "\n" + bean.getResult().getTitle() + "\n"
                                + "原价【" + bean.getResult().getReservePrice() + "元】" +
                                "  券后【" + bean.getResult().getZkPrice() + "元】，"
                                + "\n" + "— — — —↓购买方式↓— — — —" + "\n" +
                                "复制这条信息 " + bean.getResult().getTaoToken() + "\n" + " 打开「手机淘宝」下单";

                    }

                    imageLive(live.getPic(),bean.getResult());
                    mPromotion.setText(text);

                } else {
                                   ToastUtils.toast(ShareActivity.this,bean.getMsg());
                }

            }

            @Override
            public void error() {

            }
        });


    }
    private LoadDialog dialog;
    private void imageLive(String pic,ShearTaoBean.ResultBean result) {

        ViewGroup.LayoutParams layoutParams = mLl.getLayoutParams();
        layoutParams.width=MyApplication.width;
        layoutParams.height= ViewGroup.LayoutParams.WRAP_CONTENT;
        mLl.setLayoutParams(layoutParams);


        mLlTxt.setText(result.getTitle());
        mTitle = result.getTitle();
//         if (result)
        mPicOdl.setText("原价 ¥" + result.getReservePrice() + "");
        mPicOdl.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mPicOdl.getPaint().setAntiAlias(true);
        mPic.setText("¥" + result.getZkPrice() + "");
        if (result.getIs_coupon() == 0) {
            mCoupon.setVisibility(View.GONE);
        }else {
            mCoupon.setVisibility(View.VISIBLE);
            info.setText(result.getCoupon_money());
        }
        mUrl = result.getLink();
        if (mUrl!=null) create2Code(mUrl);

    }


    public void doBusiness(final Context mContext) {


        num = getIntent().getStringExtra(POSITION);
        new SharePresenter(this).shareInfo(num, new BaseView<ShareInfo>() {
            @Override
            public void result(ShareInfo bean) {
                if (bean.getCode() == 200) {
                    if (mJinbi==null)return;
                    mJinbi.setText(bean.getResult().getFanli_money() + "");
                    Picasso.with(mContext).load(bean.getResult().getPictUrl()).into(mTarget);
                    if (bean.getResult().getIs_coupon() == 1) {
                        text = bean.getResult().getShopTitle() + "\n【" + bean.getResult().getTitle() + "】\n" + "原价【" + bean.getResult().getReservePrice() + "元】" + "  券后【" + bean.getResult().getZkPrice() + "元】，"
                                + "\n" + "— — — —↓购买方式↓— — — —" + "\n" + "复制这条信息 " + bean.getResult().getTaoToken() + " 打开「手机淘宝」下单";
                    } else {
                        text = bean.getResult().getShopTitle() + "\n【" + bean.getResult().getTitle() + "】\n" + "  现价只要【" + bean.getResult().getZkPrice() + "元】，"
                                + "\n" + "— — — —↓购买方式↓— — — —" + "\n" + "复制这条信息 " + bean.getResult().getTaoToken() + " 打开「手机淘宝」下单";
                    }
                    imageInfo(bean.getResult());
                    mPromotion.setText(text);
                } else {
                    showToast(bean.getMsg());
                }

            }

            @Override
            public void error() {

            }
        });

        dialog = new LoadDialog(this);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        viewSaveToImage(mLl);

                    }
                }).start();
            }
        });


    }



    boolean  isScc=false , isClike=false;
    private Target mTarget=new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mIcon.setImageBitmap(bitmap);
            mImg.setImageBitmap(bitmap);
            isScc=true;
            goShare();
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    private  void  goShare(){
        if (!isScc){
            ToastUtils.toast(this,"图片处理中");
        }

        if (isScc&&isClike){
            mLl.setVisibility(View.VISIBLE);
            dialog.show();

        }
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.back, R.id.make_money, R.id.copy, R.id.weixin, R.id.wb, R.id.qq, R.id.kj, R.id.haoy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.make_money:

                break;

            case R.id.kj:
                mOption = 6;
                requestWritePermission();


                break;
            case R.id.qq:
                mOption = 5;
                requestWritePermission();
                break;

            case R.id.wb:
                mOption = 8;
                requestWritePermission();
                break;



            case R.id.weixin:
                mOption = 1;
                isClike=true;
                goShare();
                break;
            case R.id.haoy:
                mOption = 0;
                isClike=true;
                goShare();

                break;
            case R.id.copy:
                // 从API11开始android推荐使用android.content.ClipboardManager
                // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(text);
                Token.addKey(text);
                showToast("复制成功");
                break;
        }
    }




    private void imageInfo(ShareInfo.ResultBean result) {

        ViewGroup.LayoutParams layoutParams = mLl.getLayoutParams();
        layoutParams.width=MyApplication.width;
        layoutParams.height= ViewGroup.LayoutParams.WRAP_CONTENT;
        mLl.setLayoutParams(layoutParams);
        mLlTxt.setText(result.getTitle());
        mTitle = result.getTitle();
//         if (result)
        mPicOdl.setText("原价 ¥" + result.getReservePrice() + "");
        mPicOdl.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mPic.setText("¥" + result.getZkPrice() + "");
        if (result.getIs_coupon() == 0) {
            mCoupon.setVisibility(View.GONE);
        }else {
            mCoupon.setVisibility(View.VISIBLE);
            info.setText(result.getCoupon_money());
        }

        mUrl = result.getLink();
        create2Code(mUrl);



    }

    public void viewSaveToImage(View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        // 把一个View转换成图片
        Bitmap cachebmp = loadBitmapFromView(view);

        // 添加水印
        Bitmap bitmap = Bitmap.createBitmap(createWatermarkBitmap(cachebmp,
                ""));

        FileOutputStream fos;
        try {
            // 判断手机设备是否有SD卡
            boolean isHasSDCard = Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
            if (isHasSDCard) {
                // SD卡根目录
                if (!Constants.IMAGE_DIR.exists()) {
                    //通过file的mkdirs()方法创建<span style="color:#FF0000;">目录中包含却不存在</span>的文件夹
                    Constants.IMAGE_DIR.mkdirs();
                }
                mFile = new File(Constants.IMAGE_DIR,  num+".png");

                filePath = mFile.getAbsolutePath();
                fos = new FileOutputStream(mFile);
            } else {
                throw new Exception("创建文件失败!");
            }

            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.destroyDrawingCache();

        if (mOption==5||mOption==6){

            switch (mOption){
                case 5:
                    Logger.e("fffffffff","filePath == "+filePath);
                    QQShareSelf.getInstance(this).onClickShare(filePath,0);
                    break;

                case 6:
//                     ArrayList<String>  list=new ArrayList<>();
//                    list.add(filePath);
//                    QQShareSelf.getInstance(this).shareToQzone(mUrl,mTitle,list);
                    Logger.e("fffffffff","filePath == "+filePath);
                    QQShareSelf.getInstance(this).onClickShare(filePath,1);

                    break;
            }

        }else {
            if (mOption==8){
                ArrayList<Uri> list=new ArrayList<>();
                list.add(Uri.fromFile(mFile));
                WXShare.shareImagesWB(this,list);

            }else {
                WXShare.getInstance(this).shareWX(mOption, bitmap, mW, mH);
            }
        }
//        bitmap.recycle();
           dialog.dismiss();
    }

    private Bitmap loadBitmapFromView(View v) {
        mW = v.getWidth();
        mH = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(mW, mH, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, mW, mH);
        v.draw(c);

        return bmp;
    }

    // 为图片target添加水印
    private Bitmap createWatermarkBitmap(Bitmap target, String str) {
        int w = target.getWidth();
        int h = target.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint p = new Paint();

        // 水印的颜色
        p.setColor(Color.RED);

        // 水印的字体大小
        p.setTextSize(16);

        p.setAntiAlias(true);// 去锯齿

        canvas.drawBitmap(target, 0, 0, p);

        // 在中间位置开始添加水印
        canvas.drawText(str, w / 2, h / 2, p);

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        return bmp;
    }


    /**
     * 生成二维码
     *
     * @param key
     */

    int width = 100;

    private Bitmap create2Code(String key) {

        Bitmap qrCode = null;

        Logger.d("ffff", "dddd key==" + key);
        try {
            qrCode = EncodingHandler.create2Code(key, width);
            mIv2Code.setImageBitmap(qrCode);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return qrCode;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestWritePermission(){

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {

            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},15);
            }else {
                isClike=true;
                goShare();

            }


        }else {
            isClike=true;
            goShare();
        }

    }


    SharePopupwindow  mSharePopupwindow;
    @Override
    protected void onResume() {
        Logger.e("ddddddd","onActivityResult  onResume");
//
//        if (MyApplication.share!=0){
//
//
//            mSharePopupwindow=new SharePopupwindow(ShareActivity.this, MyApplication.share, new SharePopupwindow.OnShareClickListener() {
//                @Override
//                public void goShare() {
//                    WXShare.getInstance(ShareActivity.this).reData();
//                }
//
//                @Override
//                public void colse() {
//
//                }
//            });
//
//            mSharePopupwindow.showAtLocation(mShareAll, 0, 0, 0);
//            MyApplication.share=0;
//        }
//

        super.onResume();
    }
}
