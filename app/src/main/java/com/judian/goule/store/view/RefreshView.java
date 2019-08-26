package com.judian.goule.store.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.androidkun.PullToRefreshRecyclerView;

/**
 * Created by Administrator on 2017/11/15.
 */

public class RefreshView extends PullToRefreshRecyclerView {
    public RefreshView(Context context) {
        super(context);
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setLoadMoreFail() {
        super.setLoadMoreFail();
    }



}
