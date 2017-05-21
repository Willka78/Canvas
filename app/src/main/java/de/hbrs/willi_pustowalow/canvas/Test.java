package de.hbrs.willi_pustowalow.canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class Test extends View {
    private Paint mPaint;

    private Bitmap mBmp;

    public Test(Context context, Bitmap bmp) {
        super(context);
        init();

        mBmp = bmp;
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.rgb(110, 105, 23));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //canvas.setBitmap(mBmp);
        canvas.drawBitmap(mBmp, new Matrix(), null);
        canvas.drawLine(0, 0, 480, 650, mPaint);
        canvas.drawLine(480, 0, 0, 650, mPaint);

        //imageView.buildDrawingCache (true);
        //Bitmap b = imageView.getDrawingCache (true);

//        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
//        p.setStrokeWidth(10);
//        p.setColor(255);
//        canvas.drawLine(0, 0, 100, 100, p);
//
//
//        imageView.setImageBitmap(b);

    }

    public  Bitmap getBitmap(){
        return mBmp;
    }
}
