package com.project2015.datalab2.wellnessnet;

import android.media.MediaRecorder;
import android.os.Environment;

import java.io.IOException;

/**
 * Created by erikbenscoter on 3/7/15.
 */
public class recordStudio {
    //local variables
    MediaRecorder l_mediaRecorder;
    public String l_outputFile;
    boolean isRecording = false;

    public recordStudio(){
        l_outputFile = Environment.getExternalStorageDirectory().getAbsolutePath();
        l_outputFile += "/output.3gp";
        System.out.println("file name: " + l_outputFile);
    }
    //call this function to start recording
    public void startRecording(){
        l_mediaRecorder = new MediaRecorder();
        l_mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        l_mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        l_mediaRecorder.setOutputFile(l_outputFile);
        l_mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            l_mediaRecorder.prepare();
            isRecording = true;
        } catch (IOException e) {
            System.out.print("unable to access microphone in recordStudio.java");
        }

        l_mediaRecorder.start();

        System.out.println("started recording");



    }

    //call this function to stop recording
    public void stopRecording(){
        l_mediaRecorder.stop();
        l_mediaRecorder.reset();
        l_mediaRecorder.release();
        l_mediaRecorder = null;

        System.out.println("Stopped Recording");
    }



    /////////////////////////////Setter and getters//////////////////////////////////////////////

        public MediaRecorder getL_mediaRecorder() {
            return l_mediaRecorder;
        }

        public void setL_mediaRecorder(MediaRecorder l_mediaRecorder) {
            this.l_mediaRecorder = l_mediaRecorder;
        }

        public String getL_outputFile() {
            return l_outputFile;
        }

        public void setL_outputFile(String l_outputFile) {
            this.l_outputFile = l_outputFile;
        }

        public boolean isRecording() {
            return isRecording;
        }

        public void setRecording(boolean isRecording) {
            this.isRecording = isRecording;
        }


    /////////////////////////////////////////////////////////////////////////////////////////////

}
