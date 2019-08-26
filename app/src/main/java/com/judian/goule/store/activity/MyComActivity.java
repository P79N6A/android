package com.judian.goule.store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ccy.ccyui.view.MyGridView;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.CommonBaseAdapter;
import com.judian.goule.store.adapter.CommonViewHolder;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.MyCommentBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyComActivity extends BaseActivity  {

    @BindView(R.id.comment)
    TextView mComment;
    @BindView(R.id.num)
    TextView mNum;
    @BindView(R.id.myGv)
    MyGridView mMyGv;

    private CommonBaseAdapter<MyCommentBean.ResultBean> mAdapter;
    private CdataPresenter mPresenter;
    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_com);
        bind = ButterKnife.bind(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.1f)
                .init();
        mPresenter = new CdataPresenter(this);

       initGv();


    }

    private void initGv() {
        mMyGv.setNumColumns(1);

        mAdapter = new CommonBaseAdapter<MyCommentBean.ResultBean>(this,R.layout.item_comment1) {
            @Override
            protected void convert(CommonViewHolder viewHolder, final MyCommentBean.ResultBean resultBean, int position) {
//                Logger.e("ddddddd","getTeamOrder ccc getOrder_id== "+resultBean.getOrder_id());
                if (resultBean.getPic()!=null){
                    viewHolder .setImageURI(R.id.godImg,"",resultBean.getPic().getPath_name()+"");
                }
                       if (resultBean.getType().equals("1")){
                           viewHolder.setTextView(R.id.corz,"赞了你的订单");
                       }else {
                           viewHolder.setTextView(R.id.corz,"评论了")
                                   .setTextView(R.id.conntent,resultBean.getApply_content())
                                   .setTextView(R.id.title,resultBean.getTitle());;
                       }


                         viewHolder.setImageURI(R.id.face,"",resultBean.getAvatar())
                                 .setTextView(R.id.name,resultBean.getNickname())

                                 .setTextView(R.id.time,resultBean.getAdd_time());

                viewHolder.getView(R.id.all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MyComActivity.this,OrderDetailsActivity.class);
                        intent.putExtra("orderId",resultBean.getCid());
                        intent.putExtra("goodId",resultBean.getNum_iid());
                        startActivityForResult(intent,35);



                    }
                });
                if (resultBean.getIs_read().equals("0")){
                    viewHolder.getView(R.id.wei).setVisibility(View.VISIBLE);
                }else {
                    viewHolder.getView(R.id.wei).setVisibility(View.GONE);
                }
            }
        };

        mMyGv.setAdapter(mAdapter);


          shuaxin();


    }

    BaseView<MyCommentBean> mView=new BaseView<MyCommentBean>() {
        @Override
        public void result(MyCommentBean bean) {

             if (bean.getCode()==200){

                 mAdapter.addAll(bean.getResult());
             }else {


             }

        }

        @Override
        public void error() {

        }
    };




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        shuaxin();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {

        finish();
    }


    int page=1;

    public void shuaxin() {
        page=1;
        mAdapter.setNull();
        mPresenter.getZanList(page,mView);

    }


    public void jiazai() {
        page++;
        mPresenter.getZanList(page,mView);
    }



    @Override
    public void finish() {
        setResult(54);
        super.finish();
    }
}
