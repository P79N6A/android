package com.judian.goule.store.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.view.DividerItemDecoration;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.TeamMxBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeamActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.ren)
    TextView ren;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.oneTv)
    TextView oneTv;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.twoTv)
    TextView twoTv;
    @BindView(R.id.list)
    RecyclerView mList;
    private CdataPresenter presenter;
    private BaseQuickAdapter<TeamMxBean.ResultBean.DataBean, BaseViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        ButterKnife.bind(this);
        presenter = new CdataPresenter(this);
        setImmersionBar(2);
        presenter.getMeTeam(new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                if (bean.getCode() == 200) {
                    ren.setText("累计粉丝" + bean.getResult().getNum() + "人");
                    money.setText(bean.getResult().getTotal());
                }
            }

            @Override
            public void error() {

            }
        });

        initView();
    }

    BaseView<TeamMxBean> view = new BaseView<TeamMxBean>() {
        @Override
        public void result(TeamMxBean bean) {
            if (bean.getCode() == 200) {
                one.setText(bean.getResult().getRe_num() + "人");
                two.setText(bean.getResult().getTwo_num() + "人");
                mAdapter.addData(bean.getResult().getData());
                mAdapter.loadMoreComplete();
                if (bean.getResult().getData().size() < 10) {
                    mAdapter.loadMoreEnd();
                }

            } else {
                mAdapter.loadMoreEnd();
            }
        }

        @Override
        public void error() {
            mAdapter.loadMoreFail();
        }
    };

    private int mPage;
    private String mType;

    private void initView() {

        mAdapter = new BaseQuickAdapter<TeamMxBean.ResultBean.DataBean, BaseViewHolder>(R.layout.item_my_team) {
            @Override
            protected void convert(BaseViewHolder helper, TeamMxBean.ResultBean.DataBean item, int p) {
                helper.setText(R.id.time, item.getAdd_time())
                        .setText(R.id.name, item.getRe_nickname())
                        .setText(R.id.bass, item.getUp_user_name())
                        .setText(R.id.money, item.getTotal() + "元");
            }
        };

        mList.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));
        mPage = 1;
        presenter.getMxList(mPage, "1", view);
        mAdapter.setOnLoadMoreListener(this);
        mList.setFocusable(false);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);
    }


    @OnClick({R.id.back, R.id.oneF, R.id.twoF})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.oneF:
                shuaxin("1");
                oneTv.setVisibility(View.VISIBLE);
                twoTv.setVisibility(View.GONE);
                break;
            case R.id.twoF:
                shuaxin("2");
                twoTv.setVisibility(View.VISIBLE);
                oneTv.setVisibility(View.GONE);
                break;

        }
    }


    private void shuaxin(String mType) {
        mPage = 1;
        mAdapter.setNewData(null);
        presenter.getMxList(mPage, mType, view);

    }

    @Override
    public void onLoadMoreRequested() {
        ++mPage;
        presenter.getMxList(mPage, mType, view);
    }
}
