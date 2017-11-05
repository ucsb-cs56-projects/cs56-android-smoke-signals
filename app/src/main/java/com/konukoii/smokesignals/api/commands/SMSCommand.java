package com.konukoii.smokesignals.api.commands;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

import com.konukoii.smokesignals.api.Command;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class SMSCommand implements Command {

    /**
     * Sends an sms
     * @param context Context
     * @param args An array with a single element: the message body
     * @return Human-readable text saying the message was sent
     */
    public String execute(Context context, String[] args) {
        if (args.length != 1) {
            throw new RuntimeException("Expected 1 arg, got " + args.length);
        }

        String msg_body = args[0];

        if(msg_body.length() <= 11){
            return "//SMS [number] [message] <- please enter a 10 digit phone number," +
                    " followed by a message after //SMS to send an SMS message.";
        }

        String phoneNum = msg_body.substring(0,10);
        String message = msg_body.substring(11);
        sendSMS(phoneNum,message);


        return "Sent message to "+ phoneNum + " : " + message;
    }


    private void sendSMS(String phoneNumber, String message){
        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(message);
        sms.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
        //sms.sendTextMessage(phoneNumber, null, message, null, null);
        Log.d(TAG, "message sent!");
    }
}
