package com.example.xfermodedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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
public class LightBookView extends View {
    private Paint mPaint;
    private Bitmap mDSTBitmap;
    private Bitmap mSRCBitmap;

    public LightBookView(Context context) {
        this(context, null);
    }

    public LightBookView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LightBookView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();

        mDSTBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.twiter_bg, null);
        mSRCBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.twiter_light, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mDSTBitmap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(mSRCBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }
}
