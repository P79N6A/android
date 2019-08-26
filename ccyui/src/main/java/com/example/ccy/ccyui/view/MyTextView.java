package com.example.ccy.ccyui.view;

/**
 * Created by Administrator on 2017/5/25.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private Matrix matrix;
    private int mViewWidth = 0;
    private Rect mTextBound = new Rect();
    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private int width;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w!=0){
            width=w;
        }else{
            width=getMeasuredWidth();
        }
        mPaint=getPaint();
        mLinearGradient=new LinearGradient(-width, 0,0,0,
                new int[]{Color.RED,Color.GREEN,Color.MAGENTA},
                new float[]{0,0.5f,1f},
                Shader.TileMode.CLAMP);
        //添加渲染
        mPaint.setShader(mLinearGradient);
        mPaint.setColor(Color.BLACK);
        matrix=new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mViewWidth = getMeasuredWidth();
        mPaint = getPaint();
        String mTipText = getText().toString();
        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);
        mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                new int[] {  0xFFFE5858,0xFFFF7D01},
                null, Shader.TileMode.REPEAT);
        mPaint.setShader(mLinearGradient);
        canvas.drawText(mTipText, getMeasuredWidth() / 2 - mTextBound.width() / 2, getMeasuredHeight() / 2 +   mTextBound.height()/2, mPaint);
    }

}
