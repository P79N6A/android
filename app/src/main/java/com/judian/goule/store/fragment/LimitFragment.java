package com.judian.goule.store.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LimitFragment extends Fragment {


    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.display)
    LinearLayout display;
    private CdataPresenter presenter;

    public LimitFragment() {
        // Required empty public constructor
    }

    private BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_limit, container, false);
        unbinder = ButterKnife.bind(this, view);

        presenter = new CdataPresenter(getContext());
        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_Bitmap);
        pullRefreshLayout.setOnRefreshListener(onRefreshListener);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = AdapterUtil.getLimitGoods(getActivity(),state);
        recy.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                presenter.getLimitGoods(id, page, goodView);

            }
        });
        shuaxin();
        return view;
    }

    String id,state;

    public void bind(String id,String  state) {
        this.id = id;
        this.state = state;

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


    BaseView<GoodListBean> goodView = new BaseView<GoodListBean>() {
        @Override
        public void result(GoodListBean bean) {
            if (bean.getCode() == 200) {
                if (adapter != null) {
                    adapter.addData(bean.getResult());
                    adapter.loadMoreComplete();
                }
                if (bean.getResult().size() < 10) {
                    if (adapter != null) {
                        adapter.loadMoreEnd();
                    }
                } else {
                    if (adapter != null) {
                        adapter.loadMoreEnd();
                    }
                }
            }

             if (adapter.getItemCount()==0){
                 display.setVisibility(View.VISIBLE);
             }else {
                 display.setVisibility(View.GONE);
             }
        }

        @Override
        public void error() {

            if (adapter != null) {
                adapter.loadMoreFail();
            }

        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void shuaxin() {
        page = 1;
        adapter.setNull();
        pullRefreshLayout.setRefreshing(false);
        presenter.getLimitGoods(id, page, goodView);

    }


}
