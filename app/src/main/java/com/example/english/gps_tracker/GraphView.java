package com.example.english.gps_tracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class GraphView extends View {

    Paint black;
    Paint white;

    public GraphView(Context context) {
        super(context);
        init();
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        black = new Paint(Paint.ANTI_ALIAS_FLAG);
        black.setColor(0xFF000000);
        white = new Paint(Paint.ANTI_ALIAS_FLAG);
        white.setColor(0xFFFFFFFF);
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        int widthM = getMeasuredWidth();
        int heightM = getMeasuredHeight();
        if (widthM > heightM) {
            setMeasuredDimension(heightM, heightM);
        } else {
            setMeasuredDimension(widthM, widthM);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        canvas.drawRect(0, 0, width, width, black);
        float lspace = width / 6;
        for(int d = 0; d < 6; d++){
            canvas.drawLine(0,width - d * lspace, width, width - d * lspace, white);
        }
    }
}
