package com.judian.goule.store.fragment;


import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.judian.goule.store.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderFragment extends Fragment {


    @BindView(R.id.ysIv)
    TextView mYsIv;
    @BindView(R.id.ksIv)
    TextView mKsIv;
    @BindView(R.id.num)
    TextView mNum;
    @BindView(R.id.taoVp)
    ViewPager mTaoVp;
    Unbinder unbinder;
    private MySFragment mSFragment1;
    private MyS1Fragment mSFragment;

    public MyOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        final List<Fragment>  list=new ArrayList<>();
        mSFragment = new MyS1Fragment();
                   mSFragment.bind("1");

        mSFragment.setListener(new MyS1Fragment.NumListener() {
            @Override
            public void num(String num) {

            }

            @Override
            public void onGos() {
                mTaoVp.setCurrentItem(1);
            }
        });

        mSFragment1 = new MySFragment();
        mSFragment1.bind("0");
        mSFragment1.setListener(new MySFragment.NumListener() {
            @Override
            public void num(String num) {
               if (num.equals("0"))mNum.setVisibility(View.GONE);
                mNum.setText(num);

            }
        });
          list.add(mSFragment);
          list.add(mSFragment1);

           mTaoVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

               @Override
               public int getCount() {
                   return list.size();
               }

               @Override
               public void unregisterDataSetObserver(DataSetObserver observer) {
                   if (observer!=null)super.unregisterDataSetObserver(observer);
               }
               @Override
               public Fragment getItem(int position) {
                   return list.get(position);
               }

           });

         mTaoVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


             }

             @Override
             public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mKsIv.setVisibility(View.GONE);
                        mYsIv.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mKsIv.setVisibility(View.VISIBLE);
                        mYsIv.setVisibility(View.GONE);
                        break;


                }
             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });


        return view;
    }


    public void shuaxin(){
        if (mSFragment1!=null){
            mSFragment1.shuaxin();
        }
        if (mSFragment!=null){
            mSFragment.shuaxin();
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ys, R.id.ks})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ys:
                mKsIv.setVisibility(View.GONE);
                mYsIv.setVisibility(View.VISIBLE);
                  mTaoVp.setCurrentItem(0);
                break;
            case R.id.ks:

                mKsIv.setVisibility(View.VISIBLE);
                mYsIv.setVisibility(View.GONE);
                mTaoVp.setCurrentItem(1);
                break;
        }
    }
}
