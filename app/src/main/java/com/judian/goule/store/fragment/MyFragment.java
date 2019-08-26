package com.judian.goule.store.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ali.auth.third.core.model.Session;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.example.ccy.ccyui.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fanli.ccy.alibaic.AliManage;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.AgencyActivity;
import com.judian.goule.store.activity.CenterActivity;
import com.judian.goule.store.activity.CommonIssueActivity;
import com.judian.goule.store.activity.IntActivity;
import com.judian.goule.store.activity.NewsActivity;
import com.judian.goule.store.activity.OrderActivity;
import com.judian.goule.store.activity.SetingActivity;
import com.judian.goule.store.activity.Share3Activity;
import com.judian.goule.store.activity.TeamActivity;
import com.judian.goule.store.activity.TeamOrderActivity;
import com.judian.goule.store.activity.my.MyCollectActivity;
import com.judian.goule.store.activity.my.MyEarningsActivity;
import com.judian.goule.store.activity.my.MyQRCodeActivity;
import com.judian.goule.store.activity.my.TeachingActivity;
import com.judian.goule.store.activity.youxuan.MianDanActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.base.BaseFragment;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.MessageEvent;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.listener.HomeClikeListener;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;
import com.umeng.analytics.MobclickAgent;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.img)
    SimpleDraweeView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.memberTv)
    TextView memberTv;
    @BindView(R.id.isLogin)
    RelativeLayout isLogin;
    @BindView(R.id.leiji)
    TextView leiji;
    @BindView(R.id.daifanli)
    TextView daifanli;

    @BindView(R.id.memberIv)
    ImageView memberIv;

    @BindView(R.id.orders)
    TextView orders;
    @BindView(R.id.fans)
    TextView fans;

    @BindView(R.id.tuigL)
    LinearLayout tuigL;


    @BindView(R.id.my_fragment_new_teaching_rl)
    RelativeLayout mTeaching;//新手教程
    @BindView(R.id.my_fragment_upgrade_partner_rl)
    RelativeLayout mUpgrade_partner;//升级合伙人

    private UserInfo.ResultBean mUserinfo;

    private boolean isPartner = false;//是否是合伙人

    private CdataPresenter presenter;

    protected void immersionInit() {
//           if (getActivity()!=null)
//               ImmersionBar.with(getActivity()).fitsSystemWindows(false).transparentStatusBar().init();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        presenter = new CdataPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void hasLogin(boolean login) {
        if (login) { //登录状态
            if (presenter == null) return;
            presenter.getUserInfo(new BaseView<UserInfo>() {
                @Override
                public void result(UserInfo bean) {
                    if (bean.getCode().equals("200")) {
                        setData(bean.getResult());
                    } else {
                        Token.setToken("");
                        hasLogin(Token.isLogin());
                    }
                }

                @Override
                public void error() {

                }
            });

        }

    }

    private void setData(UserInfo.ResultBean result) {
        MobclickAgent.onProfileSignIn(result.getId());
        Token.setAgent(result.getIs_agent());
        mUserinfo = result;
        Token.setFace(result.getAvatar());
        name.setText(result.getNick_name());
        AdapterUtil.setImg(img, result.getAvatar());
//        kfl.setText(result.getGold());//可返利余额
        leiji.setText("¥ " + result.getAccount_gold());
        MyApplication.ali = result.getAli_account();
        daifanli.setText("¥ " + result.getWait_gold());
//        jifeng.setText(result.getAble_score());//积分
        orders.setText("粉丝订单" + result.getOrder_num() + "个");
        fans.setText("粉丝" + result.getEtc() + "人");
        if (result.getGrade_name().equals("合伙人")) {
            isPartner = true;
            memberIv.setImageResource(R.drawable.ioc_member);
            mUpgrade_partner.setVisibility(View.GONE);
        } else {
            isPartner = false;
            memberIv.setImageResource(R.mipmap.member);
            mUpgrade_partner.setVisibility(View.VISIBLE);
        }
        memberTv.setText(result.getGrade_name());
//        TestData.setLevel(result.getLevel(), memberTv, memberIv);
    }

    //退出淘宝
    private void taobao() {
        AliManage.logOut(getContext(), new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });


    }

    /*
       淘宝登录
      */
    private Session session;

    private void taobaoLogin() {
        AliManage.loginTaobao(getContext(), new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {
                session = AlibcLogin.getInstance().getSession();
                MyApplication.session = session;
                presenter.postTaoBaoS(session, new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        ToastUtils.toast(getContext(), bean.getMsg());
                        if (bean.getCode() == 200) {
                            OrderActivity.openMain(getContext(), "", 0);
                        } else if (bean.getCode() == 400) {
                            taobao();
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


    @Override
    public void initData() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        hasLogin(Token.isLogin());
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 45) {
            hasLogin(true);
        }
    }

    @OnClick({R.id.img, R.id.name, R.id.shouyi, R.id.memberTv, R.id.member, R.id.teamO, R.id.fansL, R.id.my_fragment_leiji_shoyi_rl, R.id.my_fragment_dai_fanli_rl,
            R.id.tuig, R.id.msg, R.id.set, R.id.my_fragment_rode_centre_rl, R.id.my_fragment_my_earnings_rl, R.id.my_fragment_my_team_rl,
            R.id.my_fragment_my_rq_code_rl, R.id.my_fragment_upgrade_partner_rl,
            R.id.my_fragment_new_teaching_rl, R.id.my_fragment_invitation_code_rl,
            R.id.my_fragment_call_service_rl, R.id.my_fragment_my_collect_rl, R.id.my_fragment_issue_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.name:

                break;
            case R.id.teamO:
                startActivity(new Intent(getContext(), TeamOrderActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));

                break;
            case R.id.my_fragment_leiji_shoyi_rl://累计收益
            case R.id.my_fragment_dai_fanli_rl://待返利
                startActivity(new Intent(getContext(), MyEarningsActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;

            /*

                 //会员升级
                startActivity(new Intent(getContext(), GradeActivity.class));
                //自营订单
                RecyActivity.openMain(getContext(), 10);
                //英雄傍
                RecyActivity.openMain(getContext(),2);
                //粉丝订单
                startActivity(new Intent(getContext(), TeamOrderActivity.class));
                //旧的收益报表
                startActivity(new Intent(getContext(), EarActivity.class));
                */

            case R.id.fansL:
                startActivity(new Intent(getContext(), TeamActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));//我的团队
                break;

            case R.id.memberTv:

                break;

            case R.id.shouyi:
                break;
            case R.id.member:
                if (!isPartner) {
                    startActivity(new Intent(getContext(), AgencyActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                }
                break;
            case R.id.msg:
                startActivity(new Intent(getContext(), NewsActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
            case R.id.set:
                startActivityForResult(new Intent(getContext(), SetingActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT), 45);
                break;
            case R.id.img:
                startActivityForResult(new Intent(getContext(), SetingActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT), 45);
                break;
            case R.id.tuig:
                if (listener != null) {
                    listener.onClike(1);
                }
//                    startActivity(new Intent(getContext(), WeAreActivity.class));
                break;
            case R.id.my_fragment_my_rq_code_rl://我的二维码
                startActivity(new Intent(getContext(), MyQRCodeActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
            case R.id.my_fragment_rode_centre_rl://订单中心
                    OrderActivity.openMain(getContext(), "", 0);
                /*if (AlibcLogin.getInstance().isLogin()) {
                } else {
                    taobaoLogin();
                }*/
                break;
            case R.id.my_fragment_my_earnings_rl://我的收益
                startActivity(new Intent(getContext(), MyEarningsActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
            case R.id.my_fragment_my_team_rl://我的团队
                startActivity(new Intent(getContext(), TeamActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
            case R.id.my_fragment_my_integral_rl://积分中心
                startActivity(new Intent(getContext(), IntActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
            case R.id.my_fragment_invitation_code_rl://邀请海报
                startActivity(new Intent(getContext(), Share3Activity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
            case R.id.my_fragment_upgrade_partner_rl://成为合伙人入口
                startActivity(new Intent(getContext(), AgencyActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
            case R.id.my_fragment_new_teaching_rl://新手教程
                startActivity(new Intent(getContext(), TeachingActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
            case R.id.my_fragment_call_service_rl://联系客服
                startActivity(new Intent(getContext(), CenterActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
            case R.id.my_fragment_my_collect_rl://我的收藏
                startActivity(new Intent(getContext(), MyCollectActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
            case R.id.my_fragment_issue_rl://常见问题
//                startActivity(new Intent(getContext(), CommonIssueActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                startActivity(new Intent(getContext(), MianDanActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                break;
        }
    }

    public void setListener(HomeClikeListener listener) {
        this.listener = listener;
    }

    HomeClikeListener listener;

}
