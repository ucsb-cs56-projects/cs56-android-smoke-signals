package com.konukoii.smokesignals.api.commands;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.konukoii.smokesignals.api.Command;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class LocationCommand implements Command {

    public String execute(Context context, String[] args) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        Location lastKnown = null;

        if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lastKnown = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else if(manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            lastKnown = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if(lastKnown == null) {
            return "Location unavailable";
        }

        return "Latitude: " + lastKnown.getLatitude() + "\n" +
               "Longitude: " + lastKnown.getLongitude() + "\n" +
               "https://www.google.com/maps/search/?api=1&query=" +
                lastKnown.getLatitude() + "," + lastKnown.getLongitude();
    }
}
