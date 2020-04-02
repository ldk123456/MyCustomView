package com.example.viewadvancedproperties;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private static final String TAG = "MainActivity";

    private Button mAddBtn;
    private Button mRemoveBtn;
    private ImageView mImageView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            startActivityForResult(intent, 100);
        } else {
            initView();
        }
    }

    private void initView() {
        mAddBtn = findViewById(R.id.add_view);
        mRemoveBtn = findViewById(R.id.remove_view);
        mAddBtn.setOnClickListener(this);
        mRemoveBtn.setOnClickListener(this);

        mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            initView();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_view:
                mImageView = new ImageView(this);
                mImageView.setBackgroundResource(R.mipmap.ic_launcher_round);

                mLayoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 2099,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                        PixelFormat.TRANSPARENT);
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
                mLayoutParams.x = 0;
                mLayoutParams.y = 300;
                mImageView.setOnTouchListener(this);
                mWindowManager.addView(mImageView, mLayoutParams);
                break;
            case R.id.remove_view:
                mWindowManager.removeViewImmediate(mImageView);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mLayoutParams.x = rawX;
                mLayoutParams.y = rawY;
                mWindowManager.updateViewLayout(mImageView, mLayoutParams);
                break;
            default:
                break;
        }
        return false;
    }
}
