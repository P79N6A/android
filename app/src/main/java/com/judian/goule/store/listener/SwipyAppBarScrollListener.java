package com.judian.goule.store.listener;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.judian.goule.store.utils.DesignViewUtils;

/**
 * Created by Administrator on 2018/3/23.
 */

public class SwipyAppBarScrollListener extends RecyclerView.OnScrollListener implements AppBarLayout.OnOffsetChangedListener {
    private final Lintener lintener;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private ViewGroup refreshLayout;
    private boolean isAppBarLayoutOpen = true;
    private boolean isAppBarLayoutClose;

    public SwipyAppBarScrollListener(AppBarLayout appBarLayout, ViewGroup refreshLayout, RecyclerView recyclerView, Lintener  lintener) {
        this.appBarLayout = appBarLayout;
        this.refreshLayout = refreshLayout;
        this.recyclerView = recyclerView;
        this.lintener = lintener;
        disptachScrollRefresh();
    }


    private void disptachScrollRefresh() {
        if (this.appBarLayout != null && this.recyclerView != null && refreshLayout != null) {
            this.appBarLayout.addOnOffsetChangedListener(this);
            this.recyclerView.addOnScrollListener(this);
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        dispatchScroll();
        if (lintener!=null){
            lintener.onScrolled(recyclerView,dx,dy);
        }
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        isAppBarLayoutOpen = DesignViewUtils.isAppBarLayoutOpen(verticalOffset);
        isAppBarLayoutClose = DesignViewUtils.isAppBarLayoutClose(appBarLayout, verticalOffset);
        dispatchScroll();
    }



    private void dispatchScroll() {
        if (this.recyclerView != null && this.appBarLayout != null && this.refreshLayout != null) {
            //不可滚动
            if ((ViewCompat.canScrollVertically(recyclerView, -1)  || ViewCompat.canScrollVertically(recyclerView, 1))) {

                             refreshLayout.setEnabled(isAppBarLayoutOpen);

            } else//可以滚动
            {
                if (isAppBarLayoutOpen || isAppBarLayoutClose) {

                    if (!ViewCompat.canScrollVertically(recyclerView, -1) && isAppBarLayoutOpen) {

                        refreshLayout.setEnabled(true);
                    } else if (isAppBarLayoutClose && !ViewCompat.canScrollVertically(recyclerView, 1)) {
                        if (recyclerView.getChildCount()!=0){
                            int  m=   recyclerView.getChildCount()*recyclerView.getChildAt(0).getHeight();
                            if (m>recyclerView.getHeight()){
                                refreshLayout.setEnabled(true);
                            }
                        }
                    } else {

                        refreshLayout.setEnabled(false);
                    }
                } else {

                    refreshLayout.setEnabled(false);
                }
            }
        }
    }

    public  interface Lintener {
        void onScrolled(RecyclerView recyclerView, int dx, int dy);

    }

}