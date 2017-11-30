package com.konukoii.smokesignals.api.commands;

import android.content.Context;

import com.konukoii.smokesignals.R;
import com.konukoii.smokesignals.api.Command;
import com.konukoii.smokesignals.api.commands.validators.NArgValidator;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class HelpCommand extends NArgValidator implements Command {

    public HelpCommand() {
        super(0);
    }

    @Override
    public String getUsage() {
        return "//help";
    }

    public String execute(Context context, String[] args) {
        return context.getResources().getString(R.string.help_text);
    }
}
