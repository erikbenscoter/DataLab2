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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tapeRecorder = new recordStudio();

        // Inflate the layout for this fragment


         final View thisFragment =  inflater.inflate(R.layout.fragment_main, container, false);
        //set up the elements on this fragment


        //get the microphone button and add an action listener
        ImageView microphoneButton = (ImageView) thisFragment.findViewById(R.id.microphoneBtn);
        microphoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySurfaceView recordStatus = (MySurfaceView) thisFragment.findViewById(R.id.surfaceView);

                //check if we are showing that we are recording, if yes, stop the drawing
                    //if not stop the drawing
                if(recordStatus.isSupposedToBeDrawn() == false) {
                    recordStatus.setSupposedToBeDrawn(true);
                    tapeRecorder.startRecording();
                }
                else {
                    recordStatus.setSupposedToBeDrawn(false);
                    tapeRecorder.stopRecording();
                }
                //redraw appropriately

                recordStatus.invalidate();



            }
        });




        return thisFragment;
    }


}
