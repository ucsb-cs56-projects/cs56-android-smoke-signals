package com.konukoii.smokesignals;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.konukoii.smokesignals.SettingsService;

/**
 * Created by Franklin on 2/21/2016.
 */

//Uses singleton design patterns
public class Settings extends SettingsService { //used to determine the state of the switches across the App
    //private SettingsService settings;
    //declare the variables as Switches, looks cleaner out here but you can move it to onCreate

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
        whit = (Switch) findViewById(R.id.switch10);
        blu = (Switch) findViewById(R.id.switch9);
        pow = (Switch) findViewById(R.id.switch11);
        sta = (Switch) findViewById(R.id.switch12);
        res = (Switch) findViewById(R.id.switch13);


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
        whit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleWhitelist(buttonView);
            }
        });
        blu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleBluetooth(buttonView);
            }
        });
        pow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                togglePower(buttonView);
            }
        });
        sta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                togglePower(buttonView);

            }
        });

        res.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleReset(buttonView);
                if(loc.isChecked()!=res.isChecked())
                {loc.toggle();}
                if(loc.isChecked()!=res.isChecked()){con.toggle();}
                if(bat.isChecked()!=res.isChecked()){bat.toggle();}
                if(cal.isChecked()!=res.isChecked()){cal.toggle();}
                if(jok.isChecked()!=res.isChecked()){jok.toggle();}
                if(tex.isChecked()!=res.isChecked()){tex.toggle();}
                if(wif.isChecked()!=res.isChecked()){wif.toggle();}
                if(whit.isChecked()!=res.isChecked()){whit.toggle();}
                if(blu.isChecked()!=res.isChecked()){blu.toggle();}
                if(pow.isChecked()!=res.isChecked()){pow.toggle();}
                if(sta.isChecked()!=res.isChecked()){sta.toggle();}
                if(rin.isChecked()!=res.isChecked()){rin.toggle();}
                if(con.isChecked()!=res.isChecked()){con.toggle();}
            }
        });
        Log.d("onCreate","onCreate");

    }


    protected void onPause() {
        super.onPause();
        //makes sure that the moment you back out of the app, settings will automatically save
        this.saveSettings();
        Log.d("onPause","onPause");
    }



    // GETTER FUNCTIONS
    //gets the state of the functions
    //try to make these static so you can use them in SMSRequestManager
    public static boolean getLocation() {
        return location;
    }

    public static boolean getContact() {
        return contact;
    }

    public static boolean getCalls() {
        return calls;
    }

    public static boolean getBattery() {
        return battery;
    }

    public static boolean getRing() {
        return ring;
    }

    public static boolean getJoke() {
        return joke;
    }

    public static boolean getSms() {
        return sms;
    }

    public static boolean getWifi(){
        return wifi;
    }

    public static boolean getWhitelist(){
        return whitelist;
    }

    public static boolean getBluetooth(){ return bluetooth; }

    public static boolean getPower(){
        return power;
    }
    
    public static boolean getStatus(){
        return status; 
    }

    public static boolean getReset(){return reset;}


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

    public void toggleWhitelist(View view) {
        whitelist = whit.isChecked();
        whit.setChecked(whitelist);

        if (whitelist) {
            Log.d("toggleWhitelist", "Whitelist is on");
        } else {
            Log.d("toggleWhitelist", "Whitelist is off");
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

    public void togglePower(View view) {
        power = pow.isChecked();
        pow.setChecked(power);

        if(power){
            Log.d("togglePower", "Power is on");
        }
        else{
            Log.d("togglePower", "Power is off");
        }
        this.saveSettings();
    }
    
    public void toggleStatus(View view){
        status = sta.isChecked(); 
        sta.setChecked(status); 
        if(status){
            Log.d("toggleStatus", "Status is on"); 
        }
        else {
            Log.d("toggleStatus", "Status is off"); 
        }
        this.saveSettings();
    }

    public void toggleReset(View view){
        reset = res.isChecked();
        res.setChecked(reset);
        if (reset){
            Log.d("toggleReset", "Reset is on");
        }
        else{
            Log.d("toggleReset", "Reset is off");
        }
        this.saveSettings();
    }
}
