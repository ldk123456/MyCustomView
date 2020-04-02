package com.example.shapedrawabledemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Region;
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
public class ShapeView extends View {
    private ShapeDrawable mShapeDrawable;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        Rect rect1 = new Rect(50, 0, 90, 150);
        Rect rect2 = new Rect(0, 50, 250, 100);

        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);
        region1.op(region2, Region.Op.XOR);

        mShapeDrawable = new ShapeDrawable(new RegionShape(region1));
        mShapeDrawable.setBounds(new Rect(50, 50, 200, 100));
        mShapeDrawable.getPaint().setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mShapeDrawable.draw(canvas);
    }
}
