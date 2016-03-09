package com.konukoii.smokesignals;

/**
 * Created by TransAtlantic on 2/14/2015.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.telephony.gsm.SmsMessage;
import android.os.Bundle;
import android.provider.Telephony.Sms;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainService extends Service{

    private final static String storeText ="storeText.txt";
    //Debuggin' Purpouses
    private final static String TAG="SmokeSignals";


    //Broadcast Reciever
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            SMSRequestManager smsRequestManager = new SMSRequestManager();
            if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
                if(verify(context, intent)==true) {
                    Log.d(TAG, "Firing up the SMSRequestManager!");
                    smsRequestManager.go(context, intent);
                }
            }
        }
    };

    public boolean verify(Context context, Intent intent){
        boolean isValid = false;

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String msg_from;
        msg_from="";
        String msg_body="";
        if (bundle != null) {
            try {
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                for (int i = 0; i < msgs.length; i++) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    msg_from = msgs[i].getOriginatingAddress();
                }


            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }



        try {

            InputStream in = openFileInput(storeText);
            if (in != null) {
                InputStreamReader tmp=new InputStreamReader(in);
                BufferedReader reader=new BufferedReader(tmp);
                String str;

                while ((str = reader.readLine()) != null) {


                    String searchMe = str;
                    //String findMe = "Eggs";
                    int phoneNumberLength = msg_from.length();
                    int savedNumber = 7;//str.length();
                    boolean foundIt = false;
                    for (int i = 0; i <= (phoneNumberLength-savedNumber); i++) {
                            if (msg_from.regionMatches(i, searchMe, 0, savedNumber)) {
                                isValid = true;
                                //System.out.println(searchMe.substring(i, i + findMeLength));
                                break;
                            }
                        }






                    if (str.equals(msg_from)) isValid = true;


                }

                in.close();

            }
        }

        catch (java.io.FileNotFoundException e) {
// that's OK, we probably haven't created it yet
        }

        catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
        return isValid;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Congrats! MyService Created", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate");

        //Initialize the SMS: Broadcast_Receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(receiver, filter);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MyService Stopped", Toast.LENGTH_LONG).show();

        //Destroy the SMS_Broadcast_Receiver
        unregisterReceiver(receiver);

        Log.d(TAG, "onDestroy");

    }

}
