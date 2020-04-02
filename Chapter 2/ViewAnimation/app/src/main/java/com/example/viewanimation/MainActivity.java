package com.example.viewanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.scale_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView textView = findViewById(R.id.tv);
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.alpha_anim);
                final Animation animation1=AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.rotate_anim);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        textView.startAnimation(animation1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                textView.startAnimation(animation);
            }
        });
    }
}
