package com.konukoii.smokesignals.api;

import android.content.Context;

import com.konukoii.smokesignals.api.commands.validators.Validator;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public interface Command extends Validator {
    String getUsage();
    String execute(Context context, String[] args);
}
