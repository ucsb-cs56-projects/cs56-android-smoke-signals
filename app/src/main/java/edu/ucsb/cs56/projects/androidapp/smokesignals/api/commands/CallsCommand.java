package edu.ucsb.cs56.projects.androidapp.smokesignals.api.commands;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import edu.ucsb.cs56.projects.androidapp.smokesignals.api.Command;
import edu.ucsb.cs56.projects.androidapp.smokesignals.api.commands.validators.NArgValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class CallsCommand extends NArgValidator implements Command {

    public CallsCommand() {
        super(0);
    }


    @Override
    public String getUsage() {
        return "//calls";
    }

    public String execute(Context context, String[] args) {
        List<Call> calls = getCalls(context);
        if (calls.isEmpty()) {
            return "No new calls.";
        }

        StringBuilder sb = new StringBuilder("MISSED CALLS:\n");

        for (Call call : calls) {
            sb.append(call);
            sb.append("-------\n");
        }

        return sb.toString();
    }

    private List<Call> getCalls(Context context) {
        List<Call> calls = new ArrayList<>();

        String[] projection = { CallLog.Calls.CACHED_NAME, CallLog.Calls.CACHED_NUMBER_LABEL, CallLog.Calls.TYPE };

        //Query to find which calls in the Call Log are MISSED and NEW (Haven't been acknowledged by user)
        String where = CallLog.Calls.TYPE+"="+CallLog.Calls.MISSED_TYPE+" AND NEW = 1";

        try (Cursor c = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,projection,where, null, null)) {
            if (c == null || !c.moveToFirst()) {
                return calls;
            }

            int numberIndex = c.getColumnIndex(CallLog.Calls.NUMBER);
            int nameIndex = c.getColumnIndex(CallLog.Calls.CACHED_NAME);
            int dateIndex = c.getColumnIndex(CallLog.Calls.DATE);

            do {
                String number = c.getString(numberIndex);
                String name = c.getString(nameIndex);
                String date = c.getString(dateIndex);

                calls.add(new Call(number, name, date));
            } while (c.moveToNext());
        }

        return calls;
    }

    private class Call {
        public final String NUMBER, NAME, DATE;

        public Call(String number, String name, String date) {
            this.NUMBER = number;
            this.NAME = name;
            this.DATE = date;
        }

        @Override
        public String toString() {
            return "Name: " + NAME + "\nPhone Number: " +
                    NUMBER + "\nCall Date: " + DATE + "\n";
        }
    }


}
