package com.konukoii.smokesignals.api.commands;

import android.content.Context;
import android.telephony.SmsManager;
import android.text.TextUtils;
import com.konukoii.smokesignals.api.Command;
import com.konukoii.smokesignals.api.commands.validators.NArgValidator;
import java.util.ArrayList;
import java.util.Arrays;



/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class SMSCommand extends NArgValidator implements Command {

    public SMSCommand() {
        super(1);
    }

    @Override
    public String getUsage() {
        return "//sms [number] [message]";
    }

    public String execute(Context context, String[] args) {
        String number = args[0];
        String body = TextUtils.join(" ", Arrays.copyOfRange(args, 1, args.length));

        sendSMS(number, body);
        return "Sent message to "+ number + " : " + body + ".";
    }

    private void sendSMS(String phoneNumber, String message){
        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(message);
        sms.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
    }
}
