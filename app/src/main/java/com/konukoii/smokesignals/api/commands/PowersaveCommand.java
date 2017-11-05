package com.konukoii.smokesignals.api.commands;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.konukoii.smokesignals.api.Command;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class PowersaveCommand implements Command {

    /** Turns of things to save battery
     * @param context Context
     * @param args An array with a single element: The thing to turn off
     * @return Confirmation message
     */
    public String execute(Context context, String[] args) {
        if (args.length == 1) {
            throw new RuntimeException("Expected one arg, got " + args.length);
        }
        return execute(context, args[0]);


    }


    private String execute(Context context, String funct) {
        if (funct.equals("wifi")){
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(false);
            Toast.makeText(context,"Wifi disabled", Toast.LENGTH_SHORT).show();
        }
        else if(funct.equals("bluetooth")){
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            mBluetoothAdapter.disable();
            Toast.makeText(context,"Bluetooth disabled", Toast.LENGTH_SHORT).show();
        }
        else if(funct.equals("mute")){
            AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            Toast.makeText(context,"Phone muted", Toast.LENGTH_SHORT).show();
        }
        else if(funct.equals("all")){
            execute(context, "wifi");
            execute(context, "bluetooth");
            execute(context, "mute");
        }

        return "Power save activated";
    }
}
