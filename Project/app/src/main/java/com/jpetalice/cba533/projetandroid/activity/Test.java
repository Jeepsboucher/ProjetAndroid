package com.jpetalice.cba533.projetandroid.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
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
        int width = canvas.getWidth();
        canvas.drawRect((int)(width * .05),100,(int)(width * .20),250, myPaint);
        canvas.drawRect((int)(width*.30),100,(int)(width*.45),250, myPaint);
        canvas.drawRect((int)(width*.55),100,(int)(width*.70),250, myPaint);
        canvas.drawRect((int)(width*.80),100,(int)(width * .95),250, myPaint);
    }
}
