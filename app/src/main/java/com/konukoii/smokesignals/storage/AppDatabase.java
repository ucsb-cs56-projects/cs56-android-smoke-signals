package com.konukoii.smokesignals.storage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by porterhaet on 11/6/17.
 */

@Database(entities = {PhoneNumber.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase{
    abstract PhoneNumberDao phoneNumberDao();
}
