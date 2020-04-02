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
public class PathFillView extends View {
    public PathFillView(Context context) {
        super(context);
    }

    public PathFillView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathFillView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.addRect(100, 100, 300, 300, Path.Direction.CW);
        path.addCircle(300, 300, 100, Path.Direction.CW);
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
    }
}
