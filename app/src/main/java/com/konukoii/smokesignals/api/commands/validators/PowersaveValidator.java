package com.konukoii.smokesignals.api.commands.validators;

/**
 * Created by ankushrayabhari on 11/5/17.
 */

public class PowersaveValidator implements Validator {

    @Override
    public boolean validate(String[] args) {
        if(args.length >= 1) {
            switch (args[0]) {
                case "wifi":
                case "bluetooth":
                case "mute":
                    return true;
                default:
                    return false;
            }
        }

        return true;
    }
}
