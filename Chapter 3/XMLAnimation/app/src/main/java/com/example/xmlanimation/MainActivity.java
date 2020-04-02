package com.example.xmlanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mStartButton;
    private TextView mTextView;
    private ValueAnimator valueAnimator;
    private ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        valueAnimator();
        objectAnimator();
    }

    private void initView() {
        mStartButton = findViewById(R.id.start_btn);
        mTextView = findViewById(R.id.anim_text);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.start();
            }
        });
    }

    private void valueAnimator() {
        valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this,
                R.animator.value_animator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mTextView.layout(value, value,
                        value + mTextView.getWidth(), value + mTextView.getHeight());
            }
        });
    }

    private void objectAnimator(){
        objectAnimator= (ObjectAnimator) AnimatorInflater.loadAnimator(this,
                R.animator.object_animator);
        objectAnimator.setTarget(mTextView);
    }
}
