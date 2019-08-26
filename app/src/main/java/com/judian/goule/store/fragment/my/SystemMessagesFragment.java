package com.judian.goule.store.fragment.my;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.util.ToastUtils;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.AllGoodsActivity;
import com.judian.goule.store.adapter.CommonBaseAdapter;
import com.judian.goule.store.adapter.CommonViewHolder;
import com.judian.goule.store.adapter.MyAdapterUtil;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.CityBean;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.MyCommentBean;
import com.judian.goule.store.bean.SystemMsgData;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.presenter.MyDataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.ali.auth.third.core.context.KernelContext.getApplicationContext;

/**
 * 系统消息
 */

public class SystemMessagesFragment extends Fragment {
    private RecyclerView mList;
    private PullRefreshLayout system_messages_prl;
    private BaseQuickAdapter<SystemMsgData.ResultBean,BaseViewHolder> mAdapter;
    private MyDataPresenter presenter;
    private RelativeLayout mNoData;
    int page = 1;
    public SystemMessagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system_messages, container, false);
        iniview(view);
        getData();
        return view;
    }

    private void iniview(View view) {
        presenter = new MyDataPresenter(getContext());
        mList = (RecyclerView) view.findViewById(R.id.system_messages_clv);
        mNoData = view.findViewById(R.id.system_messages_rl);
        system_messages_prl = view.findViewById(R.id.system_messages_prl);

        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = MyAdapterUtil.getSystemMessages(getActivity(),new ArrayList<SystemMsgData.ResultBean>());
        mList.setAdapter(mAdapter);



        system_messages_prl.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        system_messages_prl.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page =1;
                getData();

            }

            @Override
            public void onMove(boolean ismove) {

            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                jiazai();
            }
        },mList);
    }

    private void jiazai() {
        ++page;
        presenter.getSystemMessages(page, new BaseView<SystemMsgData>() {
            @Override
            public void result(SystemMsgData bean) {
                if (bean.getCode() == 200) {
                    mAdapter.addAll(bean.getResult());
                    if (bean.getResult().size() < 10) {
                        mAdapter.loadMoreEnd();
                    } else {
                        mAdapter.loadMoreComplete();
                    }
                }else{
                    if (page == 1) {
                        mAdapter.setNull();
                    }
                    mAdapter.loadMoreEnd();
                    ToastUtils.toast(getContext(),bean.getMsg());
                }
            }
            @Override
            public void error() {

            }
        });
    }

    private void getData() {
        system_messages_prl.setRefreshing(false);
        presenter.getSystemMessages(page,new BaseView<SystemMsgData>() {
            @Override
            public void result(SystemMsgData bean) {
                //Log.i("junhao", "回调数据" + bean.toString());
                if(bean.getCode()==200){
                    setui(bean);
                }else{
                    ToastUtils.toast(getContext(),"请求失败");
                }

            }

            @Override
            public void error() {
                Log.i("junhao", "error");
            }
        });
    }

    private void setui(SystemMsgData bean) {
        if(bean.getResult().size()!=0){
            mNoData.setVisibility(View.GONE);
            mAdapter.setNewData(bean.getResult());
        }else {
            mNoData.setVisibility(View.VISIBLE);
        }

    }
}
