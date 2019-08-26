package com.judian.goule.store.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.judian.goule.store.R;
import com.judian.goule.store.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();
    List<ImageBean> strs = new ArrayList<>();
    FragmentManager  manager;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<ImageBean> strs) {
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



    public View getTabView(Context context,int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_order, null);
        TextView tv = (TextView) v.findViewById(R.id.news_title);
        tv.setText(strs.get(position).getTxt());
        tv.setTextColor(context.getResources().getColor(R.color.dark_grey));
        ImageView iv = (ImageView) v.findViewById(R.id.img);
        iv.setImageResource(strs.get(position).getIocn());
        return v;
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
            return  strs.get(position).getTxt();
        }

    }
}
