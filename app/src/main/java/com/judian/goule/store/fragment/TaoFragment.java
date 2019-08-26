package com.judian.goule.store.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ccy.ccyui.adapter.StrFragmentAdapter;
import com.judian.goule.store.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaoFragment extends Fragment {


    @BindView(R.id.taoTab)
    TabLayout taoTab;
    @BindView(R.id.taoVp)
    ViewPager taoVp;
    Unbinder unbinder;

    public TaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taobaok, container, false);
        unbinder = ButterKnife.bind(this, view);
         inintVp(getList());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private AgentFragment mData,mData1,mData2,mData3;

    private void inintVp(List<String> cates) {

        final List<Fragment> list = new ArrayList<>();

        mData = new AgentFragment();
        mData.bind("1");
        mData1 = new AgentFragment();
        mData1.bind("2");
        mData2 = new AgentFragment();
        mData2.bind("3");

        mData3 = new AgentFragment();
        mData3.bind("4");
        list.add(mData);
        list.add(mData1);
        list.add(mData2);
        list.add(mData3);

        StrFragmentAdapter adapter = new StrFragmentAdapter(getChildFragmentManager(), list, cates);
        taoVp.setAdapter(adapter);
        taoTab.setupWithViewPager(taoVp);

    }
    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("已完成");
        list.add("已结算");
        list.add("已付款");
        list.add("已失效");
        return list;
    }

}
