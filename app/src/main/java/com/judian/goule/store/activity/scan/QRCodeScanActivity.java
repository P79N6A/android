package com.judian.goule.store.activity.scan;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.judian.goule.store.R;
import com.judian.goule.store.activity.my.ReferralCodeVerificationAcitivy;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.http.HttpAPI;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;


public class QRCodeScanActivity extends BaseActivity {


    private String currentRadioIDString = "0";
    private String currentVersion = "Android";

    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scan);
        setImmersionBar(2);
        CaptureFragment captureFragment = new CaptureFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container_pay, captureFragment).commit();
        CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
//                Log.i("tiancao", "成功的回调" + result);
                Uri uri = Uri.parse(result);
                String yq_code = uri.getQueryParameter("yq_code");
/*                if (result.contains(HttpAPI.HOST)) {
                    String str[] = result.split("/");
                    String tel_base64[] = str[str.length - 1].split("[?]");
                    String tel = new String(Base64.decode(tel_base64[0].getBytes(), Base64.DEFAULT));
                    String otherText = result.split("[?]")[1];
                    if (otherText.split("&").length > 0) {
                        String system = otherText.split("&")[0];
                        if (system.split("=").length > 1) {
                            currentVersion = system.split("=")[1];
                        }
                    }
                    if (otherText.split("&").length > 1) {
                        String radio = otherText.split("&")[1];
                        if (radio.split("=").length > 1) {
                            currentRadioIDString = radio.split("=")[1];
                        }
                    }
                    Log.i("tiancao", "成功的回调tel" + tel);
//                扫描得到结果震动一下表示
                    query(tel);
                }*/
                if (yq_code != null) {
                    if (yq_code.equals("")) {
//                    startActivity(new Intent(QRCodeScanActivity.this, WebViewActivity.class).putExtra("title", "解析结果").putExtra("uri", result).putExtra(WebViewActivity.ISSETBG, true));
                        toast("非法的二维码");
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra(ReferralCodeVerificationAcitivy.CODE_NAME, yq_code);
                        setResult(ReferralCodeVerificationAcitivy.CODESTIAN, intent);
                        finish();
                    }
                } else {
                    toast("非法的二维码");
                }

            }

            @Override
            public void onAnalyzeFailed() {

            }
        };
        captureFragment.setAnalyzeCallback(analyzeCallback);

        findViewById(R.id.qrcode_scan_return_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final ImageView mOpen = findViewById(R.id.open_flashlight);

        mOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    CodeUtils.isLightEnable(false);
                    isOpen = false;
                    mOpen.setImageResource(R.drawable.ioc_rich_scan_off);
                } else {
                    CodeUtils.isLightEnable(true);
                    isOpen = true;
                    mOpen.setImageResource(R.drawable.ioc_rich_scan_on);
                }
            }
        });
    }

    public void query(String keyword) {

    }
}
