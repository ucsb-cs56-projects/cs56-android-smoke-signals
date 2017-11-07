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
import android.telephony.SmsMessage;
import android.os.Bundle;

import com.konukoii.smokesignals.storage.DaoManager;
import com.konukoii.smokesignals.storage.PhoneNumber;
import com.konukoii.smokesignals.storage.PhoneNumberDao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainService extends Service{

    private final static String storeText ="storeText.txt";
    //Debuggin' Purpouses
    private final static String TAG="SmokeSignals";
    private PhoneNumberDao phoneNumberDao;
    private SMSRequestManager smsRequestManager;

    public MainService() {
        super();
        phoneNumberDao = new DaoManager(this).getPhoneNumberDao();
        smsRequestManager = new SMSRequestManager();
    }


    //Broadcast Reciever
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if("android.provider.Telephony.SMS_RECEIVED".equals(action)){
               handleSMS(context, intent);
            }
        }
    };

    private void handleSMS(final Context context, final Intent intent) {

        Callback<Boolean> callback = new Callback<Boolean>() {
            @Override
            public void done(Boolean isVerified) {
                if (isVerified) {
                    Log.d(TAG, "Firing up the SMSRequestManager!");
                    smsRequestManager.go(context, intent);
                }
            }
        };


        if(Settings.getWhitelist()){ //whitelisting enabled
            Log.d(TAG, "Whitelisting enabled, checking sender");
            verify(intent, callback);
        }
        else {
            Log.d(TAG, "Whitelisting disabled, accepting text");
            callback.done(true); // whitelisting is disabled, allow everything
        }
    }

    private Set<String> getMessageSenders(Bundle bundle) {
        Set<String> senders = new HashSet<>();

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                senders.add(msgs[i].getOriginatingAddress());
            }
        }

        return senders;
    }

    public void verify(Intent intent, Callback<Boolean> callback){
        Set<String> from = getMessageSenders(intent.getExtras());
        Log.d(TAG, "checking numbers: " + from);


        List<PhoneNumber> whitelisted = phoneNumberDao.getAll();

        for (PhoneNumber number : whitelisted) {

            String searchMe = number.getNumber();
            boolean isValid = false;
            for (String msg_from : from) {

                if (searchMe.equals(msg_from)) { continue; }

                int phoneNumberLength = msg_from.length();
                int savedNumber = 7;//str.length();
                for (int i = 0; i <= (phoneNumberLength-savedNumber); i++) {
                    if (msg_from.regionMatches(i, searchMe, 0, savedNumber)) {
                        isValid = true;
                        break;
                    }
                }

                if (!isValid) {
                    Log.d(TAG, "Invalid phone number: " + msg_from);
                    callback.done(false);
                    return;
                }
            }
        }

        callback.done(true);
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
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MyService Stopped", Toast.LENGTH_LONG).show();

        //Destroy the SMS_Broadcast_Receiver
        unregisterReceiver(receiver);

        Log.d(TAG, "onDestroy");

    }

}
