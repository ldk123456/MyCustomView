package com.example.objectanimator;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-02-21.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class FallingBallImageView extends ImageView {
    public FallingBallImageView(Context context) {
        this(context, null);
    }

    public FallingBallImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FallingBallImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFallingPos(Point pos) {
        layout(pos.x, pos.y, pos.x + getWidth(), pos.y + getHeight());
    }
}
