package edu.ucsb.cs56.projects.androidapp.smokesignals.storage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by porterhaet on 11/6/17.
 */

@Database(entities = {PhoneNumber.class}, version = 2)
public abstract  class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;

    public abstract PhoneNumberDao phoneNumberDao();


    public static AppDatabase getDataBaseInstance(Context context) {
        if (INSTANCE == null) {
            return  Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "database-name").build();
        }
        else {
            return INSTANCE;
        }
    }
}
