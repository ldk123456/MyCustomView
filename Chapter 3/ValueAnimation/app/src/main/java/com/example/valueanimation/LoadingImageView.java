package com.example.valueanimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-02-20.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class LoadingImageView extends ImageView {
    private int mTop;
    private int mCurImgIndex=0;
    private static int mImgCount=3;

    public LoadingImageView(Context context) {
        this(context, null);
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 300, 0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer dx = (Integer) animation.getAnimatedValue();
                setTop(mTop - dx);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setImageDrawable(getResources().getDrawable(R.drawable.pic_1));
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                mCurImgIndex++;
                switch (mCurImgIndex%mImgCount){
                    case 0:
                        setImageDrawable(getResources().getDrawable(R.drawable.pic_1));
                        break;
                    case 1:
                        setImageDrawable(getResources().getDrawable(R.drawable.pic_2));
                        break;
                    case 2:
                        setImageDrawable(getResources().getDrawable(R.drawable.pic_3));
                        break;
                    default:
                        break;
                }
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mTop = top;
    }
}
