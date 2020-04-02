package com.example.objectanimator;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Created by dekai.liu on 2020-02-21.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class FallingBallEvaluator implements TypeEvaluator<Point> {
    private Point point = new Point();

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        point.x = (int) (startValue.x + fraction * (endValue.x - startValue.x));
        if (fraction * 2 <= 1) {
            point.y = (int) (startValue.y + fraction * 2 * (endValue.y - startValue.y));
        } else {
            point.y = endValue.y;
        }
        return point;
    }
}
