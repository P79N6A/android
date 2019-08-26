package com.judian.goule.store.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.Constants;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.SpUtils;
import com.example.ccy.ccyui.util.ToastUtils;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.activity.LoginActivity;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.loopj.android.http.RequestParams;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.RegisterActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.WXInfoBean;
import com.judian.goule.store.bean.WXUserBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.presenter.LoginPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.SharePopupwindow;
import com.judian.goule.store.views.BaseView;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;
    @BindView(R.id.stateTv)
    TextView mStateTv;
    @BindView(R.id.stateIv)
    ImageView mStateIv;
    @BindView(R.id.close)
    ImageView mClose;
    @BindView(R.id.all)
    RelativeLayout mAll;


    private String TAG = "bbbbbbbbbb";
    private LoginPresenter loginPresenter;
    private SharePopupwindow mSharePopupwindow;
    private IWXAPI iwxapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //如果没回调onResp，八成是这句没有写
//        WXShare.getInstance(WXEntryActivity.this).handleIntent(getIntent(), this);
        iwxapi = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        iwxapi.handleIntent(getIntent(), this);
        loginPresenter = new LoginPresenter(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.1f)
                .init();
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
//        Log.i("tiancao", "回调"+resp.errCode);
        Intent intent = new Intent();
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
//                Log.i("tiancao", "用户取消");
                if (RETURN_MSG_TYPE_SHARE == resp.getType()) {
//                    setContentView(R.layout.activity_main22);
//                    ButterKnife.bind(this);
                    ToastUtils.toast(this, "分享取消");
//                    mStateTv.setText("分享失败");
//                    mStateIv.setImageResource(R.mipmap.share_dg);
                    MyApplication.share = 2;
                    intent.putExtra("SHARE", 1);
                    setResult(33);
                    finish();
                } else {
                    ToastUtils.toast(this, "登录失败");
                    finish();
                }
                break;
            case BaseResp.ErrCode.ERR_OK://响应成功，请求用户信心
//                Log.i("tiancao", "响应成功");
                switch (resp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) resp).code;

                        Logger.d(TAG, "code = " + code);
                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求
                        RequestParams params = new RequestParams();
                        params.put("appid", Constants.APP_ID);
                        params.put("secret", Constants.APPSECRET);
                        params.put("code", code);
                        params.put("grant_type", "authorization_code");
                        Logger.d(TAG, "params: " + params.toString());
                        HttpClient.getInstance(WXEntryActivity.this).post1(HttpAPI.ACCESS_TOKEN + "?" + params.toString(), null, new RawResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, String response) {
                                Logger.d(TAG, "ACCESS_TOKEN: " + response.toString());
                                WXInfoBean infoBean = new Gson().fromJson(response.toString(), WXInfoBean.class);
                                SpUtils.getInstance(WXEntryActivity.this).putString("accesstoken", infoBean.getAccess_token());
                                getInfo(infoBean);
                            }

                            @Override
                            public void onFailure(int statusCode, String error_msg) {

                            }
                        });
                        break;

                    case RETURN_MSG_TYPE_SHARE:
//
//                        setContentView(R.layout.activity_main22);
//                        ButterKnife.bind(this);
//                        mStateTv.setText("分享成功");
//                        mStateIv.setImageResource(R.mipmap.share_dg1);
//                        mSharePopupwindow.showAtLocation(allll, 0, 0, 0);
//						finish();
//                        intent.putExtra("SHARE",0);
                        ToastUtils.toast(this, "分享成功");
                        MyApplication.share = 1;
                        setResult(33);
                        finish();
                        break;
                }
                break;
        }
    }

    private void getInfo(WXInfoBean str) {
        RequestParams params = new RequestParams();
        params.put("appid", Constants.APP_ID);
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", str.getRefresh_token());
        params.put("access_token", str.getAccess_token());
        params.put("openid", str.getOpenid());
        Logger.d(TAG, "getInfo  params: " + params.toString());
        String url = HttpAPI.USERINFO + "?" + params.toString();
        GetUserInfoTask mGetUserInfoTask = new GetUserInfoTask(new OnGetUserInfoListener() {
            @Override
            public void onGetUserInfo(String userInfo) {

                MyApplication.wxInfo = userInfo;
                MyApplication.wxUser = new Gson().fromJson(userInfo, WXUserBean.class);

                loginPresenter.postWX(MyApplication.wxUser.getUnionid(), userInfo, new BaseView<UserInfo>() {

                    @Override
                    public void result(UserInfo bean) {
                        if (bean.getCode().equals("200")) {
                            Token.setToken(bean.getResult().getToken());
                            Token.setUserId(bean.getResult().getId());
                            MainActivity.openMain(WXEntryActivity.this, 0);
                            finish();
                            //保存数据库
                            UserInfoDBUtil.save(WXEntryActivity.this, bean);
                        } else {
                            RegisterActivity.openMain(WXEntryActivity.this, 5);
                            finish();
                        }
                    }

                    @Override
                    public void error() {
                        finish();
                    }
                });


            }

            @Override
            public void onNetError() {

            }
        }, url); //this代表WXEntryActivity.this
        mGetUserInfoTask.execute();

    }

    @OnClick({R.id.goShare, R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goShare:
                finish();
                WXShare.getInstance(this).reData();

                break;
            case R.id.close:
                finish();
                break;
        }
    }

    static class GetUserInfoTask extends AsyncTask<Object, Void, String> {

        private OnGetUserInfoListener mListener;
        private String mUrl;

        public GetUserInfoTask(OnGetUserInfoListener listener, String getUserInfoUrl) {
            mListener = listener;
            mUrl = getUserInfoUrl;
        }

        @Override
        protected String doInBackground(Object... params) {
            HttpURLConnection conn = null;
            BufferedReader in = null;
            StringBuilder result = new StringBuilder();

            try {
                conn = (HttpURLConnection) new URL(mUrl).openConnection();
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String buf;
                while ((buf = in.readLine()) != null)
                    result.append(buf);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    conn.disconnect();

            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {

            mListener.onGetUserInfo(s);
        }
    }

    public interface OnGetUserInfoListener {

        void onGetUserInfo(String userInfo); //json字符串

        void onNetError();
    }

}