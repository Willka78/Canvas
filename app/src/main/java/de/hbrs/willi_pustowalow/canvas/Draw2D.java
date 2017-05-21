package de.hbrs.willi_pustowalow.canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;

public class Draw2D extends View {
    private Paint mPaint;

    private static final int LINE_WIDTH1 = 9;
    private static final int LINE_WIDTH2 = 4;

    private static final int LINE_LENGTH_OFFSET1 = 17;
    private static final int LINE_LENGTH_OFFSET2 = 57;
    private static final int LINE_LENGTH_OFFSET3 = 68;
    private static final int LINE_LENGTH_OFFSET4 = 75;

    public Draw2D(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(LINE_WIDTH1);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.rgb(110, 105, 23));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawLine(0, 0, 480, 650, mPaint);
        //canvas.drawLine(480, 0, 0, 650, mPaint);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if (width <= height){
            int k = width;
            width = height;
            height = k;
        }

        float diam = (float)(height * 1.3);
        float radius = diam / 2;

        float x = (width - diam) / 2;
        float y = 10;

        RectF rect = new RectF();
        rect.set(x, y, x + diam, y + diam);

        mPaint.setStrokeWidth(LINE_WIDTH1);
        canvas.drawArc(rect, 0, -180, false, mPaint);
        //canvas.drawLine(rect.centerX(), rect.centerY(), 0, rect.centerY(), mPaint);
        //canvas.drawLine(x, y + radius - 5, x + 100, y + radius - 5, mPaint);
        //canvas.drawLine(x + diam, y + radius - 5, x + diam - 100, y + radius - 5, mPaint);

        float i = 0;

        while(i <= 180){
            long x0 = Math.round(rect.centerX() + Math.cos(i * Math.PI / 180) * (radius - 0));
            long y0 = Math.round(rect.centerY() - (LINE_WIDTH1 / 2) - Math.sin(i * Math.PI / 180) * (radius - 0));

            long x1 = Math.round(rect.centerX() + Math.cos(i * Math.PI / 180) * (radius - LINE_LENGTH_OFFSET4));
            long y1 = Math.round(rect.centerY() - Math.sin(i * Math.PI / 180) * (radius - LINE_LENGTH_OFFSET4));


            canvas.drawLine(x1, y1 - (LINE_WIDTH1 / 2), x0, y0, mPaint);
            i += 22.5;
        }

        mPaint.setStrokeWidth(LINE_WIDTH2);

        i = 0;

        int k = 40;
        int j = 0;

        while(i <= 180){

            if (j % 5 == 0)
                k = LINE_LENGTH_OFFSET3;
            else
                k = LINE_LENGTH_OFFSET2;

            j++;

            int o = LINE_WIDTH1 / 2;

            long x0 = Math.round(rect.centerX() + Math.cos(i * Math.PI / 180) * (radius - LINE_LENGTH_OFFSET1));
            long y0 = Math.round(rect.centerY() - o - Math.sin(i * Math.PI / 180) * (radius - LINE_LENGTH_OFFSET1));

            long x1 = Math.round(rect.centerX() + Math.cos(i * Math.PI / 180) * (radius - k));
            long y1 = Math.round(rect.centerY() - Math.sin(i * Math.PI / 180) * (radius - k));


            canvas.drawLine(x1, y1 - o, x0, y0, mPaint);
            i += 2.25;
        }
    }
}
