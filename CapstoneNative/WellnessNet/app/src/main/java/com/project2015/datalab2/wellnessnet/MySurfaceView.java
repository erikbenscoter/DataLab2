package com.project2015.datalab2.wellnessnet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
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
    private Sensors mySensorClass;                  //will hold the Sensors class
    private int x,y,z;                           //will hold xyz vectors

    public MySurfaceView(Context context) {
        super(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        x = y = z = 1;                               //initialization
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

                Paint textPaint;
                textPaint = new Paint();
                textPaint.setColor(Color.BLACK);
                textPaint.setTextSize(c.getHeight()/12);
                int total = (int) (x+y+z);
                c.drawText("x=" + (int) (x/9.81 * 100) + "% y=" + (int) (y/9.81 * 100)
                                + "% z= "+ (int) (z/9.81 * 100) + "%",c.getWidth()/100,centerVertical/8,textPaint);

                c.drawCircle(centerHorizontal, centerVertical, radius, redPaint);

                continuousRedraw();
            }
        }
        public void getXYZ(){
            x=(int) mySensorClass.getX();
            y=(int) mySensorClass.getY();
            z=(int) mySensorClass.getZ();
            System.out.println("x = "+x);
            System.out.println("y= "+y);
            System.out.println("z= "+z);

        }
        public void continuousRedraw() {
            getXYZ();
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

            public void setMySensorClass(Sensors input){this.mySensorClass = input;}



    ////////////////////////////////////////////////////////////////////////////////////////////////

    }//ends class



