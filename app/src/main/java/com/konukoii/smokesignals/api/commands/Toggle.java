package com.konukoii.smokesignals.api.commands;

/**
 * Created by ankushrayabhari on 12/4/17.
 */

public class Toggle {
    public static boolean getNewStatus(boolean status, String arg) {
        switch (arg) {
            case "on":
                status = true;
                break;
            case "off":
                status = false;
                break;
            case "toggle":
                status = !status;
                break;
        }

        return status;
    }
}
