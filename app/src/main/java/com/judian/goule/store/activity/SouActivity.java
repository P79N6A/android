package com.judian.goule.store.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.example.ccy.ccyui.adapter.CommonViewHolder;
import com.example.ccy.ccyui.listener.NoDoubleClickListener;
import com.example.ccy.ccyui.util.ToastUtils;
import com.example.ccy.ccyui.view.MyGridView;
import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.bean.HotBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.utils.TestData;
import com.judian.goule.store.utils.Token;
import com.judian.goule.store.views.BaseView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SouActivity extends AppCompatActivity {


    @BindView(R.id.spend_serach)
    EditText mSpendSerach;
    @BindView(R.id.sou_btn)
    Button mSouBtn;

    @BindView(R.id.flex)
    FlexboxLayout mFlex;

    @BindView(R.id.hotGv)
    MyGridView hotGv;
    private long lastClickTime = 0;
    public static final int MIN_CLICK_DELAY_TIME = 2000;
    String url = HttpAPI.BD_SEARCH;
    private String mKeyword;
    private static final String POSITION = "RecyActivity";

    public static final String XIANSTXT = "XIANSTXT";
    private InputMethodManager imm;
    private Unbinder bind;
    private ImmersionBar mImmersionBar;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou);
        bind = ButterKnife.bind(this);
        url = getIntent().getStringExtra(POSITION + 1);
        mKeyword = getIntent().getStringExtra(POSITION);

        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        mImmersionBar
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f).init();

        initHot();
        int option = getIntent().getIntExtra(POSITION + 2, 0);
        if (option == 1) {
            Token.addKey(mKeyword);
        }
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initRecord();


        mSpendSerach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    long currentTime = Calendar.getInstance().getTimeInMillis();
                    if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                        lastClickTime = currentTime;
                        sendKey();

                    }
                    return true;
                }
                return false;
            }
        });


    }

    private void initHot() {
        new CdataPresenter(this).getHot(new BaseView<HotBean>() {
            @Override
            public void result(HotBean bean) {
                if (bean.getCode() == 200) {
                    initDv(bean.getResult());
                }

            }


            @Override
            public void error() {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        setData(Token.getHistory());
    }

    private void initDv(List<HotBean.ResultBean> result) {

        CommonBaseAdapter<HotBean.ResultBean> adapter = new CommonBaseAdapter<HotBean.ResultBean>(this, R.layout.item_hot) {
            @Override
            protected void convert(CommonViewHolder viewHolder, final HotBean.ResultBean resultBean, int position) {
                viewHolder.setTextView(R.id.txt, resultBean.getName());
                viewHolder.getView(R.id.txt).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        SearchActivity.openMain(SouActivity.this, resultBean.getName(), url);
//                        SWebActivity.openSurl(SouActivity.this,resultBean.getName());

                    }
                });
            }
        };

        hotGv.setAdapter(adapter);
        adapter.setData(result);

    }

    private void initRecord() {


        if (Token.getHistory().size() != 0) {
            mSouBtn.setVisibility(View.VISIBLE);
        } else {
            mSouBtn.setVisibility(View.GONE);
        }
    }


    private void setData(List<String> data) {
        if (mFlex == null) return;
        mFlex.setFlexDirection(FlexDirection.ROW);
        mFlex.setFlexWrap(FlexWrap.WRAP);
        mFlex.setAlignItems(AlignItems.STRETCH);
        mFlex.setAlignContent(AlignContent.CENTER);
        mFlex.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            mFlex.addView(TestData.createNewFlexItemTextView(this, data.get(i), mFlexLintener));
        }
    }

    TestData.FlexLintener mFlexLintener = new TestData.FlexLintener() {
        @Override
        public void flexOnClick(String txt) {

            SearchActivity.openMain(SouActivity.this, txt, url);

//            SWebActivity.openSurl(SouActivity.this,txt);
        }
    };


    @OnClick({R.id.back, R.id.goBtn, R.id.sou_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.goBtn://搜索
                sendKey();
                TestData.deleteClipboarbText(this);
                break;
            case R.id.sou_btn:
                Token.closeHistory();

                mFlex.setVisibility(View.GONE);
                mSouBtn.setVisibility(View.GONE);
                break;


        }
    }

    private void sendKey() {

        imm.hideSoftInputFromWindow(mSpendSerach.getWindowToken(), 0);


        String trim = mSpendSerach.getText().toString().trim();
        if (trim.equals("")) {
            ToastUtils.toast(this, "请输入关键词");
        } else {
            Token.addHistory(trim);
            setData(Token.getHistory());
            SearchActivity.openMain(SouActivity.this, trim, url);
//             SWebActivity.openSurl(SouActivity.this,trim);
        }
    }

    @Override
    protected void onPause() {
        imm.hideSoftInputFromWindow(mSpendSerach.getWindowToken(), 0);
        super.onPause();
    }


}
