package com.judian.goule.store.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.share.QQShareSelf;
import com.example.ccy.ccyui.share.WXShare;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.DividerItemDecoration;
import com.example.ccy.ccyui.view.MyScrollView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.judian.goule.store.MyApplication;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.adapter.ImageHolder1;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.CommentBean;
import com.judian.goule.store.bean.HotOrderBean;
import com.judian.goule.store.bean.OrderDetailBean;
import com.judian.goule.store.bean.TaoTokenBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.TestData;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.view.ImagePopupwindow;
import com.judian.goule.store.view.ShareUrlPopupwindow;
import com.judian.goule.store.views.BaseView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrderDetailsActivity extends BaseActivity implements  MyScrollView.OnScrollListenter {

    @BindView(R.id.banner)
    ConvenientBanner<OrderDetailBean.ResultBean.CommentBean.LunBean> mBanner;
    @BindView(R.id.face)
    RoundedImageView mFace;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.crownIv)
    ImageView mCrownIv;
    @BindView(R.id.crownTv)
    TextView mCrownTv;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.txt)
    TextView mTxt;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.gicon)
    SimpleDraweeView mGicon;
    @BindView(R.id.gtitle)
    TextView mGtitle;
    @BindView(R.id.tl)
    LinearLayout mTl;
    @BindView(R.id.pic)
    TextView mPic;
    @BindView(R.id.payOld)
    TextView mPayOld;
    @BindView(R.id.youhui)
    TextView mYouhui;
    @BindView(R.id.xl)
    TextView mXl;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.commentInfo)
    TextView commentInfo;
    @BindView(R.id.myFace)
    RoundedImageView mMyFace;
    @BindView(R.id.recy)
    RecyclerView mRecy;
    @BindView(R.id.recy2)
    RecyclerView mRecy2;
    @BindView(R.id.commentNum)
    TextView mCommentNum;
    @BindView(R.id.comment)
    LinearLayout mComment;
    @BindView(R.id.likeNum)
    TextView mLikeNum;
    @BindView(R.id.like)
    LinearLayout mLike;
    @BindView(R.id.love)
    ImageView mLove;
    @BindView(R.id.ensure)
    ImageView ensure;


    @BindView(R.id.info)
    TextView mInfo;
    @BindView(R.id.scroll)
    MyScrollView mScroll;
    @BindView(R.id.bottom)
    LinearLayout mBottom;
    @BindView(R.id.saveConntent)
    EditText mSaveConntent;
    @BindView(R.id.commentLl)
    LinearLayout mCommentLl;
    @BindView(R.id.floBtn)
    ImageView mFloBtn;
    @BindView(R.id.whoGod)
    TextView mWhoGod;
    @BindView(R.id.tabTitle)
    TextView tabTitle;

    @BindView(R.id.whoOrder)
    TextView mWhoOrder;


    @BindView(R.id.all)
    RelativeLayout mAll;

    @BindView(R.id.comm)
    LinearLayout mComm;


    @BindView(R.id.likeIv)
    ImageView mLikeIv;
    @BindView(R.id.pich)
    TextView pich;
    private CdataPresenter mPresenter;
    private String mOrderId;
    private String mGoodId;

    @BindView(R.id.tabA)
    Toolbar mTabAll;
    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    private BaseQuickAdapter<CommentBean.ResultBean, BaseViewHolder> mAdapterRecy;
    //    private BaseRecyclerAdapter<HotOrderBean.ResultBean> mHotOrder;
    private BaseQuickAdapter<HotOrderBean.ResultBean, BaseViewHolder> mHotOrder;
    private TextView mMoveTv;
    private String mUser_id;
    private ImagePopupwindow imgPopupwindow;
    private int mScaleY;
    private int mLoveNum;
    private String url;
    private ShareUrlPopupwindow mPopupwindow;

    int mOption=0;
    private String title;
    private String pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        bind = ButterKnife.bind(this);

        ImmersionBar.with(this)
                .fitsSystemWindows(false)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .setOnKeyboardListener(new OnKeyboardListener() {
                    @Override
                    public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
                        if (isPopup) {
                            mCommentLl.setVisibility(View.VISIBLE);
                        } else {
                            mCommentLl.setVisibility(View.GONE);
                        }
                    }
                })
                .init();
        if (Token.getFace() != null && !Token.getFace().equals(""))
            Picasso.with(this).load(Token.getFace()).into(mMyFace);
        mOrderId = getIntent().getStringExtra("orderId");
        mGoodId = getIntent().getStringExtra("goodId");
        mPresenter = new CdataPresenter(this);
        mPopupwindow = new ShareUrlPopupwindow(this, new ShareUrlPopupwindow.OnShareClickListener() {
            @Override
            public void weixin() {
                mOption=1;
                isClike=true;
                goShare();
            }
            @Override
            public void pengyou() {
                mOption=0;
                isClike=true;
                goShare();
            }

            @Override
            public void qq() {

                if (QQShareSelf.checkApkExist(OrderDetailsActivity.this, "com.tencent.mobileqq")) {
                    QQShareSelf.getInstance(OrderDetailsActivity.this).onClickShare(url, pic, "我买了，你还等什么？", title, new IUiListener() {
                        @Override
                        public void onComplete(Object o) {
                            mPresenter.commitOrder();
                            ToastUtils.toast(OrderDetailsActivity.this,"分享成功");
                        }

                        @Override
                        public void onError(UiError uiError) {

                            ToastUtils.toast(OrderDetailsActivity.this,"分享失败");
                        }

                        @Override
                        public void onCancel() {
                            ToastUtils.toast(OrderDetailsActivity.this,"分享取消");
                        }
                    });
                } else {
                    Toast.makeText(OrderDetailsActivity.this, "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void zone() {
                ArrayList<String > list=new ArrayList<>();
                list.add(pic);

                if (QQShareSelf.checkApkExist(OrderDetailsActivity.this, "com.tencent.mobileqq")) {
                    QQShareSelf.getInstance(OrderDetailsActivity.this).shareToQzone(url,list,"我买了，你还等什么？",title,new IUiListener() {
                        @Override
                        public void onComplete(Object o) {
                            mPresenter.commitOrder();
                            ToastUtils.toast(OrderDetailsActivity.this,"分享成功");
                        }

                        @Override
                        public void onError(UiError uiError) {

                            ToastUtils.toast(OrderDetailsActivity.this,"分享失败");
                        }

                        @Override
                        public void onCancel() {
                            ToastUtils.toast(OrderDetailsActivity.this,"分享取消");
                        }
                    });
                } else {
                    Toast.makeText(OrderDetailsActivity.this, "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                }



            }


        });


        if (mOrderId == null) return;
        initBanner();
        initEdit();
        mScroll.setListenter(this);
        mScroll.setLoading(true);
        initView();
        initRecy();
        initOther();
    }


    private Bitmap bitmap1;
    boolean isScc = false, isClike = false;
    private void goShare() {
        if (!isScc){
            ToastUtils.toast(this,"图片处理中...");
        }

        if (isScc && isClike) {

            isClike = false;
            if (bitmap1!=null)
                WXShare.getInstance(OrderDetailsActivity.this).shareWX(mOption,"我买了，你还等什么",url,title,bitmap1);
            else
                ToastUtils.toast(this,"图片加载失败...");

        }
    }

    private Target mTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            bitmap1=bitmap;
            isScc=true;
            goShare();

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };










    private void initView() {

        mPresenter.getComDetail(mOrderId, mGoodId, new BaseView<OrderDetailBean>() {
            @Override
            public void result(OrderDetailBean bean) {
                if (bean.getCode() == 200) {
                    url = bean.getResult().getUrl();
                    initOrder(bean.getResult().getComment());
                    initGoods(bean.getResult().getGoods());
                } else {

                }
            }

            @Override
            public void error() {
            }
        });


    }


    private void initOther() {
        mRecy2.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.BOTH_SET));

        try {
            mHotOrder = AdapterUtil.getOtherOrder(this, new AdapterUtil.OnSorderListener() {
                @Override
                public void onclick(HotOrderBean.ResultBean hotBean) {

                    Intent intent = new Intent(OrderDetailsActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("orderId", hotBean.getId());
                    intent.putExtra("goodId", hotBean.getNum_iid());
                    startActivityForResult(intent, 33);

                }
            });
        } catch (Exception e) {
            Logger.e("dddd", "Exception  " + e);
        }


        mRecy2.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL){
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        mRecy2.setAdapter(mHotOrder);
        mHotOrder.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        });
        mPresenter.getOtherOrder(mOrderId, otherPage, mView);

    }

    int otherPage = 1;


    BaseView<HotOrderBean> mView = new BaseView<HotOrderBean>() {
        @Override
        public void result(HotOrderBean bean) {
            mScroll.setLoading(true);
            if (bean.getCode() == 200) {
                if (bean.getResult().size() < 10) {
                    mHotOrder.loadMoreEnd();
                }else {
                    mHotOrder.loadMoreComplete();
                }

                mHotOrder.addData(bean.getResult());

            } else {

                mHotOrder.loadMoreEnd();

            }

        }

        @Override
        public void error() {
            mScroll.setLoading(true);
            mHotOrder.loadMoreFail();
        }
    };


    BaseView<CommentBean> commentView = new BaseView<CommentBean>() {
        @Override
        public void result(CommentBean bean) {
            if (bean.getCode() == 200) {
                mMoveTv.setText("点击查看更多");

                mAdapterRecy.addData(bean.getResult());

            } else {
                mMoveTv.setText("点击查看更多");
            }
        }

        @Override
        public void error() {
            mMoveTv.setText("加载失败");
        }
    };


    public static final int MIN_CLICK_DELAY_TIME = 2000;
    private long lastClickTime = 0;

    private void initEdit() {

        mSaveConntent.setImeOptions(EditorInfo.IME_ACTION_SEND);
        mSaveConntent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int keyCode, KeyEvent event) {

                if (KeyEvent.ACTION_DOWN == keyCode && event.getAction() == KeyEvent.ACTION_DOWN) {
                    long currentTime = Calendar.getInstance().getTimeInMillis();
                    if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                        lastClickTime = currentTime;
                        saveMsg();
                        mSaveConntent.setText("");
                    }

                    return true;
                }
                return false;
            }

        });

    }

    private void saveMsg() {
        String str = mSaveConntent.getText().toString();
        if (str.equals("")) {

            ToastUtils.toast(this, "内容不能为空");

        } else {
            mPresenter.saveComment(mOrderId, str, new BaseView<BaseBean>() {
                @Override
                public void result(BaseBean bean) {
                    ToastUtils.toast(OrderDetailsActivity.this, bean.getMsg());
                    if (bean.getCode() == 200) {
                        mCommentNum.setText(bean.getResult().getComment_num());
                        mAdapterRecy.setNewData(null);

                        mPresenter.getCommentList(mOrderId, 1, commentView);
                    }
                }

                @Override
                public void error() {

                }
            });
        }


    }

    private void initRecy() {
        mRecy.setLayoutManager(new LinearLayoutManager(this));
        mRecy.addItemDecoration(new DividerItemDecoration(
                OrderDetailsActivity.this, DividerItemDecoration.BOTH_SET));
        mAdapterRecy = new BaseQuickAdapter<CommentBean.ResultBean, BaseViewHolder>(R.layout.item_comment) {
            @Override
            protected void convert(BaseViewHolder helper, CommentBean.ResultBean item,int p) {
                SimpleDraweeView view = helper.getView(R.id.face);
//                try {
                AdapterUtil.setImg(view, item.getAvatar());
//                }catch (Exception e){
//                    Logger.e("dddd","Exception  "+e);
//                    Logger.e("dddd","Exception  "+item.getAvatar());
//                }

                helper.setText(R.id.name, item.getNickname())
                        .setText(R.id.time, item.getAdd_time())
                        .setText(R.id.comment, item.getApply_content());

            }
        };
        mRecy.setAdapter(mAdapterRecy);
        mAdapterRecy.addFooterView(getFooterView());
//        mAdapterRecy.setOnLoadMoreListener(this);
        mPresenter.getCommentList(mOrderId, 1, commentView);


    }

    private View getFooterView() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        mMoveTv = new TextView(this);
        mMoveTv.setText("点击查看更多");
        mMoveTv.setTextSize(16f);
        mMoveTv.setTextColor(getResources().getColor(R.color.bean2));
        linearLayout.addView(mMoveTv);
        linearLayout.setGravity(Gravity.CENTER);
        mMoveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            RecyActivity.openMain(OrderDetailsActivity.this,12,mOrderId);

            }
        });
        return linearLayout;
    }


    private void initGoods(OrderDetailBean.ResultBean.GoodsBean goods) {
        mGicon.setImageURI(goods.getPict_url());
        mGtitle.setText(goods.getTitle());
        String price = goods.getPrice();
        int m=price.indexOf(".");
        if (m!=-1){
            mPic.setText(price.substring(0,m));
            pich.setText(price.substring(m,price.length()));
        }else {
            mPic.setText(goods.getPrice());
        }

        mPayOld.setText("¥" + goods.getReserve_price());
        mPayOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mPayOld.getPaint().setAntiAlias(true);

        switch (goods.getHave_coupon()) {
            case 0:
                mYouhui.setVisibility(View.GONE);
                break;
            case 1:
                mYouhui.setText("优惠券" + goods.getCoupon_money());
                break;
        }

        mXl.setText("已售 " + goods.getMonth_sales() + "件");
        mInfo.setText(goods.getFanli_money());

    }

    private ViewGroup.LayoutParams layoutParams;

    private void initBanner() {
        layoutParams = mBanner.getLayoutParams();
        mScaleY = (int) mComm.getTop() + MyApplication.height * 5 / 6;
        mLoveNum = mLove.getTop();
        layoutParams.width=defaultheight;
        layoutParams.height=defaultheight;
        mBanner.setLayoutParams(layoutParams);
    }



      int defaultheight=MyApplication.width;
    void  setData(List<OrderDetailBean.ResultBean.CommentBean.LunBean> lun){


        mBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolder1();
            }
        },lun).setPageIndicator(new int[]{R.mipmap.ic_dot_normal, R.mipmap.ic_dot_pressed})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

    }
    private List<OrderDetailBean.ResultBean.CommentBean.ImgBean> img;
    List<Integer> hits=new ArrayList<>();
    private void initOrder(final OrderDetailBean.ResultBean.CommentBean comment) {
        mLikeNum.setText(comment.getZan_num());
        pic = comment.getLun().get(0).getPath_name();
        Picasso.with(this).load(pic).into(mTarget);
        mCommentNum.setText(comment.getComment_num());
        setData(comment.getLun());
        img = comment.getImg();

        List<String >  list=new ArrayList<>();
        for (int i = 0; i < comment.getLun().size(); i++) {
            list.add(comment.getLun().get(i).getPath_name());
        }
        imgPopupwindow = new ImagePopupwindow(OrderDetailsActivity.this, list,0);

        if (img!=null){

            for (int i = 0; i < img.size(); i++) {
                hits.add(img.get(i).getHeight()*MyApplication.width/img.get(i).getWidth());
            }
            layoutParams.width = MyApplication.width;
            layoutParams.height=hits.get(0);
            mBanner.setLayoutParams(layoutParams);
            mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float positionOffset, int i1) {
                    Logger.e("wwwwwwwwwwwww","onPageScrolled i== "+i);
                    if (hits.size()==0)return;
                    int  h=  i%hits.size();
                    int  l=  (i+1)%hits.size();

                    layoutParams.height = (int) ((hits.get(h) == 0 ? defaultheight : hits.get(h))
                            * (1 - positionOffset) +
                            (hits.get(l) == 0 ? defaultheight : hits.get(l))
                                    * positionOffset);
                    mBanner.setLayoutParams(layoutParams);
                }

                @Override
                public void onPageSelected(int i) {

                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });

        }





        mBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                imgPopupwindow.setOption(position);
                imgPopupwindow.showAtLocation(mAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        title = comment.getTitle();
        mTitle.setText(comment.getTitle());
        mTxt.setText(comment.getContent());
        mTime.setText(comment.getAdd_time());
        switch (comment.getSex()) {
            case "1":
                mWhoGod.setText("他晒单的东东");
                mWhoOrder.setText("他的其他晒单");
                break;

            case "2":
                mWhoGod.setText("她晒单的东东");
                mWhoOrder.setText("她的其他晒单");
                break;


        }

        mUser_id = comment.getUser_id();
        if (comment.getAvatar() != null && !comment.getAvatar().equals(""))
            Picasso.with(this).load(comment.getAvatar()).into(mFace);
        mName.setText(comment.getNickname());
        TestData.setLevel(comment.getLevel(), mCrownTv, mCrownIv);
        switch (comment.getSex()) {
            case "1"://nan
                commentInfo.setText("这位先生共有" + comment.getTotal() + "条赞");

                break;
            case "2"://nv
                commentInfo.setText("这位小姐共有" + comment.getTotal() + "条赞");
                break;

        }
        switch (comment.getIs_zan()) {
            case 0://nan
                break;
            case 1://nv
                mLove.setImageResource(R.mipmap.zhan);
                ensure.setImageResource(R.mipmap.zhan);
                mLikeIv.setImageResource(R.mipmap.dian1);

                break;
        }


    }

    @OnClick({R.id.back, R.id.love, R.id.ensure, R.id.comment, R.id.like, R.id.floBtn, R.id.goodsAll, R.id.share, R.id.face, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();

                break;
            case R.id.love:
                dianzan();
                break;
            case R.id.ensure:
                dianzan();
                break;

            case R.id.share://分享订单
                mPopupwindow.showAtLocation(mAll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


                break;

            case R.id.goodsAll:
                mPresenter.getTaoToken(mGoodId, new BaseView<TaoTokenBean>() {
                    @Override
                    public void result(TaoTokenBean bean) {
                        if (bean.getCode() == 200) {
                            AliWebActivity.openXQ(OrderDetailsActivity.this, bean.getResult(), 6);
                        } else {
                            ToastUtils.toast(OrderDetailsActivity.this, bean.getMsg());
                        }
                    }

                    @Override
                    public void error() {
                    }
                });

                break;
            case R.id.floBtn:
                mScroll.smoothScrollTo(0, 0);
                break;

            case R.id.face:
                Intent intent = new Intent(this, HeMainActivity.class);
                intent.putExtra("userId", mUser_id);
                startActivityForResult(intent, 30);
                break;
            case R.id.comment:
                mScroll.smoothScrollTo(0, mScaleY);
                imm();
                break;
            case R.id.like:
                dianzan();
                break;
            case R.id.send:

                imm();
                break;
        }
    }


    private void dianzan() {

        mPresenter.getComment(mOrderId, new BaseView<BaseBean>() {
            @Override
            public void result(BaseBean bean) {
                ToastUtils.toast(OrderDetailsActivity.this, bean.getMsg());
                if (bean.getCode() == 200) {
                    mLove.setImageResource(R.mipmap.zhan);
                    ensure.setImageResource(R.mipmap.zhan);
                    mLikeIv.setImageResource(R.mipmap.dian1);
                    mLikeNum.setText(bean.getResult().getZan_num() + "");
                }
            }

            @Override
            public void error() {

            }
        });

    }


    private void imm() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        mSaveConntent.setFocusable(true);
        mSaveConntent.requestFocus();
//        mCommentLl.setVisibility(View.VISIBLE);
    }

    int page = 1;

    @Override
    public void onBottom() {

         if (mHotOrder.mLoading){
             mScroll.setLoading(false);
             otherPage++;
             mPresenter.getOtherOrder(mOrderId, otherPage, mView);
         }
    }

    @Override
    public void onScrollY(int scrollY) {


        if (scrollY > 240) {
            mFloBtn.setVisibility(View.VISIBLE);
        } else {
            mFloBtn.setVisibility(View.GONE);
        }


        if (scrollY > 20) {
            tabTitle.setVisibility(View.VISIBLE);
        } else {
            tabTitle.setVisibility(View.INVISIBLE);
        }

        if (scrollY > mLove.getTop()) {
            ensure.setVisibility(View.VISIBLE);
        } else ensure.setVisibility(View.GONE);

        if (scrollY < 1000) {
            float alpha = (float) scrollY / 1000;
            try {
                mTabAll.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                        , ContextCompat.getColor(this, R.color.white), alpha));
            } catch (Exception e) {
            }


        } else {

            mTabAll.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                    , ContextCompat.getColor(this, R.color.white), 1));
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, null);
        super.onActivityResult(requestCode, resultCode, data);
        initView();
    }


    @Override
    protected void onResume() {
        if (MyApplication.share==1){
            new CdataPresenter(this).commitOrder();
        }
        MyApplication.share=0;
        super.onResume();
    }




}
