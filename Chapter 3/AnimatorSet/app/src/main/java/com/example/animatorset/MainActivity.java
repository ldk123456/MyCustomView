package com.example.animatorset;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MAIN_ACTIVITY";

    private Button mStartBtn;
    private TextView mTextView1;
    private TextView mTextView2;

    private boolean mIsMenuOpen = false;
    private Button mMenu;
    private Button mItem1;
    private Button mItem2;
    private Button mItem3;
    private Button mItem4;
    private Button mItem5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        initMenu();

//        mStartBtn = findViewById(R.id.start_btn);
//        mTextView1 = findViewById(R.id.text_1);
//        mTextView2 = findViewById(R.id.text_2);

//        animation();
    }

    private void initMenu() {
        mMenu = findViewById(R.id.menu);
        mItem1 = findViewById(R.id.item1);
        mItem2 = findViewById(R.id.item2);
        mItem3 = findViewById(R.id.item3);
        mItem4 = findViewById(R.id.item4);
        mItem5 = findViewById(R.id.item5);

        mMenu.setOnClickListener(this);
        mItem1.setOnClickListener(this);
        mItem2.setOnClickListener(this);
        mItem3.setOnClickListener(this);
        mItem4.setOnClickListener(this);
        mItem5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu:
                doClicked(v);
                doMenuClicked();
                break;
            case R.id.item1:
            case R.id.item2:
            case R.id.item3:
            case R.id.item4:
            case R.id.item5:
                doClicked(v);
                break;
            default:
                break;
        }
    }

    private void doMenuClicked() {
        if (mIsMenuOpen) {
            mIsMenuOpen = false;
            closeMenu();
        } else {
            mIsMenuOpen = true;
            openMenu();
        }
    }

    private void doClicked(View view) {
        if (view != null) {
            Toast.makeText(this, "you clicked " + view, Toast.LENGTH_SHORT).show();
        }
    }

    private void openMenu() {
        doAnimateOpen(mItem1,0,5,300);
        doAnimateOpen(mItem2,1,5,300);
        doAnimateOpen(mItem3,2,5,300);
        doAnimateOpen(mItem4,3,5,300);
        doAnimateOpen(mItem5,4,5,300);
    }

    private void closeMenu() {
        doAnimateClose(mItem1,0,5,300);
        doAnimateClose(mItem2,1,5,300);
        doAnimateClose(mItem3,2,5,300);
        doAnimateClose(mItem4,3,5,300);
        doAnimateClose(mItem5,4,5,300);
    }

    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }

        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        );
        animatorSet.setDuration(500).start();
    }

    private void doAnimateClose(final View view, int index, int total, int radius) {
        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        );
        animatorSet.setDuration(500);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (view.getVisibility() == View.VISIBLE) {
                    view.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    private void animation() {
        ObjectAnimator tv1BgAnimation = ObjectAnimator.ofInt(mTextView1, "backgroundColor",
                0xffff00ff, 0xffffff00, 0xffff00ff);
//        tv1BgAnimation.setStartDelay(2000);
//        tv1BgAnimation.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(mTextView1, "translationY",
                0, 300, 0);
//        tv1TranslateY.setStartDelay(2000);
        tv1TranslateY.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(mTextView2, "translationY",
                0, 400, 0);
//        tv2TranslateY.setStartDelay(2000);
        tv2TranslateY.setRepeatCount(ValueAnimator.INFINITE);

        final AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(tv1BgAnimation, tv1TranslateY, tv2TranslateY);
        animatorSet.play(tv1TranslateY).with(tv2TranslateY).after(tv1BgAnimation);
        animatorSet.setDuration(1000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i(TAG, "onAnimationStart: " + System.currentTimeMillis());
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG, "onAnimationEnd: " + System.currentTimeMillis());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorSet.start();
            }
        });
    }
}
