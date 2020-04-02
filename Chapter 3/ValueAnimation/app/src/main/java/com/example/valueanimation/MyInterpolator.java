package com.example.valueanimation;

import android.animation.TimeInterpolator;

/**
 * Created by dekai.liu on 2020-02-20.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class MyInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return 1 - input;
    }
}
