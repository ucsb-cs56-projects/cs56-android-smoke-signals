package com.konukoii.smokesignals;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;



/**
 * Created by Franklin on 2/21/2016.
 */
public class Settings extends Activity { //used to determine the state of the switches across the App

    Switch loc;
    Switch con;
    Switch bat;
    Switch cal;
    Switch rin;
    Switch jok;
    Switch tex;
    Switch wif;
    Switch blu;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main);
        loc = (Switch) findViewById(R.id.switch1);
        con = (Switch) findViewById(R.id.switch2);
        bat = (Switch) findViewById(R.id.switch3);
        cal = (Switch) findViewById(R.id.switch4);
        rin = (Switch) findViewById(R.id.switch5);
        jok = (Switch) findViewById(R.id.switch6);
        tex = (Switch) findViewById(R.id.switch7);
        wif = (Switch) findViewById(R.id.switch8);
        blu = (Switch) findViewById(R.id.switch9);
        this.setSettings();
        loc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleLocation(buttonView);
            }
        });
        con.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleContact(buttonView);
            }
        });
        bat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleBattery(buttonView);
            }
        });
        cal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleCalls(buttonView);
            }
        });
        rin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleRing(buttonView);
            }
        });
        jok.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleJoke(buttonView);
            }
        });
        tex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleSMS(buttonView);
            }
        });
        wif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleWifi(buttonView);
            }
        });
        blu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleBluetooth(buttonView);
            }
        });
        Log.d("onCreate","onCreate");

    }

    protected void onPause() {
        super.onPause();
        this.saveSettings();
        Log.d("onPause","onPause");
    }

    //save the preferences of the settings
    public void saveSettings() {
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
        edit.putBoolean("bluetooth",blu.isChecked());
        edit.apply();

        Toast.makeText(this, "Saved the settings!", Toast.LENGTH_LONG).show();
    }

    //set the preferences of the settings from saved data
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
        this.setBluetooth(sharePref.getBoolean("bluetooth",true));

        Toast.makeText(this, "Settings are set!", Toast.LENGTH_LONG).show();
    }

    //Shared variables so that every instance knows the state of the switches. Kinda cheating but it works.
    private static boolean location = true;
    private static boolean contact = true;
    private static boolean calls = true;
    private static boolean battery = true;
    private static boolean ring = true;
    private static boolean joke = true;
    private static boolean sms = true;
    private static boolean wifi = true;
    private static boolean bluetooth = true;


    // SETTER FUNCTIONS
    //sets the state of the app functions to true or false
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

    public void setBluetooth(boolean state) {
        blu.setChecked(state);
        bluetooth = state;
    }


    // GETTER FUNCTIONS
    //gets the state of the functions
    public boolean getLocation() {
//        return loc.isChecked();
        return location;
    }

    public boolean getContact() {
//        return con.isChecked();
        return contact;
    }

    public boolean getCalls() {
//        return cal.isChecked();
        return calls;
    }

    public boolean getBattery() {
//        return bat.isChecked();
        return battery;
    }

    public boolean getRing() {
//        return rin.isChecked();
        return ring;
    }

    public boolean getJoke() {
//        return jok.isChecked();
        return joke;
    }

    public boolean getSms() {
//        return tex.isChecked();
        return sms;
    }

    public boolean getWifi(){
        return wifi;
    }

    public boolean getBluetooth(){
        return bluetooth;
    }


    //toggle the switches
    public void toggleLocation(View view) {
        location = loc.isChecked();
        loc.setChecked(location);

        if(location){
            Log.d("toggleLocation", "Location is on");
        }
        else{
            Log.d("toggleLocation", "Location is off");
        }
        this.saveSettings();
    }

    public void toggleContact(View view) {
        contact = con.isChecked();
        con.setChecked(contact);

        if(contact){
            Log.d("toggleLocation", "Location is on");
        }
        else{
            Log.d("toggleLocation", "Location is off");
        }
        this.saveSettings();
    }

    public void toggleCalls(View view) {
        calls = cal.isChecked();
        cal.setChecked(calls);

        if(calls){
            Log.d("toggleLocation", "Location is on");
        }
        else{
            Log.d("toggleLocation", "Location is off");
        }
        this.saveSettings();
    }

    public void toggleBattery(View view) {
        battery = bat.isChecked();
        bat.setChecked(battery);

        if(battery){
            Log.d("toggleBattery", "Battery is on");
        }
        else{
            Log.d("toggleBattery", "Battery is off");
        }
        this.saveSettings();
    }

    public void toggleRing(View view) {
        ring = rin.isChecked();
        rin.setChecked(ring);

        if(ring){
            Log.d("toggleRing", "Ring is on");
        }
        else{
            Log.d("toggleRing", "Ring is off");
        }
        this.saveSettings();
    }

    public void toggleJoke(View view) {
        joke = jok.isChecked();
        jok.setChecked(joke);

        if(joke){
            Log.d("toggleJoke", "Joke is on");
        }
        else{
            Log.d("toggleJoke", "Joke is off");
        }
        this.saveSettings();
    }

    public void toggleSMS(View view) {
        sms = tex.isChecked();
        tex.setChecked(sms);

        if(sms){
            Log.d("toggleSMS", "SMS is on");
        }
        else{
            Log.d("toggleSMS", "SMS is off");
        }
        this.saveSettings();
    }

    public void toggleWifi(View view) {
        wifi = wif.isChecked();
        wif.setChecked(wifi);

        if(wifi){
            Log.d("toggleWifi", "Wifi is on");
        }
        else{
            Log.d("toggleWifi", "Wifi is off");
        }
        this.saveSettings();
    }

    public void toggleBluetooth(View view) {
        bluetooth = blu.isChecked();
        blu.setChecked(bluetooth);

        if(bluetooth){
            Log.d("toggleBluetooth", "Bluetooth is on");
        }
        else{
            Log.d("toggleBluetooth", "Bluetooth is off");
        }
        this.saveSettings();
    }
}
