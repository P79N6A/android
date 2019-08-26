package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.SignBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.QQShareSelf;
import com.judian.goule.store.view.ShareUrlPopupwindow;
import com.judian.goule.store.views.BaseView;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.sign_gold)
    TextView signGold;
    @BindView(R.id.comment_gold)
    TextView commentGold;

    @BindView(R.id.wcTxt)
    TextView wcTxt;

    @BindView(R.id.zan_integration_s)
    TextView zan_integration_s;

    @BindView(R.id.zan_integration)
    TextView zanIntegration;
    @BindView(R.id.sign_bg)
    LinearLayout signBg;
    @BindView(R.id.sign_wc)
    LinearLayout signWc;

    @BindView(R.id.sappTv)
    TextView sappTv;

    String txt1, txt2;

    @BindView(R.id.all)
    LinearLayout all;
    String shareUrl = "";
    ArrayList<String> list;
    private CdataPresenter mPresenter;

    private ShareUrlPopupwindow mPopupwindow;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_sign);
        bind = ButterKnife.bind(this);
        doBusiness(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.1f)
                .init();

    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        mPresenter.dismiss();
        super.onDestroy();
    }

    public void doBusiness(Context mContext) {
        mTitle.setText("每日签到");
        mPresenter = new CdataPresenter(this);
        mPresenter.getSignList(new BaseView<SignBean>() {
            @Override
            public void result(SignBean bean) {
                if (bean.getCode() == 200) {
                    initView(bean.getResult());
                } else {
                    startActivityForResult(new Intent(SignActivity.this, LoginActivity.class), 12);
                    ToastUtils.toast(SignActivity.this, bean.getMsg());
                }

            }

            @Override
            public void error() {

            }
        });
        txt1 = "全网优惠券大全";
        txt2 = "搜券神器，汇集全网内部优惠券，网购省钱，最高可省90%";

        list = new ArrayList<>();
        list.add(HttpAPI.HOST + "/public/logo.png");
        mPopupwindow = new ShareUrlPopupwindow(this, new ShareUrlPopupwindow.OnShareClickListener() {
            @Override
            public void weixin() {
                WXShare.getInstance(SignActivity.this).shareWX(1, shareUrl);
            }

            @Override
            public void qq() {
//                QQShareSelf.getInstance(SignActivity.this).onClickShare(shareUrl, "http://47.104.193.113/Public/logo.png",txt1,txt2,new BaseUiListener());
                QQShareSelf.getInstance(SignActivity.this).onClickShare(shareUrl, HttpAPI.HOST + "/public/logo.png", txt1, txt2);
            }

            @Override
            public void zone() {
                QQShareSelf.getInstance(SignActivity.this).shareToQzone(shareUrl, list, txt1, txt2);
//                QQShareSelf.getInstance(SignActivity.this).shareToQzone(shareUrl,list,txt1,txt2,new BaseUiListener());
            }

            @Override
            public void pengyou() {
                WXShare.getInstance(SignActivity.this).shareWX(0, shareUrl);
            }
        });

    }

    private void initView(SignBean.ResultBean bean) {
        if (bean.getIs_sign() == 0) {
            signBg.setVisibility(View.VISIBLE);
            signWc.setVisibility(View.GONE);
        } else {
            signWc.setVisibility(View.VISIBLE);
            signBg.setVisibility(View.GONE);
            wcTxt.setText("+" + bean.getGold_info() + "积分");
        }
        signGold.setText(bean.getSign_gold_des());
        commentGold.setText(bean.getComment_integral_des());
        zanIntegration.setText(bean.getComment_gold_des());
        zan_integration_s.setText(bean.getZan_integration_des());
        sappTv.setText(bean.getShareappintegral_des());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, null);


        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.getSignList(new BaseView<SignBean>() {
            @Override
            public void result(SignBean bean) {
                if (bean.getCode() == 200) {
                    initView(bean.getResult());
                } else {
//                    startActivityForResult(new Intent(SignActivity.this, LoginActivity.class),12);
                    ToastUtils.toast(SignActivity.this, bean.getMsg());
                }

            }

            @Override
            public void error() {

            }
        });

    }

    @OnClick({R.id.back, R.id.gosign, R.id.sOrder, R.id.sapp, R.id.comm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.sOrder:
                SorderActivity.openMain(this, 2);
//                startActivity(new Intent(this,SorderActivity.class));
                break;

            case R.id.comm:
                startActivity(new Intent(this, SorderActivity.class));

                break;

//                case R.id.sOrder:
//                startActivity(new Intent(this,SorderActivity.class));
//                break;


            case R.id.sapp:

                new CdataPresenter(this).getShareAPP(new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        if (bean.getCode() == 200) {
                            shareUrl = bean.getResult().getUrl();
                            mPopupwindow.showAtLocation(all, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        } else {
                            ToastUtils.toast(SignActivity.this, bean.getMsg());
                        }

                    }

                    @Override
                    public void error() {

                    }
                });
                break;
            case R.id.gosign:

                mPresenter.getSign(new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        if (bean.getCode() == 200) {
                            signWc.setVisibility(View.VISIBLE);
                            signBg.setVisibility(View.GONE);
                            wcTxt.setText("+" + bean.getResult().getMoney() + "积分");
                            ToastUtils.toast(SignActivity.this, "恭喜抢到" + bean.getResult().getMoney() + "积分");
                        } else {
                            ToastUtils.toast(SignActivity.this, bean.getMsg());
                        }
                    }

                    @Override
                    public void error() {

                    }
                });
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onResume() {

        if (MyApplication.share == 1) {
            new CdataPresenter(this).commitApp();

        }
        MyApplication.share = 0;

        super.onResume();
    }


}
