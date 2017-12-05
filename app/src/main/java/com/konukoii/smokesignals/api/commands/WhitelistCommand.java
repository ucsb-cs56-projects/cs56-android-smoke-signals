package com.konukoii.smokesignals.api.commands;

import android.content.Context;
import android.content.SharedPreferences;

import com.konukoii.smokesignals.api.Command;
import com.konukoii.smokesignals.api.commands.validators.NArgValidator;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class WhitelistCommand extends NArgValidator implements Command {

    public WhitelistCommand() {
        super(0);
    }

    public String getUsage() {
        return "";
    }

    private boolean getNewStatus(boolean status, String arg) {
        switch (arg) {
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

        return status;
    }

    public String execute(Context context, String[] args) {
        SharedPreferences sharePref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        boolean status = sharePref.getBoolean("whitelist", true);
        if (args.length > 0) {
            status = getNewStatus(status, args[0]);

            SharedPreferences.Editor edit = sharePref.edit();
            edit.putBoolean("whitelist", status);
            edit.apply();
        }

        return (status) ? "Whitelist Enabled" : "Whitelist Disabled";
    }
}
