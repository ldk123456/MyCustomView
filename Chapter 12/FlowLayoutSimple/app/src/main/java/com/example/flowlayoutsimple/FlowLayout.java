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
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int lineWidth = 0;        // 记录每一行的宽度
        int lineHeight = 0;       // 记录每一行的高度
        int height = 0;           // 记录整个layout的高度
        int width = 0;            // 记录整个layout的宽度

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth + childWidth > measureWidth) {
                // 需要换行
                width = Math.max(lineWidth, childWidth);
                height += lineHeight;
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else {
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }

            if (i == count - 1) {
                height += lineHeight;
                width = Math.max(width, lineWidth);
            }
        }

        width = width + getPaddingLeft() + getPaddingRight();
        height = height + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth : width,
                (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int lineWidth = 0;    // 当前行累加的行宽
        int lineHeight = 0;   // 当前行的行高
        int top = getPaddingTop();      // 当前控件的top坐标
        int left = getPaddingLeft();     // 当前控件的left坐标

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (childWidth + lineWidth > getMeasuredWidth()) {
                top += lineHeight;
                left = getPaddingLeft();
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else {
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }

            // 计算childView的left、top、right、bottom
            int cl = left + lp.leftMargin;
            int ct = top + lp.topMargin;
            int cr = cl + child.getMeasuredWidth();
            int cb = ct + child.getMeasuredHeight();
            child.layout(cl, ct, cr, cb);

            left += childWidth;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
}
