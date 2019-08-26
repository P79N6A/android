package com.judian.goule.store.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.auth.third.core.model.Session;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.example.ccy.ccyui.view.SelectPhotoPopupwindow;
import com.example.ccy.ccyui.view.SelfFeeDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.judian.goule.store.activity.my.AboutUsActivity;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.bean.UpgradeData;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.presenter.MyDataPresenter;
import com.judian.goule.store.self.AddressListActivity;
import com.kepler.jd.login.KeplerApiManager;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.utils.QQShareSelf;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.ApkUpdateUtils;
import com.example.ccy.ccyui.util.CacheManager;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.SelfDialog;
import com.fanli.ccy.alibaic.AliManage;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.ShareUrlPopupwindow;
import com.judian.goule.store.views.BaseView;
import com.tencent.tauth.Tencent;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SetingActivity extends TakePhotoActivity implements BaseView<UserInfo> {
    @BindView(R.id.memTv)
    TextView memTv;

    @BindView(R.id.set_gx)
    TextView set_gx;

    @BindView(R.id.all)
    LinearLayout all;
    @BindView(R.id.seting_new_version_code_iv)
    ImageView mNew_version_code;


    @BindView(R.id.userImg)
    SimpleDraweeView userImg;
    @BindView(R.id.nickname_tv)
    TextView nicknameTv;
    @BindView(R.id.tasobaoTv)
    TextView tasobaoTv;
    @BindView(R.id.ali_tv)
    TextView ali_tv;
    @BindView(R.id.addree_tv)
    TextView addree_tv;


    private String size;
    private String version;
    private ShareUrlPopupwindow mPopupwindow;
    private Unbinder bind;


    private SelectPhotoPopupwindow photoPopupwindow;
    private CdataPresenter presenter;
    private RadioGroup rgCrop, rgCompress, rgFrom, rgCropSize, rgCropTool, rgShowProgressBar, rgPickTool, rgCompressTool, rgCorrectTool, rgRawFile;


    private boolean isImg = false;
    private UserInfo.ResultBean user;
    private String mPhone;
    private SelfFeeDialog nickDialog;
    private MyDataPresenter myDataPresenter;
    private String tb_nickname = "";//淘宝号


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    String txt1, txt2;

    String shareUrl = "";
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting);
        bind = ButterKnife.bind(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(false)
                .init();
        init();

        UserInfo userInfo = UserInfoDBUtil.get(this);
        user = userInfo.getResult();

        presenter = new CdataPresenter(this);
        myDataPresenter = new MyDataPresenter(this);
        photo();
        nickDialog = new SelfFeeDialog(this);
        nickDialog.setNoOnclickListener(new SelfFeeDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                nickDialog.dismiss();
            }
        });
        nickDialog.setYesOnclickListener(new SelfFeeDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                save(nickDialog.getTxt());
                nickDialog.dismiss();
            }
        });

        count();
        txt1 = "全网优惠券大全";
        txt2 = "搜券神器，汇集全网内部优惠券，网购省钱，最高可省90%";

        list = new ArrayList<>();
        list.add(HttpAPI.HOST + "/public/logo.png");
        PackageManager pm = this.getPackageManager();//得到PackageManager对象

        try {
            PackageInfo pi = pm.getPackageInfo(this.getPackageName(), 0);//得到PackageInfo对象，封装了一些软件包的信息在里面
            //获取清单文件中versionCode节点的值
            version = pi.versionName;
            set_gx.setText("当前版本:" + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        getVer();
        mPopupwindow = new ShareUrlPopupwindow(this, new ShareUrlPopupwindow.OnShareClickListener() {
            @Override
            public void weixin() {
                WXShare.getInstance(SetingActivity.this).shareWX(1, shareUrl);
            }

            @Override
            public void qq() {
                QQShareSelf.getInstance(SetingActivity.this).onClickShare(shareUrl, HttpAPI.HOST + "/public/logo.png", txt1, txt2);
            }

            @Override
            public void zone() {
                QQShareSelf.getInstance(SetingActivity.this).shareToQzone(shareUrl, list, txt1, txt2);
            }

            @Override
            public void pengyou() {
                WXShare.getInstance(SetingActivity.this).shareWX(0, shareUrl);
            }
        });


    }


    int option = 0;
    private String url;

    private void getVer() {

        new CdataPresenter(this).getVersions(version, new BaseView<UpgradeData>() {

            @Override
            public void result(UpgradeData bean) {
                String versionName = "";
                int versionCode = 0;
                try {
                    PackageManager pm = getPackageManager();
                    PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
                    versionName = pi.versionName;
                    versionCode = pi.versionCode;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (bean.getVersionCode() == versionCode) {
                    ToastUtils.toast(SetingActivity.this, "已经是最新版本了");
                    mNew_version_code.setVisibility(View.GONE);
                    return;
                }
                if (bean.getVersionCode() > versionCode) {
                    url = bean.getDown_link();
                    mNew_version_code.setVisibility(View.VISIBLE);
                } else {
                    mNew_version_code.setVisibility(View.GONE);
                }
            }

            @Override
            public void error() {
                mNew_version_code.setVisibility(View.GONE);
            }
        });


    }


    @OnClick({R.id.set_topIv, R.id.set_empty, R.id.set_tickling, R.id.share, R.id.help, R.id.update, R.id.logout,
            R.id.userIcon, R.id.ali, R.id.tasobao, R.id.nickname, R.id.addree, R.id.pass})
    public void onViewClicked(View view) {
        configCompress(getTakePhoto());
        configTakePhotoOption(getTakePhoto());
        switch (view.getId()) {
            case R.id.set_topIv:
                finish();
                break;


            case R.id.btnPickBySelect:
                Log.i("tiancao", "相册");
                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                int limit = Integer.parseInt("1");
                if (limit > 1) {
                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                        getTakePhoto().onPickMultipleWithCrop(limit, getCropOptions());
                    } else {
                        getTakePhoto().onPickMultiple(limit);
                    }
                    return;
                }
                if (rgFrom.getCheckedRadioButtonId() == R.id.rbFile) {
                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                        getTakePhoto().onPickFromDocumentsWithCrop(imageUri, getCropOptions());
                    } else {
                        getTakePhoto().onPickFromDocuments();
                    }
                    return;
                } else {
                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                        getTakePhoto().onPickFromGalleryWithCrop(imageUri, getCropOptions());
                    } else {
                        getTakePhoto().onPickFromGallery();
                    }
                }
                break;
            case R.id.btnPickByTake:
                File file1 = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file1.getParentFile().exists()) file1.getParentFile().mkdirs();
                Uri imageUri1 = Uri.fromFile(file1);
                if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                    getTakePhoto().onPickFromCaptureWithCrop(imageUri1, getCropOptions());
                } else {
                    getTakePhoto().onPickFromCapture(imageUri1);
                }
                break;
            case R.id.userIcon:
                photoPopupwindow.showAtLocation(all, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.nickname:
                nickDialog.show();

                break;
            case R.id.ali:

                if (MyApplication.ali.equals("")) {
                    startActivityForResult(new Intent(this, SzalipayActivity.class), 15);
                } else {
                    AliinfoActivity.openMain(this, user.getAli_account(), user.getName());
                }

                break;

            case R.id.tasobao://切换淘宝账号
                if (tb_nickname.equals("")) {
                    AliManage.logOut(this, new AlibcLoginCallback() {
                        @Override
                        public void onSuccess(int i) {
                            taobaoLogin();
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                } else {
                    taobao();
                }
                break;
            case R.id.pass:
                startActivity(new Intent(this, UserActivity.class));
                break;
            case R.id.addree:
                startActivityForResult(new Intent(this, AddressListActivity.class), 12);
                break;


            case R.id.set_empty://清空缓存
                if (size.equals("0K")) {

                } else {
                    dialog();
                }
                break;

            case R.id.share://分享应用
                new CdataPresenter(this).getShareAPP(new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        if (bean.getCode() == 200) {
                            shareUrl = bean.getResult().getUrl();
                            mPopupwindow.showAtLocation(all, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        } else {
                            ToastUtils.toast(SetingActivity.this, bean.getMsg());
                        }

                    }

                    @Override
                    public void error() {

                    }
                });


                break;
            case R.id.logout://
                logout();
                break;
            case R.id.set_tickling:
//                startActivity(new Intent(SetingActivity.this, TicklingActivity.class));

                break;
            case R.id.help://使用说明

                startActivity(new Intent(this, AboutUsActivity.class));
                /*try {
                    MyApplication.share = 6;
                    finish();
                } catch (Exception e) {
                    Logger.e("ddddddd", "SetingActivity  Exception == " + e);
                }*/

                break;
            case R.id.update://检查更新
                if (url != null) {
                    ApkUpdateUtils.openBrowser(getApplicationContext(), url);
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, null);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 退出淘宝
     */
    private void taobao_logout() {
        AliManage.logOut(this, new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

    /**
     * 应用退出
     */
    private void logout() {
        //清除数据库
        UserInfoDBUtil.delete(this);


        KeplerApiManager.getWebViewService().cancelAuth(this);

        AliManage.logOut(this, new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });

        new CdataPresenter(this).getLogout(new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                ToastUtils.toast(SetingActivity.this, bean.getMsg());
            }

            @Override
            public void error() {

            }
        });
        Token.logout();
        MyApplication.ali = "";
        MainActivity.openMain(SetingActivity.this, 0);

        finish();
    }


    private void dialog() {
        final SelfDialog selfDialog = new SelfDialog(this);
        selfDialog.setNoOnclickListener(new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.setYesOnclickListener(new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                CacheManager.clearAllCache(getApplicationContext());
                count();
                selfDialog.dismiss();

            }
        });
        selfDialog.show();
    }

    private void count() {
        try {
            size = CacheManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        memTv.setText(size);
    }

    private void initDownManager() {
        if (!canDownloadState()) {
            Toast.makeText(this, "下载服务不可用,请您启用", Toast.LENGTH_SHORT).show();
            showDownloadSetting();
            return;
        }
        ApkUpdateUtils.download(this, url, getResources().getString(R.string.app_name));
    }

    private void showDownloadSetting() {
        String packageName = getPackageName();
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        if (intentAvailable(intent)) {
            startActivity(intent);
        }
    }

    private boolean intentAvailable(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private boolean canDownloadState() {
        try {
            int state = this.getPackageManager().getApplicationEnabledSetting(getPackageName());
            if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    protected void onResume() {
        Logger.e("ddddddd", "SetingActivity  onResume");
        if (MyApplication.share == 1) {
            new CdataPresenter(this).commitApp();

        }
        MyApplication.share = 0;
        presenter.getUserInfo(this);
        super.onResume();
    }


    private void save(String name) {
        if (name.equals("")) {
            ToastUtils.toast(this, "请输入昵称");
        } else {
            presenter.getName(name, new BaseView<BaseBean>() {
                @Override
                public void result(BaseBean bean) {
                    ToastUtils.toast(SetingActivity.this, bean.getMsg());
                    if (bean.getCode() == 200) {
                        nicknameTv.setText(bean.getResult().getNick_name());
                    }
                }

                @Override
                public void error() {

                }
            });

        }


    }


    private void photo() {
        photoPopupwindow = new SelectPhotoPopupwindow(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //拍照
                    case R.id.btn_take_photo:
                        v.setId(R.id.btnPickByTake);
                        onViewClicked(v);
                        photoPopupwindow.dismiss();
                        photo();

                        break;
                    //相册
                    case R.id.btn_pick_photo:
                        photoPopupwindow.dismiss();
                        photo();
                        v.setId(R.id.btnPickBySelect);
                        onViewClicked(v);

                        break;

                }
            }
        }, SelectPhotoPopupwindow.PHOTO);

        photoPopupwindow.dismiss();
    }


    private EditText etCropHeight, etCropWidth, etLimit, etSize, etHeightPx, etWidthPx;

    private void init() {
        rgCrop = (RadioGroup) findViewById(R.id.rgCrop);
        rgCompress = (RadioGroup) findViewById(R.id.rgCompress);
        rgCompressTool = (RadioGroup) findViewById(R.id.rgCompressTool);
        rgCropSize = (RadioGroup) findViewById(R.id.rgCropSize);
        rgFrom = (RadioGroup) findViewById(R.id.rgFrom);
        rgPickTool = (RadioGroup) findViewById(R.id.rgPickTool);
        rgRawFile = (RadioGroup) findViewById(R.id.rgRawFile);
        rgCorrectTool = (RadioGroup) findViewById(R.id.rgCorrectTool);
        rgShowProgressBar = (RadioGroup) findViewById(R.id.rgShowProgressBar);
        rgCropTool = (RadioGroup) findViewById(R.id.rgCropTool);
        etCropHeight = (EditText) findViewById(R.id.etCropHeight);
        etCropWidth = (EditText) findViewById(R.id.etCropWidth);
        etLimit = (EditText) findViewById(R.id.etLimit);
        etSize = (EditText) findViewById(R.id.etSize);
        etHeightPx = (EditText) findViewById(R.id.etHeightPx);
        etWidthPx = (EditText) findViewById(R.id.etWidthPx);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImages());
    }

    private void showImg(ArrayList<TImage> images) {


        Log.d("fffff", "showImg: " + images.get(0).getCompressPath());
//            params.put("logo1",new File(images.get(0).getCompressPath()));
        File file = new File(images.get(0).getCompressPath());

        try {
            presenter.setImg(file, new BaseView<BaseBean>() {
                @Override
                public void result(BaseBean bean) {
                    ToastUtils.toast(SetingActivity.this, bean.getMsg());
                    if (bean.getCode() == 200)
                        userImg.setImageURI(bean.getResult().getAvatar());
                }

                @Override
                public void error() {

                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }


    @Override
    public void takeCancel() {

        super.takeCancel();
    }

    private void initView(UserInfo.ResultBean user) {
//        Log.i("tiancao", "用户数据" + user.toString());
        tb_nickname = user.getTb_nickname();
        mPhone = user.getPhone();
        if (user.getNick_name() != null) {
            nicknameTv.setText(user.getNick_name());
        }
        if (!isImg) {
            if (user.getAvatar() != null || !user.getAvatar().equals(""))
                userImg.setImageURI(user.getAvatar());
        }
        if (user.getTb_nickname() != null) tasobaoTv.setText(user.getTb_nickname());
        if (user.getAli_account() != null) ali_tv.setText(user.getAli_account());
        addree_tv.setText(user.getProvince() + user.getCity() + user.getDistrict() + user.getAddress());
    }


    @Override
    public void result(UserInfo bean) {
        initView(bean.getResult());
    }

    @Override
    public void error() {

    }

    private void taobao() {
        final SelfDialog selfDialog = new SelfDialog(this);
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
                myDataPresenter.changeTaobao(new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        if (bean.getCode() == 200) {
                           /* AliManage.logOut(SetingActivity.this, new AlibcLoginCallback() {
                                @Override
                                public void onSuccess(int i) {
                                    taobaoLogin();
                                }

                                @Override
                                public void onFailure(int i, String s) {

                                }
                            });*/
                            ToastUtils.toast(SetingActivity.this, "解绑成功");
                        } else if (bean.getCode() == 400) {
                            ToastUtils.toast(SetingActivity.this, bean.getMsg());
                        }
                    }

                    @Override
                    public void error() {

                    }
                });
            }
        });
        selfDialog.setTitle("您是否要解绑淘宝账号？");
        selfDialog.show();

    }

    /*
       淘宝登录
      */
    private Session session;

    private void taobaoLogin() {
        AliManage.loginTaobao(SetingActivity.this, new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {
                session = AlibcLogin.getInstance().getSession();
                Log.d("ffff", "onSuccess: " + session.toString());
                MyApplication.session = session;
                presenter.postTaoBaoS(session, new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtils.toast(SetingActivity.this, bean.getMsg());
                            if (bean.getResult().getTb_nickname() != null)
                                tasobaoTv.setText(bean.getResult().getTb_nickname());
                            if (bean.getResult().getAvatar() != null) {
                                Token.setFace(bean.getResult().getAvatar());
                                userImg.setImageURI(bean.getResult().getAvatar());
                            }
                        } else if (bean.getCode() == 400) {
                            ToastUtils.toast(SetingActivity.this, bean.getMsg());
                            taobao_logout();
                        }
                    }

                    @Override
                    public void error() {

                    }
                });
            }

            @Override
            public void onFailure(int i, String s) {
            }
        });
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        if (rgPickTool.getCheckedRadioButtonId() == R.id.rbPickWithOwn) {
            builder.setWithOwnGallery(true);
        }
        if (rgCorrectTool.getCheckedRadioButtonId() == R.id.rbCorrectYes) {
            builder.setCorrectImage(true);
        }
        takePhoto.setTakePhotoOptions(builder.create());

    }

    private void configCompress(TakePhoto takePhoto) {
        if (rgCompress.getCheckedRadioButtonId() != R.id.rbCompressYes) {
            takePhoto.onEnableCompress(null, false);
            return;
        }
        int maxSize = Integer.parseInt(etSize.getText().toString());
        int width = Integer.parseInt(etCropWidth.getText().toString());
        int height = Integer.parseInt(etHeightPx.getText().toString());
        boolean showProgressBar = rgShowProgressBar.getCheckedRadioButtonId() == R.id.rbShowYes ? true : false;
        boolean enableRawFile = rgRawFile.getCheckedRadioButtonId() == R.id.rbRawYes ? true : false;
        CompressConfig config;
        if (rgCompressTool.getCheckedRadioButtonId() == R.id.rbCompressWithOwn) {
            config = new CompressConfig.Builder()
                    .setMaxSize(maxSize)
                    .setMaxPixel(width >= height ? width : height)
                    .enableReserveRaw(enableRawFile)
                    .create();
        } else {
            LubanOptions option = new LubanOptions.Builder()
                    .setMaxHeight(height)
                    .setMaxWidth(width)
                    .setMaxSize(maxSize)
                    .create();
            config = CompressConfig.ofLuban(option);
            config.enableReserveRaw(enableRawFile);
        }
        takePhoto.onEnableCompress(config, showProgressBar);

    }

    private CropOptions getCropOptions() {

        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(300).setOutputY(300);
        builder.setWithOwnCrop(true);
        return builder.create();
    }
}
