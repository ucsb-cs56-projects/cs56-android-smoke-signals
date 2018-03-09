package edu.ucsb.cs56.projects.androidapp.smokesignals.api;

import android.content.Context;

import edu.ucsb.cs56.projects.androidapp.smokesignals.api.commands.validators.Validator;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public interface Command extends Validator {
    String getUsage();
    String execute(Context context, String[] args);
}
