package com.judian.goule.store.activity.my;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.KitActivity;
import com.judian.goule.store.activity.SzalipayActivity;
import com.judian.goule.store.activity.WithdrawalsRecordActivity;
import com.judian.goule.store.bean.HomeBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.im.BaseActivity;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的收益
 */
public class MyEarningsActivity extends BaseActivity {

    @BindView(R.id.my_earnings_name)
    TextView tv_nickName;//昵称
    @BindView(R.id.memberIv)
    ImageView miv_vip;//等级图片
    @BindView(R.id.memberTv)
    TextView tv_vip;//等级
    @BindView(R.id.avatar_image)
    SimpleDraweeView iv_avatar;//头像
    @BindView(R.id.my_earnings_yqr_yqm_tv)
    TextView mYqr_yqm;//推广人推广码
    @BindView(R.id.my_earnings_yqr_name_tv)
    TextView mYqr_name;//推广人
    @BindView(R.id.my_earnings_leiji_shouyi_tv)
    TextView mShouyi;//累计收益
    @BindView(R.id.my_earnings_balance_tv)
    TextView mBalance;//待提现

    @BindView(R.id.now_month_commission_tv)
    TextView mNow_month_commission;
    @BindView(R.id.pre_month_commission_tv)
    TextView mPre_month_commission;
    @BindView(R.id.today_commission_tv)
    TextView mToday_commission;
    @BindView(R.id.yestoday_commission_tv)
    TextView mYestoday_commission;

    @BindView(R.id.now_month_payment_num_tv)
    TextView mNow_month_payment_num;
    @BindView(R.id.pre_month_payment_num_tv)
    TextView mPre_month_payment_num;
    @BindView(R.id.today_payment_num_tv)
    TextView mToday_payment_num;
    @BindView(R.id.yestoday_payment_num_tv)
    TextView mYestoday_payment_num;


    private Unbinder bind;
    private CdataPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_earnings);
        bind = ButterKnife.bind(this);
        presenter = new CdataPresenter(this);
        setImmersionBar(2);
        getUserInfo();
        getSouyiData();
    }

    //获取收益报表
    private void getSouyiData() {
        presenter.getHome(new BaseView<HomeBean>() {
            @Override
            public void result(HomeBean bean) {
                if (bean.getCode() == 200) {
                    setui(bean.getResult());
                }
            }

            @Override
            public void error() {

            }
        });
    }

    //设置数据
    private void setui(HomeBean.ResultBean bean) {
        mNow_month_commission.setText("¥ " + bean.getNow_month_commission());
        mPre_month_commission.setText("¥ " + bean.getPre_month_commission());
        mToday_commission.setText("¥ " + bean.getToday_commission());
        mYestoday_commission.setText("¥ " + bean.getYestoday_commission());

        mNow_month_payment_num.setText(bean.getNow_month_payment_num()+" 笔");
        mPre_month_payment_num.setText(bean.getPre_month_payment_num()+" 笔");
        mToday_payment_num.setText(bean.getToday_payment_num()+" 笔");
        mYestoday_payment_num.setText(bean.getYestoday_payment_num()+" 笔");
    }

    //获取用户信心
    private void getUserInfo() {
        presenter.getUserInfo(new BaseView<UserInfo>() {
            @Override
            public void result(UserInfo bean) {
                if (bean.getCode().equals("200")) {
//                    Log.i("tiancao", bean.toString());
                    tv_nickName.setText(bean.getResult().getNick_name());
                    tv_vip.setText(bean.getResult().getGrade_name());
                    iv_avatar.setImageURI(bean.getResult().getAvatar());
                    if (bean.getResult().getGrade_name().equals("合伙人")) {
                        miv_vip.setImageResource(R.drawable.ioc_member);
                    } else {
                        miv_vip.setImageResource(R.mipmap.member);
                    }
                    mYqr_yqm.setText(bean.getResult().getYqr_yqm());
                    mYqr_name.setText(bean.getResult().getYqr_name());
                    mShouyi.setText("¥ " + bean.getResult().getAccount_gold());
                    MyApplication.ali = bean.getResult().getAli_account();
                    mBalance.setText("¥ " + bean.getResult().getWait_gold());
                }
            }

            @Override
            public void error() {

            }
        });
    }


    @OnClick({R.id.my_earnings_back, R.id.my_earnings_qr_rl, R.id.my_earnings_earning_rule_tv, R.id.my_earnings_withdraw_deposit_detail_tv,
            R.id.my_earnings_withdraw_deposit_tv, R.id.my_earnings_account_particulars_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_earnings_back:
                finish();
                break;
            case R.id.my_earnings_earning_rule_tv:
                startActivity(new Intent(this, EarningsRuleActivity.class));
                break;
            case R.id.my_earnings_qr_rl:
                startActivity(new Intent(this, MyQRCodeActivity.class));
                break;
            case R.id.my_earnings_withdraw_deposit_tv://立即提现
                if (MyApplication.ali.equals("")) {
                    startActivity(new Intent(this, SzalipayActivity.class));
                } else {
                    startActivity(new Intent(this, KitActivity.class));
                }
                break;
            case R.id.my_earnings_withdraw_deposit_detail_tv://提现明细
                startActivity(new Intent(this, WithdrawalsRecordActivity.class));
                break;
            case R.id.my_earnings_account_particulars_tv://账户明细
                WithdrawalsRecordActivity.openMain(this, 1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_earnings;
    }
}
