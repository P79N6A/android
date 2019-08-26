package com.judian.goule.store.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 收益规则
 */
public class EarningsRuleActivity extends BaseActivity {

    @BindView(R.id.earnings_rule_text_tv)
    TextView mEarnigsRule;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnings_rule);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        mEarnigsRule.setText(mText);
    }


    @OnClick({R.id.earnings_rule_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.earnings_rule_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    private String mText = "收益说明\n" +
            "\n" +
            "好友通过您的邀请码注册成为会员后，Ta即永久成为您的会员，未来Ta领券下单时产生的消费佣金均计入您的账户中。关于消费佣金:\n" +
            "1.\t您或您的会员领券下单并确认收货后，您将获得消费佣金；\n" +
            "2.\t每月25日将结算上一个自然月确认收货的订单，结算后的消费佣金将自动计入您的余额中，您可以通过绑定支付宝随时申请提现，申请后收益将在48小时内到账；\n" +
            "3.\t当出现取消订单、退货退款、或者因订单异常等情况时，消费佣金将相应扣除，实际根据系统结算为准。\n" +
            "\n" +
            "名词解析\n" +
            "\n" +
            "1.\t累计收益：累计结算的消费佣金；\n" +
            "2.\t预估收入：上月内创建的所有订单预估收益；\n" +
            "3.\t失效订单：取消付款、退货退款、申请维权或因订单异常，均为失效订单；\n" +
            "4.\t自然月：自然月是指每个月的1号到该月的最后一天。\n" +
            "5.\t上月预估佣金：上个月内确认收货的订单收益，每月25日结算后，将累计到余额中；\n" +
            "6.\t本月预估佣金：本月内创建的所有订单预估收益；\n" +
            "7.\t昨日预估佣金：昨日所有订单预估收益；\n" +
            "8.\t今日预估佣金：今日所有订单预估收益；\n" +
            "9.\t上月付款笔数：上个月内内所有付款的订单数量，包含有效订单和失效订单；\n" +
            "10.\t本月付款笔数：本月内内所有付款的订单数量，包含有效订单和失效订单；\n" +
            "11.\t昨日付款笔数：昨天内所有付款的订单数量，包含有效订单和失效订单；\n" +
            "12.\t今日付款笔数：今天内所有付款的订单数量，包含有效订单和失效订单；\n" +
            "13.\t累计付款笔数：累计所有付款的订单数量，包含有效订单和失效订单。\n";
}
