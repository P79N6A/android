package com.judian.goule.store.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ccy.ccyui.adapter.StrFragmentAdapter;
import com.example.ccy.ccyui.util.Logger;
import com.judian.goule.store.R;
import com.judian.goule.store.view.SelfOrderDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    @BindView(R.id.taoTab)
    TabLayout taoTab;


    @BindView(R.id.taoVp)
    ViewPager taoVp;

    @BindView(R.id.addOrder)
    LinearLayout addOrder;

    SelfOrderDialog dialog;
    private int mOption;
    private MyAgentFragment mData;
    private MyAgentFragment mData1;
    private MyAgentFragment mData2;
    private MyAgentFragment mData3;

    Unbinder unbinder;
    private String order;

    private String key = "";

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taobaok, container, false);
        unbinder = ButterKnife.bind(this, view);
//        addOrder.setVisibility(View.VISIBLE);
        dialog = new SelfOrderDialog(getContext(), order);
        if (key != null) {
            if (!key.equals("")) {
                dialog.setKey(key);
            }
        }
        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

            }
        });
        inintVp(getList());
        return view;
    }


    public void bind(String order) {
        this.order = order;

    }

    public void bind(String order, int mOption) {
        this.order = order;
        this.mOption = mOption;
        Logger.e("ccccccc", order + "bind mOption== " + mOption);

    }

    private void inintVp(List<String> cates) {
        final List<Fragment> list = new ArrayList<>();
        mData = new MyAgentFragment();
        mData.bind("1", order);

        mData1 = new MyAgentFragment();
        mData1.bind("2", order);
        mData2 = new MyAgentFragment();
        mData2.bind("3", order);
        mData3 = new MyAgentFragment();
        mData3.bind("0", order);
        list.add(mData3);
        list.add(mData);
        list.add(mData1);
        list.add(mData2);
        StrFragmentAdapter adapter = new StrFragmentAdapter(getChildFragmentManager(), list, cates);
        taoVp.setAdapter(adapter);
        taoTab.setupWithViewPager(taoVp);
        Logger.e("ccccccc", order + "bind inintVp== " + mOption);
        taoVp.setCurrentItem(mOption);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("已付款");
        list.add("已失效");
        list.add("已结算");
        return list;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
