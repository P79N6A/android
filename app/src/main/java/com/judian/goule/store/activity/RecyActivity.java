package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.adapter.BaseRecyclerAdapter;
import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.example.ccy.ccyui.adapter.RecyclerViewHolder;
import com.example.ccy.ccyui.util.DateUtils;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.DividerItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.BannerBean;
import com.judian.goule.store.bean.CommentBean;
import com.judian.goule.store.bean.GoodListBean;
import com.judian.goule.store.bean.LoveBean;
import com.judian.goule.store.bean.MsBean;
import com.judian.goule.store.bean.TaoOrderBean;
import com.judian.goule.store.bean.XuBean;
import com.judian.goule.store.bean.YingBean;
import com.judian.goule.store.db.NewsBean;
import com.judian.goule.store.db.NewsDao;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RecyActivity extends BaseActivity {


    @BindView(R.id.title)
    TextView title;


    @BindView(R.id.msg)
    TextView msg;

    @BindView(R.id.floBtn)
    ImageView floBtn;

    private static final String POSITION = "RecyActivity";
    private CommonBaseAdapter<LoveBean.ResultBean> loveAdapter;
    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.pullRefreshLayout)
    PullRefreshLayout pullRefreshLayout;



    private BaseRecyclerAdapter<NewsBean> sysAdapter;
    private Unbinder bind;
    private String orderId;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }


    private int option;
    private CdataPresenter mPresenter;
    private XuBean.ResultBean mXuBean;
    private BaseQuickAdapter<GoodListBean.ResultBean,BaseViewHolder> goodAdapter;
    private BannerBean.DataBean mBanner;
    private String mTxt;
    private String mUrl;
    private MsBean.ResultBean mMsBean;
    private String mMs;
    private boolean mIsMs=false;
    private BaseQuickAdapter<YingBean.ResultBean,BaseViewHolder> yingAdapter;
    private BaseQuickAdapter<XuBean.ResultBean, BaseViewHolder> hotAdapter;
    private BaseQuickAdapter<TaoOrderBean.ResultBean, BaseViewHolder> selfOrder;

    public static void openMain(Context context, int position) {
        Intent intent = new Intent(context, RecyActivity.class);
        intent.putExtra(POSITION, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

//
    public static void openMain(Context context, int position,String  orderId) {
        Intent intent = new Intent(context, RecyActivity.class);
        intent.putExtra(POSITION, position);
        intent.putExtra(POSITION+4, orderId);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openMain(Context context, int position, XuBean.ResultBean bean) {
        Intent intent = new Intent(context, RecyActivity.class);
        intent.putExtra(POSITION, position);
        intent.putExtra(POSITION + 1, bean);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openMain(Context context, int position, MsBean.ResultBean bean) {
        Intent intent = new Intent(context, RecyActivity.class);
        intent.putExtra(POSITION, position);
        intent.putExtra(POSITION + 3, bean);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public static void openMain(Context context, int position, BannerBean.DataBean bean) {
        Intent intent = new Intent(context, RecyActivity.class);
        intent.putExtra(POSITION, position);
        intent.putExtra(POSITION + 2, bean);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy);
        bind = ButterKnife.bind(this);
        doBusiness(this);
        setImmersionBar(2);
    }


    public void doBusiness(Context mContext) {
        option = getIntent().getIntExtra(POSITION, 0);

        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuaxin();
            }

            @Override
            public void onMove(boolean ismove) {

            }
        });
        mList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            private int totalDy = 0;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy>1000){
                    floBtn.setVisibility(View.VISIBLE);
                }else {
                    floBtn.setVisibility(View.GONE);
                }

            }
        });


        mPresenter = new CdataPresenter(this);
        switch (option) {

            case 1:
                mXuBean = (XuBean.ResultBean) getIntent().getSerializableExtra(POSITION + 1);
                mBanner = (BannerBean.DataBean) getIntent().getSerializableExtra(POSITION + 2);
                mMsBean = (MsBean.ResultBean) getIntent().getSerializableExtra(POSITION + 2);
                if (mBanner != null) {
                    mTxt = mBanner.getSlide_name();
                    mUrl = mBanner.getUrl();

                } else if (mXuBean != null) {
                    mTxt = mXuBean.getTitle();
                    mUrl = mXuBean.getHref();
                    mMs = mXuBean.getId();
                } else if (mMsBean != null) {
                    mTxt = mMsBean.getSlide_name();
                    mUrl = mMsBean.getUrl();

                     if (mMsBean.getCate_id().equals("")){

                         mIsMs = true;
                     }

                }
                title.setText(mTxt);

                initGoodList(mUrl);

                break;
            case 2:
                title.setText("英雄榜");
               initPratener();
                break;

            case 5:
                title.setText("系统通知");
                initSys();
                break;
            case 6:
                title.setText("我的消息");
                initSys();
                break;
            case 8:
                title.setText("省钱头条");
                initHot();
                break;
            case 10:
                title.setText("自营订单");
                initSelfGood();
                   msg.setText("暂无订单信息");
                break;
            case 12:
                title.setText("评论列表");
                orderId = getIntent().getStringExtra(POSITION+4);
                initComm();

                break;




        }


        floBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.smoothScrollToPosition(0);
            }
        });

    }
    BaseQuickAdapter<CommentBean.ResultBean, BaseViewHolder>  mAdapterRecy;

    private void initComm() {
        mList.addItemDecoration(new DividerItemDecoration(
                RecyActivity.this, DividerItemDecoration.HORIZONTAL_LIST));
        mList.setLayoutManager(new LinearLayoutManager(this));
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
        mList.setAdapter(mAdapterRecy);
        mAdapterRecy.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                mPresenter.getCommentList(orderId, page, commentView);

            }
        });
        mPresenter.getCommentList(orderId, 1, commentView);

    }


    BaseView<CommentBean> commentView = new BaseView<CommentBean>() {
        @Override
        public void result(CommentBean bean) {
            if (bean.getCode() == 200) {

                if (bean.getResult().size() < 10) {
                    mAdapterRecy.loadMoreEnd();
                }else {
                    mAdapterRecy.loadMoreComplete();
                }

                mAdapterRecy.addData(bean.getResult());

            } else {
                mAdapterRecy.loadMoreEnd();
            }
        }

        @Override
        public void error() {
            mAdapterRecy.loadMoreFail();
        }
    };












    private void initSelfGood() {
        mList.addItemDecoration(new DividerItemDecoration(
                RecyActivity.this, DividerItemDecoration.HORIZONTAL_LIST));
        mList.setLayoutManager(new LinearLayoutManager(this));
        selfOrder = AdapterUtil.getSelfOrder(this);
        mList.setAdapter(selfOrder);

        mPresenter.getSelfOrder(page,selfView);
        selfOrder.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                mPresenter.getSelfOrder(page,selfView);
            }
        });

    }

       BaseView<TaoOrderBean>   selfView=   new BaseView<TaoOrderBean>() {
        @Override
        public void result(TaoOrderBean bean) {
                     if (bean.getCode()==200){
                          selfOrder.addAll(bean.getResult());
                            if (bean.getResult().size()<10){
                                selfOrder.loadMoreEnd();
                            }else {
                                selfOrder.loadMoreComplete();
                            }
                     }else {
                         selfOrder.loadMoreEnd();
                         ToastUtils.toast(RecyActivity.this,bean.getMsg());
                     }
                     if ( selfOrder.getData().size()==0){
                         msg.setVisibility(View.VISIBLE);
                     }else {
                         msg.setVisibility(View.GONE);
                     }
        }

        @Override
        public void error() {
            selfOrder.loadMoreFail();
            if ( selfOrder.getData().size()==0){
                msg.setVisibility(View.VISIBLE);
            }else {
                msg.setVisibility(View.GONE);
            }
        }
    };





    private void initHot() {
        mList.addItemDecoration(new DividerItemDecoration(
                RecyActivity.this, DividerItemDecoration.HORIZONTAL_LIST));
        mList.setLayoutManager(new LinearLayoutManager(this));
        hotAdapter = new BaseQuickAdapter<XuBean.ResultBean, BaseViewHolder>( R.layout.item_text) {
            @Override
            protected void convert(BaseViewHolder helper, final XuBean.ResultBean item, int position) {
                        helper.setText(R.id.txt,(position+1)+". "+item.getTitle());

                helper.getView(R.id.all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebActivity.openMain(mContext,item.getTitle(), item.getUrl());

                    }
                });

            }
        };
       mList.setAdapter(hotAdapter);
        mPresenter.getHotHome(page,xuBeanBaseView);

        hotAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                mPresenter.getHotHome(page,xuBeanBaseView);

            }
        });

    }

    BaseView<XuBean> xuBeanBaseView=   new BaseView<XuBean>() {
        @Override
        public void result(XuBean bean) {
            if (bean.getCode()==200){
//                adapter01.setData(bean.getResult());
                hotAdapter.addAll(bean.getResult());
                  if (bean.getResult().size()<10){
                      hotAdapter.loadMoreEnd();
                  }else {
                      hotAdapter.loadMoreComplete();
                  }

            }else {
                hotAdapter.loadMoreEnd();
//                   ToastUtils.toast(getContext(),bean.getMsg());
            }
        }

        @Override
        public void error() {
            hotAdapter.loadMoreFail();
        }
    };





    private void initPratener() {
        mList.addItemDecoration(new DividerItemDecoration(
                RecyActivity.this, DividerItemDecoration.HORIZONTAL_LIST));
        mList.setLayoutManager(new LinearLayoutManager(this));
        yingAdapter = new BaseQuickAdapter<YingBean.ResultBean,BaseViewHolder>( R.layout.item_ying) {
            @Override
            protected void convert(BaseViewHolder viewHolder, YingBean.ResultBean resultBean, int position) {

                String   name=resultBean.getNickname();

                 if (name.length()>2){
                     name=name.substring(0,1)+"****"+name.substring(name.length()-1);
                 }else {
                     if (name.length()==2){
                         name=name.substring(0,1)+"****";
                     }
                 }

                viewHolder.setTextView(R.id.num,(position+1)+"")
                        .setTextView(R.id.name,name)
                        .setTextView(R.id.maney,resultBean.getMoney()+"元");
                 AdapterUtil.setImgB((SimpleDraweeView) viewHolder.getView(R.id.icon),resultBean.getAvatar());

            }
        };
                mList.setAdapter(yingAdapter);
          mPresenter.getYing(page,view);
//        yingAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                page++;
//                mPresenter.getYing(page,view);
//            }
//        });

    }

          BaseView<YingBean> view=   new BaseView<YingBean>() {
        @Override
        public void result(YingBean bean) {

                if (bean.getCode()==200){
                    yingAdapter.setNewData(bean.getResult());
                     if (bean.getResult().size()<10)
                         yingAdapter.loadMoreEnd();
                     else   yingAdapter.loadMoreComplete();

                }else {
                    yingAdapter.loadMoreEnd();
                }
        }

        @Override
        public void error() {
            yingAdapter.loadMoreFail();
        }
    };



    private void initGoodList(final String url) {

        Logger.e("ddddddd","initGoodList  url== "+url);
        mList.setLayoutManager(new LinearLayoutManager(this));
          if (mMs!=null&&mMs.equals("8")){
              Logger.e("ddddddd","initGoodList  1onSuccess== "+mMs);

          }else {
              Logger.e("ddddddd","initGoodList 2 onSuccess== "+mMs);
              goodAdapter = AdapterUtil.getSpendData(this,new ArrayList<GoodListBean.ResultBean>());
          }



        mList.setAdapter(goodAdapter);
        goodAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                mPresenter.getGoodList(url, page, mGoodListBean);
            }
        });
        mPresenter.getGoodList(url, 1, mGoodListBean);

    }

    BaseView<GoodListBean> mGoodListBean = new BaseView<GoodListBean>() {
        @Override
        public void result(GoodListBean bean) {

            if (bean.getCode() == 200) {
                goodAdapter.addAll(bean.getResult());
                if (bean.getResult().size()<10)
                    goodAdapter.loadMoreEnd();
                else   goodAdapter.loadMoreComplete();
            } else {
                goodAdapter.loadMoreEnd();
            }

        }

        @Override
        public void error() {
            goodAdapter.loadMoreFail();
        }
    };



    //
    private void initSys() {


        mList.addItemDecoration(new DividerItemDecoration(
                RecyActivity.this, DividerItemDecoration.HORIZONTAL_LIST));
        sysAdapter = new BaseRecyclerAdapter<NewsBean>(RecyActivity.this) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_sys;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, final int position, final NewsBean item) throws JSONException {
                String time = "";
                if (item.getName() != null) {
                    time = DateUtils.timet(item.getName());
                }
                 Logger.e("ddddddddd",item.toString());
                holder.setText(R.id.title, item.getTitle())
                        .setText(R.id.msg,  item.getContext())
                        .setText(R.id.start, time);
                holder.getView(R.id.del).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NewsDao.deleteShop(item.getId());
                        sysAdapter.setData();
                        sysAdapter.setData(NewsDao.queryAll());

                    }
                });
            }
        };
        mList.setLayoutManager(new LinearLayoutManager(RecyActivity.this));
        mList.setAdapter(sysAdapter);
        List<NewsBean> beanList = new ArrayList<>();
        List<NewsBean> list = NewsDao.queryAll();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                beanList.add(0, list.get(i));
            }
        }


        sysAdapter.setData(beanList);
    }





//    温馨提示


    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    int page = 1;

    public void shuaxin() {
        page = 1;
        pullRefreshLayout.setRefreshing(false);
        // adapter.setNull();
        switch (option) {
            case 1://
                goodAdapter.setNull();
                mPresenter.getGoodList(mUrl, 1, mGoodListBean);
                break;
            case 2://

                mPresenter.getYing(page,view);
                break;

            case 5://系统通知
                break;
            case 6://温馨提示
                break;
            case 8://省钱头条
                hotAdapter.setNull();
                mPresenter.getHotHome(page,xuBeanBaseView);
                break;

            case 10://自营订单
                selfOrder.setNull();
                mPresenter.getSelfOrder(page,selfView);
                break;
            case 12://自营订单
                mAdapterRecy.setNull();
                mPresenter.getCommentList(orderId, page, commentView);
                break;

        }


    }

    @Override
    public void finish() {
        setResult(20);
        super.finish();
    }

}
