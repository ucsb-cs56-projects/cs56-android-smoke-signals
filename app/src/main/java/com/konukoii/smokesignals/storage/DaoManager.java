package com.konukoii.smokesignals.storage;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by porterhaet on 11/6/17.
 */

public class DaoManager {

    private static AppDatabase appDatabase;

    public DaoManager(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "database-name")
                    .build();
        }
    }

    public PhoneNumberDao getPhoneNumberDao() {
        return appDatabase.phoneNumberDao();
    }
}
