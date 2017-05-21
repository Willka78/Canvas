package de.hbrs.willi_pustowalow.canvas;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final int SPEED_LEFT_DIGIT_WIDTH = 93;
    private static final int SPEED_LEFT_DIGIT_HEIGHT = 136;
    private static final int SPEED_RIGHT_DIGIT_WIDTH = 94;
    private static final int SPEED_RIGHT_DIGIT_HEIGHT = 136;

    private static final int SPEED_LEFT_DIGIT_X = 277;
    private static final int SPEED_LEFT_DIGIT_Y = 265;

    private static final int SPEED_RIGHT_DIGIT_X = 370;
    private static final int SPEED_RIGHT_DIGIT_Y = 265;

    Bitmap digitsLeft;
    Bitmap digitsRight;

    Bitmap speedoMeter;

    ImageView imageView;
    SeekBar seekBar;

    float scale = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);

        scale = getResources().getDisplayMetrics().density;

        speedoMeter = BitmapFactory.decodeResource(getResources(), R.drawable.tacho1);
        digitsLeft = BitmapFactory.decodeResource(getResources(), R.drawable.speed_segment_left);
        digitsRight = BitmapFactory.decodeResource(getResources(), R.drawable.speeds_segment_right);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(40);

        Timer myTimer = new Timer();
        final Handler uiHandler = new Handler();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        drawSpeed(seekBar.getProgress());
                    }
                });
            }
        }, 0L, 500);
    }

    void drawSpeed(int aSpeed){

        Bitmap tmpSpeed = speedoMeter.copy(android.graphics.Bitmap.Config.ARGB_8888, true);

        Canvas canvas = new Canvas(tmpSpeed);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        int u = aSpeed * Math.round(99 / 40f) / 2;

        int left7Digit  = u / 10;
        int right7Digit = u % 10;

        setTitle(String.valueOf(left7Digit) + " " + String.valueOf(right7Digit)  + " " + String.valueOf(u));

        Bitmap kk = Bitmap.createBitmap(digitsLeft,
                (int)(SPEED_LEFT_DIGIT_WIDTH * left7Digit * scale), 0,
                (int)(SPEED_LEFT_DIGIT_WIDTH * scale), (int)(SPEED_LEFT_DIGIT_HEIGHT * scale));
            canvas.drawBitmap(kk, SPEED_LEFT_DIGIT_X * scale, SPEED_LEFT_DIGIT_Y * scale, p);

        kk = Bitmap.createBitmap(digitsRight,
                (int)(SPEED_RIGHT_DIGIT_WIDTH * right7Digit * scale), 0,
                (int)(SPEED_RIGHT_DIGIT_WIDTH * scale), (int)(SPEED_RIGHT_DIGIT_HEIGHT * scale));
            canvas.drawBitmap(kk, SPEED_RIGHT_DIGIT_X * scale, SPEED_RIGHT_DIGIT_Y * scale, p);

        p.setColor(Color.BLUE);
        p.setStrokeWidth(10);
        p.setStyle(Paint.Style.STROKE);

        float startX = 480 * scale;
        float startY = 485 * scale;

        float x;
        float y;

        x = startX + (float)Math.cos((180 + 180 / 40f * aSpeed) * Math.PI / 180) * 350 * scale;
        y = startY + (float)Math.sin((180 + 180 / 40f * aSpeed) * Math.PI / 180) * 350 * scale;

        canvas.drawLine(startX, startY, x, y, p);



        imageView.setImageBitmap(tmpSpeed);
    }

}
