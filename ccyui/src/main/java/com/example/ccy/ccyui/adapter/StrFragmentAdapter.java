package com.example.ccy.ccyui.adapter;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class StrFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> strs = new ArrayList<>();
    FragmentManager  manager;

    public StrFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> strs) {
        super(fm);
        manager=fm;
        this.fragmentList = fragmentList;
        this.strs = strs;

    }


    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer!=null)super.unregisterDataSetObserver(observer);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment =null;
        fragment=fragmentList.get(position);
        Bundle bundle=new Bundle();
        bundle.putString("id",""+position);
        fragment.setArguments(bundle);
        return fragmentList.get(position);
     }


    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
          Fragment fragment= (Fragment) super.instantiateItem(container, position);
        manager.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        Fragment fragment = fragmentList.get(position);
        manager.beginTransaction().hide(fragment).commit();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (strs==null){
            return super.getPageTitle(position);
        }else {
            return  strs.get(position);
        }

    }
}
