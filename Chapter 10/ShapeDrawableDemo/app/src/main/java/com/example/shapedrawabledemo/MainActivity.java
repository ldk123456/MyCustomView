package com.example.shapedrawabledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean mHasCorner = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.shape_tv);
        findViewById(R.id.add_shape_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradientDrawable drawable = (GradientDrawable) textView.getBackground();
                drawable.setCornerRadius(mHasCorner ? 0 : 20);
                mHasCorner = !mHasCorner;
            }
        });

        ImageView imageView = findViewById(R.id.img);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avator);
        CustomDrawable drawable = new CustomDrawable(bitmap);
        imageView.setImageDrawable(drawable);
    }
}
