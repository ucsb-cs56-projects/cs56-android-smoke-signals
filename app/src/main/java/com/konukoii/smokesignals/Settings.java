package com.konukoii.smokesignals;

/**
 * Created by Franklin on 2/21/2016.
 */
public class Settings { //used to determine the state of the switches across the App
    private static boolean location = true; //"a"
    private static boolean contact = true; //"b"
    private static boolean calls = true; //"c"
    private static boolean battery = true; //"d"
    private static boolean ring = true; //"e"
    private static boolean joke = true;



    // SETTER FUNCTIONS
    //sets the state of the app functions to true or false
    public void toggleLocation(boolean state){
        location = state;
    }
    public void toggleContact(boolean state){
        contact = state;
    }
    public void toggleCalls(boolean state){
        calls = state;
    }
    public void toggleBattery(boolean state){
        battery = state;
    }
    public void toggleRing(boolean state){
        ring = state;
    }
    public void toggleJoke(boolean state) {
        joke = state;
    }

    // GETTER FUNCTIONS
    //gets the state of the functions
    public boolean getLocation(){
        return location;
    }
    public boolean getContact(){
        return contact;
    }
    public boolean getCalls(){
        return calls;
    }
    public boolean getBattery(){
        return battery;
    }
    public boolean getRing(){
        return ring;
    }
    public boolean getJoke(){
        return joke;
    }



}
