package com.example.shadowlayerdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class ShadowLayerView extends View {
    private Paint mPaint;
    private Bitmap mDogBitmap;
    private Rect mDogRect;

    private boolean mSetShadow;

    public ShadowLayerView(Context context) {
        this(context, null);
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(25);

        mDogBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        mDogRect = new Rect(500, 50,
                500 + mDogBitmap.getWidth(), 50 + mDogBitmap.getHeight());
    }

    public void setShadow(boolean showShadow) {
        mSetShadow = showShadow;
        invalidate();
    }

    public boolean getShadow() {
        return mSetShadow;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mSetShadow) {
            mPaint.setShadowLayer(1, 10, 10, Color.GRAY);
        } else {
            mPaint.clearShadowLayer();
        }

        canvas.drawColor(Color.WHITE);
        canvas.drawText("启航", 100, 100, mPaint);
        canvas.drawCircle(300, 100, 50, mPaint);
        canvas.drawBitmap(mDogBitmap, null, mDogRect, mPaint);
    }
}
