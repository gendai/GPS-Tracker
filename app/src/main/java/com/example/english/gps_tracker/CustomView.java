package com.example.english.gps_tracker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public class CustomView extends LinearLayout {

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.gpslayout, this);
    }
}
