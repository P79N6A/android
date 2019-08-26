package com.judian.goule.store.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ccy.ccyui.util.ToastUtils;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.GradeBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AgencyActivity extends BaseActivity {

    @BindView(R.id.agency_yxzf_tv)
    TextView mYxzf;
    @BindView(R.id.agency_jjfs_tv)
    TextView mJjfs;
    @BindView(R.id.agency_ztdd_tv)
    TextView mZtdd;
    @BindView(R.id.agency_yjzh_tv)
    TextView mYjzh;

    private CdataPresenter presenter;
    private UserInfo.ResultBean data;
    private Unbinder bind;
    private String yxzf = "";
    private String jjfs = "";
    private String ztdd = "";
    private String yjzh = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency1);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        presenter = new CdataPresenter(this);
        getCondition();
    }

    //获取申请的条件
    private void getCondition() {
        presenter.getGrade(new BaseView<GradeBean>() {
            @Override
            public void result(GradeBean bean) {
                if (bean.getCode() == 200) {
                    List<GradeBean.ResultBean.UserLevelBean> user_level = bean.getResult().getUser_level();
                    if (user_level.size() != 0) {
                        yxzf = user_level.get(0).getYxzf();
                        jjfs = user_level.get(0).getJjfs();
                        ztdd = user_level.get(0).getZtdd();
                        yjzh = user_level.get(0).getYjzh();
                        mYxzf.setText(yxzf);
                        mJjfs.setText(jjfs);
                        mZtdd.setText(ztdd);
                        mYjzh.setText(yjzh);
                    }
                } else {
                    ToastUtils.toast(AgencyActivity.this, bean.getMsg());
                }
            }

            @Override
            public void error() {

            }
        });
    }


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }


    @OnClick({R.id.back, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.commit:
                shengji();
                break;

        }
    }


    private void shengji() {
        presenter.getGradePay(yxzf, jjfs, ztdd, yjzh, new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                if (bean.getCode() == 200) {
                    toast(bean.getMsg());
                    finish();
                }
            }

            @Override
            public void error() {

            }
        });
    }


    @Override
    public void finish() {
        setResult(56);
        super.finish();
    }
}
