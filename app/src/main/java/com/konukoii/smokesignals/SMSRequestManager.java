package com.konukoii.smokesignals;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import android.telephony.SmsManager;

import java.util.ArrayList;
import java.util.Date;
import android.content.IntentFilter;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.location.Location;
import android.location.LocationManager;
import java.util.Calendar;
import java.util.Random;

import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.wifi.WifiManager;

/**
 * Created by TransAtlantic on 2/14/2015.
 */



public class SMSRequestManager extends Service { //idk why I changed it to service

    //Debuggin' Purposes
    private final static String TAG="SmokeSignals";

    //Void Read from file the messageCue

    private final static int LOCATION = 1;
    private final static int CONTACTSEARCH = 2;
    private final static int MISSEDCALLS = 3;
    private final static int BATTERYLIFE = 4;
    private final static int RING = 5;
    private final static int HELP = 6;
    private final static int JOKE = 7;
    private final static int SMS = 8;
    private final static int WIFI = 9;
    private final static int BLUETOOTH = 10;
    private final static int POWERSAVE = 11;


    private static String J0 = "Will my college degree come in Fahrenheit or Celsius?";
    private static String J1 = "Why do people come back from baby changing stations with the same baby?";
    private static String J2 = "If light travels faster than the speed of sound, how come I hear the guy in the BMW behind me honk before the light turns green?";
    private static String J3 = "Does the five-second rule apply to soup? Please hurry.";
    private static String J4 = "I am now 22 and my eyesight is worsening, at what point do I get adult supervision?";
    private static String J5 = "Is the ocean salty because the land doesn't wave back?";
    private static String J6 = "If I flip a coin 1,000,000 times, what are the odds of me wasting my time?";
    private static String J7 = "How many calories does my girlfriend burn by jumping to conclusions?";
    private static String J8 = "I heard Mars has no atmosphere. Could we create an atmosphere by dimming the lights and playing smooth jazz?";
    private static String J9 = "Do spiders in Europe have 2.4384 meters instead of 8 feet?";
    private static String J10 = "Why couldn't the bike stand on its own?\n" + "Because it's two tired";
    private static String J11 = "What do you call an elephant that doesn't matter?\n" + "irrelephant!";
    private static String J12 = "Student debt\n";
    private static String J13 = "What do you get when you cross a snowman with a vampire?\n" + "Frostbite.";
    private static String J14 = "Can YouTube slow down time? I just read that they upload 300 hours of video every 1 minute.";
    private static String J15 ="If electricity always follows the path of least resistance, why doesn't lightning only strike in France?";
    private static String J16 = "In American, someone is shot every 15 seconds. How is that person still alive?";
    private static String J17 = "I've already squirted two whole bottles of 'no tears' baby shampoo into my daughter's face. Why is she still crying?";
    private static String J18 = "If I heat my solid state hard drive until it becomes a gaseous state hard drive, would that enable cloud computing?";
    private static String J19 = "My doctor said he's been practicing for 30 years. When will he start doing his job for real?";
    private static String J20 = "At what point in a bobcat's life, as it grows and matures, does it prefer to be called a robertcat?";
    private static String J21 = "If animals don't want to be eaten why are they made of food?";



    private final static String[] JOKES = {J0,J1,J2,J3,J4,J5,J6,J7,J8,J9,J10,J11,J12,J13,J14,J15,J16,J17,J18,J19,J20,J21};
    private final static int numJokes = JOKES.length;
    private final static String HELP_TXT = "TEXT ME:\n'//Location' <- To query GPS coordinates\n" +
                                                    "'//Contact [name]' <- For contact search\n" +
                                                    "'//Calls' <- To query missed calls\n" +
                                                    "'//Battery' <-To query battery life and charging status\n"+
                                                    "'//Ring' <-For phone to start ringing (for 2 Minutes)\n"+
                                                    "'//Joke' <-To get a lame joke\n"+
                                                    "'//Help' <-To display this help menu again\n" +
                                                    "'//SMS [number] [message]' <-To send a text message to a 10-digit phone number\n" +
                                                    "'//Wifi' <-To get the wifi state of the phone\n" +
                                                    "'//Bluetooth' <-To get the bluetooth state of the phone\n" +
                                                    "'//PowerSave [function name]' <-To turn off function";


    Context context;    //The context that called this
    Intent intent;      //The intent that called this
    String msg_from;    //Who is the app talking to
    MediaPlayer ringerPlayer; //The MediaPlayer for the Ringer

    //Void Go
        //Main thing from where everything stems from
    public void go(Context context, Intent intent){
        this.context = context;
        this.intent = intent;

        Log.d(TAG, "New SMS Arrived");

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        msg_from="";
        String msg_body="";
        if (bundle != null) {
            try {
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                for (int i = 0; i < msgs.length; i++) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    msg_from = msgs[i].getOriginatingAddress();
                    msg_body = msgs[i].getMessageBody();
                }
                parseSMS(msg_body);

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }



    //ParseCmd
    private int parseSMS(String msg_body){
        int spaceIndex = msg_body.indexOf(" ");
        String msg_header;
        if (spaceIndex == -1) {
            msg_header = msg_body;
            msg_body = "";
        }
        else{
            msg_header = msg_body.substring(0, spaceIndex); 
            msg_body = msg_body.substring(msg_header.length()); 
            msg_body = msg_body.replaceAll("^\\s+", ""); 
        }   
        msg_header = msg_header.substring(0, 2) + Character.toUpperCase(msg_header.charAt(2)) + (msg_header.substring(3)).toLowerCase();
        if (!(msg_header.equals("//Sms"))){
            msg_body = msg_body.replaceFirst("\\s++$", "");
        }
        
        if (msg_header.equals("//Location")){
            if (Settings.getLocation()) {
                Toast.makeText(context, "Location?", Toast.LENGTH_LONG).show();
                QueryLocation();
                return LOCATION;
            }
            else{
                return 0;
            }
        }
        else if (msg_header.equals("//Joke")){
            if (Settings.getJoke()) {
                Toast.makeText(context, "Joke?", Toast.LENGTH_LONG).show();
                QueryJokes();
                return JOKE;
            }
            else{
                Toast.makeText(context, "Joke is off", Toast.LENGTH_LONG).show();
                return 0;
            }
        }
        else if (msg_header.equals("//Ring")){
            if (Settings.getRing()) {
                Toast.makeText(context, "Ring?", Toast.LENGTH_LONG).show();
                QueryRing();
                return RING;
            }
            else{
                Toast.makeText(context, "Ring is off",Toast.LENGTH_LONG).show();
                return 0;
            }
        }
        else if (msg_header.equals("//Battery")){
            if (Settings.getBattery()) {
                Toast.makeText(context, "Battery?", Toast.LENGTH_LONG).show();
                QueryBattery();
                return BATTERYLIFE;
            }
            else {
                Toast.makeText(context, "Battery is off", Toast.LENGTH_LONG).show();
                return 0;
            }
        }
        else if (msg_header.equals("//Calls")){
            if (Settings.getCalls()) {
                Toast.makeText(context, "Calls?", Toast.LENGTH_LONG).show();
                QueryMissedCalls();
                return MISSEDCALLS;
            }
            else {
                Toast.makeText(context, "Calls is off", Toast.LENGTH_LONG).show();
                return 0;
            }
        }
        else if (msg_header.equals("//Help")){
            Toast.makeText(context, "Help?", Toast.LENGTH_LONG).show();
            QueryHelp();
            return HELP;
        }
        else if (msg_header.equals("//Wifi")){
            if (Settings.getWifi()) {
                if(msg_body.equals("")){
                    Toast.makeText(context, "Wifi Status?", Toast.LENGTH_LONG).show();
                    QueryWifi();
                    return WIFI;
                }
                else{
                    QueryWifi(msg_body);
                    return WIFI;
                }
            }
            else{
                Toast.makeText(context, "Wifi is off", Toast.LENGTH_SHORT).show();
                return 0;
            }
        }
        else if (msg_header.equals("//Bluetooth")){
            if (Settings.getBluetooth()) {
                if (msg_body.equals("")){
                    Toast.makeText(context, "Bluetooh Status?", Toast.LENGTH_SHORT).show();
                    QueryBluetooth();
                    return BLUETOOTH;
                }
                else {
                    QueryBluetooth(msg_body);
                    return BLUETOOTH;
                }
            }
            else{
                Toast.makeText(context, "Bluetooth is off", Toast.LENGTH_SHORT).show();
                return 0;
            }
        }
        else if (msg_header.equals("//Powersave")){
            if(Settings.getPower()){
                if(msg_body.equals("")){
                    sendSMS(msg_from, "//Powersave [function name] <- please enter a function (wifi, bluetooth, mute, or all) after //Powersave to turn it off.");
                }
                else {
                    QueryPower(msg_body);
                }
                return POWERSAVE;
            }
            else{
                Toast.makeText(context, "Powersave is off", Toast.LENGTH_SHORT).show();
                return 0;
            }
        }
        else if (msg_header.equals("//Contact")){
            if (Settings.getContact()) {
                Toast.makeText(context, "Contact?", Toast.LENGTH_LONG).show();
                if(msg_body.equals("")){
                    sendSMS(msg_from, "//Contact [name] <- please enter a name (3 or more letters) after //Contact command to find a contact.");
                    return CONTACTSEARCH;
                }
                else {
                    QueryContact(msg_body);
                }
                return CONTACTSEARCH;
            }
            else {
                Toast.makeText(context, "Contact is off", Toast.LENGTH_LONG).show();
                return 0;
            }
        }
        else if (msg_header.equals("//Sms")){
            if(Settings.getSms()) {
                Toast.makeText(context, "SMS?", Toast.LENGTH_LONG).show();
                if(msg_body.length() <= 11){
                    sendSMS(msg_from, "//SMS [number] [message] <- please enter a 10 digit phone number, followed by a message after //SMS to send an SMS message.");
                    return SMS;
                }
                else {
                    QuerySMS(msg_body);
                }
                return SMS;
            }
            else {
                Toast.makeText(context, "SMS is off", Toast.LENGTH_LONG).show();
                return 0;
            }
        }
        else if (msg_header.substring(0,5).equals("//Loc")
                ||msg_header.substring(0,5).equals("//Con")
                ||msg_header.substring(0,5).equals("//Cal")
                ||msg_header.substring(0,5).equals("//Bat")
                ||msg_header.substring(0,5).equals("//Rin")
                ||msg_header.substring(0,5).equals("//Jok")
                ||msg_header.substring(0,5).equals("//Hel")
                ||msg_header.substring(0,5).equals("//Wif")
                ||msg_header.substring(0,5).equals("//Blu")
                ||msg_header.substring(0,5).equals("//Pow")
                ){
            // if the first three letters of user's command matches the first three of any, but doesn't match a command
            // but doesn't match a command we'll send them the help text
            QueryHelp();
            return 0;
        }
        return 0;
    }


    //Respond_to_SMS
    private void sendSMS(String phoneNumber, String message){

        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(message);
        sms.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
        //sms.sendTextMessage(phoneNumber, null, message, null, null);
        Log.d(TAG, "message sent!");
    }

    //Query Functions//////////////////////////////////////////////////////////////////////////////
    private void QueryHelp(){sendSMS(msg_from, HELP_TXT);}

    private void QueryBattery(){context.registerReceiver(this.BatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));}

    private void QueryLocation(){
        GPSLocation gpsloc = new GPSLocation();
        gpsloc.go();
    }

    private void QueryMissedCalls(){

        //Get all the Call Log
        String[] projection = { CallLog.Calls.CACHED_NAME, CallLog.Calls.CACHED_NUMBER_LABEL, CallLog.Calls.TYPE };

        //Query to find which calls in the Call Log are MISSED and NEW (Haven't been awknoledged by user)
        String where = CallLog.Calls.TYPE+"="+CallLog.Calls.MISSED_TYPE+" AND NEW = 1";
        Cursor c = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,null,where, null, null);

        //Check if there's no missed calls...or negative missed calls? :S
        if (c.getCount() <=0){
            sendSMS(msg_from,"No missed calls...I am lonely"); //lulz remember to change this
        }

        //Make a nice list of missed calls (Hopefully you don't have 42 missed phone calls from you girlfriend in the last hour)
        c.moveToFirst();



        String output="MISSED CALLS:";
        int number = c.getColumnIndex(CallLog.Calls.NUMBER);
        int name = c.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int date = c.getColumnIndex(CallLog.Calls.DATE);

        do{ //Because you know you have at least one

            String phNumber = c.getString(number);
            String callDate = c.getString(date);
            String callerName = c.getString(name);
            Date callDayTime = new Date(Long.valueOf(callDate));

            output+="\nName: "+callerName+"\nPhone Number: " + phNumber +"\nCall Date: " + callDayTime;
            output+="\n-------";

        }while(c.moveToNext());

        sendSMS(msg_from,output);

        Log.d(TAG, output); //do some other operation

    }

    private void QueryContact(String query){

        /*
         Explanation for this module:

            Reference Links: http://stackoverflow.com/questions/9625308/android-find-a-contact-by-display-name

         */

        //DO NOT DELETE THIS. Believe me, I have seen what happens when you indiscriminatly query for 'a' and suddenly your phone is dumping all your contacts!
        if (query.length()<=2){
            sendSMS(msg_from,"Query is too short. Please provide a contact query at least of at 3 characters");
            return;
        }

        //First get the List of All possible Contacts with their IDs
        String _query = query;
        query = "%"+query+"%"; //Super important for the SQL LIKE command (LIKE %ed% returns true to EDuardo, pEDro, etc. (also LIKE is not case sensitive!)
        String id_name=null;
        Uri resultUri = ContactsContract.Contacts.CONTENT_URI;
        Cursor cont = context.getContentResolver().query(resultUri, null, null, null, null);
        String whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME + " LIKE ?" ;
        String[] whereNameParams = new String[] { ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE,query};

        Cursor nameCur = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams, StructuredName.CONTACT_ID);

        String output = "Possible Matches:\n";

        //We are going to store phones here, so we can check that we don't print duplicate info
        //This is necessary because Android is a mess when it comes to storing contacts
        //So don't be surprised if you have tooooons of weird duplicates
        ArrayList<String> contacts = new ArrayList<String>();

        //No Contact Found
        if (nameCur.getCount()<=0){ sendSMS(msg_from,"No contact found matching "+_query);}

        //Loop through the IDs
        while (nameCur.moveToNext()) {

            String name="";
            String phone="";
            String email="";
            String id="";

            //USE THE IDs you got to find the rest of the information
            id = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID));

            //FIND NAME;
            name = nameCur.getString(nameCur.getColumnIndex(StructuredName.DISPLAY_NAME));

            //FIND NUMBER
            whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?";
            String[] whereNameParams2 = new String[]{ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, id};
            Cursor nameCur2 = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams2, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
            while (nameCur2.moveToNext()) {
                phone = nameCur2.getString(nameCur2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            nameCur2.close();

            //FIND EMAIL
            String[] whereNameParams3 = new String[]{ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE, id};
            Cursor nameCur3 = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams3, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
            while (nameCur3.moveToNext()) {
                email = nameCur3.getString(nameCur3.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            }
            nameCur3.close();

            //CHECK FOR DUPLICATES
            boolean flag=false;
            for (int i=0;i<contacts.size();i++){
                if (contacts.get(i).equals(phone)){flag=true;}
            }
            if (flag==true){continue;}

            contacts.add(phone);

            //PRINTOUT
            if (phone != "") {
                output += "\nName: " + name;
                output += "\nPhone: " + phone;
                if (email != "") {
                    output += "\nEmail: " + email;
                }
                output += "\n-----";
            }
        }

        //Since we are explicitly not allowing searching for contacts that only have emails associated with them
        if (output.equals("Possible Matches:\n")){ sendSMS(msg_from,"No contact found matching "+_query);return;}

        sendSMS(msg_from, output);

    }

    private void QueryRing(){

        //Si te vas por ringtone la broma no hace looping
        //Si te vas por MediaPlayer la broma insiste en crashea
        //Cuando decifres esto, montas un broadcast reciever para ACTION_USER_PRESENT para apagar el sonido
        //mosca que dejaste un MediaPlayer variable declarado por all'a arriba :)
        //chamo si me provoca una arepa y un papelon con limon ahorita

        //1. Get Alarm Sound
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alert == null){
            // alert is null, using backup
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            // I can't see this ever being null (as always have a default notification)
            // but just incase
            if(alert == null) {
                // alert backup is null, using 2nd backup
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }
        //2. Raise Volume
        final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        audioManager.setStreamVolume(AudioManager.STREAM_ALARM,maxVolume,0);

        //make sure that the audio is on
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        //3. Play Alarm
        final Ringtone r = RingtoneManager.getRingtone(context, alert);
        r.play();

        /*
        try {
            ringerPlayer = new MediaPlayer();
            ringerPlayer = MediaPlayer.create(context, alert);
            ringerPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
            ringerPlayer.setLooping(true);
            ringerPlayer.prepare();
            ringerPlayer.start();
        }catch (Exception e){ Log.d(TAG,e.getMessage());}
        */
    }

    private void QueryJokes(){
        Random rand = new Random();
        sendSMS(msg_from, JOKES[rand.nextInt(numJokes)]);
    }

    private void QuerySMS(String query){
        // if the length of the query (stuff after //SMS) is less than 10 numbers, plus a space, plus at least one character, send them help
        String phoneNum = query.substring(0,10);
        String message = query.substring(11);
        sendSMS(phoneNum,message);
        sendSMS(msg_from,"Sent message to "+ phoneNum + " : " + message);
    }

    private void QueryWifi(){
        //Tell me that this function is being called
        Toast.makeText(context, "Fired up QueryWifi",Toast.LENGTH_SHORT).show();

        //make sure context is used for getSystemService
        WifiManager access = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if(access.isWifiEnabled()){
            sendSMS(msg_from, "Wifi is on");
        }
        else{
            sendSMS(msg_from, "Wifi is off");
        }
    }

    private void QueryWifi(String funct){
        //Tell me that this function is being called
        Toast.makeText(context, "Fired up QueryWifi",Toast.LENGTH_SHORT).show();

        //make sure context is used for getSystemService
        WifiManager access = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if(funct.equals("on")){
            access.setWifiEnabled(true);
            Toast.makeText(context,"Wifi enabled", Toast.LENGTH_SHORT).show();
        }
        if(funct.equals("off")){
            access.setWifiEnabled(false);
            Toast.makeText(context,"Wifi disabled", Toast.LENGTH_SHORT).show();
        }
    }

    private void QueryBluetooth(){
        //Tell me that this function is being called
        Toast.makeText(context, "Fired up QueryBluetooth",Toast.LENGTH_SHORT).show();

        //make sure context is used for getSystemService
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter.isEnabled()){
            sendSMS(msg_from, "Bluetooth is on");
        }
        else{
            sendSMS(msg_from, "Bluetooth is off");
        }
    }

    private void QueryBluetooth(String funct){
        //Tell me that this function is being called
        Toast.makeText(context, "Fired up QueryBluetooth",Toast.LENGTH_SHORT).show();
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(funct.equals("on")){
            mBluetoothAdapter.enable();
            Toast.makeText(context,"Bluetooth enabled", Toast.LENGTH_SHORT).show();
        }
        if(funct.equals("off")) {
            mBluetoothAdapter.disable();
            Toast.makeText(context, "Bluetooth disabled", Toast.LENGTH_SHORT).show();
        }
    }

    private void QueryPower(String funct){
        if(funct.equals("wifi")){
            WifiManager wifiManager = (WifiManager)this.context.getSystemService(Context.WIFI_SERVICE);
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
            QueryPower("wifi");
            QueryPower("bluetooth");
            QueryPower("mute");
        }

    }

//////Broadcast Receivers and Listeners Inner Classes///////////////////////////////////////////////
    /*Broadcasters/Listeners take time to answer. (you can think of them as separate processes.
    //You call them by registering them to the service and when you are done you unregister them.
    //However the service doesn't know when these guys are done doing their thing, so they
    //gotta be inner classes to be able to unregister themselves
    */

    //Battery Broadcast Reciever
    private BroadcastReceiver BatteryReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context arg0, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            String charging = "Charging";
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            if (isCharging == false) charging = "Not Charging";
            sendSMS(msg_from,"Battery Level: "+level+"% " + charging);
            Log.d(TAG,"Sent Battery Level");
            context.unregisterReceiver(this);
        }
    };

    //GPS Location Listener
    public class GPSLocation implements LocationListener{

        /*
            God Bless whoever asked this question: http://stackoverflow.com/questions/10524381/gps-android-get-positioning-only-once
         */

        LocationManager mLocationManager;

        public void go(){
            mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Toast.makeText(context, "GPS is now running to send message", Toast.LENGTH_LONG).show();
            //Get Last Known location (2 minutes old max) [Lowers battery consumption]
            if(location != null && location.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
                Log.d(TAG, location.getLatitude() + " and " + location.getLongitude());
                String output = "Location:\n"+"Lat: "+location.getLatitude() + " Long: "+
                        location.getLongitude()+"\nGmaps: "+" http://google.com/maps/?q="+location.getLatitude()+","+location.getLongitude();

                sendSMS(msg_from,output);
                mLocationManager.removeUpdates(this); //Super Important to RemoveUpdates (only want to query once)
            }
            else {
                if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) { //this checks a boolean
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                }else{
                    sendSMS(msg_from,"GPS is Turned Off! Can't Report Location :'(");
                }
            }
        }

        @Override
        public void onLocationChanged(Location location){
            if (location !=null){
                Log.d(TAG, location.getLatitude() + " and " + location.getLongitude());
                String output = "Location:\n"+"Lat: "+location.getLatitude() + " Long: "+
                        location.getLongitude()+"\nGmaps: "+" http://google.com/maps/?q="+location.getLatitude()+","+location.getLongitude();

                sendSMS(msg_from,output);
                mLocationManager.removeUpdates(this); //Super Important to RemoveUpdates (only want to query once)
            }
        }
        // Required functions
        @Override
        public void onProviderDisabled(String arg0) {}
        @Override
        public void onProviderEnabled(String arg0) {}
        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }



////////////////////////////////////////////////////////////////////////////////////////////////////

}
