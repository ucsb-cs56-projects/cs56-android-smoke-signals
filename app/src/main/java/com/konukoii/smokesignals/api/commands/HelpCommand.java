package com.konukoii.smokesignals.api.commands;

import android.content.Context;

import com.konukoii.smokesignals.R;
import com.konukoii.smokesignals.api.Command;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class HelpCommand implements Command {

    /**
     * Returns help text
     * @param context Context
     * @param args An empty array
     * @return help text
     */
    public String execute(Context context, String[] args) {
        return context.getResources().getString(R.string.help_text);
    }
}
