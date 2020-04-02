package com.example.bitmapshader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-05.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class AvatorView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private Shader mBitmapShader;

    public AvatorView(Context context) {
        this(context, null);
    }

    public AvatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avator);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Matrix matrix = new Matrix();
        float scale = (float) getWidth() / mBitmap.getWidth();
        matrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(matrix);
        mPaint.setShader(mBitmapShader);

        float half = getHeight() / 2;
        canvas.drawCircle(half, half, getWidth() / 2, mPaint);
    }
}
