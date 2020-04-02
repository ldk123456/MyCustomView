package com.example.paintbasis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-02-18.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class SpiderView extends View {
    private Paint radarPaint;
    private Paint valuePaint;

    private float radius;
    private int centerX;
    private int centerY;
    private double[] data = {2, 5, 1, 6, 4, 5};
    private float maxValue = 6;

    public SpiderView(Context context) {
        this(context, null);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        radarPaint = new Paint();
        radarPaint.setStyle(Paint.Style.STROKE);
        radarPaint.setColor(Color.GREEN);

        valuePaint = new Paint();
        valuePaint.setStyle(Paint.Style.FILL);
        valuePaint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w, h) / 2.0f * 0.9f;

        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画网格
        drawPolygon(canvas);
        // 画网格中线
        drawLines(canvas);
        // 画数据图
        drawRegion(canvas);
    }

    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / 6.0f;
        for (int i = 1; i <= 6; i++) {
            float curR = r * i;
            path.reset();
            for (int j = 0; j < 6; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    float x = (float) (centerX + curR * Math.cos(Math.PI / 3 * j));
                    float y = (float) (centerY + curR * Math.sin(Math.PI / 3 * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, radarPaint);
        }
    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < 6; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(Math.PI / 3 * i));
            float y = (float) (centerY + radius * Math.sin(Math.PI / 3 * i));
            path.lineTo(x, y);
            canvas.drawPath(path, radarPaint);
        }
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(127);
        for (int i = 0; i < 6; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(Math.PI / 3 * i) * percent);
            float y = (float) (centerY + radius * Math.sin(Math.PI / 3 * i) * percent);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, 10, valuePaint);
        }
        canvas.drawPath(path, valuePaint);
    }
}
