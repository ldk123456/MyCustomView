package com.example.bitmapdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * Created by dekai.liu on 2020-03-18.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class StrokeImageView extends ImageView {
    public StrokeImageView(Context context) {
        super(context);
    }

    public StrokeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        Paint paint=new Paint();
        paint.setColor(Color.CYAN);
        setStateDrawable(this, paint);
    }
    private void setStateDrawable(ImageView imageView, Paint paint){
        BitmapDrawable drawable= (BitmapDrawable) imageView.getDrawable();
        Bitmap srcBitmap=drawable.getBitmap();
        Bitmap bitmap=Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        canvas.drawBitmap(srcBitmap.extractAlpha(),0,0,paint);

        StateListDrawable stateListDrawable=new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, new BitmapDrawable(bitmap));
        imageView.setBackground(stateListDrawable);
    }
}
