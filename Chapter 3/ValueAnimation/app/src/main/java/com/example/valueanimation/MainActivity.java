package com.example.valueanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView textView;
    TextView charText;
    ValueAnimator animator;
    private ValueAnimator valueAnimator;

    private float mStart;
    private int mRepeatCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initValueAnimation();

        initObject();
    }

    private void initObject() {
        charText = findViewById(R.id.object_text);
        animator = ValueAnimator.ofObject(new CharEvalutor(), 'A', 'Z');
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char text = (Character) animation.getAnimatedValue();
                charText.setText(String.valueOf(text));
            }
        });
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        findViewById(R.id.object_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });
    }

    private void initValueAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, 1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (Integer) animation.getAnimatedValue();
                textView.layout(textView.getLeft(), curValue,
                        textView.getRight(), curValue + textView.getHeight());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mStart = textView.getTop();
                mRepeatCount = 0;
                Log.d(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "onAnimationCancel: ");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if (mStart != textView.getTop()) {
                    if (mRepeatCount % 2 == 0) {
                        textView.setBackgroundColor(Color.RED);
                    } else {
                        textView.setBackgroundColor(Color.YELLOW);
                    }
                    mRepeatCount++;
                }
                Log.d(TAG, "onAnimationRepeat: ");
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
//        valueAnimator.setInterpolator(new MyInterpolator());
        valueAnimator.setEvaluator(new ReverseEvaluator());
    }

    private void initView() {
        textView = findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked + " + v.getY(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueAnimator == null) {
                    initValueAnimation();
                }

                valueAnimator.start();
            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
            }
        });
    }
}
