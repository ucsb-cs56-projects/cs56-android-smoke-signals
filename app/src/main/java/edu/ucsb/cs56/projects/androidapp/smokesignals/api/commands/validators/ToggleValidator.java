package edu.ucsb.cs56.projects.androidapp.smokesignals.api.commands.validators;

/**
 * Created by ankushrayabhari on 11/5/17.
 */

public class ToggleValidator implements Validator {

    @Override
    public boolean validate(String[] args) {
        if(args.length >= 1) {
            switch (args[0]) {
                case "on":
                case "off":
                case "toggle":
                    return true;
                default:
                    return false;
            }
        }

        return true;
    }
}
