package com.konukoii.smokesignals;

/**
 * Created by porterhaet on 11/7/17.
 */

public interface Callback<T> {

    void done(T result);
}
