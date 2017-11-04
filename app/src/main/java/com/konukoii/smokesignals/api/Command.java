package com.konukoii.smokesignals.api;

import android.content.Context;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public interface Command {
    String execute(Context context, String[] args);
}
