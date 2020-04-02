package com.example.radialgradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
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
public class RadialGradientView extends View {
    private Paint mPaint;
    private RadialGradient mRadialGradient;
    private int mRadius;

    public RadialGradientView(Context context) {
        this(context, null);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        if (mRadialGradient == null) {
            mRadius = getWidth() / 2;
            mRadialGradient = new RadialGradient(getWidth() / 2, getHeight() / 2, mRadius,
                    0xffff0000, 0xff00ff00, Shader.TileMode.REPEAT);
            mPaint.setShader(mRadialGradient);
        }

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mPaint);
    }
}
