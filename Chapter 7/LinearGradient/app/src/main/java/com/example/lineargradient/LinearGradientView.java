package com.example.lineargradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-06.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class LinearGradientView extends View {
    private Paint mPaint;

    public LinearGradientView(Context context) {
        this(context, null);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int[] colors = {0xffff0000, 0xff00ff00, 0xff0000ff, 0xffffff00, 0xff00ffff};
        float[] positions = {0f, 0.2f, 0.4f, 0.6f, 1f};
        mPaint.setShader(new LinearGradient(0, 0, getWidth() / 2, getHeight() / 2,
                colors, positions, Shader.TileMode.MIRROR));
        mPaint.setTextSize(50);
        canvas.drawText("欢迎关注启航的blog",0,200,mPaint);
    }
}
