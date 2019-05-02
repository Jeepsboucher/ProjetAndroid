package com.jpetalice.cba533.projetandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.jpetalice.cba533.projetandroid.R;

import java.util.List;

public class PasswordView extends View {
    private int numberOfInput=0;

    public PasswordView(Context context) {
        super(context);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        Paint myPaint = new Paint();
        myPaint.setTextSize(72);
        myPaint.setColor(getResources().getColor(R.color.colorPrimary));
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(3);
        Paint circlePaint = new Paint();
        circlePaint.setColor(getResources().getColor(R.color.black));
        int width = canvas.getWidth();

        Integer circle1X = ((int)(width*.05) + (int)(width*.20)) /2;
        Integer circle2X = ((int)(width*.30) + (int)(width*.45)) /2;
        Integer circle3X = ((int)(width*.55) + (int)(width*.70)) /2;
        Integer circle4X = ((int)(width*.80) + (int)(width*.95)) /2;

        canvas.drawRect((int)(width * .05),100,(int)(width * .20),250, myPaint);
        if(numberOfInput > 0){
            canvas.drawCircle(circle1X, 175, 20, circlePaint);
        }

        canvas.drawRect((int)(width*.30),100,(int)(width*.45),250, myPaint);
        if(numberOfInput > 1){
            canvas.drawCircle(circle2X, 175, 20, circlePaint);
        }

        canvas.drawRect((int)(width*.55),100,(int)(width*.70),250, myPaint);
        if(numberOfInput > 2){
            canvas.drawCircle(circle3X, 175, 20, circlePaint);
        }

        canvas.drawRect((int)(width*.80),100,(int)(width * .95),250, myPaint);
        if(numberOfInput > 3){
            canvas.drawCircle(circle4X, 175, 20, circlePaint);
        }
    }


    public void setNumberOfInput(int numberOfInput) {
        this.numberOfInput = numberOfInput;
        invalidate();
    }

}
