package com.example.surfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dekai.liu on 2020-03-19.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class SurfaceGestureView extends SurfaceView {
    private static final String TAG = "SurfaceGestureView";

    private Paint mPaint;
    private Path mPath;

    public SurfaceGestureView(Context context) {
        super(context);
        init();
    }

    public SurfaceGestureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceGestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);

        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                drawCanvas();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void drawCanvas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SurfaceHolder holder = getHolder();
                holder.addCallback(new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {

                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {

                    }
                });
                Canvas canvas = holder.lockCanvas();
                canvas.drawPath(mPath, mPaint);
                holder.unlockCanvasAndPost(canvas);
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: onDraw");

        canvas.drawPath(mPath, mPaint);
    }
}
