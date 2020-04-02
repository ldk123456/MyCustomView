package com.example.flowlayoutsimple;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dekai.liu on 2020-04-01.
 *
 * @author dekai.liu
 * @email dekai.liu@ximalaya.com
 * @phoneNumber 18392566603
 */
public class MyLinLayout extends ViewGroup {
    public MyLinLayout(Context context) {
        super(context);
    }

    public MyLinLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            width = Math.max(width, childWidth);
            height += childHeight;
        }

        setMeasuredDimension((widthMeasureMode == MeasureSpec.EXACTLY) ? widthMeasure : width,
                (heightMeasureMode == MeasureSpec.EXACTLY) ? heightMeasure : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.bottomMargin;
            child.layout(lp.leftMargin, top + lp.topMargin, childWidth + lp.leftMargin, top + childHeight + lp.topMargin);
            top += childHeight + lp.topMargin;
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
}
