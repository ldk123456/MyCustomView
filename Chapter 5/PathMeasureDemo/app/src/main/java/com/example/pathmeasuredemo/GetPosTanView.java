package com.example.pathmeasuredemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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
public class GetPosTanView extends View {
    private Paint mPaint;
    private Path mDstPath;
    private Path mCirclePath;
    private PathMeasure mPathMeasure;
    private Bitmap mArrowBitmap;
    private Matrix mMatrix;

    private float mCurAnimValue;
    private float[] pos = new float[2];
    private float[] tan = new float[2];

    public GetPosTanView(Context context) {
        this(context, null);
    }

    public GetPosTanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GetPosTanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        mPathMeasure = new PathMeasure(mCirclePath, true);

        mArrowBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arraw);
        mMatrix = new Matrix();

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurAnimValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        float length = mPathMeasure.getLength();
        float stop = length * mCurAnimValue;
//        float start = (float) (stop - ((0.5 - Math.abs(mCurAnimValue - 0.5)) * length));

        mDstPath.reset();
        mPathMeasure.getSegment(0, stop, mDstPath, true);
        canvas.drawPath(mDstPath, mPaint);

//        mPathMeasure.getPosTan(stop, pos, tan);
//        float degree = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
//        Matrix matrix = new Matrix();
//        matrix.postRotate(degree, mArrowBitmap.getWidth() / 2, mArrowBitmap.getHeight() / 2);
//        matrix.postTranslate(pos[0] - mArrowBitmap.getWidth() / 2, pos[1] - mArrowBitmap.getHeight() / 2);
        mMatrix.reset();
        mPathMeasure.getMatrix(stop, mMatrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        mMatrix.preTranslate(-mArrowBitmap.getWidth() / 2, -mArrowBitmap.getHeight() / 2);
        canvas.drawBitmap(mArrowBitmap, mMatrix, mPaint);
    }
}
