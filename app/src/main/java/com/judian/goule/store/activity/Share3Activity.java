package com.judian.goule.store.activity;

import android.Manifest;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.share.QQShareSelf;
import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.Constants;
import com.example.ccy.ccyui.util.Logger;
import com.facebook.drawee.view.SimpleDraweeView;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.adapter.ViewPager1Adapter;
import com.judian.goule.store.adapter.ZoomOutPageTransformer;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.ShareImgBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.view.LoadDialog;
import com.judian.goule.store.views.BaseView;
import com.judian.goule.store.zxing.encode.EncodingHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Share3Activity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.img)
    SimpleDraweeView imgBg;
    @BindView(R.id.erMa)
    ImageView imgEr;
    @BindView(R.id.area)
    RelativeLayout mrl;

    @BindView(R.id.share_yq_code_tv)
    TextView mYq_code;

    private LoadDialog dialog;

    private BaseQuickAdapter<ShareImgBean.ResultBean.FriendUrlBean, BaseViewHolder> adapter;
    private String key;
    private int mOption;
    List<View> list = new ArrayList<>();
    int option = 0;
    private List<ShareImgBean.ResultBean.FriendUrlBean> friends;
    private String yq_code;
    private MyApplication application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share3);
        ButterKnife.bind(this);
        title.setText("分享海报");
        dialog = new LoadDialog(this);
        setImmersionBar(2);
        application = (MyApplication) getApplication();
        UserInfo userInfo = UserInfoDBUtil.get(this);
        if (userInfo.getResult() != null) {
            yq_code = userInfo.getResult().getYq_code();
        }
        mYq_code.setText(yq_code);

        new CdataPresenter(this).getShare(new BaseView<ShareImgBean>() {
            @Override
            public void result(ShareImgBean bean) {
                if (bean.getCode() == 200) {
                    key = bean.getResult().getMe_url();
                    create2Code(imgEr, key);
                    friends = bean.getResult().getFriend_url();
                    initVp(friends);

                }
            }

            @Override
            public void error() {

            }
        });

    }

    private void initVp(final List<ShareImgBean.ResultBean.FriendUrlBean> friends) {
        List<View> list = new ArrayList<>();
        if (friends.size() > 0) url = friends.get(0).getUrl();
        for (int i = 0; i < friends.size(); i++) {
            View view = LayoutInflater.from(Share3Activity.this).inflate(R.layout.item_poster, null);
            SimpleDraweeView img = view.findViewById(R.id.img);
            ImageView erMa = view.findViewById(R.id.erMa);
            Picasso.with(Share3Activity.this).load(friends.get(i).getUrl()).into(img);
            create2Code(erMa, key);
            list.add(view);
        }

        viewpager.setAdapter(new ViewPager1Adapter(list));
        viewpager.setOffscreenPageLimit(4);
        viewpager.setPageTransformer(true, new ZoomOutPageTransformer());

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                url = friends.get(position).getUrl();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private Target mTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            imgBg.setImageBitmap(bitmap);
            viewSaveToImage(mrl);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    String url = "";

    private void goShare() {


        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Logger.e("aaaaaaaaaaa", "nnnnnnnnnnnnnnnn");
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Picasso.with(Share3Activity.this).load(url).into(mTarget);
                            }
                        });


                    }
                }).start();
            }
        });
        dialog.show();


    }


    private int mW;
    private int mH;
    private File mFile;
    private String filePath;

    public void viewSaveToImage(View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        // 把一个View转换成图片
        Bitmap cachebmp = loadBitmapFromView(view);
        ArrayList<Uri> uris = new ArrayList<>();
        // 添加水印

        if (mOption == 5 || mOption == 6 || mOption == 8) {
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
                    mFile = new File(Constants.IMAGE_DIR, System.currentTimeMillis() + "S.png");

                    filePath = mFile.getAbsolutePath();
                    fos = new FileOutputStream(mFile);
//                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Share3Activity.this, "创建文件失败", Toast.LENGTH_SHORT).show();
                    throw new Exception("创建文件失败!");

                }

                cachebmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();


                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);


                int currentapiVersion = Build.VERSION.SDK_INT;
                if (currentapiVersion > 24) { // 保存uri对应的照片于指定路径
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put(MediaStore.Images.Media.DATA, mFile.getAbsolutePath());
                    Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    Logger.e("qqqqqqqqqq", "getImageUri3 uri ==" + uri);
                    if (uri != null) uris.add(uri);
                } else {
                    uris.add(Uri.fromFile(mFile));
                }
                Uri uri = Uri.fromFile(mFile);
                intent.setData(uri);
                sendBroadcast(intent);
            } catch (Exception e) {
                Logger.e("ddddd", "Exception == " + e);
                e.printStackTrace();
            }
        }


        if (mOption == 5 || mOption == 6 || mOption == 8) {

            switch (mOption) {
                case 5:
//                    Log.i("tiancao","路径："+filePath);
                    QQShareSelf.getInstance(this).onClickShare(filePath, 0);
                    break;

                case 6:
//                     ArrayList<String>  list=new ArrayList<>();
//                    list.add(filePath);
//                    QQShareSelf.getInstance(this).shareToQzone(mUrl,mTitle,list);
                    Logger.e("fffffffff", "filePath == " + filePath);
                    QQShareSelf.getInstance(this).onClickShare(filePath, 1);

                    break;

//                case 8:
//                    WXShare.shareImagesWB(Share3Activity.this, uris);
//
//                    break;


            }

        } else {
            WXShare.getInstance(this).shareWX(mOption, cachebmp, mW, mH);
        }
        dialog.dismiss();
        view.destroyDrawingCache();
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

    Bitmap qrCode;

    private Bitmap create2Code(ImageView iv, String key) {
        Logger.d("ffff", "dddd key==" + key);
        if (qrCode != null) {
            iv.setImageBitmap(qrCode);
            return qrCode;
        }

        try {
            qrCode = EncodingHandler.create2Code(key, 800);
            iv.setImageBitmap(qrCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrCode;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.back, R.id.weixin, R.id.pen, R.id.kj, R.id.qq, R.id.share_copy_yq_code_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.weixin:
                mOption = 1;
                goShare();
                break;
            case R.id.pen:
                mOption = 0;
                goShare();
                break;
//            case R.id.wb:
//                mOption = 8;
//                requestWritePermission();
//                break;
//            case R.id.xiangce:
//                mOption=4;
//                viewSaveToImage(mrl);
//                break;
            case R.id.kj:
                mOption = 6;
                requestWritePermission();
                break;
            case R.id.qq:
                mOption = 5;
                requestWritePermission();
                break;
            case R.id.share_copy_yq_code_rl://复制推荐码
                ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip = ClipData.newPlainText("text", yq_code);
                myClipboard.setPrimaryClip(myClip);
                application.setClipboard(false);
                application.setKefu_weixin(yq_code);
                toast("复制成功");
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestWritePermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 19);
        } else {
            goShare();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        Logger.e("ddddd", "Exception  requestCode == " + requestCode);
        switch (requestCode) {
            case 15:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //premission granted by user
                    goShare();
                } else {
                    goShare();
                }

                break;


            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
