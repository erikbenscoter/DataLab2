package com.project2015.datalab2.wellnessnet;

import android.annotation.TargetApi;
import android.app.ActionBar;
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

/**
 * Created by erikbenscoter on 4/21/15.
 */
public class CorrectStringView extends LinearLayout {

    String stringToExamine = "";
    public CorrectStringView(Context context) {
        super(context);
        trueConstructor();
    }

    public CorrectStringView(Context context, AttributeSet attrs) {
        super(context, attrs);
        trueConstructor();
    }

    public CorrectStringView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        trueConstructor();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CorrectStringView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        trueConstructor();
    }

    private void trueConstructor(){
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
