package com.judian.goule.store.activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.ccy.ccyui.util.Logger;
import com.judian.goule.store.MainActivity;
import com.judian.goule.store.R;
import com.judian.goule.store.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShowActivity extends BaseActivity {


    int icon[] = {R.mipmap.show1, R.mipmap.show2};
    List<ImageView> ivs;
    @BindView(R.id.show_vp)
    ViewPager showVp;

    @BindView(R.id.liner)
    LinearLayout liner;
    private int mState;
    private boolean isStart=true;
    private List<ImageView> list;
    @BindView(R.id.close)
    ImageView close;
    private Unbinder bind;

    @Override
    protected void onResume() {
         isShowC=false;
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        bind = ButterKnife.bind(this);
//        getSwipeBackLayout().setEnableGesture(false);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        ivs = new ArrayList<>();
        for (int i = 0; i < icon.length; i++) {
            ImageView iv = new ImageView(this);
            ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
            iv.setImageResource(icon[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            ivs.add(iv);
        }


        list = new ArrayList<>();
        for (int i = 0; i < icon.length; i++) {
            ImageView iv=new ImageView(this);
            iv.setPadding(10,10,10,10);
            liner.addView(iv);
            list.add(iv);
        }
            initIv(0);
        showVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return ivs.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(ivs.get(position));
                return ivs.get(position);
            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {
                if (observer != null) {
                    super.unregisterDataSetObserver(observer);
                }
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//          container.removeView((View) object);
                container.removeView(ivs.get(position));
            }


        });


        showVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                 initIv(position);

                if (position == icon.length - 1) {
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(ShowActivity.this, MainActivity.class));
                            finish();
                        }
                    });
                } else {
                    close.setOnClickListener(null);
                }
                if (mState==1){
                    if (positionOffsetPixels==0&&positionOffset==0.0&&position==icon.length-1&&isStart){
                        startActivity(new Intent(ShowActivity.this,MainActivity.class));
                        finish();
                        isStart =false;

                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                  mState =state;
            }
        });

    }


  private void initIv(int option){
      for (int i = 0; i < list.size(); i++) {
          list.get(i).setImageResource(R.mipmap.ic_dot_normal);

      }
      list.get(option).setImageResource(R.mipmap.ic_dot_pressed);

  }

}
