package com.judian.goule.store.fragment;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.adapter.BaseRecyclerAdapter;
import com.example.ccy.ccyui.share.QQShareSelf;
import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.Constants;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.WriterException;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.youxuan.TetrisActivity;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.CateBean;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.UserInfo;
import com.judian.goule.store.db.liteorm.UserInfoDBUtil;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.LoadDialog;
import com.judian.goule.store.view.LoadPopupwindow;
import com.judian.goule.store.view.MakeCatePopupwindow;
import com.judian.goule.store.view.MakeFixPopupwindow;
import com.judian.goule.store.view.MakeTypePopupwindow;
import com.judian.goule.store.view.MySharePopupwindow;
import com.judian.goule.store.view.ShareMake2Popupwindow;
import com.judian.goule.store.view.ShareMakePopupwindow;
import com.judian.goule.store.views.BaseView;
import com.judian.goule.store.zxing.encode.EncodingHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 分享赚
 */
public class ShareFragment extends Fragment implements MySharePopupwindow.MySharePopupwindowListener {


    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.makeEt)
    EditText makeEt;

    @BindView(R.id.tabAll)
    RelativeLayout tabAll;

    @BindView(R.id.makeAll)
    RelativeLayout makeAll;

    @BindView(R.id.ll)
    LinearLayout ll;

    @BindView(R.id.ccy)
    LinearLayout ccy;

    @BindView(R.id.recy)
    RecyclerView recy;


    MakeCatePopupwindow catePopupwindow;
    MakeTypePopupwindow typePopupwindow;
    MakeFixPopupwindow fixPopupwindow;
    @BindViews({R.id.cate, R.id.mast, R.id.fix})
    List<LinearLayout> msTx2;

    @BindViews({R.id.tv1, R.id.tv2, R.id.tv3})
    List<TextView> tvs;

    @BindViews({R.id.img1, R.id.img2, R.id.img3})
    List<ImageView> imgs;
    @BindView(R.id.img)
    ImageView img;


    private long lastClickTime = 0;
    public static final int MIN_CLICK_DELAY_TIME = 2000;


    private BaseQuickAdapter<GoodListBean.ResultBean, BaseViewHolder> makeData;
    private CdataPresenter presenter;
    HashMap<String, String> params = new HashMap<>();

    List<GoodListBean.ResultBean> selList = new ArrayList<>();
    private BaseRecyclerAdapter<GoodListBean.ResultBean> adapterR;


    ShareMakePopupwindow makePopupwindow;
    ShareMake2Popupwindow make2Popupwindow;
    private Uri parse;
    private int size;
    private LoadDialog dialog;
    private String url;

    LoadPopupwindow loadPopupwindow;
    private Unbinder bind;
    private int iSc = 0;
    private MySharePopupwindow mySharePopupwindow;//分享弹出框
    private int mShareClickType = 0;//分享点击点击类型
    private UserInfo userInfo;


    @Override
    public void onDestroyView() {
        bind.unbind();
        super.onDestroyView();
    }

    public ShareFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_zhuan, container, false);
        initView(view);
        return view;
    }

    private void initView(View views) {
        bind = ButterKnife.bind(this, views);
        ImmersionBar.with(this).fitsSystemWindows(true).
                statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f).init();
        //初始化分享弹出框
        mySharePopupwindow = new MySharePopupwindow(getContext());
        mySharePopupwindow.setmListener(this);

        userInfo = UserInfoDBUtil.get(getContext());

        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new CdataPresenter(getContext());
        dialog = new LoadDialog(getContext());
        ViewGroup.LayoutParams layoutParams = mIcon.getLayoutParams();
        layoutParams.width = (int) (MyApplication.width);
        layoutParams.height = (int) (MyApplication.width);
        mIcon.setLayoutParams(layoutParams);
        makeData = AdapterUtil.getMakeData(getActivity(), new AdapterUtil.MakeSelLintener() {
            @Override
            public void add(GoodListBean.ResultBean hotBean) {
                if (selList.size() < 9) {
                    selList.add(hotBean);
                    setNum();
                    imageLive3(hotBean);
                } else {
                    ToastUtils.toast(getContext(), "一键群发最多只能选择9张哦~");
                }
            }

            @Override
            public void remov(GoodListBean.ResultBean hotBean) {
                selList.remove(hotBean);
                setNum();
            }

            @Override
            public void share(final GoodListBean.ResultBean hotBean) {
                Log.i("tianc", "点击了立即推广");
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        imageLive2(hotBean);
                    }
                });
                dialog.show();
            }

            @Override
            public void itemClick(GoodListBean.ResultBean hotBean) {
                TetrisActivity.openMain(getActivity(), hotBean, 6);
            }
        });

//        setRecyImg();
        recy.setAdapter(makeData);

        makeData.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                params.put("page", page + "");
                presenter.getMakeSearch(params, view);

            }
        });

        shuxin();

        makeEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    long currentTime = Calendar.getInstance().getTimeInMillis();
                    if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                        lastClickTime = currentTime;
                        sou();
                    }
                    return true;
                }
                return false;
            }
        });


        makeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (makeEt.getText().toString().equals("")) {
                    close.setVisibility(View.GONE);
                } else {
                    close.setVisibility(View.VISIBLE);
                }
            }
        });

        setNum();
        makePopupwindow = new ShareMakePopupwindow(getContext(), new ShareMakePopupwindow.OnShareClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void weixin() {
                mOption = 0;
                size = selList.size();
                if (size == 0) {
                    ToastUtils.toast(getContext(), "您还未选择商品");
                } else {
                    requestWritePermission();
                }

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void qq() {
                mOption = 1;
                size = selList.size();
                if (size == 0) {
                    ToastUtils.toast(getContext(), "您还未选择商品");
                } else {
                    requestWritePermission();
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void other() {
                mOption = 2;
                size = selList.size();
                if (size == 0) {
                    ToastUtils.toast(getContext(), "您还未选择商品");
                } else {
                    requestWritePermission();
                }

            }
        });


        make2Popupwindow = new ShareMake2Popupwindow(getContext(), new ShareMake2Popupwindow.OnShareClickListener() {
            @Override
            public void weixin() {
                mOption = 0;
                getUrl();
            }

            @Override
            public void qq() {
                mOption = 1;
                getUrl();
            }

            @Override
            public void other() {
                mOption = 2;
                getUrl();


            }
        });
    }

    void getUrl() {
        String ids = "";

        for (int i = 0; i < selList.size(); i++) {
            ids = ids + selList.get(i).getNum_iid() + ",";
        }
        ids = ids.substring(0, ids.length() - 1);

        presenter.getMakeUrl(ids, new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                if (bean.getCode() == 200) {
                    url = bean.getResult().getUrl();
                    switch (mOption) {
                        case 0:
                            if (!isClike) {
                                isClike = true;
                                Picasso.with(getContext()).load(selList.get(0).getPict_url()).into(mTarget);
                            } else {

                            }
                            break;
                        case 1:
                            if (QQShareSelf.checkApkExist(getContext(), "com.tencent.mobileqq")) {
                                QQShareSelf.getInstance(getActivity()).onClickShare(url, selList.get(0).getPict_url());
                            } else {
                                Toast.makeText(getContext(), "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                            }


                            break;

                        case 2:
                            ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                            Token.addKey(url);
                            cm.setText(url);
                            ToastUtils.toast(getContext(), "复制成功");
                            break;
                    }

                }
            }

            @Override
            public void error() {

            }
        });


    }

    private Bitmap bitmap1;
    boolean isScc = false, isClike = false;
    private Target mTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            bitmap1 = bitmap;
            isScc = true;
            goShare();

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    private void goShare() {
        if (!isScc) {
            ToastUtils.toast(getContext(), "图片处理中...");
        }

        if (isScc && isClike) {

            isClike = false;
            if (bitmap1 != null)
                WXShare.getInstance(getActivity()).shareWX(1, "精品优惠券集合", url, "我为小伙伴们特地挑选的淘宝优惠券，在这里，省到底！", bitmap1);
            else
                ToastUtils.toast(getContext(), "图片加载失败...");

        }
    }

    int mOption = 0;

    void setNum() {
        num.setText(selList.size() + "");
    }

    int page = 1;
    BaseView<GoodListBean> view = new BaseView<GoodListBean>() {
        @Override
        public void result(GoodListBean bean) {
            if (bean.getCode() == 200) {
                if (bean.getResult().size() < 10) {
                    makeData.loadMoreEnd();

                } else {
                    makeData.loadMoreComplete();

                }
                makeData.addData(bean.getResult());


            } else {
                makeData.loadMoreEnd();
                ToastUtils.toast(getContext(), bean.getMsg());
            }


        }

        @Override
        public void error() {
            makeData.loadMoreFail();
        }
    };

    private void shuxin() {
        selList.clear();
        bitList.clear();
        setNum();
        page = 1;
        makeData.setNewData(null);
        params.put("page", page + "");
        presenter.getMakeSearch(params, view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.close, R.id.cate, R.id.mast, R.id.share1, R.id.share2, R.id.serIv, R.id.fix})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                makeEt.setText("");
                close.setVisibility(View.INVISIBLE);
                break;
            case R.id.share1://一键群发
                list = new ArrayList<>();
                mShareClickType = 1;
                if (selList.size() != 0) {
//                    makePopupwindow.showAtLocation(makeAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    mySharePopupwindow.showAtLocation(makeAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    ToastUtils.toast(getContext(), "请选择商品");
                }
                break;
            case R.id.share2:
                mShareClickType = 2;
                if (selList.size() != 0) {
//                    make2Popupwindow.showAtLocation(makeAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    mySharePopupwindow.showAtLocation(makeAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    ToastUtils.toast(getContext(), "请选择商品");
                }

                break;
            case R.id.cate:
                setImg(0, 180);
                if (catePopupwindow == null) {
                    catePopupwindow = new MakeCatePopupwindow(getContext(), tabAll.getHeight() + ll.getHeight(), new MakeCatePopupwindow.OnOptionLister() {
                        @Override
                        public void map(CateBean.ResultBean bean) {
                            params.put("cate_id", bean.getId());
                            tvs.get(0).setText(bean.getCategory_name());
                            shuxin();
                        }
                    });

                    catePopupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            setImg(0, 0);
                        }
                    });
                }
                catePopupwindow.showAtLocation(makeAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.mast:
                setImg(1, 180);
                if (typePopupwindow == null) {
                    typePopupwindow = new MakeTypePopupwindow(getContext(), tabAll.getHeight() + ll.getHeight(), new MakeTypePopupwindow.OnOptionLister() {
                        @Override
                        public void map(CateBean.ResultBean bean) {
                            params.put("type", bean.getId());
                            tvs.get(1).setText(bean.getCategory_name());
                            shuxin();
                        }
                    });

                    typePopupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            setImg(1, 0);
                        }
                    });

                }
                typePopupwindow.showAtLocation(makeAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.fix:
                setImg(2, 180);
                if (fixPopupwindow == null) {
                    fixPopupwindow = new MakeFixPopupwindow(getContext(), tabAll.getHeight() + ll.getHeight(), new MakeFixPopupwindow.FixListener() {
                        @Override
                        public void map(HashMap<String, String> map) {
                            params.putAll(map);
                            shuxin();
                        }
                    });
                    fixPopupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            setImg(2, 0);
                        }
                    });
                }
                fixPopupwindow.showAtLocation(makeAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.serIv:
                sou();
                break;
        }
    }


    ArrayList<Uri> list = new ArrayList();

    private void all() {
        list = new ArrayList<>();
        size = selList.size();
        if (size == 0) {
            ToastUtils.toast(getContext(), "您还未选择商品");
        } else {

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            createImg();

                        }
                    }).start();


                }
            });

            if (bitList.size() != selList.size()) {
                bitList.clear();

                for (int i = 0; i < selList.size(); i++) {
                    imageLive3(selList.get(i));
                }

            }
            dialog.show();


        }
    }


    void createImg() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < bitList.size(); i++) {
                    Log.i("tiancao", "分享链接：" + bitList.get(i).name);
                    list.add(getImageUri3(bitList.get(i)));
                }
                bitList.clear();
                dialog.dismiss();
                switch (mOption) {
                    case 0:
                        WXShare.shareImages(getContext(), list);
                        break;
                    case 1:
                        WXShare.shareImagesQQ(getContext(), list);
                        break;
                    case 2:
                        WXShare.shareImagesSys(getContext(), list);
                        break;
                }


            }
        }).start();


    }


    void setImg(int option, int rotation) {
        imgs.get(option).setPivotX(imgs.get(option).getWidth() / 2);
        imgs.get(option).setPivotY(imgs.get(option).getHeight() / 2);
        imgs.get(option).setRotation(rotation);
    }


    public Uri getImageUri3(ViewBit viewBit) {

        //申请权限
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (getContext().checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }

        FileOutputStream fos;
        try {
            // 判断手机设备是否有SD卡
            boolean isHasSDCard = Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
            if (isHasSDCard) {
                // SD卡根目录
                if (!Constants.IMAGE_DIR.exists()) {
                    //通过file的mkdirs()方法创建<span style="color:#FF0000;">目录中包含却不存在</span>的文件夹
                    Constants.IMAGE_DIR.mkdirs();
                }
                mFile = new File(Constants.IMAGE_DIR, viewBit.name + ".png");

                fos = new FileOutputStream(mFile);
            } else {
                throw new Exception("创建文件失败!");
            }

            viewBit.bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.e("qqqqqqqqqq", " getImageUri3 Uri " + Uri.fromFile(mFile));

//        Uri uri = MediaStore.Audio.Media.getContentUriForPath(sdfile.getAbsolutePath());
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion > 24) { // 保存uri对应的照片于指定路径
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, mFile.getAbsolutePath());
            Uri uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            Logger.e("qqqqqqqqqq", "getImageUri3 uri ==" + uri);
            if (uri != null) return uri;
        }
        return Uri.fromFile(mFile);

    }


    public void viewSaveToImage2(View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);
        final Bitmap cachebmp = loadBitmapFromView(view);
        view.destroyDrawingCache();
        Logger.e("vvvvvvv", "dddddd  viewSaveToImage2()  5");
        dialog.dismiss();
        iSc = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                WXShare.getInstance(getActivity()).shareWX(1, cachebmp, mW, mH);
            }
        }).start();


    }


    public void viewSaveToImage3(String name, View view) {
        Logger.e("vvvvvvv", " viewSaveToImage3 " + name);
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);
        Bitmap cachebmp = loadBitmapFromView(view);
        view.destroyDrawingCache();

        bitList.add(new ViewBit(System.currentTimeMillis() + bitList.size() + "", cachebmp));
//        cachebmp.recycle();
    }


    private int mW;
    private int mH;
    private File mFile;

    private Bitmap loadBitmapFromView(View v) {
        mW = v.getWidth();
        mH = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(mW, mH, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, mW, mH);
        v.draw(c);

        return bmp;
    }


    private void sou() {
        String key = makeEt.getText().toString();
        if (key.equals("")) {
            ToastUtils.toast(getContext(), "请输入关键词");
        } else {
            params.put("keyword", key);
            shuxin();
        }
    }

    boolean isOk = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestWritePermission() {
        /*if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 15);

        } else {
            isOk = true;
            all();
        }*/
        isOk = true;
        all();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 15:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //premission granted by user
                    all();
                } else {
//                    all();
                    ToastUtils.toast(getContext(), "未获取权限，不能群发图片");
                }

                break;


            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @BindView(R.id.pic)
    TextView mPic;
    @BindView(R.id.picOdl)
    TextView mPicOdl;


    @BindView(R.id.ll_txt)
    TextView mLlTxt;

    @BindView(R.id.info)
    TextView info;

    @BindView(R.id.coupon)
    LinearLayout mCoupon;
    @BindView(R.id.iv2Code)
    ImageView mIv2Code;
    @BindView(R.id.icon)
    SimpleDraweeView mIcon;
//    @BindView(R.id.img6)
//    SimpleDraweeView img6;

    List<ViewBit> bitList = new ArrayList<>();

    private void imageLive2(final GoodListBean.ResultBean result) {
        mLlTxt.setText(result.getTitle());
        mPicOdl.setText("原价 ¥" + result.getReserve_price() + "");
        mPicOdl.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mPicOdl.getPaint().setAntiAlias(true);
        mPic.setText("¥" + result.getPrice() + "");
        if (result.getHave_coupon() == 0) {
            mCoupon.setVisibility(View.GONE);
        } else {
            mCoupon.setVisibility(View.VISIBLE);
            info.setText(result.getCoupon_money());
        }
        if (HttpAPI.HOST.equals("http://192.168.1.241:80")) {
            String shaer = "http://192.168.1.241:80/App/mobile/goods_tb?id=" + result.getNum_iid() + "&pid=" + userInfo.getResult().getPid();
            create2Code(shaer);//获取二维码
        } else {
            String shaer = "http://www.17goule.com/App/mobile/goods_tb?id=" + result.getNum_iid() + "&pid=" + userInfo.getResult().getPid();
            create2Code(shaer);//获取二维码
        }
        iSc = 1;
        Picasso.with(getContext()).load(result.getPict_url()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (iSc == 1) {
                    mIcon.setImageBitmap(bitmap);
                    iSc = 2;
                    viewSaveToImage2(ccy);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        AdapterUtil.setImgMake(mIcon, result.getPict_url());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    if (iSc == 1) {
                        iSc = 3;
                        viewSaveToImage2(ccy);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void imageLive3(final GoodListBean.ResultBean result) {
        Log.i("tiancao", " imageLive3   5 " + result.getNum_iid());
        mLlTxt.setText(result.getTitle());
        AdapterUtil.setImgMake(mIcon, result.getPict_url());
        mPicOdl.setText("原价 ¥" + result.getReserve_price() + "");
        mPicOdl.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mPicOdl.getPaint().setAntiAlias(true);
        mPic.setText("¥" + result.getPrice() + "");
        if (result.getHave_coupon() == 0) {
            mCoupon.setVisibility(View.GONE);
        } else {
            mCoupon.setVisibility(View.VISIBLE);
            info.setText(result.getCoupon_money());
        }
        if (HttpAPI.HOST.equals("http://192.168.1.241:80")) {
            String shaer = "http://192.168.1.241:80/App/mobile/goods_tb?id=" + result.getNum_iid() + "&pid=" + userInfo.getResult().getPid();
            create2Code(shaer);//获取二维码
        } else {
            String shaer = "http://www.17goule.com/App/mobile/goods_tb?id=" + result.getNum_iid() + "&pid=" + userInfo.getResult().getPid();
            create2Code(shaer);//获取二维码
        }
        Picasso.with(getContext()).load(result.getPict_url()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                mIcon.setImageBitmap(bitmap);
                viewSaveToImage3(result.getNum_iid(), ccy);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        try {
            for (int i = 0; i < bitList.size(); i++) {
                bitList.get(i).bitmap.recycle();
                bitList.get(i).bitmap = null;

            }

        } catch (Exception e) {

        }
        super.setUserVisibleHint(isVisibleToUser);
    }


    private Bitmap create2Code(String key) {
        Bitmap qrCode = null;

        try {
            qrCode = EncodingHandler.create2Code(key, 300);
            mIv2Code.setImageBitmap(qrCode);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return qrCode;
    }

    //分享弹出框的回调
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void myShareClick(int tab) {
        switch (tab) {
            case 1://微信
                if (mShareClickType == 1) {
                    mOption = 0;
                    size = selList.size();
                    if (size == 0) {
                        ToastUtils.toast(getContext(), "您还未选择商品");
                    } else {
                        requestWritePermission();
                    }
                } else if (mShareClickType == 2) {
                    mOption = 0;
                    getUrl();
                }
                break;
            case 2://朋友圈
                if (mShareClickType == 1) {

                } else if (mShareClickType == 2) {

                }
                break;
            case 3://qq
                if (mShareClickType == 1) {
                    mOption = 1;
                    size = selList.size();
                    if (size == 0) {
                        ToastUtils.toast(getContext(), "您还未选择商品");
                    } else {
                        requestWritePermission();
                    }
                } else if (mShareClickType == 2) {
                    mOption = 1;
                    getUrl();
                }
                break;
            case 4://空间
                if (mShareClickType == 1) {

                } else if (mShareClickType == 2) {

                }
                break;
        }
    }


    class ViewBit {
        String name;
        Bitmap bitmap;

        public ViewBit(String name, Bitmap bitmap) {
            this.name = name;
            this.bitmap = bitmap;
        }
    }

}
