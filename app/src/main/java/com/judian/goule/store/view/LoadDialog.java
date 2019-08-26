package com.judian.goule.store.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.judian.goule.store.R;


/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class LoadDialog extends Dialog {


    private final Context context;



    public LoadDialog(Context context ) {
        super(context, R.style.MyDialog);
        this.context = context;


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (lintener!=null)lintener.onStart();

    }
  public   onStartLintener   lintener;

    public void setLintener(onStartLintener lintener) {
        this.lintener = lintener;
    }

    public  interface    onStartLintener{
      void  onStart();


  }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_load);
        setCanceledOnTouchOutside(true);

        //初始化界面控件
        initView();
        //初始化界面数据
         initData();
        //初始化界面控件的事件
    }




    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {



    }

    /**
     * 初始化界面控件
     */


    private void initView() {
//        GifView  img= (GifView) findViewById(R.id.img);
        ImageView  img= (ImageView) findViewById(R.id.img);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, com.example.ccy.ccyui.R.anim.loading);
//        // 使用ImageView显示动画
        img.startAnimation(hyperspaceJumpAnimation);
//        img.setMovieResource(R.raw.a123);
//        setCancelable(false);
//      setCanceledOnTouchOutside(false);

    }


}
