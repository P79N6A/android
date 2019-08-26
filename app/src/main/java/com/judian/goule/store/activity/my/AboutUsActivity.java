package com.judian.goule.store.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AboutUsActivity extends BaseActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        bind = ButterKnife.bind(this);
        setImmersionBar(2);
    }



    @OnClick({R.id.about_us_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.about_us_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}
