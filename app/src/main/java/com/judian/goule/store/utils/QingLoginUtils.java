package com.judian.goule.store.utils;


import android.content.Context;
import android.widget.Toast;

import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.example.ccy.ccyui.util.ToastUtils;
import com.fanli.ccy.alibaic.AliManage;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.activity.SetingActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;
import com.kepler.jd.login.KeplerApiManager;

/**
 * 交验 用户是否在别的设备登录
 */
public class QingLoginUtils {

    public static void outLogin(final Context context) {
        //清除数据库
        UserInfoDBUtil.delete(context);


        KeplerApiManager.getWebViewService().cancelAuth(context);

        AliManage.logOut(context, new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });

        new CdataPresenter(context).getLogout(new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
            }

            @Override
            public void error() {

            }
        });
        Token.logout();
        MyApplication.ali = "";
        MainActivity.openMain(context, 0);

        ToastUtils.toast(context, "您的账号，已经在其他设备上登录，请重新登录~~");
    }
}
