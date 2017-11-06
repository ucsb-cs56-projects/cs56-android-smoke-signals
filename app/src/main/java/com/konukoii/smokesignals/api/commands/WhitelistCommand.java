package com.konukoii.smokesignals.api.commands;

import android.content.Context;

import com.konukoii.smokesignals.api.Command;
import com.konukoii.smokesignals.api.commands.validators.NArgValidator;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class WhitelistCommand extends NArgValidator implements Command {

    public WhitelistCommand() {
        super(0);
    }

    public String getUsage() {
        return "";
    }

    public String execute(Context context, String[] args) {
        return "";
    }
}
