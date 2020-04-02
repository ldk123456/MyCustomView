package com.example.surfaceviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dekai.liu on 2020-03-19.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class AnimationSurfaceView extends SurfaceView {
    private static final int BITMAP_STEP = 1; // 背景画布移动步伐

    private Thread mThread;
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private Bitmap mBgBitmap;       // 背景图
    private boolean mFlag = false;     // 线程标识
    private float mSurfaceWidth;    // 屏幕宽
    private float mSurfaceHeight;   // 屏幕高
    private int mBitPosX;           // 开始绘制图片的X坐标
    private State mState = State.LEFT; // 默认向右移动

    //背景移动状态
    private enum State {
        LEFT, RIGHT
    }

    public AnimationSurfaceView(Context context) {
        super(context);
        init();
    }

    public AnimationSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimationSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mFlag = true;
                startAnimation();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mFlag = false;
            }
        });
    }

    private void startAnimation() {
        mSurfaceWidth = getWidth();
        mSurfaceHeight = getHeight();
        int bgWidth = (int) (mSurfaceWidth * 3 / 2);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        mBgBitmap = Bitmap.createScaledBitmap(bitmap, bgWidth, (int) mSurfaceHeight, true);

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (mFlag) {
                    mCanvas = mSurfaceHolder.lockCanvas();
                    drawView();
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        });
        mThread.start();
    }

    private void drawView() {
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        mCanvas.drawBitmap(mBgBitmap, mBitPosX, 0, null);

        switch (mState) {
            case LEFT:
                mBitPosX -= BITMAP_STEP;
                break;
            case RIGHT:
                mBitPosX += BITMAP_STEP;
                break;
            default:
                break;
        }

        if (mBitPosX <= -mSurfaceWidth / 2) {
            mState = State.RIGHT;
        } else if (mBitPosX >= 0) {
            mState = State.LEFT;
        }
    }
}
