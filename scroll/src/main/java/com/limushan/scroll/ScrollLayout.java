package com.limushan.scroll;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @author libinbin
 *         2017/11/21
 */

public class ScrollLayout extends ViewGroup {
    private Scroller mScroller;
    private int mTouchSlop;
    private float mXDown;
    private float mXMove;
    private float mXLastMove;
    private int leftBorder;
    private int rightBorder;

    public ScrollLayout(Context context) {
        this(context, null);
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledPagingTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.layout(i * childAt.getMeasuredWidth(), 0, (i + 1) * childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
        }
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(childCount - 1).getRight();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = mXLastMove = ev.getRawX();
                Log.e("log---------", "onInterceptTouchEvent(ScrollLayout.java:70)-->>" + mXDown);
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                Log.e("log---------", "onInterceptTouchEvent(ScrollLayout.java:77)-->>" + mXMove);
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("log---------", "onTouchEvent(ScrollLayout.java:90)-->>" + event.getRawX());
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrollX = (int) (mXLastMove - mXMove);
                if (getScrollX() + scrollX < leftBorder) {
                    setScrollX(leftBorder);
                    return true;
                }
                if (getScrollX() + scrollX + getWidth() > rightBorder) {
                    setScrollX(rightBorder - getWidth());
                    return true;
                }
                scrollBy(scrollX, 0);
                mXLastMove = mXMove;
                break;

            case MotionEvent.ACTION_UP:
                int index = ((getScrollX() + getWidth() / 2) / getWidth());
                int dx = index * getWidth() - getScrollX();
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
