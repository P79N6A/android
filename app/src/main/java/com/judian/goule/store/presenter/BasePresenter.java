package com.judian.goule.store.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;


import com.example.ccy.ccyui.util.Dialogutils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liang on 2015/9/21.
 */
public abstract class BasePresenter {

     Map<String ,String> params;
    protected Dialog mLoadingDialog;

    private static final String densityKey = "99-k";

   Context  mContext;

    public BasePresenter(Context mContext) {
        params=new HashMap<>();
        this.mContext = mContext;
    }

    protected void initLoadDialog(@NonNull Context context) {

        mLoadingDialog =  Dialogutils.createLoadingDialog(context, "");;
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setCanceledOnTouchOutside(false);

    }

    /**
     * 关闭数据加载框
     */
    public void dismiss() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }
}


