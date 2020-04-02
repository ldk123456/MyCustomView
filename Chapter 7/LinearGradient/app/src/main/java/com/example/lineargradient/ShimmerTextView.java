package com.example.lineargradient;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-06.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class ShimmerTextView extends TextView {
    private Paint mPaint;
    private int mDX;
    private LinearGradient mLinearGradient;
    public ShimmerTextView(Context context) {
        this(context,null);
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint=getPaint();
        int length= (int) mPaint.measureText(getText().toString());

        createAnim(length);
        createLinearGradient(length);
    }
    private void createAnim(int length){
        ValueAnimator animator=ValueAnimator.ofInt(0,2*length);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDX= (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2000);
        animator.start();
    }
    private void createLinearGradient(int length){
        int currentTextColor=getCurrentTextColor();
        int[] colors={currentTextColor,0xff00ff00,currentTextColor};
        float[] positions={0f,0.5f,1f};
        mLinearGradient=new LinearGradient(-length,0,0,0,colors,positions, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Matrix matrix=new Matrix();
        matrix.setTranslate(mDX,0);
        mLinearGradient.setLocalMatrix(matrix);
        mPaint.setShader(mLinearGradient);

        super.onDraw(canvas);
    }
}
