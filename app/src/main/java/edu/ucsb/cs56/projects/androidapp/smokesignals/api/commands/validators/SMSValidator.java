package edu.ucsb.cs56.projects.androidapp.smokesignals.api.commands.validators;

/**
 * Created by ankushrayabhari on 11/5/17.
 */

public class SMSValidator extends NArgValidator {

    public SMSValidator() {
        super(2);
    }

    @Override
    public boolean validate(String[] args) {
        if(!super.validate(args)) {
            return false;
        }

        return (args[0].length() == 10 || args[0].length() == 9);
    }

}
