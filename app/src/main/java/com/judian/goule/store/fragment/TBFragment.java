package com.judian.goule.store.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ccy.ccyui.util.Logger;
import com.example.ccy.ccyui.util.SpUtils;
import com.example.ccy.ccyui.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.R;
import com.judian.goule.store.activity.SearchActivity;
import com.judian.goule.store.activity.SouActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TBFragment extends Fragment {


    @BindView(R.id.fl_sou_et)
    EditText flSouEt;
    @BindView(R.id.fl_sou_guan)
    ImageView flSouGuan;
    private View view;
    private Unbinder bind;

    private InputMethodManager imm;

    public TBFragment() {
        // Required empty public constructor
    }



    @Override
    public void onResume() {
        super.onResume();
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tb, container, false);
        bind = ButterKnife.bind(this, view);
        list=new ArrayList<>();
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        String  strJson = SpUtils.getInstance(getContext()).getString(SouActivity.XIANSTXT,null);

        if (strJson!=null){
            Gson gson=new Gson();
            list = gson.fromJson(strJson, new TypeToken<List<String>>() {
            }.getType());
        }



          flSouEt.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

              }
              @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {

              }

              @Override
              public void afterTextChanged(Editable s) {
                       if (flSouEt.getText().toString().equals("")){
                           flSouGuan.setVisibility(View.GONE);
                       }else {
                           flSouGuan.setVisibility(View.VISIBLE);
                       }
              }
          });








        return view;
    }

    @Override
    public void onPause() {
        imm.hideSoftInputFromWindow(flSouEt.getWindowToken(), 0) ;
        super.onPause();

    }

    @OnClick({
            R.id.fl_sou_guan,
            R.id.seekBtn})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.fl_sou_guan:
                flSouEt.setText("");
                break;
            case R.id.seekBtn:
                if (!flSouEt.getText().toString().equals("")){
                    souData();
                }else {
                    ToastUtils.toast(getContext(),"关键字不能为空");
                }
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
    List<String> list;
    private void souData() {
        String trim = flSouEt.getText().toString().trim();
        boolean  is=false;
        if (list.size()==0){
            is=false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (trim.equals(list.get(i))){
                is=true;
            }
        }
        if (list.size()>0){
            String toJson = new Gson().toJson(list);
            SpUtils.getInstance(getContext()).putString(SouActivity.XIANSTXT,toJson);
        }

        if (!is){
            list.add(0,trim);
            String toJson = new Gson().toJson(list);
            SpUtils.getInstance(getContext()).putString(SouActivity.XIANSTXT,toJson);
        }

        SearchActivity.openMain(getContext(),trim,null);

    }

}
