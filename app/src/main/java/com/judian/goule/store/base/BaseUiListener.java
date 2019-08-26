package com.judian.goule.store.base;

import android.util.Log;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * 这个是QQ登录一个复写类，没有在这里做操作，单必须要复写，QQ 才有回调来
 */
public class BaseUiListener implements IUiListener {
    @Override
    public void onComplete(Object o) {
    }

    @Override
    public void onError(UiError uiError) {
    }

    @Override
    public void onCancel() {
    }
}
