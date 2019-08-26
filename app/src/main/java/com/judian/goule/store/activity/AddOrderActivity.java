package com.judian.goule.store.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.SelectPhotoPopupwindow;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.TestData;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.SelftaoDialog;
import com.judian.goule.store.views.BaseView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddOrderActivity extends TakePhotoActivity {

    @BindView(R.id.title)
    EditText mTitle;
    @BindView(R.id.context)
    EditText mContext;
    @BindView(R.id.img1)
    ImageView mImg1;
    @BindView(R.id.img2)
    ImageView mImg2;
    @BindView(R.id.img3)
    ImageView mImg3;
    @BindView(R.id.img4)
    ImageView mImg4;
    @BindView(R.id.img5)
    ImageView mImg5;
    @BindView(R.id.activity_add)
    LinearLayout activityAdd;


    private SelectPhotoPopupwindow photoPopupwindow;
    private CdataPresenter mPresenter;
    private File[] mFiles=new File[5];
    private List<ImageView> mList;
    private String mOrderId;
    private Unbinder bind;

    @Override
    protected void onResume() {
        super.onResume();
        String key = TestData.getClipboardText(this);
        if (key != null && !key.equals("")) {
            if ( Token.isBCopy(key))
                return;
            SelftaoDialog dialog = new SelftaoDialog(this, key);
            dialog.show();
        }
        MobclickAgent.onResume(this);
    }
    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        bind = ButterKnife.bind(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.2f)
                .init();
        WindowManager wm1 = getWindowManager();
        MyApplication.width = wm1.getDefaultDisplay().getWidth();
        MyApplication.height = wm1.getDefaultDisplay().getHeight();
        mList = new ArrayList<>();
        mList.add(mImg1);
        mList.add(mImg2);
        mList.add(mImg3);
        mList.add(mImg4);
        mList.add(mImg5);
        mPresenter = new CdataPresenter(this);
        mOrderId = getIntent().getStringExtra("ORDERID");
        photo();
        init();



        ViewGroup.LayoutParams layoutParams = mImg1.getLayoutParams();
        layoutParams.width= (int) (MyApplication.width/4.4);
        layoutParams.height=  layoutParams.width;
        ViewGroup.LayoutParams layoutParams1 = mImg5.getLayoutParams();
        layoutParams1.width = (int) (MyApplication.width/4.2);
        layoutParams1.height = layoutParams1.width;
        mImg5.setLayoutParams(layoutParams1);
        mImg5.setLayoutParams(layoutParams1);
        for (int i = 0; i < mList.size()-1; i++) {

            mList.get(i).setLayoutParams(layoutParams);

        }




    }


    private void photo() {
        photoPopupwindow = new SelectPhotoPopupwindow(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Logger.d("ffffffffff", "onClick" + v.getId());
                switch (v.getId()) {


                    //拍照
                    case R.id.btn_take_photo:

                        v.setId(R.id.btnPickByTake);
                        onViewClicked(v);
                        TUPIAN = XIANGJI;
                        photoPopupwindow.dismiss();
                        photo();

                        break;
                    //相册
                    case R.id.btn_pick_photo:
                        TUPIAN = XIANGCE;
                        photoPopupwindow.dismiss();
                        photo();
                        v.setId(R.id.btnPickBySelect);
                        onViewClicked(v);

                        break;

                }
            }
        }, SelectPhotoPopupwindow.PHOTO);

        photoPopupwindow.dismiss();
    }


    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        try {
            showImg(result.getImages());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showImg(ArrayList<TImage> images) throws FileNotFoundException {


        switch (TUPIAN) {

            case 1:
                mFiles[option]=new File(images.get(0).getCompressPath());
                Picasso.with(this).load(mFiles[option]).into(mList.get(option));

                break;
            case 2:
                int s=0;
               if (option+images.size()<=5){
                   s=images.size();
               }else {
                   s=5-option;
               }

                for (int i = 0; i < s; i++) {

                    mFiles[i+option]=new File(images.get(i).getCompressPath());
                    Picasso.with(this).load(mFiles[i+option]).into(mList.get(i+option));

                }
                break;


        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }


    @Override
    public void takeCancel() {

        super.takeCancel();
    }

    private RadioGroup rgCrop, rgCompress, rgFrom, rgCropSize, rgCropTool, rgShowProgressBar, rgPickTool, rgCompressTool, rgCorrectTool, rgRawFile;

    private EditText etCropHeight, etCropWidth, etLimit, etSize, etHeightPx, etWidthPx;

    private void init() {
        rgCrop = (RadioGroup) findViewById(R.id.rgCrop);
        rgCompress = (RadioGroup) findViewById(R.id.rgCompress);
        rgCompressTool = (RadioGroup) findViewById(R.id.rgCompressTool);
        rgCropSize = (RadioGroup) findViewById(R.id.rgCropSize);
        rgFrom = (RadioGroup) findViewById(R.id.rgFrom);
        rgPickTool = (RadioGroup) findViewById(R.id.rgPickTool);
        rgRawFile = (RadioGroup) findViewById(R.id.rgRawFile);
        rgCorrectTool = (RadioGroup) findViewById(R.id.rgCorrectTool);
        rgShowProgressBar = (RadioGroup) findViewById(R.id.rgShowProgressBar);
        rgCropTool = (RadioGroup) findViewById(R.id.rgCropTool);
        etCropHeight = (EditText) findViewById(R.id.etCropHeight);
        etCropWidth = (EditText) findViewById(R.id.etCropWidth);
        etLimit = (EditText) findViewById(R.id.etLimit);
        etSize = (EditText) findViewById(R.id.etSize);
        etHeightPx = (EditText) findViewById(R.id.etHeightPx);
        etWidthPx = (EditText) findViewById(R.id.etWidthPx);

    }

    private int TUPIAN = 0;
    private int XIANGJI = 1;
    private int XIANGCE = 2;


    @OnClick({R.id.back, R.id.commit, R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btnPickBySelect:
                configCompress(getTakePhoto());
                configTakePhotoOption(getTakePhoto());
                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                int limit = Integer.parseInt("5");
                if (limit > 1) {
                    getTakePhoto().onPickMultiple(limit);
                    return;
                }
                if (rgFrom.getCheckedRadioButtonId() == R.id.rbFile) {
                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                        getTakePhoto().onPickFromDocumentsWithCrop(imageUri, getCropOptions());
                    } else {
                        getTakePhoto().onPickFromDocuments();
                    }
                    return;
                } else {
                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                        getTakePhoto().onPickFromGalleryWithCrop(imageUri, getCropOptions());
                    } else {
                        getTakePhoto().onPickFromGallery();
                    }
                }
                break;
            case R.id.btnPickByTake:

                configCompress(getTakePhoto());
                configTakePhotoOption(getTakePhoto());
                File file1 = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file1.getParentFile().exists()) file1.getParentFile().mkdirs();
                Uri imageUri1 = Uri.fromFile(file1);

                getTakePhoto().onPickFromCapture(imageUri1);
                break;


            case R.id.back:
                finish();
                break;
            case R.id.commit:
                commit();
                break;
            case R.id.img1:
                option = 0;
                photoPopupwindow.showAtLocation(activityAdd, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.img2:
                option = 1;
                photoPopupwindow.showAtLocation(activityAdd, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.img3:
                option = 2;
                photoPopupwindow.showAtLocation(activityAdd, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.img4:
                option = 3;
                photoPopupwindow.showAtLocation(activityAdd, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.img5:
                option = 4;
                photoPopupwindow.showAtLocation(activityAdd, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }

    }

    int option = 0;


    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        if (rgPickTool.getCheckedRadioButtonId() == R.id.rbPickWithOwn) {
            builder.setWithOwnGallery(true);
        }
        if (rgCorrectTool.getCheckedRadioButtonId() == R.id.rbCorrectYes) {
            builder.setCorrectImage(true);
        }
        takePhoto.setTakePhotoOptions(builder.create());

    }


    @Override
    public void finish() {
       setResult(26);
        super.finish();
    }

    private void configCompress(TakePhoto takePhoto) {
        if (rgCompress.getCheckedRadioButtonId() != R.id.rbCompressYes) {
            takePhoto.onEnableCompress(null, false);
            return;
        }
        int maxSize = Integer.parseInt(etSize.getText().toString());
        int width = Integer.parseInt(etCropWidth.getText().toString());
        int height = Integer.parseInt(etHeightPx.getText().toString());
        boolean showProgressBar = rgShowProgressBar.getCheckedRadioButtonId() == R.id.rbShowYes ? true : false;
        boolean enableRawFile = rgRawFile.getCheckedRadioButtonId() == R.id.rbRawYes ? true : false;
        CompressConfig config;
        if (rgCompressTool.getCheckedRadioButtonId() == R.id.rbCompressWithOwn) {
            config = new CompressConfig.Builder()
                    .setMaxSize(maxSize)
                    .setMaxPixel(width >= height ? width : height)
                    .enableReserveRaw(enableRawFile)
                    .create();
        } else {
            LubanOptions option = new LubanOptions.Builder()
                    .setMaxHeight(height)
                    .setMaxWidth(width)
                    .setMaxSize(maxSize)
                    .create();
            config = CompressConfig.ofLuban(option);
            config.enableReserveRaw(enableRawFile);
        }
        takePhoto.onEnableCompress(config, showProgressBar);

    }

    private CropOptions getCropOptions() {

        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(300).setOutputY(300);
        builder.setWithOwnCrop(true);
        return builder.create();
    }

    List<Img> listImg;
    private void commit() {
        String context = mContext.getText().toString().trim();
        String title = mTitle.getText().toString().trim();
          int ss=0;
        for (int i = 0; i < mFiles.length; i++) {
        if (mFiles[i]!=null){
            ++ss;
        }}
         File[]  files=new File[ss];
        ss=0;
        listImg=new ArrayList<>();
        for (int i = 0; i < mFiles.length; i++) {
            if (mFiles[i]!=null){
                files[ss]=mFiles[i];
                ++ss;
                Picasso.with(this).load(mFiles[i]).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        listImg.add(new Img(bitmap.getHeight(),bitmap.getWidth()));

                    }
                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }}



        if (context.equals("")) {
            ToastUtils.toast(AddOrderActivity.this, "内容不能为空");

        } else if (context.length() < 20) {
            ToastUtils.toast(AddOrderActivity.this, "内容不能少于20字");

        } else if (title.equals("")) {
            ToastUtils.toast(AddOrderActivity.this, "标题不能为空");

        } else if (mFiles == null || files.length == 0) {
            ToastUtils.toast(AddOrderActivity.this, "请至少添加一张图片");


        } else {
            String   str= ""+new Gson().toJson(listImg)+"";
            try {
                mPresenter.commitOrder(files, title, context, mOrderId, str,new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        ToastUtils.toast(AddOrderActivity.this, bean.getMsg());
                        if (bean.getCode()==200){
                            finish();
                        }
                    }

                    @Override
                    public void error() {

                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }


    }

    class Img {
        int  height;

        public Img(int height, int width) {
            this.height = height;
            this.width = width;
        }

        int  width;



    }
}
