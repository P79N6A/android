package com.judian.goule.store.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.util.Logger;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.AddOrderActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.bean.FansBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * 返利订单
 */
public class TeamAgentFragment extends Fragment {
    @BindView(R.id.msg)
    TextView msg;

    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;
    private BaseQuickAdapter<FansBean.ResultBean,BaseViewHolder> adapter;
    @BindView(R.id.floBtn)
    ImageView floBtn;
    Unbinder unbinder;
    private String type;

    private CdataPresenter presenter;


    public TeamAgentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new CdataPresenter(getContext());

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

        msg.setText("没有订单信息");
        adapter = AdapterUtil.getTOrder(getActivity(), new AdapterUtil.OnGoAddOrder() {
            @Override
            public void goS(String orderId) {

                Intent intent=new Intent(getContext(), AddOrderActivity.class);
                        intent.putExtra("ORDERID",orderId);
                        startActivityForResult(intent,46);

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
                    floBtn.setVisibility(View.VISIBLE);
                }else {
                    floBtn.setVisibility(View.GONE);
                }

            }
        });
        mList.setLayoutManager(new LinearLayoutManager(getContext()));

        mList.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                jiazai();
            }
        });
           presenter.getTeamOrder(type,1,baseView);
        return view;
    }
    BaseView<FansBean> baseView=new BaseView<FansBean>() {
        @Override
        public void result(FansBean bean) {
              if (msg==null)return;
             if (bean.getCode()==200&&bean.getResult()!=null){
                 if (bean.getResult().size()<10){
                     adapter.loadMoreEnd();
                 }else {
                     adapter.loadMoreComplete();
                 }
                 Logger.e("fffffff","adapter == "+adapter.getItemCount());
                 adapter.addAllN(bean.getResult());
                 Logger.e("fffffff","adapter == "+adapter.getItemCount());
             }else {
                 adapter.loadMoreEnd();
//                 ToastUtils.toast(getContext(),bean.getMsg());
             }

            if(adapter.getData().size()==0){
                msg.setVisibility(View.VISIBLE);
            }else {
                msg.setVisibility(View.GONE);
            }
        }

        @Override
        public void error() {
            adapter.loadMoreFail();
            if(adapter.getData().size()==0){
                msg.setVisibility(View.VISIBLE);
            }else {
                msg.setVisibility(View.GONE);
            }

        }
    };



    public void bind(String ss) {
        type = ss;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.floBtn)
    public void onViewClicked() {
        mList.smoothScrollToPosition(0);

    }


    public void shuaxin() {
        if (adapter==null)return;
        pullRefreshLayout.setRefreshing(false);
        adapter.setNull();
        presenter.getTeamOrder(type,1,baseView);

    }
int  page=1;

    public void jiazai() {
          page++;
        presenter.getTeamOrder(type,page,baseView);

    }

}
