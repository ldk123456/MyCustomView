package com.example.valueanimation;

import android.animation.TypeEvaluator;

/**
 * Created by dekai.liu on 2020-02-20.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class CharEvalutor implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int curInt = (int) (startInt + fraction * (endInt - startInt));
        return (char) curInt;
    }
}
