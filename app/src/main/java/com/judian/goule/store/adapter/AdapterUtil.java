package com.judian.goule.store.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.example.ccy.ccyui.adapter.CommonViewHolder;
import com.example.ccy.ccyui.listener.NoDoubleClickListener;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.judian.goule.store.activity.CommonIssueParticularsActivity;
import com.judian.goule.store.activity.YxGoodsActivity;
import com.judian.goule.store.activity.youxuan.OptimizationWebActivity;
import com.judian.goule.store.activity.youxuan.TetrisActivity;
import com.judian.goule.store.bean.CommonlssueData;
import com.judian.goule.store.bean.OptimizationData;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.utils.FrescoUtils;
import com.kepler.jd.Listener.LoginListener;
import com.kepler.jd.Listener.OpenAppAction;
import com.kepler.jd.login.KeplerApiManager;
import com.kepler.jd.sdk.bean.KeplerAttachParameter;
import com.kepler.jd.sdk.exception.KeplerBufferOverflowException;
import com.makeramen.roundedimageview.RoundedImageView;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.AliWebActivity;
import com.judian.goule.store.activity.AllGoodsActivity;
import com.judian.goule.store.activity.LimitActivity;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.activity.OneGoodsActivity;

import com.judian.goule.store.activity.SelfGoodsActivity;
import com.judian.goule.store.activity.ShareActivity;
import com.judian.goule.store.activity.SignActivity;
import com.judian.goule.store.activity.SorderActivity;
import com.judian.goule.store.activity.WebActivity;
import com.judian.goule.store.bean.AreaListBean;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.BounsBean;
import com.judian.goule.store.bean.FansBean;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.HeMainBean;
import com.judian.goule.store.bean.HelpBean;
import com.judian.goule.store.bean.HotOrderBean;
import com.judian.goule.store.bean.MsBean;
import com.judian.goule.store.bean.MySBean;
import com.judian.goule.store.bean.SelfGoodsBean;
import com.judian.goule.store.bean.TaoOrderBean;
import com.judian.goule.store.bean.TaoTokenBean;
import com.judian.goule.store.bean.XuBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.activity.ExchangeActivity;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.self.EtActivity;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */

public class AdapterUtil {


    /**
     * 地址列表
     *
     * @param activity
     * @return
     */
    public static BaseQuickAdapter<AreaListBean.ResultBean, BaseViewHolder> getAreaList(final Activity activity, final AddressListener listener) {
        return new BaseQuickAdapter<AreaListBean.ResultBean, BaseViewHolder>(R.layout.item_address) {
            @Override
            protected void convert(BaseViewHolder helper, final AreaListBean.ResultBean item, final int position) {

                String str = item.getProvince_name() + item.getCity_name() + item.getArea_name() + item.getAddress();
                helper.setText(R.id.name, item.getName())
                        .setText(R.id.tel, item.getPhone())
                        .setText(R.id.area, str);
                switch (item.getState()) {
                    case "0":
                        helper.getView(R.id.dui).setVisibility(View.GONE);
                        helper.getView(R.id.bm).setVisibility(View.GONE);
                        helper.setImageResource(R.id.mor, R.mipmap.morendizhi1);
                        break;
                    case "1":
                        helper.getView(R.id.dui).setVisibility(View.VISIBLE);
                        helper.getView(R.id.bm).setVisibility(View.VISIBLE);
                        helper.setImageResource(R.id.mor, R.mipmap.morendizhi2);
                        break;
                }


                helper.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        listener.selAddress(item);

                    }
                });
                helper.getView(R.id.xiug).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, EtActivity.class);
                        intent.putExtra(EtActivity.POSITION, item);
                        activity.startActivityForResult(intent, 45);
                    }

                });


            }

        };

    }

    public interface AddressListener {
        void selAddress(AreaListBean.ResultBean bean);


    }

    /**
     * 首页商品适配器
     */
    public static com.judian.goule.store.adapter.BaseQuickAdapter<GoodListBean.ResultBean> getVlayData(final Activity activity, int position, final LoginTaobao mListener) {

        return new com.judian.goule.store.adapter.BaseQuickAdapter<GoodListBean.ResultBean>(R.layout.item_coupon) {

            @Override
            public LayoutHelper onCreateLayoutHelper() {
//                return new GridLayoutHelper(2);
                return new LinearLayoutHelper();
            }

            @Override
            protected void convert(BaseViewHolder viewHolder, final GoodListBean.ResultBean hotBean, int position) {

                viewHolder.setTextView(R.id.pic, hotBean.getPrice());
                switch (hotBean.getUser_type()) {
                    case "0":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tb21)
                                .setTextView(R.id.odl, "淘宝价¥" + hotBean.getReserve_price());
                        break;
                    case "1":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tm)
                                .setTextView(R.id.odl, "天猫价¥" + hotBean.getReserve_price());
                        break;
                }
//                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean.getPict_url(), 2);
                FrescoUtils.load(hotBean.getPict_url(), (SimpleDraweeView) viewHolder.getView(R.id.face), 2, 20, R.drawable.ioc_errer_image_z_y);
                viewHolder.setTextView(R.id.title, "          " + hotBean.getTitle())
                        .setTextView(R.id.xl, hotBean.getMonth_sales() + "人已买")
                        .setTextView(R.id.jifen, "分享赚" + hotBean.getFanli_money() + "");
                if (hotBean.getFanli_money() == null) {
                    viewHolder.setTextView(R.id.info, hotBean.getRebate_money())
                            .setTextView(R.id.pic1, "¥" + hotBean.getRebate_money());
                }

                if (hotBean.getHave_coupon() == 1) {
                    viewHolder.getView(R.id.linquan).setVisibility(View.VISIBLE);
                    viewHolder.setTextView(R.id.linquan, "领券减 ¥" + hotBean.getCoupon_money());
                } else viewHolder.getView(R.id.linquan).setVisibility(View.GONE);

                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (Token.isLogin()) {
//                            AliWebActivity.openXQ(activity, hotBean, 6);
                            mListener.logintaobao(hotBean);
                        } else {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                        }
                    }
                });
            }

        };


    }

    /**
     * 下面这个接口是商品点击的回调
     */

    public interface LoginTaobao {
        void logintaobao(GoodListBean.ResultBean hotBean);
    }

    /**
     * 红包记录
     *
     * @param activity
     * @return
     */

    public static CommonBaseAdapter<BounsBean.ResultBean> getBounsList(final Activity activity) {
        return new CommonBaseAdapter<BounsBean.ResultBean>(activity, R.layout.bouns_item) {
            @Override
            protected void convert(CommonViewHolder helper, final BounsBean.ResultBean item, int position) {

                helper.setTextView(R.id.money, item.getLess_money());
                helper.setTextView(R.id.condition, item.getInstructions());
                helper.setTextView(R.id.time, "有效期：" + item.getStart_time_one() + "至" + item.getEnd_time_one());
                TextView status = helper.getView(R.id.status);
                LinearLayout colorLL = helper.getView(R.id.color_ll);
                ImageView point = helper.getView(R.id.point);
                status.setVisibility(View.GONE);
                switch (item.getStatus()) {
                    case "1":
                        status.setText("未使用");
                        colorLL.setBackgroundResource(R.color.wsy_bg);
                        point.setImageResource(R.mipmap.bouns_content_bg_notused);
                        break;
                    case "2":
                        status.setText("已使用");
                        colorLL.setBackgroundResource(R.color.ysy_bg);
                        point.setImageResource(R.mipmap.bouns_content_bg_hasbeenused);
                        break;
                    case "3":
                        status.setText("已过期");
                        colorLL.setBackgroundResource(R.color.ygq_bg);
                        point.setImageResource(R.mipmap.bouns_content_bg_expired);
                        break;

                }


            }
        };


    }


    //    限时商品
    public static BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> getLimitGoods(final Activity activity, final String state) {

        return new BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder>(R.layout.item_limit_goods) {
            @Override
            protected void convert(BaseViewHolder viewHolder, final GoodListBean.ResultBean dataBean, int p) {
                viewHolder.setText(R.id.title, dataBean.getTitle())
                        .setText(R.id.prc, dataBean.getPrice())
                        .setText(R.id.prcT, "券后：¥")
                        .setText(R.id.info, dataBean.getIntroduce())
                        .setText(R.id.quan, "减" + dataBean.getCoupon_money())

                        .setText(R.id.xl, dataBean.getMonth_sales() + "人已买");
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), dataBean.getPict_url());

//                if (dataBean.getType().equals("3")){
//                    viewHolder.getView(R.id.video_btn).setVisibility(View.VISIBLE);
//                    viewHolder.getView(R.id.video_btn).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
////                            GoodDetailVActivity.openMain(activity,1,dataBean.getNum_iid());
//                        }
//                    });
//                }
                if (Token.getAgent().equals("2")) {
                    viewHolder.setText(R.id.fan, "分享赚" + dataBean.getFanli_money());
                    viewHolder.getView(R.id.fan).setVisibility(View.VISIBLE);
                } else {
                    viewHolder.getView(R.id.fan).setVisibility(View.INVISIBLE);

                }

                if (dataBean.getHave_coupon() == 0) {
                    viewHolder.setText(R.id.prcT, "现价：¥");
                    viewHolder.getView(R.id.you).setVisibility(View.INVISIBLE);
                } else {
                    viewHolder.setText(R.id.prcT, "券后：¥");
                    viewHolder.getView(R.id.you).setVisibility(View.VISIBLE);
                }

                switch (dataBean.getUser_type()) {

                    case "0":
                        viewHolder.setText(R.id.type, "淘宝");
                        break;
                    case "1":
                        viewHolder.setText(R.id.type, "天猫");
                        break;

                }


                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {

                        if (Token.isLogin()) {
                            new CdataPresenter(activity).getTaoToken(dataBean.getNum_iid(), new BaseView<TaoTokenBean>() {
                                @Override
                                public void result(TaoTokenBean bean) {
                                    if (bean.getCode() == 200) {
                                        AliWebActivity.openXQ(activity, bean.getResult(), 6);

                                    } else {
                                        ToastUtils.toast(activity, bean.getMsg());
                                    }
                                }

                                @Override
                                public void error() {
                                }
                            });
                        } else {
                            ToastUtils.toast(activity, "请先登录");
                        }

                    }
                });
            }
        };

    }


    public static void setImgB(SimpleDraweeView mImg, String url, double scale) {
        ViewGroup.LayoutParams layoutParams = mImg.getLayoutParams();
        if (MyApplication.width != 0) {
            layoutParams.width = (int) (MyApplication.width / scale);
            layoutParams.height = (int) (layoutParams.width);
        } else {
            layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            layoutParams.height = (int) (layoutParams.width * 1.33);
        }
        mImg.setLayoutParams(layoutParams);
        if (url == null) return;
        Uri uri = Uri.parse(url);
        ImageRequest request = null;
        request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(400, 400))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mImg.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .setImageRequest(request).build();
        mImg.setController(controller);
    }

    public static void setImgBs(final SimpleDraweeView mImg, final String url, final int imageWidth) {
/*        ViewGroup.LayoutParams layoutParams = mImg.getLayoutParams();
        layoutParams.width = (int) (MyApplication.width);
        layoutParams.height = (int) (layoutParams.width* 1.1);
//        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
//        layoutParams.height = (int) (layoutParams.width * 1.33);
        mImg.setLayoutParams(layoutParams);
        if (url == null) return;
        Uri uri = Uri.parse(url);
        ImageRequest request = null;
        request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(400, 400))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mImg.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .setImageRequest(request)
                .setAutoPlayAnimations(true)//自动播放gif
                .build();
        mImg.setController(controller);*/

        final ViewGroup.LayoutParams layoutParams = mImg.getLayoutParams();
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
                mImg.setLayoutParams(layoutParams);
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
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setAutoPlayAnimations(true)
                .setUri(Uri.parse(url))
                .build();
        mImg.setController(controller);

    }


    /**
     * 优选商品列表页
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> getYxListData(final Activity activity, List<GoodListBean.ResultBean> list) {
        return new BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder>(R.layout.item_coupon, list) {

            @Override
            protected void convert(BaseViewHolder viewHolder, final GoodListBean.ResultBean hotBean, int position) {

                viewHolder.setTextView(R.id.pic, hotBean.getPrice());
                switch (hotBean.getUser_type()) {
                    case "0":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tb21)
                                .setTextView(R.id.odl, "淘宝价¥" + hotBean.getReserve_price());
                        break;
                    case "1":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tm)
                                .setTextView(R.id.odl, "天猫价¥" + hotBean.getReserve_price());
                        break;
                }
                FrescoUtils.load(hotBean.getPict_url(), (SimpleDraweeView) viewHolder.getView(R.id.face), 2, 20);
                viewHolder.setTextView(R.id.title, "           " + hotBean.getTitle())
                        .setTextView(R.id.xl, hotBean.getMonth_sales() + "人已买");
                UserInfo userInfo = UserInfoDBUtil.get(mContext);
                if (userInfo.getResult().getLevel().equals("6")) {
                    viewHolder.getView(R.id.jifen).setVisibility(View.GONE);
                    TextView shengji_zhuan_tv = viewHolder.getView(R.id.shengji_zhuan_tv);
                    shengji_zhuan_tv.setTextColor(mContext.getResources().getColor(R.color.coupon_item1));
                    shengji_zhuan_tv.setBackground(mContext.getResources().getDrawable(R.drawable.xianquan_hongse));
                    viewHolder.setTextView(R.id.shengji_zhuan_tv, "分享赚" + hotBean.getFanli_money_fenxiang() + "");
                } else {
                    viewHolder.setTextView(R.id.jifen, "分享赚" + hotBean.getFanli_money_fenxiang() + "");
                    viewHolder.setTextView(R.id.shengji_zhuan_tv, "升级赚" + hotBean.getFanli_money_shengji() + "");
                }

                if (hotBean.getFanli_money() == null) {
                    viewHolder.setTextView(R.id.info, hotBean.getRebate_money())
                            .setTextView(R.id.pic1, "¥" + hotBean.getRebate_money());
                }

                if (hotBean.getHave_coupon() == 1) {
                    viewHolder.getView(R.id.linquan).setVisibility(View.VISIBLE);
                    viewHolder.setTextView(R.id.linquan, "领券减 ¥" + hotBean.getCoupon_money());
                } else viewHolder.getView(R.id.linquan).setVisibility(View.GONE);

                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (Token.isLogin()) {
                            TetrisActivity.openMain(activity, hotBean, 6);
                        } else {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                        }
                    }
                });

            }
        };


    }

    /**
     * 搜索页
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> getSouData(final Activity activity, List<GoodListBean.ResultBean> list) {
        return new BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder>(R.layout.item_coupon2, list) {
            @Override
            protected void convert(BaseViewHolder viewHolder, final GoodListBean.ResultBean hotBean, int position) {

                viewHolder.setTextView(R.id.pic, hotBean.getPrice());
                switch (hotBean.getUser_type()) {
                    case "0":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tb21)
                                .setTextView(R.id.odl, "淘宝价¥" + hotBean.getReserve_price());
                        break;
                    case "1":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tm)
                                .setTextView(R.id.odl, "天猫价¥" + hotBean.getReserve_price());
                        break;
                }
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean.getPict_url(), 2);
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean.getPict_url());
                viewHolder.setTextView(R.id.title, "           " + hotBean.getTitle())
                        .setTextView(R.id.xl, "月销" + hotBean.getMonth_sales())
                        .setTextView(R.id.jifen, "预估佣金：¥" + hotBean.getFanli_money() + "");
                if (hotBean.getFanli_money() == null) {
                    viewHolder.setTextView(R.id.info, hotBean.getRebate_money())
                            .setTextView(R.id.pic1, "¥" + hotBean.getRebate_money());
                }

                if (hotBean.getHave_coupon() == 1) {
                    viewHolder.getView(R.id.linquan).setVisibility(View.VISIBLE);
                    viewHolder.setTextView(R.id.linquan, "券 ¥" + hotBean.getCoupon_money());
                } else viewHolder.getView(R.id.linquan).setVisibility(View.GONE);

                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (Token.isLogin()) {
//                            AliWebActivity.openXQ(activity, hotBean, 6);
                            TetrisActivity.openMain(activity, hotBean, 6);
                        } else {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                        }
                    }
                });

            }
        };


    }


    /**
     * 商品详情里面的淘宝详情
     */

    public static BaseQuickAdapter<String, BaseViewHolder> getTetris(final Context context, List<String> list) {
        return new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_tetris, list) {
            @Override
            protected void convert(BaseViewHolder viewHolder, final String hotBean, int position) {

                RelativeLayout view = (RelativeLayout) viewHolder.getView(R.id.tetris_ll);
                int screenWidth = FrescoUtils.getScreenWidth(context);
                setImgBs((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean, screenWidth);
//                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean);
//                FrescoUtils.load(hotBean,(SimpleDraweeView) viewHolder.getView(R.id.face));
//                ImageView view = (ImageView)viewHolder.getView(R.id.face);
//                Glide.with(context).load(hotBean).diskCacheStrategy(DiskCacheStrategy.NONE).into(view);
            }
        };


    }


    /**
     * 搜索页
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> getSouData2(final Activity activity, List<GoodListBean.ResultBean> list) {
        return new BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder>(R.layout.item_coupon2, list) {
            @Override
            protected void convert(BaseViewHolder viewHolder, final GoodListBean.ResultBean hotBean, int position) {

                viewHolder.setTextView(R.id.pic, hotBean.getCoupon_price());
                switch (hotBean.getUser_type()) {
                    case "0":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tb21)
                                .setTextView(R.id.odl, "淘宝价¥" + hotBean.getReserve_price());
                        break;
                    case "1":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tm)
                                .setTextView(R.id.odl, "天猫价¥" + hotBean.getReserve_price());
                        break;
                }
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean.getPict_url(), 2);
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean.getPict_url());
                viewHolder.setTextView(R.id.title, "           " + hotBean.getTitle())
                        .setTextView(R.id.xl, "月销" + hotBean.getMonth_sales())
                        .setTextView(R.id.jifen, "预估佣金：¥" + hotBean.getFanli_money() + "");
                if (hotBean.getFanli_money() == null) {
                    viewHolder.setTextView(R.id.info, hotBean.getRebate_money());
                }

                if (hotBean.getHave_coupon() == 1) {
                    viewHolder.getView(R.id.linquan).setVisibility(View.VISIBLE);
                    viewHolder.setTextView(R.id.linquan, "券 ¥" + hotBean.getCoupon_money());
                } else viewHolder.getView(R.id.linquan).setVisibility(View.GONE);

                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (Token.isLogin()) {
                            TetrisActivity.openMain(activity, hotBean, 6);
                            //下面这个操作未知
                            /*new CdataPresenter(activity).getTaoToken(hotBean.getNum_iid(), new BaseView<TaoTokenBean>() {
                                @Override
                                public void result(TaoTokenBean bean) {
                                    if (bean.getCode() == 200) {
                                        AliWebActivity.openXQ(activity, bean.getResult(), 6);
                                        Log.i("tiancao", "搜索全网数据：" + hotBean.toString());
                                    } else {
                                        ToastUtils.toast(activity, bean.getMsg());
                                    }
                                }

                                @Override
                                public void error() {
                                }
                            });*/
                        } else {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                        }
                    }
                });

            }
        };


    }


    /**
     * 花钱页
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> getSpendData(final Activity activity, List<GoodListBean.ResultBean> list) {
        return new BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder>(R.layout.item_coupon1, list) {
            @Override
            protected void convert(BaseViewHolder helper, GoodListBean.ResultBean item, int position) {
                convert(helper, item);

                if ((position % 20) == 0) {
                    Fresco.getImagePipeline().clearCaches();
                }

            }

            protected void convert(final BaseViewHolder viewHolder, final GoodListBean.ResultBean hotBean) {


                viewHolder.setTextView(R.id.pic, hotBean.getCoupon_price());
                switch (hotBean.getUser_type()) {
                    case "0":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tb21);
                        break;
                    case "1":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tm);
                        break;

                    case "3":
                        viewHolder.setImageResource(R.id.type, R.mipmap.jd);
                        break;

                }
//                Glide.with(activity).load(hotBean.getPict_url()).crossFade().centerCrop().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).into((SimpleDraweeView)viewHolder.getView(R.id.face));


                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean.getPict_url());
                viewHolder.setTextView(R.id.title, "        " + hotBean.getTitle())
                        .setTextView(R.id.info, hotBean.getFanli_money())
                        .setTextView(R.id.pic1, "赚" + hotBean.getFanli_money())
                        .setTextView(R.id.payOld, "¥" + hotBean.getReserve_price());
                if (hotBean.getClick_num() != null) {
                    viewHolder.setTextView(R.id.click_num, "点击量" + hotBean.getClick_num());
                }

                if (hotBean.getFanli_money() == null) {
                    viewHolder.setTextView(R.id.info, hotBean.getRebate_money())
                            .setTextView(R.id.pic1, "赚" + hotBean.getRebate_money());
                }

                if (Token.getAgent().equals("2")) {
                    viewHolder.getView(R.id.pic1).setVisibility(View.VISIBLE);
                    viewHolder.getView(R.id.ss2).setVisibility(View.VISIBLE);


                } else {
                    viewHolder.getView(R.id.pic1).setVisibility(View.GONE);
                    viewHolder.getView(R.id.ss2).setVisibility(View.GONE);
                }

                viewHolder.getTextView(R.id.payOld).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.getTextView(R.id.payOld).getPaint().setAntiAlias(true);
                if (hotBean.getHave_coupon() == 1) {
                    viewHolder.getView(R.id.youhui).setVisibility(View.VISIBLE);
                    viewHolder.setTextView(R.id.quan, "立减" + hotBean.getCoupon_money() + "元");
                } else {
                    viewHolder.getView(R.id.youhui).setVisibility(View.GONE);
                }

                if (hotBean.getUser_type().equals("3")) {
                    viewHolder.setTextView(R.id.txt, "马上抢购")
                            .setTextView(R.id.pic1, "");
                } else {
                    viewHolder.setTextView(R.id.txt, "分享");

                }

                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (Token.isLogin()) {
                            new CdataPresenter(activity).getAddNum(hotBean.getNum_iid());

                            if (hotBean.getUser_type().equals("3")) {
                                if (!KeplerApiManager.getWebViewService().isKeplerLogined()) {
                                    // 直接授权登录京东​
                                    KeplerApiManager.getWebViewService().login(
                                            activity, mLoginListener);
                                } else
                                    loginJD(activity, hotBean.getItem_url());
//                                WebActivity.openMain(activity,hotBean.getTitle(),hotBean.getItem_url());
                            } else {
                                AliWebActivity.openXQ(activity, hotBean, 6);
                            }
                            if (hotBean.getClick_num() != null) {
                                viewHolder.setTextView(R.id.click_num, "点击量" + (Integer.valueOf(hotBean.getClick_num()) + 1));
                            }


                        } else {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                        }


                    }
                });
                viewHolder.getView(R.id.shaer).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (Token.isLogin()) {
                            if (hotBean.getUser_type().equals("3")) {
                                if (!KeplerApiManager.getWebViewService().isKeplerLogined()) {
                                    // 直接授权登录京东​
                                    KeplerApiManager.getWebViewService().login(
                                            activity, mLoginListener);
                                } else
                                    loginJD(activity, hotBean.getItem_url());
//
                            } else {
                                ShareActivity.openMain(activity, hotBean.getNum_iid());
                            }
                        } else {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                        }
                    }
                });
            }
        };
    }

    /**
     * 商品列表旧的
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> getSpendData2(final Activity activity, List<GoodListBean.ResultBean> list) {
        return new BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder>(R.layout.item_coupon1, list) {
            @Override
            protected void convert(BaseViewHolder helper, GoodListBean.ResultBean item, int position) {
                convert(helper, item);

                if ((position % 20) == 0) {
                    Fresco.getImagePipeline().clearCaches();
                }

            }

            protected void convert(final BaseViewHolder viewHolder, final GoodListBean.ResultBean hotBean) {

                String price = hotBean.getCoupon_price();
                viewHolder.setTextView(R.id.pic, price);

                switch (hotBean.getUser_type()) {
                    case "0":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tb21);
                        break;
                    case "1":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tm);
                        break;

                    case "3":
                        viewHolder.setImageResource(R.id.type, R.mipmap.jd);
                        break;

                }

                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean.getPict_url());
                viewHolder.setTextView(R.id.title, "        " + hotBean.getTitle())
                        .setTextView(R.id.info, hotBean.getFanli_money())
                        .setTextView(R.id.pic1, "赚" + hotBean.getFanli_money())
                        .setTextView(R.id.payOld, "¥" + hotBean.getReserve_price());
                if (hotBean.getClick_num() != null) {
                    viewHolder.setTextView(R.id.click_num, "点击量" + hotBean.getClick_num());
                }

                if (hotBean.getFanli_money() == null) {
                    viewHolder.setTextView(R.id.info, hotBean.getRebate_money())
                            .setTextView(R.id.pic1, "赚" + hotBean.getRebate_money());
                }

                if (Token.getAgent().equals("2")) {
                    viewHolder.getView(R.id.pic1).setVisibility(View.VISIBLE);
                    viewHolder.getView(R.id.ss2).setVisibility(View.VISIBLE);


                } else {
                    viewHolder.getView(R.id.pic1).setVisibility(View.GONE);
                    viewHolder.getView(R.id.ss2).setVisibility(View.GONE);
                }

                viewHolder.getTextView(R.id.payOld).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.getTextView(R.id.payOld).getPaint().setAntiAlias(true);
                if (hotBean.getHave_coupon() == 1) {
                    viewHolder.getView(R.id.youhui).setVisibility(View.VISIBLE);
                    viewHolder.setTextView(R.id.quan, "立减" + hotBean.getCoupon_money() + "元");
                } else {
                    viewHolder.getView(R.id.youhui).setVisibility(View.GONE);
                }

                if (hotBean.getUser_type().equals("3")) {
                    viewHolder.setTextView(R.id.txt, "马上抢购")
                            .setTextView(R.id.pic1, "");
                } else {
                    viewHolder.setTextView(R.id.txt, "分享");

                }

                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (Token.isLogin()) {
                            new CdataPresenter(activity).getAddNum(hotBean.getNum_iid());

                            if (hotBean.getUser_type().equals("3")) {
                                if (!KeplerApiManager.getWebViewService().isKeplerLogined()) {
                                    // 直接授权登录京东​
                                    KeplerApiManager.getWebViewService().login(
                                            activity, mLoginListener);
                                } else
                                    loginJD(activity, hotBean.getItem_url());
//                                WebActivity.openMain(activity,hotBean.getTitle(),hotBean.getItem_url());
                            } else {
                                TetrisActivity.openMain(activity, hotBean, 6);
                                /*new CdataPresenter(activity).getTaoToken(hotBean.getNum_iid(), new BaseView<TaoTokenBean>() {
                                    @Override
                                    public void result(TaoTokenBean bean) {
                                        if (bean.getCode() == 200) {
                                            AliWebActivity.openXQ(activity, bean.getResult(), 6);
                                            Log.i("tiancao", "搜索APP数据：" + hotBean.toString());
                                        } else {
                                            ToastUtils.toast(activity, bean.getMsg());
                                        }
                                    }

                                    @Override
                                    public void error() {
                                    }
                                });*/
                            }
                            if (hotBean.getClick_num() != null) {
                                viewHolder.setTextView(R.id.click_num, "点击量" + (Integer.valueOf(hotBean.getClick_num()) + 1));
                            }


                        } else {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                        }


                    }
                });
                /*viewHolder.getView(R.id.shaer).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (Token.isLogin()) {
                            if (hotBean.getUser_type().equals("3")) {
                                if (!KeplerApiManager.getWebViewService().isKeplerLogined()) {
                                    // 直接授权登录京东​
                                    KeplerApiManager.getWebViewService().login(
                                            activity, mLoginListener);
                                } else
                                    loginJD(activity, hotBean.getItem_url());
//
                            } else {
                                ShareActivity.openMain(activity, hotBean.getNum_iid());
                            }
                        } else {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                        }
                    }
                });*/
            }
        };
    }


    public static BaseQuickAdapter<TaoOrderBean.ResultBean, BaseViewHolder> getSelfOrder(final Activity activity) {

        return new BaseQuickAdapter<TaoOrderBean.ResultBean, BaseViewHolder>(R.layout.item_self) {
            @Override
            protected void convert(BaseViewHolder viewHolder, final TaoOrderBean.ResultBean dataBean, int position) {
                viewHolder.setText(R.id.title, dataBean.getGood_title())
                        .setText(R.id.price, "¥" + dataBean.getAmount_pay())
                        .setText(R.id.tv1, "实付金额");
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), dataBean.getPict_url());
                TextView tv = viewHolder.getView(R.id.status);
                tv.setText(dataBean.getOrder_state());

                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {

//                            OrderActivity.openMain(activity,dataBean.getId());

                    }
                });
            }
        };

    }


    /**
     * 京东
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> getJDData(final Activity activity, List<GoodListBean.ResultBean> list) {
        return new BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder>(R.layout.item_jd, list) {
            @Override
            protected void convert(BaseViewHolder helper, GoodListBean.ResultBean item, int position) {
                convert(helper, item);
            }

            protected void convert(BaseViewHolder viewHolder, final GoodListBean.ResultBean hotBean) {

                viewHolder.setTextView(R.id.pic, hotBean.getCoupon_price());
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean.getPict_url());
                viewHolder.setTextView(R.id.title, "        " + hotBean.getTitle())
                        .setTextView(R.id.info, "   赚  " + hotBean.getFanli_money())
                        .setTextView(R.id.payOld, "¥" + hotBean.getReserve_price());
                if (hotBean.getFanli_money() == null) {
                    viewHolder.setTextView(R.id.info, hotBean.getRebate_money());

                }

                viewHolder.getTextView(R.id.payOld).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.getTextView(R.id.payOld).getPaint().setAntiAlias(true);
                if (!hotBean.getCoupon_money().equals("0")) {
                    viewHolder.getView(R.id.youhui).setVisibility(View.VISIBLE);
                    viewHolder.setTextView(R.id.quan, "立减" + hotBean.getCoupon_money() + "元");
                } else {
                    viewHolder.getView(R.id.youhui).setVisibility(View.GONE);
                }

                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {

                        loginJD(activity, hotBean.getItem_url());

                    }
                });

            }
        };


    }


    public static OpenAppAction mOpenAppAction = new OpenAppAction() {
        @Override
        public void onStatus(final int status) {
            Logger.e("ffffffff", "mOpenAppAction == " + status);

        }
    };


    private static LoginListener mLoginListener = new LoginListener() {

        @Override
        public void authSuccess(final Object token) {
            Logger.e("fffffff", "app mLoginListener" + token);
            Log.e("Kepler", "Kepler mLoginListener onSuccess " + token);
        }

        @Override
        public void authFailed(final int errorCode) {
            Logger.e("fffffff", "app authFailed" + errorCode);
            Log.e("Kepler", "Kepler mLoginListener authFailed " + errorCode);
            switch (errorCode) {
                case KeplerApiManager.KeplerApiManagerLoginErr_Init:// 初始化失败
                    break;
                case KeplerApiManager.KeplerApiManagerLoginErr_InitIng:// 初始化没有完成
                    break;
                case KeplerApiManager.KeplerApiManagerLoginErr_openH5authPageURLSettingNull:// 跳转url
                    break;
                case KeplerApiManager.KeplerApiManagerLoginErr_getTokenErr:// 获取失败(oath授权之后，获取cookie过程出错)
                    break;
                case KeplerApiManager.KeplerApiManagerLoginErr_User_Cancel:// 用户取消
                    break;
                case KeplerApiManager.KeplerApiManagerLoginErr_AuthErr_ActivityOpen:// 打开授权页面失败
                    break;
                default:
                    break;
            }


        }
    };


    public static final int timeOut = 15;

    public static void loginJD(Activity activity, String info) {

        KeplerAttachParameter mKeplerAttachParameter = new KeplerAttachParameter();//这个是即时性参数  可以设置
        try {

            KeplerApiManager
                    .getWebViewService()
                    .openJDUrlWebViewPage(info,
                            mKeplerAttachParameter);

        } catch (KeplerBufferOverflowException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 自营
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<SelfGoodsBean.ResultBean, BaseViewHolder> getSelfData(final Activity activity) {
        return new BaseQuickAdapter<SelfGoodsBean.ResultBean, BaseViewHolder>(R.layout.item_selfg) {
            @Override
            protected void convert(BaseViewHolder helper, SelfGoodsBean.ResultBean item, int position) {
                convert(helper, item);
            }

            protected void convert(BaseViewHolder viewHolder, final SelfGoodsBean.ResultBean hotBean) {

                viewHolder.setTextView(R.id.pic, hotBean.getPrice());
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean.getPict_url());
                viewHolder.setTextView(R.id.title, "  " + hotBean.getTitle());
                viewHolder.setTextView(R.id.intro, "  " + hotBean.getIntro());

                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {

                        OneGoodsActivity.openMain(activity, hotBean.getId());

                    }
                });

            }
        };


    }


    /**
     * 分享赚钱
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> getMakeData(final Activity activity, final MakeSelLintener lintener) {
        return new BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder>(R.layout.item_make) {
            @Override
            protected void convert(final BaseViewHolder viewHolder, final GoodListBean.ResultBean hotBean, int p) {

                viewHolder.setText(R.id.pic, hotBean.getPrice());
                switch (hotBean.getUser_type()) {
                    case "0":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tb21);
                        break;
                    case "1":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tm);
                        break;

                }
                setImg((SimpleDraweeView) viewHolder.getView(R.id.face), hotBean.getPict_url());
                viewHolder.setText(R.id.title, "        " + hotBean.getTitle())
                        .setText(R.id.pic1, "¥" + hotBean.getFanli_money());

                UserInfo userInfo = UserInfoDBUtil.get(activity);
                if (userInfo.getResult() != null) {
                    if (userInfo.getResult().getLevel().equals("1")) {
                        viewHolder.setText(R.id.pic1, "¥" + hotBean.getFanli_money_fenxiang());
                    } else {
                        viewHolder.setText(R.id.pic1, "¥" + hotBean.getFanli_money_shengji());
                    }
                }



   /*             if (Token.getAgent().equals("2")) {
                    viewHolder.getView(R.id.pic1).setVisibility(View.VISIBLE);
                    viewHolder.getView(R.id.tv2).setVisibility(View.VISIBLE);
                } else {
                    viewHolder.getView(R.id.pic1).setVisibility(View.GONE);
                    viewHolder.getView(R.id.tv2).setVisibility(View.GONE);
                }*/


                if (hotBean.getHave_coupon() == 1) {
                    viewHolder.getView(R.id.youhui).setVisibility(View.VISIBLE);
                    viewHolder.setText(R.id.quan, hotBean.getCoupon_money() + "元");
                } else {
                    viewHolder.getView(R.id.youhui).setVisibility(View.GONE);
                }

                viewHolder.getView(R.id.share).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        lintener.share(hotBean);
                    }
                });
                if (hotBean.isSel()) {
                    viewHolder.setImageResource(R.id.sel, R.mipmap.proxy_tag);
                } else {
                    viewHolder.setImageResource(R.id.sel, R.mipmap.proxy_tag_de);
                }


                viewHolder.getView(R.id.face).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (hotBean.isSel()) {
                            lintener.remov(hotBean);

                            hotBean.setSel(false);
                            viewHolder.setImageResource(R.id.sel, R.mipmap.proxy_tag_de);
                        } else {
                            lintener.add(hotBean);
                            hotBean.setSel(true);
                            viewHolder.setImageResource(R.id.sel, R.mipmap.proxy_tag);

                        }


                    }
                });

                //商品详情的点击
                viewHolder.getView(R.id.item_make_ll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lintener.itemClick(hotBean);
                    }
                });

            }
        };


    }

    public interface MakeSelLintener {

        void add(GoodListBean.ResultBean hotBean);

        void remov(GoodListBean.ResultBean hotBean);

        void share(GoodListBean.ResultBean hotBean);

        void itemClick(GoodListBean.ResultBean hotBean);
    }


    /**
     *  商品列表
     * @param activity
     * @return
     */


    /**
     *  花钱页
     * @param activity
     * @return
     */


    /**
     * 晒单
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<HotOrderBean.ResultBean, BaseViewHolder> getHotOrder(final Activity activity, final OnSorderListener listener) {
        return new BaseQuickAdapter<HotOrderBean.ResultBean, BaseViewHolder>(R.layout.item_hot_order) {
            @Override
            public void convert(final BaseViewHolder viewHolder, final HotOrderBean.ResultBean hotBean, int position) {
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.icon), hotBean.getPict_url());
                if (!hotBean.getAvatar().equals("")) {
                    Picasso.with(activity).load(hotBean.getAvatar()).into(viewHolder.getImageView(R.id.face));
                }

                viewHolder.setText(R.id.title, hotBean.getTitle())
                        .setText(R.id.txt, hotBean.getContent())
                        .setText(R.id.name, hotBean.getNickname())
                        .setText(R.id.zhan, hotBean.getZan() + "");
                if (hotBean.getIs_zan().equals("0")) {
                    viewHolder.getImageView(R.id.zhanIv).setImageResource(R.mipmap.zhan1);
                } else {
                    viewHolder.getImageView(R.id.zhanIv).setImageResource(R.mipmap.zhan);
                }


                viewHolder.getView(R.id.icon).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (listener != null) listener.onclick(hotBean);
                    }
                });
                viewHolder.getView(R.id.zhanIv).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        new CdataPresenter(activity).getComment(hotBean.getId(), new BaseView<BaseBean>() {
                            @Override
                            public void result(BaseBean bean) {
                                ToastUtils.toast(activity, bean.getMsg());
                                if (bean.getCode() == 200) {
                                    viewHolder.getImageView(R.id.zhanIv).setImageResource(R.mipmap.zhan);
                                    viewHolder.setText(R.id.zhan, bean.getResult().getZan_num() + "");
                                }

                            }

                            @Override
                            public void error() {

                            }
                        });
                    }
                });

            }
        };


    }


    /**
     * 他的 晒单
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<HeMainBean.ResultBean.CommentListBean, BaseViewHolder> getHeOrder(final Activity activity, final OnHeorderListener listener) {
        return new BaseQuickAdapter<HeMainBean.ResultBean.CommentListBean, BaseViewHolder>(R.layout.item_hot_order) {


            @Override
            protected void convert(BaseViewHolder helper, HeMainBean.ResultBean.CommentListBean item, int position) {

                try {
                    bindData(helper, position, item);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void bindData(final BaseViewHolder viewHolder, int position, final HeMainBean.ResultBean.CommentListBean hotBean) throws JSONException {
                viewHolder.setText(R.id.title, hotBean.getTitle())
                        .setText(R.id.txt, hotBean.getContent())
                        .setText(R.id.name, hotBean.getNickname())
                        .setText(R.id.zhan, hotBean.getZan() + "");
                if (hotBean.getAvatar() != null)
                    Picasso.with(activity).load(hotBean.getAvatar()).into(viewHolder.getImageView(R.id.face));
                setControllerListener((SimpleDraweeView) viewHolder.getView(R.id.icon), hotBean.getPict_url(), (int) (MyApplication.width / 2.3));
                if (hotBean.getIs_zan().equals("0")) {
                    viewHolder.getImageView(R.id.zhanIv).setImageResource(R.mipmap.zhan1);
                } else {
                    viewHolder.getImageView(R.id.zhanIv).setImageResource(R.mipmap.zhan);
                }


                viewHolder.getView(R.id.icon).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (listener != null) listener.onclick(hotBean);
                    }
                });
                viewHolder.getView(R.id.zhanIv).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        new CdataPresenter(activity).getComment(hotBean.getId(), new BaseView<BaseBean>() {
                            @Override
                            public void result(BaseBean bean) {
                                ToastUtils.toast(activity, bean.getMsg());
                                if (bean.getCode() == 200) {
                                    viewHolder.getImageView(R.id.zhanIv).setImageResource(R.mipmap.zhan);
                                    viewHolder.setText(R.id.zhan, bean.getResult().getZan_num() + "");
                                }

                            }

                            @Override
                            public void error() {

                            }
                        });
                    }
                });

            }
        };


    }


    public interface OnHeorderListener {
        void onclick(HeMainBean.ResultBean.CommentListBean hotBean);


    }


    /**
     * 晒单
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<HotOrderBean.ResultBean, BaseViewHolder> getOtherOrder(final Activity activity, final OnSorderListener listener) {
        return new BaseQuickAdapter<HotOrderBean.ResultBean, BaseViewHolder>(R.layout.item_other_order) {
            @Override
            protected void convert(final BaseViewHolder helper, final HotOrderBean.ResultBean hotBean, int p) {
                helper.setText(R.id.title, hotBean.getTitle())
                        .setText(R.id.txt, hotBean.getContent())
                        .setText(R.id.name, hotBean.getNickname())
                        .setText(R.id.zhan, hotBean.getZan() + "");
                if (hotBean.getAvatar() != null && !hotBean.getAvatar().equals(""))
                    Picasso.with(activity).load(hotBean.getAvatar()).into((RoundedImageView) helper.getView(R.id.face));
                if (hotBean.getPict_url() != null)
                    setControllerListener((SimpleDraweeView) helper.getView(R.id.icon), hotBean.getPict_url(), (int) (MyApplication.width / 2.3));
                if (hotBean.getIs_zan() != null) {
                    if (hotBean.getIs_zan().equals("0")) {
                        helper.setImageResource(R.id.zhanIv, R.mipmap.zhan1);

                    } else {
                        helper.setImageResource(R.id.zhanIv, R.mipmap.zhan);
                    }

                }


                helper.getView(R.id.icon).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (listener != null) listener.onclick(hotBean);
                    }
                });
                helper.getView(R.id.zhanIv).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        new CdataPresenter(activity).getComment(hotBean.getId(), new BaseView<BaseBean>() {
                            @Override
                            public void result(BaseBean bean) {
                                ToastUtils.toast(activity, bean.getMsg());
                                if (bean.getCode() == 200) {
                                    helper.setImageResource(R.id.zhanIv, R.mipmap.zhan);
                                    helper.setText(R.id.zhan, bean.getResult().getZan_num() + "");
                                }

                            }

                            @Override
                            public void error() {

                            }
                        });
                    }
                });

            }

        };


    }


    public static void setController(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageWidth) {
        if (imagePath == null) return;
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
                layoutParams.height = (int) (imageWidth * height / width * 2);
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

        DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener)
                .setUri(Uri.parse(imagePath))
                .build();
        simpleDraweeView.setController(controller);
    }


    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageWidth) {
        if (imagePath == null) return;
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
                layoutParams.height = (int) (imageWidth * height) / width;
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

        DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener)
                .setUri(Uri.parse(imagePath))
                .build();
        simpleDraweeView.setController(controller);
    }


    public static void setPicaso(Context context, final ImageView imageView, String imagePath, final int imageWidth) {

        Picasso.with(context).load(imagePath).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageView.setImageBitmap(bitmap);
                imageView.getLayoutParams().height = imageWidth * bitmap.getHeight() / bitmap.getWidth();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


    }


    public static void setImgB(SimpleDraweeView mImg, String url) {

        if (url == null) return;
        Uri uri = Uri.parse(url);
        ImageRequest request = null;
        request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(280, 280))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mImg.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .setImageRequest(request).build();
        mImg.setController(controller);
    }


    public static void setImgMake(SimpleDraweeView mImg, String url) {

        if (url == null) return;
        Uri uri = Uri.parse(url);
        ImageRequest request = null;
        request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(800, 800))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mImg.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .setImageRequest(request).build();
        mImg.setController(controller);
    }


    public interface OnSorderListener {
        void onclick(HotOrderBean.ResultBean hotBean);


    }


    /**
     * 宫格图
     *
     * @param activity
     * @return
     */
    public static CommonBaseAdapter<XuBean.ResultBean> getGg(final Activity activity, final HomeLintener lintener) {
        return new CommonBaseAdapter<XuBean.ResultBean>(activity, R.layout.sub_cate_gridview) {
            @Override
            protected void convert(CommonViewHolder viewHolder, final XuBean.ResultBean xuBean, int position) {
                viewHolder.setTextView(R.id.tvCateName, xuBean.getTitle())
                        .setImageResource(R.id.ivPic, xuBean.getImg());
//                        .setImageURI(R.id.ivPic, HttpAPI.HOST + "/"+xuBean.getPic_url());
                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {

                        switch (xuBean.getId()) {
                            case "28"://签到
                                activity.startActivity(new Intent(activity, SignActivity.class));
                                break;

                            case "11"://积分兑换
                                activity.startActivity(new Intent(activity, ExchangeActivity.class));
                                break;
                            case "31"://在线客服
                                if (Token.isLogin()) {
                                    SorderActivity.openMain(activity, 2);
                                } else
                                    activity.startActivity(new Intent(activity, LoginActivity.class));

                                break;
                            case "2"://天猫超市
                                xuBean.setHref("/App/SpendMoney/categoods?cate_id=106");
                                AllGoodsActivity.openMain(activity, xuBean, 3);
//                             AllGoodsActivity.openMain(context,xuBean.getTitle(),0);
//                              WebActivity.openMain(context,"京东商城",HttpAPI.JINGDONG);
                                break;

                            case "1"://女装配饰
                                xuBean.setHref("/App/SpendMoney/categoods?cate_id=104");
                                AllGoodsActivity.openMain(activity, xuBean, 3);
//                             AllGoodsActivity.openMain(context,xuBean.getTitle(),0);

                                break;


                            case "8"://居家百货
                                xuBean.setHref("/App/SpendMoney/categoods?cate_id=108");
                                AllGoodsActivity.openMain(activity, xuBean, 3);
//                             AllGoodsActivity.openMain(context,xuBean.getTitle(),0);
//                             WebActivity.openMain(context,"蘑菇街",HttpAPI.MOGUJIE);
                                break;


                            case "29"://限时抢购

                                activity.startActivity(new Intent(activity, LimitActivity.class));

                                break;

                            case "21"://美妆日化
                                xuBean.setHref("/App/SpendMoney/categoods?cate_id=110");
                                AllGoodsActivity.openMain(activity, xuBean, 3);
                                break;

                            case "25"://美食生鲜
                                xuBean.setHref("/App/SpendMoney/categoods?cate_id=112");
                                AllGoodsActivity.openMain(activity, xuBean, 3);
                                break;

                            case "30"://自营商品
                                activity.startActivity(new Intent(activity, SelfGoodsActivity.class));
                                break;
//
//                         case "28"://新手教程
//
//                                  activity.startActivity(new Intent(activity, CommonIssueActivity.class));
//
//                             break;
                        }


                    }
                });
            }
        };

    }

    public interface HomeLintener {
        void option(int op);

    }


    //    返利订单
    public static BaseQuickAdapter<TaoOrderBean.ResultBean, BaseViewHolder> getTaoBOrder(final Activity activity, final OnGoAddOrder listener) {

        return new BaseQuickAdapter<TaoOrderBean.ResultBean, BaseViewHolder>(R.layout.item_fans_order) {
            @Override
            protected void convert(BaseViewHolder viewHolder, final TaoOrderBean.ResultBean dataBean, int position) {

                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), dataBean.getPict_url());
                viewHolder.setTextView(R.id.title, dataBean.getGood_title())
                        .setTextView(R.id.fuMoney, "订单总金额：" + dataBean.getAmount_pay() + "元")
                        .setTextView(R.id.time, dataBean.getAdd_time())
                        .setTextView(R.id.type, dataBean.getOrder_type())
                        .setTextView(R.id.ddb, "+" + dataBean.getRe_money())
                        .setTextView(R.id.order_sn, "订单号：" + dataBean.getOrder_sn())
                        .setTextView(R.id.state, "" + dataBean.getOrder_state());    // 购买  待付款


                TextView tv = viewHolder.getView(R.id.state);
                if (dataBean.getOrder_state().equals("订单失效")) {
                    tv.setText("已失效");
                    tv.setBackgroundResource(R.drawable.bg_order_state3);
                    viewHolder.setImageResource(R.id.jinzi, R.mipmap.jinzi1);
                    viewHolder.getTextView(R.id.textView).setTextColor(activity.getResources().getColor(R.color.baen_jinzi));
                    viewHolder.getTextView(R.id.ddb).setTextColor(activity.getResources().getColor(R.color.baen_jinzi));

                } else if (dataBean.getOrder_state().equals("订单付款")) {
                    tv.setText("已付款");
                    tv.setBackgroundResource(R.drawable.bg_order_state);
                } else if (dataBean.getOrder_state().equals("订单结算")) {
                    tv.setText("已结算");
                    tv.setBackgroundResource(R.drawable.bg_order_state2);
                } else if (dataBean.getOrder_state().equals("订单完成")) {
                    tv.setText("已完成");
                    viewHolder.getTextView(R.id.go).setVisibility(View.VISIBLE);


                }


                viewHolder.getTextView(R.id.go).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
//
                        listener.goS(dataBean.getId());

                    }
                });
                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {

//                        AliWebActivity.openMain(activity,dataBean.getId(),dataBean.getGood_id(),0);

                    }
                });

            }
        };

    }


    //    返利订单
    public static BaseQuickAdapter<FansBean.ResultBean, BaseViewHolder> getTOrder(final Activity activity, final OnGoAddOrder listener) {

        return new BaseQuickAdapter<FansBean.ResultBean, BaseViewHolder>(R.layout.item_fans_order) {
            @Override
            protected void convert(BaseViewHolder viewHolder, final FansBean.ResultBean dataBean, int position) {

                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), dataBean.getPict_url());
                viewHolder.setTextView(R.id.title, dataBean.getGood_title())
                        .setTextView(R.id.fuMoney, "订单总金额：" + dataBean.getAmount_pay() + "元")
                        .setTextView(R.id.time, dataBean.getAdd_time())
                        .setTextView(R.id.type, dataBean.getOrder_type())
                        .setTextView(R.id.ddb, "+" + dataBean.getRe_money())
                        .setTextView(R.id.order_sn, "订单号：" + dataBean.getOrder_sn())
                        .setTextView(R.id.state, "" + dataBean.getOrder_state());    // 购买  待付款


                TextView tv = viewHolder.getView(R.id.state);
                if (dataBean.getOrder_state().equals("订单失效")) {
                    tv.setText("已失效");
                    tv.setBackgroundResource(R.drawable.bg_order_state3);
                    viewHolder.setImageResource(R.id.jinzi, R.mipmap.jinzi1);
                    viewHolder.getTextView(R.id.textView).setTextColor(activity.getResources().getColor(R.color.baen_jinzi));
                    viewHolder.getTextView(R.id.ddb).setTextColor(activity.getResources().getColor(R.color.baen_jinzi));

                } else if (dataBean.getOrder_state().equals("订单付款")) {
                    tv.setText("已付款");
                    tv.setBackgroundResource(R.drawable.bg_order_state);
                } else if (dataBean.getOrder_state().equals("订单结算")) {
                    tv.setText("已结算");
                    tv.setBackgroundResource(R.drawable.bg_order_state2);
                } else if (dataBean.getOrder_state().equals("订单完成")) {
                    tv.setText("已完成");
                    viewHolder.getTextView(R.id.go).setVisibility(View.VISIBLE);


                }


                viewHolder.getTextView(R.id.go).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
//
                        listener.goS(dataBean.getId());

                    }
                });
                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {

//                        AliWebActivity.openMain(activity,dataBean.getId(),dataBean.getGood_id(),0);

                    }
                });

            }
        };

    }


    public interface OnGoAddOrder {

        void goS(String orderId);

    }


    //    我的晒单  可
    public static BaseQuickAdapter<MySBean.ResultBean.OrderInfoBean, BaseViewHolder> getMys(final Activity activity, final OnSOderLintener lintener) {

        return new BaseQuickAdapter<MySBean.ResultBean.OrderInfoBean, BaseViewHolder>(R.layout.item_mys_order) {
            @Override
            protected void convert(BaseViewHolder viewHolder, final MySBean.ResultBean.OrderInfoBean dataBean, int position) {
                viewHolder.setTextView(R.id.title, dataBean.getGood_title())
                        .setTextView(R.id.fuMoney, "订单总金额：" + dataBean.getAmount_pay() + "元")
                        .setTextView(R.id.time, dataBean.getAdd_time());    // 购买  待付款
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), dataBean.getPict_url());
                TextView tv = viewHolder.getView(R.id.state);
                if (dataBean.getOrder_state().equals("订单失效")) {
                    tv.setText("已失效");
                    tv.setBackgroundResource(R.drawable.bg_order_state3);
                } else if (dataBean.getOrder_state().equals("订单付款")) {
                    tv.setText("已付款");
                    tv.setBackgroundResource(R.drawable.bg_order_state);
                } else if (dataBean.getOrder_state().equals("订单结算")) {
                    tv.setText("待结算");
                    tv.setBackgroundResource(R.drawable.bg_order_state2);
                } else if (dataBean.getOrder_state().equals("订单完成")) {
                    tv.setText("已完成");
                    viewHolder.getTextView(R.id.go).setVisibility(View.VISIBLE);
                    tv.setBackgroundResource(R.drawable.bg_order_state1);
                }

                viewHolder.getView(R.id.go).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (lintener != null) lintener.onClick(dataBean);
                    }
                });
                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {

//                        AliWebActivity.openMain(activity,dataBean.getId(),dataBean.getGood_id(),0);

                    }
                });

            }
        };

    }


    public interface OnSOderLintener {

        void onClick(MySBean.ResultBean.OrderInfoBean dataBean);

    }


    //    我的晒单  已
    public static BaseQuickAdapter<MySBean.ResultBean.OrderInfoBean, BaseViewHolder> getMys1(final Activity activity) {

        return new BaseQuickAdapter<MySBean.ResultBean.OrderInfoBean, BaseViewHolder>(R.layout.item_mys_order1) {
            @Override
            protected void convert(BaseViewHolder viewHolder, final MySBean.ResultBean.OrderInfoBean dataBean, int position) {
                viewHolder.setTextView(R.id.title, dataBean.getGood_title())
                        .setTextView(R.id.fuMoney, "订单总金额：" + dataBean.getAmount_pay() + "元")
                        .setTextView(R.id.time, dataBean.getAdd_time());    // 购买  待付款
                setImgB((SimpleDraweeView) viewHolder.getView(R.id.face), dataBean.getPict_url());
                TextView tv = viewHolder.getView(R.id.state);
                if (dataBean.getOrder_state().equals("订单失效")) {
                    tv.setText("已失效");
                    tv.setBackgroundResource(R.drawable.bg_order_state3);
                } else if (dataBean.getOrder_state().equals("订单付款")) {
                    tv.setText("已付款");
                    tv.setBackgroundResource(R.drawable.bg_order_state);
                } else if (dataBean.getOrder_state().equals("订单结算")) {
                    tv.setText("待结算");
                    tv.setBackgroundResource(R.drawable.bg_order_state2);
                } else if (dataBean.getOrder_state().equals("订单完成")) {
                    tv.setText("已完成");
                    viewHolder.getTextView(R.id.go).setVisibility(View.VISIBLE);
                    tv.setBackgroundResource(R.drawable.bg_order_state1);
                }

                viewHolder.getView(R.id.all).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {

//                        AliWebActivity.openMain(activity,dataBean.getId(),dataBean.getGood_id(),0);

                    }
                });

            }
        };

    }


    public static void setImg(SimpleDraweeView mImg, String url) {
        if (url == null) return;
        Uri uri = Uri.parse(url);
        ImageRequest request = null;
        request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(300, 300))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mImg.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .setImageRequest(request).build();
        mImg.setController(controller);
    }


    /**
     * 新手攻略
     *
     * @param activity
     * @return
     */
    public static CommonBaseAdapter<HelpBean.ResultBean> getArticle(final Activity activity) {
        return new CommonBaseAdapter<HelpBean.ResultBean>(activity, R.layout.item_problem) {
            @Override
            protected void convert(CommonViewHolder viewHolder, final HelpBean.ResultBean articleBean, int position) {
                /*viewHolder.setTextView(R.id.serial_number, (position + 1) + ".");
                viewHolder.setTextView(R.id.main_text, articleBean.getDescribe());*/
//                Log.i("tiancao", articleBean.toString());
                SimpleDraweeView image = viewHolder.getView(R.id.teaching_item_sdv);
                FrescoUtils.load(articleBean.getImg_url(), image, 2, 20, R.drawable.ioc_errer_image_c_y);
                viewHolder.getView(R.id.all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        WebActivity.openMain(activity, articleBean.getDescribe(), HttpAPI.HOST + HttpAPI.HELPH5 + articleBean.getColumn_id());
//                        Log.i("tiancao", HttpAPI.HOST + HttpAPI.HELPH5 + articleBean.getColumn_id());

                    }
                });


            }
        };
    }

    /**
     * 常见问题
     *
     * @param activity
     * @return
     */
    public static CommonBaseAdapter<CommonlssueData.ResultBean> getCommonIssueAdapter(final Activity activity) {
        return new CommonBaseAdapter<CommonlssueData.ResultBean>(activity, R.layout.item_common_issue) {
            @Override
            protected void convert(CommonViewHolder viewHolder, final CommonlssueData.ResultBean articleBean, int position) {
                viewHolder.setTextView(R.id.common_issue_text, articleBean.getTitle());
                viewHolder.getView(R.id.item_common_issue_ll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("tiancao", articleBean.getContent());
                        CommonIssueParticularsActivity.openMain(activity, articleBean.getTitle(), articleBean.getContent());
                    }
                });


            }
        };
    }


}
