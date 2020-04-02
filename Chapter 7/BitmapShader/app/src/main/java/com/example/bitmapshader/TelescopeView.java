package com.example.bitmapshader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-05.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class TelescopeView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mBitmapBg;

    private int mDX = -1;
    private int mDY = -1;

    public TelescopeView(Context context) {
        this(context, null);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDX = (int) event.getX();
                mDY = (int) event.getY();
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                mDX = (int) event.getX();
                mDY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDX = -1;
                mDY = -1;
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmapBg == null) {
            mBitmapBg = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvasBg = new Canvas(mBitmapBg);
            canvasBg.drawBitmap(mBitmap, null, new Rect(0, 0, getWidth(), getHeight()), mPaint);
        }

        if (mDX != -1 && mDY != -1) {
            mPaint.setShader(new BitmapShader(mBitmapBg, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            canvas.drawCircle(mDX, mDY, 150, mPaint);
        }
    }
}
