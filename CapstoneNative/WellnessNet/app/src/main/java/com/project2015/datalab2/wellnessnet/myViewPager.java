package com.project2015.datalab2.wellnessnet;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by erikbenscoter on 3/16/15.
 */
   //create a custom ViewPager Class
   public class myViewPager extends ViewPager {

        boolean isLockOnCurrentFragment = false;

        //constructors
        public myViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public myViewPager(Context context) {
            super(context);
        }


        //what we want to cusomize:
        //we would like to customize the touch events so that we can turn off swiping when
        //recording is in process


        @Override
        public boolean onTouchEvent(MotionEvent ev) {

            if(!isLockOnCurrentFragment())                            //if not locked, handle normal
                return super.onTouchEvent(ev);
            else                                                        //if locked
                return false;                                         //don't acknowledge touch
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            if(!isLockOnCurrentFragment())                              //if not locked, handle normal
                return super.onInterceptTouchEvent(ev);
            else                                                        //if locked
                return false;                                           //don't acknowledge touch
        }

        /////////////////////////////getters and setters////////////////////////////////////////////
        public boolean isLockOnCurrentFragment(){return isLockOnCurrentFragment;}
        public void setLockOnCurrentFragment(boolean inputValue){isLockOnCurrentFragment = inputValue;}

    }

