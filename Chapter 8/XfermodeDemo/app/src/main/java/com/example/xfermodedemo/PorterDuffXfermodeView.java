package com.example.xfermodedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-09.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class PorterDuffXfermodeView extends View {
    private Paint mPaint;
    private Bitmap mDstBitmap;
    private Bitmap mSrcBitmap;

    private int mWidth = 200;
    private int mHeight = 200;

    public PorterDuffXfermodeView(Context context) {
        this(context, null);
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();

        mDstBitmap = makeDst();
        mSrcBitmap = makeSrc();
    }

    private Bitmap makeDst() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(0XFFFFCC44);
        canvas.drawOval(new RectF(0, 0, mWidth, mHeight), paint);

        return bitmap;
    }

    private Bitmap makeSrc() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(0XFF66AAFF);
        canvas.drawRect(0, 0, mWidth, mHeight, paint);

        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mDstBitmap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mSrcBitmap, 100, 100, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }
}
