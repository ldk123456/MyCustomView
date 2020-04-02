package com.example.pathmeasuredemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-02-26.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class AliPayView extends View {
    private Paint mPaint;
    private Path mDstPath;
    private Path mCirclePath;
    private PathMeasure mPathMeasure;
    private float mCurAnimValue;
    private boolean mNext;

    public AliPayView(Context context) {
        this(context, null);
    }

    public AliPayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AliPayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);

        mDstPath = new Path();
        mCirclePath = new Path();
        mCirclePath.addCircle(100, 100, 50, Path.Direction.CW);
        mCirclePath.moveTo(100 - 50 / 2, 100);
        mCirclePath.lineTo(100, 100 + 50 / 2);
        mCirclePath.lineTo(100 + 50 / 2, 100 - 50 / 3);

        mPathMeasure = new PathMeasure(mCirclePath, false);

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 2f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurAnimValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(4000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        if (mCurAnimValue < 1) {
            float stop = mPathMeasure.getLength() * mCurAnimValue;
            mPathMeasure.getSegment(0, stop, mDstPath, true);
        } else {
            if (!mNext) {
                mNext = true;
                mPathMeasure.getSegment(0, mPathMeasure.getLength(), mDstPath, true);
                mPathMeasure.nextContour();
            }
            float stop = mPathMeasure.getLength() * (mCurAnimValue - 1);
            mPathMeasure.getSegment(0, stop, mDstPath, true);
        }
        canvas.drawPath(mDstPath, mPaint);
    }
}
