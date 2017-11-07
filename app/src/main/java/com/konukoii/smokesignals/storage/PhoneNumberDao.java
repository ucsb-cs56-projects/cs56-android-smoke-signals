package com.konukoii.smokesignals.storage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by porterhaet on 11/6/17.
 */

@Dao
public interface PhoneNumberDao {

    @Query("SELECT number from phonenumber")
    List<PhoneNumber> getAll();

    @Insert
    void addPhoneNumber(PhoneNumber number);
}
