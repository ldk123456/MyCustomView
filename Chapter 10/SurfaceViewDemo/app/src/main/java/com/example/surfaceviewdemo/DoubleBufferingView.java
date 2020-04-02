package com.example.surfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-20.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class DoubleBufferingView extends SurfaceView {
    private Paint mPaint;

    public DoubleBufferingView(Context context) {
        super(context);
        init();
    }

    public DoubleBufferingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DoubleBufferingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(30);

        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                drawText(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    private void drawText(final SurfaceHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Canvas canvas = holder.lockCanvas();
                for (int i = 0; i < 10; i++) {
                    if (canvas != null) {
                        canvas.drawText(String.valueOf(i), i * 30, 50, mPaint);
                    }
                }
                holder.unlockCanvasAndPost(canvas);
            }
        }).start();
    }
}
