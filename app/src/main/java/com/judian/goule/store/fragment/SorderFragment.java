package com.judian.goule.store.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.OrderDetailsActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.bean.HotOrderBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SorderFragment extends Fragment  {


    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;
    @BindView(R.id.floBtn)
    ImageView mFloBtn;
    Unbinder unbinder;
    private BaseQuickAdapter<HotOrderBean.ResultBean,BaseViewHolder> mHotOrder;
    private CdataPresenter mPresenter;
    private String mType;


    public SorderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sorder, container, false);
        unbinder = ButterKnife.bind(this, view);
//        mList.addItemDecoration(new DividerItemDecoration(
//                getContext(), DividerItemDecoration.BOTH_SET));
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
        mHotOrder = AdapterUtil.getHotOrder(getActivity(), new AdapterUtil.OnSorderListener() {
            @Override
            public void onclick(HotOrderBean.ResultBean hotBean) {
                Intent intent=new Intent(getContext(),OrderDetailsActivity.class);
                intent.putExtra("orderId",hotBean.getId());
                intent.putExtra("goodId",hotBean.getNum_iid());
                startActivityForResult(intent,33);

            }
        });
         mList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

         mList.setAdapter(mHotOrder);
        mHotOrder.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                jiazai();
            }
        });
        mPresenter = new CdataPresenter(getContext());
        mPresenter.getHotOrder(mType,1,mView);

        return view;
    }
    BaseView<HotOrderBean> mView=new BaseView<HotOrderBean>() {
        @Override
        public void result(HotOrderBean bean) {

            if (bean.getCode()==200){
                if (page==1){
                    mHotOrder.setNewData(bean.getResult());
                }else {
                    mHotOrder.addAll(bean.getResult());
                }

              if (bean.getResult().size()<10){
                mHotOrder.loadMoreEnd();
              }else mHotOrder.loadMoreComplete();

            }else {
mHotOrder.loadMoreEnd();


            }

        }

        @Override
        public void error() {
            mHotOrder.loadMoreFail();
        }
    };

  public void bind(String  type){

      mType = type;


  };

    @Override
    public void onResume() {
        super.onResume();
        shuaxin();
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

    int page=1;

    public void shuaxin() {
        if (mHotOrder==null)return;
        pullRefreshLayout.setRefreshing(false);
        mHotOrder.setNewData(null);
        page=1;
        mPresenter.getHotOrder(mType,1,mView);

    }

    public void jiazai() {
        ++page;
        mPresenter.getHotOrder(mType,page,mView);

    }
}
