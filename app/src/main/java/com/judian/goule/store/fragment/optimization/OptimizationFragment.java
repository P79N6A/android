package com.judian.goule.store.fragment.optimization;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.judian.goule.store.R;
import com.judian.goule.store.adapter.optimization.OptimizationAdapter;
import com.judian.goule.store.bean.OptimizationData;
import com.judian.goule.store.bean.YouXuanData;
import com.judian.goule.store.db.liteorm.YouXuanDataDBUtil;
import com.judian.goule.store.presenter.HelpPresenter;
import com.judian.goule.store.views.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * 优选
 */
public class OptimizationFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipe;
    private ListView mList;
    private View footview;
    private List<YouXuanData> mData = new ArrayList<>();
    private OptimizationAdapter adapter;

    public OptimizationFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        /*OptimizationData youXuanData = YouXuanDataDBUtil.getYouXuanData(getContext());
        if (youXuanData != null) {
            if (youXuanData.getCode() == 200) {
                mData.clear();
                mData.addAll(youXuanData.getResult());
                adapter.notifyDataSetChanged();
            } else {
                getData();
            }
        } else {
            getData();
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_we_are, container, false);
        footview = inflater.inflate(R.layout.optimization_item_foot, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSwipe = (SwipeRefreshLayout) view.findViewById(R.id.optimization_srl);
        mSwipe.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light
        );
        mSwipe.setOnRefreshListener(this);
        mList = (ListView) view.findViewById(R.id.list_data_lv);

        adapter = new OptimizationAdapter(getContext(), mData);
        mList.setAdapter(adapter);
        mList.addFooterView(footview);
        getData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
         //显示的时候处理你要更新的数据

        } else {  //隐藏的时候一般没什么要处理的

        }
    }

    private void getData() {
        new HelpPresenter(getContext()).getYouxuan(new BaseView<OptimizationData>() {
            @Override
            public void result(OptimizationData bean) {
                mSwipe.setRefreshing(false);
                if (bean.getCode() == 200) {
                    mData.clear();
                    mData.addAll(bean.getResult());
                    adapter.notifyDataSetChanged();
                    YouXuanDataDBUtil.saveYouXuanData(getContext(), bean);
//                    Log.i("tiancao", "保存成功" + YouXuanDataDBUtil.getYouXuanData(getContext()));
                }
            }

            @Override
            public void error() {

            }
        });
    }

    //下拉刷新回调
    @Override
    public void onRefresh() {
        getData();
    }
}
