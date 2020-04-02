package com.example.paintdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-02-27.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class BaseLineView extends View {
    Paint mPaint;

    public BaseLineView(Context context) {
        this(context, null);
    }

    public BaseLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int baseLineX = 0;
        int baseLineY = 200;

        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(120);
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("harvic\'s blog", baseLineX, baseLineY, mPaint);

        Paint.FontMetrics fm = mPaint.getFontMetrics();
        float ascent = baseLineY + fm.ascent;
        float descent = baseLineY + fm.descent;
        float top = baseLineY + fm.top;
        float bottom = baseLineY + fm.bottom;

        mPaint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawLine(baseLineX, top, 3000, top, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawLine(baseLineX, ascent, 3000, ascent, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawLine(baseLineX, descent, 3000, descent, mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawLine(baseLineX, bottom, 3000, bottom, mPaint);
    }
}
