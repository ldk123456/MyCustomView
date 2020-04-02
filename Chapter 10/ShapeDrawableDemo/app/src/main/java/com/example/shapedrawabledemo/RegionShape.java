package com.example.shapedrawabledemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by dekai.liu on 2020-03-18.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class RegionShape extends Shape {
    private Region mRegion;

    public RegionShape(Region region) {
        assert (region != null);
        mRegion = region;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        RegionIterator iterator = new RegionIterator(mRegion);
        Rect rect = new Rect();

        while (iterator.next(rect)) {
            canvas.drawRect(rect, paint);
        }
    }
}
