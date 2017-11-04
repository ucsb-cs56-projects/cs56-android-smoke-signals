package com.konukoii.smokesignals.api.commands;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.konukoii.smokesignals.api.Command;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class BatteryCommand implements Command {

    private class BatteryStatus {
        public boolean charging;
        public int percent;

        public BatteryStatus(boolean charging, int percent) {
            this.charging = charging;
            this.percent = percent;
        }
    }

    private BatteryStatus getStatus(Context context) {
        Intent batteryIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

        int percent = (int) Math.ceil(((double) level / (double) scale * 100.0));
        boolean charging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
        return new BatteryStatus(charging, percent);
    }

    public String execute(Context context, String[] args) {
        BatteryStatus status = getStatus(context);

        String charging = (status.charging) ? "Currently Charging." : "Currently not charging.";
        return "Battery Level: " + Integer.toString(status.percent) + "%. " + charging;
    }
}
