package com.konukoii.smokesignals;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.konukoii.smokesignals.api.Command;
import com.konukoii.smokesignals.api.CommandManager;
import com.konukoii.smokesignals.storage.AppDatabase;
import com.konukoii.smokesignals.storage.PhoneNumber;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ankushrayabhari on 11/5/17.
 */

public class SMSManager extends BroadcastReceiver {
    private SmsManager smsManager;
    private CommandManager commandManager;
    private AppDatabase mDb;

    public SMSManager() {
        smsManager = SmsManager.getDefault();
        commandManager = new CommandManager();
    }

    public boolean validPhoneNumber(String phoneNumber, Context context) {

        SharedPreferences sharePref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        if(sharePref.getBoolean("whitelist", true)) {
            mDb = AppDatabase.getDataBaseInstance(context);
            return mDb.phoneNumberDao().getAll().contains(new PhoneNumber(phoneNumber));
        } else {
            return false;
        }
    }

    String[] getArgs(String body) {
        String[] temp = body.split("\\s+");
        if(temp.length == 0) {
            throw new RuntimeException("malformed body");
        }

        return Arrays.copyOfRange(temp, 1, temp.length);
    }

    String getCommand(String body) {
        String[] temp = body.split("\\s+");
        if(temp.length == 0) {
            throw new RuntimeException("malformed body");
        }

        return temp[0].toLowerCase();
    }

    private void messageReceived(final Context context, final String phoneNumber, String body) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // DB queries have to be run on a separate thread
                if(!validPhoneNumber(phoneNumber, context))
                    return;
            }
        };
        Thread t = new Thread(r);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!body.startsWith("//"))
            return;



        body = body.substring(2);
        String[] arguments;
        String commandName;

        try {
            arguments = getArgs(body);
            commandName = getCommand(body);
        } catch(RuntimeException e) {
            Log.e("Parse Body Error", e.getMessage());
            return;
        }

        String returnMessage = execute(commandName, arguments, context);
        sendMessage(phoneNumber, returnMessage);
    }

    private String execute(String commandName, String[] arguments, Context context) {
        Command command = commandManager.getCommand(commandName);
        String returnMessage = command.getUsage();

        if(command.validate(arguments)) {
            returnMessage = command.execute(context, arguments);
        }

        return returnMessage;
    }

    public void sendMessage(String phoneNumber, String body) {
        if(body == null) return;

        ArrayList<String> parts = smsManager.divideMessage(body);
        smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if(intent.getAction() == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String body = smsMessage.getMessageBody().trim();
                String phoneNumber = smsMessage.getOriginatingAddress();

                messageReceived(context, phoneNumber, body);
            }
        }


    }
}