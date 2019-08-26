package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.WithdrawalsAdapter;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.WithdrawalsBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.WithdrawalsPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WithdrawalsRecordActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.msg)
    TextView msg;


    @BindView(R.id.withdrawals_rec)
    RecyclerView withdrawalsRec;
    private WithdrawalsAdapter adapter;
    private int page = 1;

      String  mQQ="";

    private static final String POSITION = "WithdrawalsRecordActivity";
    private int mOption;
    private String url;
    private Unbinder bind;

    public static void openMain(Context context, int position) {
        Intent intent = new Intent(context, WithdrawalsRecordActivity.class);
        intent.putExtra(POSITION, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    public void doBusiness(Context mContext) {

        mOption = getIntent().getIntExtra(POSITION,0);
          switch (mOption){
              case 0:
                  title.setText("提现明细");
                  msg.setText("暂无提现记录");
                  url = HttpAPI.WITHDRAW_RECORD;
                  break;

              case 1:
                  title.setText("账户明细");
                  msg.setText("暂无账户明细");
                  url = HttpAPI.ACCOUNT_DETAIL;
                  break;
              case 2:
                  title.setText("积分记录");
                  msg.setText("暂无积分记录");
                  url = HttpAPI.USER_SCORE_LIST;
                  break;

          }

        adapter = new WithdrawalsAdapter(this,R.layout.item_withdrawals, null,mOption);



        withdrawalsRec.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        withdrawalsRec.setLayoutManager(new LinearLayoutManager(this));
        withdrawalsRec.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this);
        new WithdrawalsPresenter(mContext).getWithdrawals(url,page + "", view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_withdrawals_record);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
        doBusiness(this);

    }


    BaseView<WithdrawalsBean>  view=   new BaseView<WithdrawalsBean>() {
        @Override
        public void result(WithdrawalsBean bean) {
            if (bean.getCode()==200) {
                adapter.addData(bean.getResult());
                if (bean.getResult().size() <10) {
                    adapter.loadMoreEnd();
                } else {

                    adapter.loadMoreComplete();
                }


            } else {
                adapter.loadMoreEnd();
            }

             if (adapter.getData().size()==0){
                 msg.setVisibility(View.VISIBLE);
             }else     msg.setVisibility(View.GONE);


        }

        @Override
        public void error() {
            adapter.loadMoreFail();
            if (adapter.getData().size()==0){
                msg.setVisibility(View.VISIBLE);
            }else     msg.setVisibility(View.GONE);

        }
    };











    @Override
    public void onLoadMoreRequested() {
        ++page;
        new WithdrawalsPresenter(WithdrawalsRecordActivity.this).getWithdrawals(url,page + "",view );
    }

    @OnClick({R.id.help_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.help_back:
                finish();
                break;

        }
    }
}
