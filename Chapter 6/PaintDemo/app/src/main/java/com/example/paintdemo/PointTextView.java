package com.example.paintdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-02.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class PointTextView extends View {
    private Paint mPaint;

    public PointTextView(Context context) {
        this(context, null);
    }

    public PointTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String text = "harvic\'s blog";
        int top = 200;
        int baseLineX = 0;

        mPaint.setTextSize(120);
        mPaint.setTextAlign(Paint.Align.LEFT);

        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(baseLineX, top, 3000, top, mPaint);

        Paint.FontMetricsInt fm = mPaint.getFontMetricsInt();
        int baseLineY = top - fm.top;

        mPaint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawText(text, baseLineX, baseLineY, mPaint);
    }
}
