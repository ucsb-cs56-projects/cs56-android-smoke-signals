package edu.ucsb.cs56.projects.androidapp.smokesignals.api.commands.validators;

/**
 * Created by ankushrayabhari on 11/5/17.
 */

public class NArgValidator implements Validator {
    int numOfArgs;

    public NArgValidator(int numOfArgs) {
        this.numOfArgs = numOfArgs;
    }

    @Override
    public boolean validate(String[] args) {
        return args.length >= numOfArgs;
    }
}
