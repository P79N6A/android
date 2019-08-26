package com.judian.goule.store.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.bean.MySBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyS1Fragment extends Fragment  {



    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;

    @BindView(R.id.floBtn)
    ImageView mFloBtn;
    Unbinder unbinder;
    @BindView(R.id.diaOrder)
    LinearLayout mDiaOrder;
    private String mType;
    private BaseQuickAdapter<MySBean.ResultBean.OrderInfoBean,BaseViewHolder> mAdapter;
    private CdataPresenter mPresenter;

    public MyS1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        mAdapter = AdapterUtil.getMys1(getActivity());
        mPresenter = new CdataPresenter(getContext());
        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        mList.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                jiazai();
            }
        });
        mList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            private int totalDy = 0;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy>1000){
                    mFloBtn.setVisibility(View.VISIBLE);
                }else {
                    mFloBtn.setVisibility(View.GONE);
                }

            }
        });
        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuaxin();
            }

            @Override
            public void onMove(boolean ismove) {

            }
        });
        mPresenter.getMysorder(mType, page, mView);
        return view;
    }


    BaseView<MySBean> mView = new BaseView<MySBean>() {
        @Override
        public void result(MySBean bean) {

            if (bean.getCode() == 200) {
                if (listener != null) {
                    listener.num(bean.getResult().getTotal());
                }

                mAdapter.addAll(bean.getResult().getOrder_info());
                   if (bean.getResult().getOrder_info().size()<10){
                       mAdapter.loadMoreEnd();
                   }else  mAdapter.loadMoreComplete();




            } else {

                mAdapter.loadMoreEnd();


            }

            if (mAdapter.getItemCount()==0){
                mDiaOrder.setVisibility(View.VISIBLE);
            }else {
                mDiaOrder.setVisibility(View.GONE);
            }
        }

        @Override
        public void error() {
            if (mAdapter.getItemCount()==0){
                mDiaOrder.setVisibility(View.VISIBLE);
            }else {
                mDiaOrder.setVisibility(View.GONE);
            }

            mAdapter.loadMoreFail();
        }
    };


    public void bind(String type) {
        mType = type;


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    int page = 1;


    public void shuaxin() {
        pullRefreshLayout.setRefreshing(false);
        mAdapter.setNull();
        page = 1;
        mPresenter.getMysorder(mType, page, mView);


    }

    public void jiazai() {
        page++;
        mPresenter.getMysorder(mType, page, mView);
    }


    public void onScrollY(int scrollY) {
        if (scrollY > 200) {
            mFloBtn.setVisibility(View.VISIBLE);
        } else {
            mFloBtn.setVisibility(View.GONE);
        }
    }

    public void setListener(NumListener listener) {
        this.listener = listener;
    }

    NumListener listener;

    @OnClick({R.id.gos, R.id.floBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gos:
                if (listener!=null)
                listener.onGos();
                break;
            case R.id.floBtn:
                mList.smoothScrollToPosition(0);
                break;
        }
    }

    public interface NumListener {

        void num(String num);
        void  onGos();

    }
}
