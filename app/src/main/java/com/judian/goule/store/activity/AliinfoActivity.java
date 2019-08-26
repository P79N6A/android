package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AliinfoActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.aliTv)
    TextView mAliTv;
    @BindView(R.id.name)
    TextView mName;


    private static final String POSITION = "AliinfoActivity";
    private Unbinder bind;

    public static void openMain(Context context, String  ali,String name) {
        Intent intent = new Intent(context, AliinfoActivity.class);
        intent.putExtra(POSITION, ali);
        intent.putExtra(POSITION+1, name);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView( R.layout.activity_aliinfo);
        bind = ButterKnife.bind(this);
        doBusiness(this);
        setImmersionBar(2);
    }


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }


    public void doBusiness(Context mContext) {
              String ali=getIntent().getStringExtra(POSITION);
              String name=getIntent().getStringExtra(POSITION+1);
        mTitle.setText("我的支付宝");
        mName.setText(name);
              mAliTv.setText(ali);




    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
