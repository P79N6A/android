package com.judian.goule.store.activity.my;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.adapter.BaseRecyclerAdapter;
import com.example.ccy.ccyui.share.QQShareSelf;
import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.Constants;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.WriterException;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.MakeActivity;
import com.judian.goule.store.activity.SetingActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.adapter.MyAdapterUtil;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.CateBean;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.presenter.YXDataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.LoadDialog;
import com.judian.goule.store.view.LoadPopupwindow;
import com.judian.goule.store.view.MySharePopupwindow;
import com.judian.goule.store.views.BaseView;
import com.judian.goule.store.zxing.encode.EncodingHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//我的收藏
public class MyCollectActivity extends BaseActivity implements MySharePopupwindow.MySharePopupwindowListener {

    @BindView(R.id.num)
    TextView num;

    @BindView(R.id.makeAll)
    RelativeLayout makeAll;

    @BindView(R.id.ll)
    LinearLayout ll;

    @BindView(R.id.ccy)
    LinearLayout ccy;

    @BindView(R.id.recy)
    RecyclerView recy;


    @BindViews({R.id.tv1, R.id.tv2, R.id.tv3})
    List<TextView> tvs;

    @BindViews({R.id.img1, R.id.img2, R.id.img3})
    List<ImageView> imgs;
    @BindView(R.id.img)
    ImageView img;


    @BindView(R.id.my_collect_check_all_check_box)
    CheckBox mCheck_box;//全选

    private boolean isCheck_All = false;//是否全选

    private long lastClickTime = 0;
    public static final int MIN_CLICK_DELAY_TIME = 2000;


    private BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> makeData;
    private YXDataPresenter presenter;
    HashMap<String, String> params = new HashMap<>();

    List<GoodListBean.ResultBean> selList = new ArrayList<>();
    private BaseRecyclerAdapter<GoodListBean.ResultBean> adapterR;

    private Uri parse;
    private int size;
    private LoadDialog dialog;
    private String url;

    private Unbinder bind;
    private int iSc = 0;
    private View mFootView;//尾部
    private MySharePopupwindow mySharePopupwindow;
    private int mShareClickType = 0;//分享点击点击类型

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        mFootView = LayoutInflater.from(this).inflate(R.layout.my_collect_footview, null);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        recy.setLayoutManager(new LinearLayoutManager(this));
        presenter = new YXDataPresenter(this);
        dialog = new LoadDialog(MyCollectActivity.this);
        ViewGroup.LayoutParams layoutParams = mIcon.getLayoutParams();
        layoutParams.width = (int) (MyApplication.width);
        layoutParams.height = (int) (MyApplication.width);
        mIcon.setLayoutParams(layoutParams);

        //初始化分享弹出框
        mySharePopupwindow = new MySharePopupwindow(this);
        mySharePopupwindow.setmListener(this);

        //获取适配器
        makeData = MyAdapterUtil.getCollectData(this, new MyAdapterUtil.CollectLintener() {
            @Override
            public void add(GoodListBean.ResultBean hotBean) {
                selList.add(hotBean);
                setNum();
                imageLive3(hotBean);

                //判断数据是否是全选了
                /*boolean ss = false;
                List<GoodListBean.ResultBean> data = makeData.getData();
                for (int i = 0; i < data.size(); i++) {
                    if (!data.get(i).isSel()) {
                        ss = true;
                    }
                }
                if(ss){
                    mCheck_box.setChecked(false);
                    isCheck_All = false;
                }else {
                    mCheck_box.setChecked(true);
                    isCheck_All = true;
                }*/


            }

            @Override
            public void remov(GoodListBean.ResultBean hotBean) {
                selList.remove(hotBean);
                setNum();

                mCheck_box.setChecked(false);
                isCheck_All = false;

                //判断数据是否是全选了
                /*boolean ss = false;
                List<GoodListBean.ResultBean> data = makeData.getData();
                for (int i = 0; i < data.size(); i++) {
                    if (!data.get(i).isSel()) {
                        ss = true;
                    }
                }
                if(ss){
                    mCheck_box.setChecked(false);
                    isCheck_All = false;
                }else {
                    mCheck_box.setChecked(true);
                    isCheck_All = true;
                }*/
            }

            @Override
            public void share(final GoodListBean.ResultBean hotBean) {
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageLive2(hotBean);
                            }
                        });

                    }
                });
                dialog.show();


            }
        });

        recy.setAdapter(makeData);

        makeData.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                params.put("page", page + "");
                presenter.getCollectList(params, view);

            }
        });

        shuxin();
        setNum();
    }


    void getUrl() {
        String ids = "";

        for (int i = 0; i < selList.size(); i++) {
            ids = ids + selList.get(i).getNum_iid() + ",";
        }
        ids = ids.substring(0, ids.length() - 1);

        presenter.getMakeUrl(ids, new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                if (bean.getCode() == 200) {
                    url = bean.getResult().getUrl();
                    switch (mOption) {
                        case 0:
                            WXShare.getInstance(MyCollectActivity.this).shareWX(1, url);
                            break;
                        case 1:
                            WXShare.getInstance(MyCollectActivity.this).shareWX(0, url);
                            break;
                        case 2:
                            if (QQShareSelf.checkApkExist(MyCollectActivity.this, "com.tencent.mobileqq")) {
                                QQShareSelf.getInstance(MyCollectActivity.this).onClickShare(url, selList.get(0).getPict_url());
                            } else {
                                Toast.makeText(MyCollectActivity.this, "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 3:
                            if (QQShareSelf.checkApkExist(MyCollectActivity.this, "com.tencent.mobileqq")) {
                                QQShareSelf.getInstance(MyCollectActivity.this).onClickShare(url, selList.get(0).getPict_url());
                            } else {
                                Toast.makeText(MyCollectActivity.this, "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }

                }
            }

            @Override
            public void error() {

            }
        });


    }

    private Bitmap bitmap1;
    boolean isScc = false, isClike = false;
    private Target mTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            bitmap1 = bitmap;
            isScc = true;
            goShare();

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    private void goShare() {
        if (!isScc) {
            ToastUtils.toast(this, "图片处理中...");
        }

        if (isScc && isClike) {

            isClike = false;
            if (bitmap1 != null)
                WXShare.getInstance(MyCollectActivity.this).shareWX(1, "精品优惠券集合", url, "我为小伙伴们特地挑选的淘宝优惠券，在这里，省到底！", bitmap1);
            else
                ToastUtils.toast(this, "图片加载失败...");

        }
    }

    int mOption = 0;

    void setNum() {
        num.setText(selList.size() + "");
    }

    //获取收藏列表
    int page = 1;
    BaseView<GoodListBean> view = new BaseView<GoodListBean>() {
        @Override
        public void result(GoodListBean bean) {
            if (bean.getCode() == 200) {
                ccy.setVisibility(View.VISIBLE);
                if (page == 1) {
                    makeData.setNewData(null);
                    makeData.addData(bean.getResult());
                } else {
                    makeData.addData(bean.getResult());
                }

                //判断个数
                if (bean.getResult().size() < 10) {
                    makeData.addFooterView(mFootView);
                    makeData.loadMoreEnd();
                } else {
                    makeData.loadMoreComplete();
                }


            } else {
                makeData.loadMoreEnd();
                ccy.setVisibility(View.GONE);
                ToastUtils.toast(MyCollectActivity.this, bean.getMsg());
            }


        }

        @Override
        public void error() {
            makeData.loadMoreFail();
        }
    };

    //初始化显示
    private void shuxin() {
        selList.clear();
        bitList.clear();
        setNum();
        makeData.setNewData(null);
        mCheck_box.setChecked(false);
        isCheck_All = false;

        page = 1;
        params.put("page", page + "");
        presenter.getCollectList(params, view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.my_collect_back, R.id.my_collect_delete, R.id.share1, R.id.share2, R.id.my_collect_check_all_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_collect_back:
                finish();
                break;
            case R.id.my_collect_delete://删除
                String unm_iid = "";
                if (selList.size() != 0) {
                    for (int i = 0; i < selList.size(); i++) {
                        unm_iid = unm_iid + "," + selList.get(i).getNum_iid();
                    }
                    deleteGoods(unm_iid);
                } else {
                    ToastUtils.toast(MyCollectActivity.this, "请选择商品");
                }
                break;
            case R.id.share1:
                mShareClickType = 1;
                list = new ArrayList<>();
                if (selList.size() != 0) {
                    mySharePopupwindow.showAtLocation(makeAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    ToastUtils.toast(MyCollectActivity.this, "请选择商品");
                }
                break;
            case R.id.share2:
                mShareClickType = 2;
                if (selList.size() != 0) {
                    mySharePopupwindow.showAtLocation(makeAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    ToastUtils.toast(MyCollectActivity.this, "请选择商品");
                }
                break;
            case R.id.my_collect_check_all_rl://全选
                List<GoodListBean.ResultBean> data = makeData.getData();//获取到适配所有的数据
                if (data.size() != 0) {
                    if (!isCheck_All) {
                        mCheck_box.setChecked(true);
                        isCheck_All = true;
                    } else {
                        mCheck_box.setChecked(false);
                        isCheck_All = false;
                        selList.clear();
                    }

                    for (int i = 0; i < data.size(); i++) {
                        if (!isCheck_All) {
                            data.get(i).setSel(false);
                        } else {
                            data.get(i).setSel(true);
                            selList.add(data.get(i));
                        }
                    }
                    makeData.notifyDataSetChanged();
                } else {
                    toast("您没有收藏的商品");
                }
                break;
        }
    }

    /**
     * 删除收藏商品
     */
    private void deleteGoods(String unm_iid) {
        presenter.deleteCollectList(unm_iid, new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                if (bean.getCode() == 200) {
                    shuxin();
                }
            }

            @Override
            public void error() {

            }
        });
    }

    ArrayList<Uri> list = new ArrayList();
    ArrayList<String> urlStringlist = new ArrayList();

    private void all() {
        list = new ArrayList<>();
        size = selList.size();
        if (size == 0) {
            ToastUtils.toast(this, "您还未选择商品");
        } else {
//            ToastUtils.toast(this, "图片处理中");


            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    createImg();
                                }
                            });

                        }
                    }).start();


                }
            });

            if (bitList.size() != selList.size()) {
                bitList.clear();

                for (int i = 0; i < selList.size(); i++) {
                    imageLive3(selList.get(i));
                }

            }
            dialog.show();


        }
    }


    void createImg() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < bitList.size(); i++) {
                    list.add(getImageUri3(bitList.get(i)));
                    urlStringlist.add(getImageUri4(bitList.get(i)));
                }
                bitList.clear();
                dialog.dismiss();
                switch (mOption) {
                    case 0:
                        WXShare.shareImages(MyCollectActivity.this, list);
                        Log.i("tiancao", "createImg()000");
                        break;
                    case 1:
                        WXShare.shareweipyqSomeImg(MyCollectActivity.this, list);
                        Log.i("tiancao", "createImg()111");
                        break;
                    case 2:
                        WXShare.shareImagesQQ(MyCollectActivity.this, list);
                        break;
                    case 3:
//                        QQShareSelf.getInstance(MyCollectActivity.this).shareToQzone("https://www.baidu.com/", urlStringlist);
                        QQShareSelf.getInstance(MyCollectActivity.this).onClickShare(urlStringlist.get(0), 1);
                        Log.i("tiancao", "数据" + urlStringlist.toString());
                        break;
                }


            }
        }).start();


    }


    void setImg(int option, int rotation) {
        imgs.get(option).setPivotX(imgs.get(option).getWidth() / 2);
        imgs.get(option).setPivotY(imgs.get(option).getHeight() / 2);
        imgs.get(option).setRotation(rotation);
    }


    public Uri getImageUri3(MyCollectActivity.ViewBit viewBit) {
        Logger.e("qqqqqqqqqq", " viewSaveToImage3 " + viewBit.name);
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
                mFile = new File(Constants.IMAGE_DIR, viewBit.name + ".png");

                fos = new FileOutputStream(mFile);
            } else {
                throw new Exception("创建文件失败!");
            }

            viewBit.bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.e("qqqqqqqqqq", " getImageUri3 Uri " + Uri.fromFile(mFile));

//        Uri uri = MediaStore.Audio.Media.getContentUriForPath(sdfile.getAbsolutePath());
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion > 24) { // 保存uri对应的照片于指定路径
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, mFile.getAbsolutePath());
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            Logger.e("qqqqqqqqqq", "getImageUri3 uri ==" + uri);
            if (uri != null) return uri;
        }
        return Uri.fromFile(mFile);

    }

    //拿到分享的文件路径
    public String getImageUri4(MyCollectActivity.ViewBit viewBit) {
        Logger.e("qqqqqqqqqq", " viewSaveToImage3 " + viewBit.name);
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
                mFile = new File(Constants.IMAGE_DIR, viewBit.name + ".png");
                fos = new FileOutputStream(mFile);
            } else {
                throw new Exception("创建文件失败!");
            }

            viewBit.bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mFile.getAbsolutePath();
    }

    public void viewSaveToImage2(View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);
        final Bitmap cachebmp = loadBitmapFromView(view);
        view.destroyDrawingCache();
        Logger.e("vvvvvvv", "dddddd  viewSaveToImage2()  5");
        dialog.dismiss();
        iSc = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                WXShare.getInstance(MyCollectActivity.this).shareWX(1, cachebmp, mW, mH);
            }
        }).start();


    }


    public void viewSaveToImage3(String name, View view) {
        Logger.e("vvvvvvv", " viewSaveToImage3 " + name);
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);
        Bitmap cachebmp = loadBitmapFromView(view);
        view.destroyDrawingCache();

        bitList.add(new MyCollectActivity.ViewBit(System.currentTimeMillis() + bitList.size() + "", cachebmp));
//        cachebmp.recycle();
    }


    private int mW;
    private int mH;
    private File mFile;

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


    boolean isOk = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestWritePermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 15);

        } else {
            isOk = true;
            all();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 15:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //premission granted by user
                    all();
                } else {
//                    all();
                    ToastUtils.toast(MyCollectActivity.this, "未获取权限，不能群发图片");
                }

                break;


            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @BindView(R.id.pic)
    TextView mPic;
    @BindView(R.id.picOdl)
    TextView mPicOdl;


    @BindView(R.id.ll_txt)
    TextView mLlTxt;

    @BindView(R.id.info)
    TextView info;

    @BindView(R.id.coupon)
    LinearLayout mCoupon;
    @BindView(R.id.iv2Code)
    ImageView mIv2Code;
    @BindView(R.id.icon)
    SimpleDraweeView mIcon;
//    @BindView(R.id.img6)
//    SimpleDraweeView img6;

    List<MyCollectActivity.ViewBit> bitList = new ArrayList<>();

    private void imageLive2(final GoodListBean.ResultBean result) {
        Logger.e("vvvvvvv", "dddddd  imageLive2()");
        mLlTxt.setText(result.getTitle());
        mPicOdl.setText("原价 ¥" + result.getReserve_price() + "");
        mPicOdl.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mPicOdl.getPaint().setAntiAlias(true);
        mPic.setText("¥" + result.getPrice() + "");
        if (result.getHave_coupon() == 0) {
            mCoupon.setVisibility(View.GONE);
        } else {
            mCoupon.setVisibility(View.VISIBLE);
            info.setText(result.getCoupon_money());
        }
        create2Code(result.getUrl());//获取二维码
        iSc = 1;
        Picasso.with(this).load(result.getPict_url()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (iSc == 1) {
                    mIcon.setImageBitmap(bitmap);
                    iSc = 2;
                    viewSaveToImage2(ccy);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        AdapterUtil.setImgMake(mIcon, result.getPict_url());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    if (iSc == 1) {
                        iSc = 3;
                        viewSaveToImage2(ccy);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void imageLive3(final GoodListBean.ResultBean result) {
        Logger.e("vvvvvvv", " imageLive3   5 " + result.getNum_iid());
        mLlTxt.setText(result.getTitle());
        //         if (result)
        AdapterUtil.setImgMake(mIcon, result.getPict_url());
        mPicOdl.setText("原价 ¥" + result.getReserve_price() + "");
        mPicOdl.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mPicOdl.getPaint().setAntiAlias(true);
        mPic.setText("¥" + result.getPrice() + "");
        if (result.getHave_coupon() == 0) {
            mCoupon.setVisibility(View.GONE);
        } else {
            mCoupon.setVisibility(View.VISIBLE);
            info.setText(result.getCoupon_money());
        }
        create2Code(result.getUrl());
        Picasso.with(this).load(result.getPict_url()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                mIcon.setImageBitmap(bitmap);
                viewSaveToImage3(result.getNum_iid(), ccy);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


    }


    @Override
    protected void onPause() {
        try {
            for (int i = 0; i < bitList.size(); i++) {
                bitList.get(i).bitmap.recycle();
                bitList.get(i).bitmap = null;

            }

        } catch (Exception e) {

        }

        super.onPause();
    }

    private Bitmap create2Code(String key) {
        Bitmap qrCode = null;

        try {
            qrCode = EncodingHandler.create2Code(key, 300);
            mIv2Code.setImageBitmap(qrCode);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return qrCode;
    }

    //分享弹出框的回调
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void myShareClick(int tab) {
        switch (tab) {
            case 1://微信
                if (mShareClickType == 1) {
                    mOption = 0;
                    size = selList.size();
                    if (size == 0) {
                        ToastUtils.toast(MyCollectActivity.this, "您还未选择商品");
                    } else if (size < 9) {
                        requestWritePermission();
                    } else {
                        ToastUtils.toast(MyCollectActivity.this, "一次最多只能分享9张图片");
                    }
                } else if (mShareClickType == 2) {
                    mOption = 0;
                    getUrl();
                }
                break;
            case 2://朋友圈
                if (mShareClickType == 1) {
                    mOption = 1;
                    size = selList.size();
                    if (size == 0) {
                        ToastUtils.toast(MyCollectActivity.this, "您还未选择商品");
                    } else if (size < 9) {
                        requestWritePermission();
                    } else {
                        ToastUtils.toast(MyCollectActivity.this, "一次最多只能分享9张图片");
                    }
                } else if (mShareClickType == 2) {
                    mOption = 1;
                    getUrl();
                }
                break;
            case 3://qq
                if (mShareClickType == 1) {
                    mOption = 2;
                    size = selList.size();
                    if (size == 0) {
                        ToastUtils.toast(MyCollectActivity.this, "您还未选择商品");
                    } else {
                        requestWritePermission();
                    }
                } else if (mShareClickType == 2) {
                    mOption = 2;
                    getUrl();
                }
                break;
            case 4://空间
                if (mShareClickType == 1) {
                    mOption = 3;
                    size = selList.size();
                    if (size == 0) {
                        ToastUtils.toast(MyCollectActivity.this, "您还未选择商品");
                    } else {
                        requestWritePermission();
                    }
                } else if (mShareClickType == 2) {
                    mOption = 3;
                    getUrl();
                }
                break;
        }
    }


    class ViewBit {
        String name;
        Bitmap bitmap;

        public ViewBit(String name, Bitmap bitmap) {
            this.name = name;
            this.bitmap = bitmap;
        }
    }


}
