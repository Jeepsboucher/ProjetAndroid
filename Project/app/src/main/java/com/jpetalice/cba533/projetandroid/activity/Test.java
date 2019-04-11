package com.jpetalice.cba533.projetandroid.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jpetalice.cba533.projetandroid.R;

public class Test extends View {
    public Test(Context context) {
        super(context);
    }

    public Test(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Test(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint myPaint = new Paint();
        myPaint.setTextSize(72);
        myPaint.setColor(getResources().getColor(R.color.colorPrimary));
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(3);
        Rect rec = new Rect();
        rec.bottom = 30;
        rec.contains(100,100,100,100);
        rec.contains(100,100);
        //Rect rect = new Rect(100,100);
        canvas.drawRect(200,100,350,250, myPaint);
        canvas.drawRect(500,100,650,250, myPaint);
        canvas.drawRect(800,100,950,250, myPaint);
        canvas.drawRect(1100,100,1250,250, myPaint);
    }
}
