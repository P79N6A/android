package com.judian.goule.store.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.example.ccy.ccyui.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/13.
 */

public abstract class BaseDelegateAdapter<T> extends DelegateAdapter.Adapter<BaseViewHolder> {

    private LayoutHelper mLayoutHelper;
    private int mCount = -1;
    private int mLayoutId = -1;
    private Context mContext;
    private int mViewTypeItem = -1;
    private LinearLayout mHeaderLayout;
    private LinearLayout mFooterLayout;
    private boolean mLoadMoreEnable = false;
    public boolean mLoading = false;
    private LoadMoreView mLoadMoreView = new SimpleLoadMoreView();
    private RequestLoadMoreListener mRequestLoadMoreListener;

    private boolean mNextLoadEnable = false;
    protected List<T> mData=new ArrayList<>();



    public BaseDelegateAdapter(Context context, LayoutHelper layoutHelper, int layoutId, int count, int viewTypeItem) {
        this.mContext = context;
        this.mCount = count;
        this.mLayoutHelper = layoutHelper;
        this.mLayoutId = layoutId;
        this.mViewTypeItem = viewTypeItem;
    }


    public void loadMoreEnd() {
        loadMoreEnd(false);
    }

    /**
     * Refresh end, no more data
     *
     * @param gone if true gone the load more view
     */
    public void loadMoreEnd(boolean gone) {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        mLoading = false;
        mNextLoadEnable = false;
        mLoadMoreView.setLoadMoreEndGone(gone);
        if (gone) {
//            notifyItemRemoved(getLoadMoreViewPosition());
        } else {
            mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_END);
//            notifyItemChanged(getLoadMoreViewPosition());
        }
    }

    public interface RequestLoadMoreListener {

        void onLoadMoreRequested();

    }
    public int getLoadMoreViewCount() {
        if (mRequestLoadMoreListener == null || !mLoadMoreEnable) {
            return 0;
        }
        if (!mNextLoadEnable && mLoadMoreView.isLoadEndMoreGone()) {
            return 0;
        }
        if (mData.size() == 0) {
            return 0;
        }
        return 1;
    }

    private void autoLoadMore(int position) {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
//        if (position < getItemCount() - mPreLoadNumber) {
//            return;
//        }
        if (mLoadMoreView.getLoadMoreStatus() != LoadMoreView.STATUS_DEFAULT) {
            return;
        }
        mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_LOADING);
        if (!mLoading) {
            mLoading = true;
            if (getRecyclerView() != null) {
                getRecyclerView().post(new Runnable() {
                    @Override
                    public void run() {
                        mRequestLoadMoreListener.onLoadMoreRequested();
                    }
                });
            } else {
                mRequestLoadMoreListener.onLoadMoreRequested();
            }
        }
    }

    private RecyclerView mRecyclerView;
    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }




    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Logger.w("vvvvvvvvv","viewType"+viewType);
//        Logger.w("vvvvvvvvv","mContext == "+mContext);
//
//        Logger.w("vvvvvvvvv","mViewTypeItem"+viewType);
        try {
        if (mViewTypeItem == viewType) {
            return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false));
        }
        return null;
    }catch (Exception e){
        Logger.w("vvvvvvvvv","Exception == "+e);
    }
        return null;
}

    public void onBindViewHolder(BaseViewHolder holder, int position) {

        try {
            convert(holder,mData.get(position),position);
        }catch (Exception e){

        }


    }
    protected abstract void convert(BaseViewHolder helper, T item, int position);


    public void addAll(List<T>  t){
        mData.addAll(t);

    }
    public void addAllN(List<T>  t){
        mData.addAll(t);
        notifyDataSetChanged();

    }
    /**
     * setting up a new instance to data;
     *
     * @param data
     */
    public void setNewData(@Nullable List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        if (mRequestLoadMoreListener != null) {
            mNextLoadEnable = true;
            mLoadMoreEnable = true;
            mLoading = false;
            mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_DEFAULT);
        }

        notifyDataSetChanged();
    }

    public void setNull(){
        mData.clear();
        notifyDataSetChanged();
    }


    @NonNull
    public List<T> getData() {
        return mData;
    }



    public void setNull1(){
        mData.clear();

    }


    /**
     * Gets to load more locations
     *
     * @return
     */
    public int getLoadMoreViewPosition() {
        return   mData.size() + getFooterLayoutCount();
    }

    public int getFooterLayoutCount() {
        if (mFooterLayout == null || mFooterLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }





    /**
     * 必须重写不然会出现滑动不流畅的情况
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mViewTypeItem;
    }

    //条目数量
    @Override
    public int getItemCount() {
        return mData==null?mCount:mData.size();
    }
}