package com.judian.goule.store.self;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccy.ccyui.util.ToastUtils;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.CityListActivity;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.AreaDetailBean;
import com.judian.goule.store.bean.AreaListBean;
import com.judian.goule.store.bean.BaseBean;
import com.judian.goule.store.bean.CityBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EtActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ensure)
    TextView ensure;
    @BindView(R.id.save)
    TextView save;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.area)
    EditText area;
    @BindViews({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5})
    List<TextView> tvs;

    public static final String POSITION = "EtActivity";
    String  areaId;
    @BindView(R.id.morIm)
    ImageView morIm;
    private CdataPresenter presenter;
    AreaListBean.ResultBean areaBean;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et);
        ButterKnife.bind(this);
        setImmersionBar(2);
        title.setText("编辑收货地址");
        ensure.setText("删除");
        areaBean = (AreaListBean.ResultBean) getIntent().getSerializableExtra(POSITION);
        presenter = new CdataPresenter(this);
        if (areaBean!=null){
            areaId=areaBean.getId();
            String str = areaBean.getProvince_name() + areaBean.getCity_name() + areaBean.getArea_name();
            address.setText(str);
            name.setText(areaBean.getName());
            tel.setText(areaBean.getPhone());
            area.setText(areaBean.getAddress());
             if (areaBean.getState().equals("1")){
                 morIm.setImageResource(R.mipmap.sel_area);
             }

            presenter.getAddressDet(areaBean.getId(), new BaseView<AreaDetailBean>() {
                @Override
                public void result(AreaDetailBean bean) {

                     if (bean.getCode().equals("200")){
                         AreaDetailBean.ResultBean areaBean=bean.getResult();

                         province_id =areaBean.getProvince_id();
                         city_id =areaBean.getCity_id();
                         area_id =areaBean.getArea_id();


                     }else {
                         ToastUtils.toast(EtActivity.this,bean.getMsg());
                     }
                }

                @Override
                public void error() {

                }
            });
        }else {
            address.setText("请选择所在区域");
        }

    }

//    private void setFont() {
//        for (int i = 0; i < tvs.size(); i++) {
//            tvs.get(i).setTypeface(MyApplication.fontFace);
//        }
//        title.setTypeface(MyApplication.fontFace);
//        ensure.setTypeface(MyApplication.fontFace);
//        save.setTypeface(MyApplication.fontFace);
//        name.setTypeface(MyApplication.fontFace);
//        tel.setTypeface(MyApplication.fontFace);
//        address.setTypeface(MyApplication.fontFace);
//        area.setTypeface(MyApplication.fontFace);
//    }

    @OnClick({R.id.back, R.id.ensure, R.id.save, R.id.mor, R.id.areaB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ensure:
                del();
                break;
            case R.id.save:

                save();
                break;
            case R.id.mor:
                if (option == 0) {
                    option=1;
                    morIm.setImageResource(R.mipmap.sel_area);
                }else {
                    option=0;
                    morIm.setImageResource(R.mipmap.no_area);
                }
                break;
            case R.id.areaB:
                startActivityForResult(new Intent(this, CityListActivity.class), 24);
                break;
        }
    }

    private CityBean.ResultBean province, city, areaN;
       String  province_id,city_id,area_id;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 32) {
            province = (CityBean.ResultBean) data.getSerializableExtra("shengname");
            city = (CityBean.ResultBean) data.getSerializableExtra("shiname");
            areaN = (CityBean.ResultBean) data.getSerializableExtra("quname");
            address.setText(province.getName() + "--" + city.getName() + "--" + areaN.getName());
            province_id=province.getId();
            city_id=city.getId();
            area_id=areaN.getId();
        }
        super.onActivityResult(requestCode, resultCode, data);


    }

    int option = 0;

    //    保存
    private void save() {
        String nameTxt = name.getText().toString().trim();
        String telTxt = tel.getText().toString().trim();
        String addressTxt = address.getText().toString().trim();
        String areaTxt = area.getText().toString().trim();
        if (nameTxt.equals("")) {
            ToastUtils.toast(this, "请输入收货人姓名");
        } else if (telTxt.equals("")) {
            ToastUtils.toast(this, "请输入收货人手机号");
        } else if (addressTxt.equals("请选择所在区域")) {
            ToastUtils.toast(this, "请选择所在区域");
        } else if (areaTxt.equals("")) {
            ToastUtils.toast(this, "请输入详细地址");
        } else {

            HashMap<String,String> params = new  HashMap<String,String>();
            params.put("name", nameTxt);
            params.put("address", areaTxt);
            params.put("sphone", telTxt);
            params.put("state", option+"");
            params.put("province_id", province_id);
            params.put("city_id", city_id);
            params.put("area_id", area_id);
            if (areaId==null){

                presenter.getsaveArea(params, new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        ToastUtils.toast(EtActivity.this, bean.getMsg());
                        if (bean.getCode() == 200) {
                            finish();
                        }
                    }

                    @Override
                    public void error() {
                    }
                });

            }else {
                params.put("address_id", areaId);
                presenter.getsUpArea(params, new BaseView<BaseBean>() {
                    @Override
                    public void result(BaseBean bean) {
                        ToastUtils.toast(EtActivity.this, bean.getMsg());
                        if (bean.getCode() == 200) {
                            finish();
                        }

                    }

                    @Override
                    public void error() {

                    }
                });

            }


        }


    }

    //  删除
    private void del() {
        if (areaId!=null){
            presenter.getDelAddress(areaId, new BaseView<BaseBean>() {
                @Override
                public void result(BaseBean bean) {
                    ToastUtils.toast(EtActivity.this, bean.getMsg());
                    if (bean.getCode() == 200) {
                        finish();
                    }
                }

                @Override
                public void error() {

                }
            });
        }


    }

    @Override
    public void finish() {
        setResult(23);
        super.finish();
    }
}
