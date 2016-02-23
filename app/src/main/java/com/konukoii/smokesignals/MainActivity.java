package com.konukoii.smokesignals;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends ActionBarActivity {

    Settings toggle = new Settings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //start the service from here //MainService is your service class name
        startService(new Intent(this, MainService.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            setContentView(R.layout.settings_main);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //SWITCH FUNCTIONS
    //these functions will change the state of the
    public void switchLocation(View view){
        Switch location = (Switch) findViewById(R.id.switch1);
        toggle.toggleLocation(location.isChecked());
        if(location.isChecked() == true){
            Log.d("switchLocation","Location is on");
        }
        else{
            Log.d("switchLocation", "Location is off");
        }
    }

    public void switchRing(View view){
        Switch ring = (Switch) findViewById(R.id.switch5);
        toggle.toggleRing(ring.isChecked());
        if(ring.isChecked() == true){
            Log.d("switchRing","Ring is on");
        }
        else{
            Log.d("switchRing","Ring is off");
        }
    }
    public void switchBattery(View view){
        Switch battery = (Switch) findViewById(R.id.switch3);
        toggle.toggleBattery(battery.isChecked());
        if(battery.isChecked() == true){
            Log.d("switchRing","Battery is on");
        }
        else{
            Log.d("switchRing","Battery is off");
        }
    }

    public void switchCalls(View view){
        Switch calls = (Switch) findViewById(R.id.switch4);
        toggle.toggleCalls(calls.isChecked());
        if(calls.isChecked() == true){
            Log.d("switchRing","Calls is on");
        }
        else{
            Log.d("switchRing","Calls is off");
        }
    }

    public void switchContacts(View view){
        Switch contact = (Switch) findViewById(R.id.switch4);
        toggle.toggleContact(contact.isChecked());
        if(contact.isChecked() == true){
            Log.d("switchContacts", "Contact is on");
        }
        else{
            Log.d("switchContacts","Contact is off");
        }
    }
    public void switchJokes(View view){
        Switch joke = (Switch) findViewById(R.id.switch6);
        toggle.toggleJoke(joke.isChecked());
        if(joke.isChecked() == true){
            Log.d("switchJokes","Jokes is on");
        }
        else{
            Log.d("switchJokes","Jokes is off");
        }
    }

    public void switchSms(View view){
        Switch sms = (Switch) findViewById(R.id.switch7);
        toggle.toggleSMS(sms.isChecked());
        if(sms.isChecked() == true){
            Log.d("switchSms","SMS is on");
        }
        else Log.d("switchSms","SMS is off");
    }

}
