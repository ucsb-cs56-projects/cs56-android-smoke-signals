package com.konukoii.smokesignals.api.commands;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.konukoii.smokesignals.api.Command;
import com.konukoii.smokesignals.api.commands.validators.NArgValidator;
import com.konukoii.smokesignals.api.commands.validators.PowersaveValidator;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class PowersaveCommand extends PowersaveValidator implements Command {

    @Override
    public String getUsage() {
        return "//powersave [wifi|bluetooth|audio - optional]";
    }

    public String execute(Context context, String[] args) {
        if (args.length == 0) {
            disableWifi(context);
            disableAudio(context);
            disableBluetooth(context);
        } else {
            switch(args[0]) {
                case "wifi":
                    disableWifi(context);
                    break;
                case "bluetooth":
                    disableBluetooth(context);
                    break;
                case "audio":
                    disableAudio(context);
                    break;
            }
        }

        return "Powersave activated";
    }


    private void disableWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);
    }

    private void disableBluetooth(Context context) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.disable();
    }

    private void disableAudio(Context context) {
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }
}
