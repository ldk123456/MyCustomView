package com.example.animationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_layout);

//        anim1();

        anim2();
    }

    private void anim1() {
        ImageView imageView = findViewById(R.id.img);
        imageView.setVisibility(View.GONE);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setDuration(6000);
//        imageView.startAnimation(scaleAnimation);

        ImageView loading = findViewById(R.id.loading);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatMode(Animation.INFINITE);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        loading.startAnimation(rotateAnimation);
    }

    private void anim2() {
        final Animation animation1= AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim);
        final Animation animation2= AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim);
        final Animation animation3= AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim);
        final Animation animation4= AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim);

        final ImageView circle1=findViewById(R.id.circle1);
        final ImageView circle2=findViewById(R.id.circle2);
        final ImageView circle3=findViewById(R.id.circle3);
        final ImageView circle4=findViewById(R.id.circle4);

        findViewById(R.id.start_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circle1.startAnimation(animation1);

                animation2.setStartOffset(600);
                circle2.startAnimation(animation2);

                animation3.setStartOffset(1200);
                circle3.startAnimation(animation3);

                animation4.setStartOffset(1800);
                circle4.startAnimation(animation4);
            }
        });
    }
}
