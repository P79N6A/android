package com.judian.goule.store.self;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.ccy.ccyui.util.PayResult;
import com.example.ccy.ccyui.util.ToastUtils;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;

import com.judian.goule.store.bean.AreaListBean;
import com.judian.goule.store.bean.OneOrderBean;
import com.judian.goule.store.bean.PayOrderBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.sf)
    TextView sf;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.gTitle)
    TextView gTitle;
    @BindView(R.id.gPrice)
    TextView gPrice;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.sum)
    TextView sum;
    @BindView(R.id.yue)
    TextView yue;
    @BindView(R.id.edit_area)
    TextView edit_area;

    @BindViews({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5})
    List<TextView> tvs;
    public static final String POSITION = "OrderDetailActivity";
    @BindView(R.id.goArea)
    RelativeLayout goArea;
    @BindView(R.id.no)
    RelativeLayout no;

    @BindView(R.id.has)
    RelativeLayout has;
    @BindView(R.id.type)
    ImageView type;
    private String goodId;
    private CdataPresenter presenter;
    private double price;
    private double goodSum;
    private String areaId="";
    private double uAble;
    private AreaListBean.ResultBean aBean;

    public static void openMain(Context context, String goodId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(POSITION, goodId);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);

        title.setText("填写订单");
        goodId = getIntent().getStringExtra(POSITION);
        presenter = new CdataPresenter(this);
        sf.setText("¥ 0.00");
        presenter.getOrderDatail(goodId, new BaseView<OneOrderBean>() {
            @Override
            public void result(OneOrderBean bean) {
                if (bean.getErrcode() == 200) {
                    initView(bean.getResult());

                } else {
//                    ToastUtils.toast(OrderDetailActivity.this, bean.getErrmsg());
                }
            }

            @Override
            public void error() {

            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
             if (resultCode==36){
                 aBean = (AreaListBean.ResultBean) data.getSerializableExtra(POSITION);
                  initArea(aBean);
             }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initArea(AreaListBean.ResultBean aBean) {

        no.setVisibility(View.GONE);
        has.setVisibility(View.VISIBLE);
        name.setText(aBean.getName());
        String phone = aBean.getPhone();
        areaId = aBean.getId();
        tel.setText(phone.substring(0, 3) + "****" + phone.substring(7, 11));
        area.setText(aBean.getProvince_name() + aBean.getCity_name() + aBean.getArea_name() + aBean.getAddress());

    }

    private void initView(OneOrderBean.ResultBean result) {
        if (result.getAddress() == null) {

        } else {

            OneOrderBean.ResultBean.AddressBean addressBean = result.getAddress();

            if (addressBean.getProvince_name()==null){

            }else {

                name.setText(addressBean.getName());
                String phone = addressBean.getPhone();
                areaId = addressBean.getId();
                if (phone.length()==11){
                    tel.setText(phone.substring(0, 4) + "****" + phone.substring(7, 11));
                }else {
                    tel.setText(phone);
                }

                no.setVisibility(View.GONE);
                has.setVisibility(View.VISIBLE);
                area.setText(addressBean.getProvince_name() + addressBean.getCity_name() + addressBean.getArea_name() + addressBean.getAddress());


            }

        }
        OneOrderBean.ResultBean.UserBean user = result.getUser();

//        if (user.getUsable()==null){
////            yue.setText("¥ 0.00");
//            uAble=0.00;
//        }else {
//            uAble = Double.valueOf( user.getUsable());
//            yue.setText("¥ "+user.getUsable());
//        }

        OneOrderBean.ResultBean.GoodsBean good = result.getGoods();
        gPrice.setText("¥ "+good.getPrice());
        sum.setText("¥ "+good.getPrice());
        price = Double.valueOf(good.getPrice());
        gTitle.setText(good.getTitle());
        Picasso.with(this).load(good.getPict_url()).into(img);
        setNum(goodNum);
    }




    int goodNum = 1;

     int zhifu=1;

    @OnClick({R.id.back, R.id.goArea, R.id.jia, R.id.jian, R.id.type, R.id.pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.goArea:
                      Intent  intent=new Intent(this,AddressListActivity.class);
                       intent.putExtra(AddressListActivity.POSITION,1);
                 startActivityForResult(intent,63);

                break;
            case R.id.pay: //支付宝 支付
//                startActivityForResult(new Intent(this,EtActivity.class),23);
                commit();
                break;



            case R.id.type:
                if (zhifu==1){
                     zhifu=2;
                    type.setImageResource(R.mipmap.switch_on);
                }else {
                    zhifu=1;
                    type.setImageResource(R.mipmap.switch_off);
                }

                setNum(goodNum);
                break;


            case R.id.jian:
                if (goodNum > 1) {
                    goodNum--;
                    setNum(goodNum);

                }


                break;
            case R.id.jia:
                goodNum++;
                setNum(goodNum);
                break;
        }
    }


    private void setNum(int goodNum){
        num.setText(goodNum + "");
        goodSum = goodNum * price;
        sum.setText("¥ "+goodSum);

        if (zhifu==2){
            if (goodSum>uAble){
                sf.setText("¥ "+(goodSum-uAble));
            }else {
                sf.setText("¥ 0.00");
            }
        }else {

            sf.setText("¥ " +goodSum);
        }

    }


    private void commit() {
        if (areaId!=null&&areaId.equals("")){
            ToastUtils.toast(this,"请选择收货地址");
        }else {
            HashMap<String ,String> params=new HashMap<>();
            params.put("address_id",areaId);
            params.put("goods_id",goodId);
            params.put("pay_type",zhifu+"");
            params.put("goods_num",goodNum+"");

        presenter.getCommitOrder(params, new BaseView<PayOrderBean>() {
            @Override
            public void result(PayOrderBean bean) {

                if (bean.getErrcode()==201){
                    aliPay(bean.getResult().getSign());
                }else if (bean.getErrcode()==200){
                    ToastUtils.toast(OrderDetailActivity.this, bean.getErrmsg());
                    finish();
                }else {
                    ToastUtils.toast(OrderDetailActivity.this, bean.getErrmsg());
                }
            }
            @Override
            public void error() {

            }});
        }

    }


    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getApplicationContext(), "操作已经取消", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "支付失败", Toast.LENGTH_SHORT).show();

                    }
                    break;

                }
                default:
                    break;
            }
        }

        ;
    };





    private static final int SDK_PAY_FLAG = 1;
    private void aliPay(final String orderInfo) {

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderDetailActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


}
