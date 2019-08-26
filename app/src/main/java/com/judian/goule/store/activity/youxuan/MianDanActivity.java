package com.judian.goule.store.activity.youxuan;


import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.auth.third.core.model.Session;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.alibaba.baichuan.trade.biz.AlibcConstants;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.share.QQShareSelf;
import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.Constants;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fanli.ccy.alibaic.AliManage;
import com.google.zxing.WriterException;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.AliWebActivity;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.adapter.MyAdapterUtil;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.CommdityData;
import com.judian.goule.store.bean.CommodityInfoData;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.MiandanInfoData;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.im.BaseActivity;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.presenter.YXDataPresenter;
import com.judian.goule.store.utils.JianCheYYUtils;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.LoadDialog;
import com.judian.goule.store.view.MySharePopupwindow;
import com.judian.goule.store.views.BaseView;
import com.judian.goule.store.zxing.encode.EncodingHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 免单活动
 */
public class MianDanActivity extends BaseActivity implements View.OnClickListener, MySharePopupwindow.MySharePopupwindowListener {


    public static final String GOODSINDO = "goods_id";
    public static final String GOODSINDO_TYPE = "option";

    private RecyclerView mList;
    private YXDataPresenter mPresenter;
    private CdataPresenter presenter;
    private BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> tetris;
    private View headView;//头部


    private MySharePopupwindow mySharePopupwindow;
    private LoadDialog dialog;
    private LinearLayout mShaerView;
    private SimpleDraweeView mShaerImage;
    private ImageView mShaerCode;
    private TextView mShaerCommodityTitle;
    private TextView mShaerCommodityTicketPic;
    private TextView mShaerCommodityPic1;
    private TextView mShaerCommodityPic2;
    private UserInfo userInfo;
    private GoodListBean.ResultBean mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miandan_activity);
        headView = LayoutInflater.from(this).inflate(R.layout.activity_miandan_headview, null);
        setImmersionBar(2);
        mPresenter = new YXDataPresenter(this);
        presenter = new CdataPresenter(this);
        userInfo = UserInfoDBUtil.get(this);
//        Log.i("tiancao", "inten数据" + mGoodsData.toString());
        iniUI();
        getActivityInfo();
        getMiandanCommodityData();
    }

    //获取活动信息
    private void getActivityInfo() {
        mPresenter.getMiandanInfo(new BaseView<MiandanInfoData>() {
            @Override
            public void result(MiandanInfoData bean) {
                if (bean.getCode() == 200) {
                    setUI(bean);
                }
            }

            @Override
            public void error() {

            }
        });
    }

    private void iniUI() {

        ImageView mBack = findViewById(R.id.miandan_activity_back);
        mBack.setOnClickListener(this);
        ImageView mRule = findViewById(R.id.miandan_activity_rule);
        mBack.setOnClickListener(this);


        //商品详情
        mList = (RecyclerView) findViewById(R.id.miandan_list);
        mList.setLayoutManager(new LinearLayoutManager(this));
        tetris = MyAdapterUtil.getMIandanListData(this, new ArrayList<GoodListBean.ResultBean>());
        mList.setAdapter(tetris);
        tetris.addHeaderView(headView);

        //初始化分享弹出框
        mySharePopupwindow = new MySharePopupwindow(this);
        mySharePopupwindow.setmListener(this);
        dialog = new LoadDialog(this);

        //分享截图
        mShaerView = findViewById(R.id.tetris_ll_ccy);
        mShaerImage = findViewById(R.id.tetris_image);
        mShaerCode = findViewById(R.id.tetris_qr_code);
        mShaerCommodityTitle = findViewById(R.id.commodity_title_tv);
        mShaerCommodityTicketPic = findViewById(R.id.tetris_commodity_ticket_pic_tv);
        mShaerCommodityPic1 = findViewById(R.id.tetris_commodity_pic1_tv);
        mShaerCommodityPic2 = findViewById(R.id.tetris_commodity_pic2_tv);

    }

    //获取免单商品列表
    private void getMiandanCommodityData() {
        mPresenter.getMiandanCommodityList(new BaseView<GoodListBean>() {
            @Override
            public void result(GoodListBean bean) {
                if (bean.getCode() == 200) {
//                    Log.i("tiancao", bean.getResult().toString());
                    tetris.setNewData(bean.getResult());
                }
            }

            @Override
            public void error() {

            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.tetris_activity;
    }

    //设置参数
    private void setUI(MiandanInfoData data) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.miandan_activity_back:
                finish();
                break;
            case R.id.miandan_activity_rule:
                finish();
                break;
        }
    }


    //分享点击回调
    @Override
    public void myShareClick(int tab) {
        switch (tab) {
            case 1://微信
                viewSaveToImage(mShaerView, tab);
                break;
            case 2://朋友圈
                viewSaveToImage(mShaerView, tab);
                break;
            case 3://qq
                viewSaveToImage(mShaerView, tab);
                break;
            case 4://空间
                viewSaveToImage(mShaerView, tab);
                break;
        }
    }

    //生成二维码
    private Bitmap create2Code(String key) {
        Bitmap qrCode = null;

        try {
            qrCode = EncodingHandler.create2Code(key, 300);
            mShaerCode.setImageBitmap(qrCode);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return qrCode;
    }

    /**
     * 转换成图片链接
     */
    private int mW;
    private int mH;
    private File mFile;
    private String filePath;

    public void viewSaveToImage(View view, int tab) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        // 把一个View转换成图片
        Bitmap cachebmp = loadBitmapFromView(view);

        ArrayList<Uri> uris = new ArrayList<>();
        // 添加水印


        //申请权限
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }

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
                Toast.makeText(MianDanActivity.this, "创建文件失败", Toast.LENGTH_SHORT).show();
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
        dialog.dismiss();
        switch (tab) {
            case 1:
                WXShare.getInstance(this).shareWX(1, cachebmp, mW, mH);
                break;
            case 2:
                WXShare.getInstance(this).shareWX(0, cachebmp, mW, mH);
                break;
            case 3:
                QQShareSelf.getInstance(this).onClickShare(filePath, 0);
                break;
            case 4:
                QQShareSelf.getInstance(this).onClickShare(filePath, 1);
                break;
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AlibcTradeSDK.destory();
    }
}
