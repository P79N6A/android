package com.judian.goule.store.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ccy.ccyui.util.Constants;
import com.judian.goule.store.base.BaseActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
	
	private static final String TAG = "SSSSSSSSSSSSSSSSSSs";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.pay_result);
        
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
		//Log.d(TAG, "onPayFinish, errCode = " + req.toString());
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
		Log.d(TAG, "onPayFinish, errCode = " + req.toString());
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.d(TAG, "onPayFinish, onResp = " + resp.toString());
	}









}