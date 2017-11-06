package com.konukoii.smokesignals.api.commands;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.konukoii.smokesignals.api.Command;
import com.konukoii.smokesignals.api.commands.validators.ToggleValidator;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class WifiCommand extends ToggleValidator implements Command {
    @Override
    public String getUsage() {
        return "//wifi [on|off|toggle - optional]";
    }

    @Override
    public String execute(Context context, String[] args) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        boolean status = wifiManager.isWifiEnabled();;
        if (args.length > 0) {
            switch(args[0]) {
                case "on":
                    status = true;
                    break;
                case "off":
                    status = false;
                    break;
                case "toggle":
                    status = !status;
                    break;
            }

            wifiManager.setWifiEnabled(status);
        }

        return (status) ? "Wifi Enabled" : "Wifi Disabled";
    }
}
