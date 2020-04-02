package com.example.pathmeasuredemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-02-25.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class PathMeasureLengthView extends View {
    public PathMeasureLengthView(Context context) {
        this(context, null);
    }

    public PathMeasureLengthView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureLengthView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(100, 100);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
//        path.moveTo(0, 0);
//        path.lineTo(0, 100);
//        path.lineTo(100, 100);
//        path.lineTo(100, 0);
//
//        PathMeasure measure1 = new PathMeasure(path, false);
//        PathMeasure measure2 = new PathMeasure(path, true);
//
//        Log.d("PathMeasure", "forceClosed==false----->" + measure1.getLength());
//        Log.d("PathMeasure", "forceClosed==true----->" + measure2.getLength());
        path.addRect(-50, -50, 50, 50, Path.Direction.CW);
        Path dst = new Path();
        dst.lineTo(10, 100);
        PathMeasure measure = new PathMeasure(path, false);
        measure.getSegment(0, 150, dst, false);
        canvas.drawPath(dst, paint);
    }
}
