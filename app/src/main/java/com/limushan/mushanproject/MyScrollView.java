package com.limushan.mushanproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * @author libinbin
 *         2017/8/30
 */

public class MyScrollView extends ViewGroup implements View.OnTouchListener {
    private int mScreenHeight;
    private Scroller mScroller;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenHeight = getScreenHeight(getContext());
        mScroller = new Scroller(getContext());
        setOnTouchListener(this);
        Scroller scroller = new Scroller(getContext());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
        int childCount = getChildCount();
        layoutParams.height = childCount * mScreenHeight;
        setLayoutParams(layoutParams);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
        }

    }

    public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    private float mLastY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = (int) (mLastY - y);
                if (getScrollY() < 0) {
                    dy = 0;
                }
                /*if (getScrollY() > getHeight() - mScreenHeight) {
                    dy = 0;
                }*/
                scrollBy(0, dy);
                break;
            case MotionEvent.ACTION_UP:
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
