package com.example.xfermodedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-16.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class EraserView extends View {
    private Paint mPaint;
    private Bitmap mDSTBitmap;
    private Bitmap mSRCBitmap;
    private Bitmap mTextBitmap;
    private Path mPath;
    private float mPreX;
    private float mPreY;

    public EraserView(Context context) {
        this(context, null);
    }

    public EraserView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EraserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(45);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        mSRCBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog, options);
        mDSTBitmap = Bitmap.createBitmap(mSRCBitmap.getWidth(), mSRCBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mTextBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guaguaka_text, null);

        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPreX = event.getX();
                mPreY = event.getY();
                mPath.moveTo(mPreX, mPreY);
                return true;
            case MotionEvent.ACTION_MOVE:
                float curX = event.getX();
                float curY = event.getY();
                float endX = (mPreX + curX) / 2;
                float endY = (mPreY + curY) / 2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = curX;
                mPreY = curY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mTextBitmap, null, new RectF(0, 0, mDSTBitmap.getWidth(), mDSTBitmap.getHeight()), mPaint);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        Canvas c = new Canvas(mDSTBitmap);
        c.drawPath(mPath, mPaint);

        canvas.drawBitmap(mDSTBitmap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(mSRCBitmap, 0, 0, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }
}
