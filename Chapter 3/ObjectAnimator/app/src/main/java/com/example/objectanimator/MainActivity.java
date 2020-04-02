package com.example.objectanimator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        final ImageView ballImg = findViewById(R.id.ball_img);
        findViewById(R.id.start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofObject(ballImg, "fallingPos",
                        new FallingBallEvaluator(), new Point(0, 0), new Point(500, 500));
                animator.setDuration(2000);
                animator.start();
            }
        });
    }
}
