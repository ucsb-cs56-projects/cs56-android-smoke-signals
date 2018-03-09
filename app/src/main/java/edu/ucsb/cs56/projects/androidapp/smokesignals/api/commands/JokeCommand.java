package edu.ucsb.cs56.projects.androidapp.smokesignals.api.commands;

import android.content.Context;

import edu.ucsb.cs56.projects.androidapp.smokesignals.R;
import edu.ucsb.cs56.projects.androidapp.smokesignals.api.Command;
import edu.ucsb.cs56.projects.androidapp.smokesignals.api.commands.validators.NArgValidator;

import java.util.Random;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class JokeCommand extends NArgValidator implements Command {

    public JokeCommand() {
        super(0);
    }

    @Override
    public String getUsage() {
        return "//joke";
    }

    public String execute(Context context, String[] args) {
        final String[] jokes = context.getResources().getStringArray(R.array.jokes);
        return jokes[(new Random()).nextInt(jokes.length)];
    }
}
