package com.konukoii.smokesignals.api.commands;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import com.konukoii.smokesignals.api.Command;
import com.konukoii.smokesignals.api.commands.validators.ToggleValidator;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class BluetoothCommand extends ToggleValidator implements Command {

    @Override
    public String getUsage() {
        return "//bluetooth [on|off|toggle - optional]";
    }

    public String execute(Context context, String[] args) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        boolean status = adapter.isEnabled();
        if (args.length > 0) {
            status = Toggle.getNewStatus(status, args[0]);

            if (status) {
                adapter.enable();
            } else {
                adapter.disable();
            }
        }

        return (status) ? "Bluetooth Enabled" : "Bluetooth Disabled";
    }
}
