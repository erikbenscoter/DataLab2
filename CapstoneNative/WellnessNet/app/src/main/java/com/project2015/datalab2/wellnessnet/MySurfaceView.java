package com.project2015.datalab2.wellnessnet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by erikbenscoter on 3/7/15.
 */
public class MySurfaceView extends View{
    private boolean grow = true;                    //is the recording circle growing in size
    private boolean supposedToBeDrawn = false;      //is the drawing supposed to be drawn
    private int sizeChangeGrow = 1;                 //how fast does the circle grow
    private int sizeChangeShrink = 1;               //how fast does the circle shrink
    private int maxSize = 100;                      //how big will the circle radius get
    private int waitMiliseconds = 60;               //how many ms between frames
    private int minSize = 80;                       //how many small can the circle radius get
    private float radius = minSize;                 //initial size of the circle


    public MySurfaceView(Context context) {
        super(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
        public void draw(Canvas c) {
            super.draw(c);
            //c.drawCircle(cx,cy,radius,paint);
            if(!supposedToBeDrawn){
                System.out.println("nothing is supposed to be drawn yet");
            }else {
                int centerHorizontal, centerVertical;
                centerHorizontal = c.getWidth() / 2;
                centerVertical = c.getHeight() / 2;

                Paint redPaint;
                redPaint = new Paint();
                redPaint.setColor(Color.RED);
                redPaint.setStyle(Paint.Style.FILL);

                c.drawCircle(centerHorizontal, centerVertical, radius, redPaint);

                continuousRedraw();
            }
        }

        public void continuousRedraw() {

            if(grow == true){
                if(radius < maxSize)
                    radius += sizeChangeGrow;
                else{
                    //begin shrinking
                    radius -=sizeChangeShrink;
                    grow = false;
                }
            }
            if(grow == false){
                if(radius > minSize)
                    radius -= sizeChangeShrink;
                else{
                    grow = true;
                    radius += sizeChangeGrow;
                }

            }


                this.postInvalidateDelayed((long) waitMiliseconds);     //wait and then redraw

        }//ends continuous redraw

    ///////////////////////////////////setters and getters//////////////////////////////////////////

            public boolean isGrow() {
                return grow;
            }

            public void setGrow(boolean grow) {
                this.grow = grow;
            }

            public boolean isSupposedToBeDrawn() {
                return supposedToBeDrawn;
            }

            public void setSupposedToBeDrawn(boolean supposedToBeDrawn) {
                this.supposedToBeDrawn = supposedToBeDrawn;
            }

            public int getSizeChangeGrow() {
                return sizeChangeGrow;
            }

            public void setSizeChangeGrow(int sizeChangeGrow) {
                this.sizeChangeGrow = sizeChangeGrow;
            }

            public int getSizeChangeShrink() {
                return sizeChangeShrink;
            }

            public void setSizeChangeShrink(int sizeChangeShrink) {
                this.sizeChangeShrink = sizeChangeShrink;
            }

            public int getMaxSize() {
                return maxSize;
            }

            public void setMaxSize(int maxSize) {
                this.maxSize = maxSize;
            }

            public int getWaitMiliseconds() {
                return waitMiliseconds;
            }

            public void setWaitMiliseconds(int waitMiliseconds) {
                this.waitMiliseconds = waitMiliseconds;
            }

            public int getMinSize() {
                return minSize;
            }

            public void setMinSize(int minSize) {
                this.minSize = minSize;
            }

            public float getRadius() {
                return radius;
            }

            public void setRadius(float radius) {
                this.radius = radius;
            }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    }//ends class



