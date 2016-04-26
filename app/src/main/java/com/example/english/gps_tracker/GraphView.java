package com.example.english.gps_tracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


public class GraphView extends View {

    Paint black;
    Paint white;
    Paint green;
    ArrayList<Location> locations;
    float xpoint;
    float ypoint;

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
        green = new Paint();
        green.setColor(0xFF00FF00);
        green.setStrokeWidth(1f);
        locations = new ArrayList<>();
    }

    public void setLocations(ArrayList<Location> locs){
        this.locations = locs;
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
        float lspace = width / 6;
        for(int d = 0; d < 6; d++){
            canvas.drawLine(0,width - d * lspace, width, width - d * lspace, white);
        }
        float xspace = width / 100f;
        if(locations.size() < 100 ) {
             xpoint = 0;
             ypoint = width;
        }else{
            xpoint = 0;
            ypoint = width - (locations.get(0).getSpeed() * 0.36f)*lspace;
        }
        for(int i = 0; i < locations.size(); i++){
            canvas.drawLine(xpoint, ypoint,xpoint+xspace,width - (locations.get(i).getSpeed() * 0.36f)*lspace, green);
            xpoint = xpoint+xspace;
            ypoint = width - (locations.get(i).getSpeed() * 0.36f)*lspace;
        }
        invalidate();
    }
}
