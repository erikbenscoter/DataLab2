package com.project2015.datalab2.wellnessnet;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by erikbenscoter on 4/18/15.
 */
public class Transmitter extends AsyncTask {
    private Socket socky;
    private int port = 8002;
    private String serverIP = "192.168.1.5";
    private int timeoutBeforeError = 1 * 60 * 1000; //one minute
    char voiceOrText;
    String fileNameOrStringToSend;
    Activity activity;
    String returnString = null;


    public Transmitter(Activity a) {
        activity = a;
    }

    public String sendVoice(String nameOfFile) {
        return send('v', nameOfFile);
    }

    public String sendText(String textToSend) {
        return send('t', textToSend);
    }

    public String send(char vOrT, String fileNameOrStringToSend) {
        this.fileNameOrStringToSend = fileNameOrStringToSend;
        this.voiceOrText = vOrT;
        String stringToReturn = "";
        try {

            this.execute();

        } catch (Exception e) {
            System.out.println(e);
            stringToReturn = "exception\n";
        }
        return stringToReturn;
    }


    @Override
    protected Object doInBackground(Object[] params) {
        System.out.println("entering the sendFile method");
        String returnedString = "";
        try {
            InetSocketAddress sockyAddress = new InetSocketAddress(serverIP, port);

            socky = new Socket();
            try {
                socky.connect(sockyAddress);
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Made a successful connection");
            PrintWriter sendOut = new PrintWriter(socky.getOutputStream(), true);
            BufferedReader bufferOfReceivedIn = new BufferedReader(new InputStreamReader(socky.getInputStream()));


            System.out.println("made it past making PrintWriter object");
            if (this.voiceOrText == 't') {                               //we are sending text to server
                sendOut.println(this.fileNameOrStringToSend);
                returnedString = bufferOfReceivedIn.readLine();
            } else {                                                     //we are sending voice to the server

                OutputStream outputStream = socky.getOutputStream();
                File f = new File(fileNameOrStringToSend);
                byte[] buffer = new byte[(int) f.length()];
                FileInputStream fis = new FileInputStream(f);
                BufferedInputStream bis = new BufferedInputStream(fis);
                bis.read(buffer, 0, buffer.length);
                outputStream.write(buffer, 0, buffer.length);
                outputStream.flush();

                //get the result
                try {
                    returnedString = bufferOfReceivedIn.readLine();
                    System.out.println(returnedString);
                } catch (Exception e) {
                    System.out.println("ERROR" + e.toString());
                }
            }

            System.out.println("made it past sending the string");


        } catch (Exception e) {
            System.out.println("Encountered an error: " + e.toString());
        }
        try {
            socky.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return (Object) returnedString;

    }

    @Override
    protected void onPostExecute(Object o) {
        this.returnString = (String) o;
        System.out.println("HEEEEREEE" + returnString);

    }

    public void myOwnHandler(String toDisplay) {
        Toast toast = Toast.makeText((Context) activity, toDisplay, Toast.LENGTH_LONG);
    }
}

