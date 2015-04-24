package com.project2015.datalab2.wellnessnet;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

/**
 * Created by erikbenscoter on 4/21/15.
 */
public class CorrectStringView extends LinearLayout {

    //class varriables
    Vector vectorOfButtons;
    public boolean stringHasReturned = false;
    Transmitter transmitter;

    String stringToExamine = "";
    public CorrectStringView(Context context) {
        super(context);

    }

    public CorrectStringView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CorrectStringView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CorrectStringView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    public void trueConstructor(){
        this.setOrientation(LinearLayout.VERTICAL);
        vectorOfButtons = new Vector();
        stringToExamine.trim();
        String[] words = stringToExamine.split(" ");


        class DialogueHandler implements DialogInterface.OnClickListener{
            View v;
            TextView tv;
            DialogueHandler(View v,TextView tv){
                this.v = v;
                this.tv = tv;
            }

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Button) v).setText(tv.getText());
            }
        }

        for(String word : words) {
            Button b = new Button(getContext());
            b.setText(word);
            b.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            addView(b);
            vectorOfButtons.add(b);
            b.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String wordToCorrect = (String)((Button) v).getText();
                    final EditText editText = new EditText(getContext());
                    editText.setText(wordToCorrect);



                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder
                            .setTitle("Corrections")
                            .setMessage("Please correct the below text")
                            .setView(editText)
                            .setPositiveButton("done", new DialogueHandler(v, editText));

                    alertDialogBuilder.show();
                }
            });

        }

        //set the done button

        final Button finishedButton = new Button(getContext());
        finishedButton.setBackgroundColor(Color.GREEN);

        finishedButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //build up the corrected string
                String correctedString = "";
                for (int buttonItterator = 0; buttonItterator < vectorOfButtons.size(); buttonItterator++) {
                    correctedString = correctedString + ((Button) vectorOfButtons.get(buttonItterator)).getText() + " ";
                }
                correctedString = correctedString.trim();
                finishedButton.setBackgroundColor(Color.WHITE);
                transmitter.returnString = null;
                Transmitter transmitter2 = new Transmitter((Activity) getContext());
                transmitter2.socky = transmitter.socky;
                transmitter2.socketExists = true;
                transmitter2.sendText(correctedString);
                while(transmitter2.returnString == null);
                System.out.println(transmitter2.returnString);
                Toast toast = Toast.makeText(getContext(),transmitter2.returnString,Toast.LENGTH_LONG);
                toast.show();


            }

        });
        finishedButton.setText("Finished");
        this.addView(finishedButton);



    }

    public class WaitingOnTransmitterReturn implements Runnable{
        Transmitter t;
        public WaitingOnTransmitterReturn(Transmitter t){
            this.t = t;
        }
        @Override
        public void run() {
            while (t.returnString == null);
            //busy wait
            Intent intent = new Intent((Activity)getContext(),DiagnosisResultActivity.class);
            //pass parameters
            intent.putExtra("ReceivedDiagnosis",t.returnString);
            getContext().startActivity(intent);

        }
    }

    public void refresh(){
        trueConstructor();
        invalidate();
    }

    public String getStringToExamine() {
        return stringToExamine;
    }

    public void setStringToExamine(String stringToExamine) {
        this.stringToExamine = stringToExamine;
    }
}
