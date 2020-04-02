package com.example.keyframeandpropertyviewholder;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mStartButton;
    private MyTextView mAniText;
    private ImageView mImage;

    private ObjectAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

//        propertyValueHolder();
        objectPropertyValueHolder();
    }

    private void initView() {
        mAniText = findViewById(R.id.ani_text);
        mStartButton = findViewById(R.id.start_btn);
        mImage = findViewById(R.id.ani_image);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameAnimation();
            }
        });
    }

    private void frameAnimation() {
        Keyframe keyframe0 = Keyframe.ofFloat(0f, 0f);
        Keyframe keyframe1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe keyframe3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe keyframe4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe keyframe5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe keyframe6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe keyframe7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe keyframe8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe keyframe9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe keyframe10 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation",
                keyframe0, keyframe1, keyframe2, keyframe3, keyframe4, keyframe5, keyframe6,
                keyframe7, keyframe8, keyframe9, keyframe10);

        Keyframe scaleXframe0 = Keyframe.ofFloat(0f, 1f);
        Keyframe scaleXframe1 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleXframe9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleXframe10 = Keyframe.ofFloat(1f, 1f);
        PropertyValuesHolder scaleXframeHolder = PropertyValuesHolder.ofKeyframe("scaleX",
                scaleXframe0, scaleXframe1, scaleXframe9, scaleXframe10);

        Keyframe scaleYframe0 = Keyframe.ofFloat(0f, 1f);
        Keyframe scaleYframe1 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleYframe9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleYframe10 = Keyframe.ofFloat(1f, 1f);
        PropertyValuesHolder scaleYframeHolder = PropertyValuesHolder.ofKeyframe("scaleY",
                scaleYframe0, scaleYframe1, scaleYframe9, scaleYframe10);

        Animator animator = ObjectAnimator.ofPropertyValuesHolder(mImage,
                frameHolder, scaleXframeHolder, scaleYframeHolder);
        animator.setDuration(1000);
        animator.start();
    }

    private void objectPropertyValueHolder() {
        PropertyValuesHolder holder = PropertyValuesHolder.ofObject("charText",
                new CharEvaluator(), 'A', 'Z');
        mAnimator = ObjectAnimator.ofPropertyValuesHolder(mAniText, holder);
        mAnimator.setDuration(3000);
        mAnimator.setInterpolator(new AccelerateInterpolator());
    }

    private void propertyValueHolder() {
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("rotation",
                60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f);
        PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofFloat("alpha",
                0.1f, 1f, 0.1f, 1f);
        mAnimator = ObjectAnimator.ofPropertyValuesHolder(mAniText, rotationHolder, alphaHolder);
        mAnimator.setDuration(3000);
    }
}
