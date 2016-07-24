package com.redoc.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by limen on 2016/7/17.
 */
public class GradientShaderTextView extends TextView {
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private Paint mPaint;
    private int mViewWidth = 0;
    private int mTranslate = 0;

    private boolean mAnimating = true;
    private float delta = 6;
    private float mGradientWidth;
    private boolean mBackForward;
    private float mGradientWidthPercent;
    private float mSpeedPercent;
    public GradientShaderTextView(Context ctx)
    {
        this(ctx,null);
    }

    public GradientShaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GradientShaderTextView);
        mBackForward = typedArray.getBoolean(R.styleable.GradientShaderTextView_backForward, true);
        mGradientWidthPercent = typedArray.getFloat(R.styleable.GradientShaderTextView_gradientWidthPercentage, 0.5f);
        mSpeedPercent = typedArray.getFloat(R.styleable.GradientShaderTextView_speedPercentage, 0.5f);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                String text = getText().toString();
                if(text.length()>0)
                {
                    mGradientWidth = mViewWidth*mGradientWidthPercent;
                }else{
                    mGradientWidth = mViewWidth;
                }
                mLinearGradient = new LinearGradient(-mGradientWidth, 0, 0, 0,
                        new int[] { 0x66ffffff, 0xffffffff, 0x66ffffff },
                        new float[] { 0, 0.5f, 1 }, Shader.TileMode.CLAMP); //边缘融合
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mAnimating && mGradientMatrix != null) {
            float mTextWidth = getPaint().measureText(getText().toString());
            mTranslate += delta;
            if (mTranslate > mTextWidth + mGradientWidth + 1 || mTranslate < 1) {
                if(mBackForward) {
                    delta = -delta;
                    mGradientMatrix.setTranslate(mTranslate, 0);
                }
                else {
                    mTranslate = (int)(-mTextWidth - mGradientWidth);
                    mGradientMatrix.setTranslate(mTranslate, 0);
                    mTranslate = 0;
                }
            }
            else {
                mGradientMatrix.setTranslate(mTranslate, 0);
            }
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed((long)(60*(1-mSpeedPercent)));
        }
    }
}

