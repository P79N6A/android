package com.judian.goule.store.self;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.AreaListBean;
import com.judian.goule.store.bean.OneGoodsBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressListActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.recy)
    RecyclerView recy;
    public static final String POSITION = "AddressListActivity";
    private OneGoodsBean.ResultBean bean;
    private int option;
    private CdataPresenter presenter;
    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;
    private BaseQuickAdapter<AreaListBean.ResultBean, BaseViewHolder> areaList;

    public static void openMain(Activity context, int option) {
        Intent intent = new Intent(context, AddressListActivity.class);
        intent.putExtra(POSITION, option);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivityForResult(intent, 23);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        ButterKnife.bind(this);
        title.setText("收货地址");
        setImmersionBar(2);
        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_Bitmap);
        pullRefreshLayout.setOnRefreshListener(onRefreshListener);
        recy.setLayoutManager(new LinearLayoutManager(this));


        option = getIntent().getIntExtra(POSITION, 0);
        presenter = new CdataPresenter(this);

        areaList = AdapterUtil.getAreaList(this, new AdapterUtil.AddressListener() {
            @Override
            public void selAddress(AreaListBean.ResultBean bean) {
                if (option == 1) {
                    Intent intent = new Intent();
                    intent.putExtra(OrderDetailActivity.POSITION, bean);
                    setResult(36, intent);
                    finish();
                }

            }
        });
        recy.setAdapter(areaList);
        areaList.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                presenter.getAreaList(page, view);
            }
        });

        shuaxin();
    }

    int page = 1;
    private PullRefreshLayout.OnRefreshListener onRefreshListener = new PullRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            pullRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {

                    shuaxin();
                }
            }, 2000);
        }

        @Override
        public void onMove(boolean ismove) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 23) {
            shuaxin();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @OnClick({R.id.back, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add:
                startActivityForResult(new Intent(AddressListActivity.this, EtActivity.class), 33);
                break;
        }
    }

    private void shuaxin() {
        page = 1;
        pullRefreshLayout.setRefreshing(false);
        areaList.setNull();
        presenter.getAreaList(page, view);

    }

    BaseView<AreaListBean> view = new BaseView<AreaListBean>() {
        @Override
        public void result(AreaListBean bean) {
            if (bean.getCode() == 200) {

                for (int i = 0; i < bean.getResult().size(); i++) {
                    if (bean.getResult().get(i).getState().equals("1")) {
                        areaList.addData(0, bean.getResult().get(i));
                    } else {
                        areaList.addData(bean.getResult().get(i));
                    }
                }
                if (bean.getResult().size() < 10) {
                    areaList.loadMoreEnd();
                } else {
                    areaList.loadMoreComplete();
                }

            } else {
                areaList.loadMoreEnd();
            }
        }

        @Override
        public void error() {
            areaList.loadMoreFail();
        }
    };


}
