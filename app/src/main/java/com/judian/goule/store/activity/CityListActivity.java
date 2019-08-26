package com.judian.goule.store.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.example.ccy.ccyui.adapter.CommonViewHolder;
import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.ToastUtils;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;
import com.judian.goule.store.bean.CityBean;
import com.judian.goule.store.http.HttpAPI;
import com.judian.goule.store.http.HttpClient;
import com.judian.goule.store.presenter.CdataPresenter;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CityListActivity extends BaseActivity {

    @BindView(R.id.add_list)
    ListView add_list;
    private CityBean.ResultBean shiname, shengname, quname;
    private CommonBaseAdapter<CityBean.ResultBean> adapter;

     int option;
    private CdataPresenter presenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView( R.layout.activity_city_list);
        bind = ButterKnife.bind(this);
        doBusiness(this);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.2f)
                .init();
    }


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }



    public void doBusiness(Context mContext) {
        presenter = new CdataPresenter(this);
        adapter = new CommonBaseAdapter<CityBean.ResultBean>(getApplicationContext(), R.layout.cate_item2) {
            @Override
            protected void convert(CommonViewHolder viewHolder, final CityBean.ResultBean dataBean, int  p) {
                viewHolder.setTextView(R.id.title,dataBean.getName());
            }
        };
        add_list.setAdapter(adapter);

        add_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (option==1){
                    shengname=adapter.getmData().get(position);
                    city(shengname.getId());
                }else if (option==2){
                    shiname=adapter.getmData().get(position);
                    area(shiname.getId());
                }else {
                    if (option == 3) {
                        quname = adapter.getmData().get(position);
                        Logger.e("ddddddd","area  onSuccess"+quname.getName());
                        Intent intent=new Intent();
                        intent.putExtra("shengname", shengname);
                        intent.putExtra("shiname", shiname);
                        intent.putExtra("quname", quname);
                        setResult(32,intent);
                        finish();
                    }
                }
            }
        });
        province();
    }

    private void province() {
        Log.d("vvvvvvvv", "city: "+1);
        HttpClient.getInstance(this).post(HttpAPI.SHENGC , null, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd","province  onSuccess"+response);
                CityBean cityBean = new Gson().fromJson(response,CityBean.class);
                if (cityBean.getCode()==200){
                    adapter.setData(cityBean.getResult());
                    option=1;
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(CityListActivity.this,"获取数据失败");
                Logger.e("ddddddd","getHomeData  onFailure"+error_msg);
            }
        });
    }
    private void area(String id) {
        HashMap<String ,String> params = new HashMap<>();
        params.put("id", id);
        Log.d("vvvvvvvv", "city: "+id);
        HttpClient.getInstance(this).post(HttpAPI.SHI , params,new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd","area  onSuccess"+response);
                CityBean cityBean = new Gson().fromJson(response,CityBean.class);
                if (cityBean.getCode()==200){
                    adapter.setData(cityBean.getResult());
                    option=3;
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(CityListActivity.this,"获取数据失败");
                Logger.e("ddddddd","getHomeData  onFailure"+error_msg);
            }
        });
    }
    private void city(String id) {
        HashMap<String ,String> params = new HashMap<>();
        params.put("id", id);
        Log.d("vvvvvvvv", "city: "+id);
        HttpClient.getInstance(this).post(HttpAPI.QV , params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("ddddddd","city  onSuccess"+response);
                CityBean cityBean = new Gson().fromJson(response,CityBean.class);
                if (cityBean.getCode()==200){
                    adapter.setData(cityBean.getResult());
                    option=2;
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.toast(CityListActivity.this,"获取数据失败");
                Logger.e("ddddddd","getHomeData  onFailure"+error_msg);
            }
        });
    }







    @OnClick(R.id.list_topIv)
    public void onClick() {
        finish();
    }
}
