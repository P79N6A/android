package com.judian.goule.store.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.listener.NoDoubleClickListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.activity.youxuan.TetrisActivity;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.SystemMsgData;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.utils.FrescoUtils;
import com.judian.goule.store.utils.Token;

import java.util.List;

/**
 * 新写的适配器
 */

public class MyAdapterUtil {

    /**
     * 我的收藏里面的适配器
     *
     * @param activity
     * @return
     */

    public static com.chad.library.adapter.base.BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> getCollectData(final Activity activity, final MyAdapterUtil.CollectLintener lintener) {
        return new BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder>(R.layout.item_collect) {
            @Override
            protected void convert(final BaseViewHolder viewHolder, final GoodListBean.ResultBean hotBean, int p) {
//                Log.i("tiancao", hotBean.toString());
                viewHolder.setTextView(R.id.pic, "¥ " + hotBean.getPrice());
                UserInfo userInfo = UserInfoDBUtil.get(mContext);
                if (userInfo.getResult().getLevel().equals("6")) {
                    viewHolder.getView(R.id.shengji_zhuan_pic).setVisibility(View.GONE);
                    viewHolder.setTextView(R.id.jifen, "分享赚" + hotBean.getFanli_money_fenxiang());
                } else {
                    viewHolder.setTextView(R.id.shengji_zhuan_pic, "升级赚" + hotBean.getFanli_money_shengji());
                    viewHolder.setTextView(R.id.jifen, "分享赚" + hotBean.getFanli_money_fenxiang());
                }
                switch (hotBean.getUser_type()) {
                    case "0":
                        viewHolder.setTextView(R.id.taobao_and_tianmao, "淘宝价¥" + hotBean.getReserve_price());
                        break;
                    case "1":
                        viewHolder.setTextView(R.id.taobao_and_tianmao, "天猫价¥" + hotBean.getReserve_price());
                        break;

                }
                FrescoUtils.load(hotBean.getPict_url(), (SimpleDraweeView) viewHolder.getView(R.id.face), 2, 20, R.drawable.ioc_errer_image_z_y);
                viewHolder.setText(R.id.title, hotBean.getTitle());

                viewHolder.setText(R.id.quan, "领券减" + hotBean.getCoupon_money());

                if (hotBean.isSel()) {
                    viewHolder.setImageResource(R.id.sel, R.mipmap.proxy_tag);
                } else {
                    viewHolder.setImageResource(R.id.sel, R.mipmap.proxy_tag_de);
                }


                viewHolder.getView(R.id.my_collect_item_box_rl).setOnClickListener(new NoDoubleClickListener() {
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
                //点击进商品详情
                viewHolder.getView(R.id.my_collect_image_view_rl).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TetrisActivity.openMain(activity, hotBean, 6);
                    }
                });
                viewHolder.getView(R.id.my_collect_info_view_ll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TetrisActivity.openMain(activity, hotBean, 6);
                    }
                });

            }
        };
    }

    public interface CollectLintener {

        void add(GoodListBean.ResultBean hotBean);

        void remov(GoodListBean.ResultBean hotBean);

        void share(GoodListBean.ResultBean hotBean);


    }


    /**
     * 系统消息
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<SystemMsgData.ResultBean, BaseViewHolder> getSystemMessages(final Activity activity, List<SystemMsgData.ResultBean> list) {
        return new BaseQuickAdapter<SystemMsgData.ResultBean, BaseViewHolder>(R.layout.item_system_messages, list) {
            @Override
            protected void convert(BaseViewHolder viewHolder, final SystemMsgData.ResultBean hotBean, int position) {

                viewHolder.setTextView(R.id.item_system_messages_title_tv, hotBean.getMsg_title());
                viewHolder.setTextView(R.id.item_system_messages_con_tv, hotBean.getMsg_content());
                viewHolder.setTextView(R.id.item_system_messages_time_tv, hotBean.getSend_time());

            }
        };
    }


    /**
     * 免单商品列表页
     *
     * @param activity
     * @return
     */

    public static BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> getMIandanListData(final Activity activity, List<GoodListBean.ResultBean> list) {
        return new BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder>(R.layout.item_miandan, list) {

            @Override
            protected void convert(BaseViewHolder viewHolder, final GoodListBean.ResultBean hotBean, int position) {

                viewHolder.setTextView(R.id.pic, hotBean.getCoupon_price());
                FrescoUtils.load(hotBean.getPict_url(), (SimpleDraweeView) viewHolder.getView(R.id.face), 2, 20);
                viewHolder.setTextView(R.id.title, "           " + hotBean.getTitle());

                switch (hotBean.getUser_type()) {
                    case "0":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tb21);
                        break;
                    case "1":
                        viewHolder.setImageResource(R.id.type, R.mipmap.tm);
                        break;
                }
                if (hotBean.getCoupon_money().equals("")) {
                    viewHolder.setTextView(R.id.quan, "券 ¥ 0.0");
                } else {
                    viewHolder.setTextView(R.id.quan, "券 ¥ " + hotBean.getCoupon_money());
                }
//                (ProgressBar) viewHolder.getView(R.id.item_miandan_pb);
                viewHolder.setTextView(R.id.item_miandan_numm, "共" + hotBean.getGoods_num() + "件");
                viewHolder.setTextView(R.id.item_miandan_shengyu, "剩余" + hotBean.getGoods_num_surplus() + "件");

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

}
