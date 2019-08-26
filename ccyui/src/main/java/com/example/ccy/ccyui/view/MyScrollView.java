package com.example.ccy.ccyui.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.example.ccy.ccyui.util.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chinanet on 16/11/18.
 */

public class MyScrollView extends ScrollView {
    //获取 Y轴滚动距离

    int lastY;
    int  topY;

    public void setTopY(int topY) {
        this.topY = topY;
    }

    public MyScrollView(Context context) {
        this(context, null,0);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//          Timer  timer=new Timer();
//          timer.schedule(new TimerTask() {
//              @Override
//              public void run() {
//                  if (listenter!=null){
//                      Logger.d("ddddd","Timer   scrollY==  "+getScrollY());
//                      try {
//                          listenter.onScrollY(getScrollY());
//                      }catch (Exception e){
//                          Logger.d("ddddd","Timer   Exception==  "+e);
//                      }
//
//
//                  }
//              }
//          },10,200);

    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if((getHeight() + getScrollY()+200)>= (getChildAt(0).getMeasuredHeight())){
            if (load){
                if(listenter != null){
                    listenter.onBottom();
                }
            }

        }
    }

    boolean  load=false;
    public  void  setLoading(boolean loading){
        load=loading;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        if (listenter!=null){
            listenter.onScrollY(scrollY);

        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        switch (ev.getAction()){

            case MotionEvent.ACTION_UP: //判断手指离开屏幕时

                handler.sendMessageDelayed(handler.obtainMessage(),1);

                break;


        }

        return super.onTouchEvent(ev);
    }

    Handler  handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int scrollY=getScrollY();
            if (scrollY!=lastY){

                lastY=scrollY;

                handler.sendMessageDelayed(handler.obtainMessage(),1);

            }
//              if (listenter!=null){
//
//                  listenter.onScrollY(lastY);
//
//              }


        }
    };




    public void setListenter(OnScrollListenter listenter) {
        this.listenter = listenter;
    }

    OnScrollListenter  listenter;

    public   interface OnScrollListenter{
        public void onBottom();
        public void onScrollY(int scrollY);
    }



}
