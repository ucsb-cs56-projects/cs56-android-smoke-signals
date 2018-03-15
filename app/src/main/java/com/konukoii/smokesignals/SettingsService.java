package com.konukoii.smokesignals;

/**
 * Created by Juan on 3/14/18.
 */

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.konukoii.smokesignals.Settings;
import android.content.Context;

public class SettingsService extends Activity{

    //save the preferences of the settings
    public void saveSettings() {
        //make sure that the settings are private so other function
        SharedPreferences sharePref = getSharedPreferences("settings", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharePref.edit();
        edit.putBoolean("location", loc.isChecked());
        edit.putBoolean("contacts", con.isChecked());
        edit.putBoolean("battery", bat.isChecked());
        edit.putBoolean("calls", cal.isChecked());
        edit.putBoolean("ring", rin.isChecked());
        edit.putBoolean("jokes", jok.isChecked());
        edit.putBoolean("sms", tex.isChecked());
        edit.putBoolean("wifi",wif.isChecked());
        edit.putBoolean("whitelist",whit.isChecked());
        edit.putBoolean("bluetooth",blu.isChecked());
        edit.putBoolean("power",pow.isChecked());
        edit.putBoolean("status",sta.isChecked());
        edit.putBoolean("reset", res.isChecked());
        //it settles the variables that you added
        edit.apply();

        Toast.makeText(this, "Saved the settings!", Toast.LENGTH_LONG).show();
    }



    public void setSettings() {
        SharedPreferences sharePref = getSharedPreferences("settings", Context.MODE_PRIVATE);

        this.setLocation(sharePref.getBoolean("location", true));
        this.setContact(sharePref.getBoolean("contacts", true));
        this.setBattery(sharePref.getBoolean("battery", true));
        this.setCalls(sharePref.getBoolean("calls", true));
        this.setRing(sharePref.getBoolean("ring", true));
        this.setJoke(sharePref.getBoolean("jokes", true));
        this.setSMS(sharePref.getBoolean("sms", true));
        this.setWifi(sharePref.getBoolean("wifi",true));
        this.setWhitelist(sharePref.getBoolean("whitelist",true));
        this.setBluetooth(sharePref.getBoolean("bluetooth",true));
        this.setPower(sharePref.getBoolean("power",true));
        this.setStatus(sharePref.getBoolean("status",true));
        this.setReset(sharePref.getBoolean("reset", true));

        Toast.makeText(this, "Settings are set!", Toast.LENGTH_LONG).show();
    }

    Switch loc;
    Switch con;
    Switch bat;
    Switch cal;
    Switch rin;
    Switch jok;
    Switch tex;
    Switch wif;
    Switch whit;
    Switch blu;
    Switch pow;
    Switch sta;
    Switch res;



    //Shared variables so that every instance knows the state of the switches.
    protected static boolean location = true;
    protected static boolean contact = true;
    protected static boolean calls = true;
    protected static boolean battery = true;
    protected static boolean ring = true;
    protected static boolean joke = true;
    protected static boolean sms = true;
    protected static boolean wifi = true;
    protected static boolean whitelist = true;
    protected static boolean bluetooth = true;
    protected static boolean power = true;
    protected static boolean status = true;
    protected static boolean reset = true;

    // SETTER FUNCTIONS
    //sets the state of the app functions to true or false
    // The first line is to set the button's state
    // The second line is to update the state of the static variables for SMSRequestManager to check if the button is on or not
    public void setLocation(boolean state) {
        loc.setChecked(state);
        location = state;
    }

    public void setContact(boolean state) {
        con.setChecked(state);
        contact = state;
    }

    public void setCalls(boolean state) {
        cal.setChecked(state);
        calls = state;
    }

    public void setBattery(boolean state) {
        bat.setChecked(state);
        battery = state;
    }

    public void setRing(boolean state) {
        rin.setChecked(state);
        ring = state;
    }

    public void setJoke(boolean state) {
        jok.setChecked(state);
        joke = state;
    }

    public void setSMS(boolean state) {
        tex.setChecked(state);
        sms = state;
    }

    public void setWifi(boolean state) {
        wif.setChecked(state);
        wifi = state;
    }

    public void setWhitelist(boolean state){
        whit.setChecked(state);
        whitelist=state;
    }
    public void setBluetooth(boolean state) {
        blu.setChecked(state);
        bluetooth = state;
    }
    public void setPower(boolean state) {
        pow.setChecked(state);
        power = state;
    }

    public void setStatus(boolean state){
        sta.setChecked(state);
        status = state;
    }

    public void setReset(boolean state){
        res.setChecked(state);
        reset = state;

    }


}
