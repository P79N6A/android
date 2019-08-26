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
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.judian.goule.store.activity.AllGoodsActivity;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.activity.Share3Activity;
import com.judian.goule.store.activity.my.MyCollectActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.CommdityData;
import com.judian.goule.store.bean.CommodityInfoData;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.MsBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.im.BaseActivity;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.presenter.YXDataPresenter;
import com.judian.goule.store.utils.FrescoUtils;
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
 * 商品详情界面
 */
public class TetrisActivity extends BaseActivity implements View.OnClickListener, MySharePopupwindow.MySharePopupwindowListener {


    public static final String GOODSINDO = "goods_id";
    public static final String GOODSINDO_TYPE = "option";

    private RecyclerView mList;
    private YXDataPresenter mPresenter;
    private CdataPresenter presenter;
    private BaseQuickAdapter<String, BaseViewHolder> tetris;
    private View headView;//头部


    private GoodListBean.ResultBean mGoodsData;//商品的详情
    private int option;
    private List<String> imageurl = new ArrayList<>();

    private RelativeLayout mRl_All;
    private ConvenientBanner mBanner;

    private TextView mPrice;

    private TextView mTianmao_and_taobao_price;

    private TextView mYimai;

    private ImageView mCommodity_type;

    private TextView mCommodity_title;

    private TextView mLinQuan;

    private RelativeLayout discount_ll_layout;

    private TextView mShare1_text;
    private TextView mShare2_text;

    private TextView mTime;
    private ImageView mCollect_iv;
    private TextView mCollect_tv;

    private boolean isCollect = false;//是否是收藏商品

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

    private RelativeLayout mTetril_fenxiang_zhuan_rl;
    private RelativeLayout mTetril_shengji_zhuan_rl;
    private RelativeLayout mTetril_miandan_butie_rl;

    public static void openMain(Context context, GoodListBean.ResultBean bean, int option) {
        Intent intent = new Intent(context, TetrisActivity.class);
        intent.putExtra(GOODSINDO, bean);
        intent.putExtra(GOODSINDO_TYPE, option);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tetris_activity);
        headView = LayoutInflater.from(this).inflate(R.layout.activity_commodity_details, null);
        setImmersionBar(2);
        mGoodsData = (GoodListBean.ResultBean) getIntent().getSerializableExtra(GOODSINDO);
        option = getIntent().getIntExtra(GOODSINDO_TYPE, 0);
        mPresenter = new YXDataPresenter(this);
        presenter = new CdataPresenter(this);
        userInfo = UserInfoDBUtil.get(this);
//        Log.i("tiancao", "inten数据" + mGoodsData.toString());
        iniUI();
        mStartAnimation();//开启动画
        getCommodityInfo(mGoodsData.getNum_iid());
        getCommodityData();
    }

    private void mStartAnimation() {
        final TranslateAnimation animation = new TranslateAnimation(300, 0,0, 0);
        animation.setDuration(2000);//设置动画持续时间
        mTetril_fenxiang_zhuan_rl.setAnimation(animation);
        mTetril_shengji_zhuan_rl.setAnimation(animation);
        mTetril_miandan_butie_rl.setAnimation(animation);
        animation.startNow();
    }

    //获取商品信息
    private void getCommodityInfo(String num_iid) {
        mPresenter.getCommodityInfo(num_iid, new BaseView<CommodityInfoData>() {
            @Override
            public void result(CommodityInfoData bean) {
                if (bean.getCode() == 200) {
                    setUI(bean);
//                    Log.i("tiancao", "商品信息" + bean.toString());
                }
            }

            @Override
            public void error() {

            }
        });
    }

    private void iniUI() {
        mRl_All = findViewById(R.id.tetris_rll_all);
        RelativeLayout mCollect_rl = findViewById(R.id.commodity_details_collect_rl);
        mCollect_rl.setOnClickListener(this);
        RelativeLayout mShare1 = findViewById(R.id.commodity_details_share1);
        mShare1.setOnClickListener(this);
        mShare1_text = findViewById(R.id.commodity_details_share1_tv);
        RelativeLayout mShare2 = findViewById(R.id.commodity_details_share2);
        mShare2.setOnClickListener(this);
        mShare2_text = findViewById(R.id.commodity_details_share2_tv);
        ImageView mBack = findViewById(R.id.commodity_details_back);
        mBack.setOnClickListener(this);
        mCollect_iv = findViewById(R.id.commodity_details_collect_iv);
        mCollect_tv = findViewById(R.id.commodity_details_collect_tv);

        mTetril_fenxiang_zhuan_rl = findViewById(R.id.tetril_fenxiang_zhuan_rl);
        mTetril_shengji_zhuan_rl = findViewById(R.id.tetril_shengji_zhuan_rl);
        mTetril_miandan_butie_rl = findViewById(R.id.tetril_miandan_butie_rl);


        mBanner = headView.findViewById(R.id.commodity_details_banner);
        mPrice = headView.findViewById(R.id.commodity_price);
        mTianmao_and_taobao_price = headView.findViewById(R.id.commodity_tianmao_and_taoboa_price);
        mYimai = headView.findViewById(R.id.commodity_yimai);
        mCommodity_type = headView.findViewById(R.id.commodity_type);
        mCommodity_title = headView.findViewById(R.id.commodity_title);
        mLinQuan = headView.findViewById(R.id.commodity_details_linquan_tv);
        mTime = headView.findViewById(R.id.commodity_details_time_tv);
        RelativeLayout mShare3 = headView.findViewById(R.id.commodity_details_share3);
        mShare3.setOnClickListener(this);
        RelativeLayout immediately_get = headView.findViewById(R.id.commodity_details_immediately_get);
        immediately_get.setOnClickListener(this);
        discount_ll_layout = headView.findViewById(R.id.discount_ll_layout);


        //商品详情
        mList = (RecyclerView) findViewById(R.id.tetris_list);
        tetris = AdapterUtil.getTetris(this, new LinkedList<String>());
        mList.setLayoutManager(new LinearLayoutManager(this));
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

    //获取商品 淘宝详情
    private void getCommodityData() {
        mPresenter.getCommodityDetails(mGoodsData.getNum_iid(), new BaseView<CommdityData>() {
            @Override
            public void result(CommdityData bean) {
                if (bean.getCode() == 200) {
                    tetris.setNewData(bean.getResult().getImages());
//                    Log.i("tiancao", bean.getResult().getImages().toString());
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
    private void setUI(CommodityInfoData data) {
        //数据转换
        mData = new GoodListBean.ResultBean();
        mData.setUser_type(data.getResult().getUser_type());
        mData.setCoupon_click_url(data.getResult().getCoupon_click_url());
        mData.setType(data.getResult().getTitle());
        mData.setTitle(data.getResult().getTitle());
        mData.setCoupon_link(data.getResult().getCoupon_link());
        mData.setCoupon_money(data.getResult().getCoupon_money());
        mData.setPrice(data.getResult().getZk_final_price());
        mData.setNum_iid(data.getResult().getNum_iid());
        mData.setCoupon_price(data.getResult().getCoupon_price());
        mData.setPict_url(data.getResult().getPict_url());

        //设置详情的头部数据
        CommodityInfoData.ResultBean result = data.getResult();
        String small_images = result.getSmall_images();
        String[] split = small_images.split(",");
        for (int i = 0; i < split.length; i++) {
            imageurl.add(split[i]);
        }

        //图片轮播
        mBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, imageurl) //mList是图片地址的集合
                .setPointViewVisible(true)    //设置指示器是否可见
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_dot_normal, R.mipmap.ic_dot_pressed})
                //设置指示器位置（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(5000)     //设置自动切换（同时设置了切换时间间隔）
                .setManualPageable(true); //设置手动影响（设置了该项无法手动切换）

        switch (mGoodsData.getUser_type()) {
            case "0":
                mCommodity_type.setImageResource(R.mipmap.tb21);
                mTianmao_and_taobao_price.setText("淘宝价¥" + result.getZk_final_price());
                break;
            case "1":
                mCommodity_type.setImageResource(R.mipmap.tm);
                mTianmao_and_taobao_price.setText("天猫价¥" + result.getZk_final_price());
                break;
        }

        mPrice.setText("¥" + result.getCoupon_price());
        mYimai.setText(result.getVolume() + "人已买");
        mCommodity_title.setText("           " + result.getTitle());
        mLinQuan.setText(mGoodsData.getCoupon_money());
        if (mGoodsData.getCoupon_money().equals("0")) {
            discount_ll_layout.setVisibility(View.GONE);
        } else {
            discount_ll_layout.setVisibility(View.VISIBLE);
        }
        mTime.setText("限时：" + result.getCoupon_start_time() + "-" + result.getCoupon_end_time());

        if (result.getFavorite().equals("1")) {
            mCollect_iv.setImageResource(R.drawable.commodity_details_selected2);
            mCollect_tv.setText("已收藏");
            isCollect = true;
        } else {
            mCollect_iv.setImageResource(R.drawable.commodity_details_selected1);
            mCollect_tv.setText("收藏");
            isCollect = false;
        }

        mShare1_text.setText("分享赚 ¥ " + result.getFenxiang_money());
        mShare2_text.setText("自购赚 ¥ " + result.getZigou_money());


        //设置分享图片数据
        mShaerImage.setImageURI(result.getPict_url());
        mShaerCommodityTitle.setText(result.getTitle());
        mShaerCommodityPic1.setText("原价 ¥" + result.getZk_final_price() + "");
        mShaerCommodityTicketPic.setText(result.getCoupon_money());
        mShaerCommodityPic2.setText("¥" + result.getCoupon_price() + "");

        if (HttpAPI.HOST.equals("http://192.168.1.241:80")) {
            String shaer = "http://192.168.1.241:80/App/mobile/goods_tb?id=" + result.getNum_iid() + "&pid=" + userInfo.getResult().getPid();
            create2Code(shaer);//获取二维码
        } else {
            String shaer = "http://www.17goule.com/App/mobile/goods_tb?id=" + result.getNum_iid() + "&pid=" + userInfo.getResult().getPid();
            create2Code(shaer);//获取二维码
        }
//        create2Code(result.getCoupon_click_url());//获取二维码
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commodity_details_back:
                finish();
                break;
            case R.id.commodity_details_immediately_get://立即领取
               /* if (Token.isLogin()) {
                    if (AliManage.isLogins()) {
                        AliWebActivity.openXQ(this, mGoodsData, option);
                    } else {
                        taobaoLogin();
                    }
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }*/

                if (Token.isLogin()) {
                    boolean b = JianCheYYUtils.checkPackage(this, "com.taobao.taobao");
                    if (b) {
                        Map<String, String> exParams = new HashMap<>();
                        exParams.put(AlibcConstants.ISV_CODE, "appisvcode");
                        //实例化URL打开page
                        AlibcBasePage page = new AlibcPage(mGoodsData.getCoupon_click_url());

                        //设置页面打开方式
                        AlibcShowParams showParams = new AlibcShowParams(OpenType.Native, false);

                        //使用百川sdk提供默认的Activity打开detail
                        AlibcTrade.show(this, page, showParams, null, exParams, new AlibcTradeCallback() {
                            @Override
                            public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {
                                Log.i("tiancao", "成功");
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Log.i("tiancao", i + "失败" + s);
                                taobaoLogin();
                            }
                        });

                    } else {
                        if (AliManage.isLogins()) {
                            AliWebActivity.openXQ(this, mData, option);
                        } else {
                            taobaoLogin();
                        }
                    }
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }


                break;
            case R.id.commodity_details_collect_rl://点击收藏
                if (Token.isLogin()) {
                    if (!isCollect) {
                        mCollect();
                    }
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.commodity_details_share1://分享赚
                if (Token.isLogin()) {
                    mySharePopupwindow.showAtLocation(mRl_All, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.commodity_details_share2://自己赚
                if (Token.isLogin()) {
                    boolean b = JianCheYYUtils.checkPackage(this, "com.taobao.taobao");
                    if (b) {
                        Map<String, String> exParams = new HashMap<>();
                        exParams.put(AlibcConstants.ISV_CODE, "appisvcode");
                        //实例化URL打开page
                        AlibcBasePage page = new AlibcPage(mGoodsData.getCoupon_click_url());

                        //设置页面打开方式
                        AlibcShowParams showParams = new AlibcShowParams(OpenType.Native, false);

                        //使用百川sdk提供默认的Activity打开detail
                        AlibcTrade.show(this, page, showParams, null, exParams, new AlibcTradeCallback() {
                            @Override
                            public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {
                                Log.i("tiancao", "成功");
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Log.i("tiancao", i + "失败" + s);
                                taobaoLogin();
                            }
                        });

                    } else {
                        if (AliManage.isLogins()) {
                            AliWebActivity.openXQ(this, mData, option);
                        } else {
                            taobaoLogin();
                        }
                    }
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.commodity_details_share3://分享链接
                if (Token.isLogin()) {
                    mySharePopupwindow.showAtLocation(mRl_All, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }

    //收藏商品
    private void mCollect() {
        mPresenter.getCollectCommodity(mGoodsData.getNum_iid(), new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                if (bean.getCode() == 200) {
                    mCollect_iv.setImageResource(R.drawable.commodity_details_selected2);
                    mCollect_tv.setText("已收藏");
                    isCollect = true;
                } else {
                    mCollect_iv.setImageResource(R.drawable.commodity_details_selected1);
                    mCollect_tv.setText("收藏");
                    isCollect = false;
                }
            }

            @Override
            public void error() {

            }
        });
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

    //图片滚动
    public class NetworkImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(TetrisActivity.this).load(data).into(imageView);
        }
    }

    //登录淘宝
    private Session session;

    private void taobaoLogin() {
        AliManage.loginTaobao(this, new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {
                session = AlibcLogin.getInstance().getSession();
                MyApplication.session = session;
                presenter.postTaoBaoS(session, new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        if (bean.getCode() == 200) {
                            AliWebActivity.openXQ(TetrisActivity.this, mGoodsData, option);
                        } else if (bean.getCode() == 400) {
                            ToastUtils.toast(TetrisActivity.this, bean.getMsg());
                            taobao_logout();
                        }
                    }

                    @Override
                    public void error() {

                    }
                });
            }

            @Override
            public void onFailure(int i, String s) {
            }
        });
    }


    /**
     * 退出淘宝
     */
    private void taobao_logout() {
        AliManage.logOut(this, new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
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
                Toast.makeText(TetrisActivity.this, "创建文件失败", Toast.LENGTH_SHORT).show();
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
