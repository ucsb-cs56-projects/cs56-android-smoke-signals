package com.konukoii.smokesignals.api.commands;

import android.content.Context;
import com.konukoii.smokesignals.api.Command;
import java.util.Random;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class JokeCommand implements Command {
    public String execute(Context context, String[] args) {
        final String[] jokes = context.getResources().getStringArray(jokes);
        return jokes[(new Random()).nextInt(jokes.length)];
    }
}
