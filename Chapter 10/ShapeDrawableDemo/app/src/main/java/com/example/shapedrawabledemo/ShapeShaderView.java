package com.example.shapedrawabledemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-18.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class ShapeShaderView extends View {
    private ShapeDrawable mShapeDrawable;

    public ShapeShaderView(Context context) {
        super(context);

        init();
    }

    public ShapeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ShapeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mShapeDrawable = new ShapeDrawable(new RectShape());
        mShapeDrawable.setBounds(new Rect(100, 100, 300, 300));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avator);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mShapeDrawable.getPaint().setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mShapeDrawable.draw(canvas);
    }
}
