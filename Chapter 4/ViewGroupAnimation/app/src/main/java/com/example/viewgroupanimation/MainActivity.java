package com.example.viewgroupanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addBtn;
    private Button removeBtn;
    private LinearLayout btnContainer;
    private int buttonCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        addBtn = findViewById(R.id.add_btn);
        removeBtn = findViewById(R.id.remove_btn);
        addBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);

        buttonCount = 0;
        btnContainer = findViewById(R.id.button_container);
        LayoutTransition transition = new LayoutTransition();
        ObjectAnimator inAnim = ObjectAnimator.ofFloat(null, "rotationY",
                0f, 360f, 0f);
        transition.setAnimator(LayoutTransition.APPEARING, inAnim);

        ObjectAnimator outAnim = ObjectAnimator.ofFloat(null, "rotation",
                0f, 90f, 0f);
        transition.setAnimator(LayoutTransition.DISAPPEARING, outAnim);
        btnContainer.setLayoutTransition(transition);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btn:
                addButtonView();
                break;
            case R.id.remove_btn:
                removeButtonView();
                break;
            default:
                break;
        }
    }

    private void addButtonView() {
        buttonCount++;
        Button button = new Button(this);
        button.setText("Button " + buttonCount);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(layoutParams);
        btnContainer.addView(button, 0);
    }

    private void removeButtonView() {
        if (buttonCount > 0) {
            btnContainer.removeViewAt(0);
            buttonCount--;
        }
    }
}
