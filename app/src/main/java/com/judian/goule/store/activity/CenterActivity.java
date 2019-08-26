package com.judian.goule.store.activity;

import android.Manifest;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ccy.ccyui.view.SelfDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.KefuData;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.FrescoUtils;
import com.judian.goule.store.utils.ImgeUtils;
import com.judian.goule.store.views.BaseView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 客服中心
 */
public class CenterActivity extends BaseActivity {

    @BindView(R.id.qqTv)
    TextView qqTv;
    @BindView(R.id.phoneTv)
    TextView phoneTv;
    @BindView(R.id.center_service_code)
    SimpleDraweeView mQr_code_image;
    @BindView(R.id.center_weixin_tv)
    TextView mWeixing;
    @BindView(R.id.center_save_qr_chou_phone_tv)
    TextView mSave_qr_phone;
    private String qq;
    private String phone;
    private Unbinder bind;
    private String weixin;
    private MyApplication application;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        application = (MyApplication) getApplication();
        new CdataPresenter(this).getKefu(new BaseView<KefuData>() {
            @Override
            public void result(KefuData bean) {
                if (bean.getCode().equals("200")) {
                    qq = bean.getResult().getQq();
                    phone = bean.getResult().getPhone();
                    qqTv.setText("QQ客服: " + qq);
                    phoneTv.setText("联系电话: " + phone);
                    weixin = bean.getResult().getWeixin();
                    mWeixing.setText("复制圆宝妹专属微信：" + weixin);
                    FrescoUtils.load(bean.getResult().getWeixin_url(), mQr_code_image);
                }


            }

            @Override
            public void error() {

            }
        });

    }

    @OnClick({R.id.my_center_back, R.id.center_save_qr_chou_phone_tv, R.id.center_weixin_tv, R.id.qqR, R.id.phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_center_back:
                finish();
                break;
            case R.id.center_save_qr_chou_phone_tv://保存相片到手机相机
                //申请权限
                if (Build.VERSION.SDK_INT >= 23) {
                    int REQUEST_CODE_CONTACT = 101;
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    //验证是否许可权限
                    for (String str : permissions) {
                        if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                            //申请权限
                            this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                        }
                    }
                }

                Bitmap bitmap = getSvaeIamge();
                ImgeUtils.saveImageToGallery(this, bitmap);
                toast("保存成功");

                break;
            case R.id.center_weixin_tv://复制微信号
                ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip = ClipData.newPlainText("text", weixin);
                myClipboard.setPrimaryClip(myClip);
                application.setClipboard(false);
                application.setKefu_weixin(weixin);
                toast("复制成功");
                break;
            case R.id.qqR:
                if (checkApkExist(this, "com.tencent.mobileqq")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qq + "&version=1")));
                } else {
                    Toast.makeText(this, "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.phone:
                // 检查是否获得了权限（Android6.0运行时权限）
                if (ContextCompat.checkSelfPermission(CenterActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(CenterActivity.this,
                            Manifest.permission.CALL_PHONE)) {
                        // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                        // 弹窗需要解释为何需要该权限，再次请求授权
                        Toast.makeText(CenterActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    } else {
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(CenterActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                } else {
                    // 已经获得授权，可以打电话
                    dialog(phone);
                }


                break;
        }
    }

    private void dialog(final String tel) {
        final SelfDialog selfDialog = new SelfDialog(this, SelfDialog.TEL);
        selfDialog.setTel(tel);
        selfDialog.setNoOnclickListener(new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.setYesOnclickListener(new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                selfDialog.dismiss();
                callPhone(tel);

            }
        });
        selfDialog.show();
    }
    //拨打电话

    private void callPhone(String tel) {
        Intent intent = new Intent(Intent.ACTION_CALL);

        Uri data = Uri.parse("tel:" + tel);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);


    }

    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    // 处理权限申请的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    dialog(phone);
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

    }

    //保存图片到相册
    private Bitmap getSvaeIamge() {
        // View是你须要截图的View
        View view = findViewById(R.id.center_save_view_rl);
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();

        // 获取view长和高
        int width = view.getWidth();
        int height = view.getHeight();
        Bitmap b = Bitmap.createBitmap(b1, 0, 0, width, height);
        view.destroyDrawingCache();
        return b;
    }

}
