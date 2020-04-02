package com.example.shadowlayerdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-04.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class BitmapShadowView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mAlphaBitmap;
    private Rect mBitmapRect;

    private int mWidth;
    private int mHeight;

    public BitmapShadowView(Context context) {
        this(context, null);
    }

    public BitmapShadowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat_dog);
        mAlphaBitmap = mBitmap.extractAlpha();
        mWidth = 500;
        mHeight = mWidth * mAlphaBitmap.getWidth() / mAlphaBitmap.getHeight();
        mBitmapRect = new Rect(10, 10, mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.GRAY);
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(mAlphaBitmap, null, mBitmapRect, mPaint);

        canvas.translate(-10, -10);
        mPaint.setMaskFilter(null);
        canvas.drawBitmap(mBitmap, null, mBitmapRect, mPaint);
    }
}
