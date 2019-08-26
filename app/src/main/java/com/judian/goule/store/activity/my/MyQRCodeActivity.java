package com.judian.goule.store.activity.my;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.Share3Activity;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyQRCodeActivity extends BaseActivity {

    @BindView(R.id.tv_user_name)
    TextView tv_nickName;//昵称
    @BindView(R.id.user_image)
    SimpleDraweeView iv_avater;//头像
    @BindView(R.id.memberTv)
    TextView mVIP;//等级
    @BindView(R.id.iv_my_qrcard)
    ImageView iv_code;//二维码
    @BindView(R.id.my_yq_code_tv)
    TextView mYq_code;//推荐码

    private String shareURLString = "";
    private String shareImageFilepath = "";
    private CdataPresenter presenter;
    private Unbinder bind;
    private MyApplication application;

    private String yq_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qrcode);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        application = (MyApplication) getApplication();
        presenter = new CdataPresenter(this);
        getUserInfo();
    }

    private void getUserInfo() {
        presenter.getUserInfo(new BaseView<UserInfo>() {
            @Override
            public void result(UserInfo bean) {
                if (bean.getCode().equals("200")) {
//                    Log.i("tiancao", bean.toString());
                    tv_nickName.setText(bean.getResult().getNick_name());
                    iv_avater.setImageURI(bean.getResult().getAvatar());
                    mVIP.setText(bean.getResult().getGrade_name());
                    yq_code = bean.getResult().getYq_code();
                    mYq_code.setText(yq_code);
                    String me_url = bean.getResult().getMe_url();//获取个人推广链接
                    String yq_code = bean.getResult().getYq_code();//获取个人推广码
//                    initView(yq_code);
                    initView(me_url);
//                    initView(me_url + "&yq_code=" + yq_code);
                }
            }

            @Override
            public void error() {
                toast("生成二维码失败，请稍后在试");
            }
        });
    }

    private void initView(String me_url) {
//        final String registerUrl = "https://www.baidu.com/";
        final String registerUrl = me_url;
        final String filePath = getFileRoot(this) + File.separator
                + "qr_code" + ".jpg";

        //分享的链接文本
        shareURLString = registerUrl;
        //分享的图片路径
        shareImageFilepath = filePath;


        iv_avater.setDrawingCacheEnabled(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean success = QRCodeUtil.createQRImage(registerUrl, 300, 300, BitmapFactory.decodeResource(getResources(), 0),
                        filePath);

                if (success) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv_code.setImageBitmap(BitmapFactory.decodeFile(filePath));
                            iv_avater.setDrawingCacheEnabled(false);
                            iv_code.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    share();
                                }
                            });
                        }
                    });
                }
            }
        }).start();
    }

    @OnClick({R.id.my_qr_code_back, R.id.my_qr_code_fz_yaoqingma_rl, R.id.my_qr_code_share_poster_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_qr_code_back:
                finish();
                break;
            case R.id.my_qr_code_fz_yaoqingma_rl://复制邀请码
                ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip = ClipData.newPlainText("text", yq_code);
                myClipboard.setPrimaryClip(myClip);
                application.setClipboard(false);
                application.setKefu_weixin(yq_code);
                toast("复制成功");
                break;
            case R.id.my_qr_code_share_poster_rl://分享海报
                startActivity(new Intent(this, Share3Activity.class));
                break;
        }
    }

    //文件存储根目录
    private String getFileRoot(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getExternalFilesDir(null);
            if (external != null) {
                return external.getAbsolutePath();
            }
        }

        return context.getFilesDir().getAbsolutePath();
    }


    //分享的内容
    private void share() {

        final String filePath = getFileRoot(this) + File.separator  //这个是要分享图片路径
                + "pay_qr_code" + ".jpg";
        Bitmap bitmap = getScreenIamge();
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Bitmap getScreenIamge() {
        // View是你须要截图的View
        View view = findViewById(R.id.rl_my_qrcord);
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

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}
