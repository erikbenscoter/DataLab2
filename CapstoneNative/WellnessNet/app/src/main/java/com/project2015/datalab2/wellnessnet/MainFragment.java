package com.project2015.datalab2.wellnessnet;



        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.os.Bundle;
        import android.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.SurfaceView;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends android.support.v4.app.Fragment{

    //locals

    recordStudio tapeRecorder;
    Sensors mySensorClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tapeRecorder = new recordStudio();
        mySensorClass = new Sensors(getActivity());

        // Inflate the layout for this fragment


         final View thisFragment =  inflater.inflate(R.layout.fragment_main, container, false);
        //set up the elements on this fragment


        //get the microphone button and add an action listener
        ImageView microphoneButton = (ImageView) thisFragment.findViewById(R.id.microphoneBtn);
        microphoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set some locals
                MySurfaceView recordStatus = (MySurfaceView) thisFragment.findViewById(R.id.surfaceView);   //this gets the canvas on which to paint
                recordStatus.setMySensorClass(mySensorClass);
                MainActivity activityRunningFragment = (MainActivity) thisFragment.getContext();            //this gets the Activity running this fragment


                //check if we are showing that we are recording, if yes, stop the drawing
                    //if not stop the drawing
                //MAY NEED TO BE MULTI-THREADED
                if(recordStatus.isSupposedToBeDrawn() == false) {
                    recordStatus.setSupposedToBeDrawn(true);                                                //allow the recoding to draw
                    System.out.println("    \t Recording.....");                                            //let us know where we are in debug

                    activityRunningFragment.setLockOnCurrentFragment(true);                                 //lock it so the user cannot move to other fragments
                                                                                                                //because we are recording

                    tapeRecorder.startRecording();                                                          //begin the recording


                }
                else {
                    System.out.println("\t\t Stopped Recording.....");                                      //used for debug
                    recordStatus.setSupposedToBeDrawn(false);                                               //stop drawing the record status
                    activityRunningFragment.setLockOnCurrentFragment(false);                                 //unlock the user to the recording fragment

                    tapeRecorder.stopRecording();                                                           //stop the actual recording
                }



                //redraw appropriately
                recordStatus.invalidate();                                                                  //redraw the screen so the
                                                                                                            //animation can begin



            }
        });




        return thisFragment;
    }


}
