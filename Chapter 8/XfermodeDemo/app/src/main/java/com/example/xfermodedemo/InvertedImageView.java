package com.example.xfermodedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-16.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class InvertedImageView extends View {
    private Paint mPaint;
    private Bitmap mDSTBitmap;
    private Bitmap mSRCBitmap;
    private Bitmap mRevertBitmap;

    public InvertedImageView(Context context) {
        this(context, null);
    }

    public InvertedImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InvertedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mDSTBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog_invert_shade, null);
        mSRCBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);

        Matrix matrix = new Matrix();
        matrix.setScale(1f, -1f);
        mRevertBitmap = Bitmap.createBitmap(mSRCBitmap, 0, 0,
                mSRCBitmap.getWidth(), mSRCBitmap.getHeight(), matrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() / 2;
        int height = width * mDSTBitmap.getHeight() / mDSTBitmap.getWidth();
        canvas.drawBitmap(mSRCBitmap, null, new RectF(0, 0, width, height), mPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0, height);

        canvas.drawBitmap(mDSTBitmap, null, new RectF(0, 0, width, height), mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mRevertBitmap, null, new RectF(0, 0, width, height), mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }
}
