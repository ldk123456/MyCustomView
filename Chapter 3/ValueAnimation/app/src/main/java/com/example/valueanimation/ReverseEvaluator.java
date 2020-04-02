package com.example.valueanimation;

import android.animation.TypeEvaluator;

/**
 * Created by dekai.liu on 2020-02-20.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class ReverseEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        return (int) (endValue - fraction * (endValue - startValue));
    }
}
