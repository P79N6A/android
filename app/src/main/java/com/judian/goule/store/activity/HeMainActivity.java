package com.judian.goule.store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ccy.ccyui.adapter.RecyclerViewHolder;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.HeMainBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.TestData;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HeMainActivity extends BaseActivity  {

    @BindView(R.id.img)
    SimpleDraweeView mImg;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.crown)
    ImageView mCrown;
    @BindView(R.id.user_grade)
    TextView mUserGrade;
    @BindView(R.id.grade)
    TextView mGrade;
    @BindView(R.id.recy)
    RecyclerView mRecy;
    @BindView(R.id.user_id)
    TextView mId;
    @BindView(R.id.orderNum)
    TextView orderNum;
    @BindView(R.id.floBtn)
    ImageView mFloBtn;
    @BindView(R.id.title)
    TextView mTitle;
    private CdataPresenter mPresenter;
    private BaseQuickAdapter<HeMainBean.ResultBean.CommentListBean,BaseViewHolder> mHotOrder;
    private String mUserId;
    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_he_main);
        bind = ButterKnife.bind(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.2f)
                .init();


        mUserId = getIntent().getStringExtra("userId");
        mPresenter = new CdataPresenter(this);
        mHotOrder = AdapterUtil.getHeOrder(this, new AdapterUtil.OnHeorderListener() {
            @Override
            public void onclick(HeMainBean.ResultBean.CommentListBean hotBean) {

                Intent intent = new Intent(HeMainActivity.this, OrderDetailsActivity.class);
                intent.putExtra("orderId", hotBean.getId());
                intent.putExtra("goodId", hotBean.getNum_iid() + "");
                startActivityForResult(intent, 33);

            }
        });
        mRecy.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        mRecy.setAdapter(mHotOrder);

        mPresenter.getHeMain(mUserId, 1, mView);

        mHotOrder.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                jiazai();
            }
        });

    }


    BaseView<HeMainBean> mView = new BaseView<HeMainBean>() {
        @Override
        public void result(HeMainBean bean) {

            if (bean.getCode() == 200) {
                initView(bean.getResult().getUser_info());
                orderNum.setText(bean.getResult().getTotal());
                if (bean.getResult().getComment_list().size()<10){
                    mHotOrder.loadMoreEnd();
                }else mHotOrder.loadMoreComplete();
                mHotOrder.addAll(bean.getResult().getComment_list());

            } else {

                ToastUtils.toast(HeMainActivity.this, bean.getMsg());
            }
        }

        @Override
        public void error() {
            mHotOrder.loadMoreFail();
        }
    };

    private void initView(HeMainBean.ResultBean.UserInfoBean bean) {
        RecyclerViewHolder.setImg(mImg, bean.getAvatar(), 1);
        mUserName.setText(bean.getNickname());
        mId.setText("ID " + bean.getUser_id());
//           switch (bean.)
        mTitle.setText("他的主页");
        mTitle.setText("她的主页");
        TestData.setLevel(bean.getLevel(), mUserGrade, mCrown);
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();

    }

    int page = 1;


    public void shuaxin() {
        page = 1;
        mHotOrder.setNewData(null);
        mPresenter.getHeMain(mUserId, page, mView);

    }

    public void jiazai() {
        ++page;
        mPresenter.getHeMain(mUserId, page, mView);
    }

    @OnClick({R.id.back, R.id.grade, R.id.floBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.floBtn:


                break;
            case R.id.grade:
                WebActivity.openMain(this, "等级说明", HttpAPI.GRADE);
                break;
        }
    }


    public void onScrollY(int scrollY) {
        Logger.e("ddddddd", "  scrollY===" + scrollY);
        if (scrollY > 20) {
            mFloBtn.setVisibility(View.VISIBLE);
        } else {
            mFloBtn.setVisibility(View.GONE);
        }
    }

}
