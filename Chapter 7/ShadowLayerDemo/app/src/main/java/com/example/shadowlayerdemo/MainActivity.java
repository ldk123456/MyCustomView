package com.example.shadowlayerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ShadowLayerView mShadowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        shadowView();
    }
    private void shadowView() {
        mShadowView=findViewById(R.id.shadow_view);
        mShadowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShadowView.setShadow(!mShadowView.getShadow());
            }
        });
    }
}
