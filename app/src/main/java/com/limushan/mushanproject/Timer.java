package com.limushan.mushanproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author libinbin
 *         2017/9/25
 */

public class Timer extends View {
    private Paint paint;

    public Timer(Context context) {
        this(context, null);
    }

    public Timer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Timer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawFirstCircle(canvas);
    }

    private void drawFirstCircle(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(100, 100, 100, paint);

        canvas.saveLayerAlpha(0, 0, 400, 400, 121, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        paint.setColor(Color.RED);
        canvas.drawCircle(150, 150, 100, paint);
    }
}
